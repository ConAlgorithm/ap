package jma.handlers.preprocess.model;


/**
 * 
 * 〈前海征信DSP数据源请求参数〉
 *
 * @author wuwj
 * @version CheckUserCreditOn3rdPartyByDspPreModel.java, V1.0 2017年5月8日 上午11:57:35
 */
public class CheckUserCreditOn3rdPartyByDspPreModel {

    private String name; // 主体名称
    private String idNo; // 证件号码

    public CheckUserCreditOn3rdPartyByDspPreModel() {

    }

    public CheckUserCreditOn3rdPartyByDspPreModel(String name, String idNo) {
        this.name = name;
        this.idNo = idNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    @Override
    public String toString() {
        return String.format("CheckUserCreditOn3rdPartyByDspPreModel[name=%s,idNo=%s]", getName(),
            getIdNo());
    }
}
