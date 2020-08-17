package by.epamtc.jwd.socket_serialization.client.service.impl;

import by.epamtc.jwd.socket_serialization.client.service.RequestBuilderService;
import by.epamtc.jwd.socket_serialization.model.Request;

public class DefaultRequestBuilderService implements RequestBuilderService {
    private Request request = new Request();

    @Override
    public void addFileName(String userInput) {
        request.setFileName(userInput);
    }
}
