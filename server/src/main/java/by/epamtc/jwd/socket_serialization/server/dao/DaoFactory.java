package by.epamtc.jwd.socket_serialization.server.dao;

import by.epamtc.jwd.socket_serialization.server.dao.impl.FileTextDao;

public class DaoFactory {
    private static volatile DaoFactory instance;

    private final TextDao textDao = new FileTextDao();

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        DaoFactory localInstance = instance;
        if (localInstance == null) {
            synchronized (DaoFactory.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DaoFactory();
                }
            }
        }
        return localInstance;
    }

    public TextDao getTextDao() {
        return textDao;
    }
}
