package by.epamtc.jwd.socket_serialization.model.request;

import java.io.Serializable;
import java.util.ArrayList;

public class Request implements Serializable {
    private static final long serialVersionUID = -6094367457972134648L;

    private String fileName = "";
    private ArrayList<RequestOperationEntry> operations = new ArrayList<>();

    public Request() {
    }

    public Request(String fileName, ArrayList<RequestOperationEntry> operations) {
        this.fileName = fileName;
        this.operations = operations;
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
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        for (RequestOperationEntry operation : operations) {
            hash = 31 * hash + (operation == null ? 0 : operation.hashCode());
        }
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
