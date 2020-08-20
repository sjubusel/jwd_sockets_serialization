package by.epamtc.jwd.socket_serialization.client.view;

import by.epamtc.jwd.socket_serialization.model.response.Text;

public class ResponsePrinter {
    public void print(Text responseText) {
        StringBuilder builder = new StringBuilder();
        responseText.fillWithContents(builder);
        System.out.println(new String(builder));
        builder.delete(0, builder.length());
    }
}
