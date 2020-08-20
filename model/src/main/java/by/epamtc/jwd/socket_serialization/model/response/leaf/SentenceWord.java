package by.epamtc.jwd.socket_serialization.model.response.leaf;

import by.epamtc.jwd.socket_serialization.model.response.CompositeElement;
import by.epamtc.jwd.socket_serialization.model.response.Text;

public class SentenceWord implements Text, CompositeElement {
    private static final long serialVersionUID = 180539874523038405L;
    /**
     * simple word or punctuation mark
     */
    String value;

    public SentenceWord() {
    }

    public SentenceWord(String value) {
        this.value = value;
    }

    @Override
    public void fillWithContents(StringBuilder builder) {
        System.out.println(value);
    }

    @Override
    public boolean isComposite() {
        return false;
    }

    @Override
    public String receiveContents() {
        return value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SentenceWord word = (SentenceWord) o;
        return value.equals(word.value);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + (value == null ? 0 : value.hashCode());
        return hash;
    }

    @Override
    public String toString() {
        return "Word{" +
                "value='" + value + '\'' +
                '}';
    }
}
