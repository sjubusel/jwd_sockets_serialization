package by.epamtc.jwd.socket_serialization.server.dao.util.parser;

import by.epamtc.jwd.socket_serialization.model.response.Text;
import by.epamtc.jwd.socket_serialization.model.response.composite.Paragraph;
import by.epamtc.jwd.socket_serialization.model.response.composite.TextComposite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.epamtc.jwd.socket_serialization.model.response.RegExPattern.*;

public class TextBlockIntoParagraphParser extends TextParser {
    private Pattern paragraphPattern = Pattern.compile(PARAGRAPH,
            Pattern.MULTILINE);

    @Override
    public void parseAndUpdate(String parsableElement, Text text) {
        TextComposite textComposite = null;
        if (text.isComposite()) {
            if (text.getClass() == TextComposite.class) {
                textComposite = (TextComposite) text;
            }
        }

        if (next != null) {
            Matcher matcher = paragraphPattern.matcher(parsableElement);
            while (matcher.find()) {
                Paragraph paragraph = new Paragraph();
                String strParagraph = matcher.group()
                        .replaceAll(LINE_BREAK, BLANK_SPACE).trim();
                next.parseAndUpdate(strParagraph, paragraph);
                if (textComposite != null) {
                    textComposite.add(paragraph);
                }
            }
        }

    }
}
