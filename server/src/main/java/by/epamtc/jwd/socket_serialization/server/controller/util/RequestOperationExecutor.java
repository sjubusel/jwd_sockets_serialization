package by.epamtc.jwd.socket_serialization.server.controller.util;

import by.epamtc.jwd.socket_serialization.model.request.RequestOperationEntry;
import by.epamtc.jwd.socket_serialization.model.response.Text;

public interface RequestOperationExecutor {
    void executeAndUpdate(RequestOperationEntry operation, Text text);
}
