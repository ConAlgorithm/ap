/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.models.jxl;

import java.util.List;

/**
 * 〈运营商数据〉
 *
 * @author hwei
 * @version CellBehavior.java, V1.0 2017年2月6日 上午10:55:22
 */
public class CellBehavior {
    //手机号
    private String phone_num;
    //运营商数据整理
    private List<CellBehaviorItem> behavior;
    
    public String getPhone_num() {
        return phone_num;
    }
    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }
    public List<CellBehaviorItem> getBehavior() {
        return behavior;
    }
    public void setBehavior(List<CellBehaviorItem> behavior) {
        this.behavior = behavior;
    }
    
    
}
