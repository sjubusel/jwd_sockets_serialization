package by.epamtc.jwd.socket_serialization.model.request;

import by.epamtc.jwd.socket_serialization.model.RegExPattern;
import by.epamtc.jwd.socket_serialization.model.response.Text;

import java.io.Serializable;
import java.util.ArrayList;

public class Request implements Serializable {
    private static final long serialVersionUID = -6094367457972134648L;

    private String fileName = RegExPattern.EMPTY_STRING;
    private ArrayList<RequestOperationEntry> operations = new ArrayList<>();
    private Text text = null;

    public Request() {
    }

    public Request(String fileName, ArrayList<RequestOperationEntry> operations, Text text) {
        this.fileName = fileName;
        this.operations = operations;
        this.text = text;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public ArrayList<RequestOperationEntry> getOperations() {
        return operations;
    }

    public void setOperations(ArrayList<RequestOperationEntry> operations) {
        this.operations = operations;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public boolean addOperation(RequestOperationEntry operation) {
        return operations.add(operation);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Request request = (Request) o;
        if (!fileName.equals(request.fileName)) {
            return false;
        }
        for (int i = 0; i < operations.size(); i++) {
            if (!operations.get(i).equals(request.operations.get(i))) {
                return false;
            }
        }
        return text.equals(request.text);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        for (RequestOperationEntry operation : operations) {
            hash = 31 * hash + (operation == null ? 0 : operation.hashCode());
        }
        hash = 31 * hash + (text == null ? 0 : text.hashCode());
        return hash;
    }

    @Override
    public String toString() {
        return "Request{" +
                "fileName='" + fileName + '\'' +
                ", operations=" + operations +
                '}';
    }
}
