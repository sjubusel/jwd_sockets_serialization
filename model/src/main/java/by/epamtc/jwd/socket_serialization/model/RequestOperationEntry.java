package by.epamtc.jwd.socket_serialization.model;

public class RequestOperationEntry {
    private RequestOperation operationKey;
    private String paramValue;

    public RequestOperationEntry() {
    }

    public RequestOperationEntry(RequestOperation operationKey, String parameterValue) {
        this.operationKey = operationKey;
        this.paramValue = parameterValue;
    }

    public RequestOperation getOperationKey() {
        return operationKey;
    }

    public void setOperationKey(RequestOperation operationKey) {
        this.operationKey = operationKey;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
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
                && (paramValue.equals(entry.paramValue));
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + (operationKey == null ? 0 : operationKey.hashCode());
        hash = 31 * hash + (paramValue == null ? 0 : paramValue.hashCode());
        return hash;
    }
}
