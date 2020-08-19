package by.epamtc.jwd.socket_serialization.client.service;

import by.epamtc.jwd.socket_serialization.client.service.exception.ServiceException;

public interface ClientSocketService {
    void startConnection(String address, int port) throws ServiceException;

    void sendObject(Object objectToSend) throws ServiceException;

    Object receiveObject() throws ServiceException;

    void stopConnection() throws ServiceException;
}
