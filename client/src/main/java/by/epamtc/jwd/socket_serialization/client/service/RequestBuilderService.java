package by.epamtc.jwd.socket_serialization.client.service;

import by.epamtc.jwd.socket_serialization.model.request.Request;
import by.epamtc.jwd.socket_serialization.model.request.RequestOperationEntry;
import by.epamtc.jwd.socket_serialization.model.response.Text;

public interface RequestBuilderService {
    void addFileName(String userInput);

    void addOperation(RequestOperationEntry operation);

    void addText(Text text);

    Request buildRequest();

    boolean doOperationsExist();

    boolean isOperationValid(RequestOperationEntry operation);
}
