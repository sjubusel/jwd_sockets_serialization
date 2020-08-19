package by.epamtc.jwd.socket_serialization.server.dao.util.parser;

import by.epamtc.jwd.socket_serialization.model.response.Text;
import by.epamtc.jwd.socket_serialization.model.response.composite.TextComposite;
import by.epamtc.jwd.socket_serialization.model.response.leaf.CodeBlock;

public class CodeBlockIntoTextComponentParser extends TextParser {
    @Override
    public void parseAndUpdate(String parsableElement, Text text) {
        if (text.isComposite()) {
            if (text.getClass() == TextComposite.class) {
                TextComposite textComposite = (TextComposite) text;
                textComposite.add(new CodeBlock(parsableElement));
            }
        }
    }
}
