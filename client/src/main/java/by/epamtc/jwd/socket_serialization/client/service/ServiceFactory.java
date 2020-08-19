package by.epamtc.jwd.socket_serialization.client.service;

import by.epamtc.jwd.socket_serialization.client.service.impl.DefaultClientSocketService;
import by.epamtc.jwd.socket_serialization.client.service.impl.DefaultRequestBuilderService;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final RequestBuilderService requestBuilderService = new DefaultRequestBuilderService();
    private final ClientSocketService clientSocketService = new DefaultClientSocketService();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public RequestBuilderService getRequestBuilderService() {
        return requestBuilderService;
    }

    public ClientSocketService getClientSocketService() {
        return clientSocketService;
    }
}
