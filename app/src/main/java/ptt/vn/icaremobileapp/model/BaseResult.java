package ptt.vn.icaremobileapp.model;

public class BaseResult<T> {
    private int Code;
    private T Data;

    public BaseResult() {
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }
}
