package by.epamtc.jwd.socket_serialization.server.controller;


import by.epamtc.jwd.socket_serialization.server.controller.exception.ControllerException;
import by.epamtc.jwd.socket_serialization.server.controller.util.MultithreadedServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerController.class);

    public static void main(String[] args) {
        int serverPort = 443;
        int maxNumberOfClients = 10;
        MultithreadedServer server = new MultithreadedServer(serverPort,
                maxNumberOfClients);

        try {
            server.startServer();
        } catch (ControllerException e) {
            LOGGER.error("ERROR WHILE STARTING SERVER", e);
        }


        try {
            Thread.sleep(60 * 1000);
        } catch (InterruptedException e) {
            LOGGER.error("WORK OF SERVER CONTROLLER IS INTERRUPTED.");
        }

        try {
            server.stopServer();
        } catch (ControllerException e) {
            LOGGER.error("ERROR WHILE STOPPING SERVER", e);
        }
    }
}
