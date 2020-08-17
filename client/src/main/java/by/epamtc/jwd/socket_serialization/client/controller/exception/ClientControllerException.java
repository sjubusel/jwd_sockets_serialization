package by.epamtc.jwd.socket_serialization.client.controller.exception;

public class ClientControllerException extends Exception {
    private static final long serialVersionUID = -2128520093645202217L;

    public ClientControllerException() {
    }

    public ClientControllerException(String message) {
        super(message);
    }

    public ClientControllerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientControllerException(Throwable cause) {
        super(cause);
    }

}
