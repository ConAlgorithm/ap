/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.models.jxl;

/**
 * 〈用户信息检测〉
 *
 * @author hwei
 * @version UserInfoCheck.java, V1.0 2017年2月6日 上午9:54:00
 */
public class UserInfoCheck {
    //用户查询信息
    private UserCheckSearchInfo checkSearchInfo;
    //用户黑名单信息
    private UserCheckBlackInfo checkBlackInfo;
    
    public UserCheckSearchInfo getCheckSearchInfo() {
        return checkSearchInfo;
    }
    public void setCheckSearchInfo(UserCheckSearchInfo checkSearchInfo) {
        this.checkSearchInfo = checkSearchInfo;
    }
    public UserCheckBlackInfo getCheckBlackInfo() {
        return checkBlackInfo;
    }
    public void setCheckBlackInfo(UserCheckBlackInfo checkBlackInfo) {
        this.checkBlackInfo = checkBlackInfo;
    }
    
}
