package by.epamtc.jwd.socket_serialization.server.dao.util.parser;

import by.epamtc.jwd.socket_serialization.model.response.Text;

public abstract class TextParser {
    protected TextParser next;

    public TextParser setNext(TextParser parser) {
        this.next = parser;
        return parser;
    }

    public abstract void parseAndUpdate(String parsableElement, Text text);

}
