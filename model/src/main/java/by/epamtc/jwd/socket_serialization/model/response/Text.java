package by.epamtc.jwd.socket_serialization.model.response;

/**
 * A Component, which is a part of Composite Pattern
 */
public interface Text extends java.io.Serializable {
    void fillWithContents(StringBuilder builder);

    boolean isComposite();
}
