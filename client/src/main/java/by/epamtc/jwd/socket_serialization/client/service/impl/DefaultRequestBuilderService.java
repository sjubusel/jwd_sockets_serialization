package by.epamtc.jwd.socket_serialization.client.service.impl;

import by.epamtc.jwd.socket_serialization.client.service.RequestBuilderService;
import by.epamtc.jwd.socket_serialization.model.request.Request;
import by.epamtc.jwd.socket_serialization.model.request.RequestOperation;
import by.epamtc.jwd.socket_serialization.model.request.RequestOperationEntry;
import by.epamtc.jwd.socket_serialization.model.response.Text;

public class DefaultRequestBuilderService implements RequestBuilderService {
    private Request request = new Request();

    @Override
    public void addFileName(String userInput) {
        request.setFileName(userInput);
    }

    @Override
    public void addOperation(RequestOperationEntry operation) {
        if (operation.getOperationKey() != RequestOperation.VIEW_OPERATION) {
            request.addOperation(operation);
        }
    }

    @Override
    public void addText(Text text) {
        request.setText(text);
    }

    @Override
    public Request buildRequest() {
        Request buildRequest = this.request;
        this.request = new Request();
        return buildRequest;
    }

    @Override
    public boolean doOperationsExist() {
        return request.getOperations().size() > 0;
    }
}
