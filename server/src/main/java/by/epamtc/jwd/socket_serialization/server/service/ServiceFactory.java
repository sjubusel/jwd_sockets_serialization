package by.epamtc.jwd.socket_serialization.server.service;

import by.epamtc.jwd.socket_serialization.server.service.impl.DefaultServerSocketService;
import by.epamtc.jwd.socket_serialization.server.service.impl.DefaultTextProcessingService;
import by.epamtc.jwd.socket_serialization.server.service.impl.DefaultTextService;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final TextService textService = new DefaultTextService();
    private final TextProcessingService textProcessingService
            = new DefaultTextProcessingService();

    private final ServerSocketService serverSocketService =
            new DefaultServerSocketService();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public TextService getTextService() {
        return textService;
    }

    public TextProcessingService getTextProcessingService() {
        return textProcessingService;
    }

    public ServerSocketService getServerSocketService() {
        return serverSocketService;
    }

}
