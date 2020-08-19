package by.epamtc.jwd.socket_serialization.server.service.impl;

import by.epamtc.jwd.socket_serialization.model.response.Text;
import by.epamtc.jwd.socket_serialization.server.dao.DaoFactory;
import by.epamtc.jwd.socket_serialization.server.dao.TextDao;
import by.epamtc.jwd.socket_serialization.server.dao.exception.DaoException;
import by.epamtc.jwd.socket_serialization.server.service.TextService;
import by.epamtc.jwd.socket_serialization.server.service.exception.ServiceException;
import by.epamtc.jwd.socket_serialization.server.service.validation.TextPathValidator;

public class DefaultTextService implements TextService {
    private TextPathValidator validator = new TextPathValidator();

    @Override
    public Text find(String textName) throws ServiceException {
        if (!validator.isTextPathCorrect(textName)) {
            throw new ServiceException("INVALID TEXT NAME");
        }

        DaoFactory daoFactory = DaoFactory.getInstance();
        TextDao textDao = daoFactory.getTextDao();

        Text text;
        try {
            text = textDao.find(textName);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return text;
    }
}
