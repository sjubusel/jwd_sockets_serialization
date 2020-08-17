package by.epamtc.jwd.socket_serialization.model;

import java.util.ArrayList;

public class Request {
    private String fileName = "";
    private ArrayList<RequestOperation> operationsOrder = new ArrayList<>();
    private ArrayList<String> operationsParams = new ArrayList<>();

    public Request() {
    }

    public Request(String fileName, ArrayList<RequestOperation> operationsOrder,
            ArrayList<String> operationsParameters) {
        this.fileName = fileName;
        this.operationsOrder = operationsOrder;
        this.operationsParams = operationsParameters;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public ArrayList<RequestOperation> getOperationsOrder() {
        return operationsOrder;
    }

    public void setOperationsOrder(ArrayList<RequestOperation> operationsOrder) {
        this.operationsOrder = operationsOrder;
    }

    public ArrayList<String> getOperationsParams() {
        return operationsParams;
    }

    public void setOperationsParams(ArrayList<String> operationsParams) {
        this.operationsParams = operationsParams;
    }

    public boolean addOperation(RequestOperation operation, String parameterValue) {
        return operationsOrder.add(operation)
                && operationsParams.add(parameterValue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Request req = (Request) o;
        if (!this.fileName.equals(req.fileName)) {
            return false;
        }
        if ((operationsOrder.size() != req.operationsOrder.size())) {
            return false;
        }
        if ((operationsParams.size() != req.operationsParams.size())) {
            return false;
        }
        for (int i = 0; i < operationsOrder.size(); i++) {
            if (operationsOrder.get(i) != req.operationsOrder.get(i)) {
                return false;
            }
        }
        for (int i = 0; i < operationsParams.size(); i++) {
            if (!operationsParams.get(i).equals(req.operationsParams.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        for (RequestOperation operation : operationsOrder) {
            hash = 31 * hash + (operation == null ? 0 : operation.hashCode());
        }
        for (String param : operationsParams) {
            hash = 31 * hash + (param == null ? 0 : param.hashCode());
        }
        return hash;
    }

    @Override
    public String toString() {
        return "Request{" +
                "fileName='" + fileName + '\'' +
                ", operationsOrder=" + operationsOrder +
                ", operationsParams=" + operationsParams +
                '}';
    }
}
