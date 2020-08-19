package by.epamtc.jwd.socket_serialization.server.controller.exception;

public class ControllerException extends Exception {
    private static final long serialVersionUID = -5751395888067241734L;

    public ControllerException() {
    }

    public ControllerException(String message) {
        super(message);
    }

    public ControllerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ControllerException(Throwable cause) {
        super(cause);
    }
}
