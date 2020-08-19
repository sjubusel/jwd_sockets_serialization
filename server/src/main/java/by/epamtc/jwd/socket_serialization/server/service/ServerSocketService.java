package by.epamtc.jwd.socket_serialization.server.service;

import by.epamtc.jwd.socket_serialization.server.service.exception.ServiceException;

public interface ServerSocketService {
    void startServer(int port) throws ServiceException;

    void sendObject(Object obj);

    Object receiveObject(Object obj);

    void stopServer() throws ServiceException;
}
