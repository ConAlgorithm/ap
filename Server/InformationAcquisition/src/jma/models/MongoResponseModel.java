package jma.models;

/**
 * Created by t_mal on 2017/1/10.
 */
public class MongoResponseModel<T> {

    private int code;
    private T data;
    private String msg ;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
