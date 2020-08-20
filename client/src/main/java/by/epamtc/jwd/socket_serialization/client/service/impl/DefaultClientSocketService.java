package by.epamtc.jwd.socket_serialization.client.service.impl;

import by.epamtc.jwd.socket_serialization.client.service.ClientSocketService;
import by.epamtc.jwd.socket_serialization.client.service.exception.ServiceException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class DefaultClientSocketService implements ClientSocketService {
    private Socket clientSocket;
    private ObjectOutputStream oStream;
    private ObjectInputStream iStream;

    @Override
    public void startConnection(String address, int port) throws ServiceException {
        try {
            clientSocket = new Socket("localhost", 443);
            oStream = new ObjectOutputStream(clientSocket.getOutputStream());
            iStream = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            throw new ServiceException("START SERVER CONNECTION ERROR");
        }
    }

    @Override
    public void sendObject(Object objectToSend) throws ServiceException {
        try {
            oStream.writeObject(objectToSend);
        } catch (IOException e) {
            throw new ServiceException("WRITING TO SERVER ERROR");
        }
    }

    @Override
    public Object receiveObject() throws ServiceException {
        try {
            return iStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new ServiceException("READING FROM SERVER ERROR");
        }
    }

    @Override
    public void stopConnection() throws ServiceException {
        try {
            iStream.close();
            oStream.close();
            clientSocket.close();
        } catch (IOException e) {
            throw new ServiceException("STOPPING SERVER CONNECTION ERROR");
        }
    }
}
