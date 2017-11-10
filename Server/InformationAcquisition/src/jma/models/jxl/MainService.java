/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.models.jxl;

import java.util.List;

/**
 * 〈常用服务〉
 *
 * @author hwei
 * @version MainService.java, V1.0 2017年2月6日 下午12:00:38
 */
public class MainService {
    //企业类型
    private String companyType;
    //企业名称
    private String companyName;
    //总互动次数
    private Integer totalServiceCnt;
    //月互动详情
    private List<ServiceDetails> serviceDetails;
    
    public String getCompanyType() {
        return companyType;
    }
    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public Integer getTotalServiceCnt() {
        return totalServiceCnt;
    }
    public void setTotalServiceCnt(Integer totalServiceCnt) {
        this.totalServiceCnt = totalServiceCnt;
    }
    public List<ServiceDetails> getServiceDetails() {
        return serviceDetails;
    }
    public void setServiceDetails(List<ServiceDetails> serviceDetails) {
        this.serviceDetails = serviceDetails;
    }

}
