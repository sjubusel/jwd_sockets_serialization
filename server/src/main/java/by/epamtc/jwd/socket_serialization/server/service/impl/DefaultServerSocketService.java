package by.epamtc.jwd.socket_serialization.server.service.impl;

import by.epamtc.jwd.socket_serialization.server.service.ServerSocketService;
import by.epamtc.jwd.socket_serialization.server.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

// https://github.com/eugenp/tutorials/blob/master/core-java-modules/core-java-networking/src/main/java/com/baeldung/socket/EchoMultiServer.java
public class DefaultServerSocketService implements ServerSocketService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultServerSocketService.class);
    private ServerSocket serverSocket;

    @Override
    public void startServer(int port) throws ServiceException {
        try {
            serverSocket = new ServerSocket(port);

            new DefaultServerSocketServiceHandler(serverSocket.accept()).start();
        } catch (IOException e) {
            throw new ServiceException("STARTING SERVER ERROR");
        }
    }

    @Override
    public void sendObject(Object obj) {

    }

    @Override
    public Object receiveObject(Object obj) {
        return null;
    }

    @Override
    public void stopServer() throws ServiceException {
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new ServiceException("STOPPING SERVER ERROR");
        }
    }

    private static class DefaultServerSocketServiceHandler extends Thread {
        private Socket clientSocket;
        private ObjectOutputStream oStream;
        private ObjectInputStream iStream;

        public DefaultServerSocketServiceHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                oStream = new ObjectOutputStream(clientSocket.getOutputStream());
                iStream = new ObjectInputStream(clientSocket.getInputStream());
            } catch (IOException e) {
                LOGGER.error("STARTING CLIENT STREAMS ERROR", e);
            } finally {
                closeClientSocketStreams();
            }
        }

        private void closeClientSocketStreams() {
            try {
                oStream.close();
                iStream.close();
            } catch (IOException e) {
                LOGGER.error("CLOSING CLIENT STREAMS ERROR");
            }
        }
    }
}
