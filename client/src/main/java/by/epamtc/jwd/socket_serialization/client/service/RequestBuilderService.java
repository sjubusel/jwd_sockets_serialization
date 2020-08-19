package by.epamtc.jwd.socket_serialization.client.service;

import by.epamtc.jwd.socket_serialization.model.request.Request;
import by.epamtc.jwd.socket_serialization.model.request.RequestOperationEntry;

public interface RequestBuilderService {
    void addFileName(String userInput);

    void addOperation(RequestOperationEntry operation);

    Request buildRequest();
}
