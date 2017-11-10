/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.models.jxl;

import java.util.List;

/**
 * 〈收货信息〉
 *
 * @author hwei
 * @version Receiver.java, V1.0 2017年2月6日 下午2:08:32
 */
public class Receiver {
    //收获次数
    private Integer count;
    //收获金额
    private Float amount;
    //收货人名称
    private String name;
    //收货人号码
    private List<String> phoneNumList;
    
    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
    public Float getAmount() {
        return amount;
    }
    public void setAmount(Float amount) {
        this.amount = amount;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<String> getPhoneNumList() {
        return phoneNumList;
    }
    public void setPhoneNumList(List<String> phoneNumList) {
        this.phoneNumList = phoneNumList;
    }
    
}
