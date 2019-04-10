package ptt.vn.icaremobileapp.model;

public class RestResult {
    private int Code;
    private Object Data;

    public RestResult() {
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public Object getData() {
        return Data;
    }

    public void setData(Object data) {
        Data = data;
    }
}
