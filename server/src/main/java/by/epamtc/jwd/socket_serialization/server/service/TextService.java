package by.epamtc.jwd.socket_serialization.server.service;

import by.epamtc.jwd.socket_serialization.model.response.Text;
import by.epamtc.jwd.socket_serialization.server.service.exception.ServiceException;

public interface TextService {
    Text find(String textName) throws ServiceException;
}
