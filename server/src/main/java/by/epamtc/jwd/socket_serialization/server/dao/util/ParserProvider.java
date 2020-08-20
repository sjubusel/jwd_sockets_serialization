package by.epamtc.jwd.socket_serialization.server.dao.util;

import by.epamtc.jwd.socket_serialization.model.response.Text;
import by.epamtc.jwd.socket_serialization.server.dao.util.parser.*;

import java.util.Deque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.epamtc.jwd.socket_serialization.model.RegExPattern.CURLY_BRACKET;
import static by.epamtc.jwd.socket_serialization.model.RegExPattern.SPECIAL_CODE_LINE_CASE;

public class ParserProvider {
    private TextParser codeBlockParser = new CodeBlockIntoTextComponentParser();
    private TextParser textBlockParser = new TextBlockIntoParagraphParser();
    private TextParser paragraphParser = new ParagraphIntoSentenceParser();
    private TextParser sentenceParser = new SentenceIntoSentencePartsParser();

    {
        codeBlockParser.setNext(textBlockParser);
        textBlockParser.setNext(paragraphParser);
        paragraphParser.setNext(sentenceParser);
        sentenceParser.setNext(null);
    }

    private Pattern curlyBracketsPattern = Pattern.compile(CURLY_BRACKET);

    public boolean isCodeLine(String line, Deque<String> curlyBracketsStack) {
        Matcher matcher = curlyBracketsPattern.matcher(line);
        boolean isCodelineable = false;
        while (matcher.find()) {
            isCodelineable = true;
            String curlyBracket = matcher.group();
            if (curlyBracket.equals("{")) {
                curlyBracketsStack.addLast("{");
            } else {
                curlyBracketsStack.pollLast();
            }
        }
        if (line.matches(SPECIAL_CODE_LINE_CASE)) {
            isCodelineable = true;
        }
        return isCodelineable;
    }

    public void parseAndUpdate(String str, Text text) {
        if (str.contains("{") || str.matches(SPECIAL_CODE_LINE_CASE)) {
            codeBlockParser.parseAndUpdate(str, text);
        } else {
            textBlockParser.parseAndUpdate(str, text);
        }
    }
}
