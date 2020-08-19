package by.epamtc.jwd.socket_serialization.server.dao.util.parser;

import by.epamtc.jwd.socket_serialization.model.response.RegExPattern;
import by.epamtc.jwd.socket_serialization.model.response.Text;
import by.epamtc.jwd.socket_serialization.model.response.composite.Paragraph;
import by.epamtc.jwd.socket_serialization.model.response.composite.TextSentence;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphIntoSentenceParser extends TextParser {
    private Pattern sentencePattern = Pattern.compile(RegExPattern.SENTENCE);

    @Override
    public void parseAndUpdate(String parsableElement, Text text) {
        Paragraph paragraph = null;
        if (text.isComposite()) {
            if (text.getClass() == Paragraph.class) {
                paragraph = (Paragraph) text;
            }
        }

        if (next != null) {
            Matcher matcher = sentencePattern.matcher(parsableElement);
            while (matcher.find()) {
                TextSentence sentence = new TextSentence();
                String strSentence = matcher.group();
                next.parseAndUpdate(strSentence, sentence);
                if (paragraph != null) {
                    paragraph.add(sentence);
                }
            }
        }

    }
}
