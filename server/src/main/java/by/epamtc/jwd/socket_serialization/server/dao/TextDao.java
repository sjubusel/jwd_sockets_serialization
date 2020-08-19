package by.epamtc.jwd.socket_serialization.server.dao;

import by.epamtc.jwd.socket_serialization.model.response.Text;
import by.epamtc.jwd.socket_serialization.server.dao.exception.DaoException;

public interface TextDao {
    Text find(String textName) throws DaoException;
}
