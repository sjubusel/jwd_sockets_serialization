package by.epamtc.jwd.socket_serialization.client.service;

import by.epamtc.jwd.socket_serialization.model.RequestOperationEntry;

public interface RequestBuilderService {
    void addFileName(String userInput);

    void addOperation(RequestOperationEntry operation);
}
