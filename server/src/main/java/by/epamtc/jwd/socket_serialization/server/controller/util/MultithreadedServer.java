package by.epamtc.jwd.socket_serialization.server.controller.util;

import by.epamtc.jwd.socket_serialization.model.request.Request;
import by.epamtc.jwd.socket_serialization.model.request.RequestOperationEntry;
import by.epamtc.jwd.socket_serialization.model.response.Text;
import by.epamtc.jwd.socket_serialization.server.controller.exception.ControllerException;
import by.epamtc.jwd.socket_serialization.server.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Semaphore;


public class MultithreadedServer extends Thread {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(MultithreadedServer.class);

    private ServerSocket serverSocket;
    private int serverPort;
    private int dayInMillis = 24 * 60 * 60 * 1000;
    private Semaphore semaphore;

    private RequestOperationExecutorProvider executorProvider =
            new RequestOperationExecutorProvider();

    public MultithreadedServer(int serverPort, int maxNumberOfClients) {
        this.serverPort = serverPort;
        this.semaphore = new Semaphore(maxNumberOfClients);
    }

    public void startServer() throws ControllerException {
        try {
            serverSocket = new ServerSocket(serverPort);
            this.start();
        } catch (IOException e) {
            throw new ControllerException("STARTING SERVER SOCKET ERROR", e);
        }
    }

    private boolean isRequestClosing(Request request) {
        return request.getFileName().equals("") && (request.getText() == null);
    }

    public void stopServer() throws ControllerException {
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new ControllerException("STOPPING SERVER SOCKET ERROR");
        }
        this.interrupt();
    }

    @Override
    public void run() {
        long currentTimeMillis = System.currentTimeMillis();
        long stopTime = currentTimeMillis + dayInMillis;
        try {
            while (stopTime > System.currentTimeMillis()) {
                new MultithreadedServerHandler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            LOGGER.error("ERROR WHILE CONNECTING OF A NEW CLIENT", e);
        }
    }

    private class MultithreadedServerHandler extends Thread {
        private Socket clientSocket;
        private ObjectOutputStream oStream;
        private ObjectInputStream iStream;

        public MultithreadedServerHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();

                iStream = new ObjectInputStream(clientSocket.getInputStream());
                oStream = new ObjectOutputStream(clientSocket.getOutputStream());

                Request request = (Request) iStream.readObject();
                while (!isRequestClosing(request)) {
                    Text text = executorProvider.receiveText(request);
                    for (RequestOperationEntry operation : request.getOperations()) {
                        executorProvider.executeAndUpdate(operation, text);
                    }
                    oStream.writeObject(text);
                    request = (Request) iStream.readObject();
                }

            } catch (IOException e) {
                LOGGER.error("ERROR WHILE CLIENT-SERVER COMMUNICATION", e);
            } catch (InterruptedException e) {
                LOGGER.error("MULTITHREADEDHANDLER IS INTERRUPTED WHILE " +
                        "WORKING.");
            } catch (ClassNotFoundException e) {
                LOGGER.error("DIFFERENT CLASS IS READ INSTEAD OF \"{}\"",
                        Request.class.getName(), e);
            } catch (ServiceException e) {
                LOGGER.error("ERROR WHILE ");
            } finally {
                semaphore.release();
                closeCommunicationStreams();
            }
        }

        private void closeCommunicationStreams() {
            try {
                oStream.close();
                iStream.close();
            } catch (IOException e) {
                LOGGER.error("ERROR WHILE CLOSING OF CLIENT-SERVER " +
                        "COMMUNICATION STREAMS", e);
            }
        }
    }

}
