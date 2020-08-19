package by.epamtc.jwd.socket_serialization.client.service.impl;

import by.epamtc.jwd.socket_serialization.client.service.RequestBuilderService;
import by.epamtc.jwd.socket_serialization.model.Request;
import by.epamtc.jwd.socket_serialization.model.RequestOperationEntry;

public class DefaultRequestBuilderService implements RequestBuilderService {
    private Request request = new Request();

    @Override
    public void addFileName(String userInput) {
        request.setFileName(userInput);
    }

    @Override
    public void addOperation(RequestOperationEntry operation) {
        request.addOperation(operation);
    }

    @Override
    public Request buildRequest() {
        Request request = this.request;
        this.request = new Request();
        return request;
    }
}
