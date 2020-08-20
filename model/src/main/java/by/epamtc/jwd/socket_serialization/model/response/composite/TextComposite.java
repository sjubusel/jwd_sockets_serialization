package by.epamtc.jwd.socket_serialization.model.response.composite;

import by.epamtc.jwd.socket_serialization.model.response.Composite;
import by.epamtc.jwd.socket_serialization.model.response.RegExPattern;
import by.epamtc.jwd.socket_serialization.model.response.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * a class, which is a pure realization of a "Composite" part of Composite Pattern
 */
public class TextComposite implements Text, Composite {
    private static final long serialVersionUID = 6090643406958352425L;
    /**
     * paragraphs and code blocks
     */
    List<Text> components = new ArrayList<>();

    public TextComposite() {
    }

    public TextComposite(List<Text> components) {
        this.components = components;
    }

    @Override
    public void fillWithContents(StringBuilder builder) {
        for (Text component : components) {
            component.fillWithContents(builder);
            if (component.getClass() == Paragraph.class) {
                builder.append(RegExPattern.LINE_BREAK);
            }
        }
    }

    @Override
    public boolean isComposite() {
        return true;
    }

    @Override
    public void add(Text component) {
        components.add(component);
    }

    @Override
    public void remove(Text component) {
        components.remove(component);
    }

    @Override
    public List<Text> receiveCompositeContents() {
        return components;
    }

    public List<Text> getComponents() {
        return components;
    }

    public void setComponents(List<Text> components) {
        this.components = components;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TextComposite textComposite = (TextComposite) o;
        boolean isEquals = true;
        for (int i = 0; i < textComposite.components.size(); i++) {
            if (!components.get(i).equals(textComposite.components.get(i))) {
                isEquals = false;
                break;
            }
        }
        return isEquals;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        for (Text component : components) {
            hash = 31 * hash + (component == null ? 0 : component.hashCode());
        }
        return hash;
    }

    @Override
    public String toString() {
        return "TextComposite{" +
                "components=" + components +
                '}';
    }
}

