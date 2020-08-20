package by.epamtc.jwd.socket_serialization.model.response.composite;

import by.epamtc.jwd.socket_serialization.model.response.Composite;
import by.epamtc.jwd.socket_serialization.model.RegExPattern;
import by.epamtc.jwd.socket_serialization.model.response.Text;

import java.util.ArrayList;
import java.util.List;

public class Paragraph implements Text, Composite {
    private static final long serialVersionUID = -6383360481285235651L;
    /**
     * sentences
     */
    List<Text> sentences = new ArrayList<>();

    public Paragraph() {
    }

    public Paragraph(List<Text> sentences) {
        this.sentences = sentences;
    }

    @Override
    public void fillWithContents(StringBuilder builder) {
        for (int i = 0; i < sentences.size(); i++) {
            if (i != 0) {
                builder.append(RegExPattern.BLANK_SPACE);
            }
            sentences.get(i).fillWithContents(builder);
        }
    }

    @Override
    public boolean isComposite() {
        return true;
    }

    @Override
    public void add(Text text) {
        sentences.add(text);
    }

    @Override
    public void remove(Text text) {
        sentences.remove(text);
    }

    @Override
    public List<Text> receiveCompositeContents() {
        return sentences;
    }

    public List<Text> getSentences() {
        return sentences;
    }

    public void setSentences(List<Text> sentences) {
        this.sentences = sentences;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Paragraph paragraph = (Paragraph) o;
        boolean isEquals = true;
        for (int i = 0; i < paragraph.sentences.size(); i++) {
            if (!sentences.get(i).equals(paragraph.sentences.get(i))) {
                isEquals = false;
                break;
            }
        }
        return isEquals;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        for (Text sentence : sentences) {
            hash = 31 * hash + (sentence == null ? 0 : sentence.hashCode());
        }
        return hash;
    }

    @Override
    public String toString() {
        return "Paragraph{" +
                "sentences=" + sentences +
                '}';
    }
}
