package by.epamtc.jwd.socket_serialization.model.response.leaf;

import by.epamtc.jwd.socket_serialization.model.response.CompositeElement;
import by.epamtc.jwd.socket_serialization.model.response.Text;

public class PunctuationMark implements Text, CompositeElement {
    /**
     * a punctuation mark
     */
    String value;

    public PunctuationMark() {
    }

    public PunctuationMark(String value) {
        this.value = value;
    }

    @Override
    public void fillWithContents(StringBuilder builder) {
        builder.append(value);
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
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PunctuationMark punctuationMark = (PunctuationMark) o;

        return value.equals(punctuationMark.value);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + (value == null ? 0 : value.hashCode());
        return hash;
    }

    @Override
    public String toString() {
        return "PunctuationMark{" +
                "value='" + value + '\'' +
                '}';
    }
}
