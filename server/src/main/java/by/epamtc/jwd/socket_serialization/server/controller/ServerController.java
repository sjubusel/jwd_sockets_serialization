package by.epamtc.jwd.socket_serialization.server.controller;

import by.epamtc.jwd.socket_serialization.server.service.ServerSocketService;
import by.epamtc.jwd.socket_serialization.server.service.ServiceFactory;

public class ServerController {
    private static ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private static ServerSocketService serverSocketService
            = serviceFactory.getServerSocketService();

    public static void main(String[] args) {

    }
}
