package by.epamtc.jwd.socket_serialization.server.dao.util.parser;

import by.epamtc.jwd.socket_serialization.model.response.Text;
import by.epamtc.jwd.socket_serialization.model.response.composite.TextSentence;
import by.epamtc.jwd.socket_serialization.model.response.leaf.Bracket;
import by.epamtc.jwd.socket_serialization.model.response.leaf.PunctuationMark;
import by.epamtc.jwd.socket_serialization.model.response.leaf.SentenceWord;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.epamtc.jwd.socket_serialization.model.RegExPattern.*;

public class SentenceIntoSentencePartsParser extends TextParser {
    private Pattern sentencePartPattern = Pattern.compile(SENTENCE_PART);

    @Override
    public void parseAndUpdate(String parsableElement, Text text) {
        TextSentence sentence = null;
        if (text.isComposite()) {
            if (text.getClass() == TextSentence.class) {
                sentence = (TextSentence) text;
            }
        }


        Matcher matcher = sentencePartPattern.matcher(parsableElement);
        if (sentence != null) {
            while (matcher.find()) {
                String sentencePart = matcher.group();
                if (sentencePart.matches(PUNCTUATION_MARK)) {
                    sentence.add(new PunctuationMark(sentencePart));
                    continue;
                }
                if (sentencePart.matches(ROUND_BRACKET)) {
                    sentence.add(new Bracket(sentencePart));
                    continue;
                }
                sentence.add(new SentenceWord(sentencePart));
            }
        }

    }
}
