/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package engine.rule.model.creator.app.domain;

import java.util.Date;

/**
 *  订单状态实体类
 *
 * @author liucj
 * @version InstallmentStatus.java, V1.0 2016年10月28日 上午11:12:58
 */
public class InstallmentStatus {

    private String status;

    private Date updateDate;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

}
