package by.epamtc.jwd.socket_serialization.model;

public class RequestOperationEntry implements java.io.Serializable {
    private static final long serialVersionUID = 168182145881206812L;

    private RequestOperation operationKey;
    private String operationValue;

    public RequestOperationEntry() {
    }

    public RequestOperationEntry(RequestOperation operationKey, String operationValue) {
        this.operationKey = operationKey;
        this.operationValue = operationValue;
    }

    public RequestOperation getOperationKey() {
        return operationKey;
    }

    public void setOperationKey(RequestOperation operationKey) {
        this.operationKey = operationKey;
    }

    public String getOperationValue() {
        return operationValue;
    }

    public void setOperationValue(String operationValue) {
        this.operationValue = operationValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RequestOperationEntry entry = (RequestOperationEntry) o;
        return (operationKey != entry.operationKey)
                && (operationValue.equals(entry.operationValue));
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + (operationKey == null ? 0 : operationKey.hashCode());
        hash = 31 * hash + (operationValue == null ? 0 : operationValue.hashCode());
        return hash;
    }

    @Override
    public String toString() {
        return "RequestOperationEntry{" +
                "operationKey=" + operationKey +
                ", operationValue='" + operationValue + '\'' +
                '}';
    }
}
