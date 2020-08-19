package by.epamtc.jwd.socket_serialization.server.controller.util.command;

import by.epamtc.jwd.socket_serialization.model.request.RequestOperationEntry;
import by.epamtc.jwd.socket_serialization.model.response.Text;
import by.epamtc.jwd.socket_serialization.server.controller.util.RequestOperationExecutor;
import by.epamtc.jwd.socket_serialization.server.service.ServiceFactory;
import by.epamtc.jwd.socket_serialization.server.service.TextProcessingService;

public class FirstLetterDeletion15Executor implements RequestOperationExecutor {
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private TextProcessingService textProcessingService = serviceFactory
            .getTextProcessingService();

    @Override
    public void executeAndUpdate(RequestOperationEntry operation, Text text) {
        textProcessingService.deleteCoincidencesOfFirstLetterOfEachWord(text);
    }
}
