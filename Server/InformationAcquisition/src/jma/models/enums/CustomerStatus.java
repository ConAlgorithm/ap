package jma.models.enums;

public enum CustomerStatus {
    NOT_EXIST(0, "客户不存在"),
    NO_STATUS(1, "无通话状态"),
    ON_SELF_AGREE(111, "接通->本人接听->办理"),
    ON_SELF_REJECT(112, "接通->本人接听->拒绝"),
    ON_SELF_CONSIDER(113, "接通->本人接听->考虑"),
    ON_SELF_NORESOPONSE(114, "接通->本人接听->不表态挂机"),
    ON_OTHER_NORESPONSE(121, "接通->非本人接听->接听人不配合/不回应身份/无语挂机"),
    ON_OTHER_OTHER(122, "接通->非本人接听->接听人为其它人员"),
    ON_OTHER_REJECT(123, "接通->非本人接听->拒绝"),
    ON_OTHER_LANGUAGE(124, "接通->非本人接听->语言障碍，无法沟通"),
    OFF_POWER(201, "未接通->关机/无法接通/占线/无人接听"),
    OFF_WRONG(202, "未接通->空号/错号"),
    OFF_SERVICE(203, "未接通->停机/欠费/呼入限制/过期电话");
    
    private int code;
    private String lable;

    private CustomerStatus(int code, String lable) {
        this.code = code;
        this.lable = lable;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }
    
}
