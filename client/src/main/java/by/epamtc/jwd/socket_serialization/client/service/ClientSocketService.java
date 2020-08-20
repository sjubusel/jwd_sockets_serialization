package by.epamtc.jwd.socket_serialization.client.service;

import by.epamtc.jwd.socket_serialization.client.service.exception.ServiceException;

public interface ClientSocketService {
    void startConnection(String address, int port) throws ServiceException;

    void sendObjectToServer(Object objectToSend) throws ServiceException;

    Object receiveObjectFromServer() throws ServiceException;

    void stopConnection() throws ServiceException;
}
