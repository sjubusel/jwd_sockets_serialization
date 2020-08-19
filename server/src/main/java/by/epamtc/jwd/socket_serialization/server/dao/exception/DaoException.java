package by.epamtc.jwd.socket_serialization.server.dao.exception;

public class DaoException extends Exception {
    private static final long serialVersionUID = -7994622778827028562L;

    public DaoException() {
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}
