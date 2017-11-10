/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.models;

/**
 * 〈百度黑名单model〉
 *
 * @author hwei
 * @version BaiDuBlackListModel.java, V1.0 2016年12月6日 下午6:53:27
 */
public class BaiDuBlackListModel {
    //签名类型，固定为MD5
    private String signType;
    //处理码
    private String retCode;
    //描述
    private String retMsg;
    //签名
    private String sign;
    //响应时间戳
    private String timestamp;
    //黑名单结果
    private BlackResult result;
    public String getSignType() {
        return signType;
    }
    public void setSignType(String signType) {
        this.signType = signType;
    }
    public String getRetCode() {
        return retCode;
    }
    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }
    public String getRetMsg() {
        return retMsg;
    }
    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }
    public String getSign() {
        return sign;
    }
    public void setSign(String sign) {
        this.sign = sign;
    }
    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    public BlackResult getResult() {
        return result;
    }
    public void setResult(BlackResult result) {
        this.result = result;
    }
    
}
