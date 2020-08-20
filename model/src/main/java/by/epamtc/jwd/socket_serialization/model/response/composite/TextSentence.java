package by.epamtc.jwd.socket_serialization.model.response.composite;

import by.epamtc.jwd.socket_serialization.model.response.Composite;
import by.epamtc.jwd.socket_serialization.model.response.CompositeElement;
import by.epamtc.jwd.socket_serialization.model.response.Text;
import by.epamtc.jwd.socket_serialization.model.response.leaf.PunctuationMark;

import java.util.ArrayList;
import java.util.List;

public class TextSentence implements Text, Composite {
    private static final long serialVersionUID = 2169275861258217427L;
    /**
     * words
     */
    List<Text> sentenceParts = new ArrayList<>();

    public TextSentence() {
    }

    public TextSentence(List<Text> sentenceParts) {
        this.sentenceParts = sentenceParts;
    }

    @Override
    public void fillWithContents(StringBuilder builder) {
        for (int i = 0; i < sentenceParts.size(); i++) {
            if ((i != 0) && (sentenceParts.get(i).getClass() != PunctuationMark.class)) {
                builder.append(' ');
            }
            CompositeElement element = null;
            if (!sentenceParts.get(i).isComposite()) {
                element = (CompositeElement) sentenceParts.get(i);
            }
            if (element != null) {
                builder.append(element.receiveContents());
            }
        }
    }

    @Override
    public boolean isComposite() {
        return true;
    }

    @Override
    public void add(Text component) {
        sentenceParts.add(component);
    }

    @Override
    public void remove(Text component) {
        sentenceParts.remove(component);
    }

    @Override
    public List<Text> receiveCompositeContents() {
        return sentenceParts;
    }

    public List<Text> getSentenceParts() {
        return sentenceParts;
    }

    public void setSentenceParts(List<Text> sentenceParts) {
        this.sentenceParts = sentenceParts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TextSentence textSentence = (TextSentence) o;
        boolean isEquals = true;
        for (int i = 0; i < textSentence.sentenceParts.size(); i++) {
            if (!sentenceParts.get(i).equals(textSentence.sentenceParts.get(i))) {
                isEquals = false;
                break;
            }
        }

        return isEquals;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        for (Text word : sentenceParts) {
            hash = 31 * hash + (word == null ? 0 : word.hashCode());
        }
        return hash;
    }

    @Override
    public String toString() {
        return "TextSentence{" +
                "sentenceParts=" + sentenceParts +
                '}';
    }
}
