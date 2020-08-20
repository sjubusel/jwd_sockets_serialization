package by.epamtc.jwd.socket_serialization.server.controller.util;

import by.epamtc.jwd.socket_serialization.model.request.Request;
import by.epamtc.jwd.socket_serialization.model.request.RequestOperation;
import by.epamtc.jwd.socket_serialization.model.request.RequestOperationEntry;
import by.epamtc.jwd.socket_serialization.model.response.Text;
import by.epamtc.jwd.socket_serialization.server.controller.util.command.FirstLetterDeletion15Executor;
import by.epamtc.jwd.socket_serialization.server.controller.util.command.WordReplacement16Executor;
import by.epamtc.jwd.socket_serialization.server.controller.util.command.WordsDeletion12Executor;
import by.epamtc.jwd.socket_serialization.server.service.ServiceFactory;
import by.epamtc.jwd.socket_serialization.server.service.TextService;
import by.epamtc.jwd.socket_serialization.server.service.exception.ServiceException;

import java.util.HashMap;
import java.util.Map;

public class RequestOperationExecutorProvider {
    private final Map<RequestOperation, RequestOperationExecutor> repository;

    {
        repository = new HashMap<>();
    }

    RequestOperationExecutorProvider() {
        repository.putIfAbsent(RequestOperation.WORDS_DELETION_12,
                new WordsDeletion12Executor());
        repository.putIfAbsent(RequestOperation.FIRST_LETTER_DELETION_15,
                new FirstLetterDeletion15Executor());
        repository.putIfAbsent(RequestOperation.WORD_REPLACEMENT_16,
                new WordReplacement16Executor());
    }

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private TextService textService = serviceFactory.getTextService();

    public Text receiveText(Request request) throws ServiceException {
        Text responseText = request.getText();
        if (responseText != null) {
            return responseText;
        }
        return textService.find(request.getFileName());
    }

    public void executeAndUpdate(RequestOperationEntry operation, Text text) {
        RequestOperationExecutor executor = repository.get(operation
                .getOperationKey());
        executor.executeAndUpdate(operation, text);
    }
}
