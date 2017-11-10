/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.models.jxl;

import java.util.List;

/**
 * 〈 电商地址详情〉
 *
 * @author hwei
 * @version DeliverAddress.java, V1.0 2017年2月6日 下午2:05:36
 */
public class DeliverAddress {
    private String address;// 收货地址
    private Float lng;// 经度
    private Float lat;// 纬度
    private String predictAddrType;// 地址类型
    private String beginDate;// 开始送货时间
    private String endDate;// 结束送货时间
    private String totalAmount;// 总送货金额
    private Float totalCount;// 总送货次数
    private List<Receiver> receiver;// 收货人列表
    
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Float getLng() {
        return lng;
    }
    public void setLng(Float lng) {
        this.lng = lng;
    }
    public Float getLat() {
        return lat;
    }
    public void setLat(Float lat) {
        this.lat = lat;
    }
    public String getPredictAddrType() {
        return predictAddrType;
    }
    public void setPredictAddrType(String predictAddrType) {
        this.predictAddrType = predictAddrType;
    }
    public String getBeginDate() {
        return beginDate;
    }
    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public String getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
    public Float getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(Float totalCount) {
        this.totalCount = totalCount;
    }
    public List<Receiver> getReceiver() {
        return receiver;
    }
    public void setReceiver(List<Receiver> receiver) {
        this.receiver = receiver;
    }

}
