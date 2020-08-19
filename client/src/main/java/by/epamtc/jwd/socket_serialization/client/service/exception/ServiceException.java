package by.epamtc.jwd.socket_serialization.client.service.exception;

public class ServiceException extends Exception {
    private static final long serialVersionUID = 8516327441967101394L;

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
