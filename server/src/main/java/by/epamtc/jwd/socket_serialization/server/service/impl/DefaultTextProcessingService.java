package by.epamtc.jwd.socket_serialization.server.service.impl;

import by.epamtc.jwd.socket_serialization.model.response.Text;
import by.epamtc.jwd.socket_serialization.model.response.composite.Paragraph;
import by.epamtc.jwd.socket_serialization.model.response.composite.TextComposite;
import by.epamtc.jwd.socket_serialization.model.response.composite.TextSentence;
import by.epamtc.jwd.socket_serialization.model.response.leaf.SentenceWord;
import by.epamtc.jwd.socket_serialization.server.service.TextProcessingService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DefaultTextProcessingService implements TextProcessingService {
    @Override
    public void deleteWordsOfLengthIfFirstLetterIsConsonant(Text text, int length) {
        TextComposite textComposite = null;
        if (text.isComposite() && (text.getClass() == TextComposite.class)) {
            textComposite = (TextComposite) text;
        }

        List<Text> textSentences = receiveAllSentences(textComposite);
        for (Text textSentence : textSentences) {
            if (textSentence.getClass() == TextSentence.class) {
                TextSentence sentence = (TextSentence) textSentence;
                // overloaded version
                deleteWordsOfLengthIfFirstLetterIsConsonant(sentence, length);
            }
        }
    }

    @Override
    public void deleteCoincidencesOfFirstLetterOfEachWord(Text text) {
        TextComposite textComposite = null;
        if (text.isComposite() && (text.getClass() == TextComposite.class)) {
            textComposite = (TextComposite) text;
        }

        List<Text> textSentences = receiveAllSentences(textComposite);
        for (Text textSentence : textSentences) {
            if (textSentence.getClass() == TextSentence.class) {
                TextSentence sentence = (TextSentence) textSentence;
                // overloaded version
                deleteCoincidencesOfFirstLetterOfEachWord(sentence);
            }
        }
    }

    @Override
    public void replaceWordsOfLengthWithSubstring(Text text, int length,
            String substring) {
        TextComposite textComposite = null;
        if (text.isComposite() && (text.getClass() == TextComposite.class)) {
            textComposite = (TextComposite) text;
        }

        List<Text> textSentences = receiveAllSentences(textComposite);
        for (Text textSentence : textSentences) {
            if (textSentence.getClass() == TextSentence.class) {
                TextSentence sentence = (TextSentence) textSentence;
                // overloaded version
                replaceWordsOfLengthWithSubstring(sentence, length, substring);
            }
        }

    }

    private List<Text> receiveAllSentences(TextComposite textComposite) {
        List<Text> sentences = new ArrayList<>();
        if (textComposite != null) {
            List<Text> textParts = textComposite.receiveCompositeContents();
            for (Text textPart : textParts) {
                if (textPart.getClass() == Paragraph.class) {
                    Paragraph paragraph = (Paragraph) textPart;
                    sentences.addAll(paragraph.getSentences());
                }
            }
        }
        return sentences;
    }

    private void deleteWordsOfLengthIfFirstLetterIsConsonant(TextSentence sentence,
            int length) {
        List<Text> sentenceParts = sentence.getSentenceParts();

        Iterator<Text> iterator = sentenceParts.iterator();
        while (iterator.hasNext()) {
            Text sentencePart = iterator.next();
            if (sentencePart.getClass() == SentenceWord.class) {
                SentenceWord word = (SentenceWord) sentencePart;
                if (isWordToDelete(word, length)) {
                    iterator.remove();
                }
            }

        }
    }

    private boolean isWordToDelete(SentenceWord word, int length) {
        if (word.getValue().matches("[-'\"A-Za-z]+")) {
            if (!word.getValue().startsWith("\"")) {
                return isLetterConsonant(word.getValue().charAt(0))
                        && (word.getValue().length() == length);
            } else {
                return isLetterConsonant(word.getValue().charAt(1))
                        && (word.getValue().length() == length + 2);
            }
        }
        return false;
    }

    private boolean isLetterConsonant(char letter) {
        String consonants = "BbCcDdFfGfHhJjKkLlMmNnPpQqRrSsTtVvXxZzWwYy";
        return consonants.contains(String.valueOf(letter));
    }

    private void deleteCoincidencesOfFirstLetterOfEachWord(TextSentence sentence) {
        List<Text> sentenceParts = sentence.getSentenceParts();
        for (Text sentencePart : sentenceParts) {
            if (sentencePart.getClass() == SentenceWord.class) {
                SentenceWord word = (SentenceWord) sentencePart;
                // overloaded version
                deleteCoincidencesOfFirstLetterOfEachWord(word);
            }
        }
    }

    private void deleteCoincidencesOfFirstLetterOfEachWord(SentenceWord word) {
        String charToDelete;
        String initialValue;
        String modifiedValue;

        if (word.getValue().matches("[-'\"A-Za-z]+")) {
            if (!word.getValue().startsWith("\"")) {
                charToDelete = String.valueOf(word.getValue().charAt(0));
                initialValue = word.getValue().substring(1);
                modifiedValue = charToDelete + initialValue
                        .replaceAll(charToDelete, "");
            } else {
                charToDelete = String.valueOf(word.getValue().charAt(1));
                initialValue = word.getValue().substring(2);
                modifiedValue = "\"" + charToDelete + initialValue
                        .replaceAll(charToDelete, "");
            }
        } else {
            return;
        }
        word.setValue(modifiedValue);
    }

    private void replaceWordsOfLengthWithSubstring(TextSentence sentence,
            int length, String substring) {
        List<Text> sentenceParts = sentence.getSentenceParts();
        for (Text sentencePart : sentenceParts) {
            if (sentencePart.getClass() == SentenceWord.class) {
                SentenceWord word = (SentenceWord) sentencePart;
                if (isWordToBeReplaced(word, length)) {
                    replace(word, substring);
                }
            }
        }

    }

    private boolean isWordToBeReplaced(SentenceWord word, int length) {
        if (word.getValue().matches("[-'\"A-Za-z]+")) {
            if (!word.getValue().startsWith("\"")) {
                return word.getValue().length() == length;
            } else {
                return word.getValue().length() == (length + 2);
            }
        }
        return false;
    }

    private void replace(SentenceWord word, String substring) {
        String initValue = word.getValue();
        String modifiedValue = initValue.replaceAll("[-'A-Za-z]+", substring);
        word.setValue(modifiedValue);
    }
}
