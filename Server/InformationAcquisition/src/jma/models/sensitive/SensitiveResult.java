package jma.models.sensitive;

import java.util.List;

public class SensitiveResult {
    private String code;
    private String msg;
    private String status;
    private List<SensitiveWordResponse> data;
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public List<SensitiveWordResponse> getData() {
        return data;
    }
    public void setData(List<SensitiveWordResponse> data) {
        this.data = data;
    }
    
    
}
