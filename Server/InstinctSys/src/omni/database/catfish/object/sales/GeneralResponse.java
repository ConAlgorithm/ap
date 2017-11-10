/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package omni.database.catfish.object.sales;

import java.io.Serializable;

/**
 * 〈销售系统返回model〉
 *
 * @author hwei
 * @version GeneralResponse.java, V1.0 2017年5月26日 下午3:33:58
 */
public class GeneralResponse<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private int code;
    private String message;
    private T value;

    public GeneralResponse() {
        this.value = null;
        this.code = 0;
        this.message = "操作成功";
    }

    public GeneralResponse<T> setData(T value) {
        this.value = value;
        return this;
    }

    public int getCode() {
        return this.code;
    }

    public GeneralResponse<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public GeneralResponse<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getValue() {
        return this.value;
    }

    public GeneralResponse<T> setValue(T value) {
        this.value = value;
        return this;
    }

    public static class Code {
        public static final int SUCCESS = 0;
        public static final int FAIL = 1;
    }
}
