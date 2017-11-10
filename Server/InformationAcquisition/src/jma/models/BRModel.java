/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.models;

/**
 * 〈百融结果〉
 *
 * @author hwei
 * @version BRModel.java, V1.0 2016年11月21日 下午8:42:07
 */
public class BRModel {

    //通过身份证查询银行不良:
    //0----代表通过用户提供的key值（id、cell、gid）直接命中
    //1----代表由key（id、cell、gid）扩展命中第一阶层社交关系（1度人脉）
    //2----代表由key（id、cell、gid）扩展命中第二阶层社交关系命（2度人脉）
    private String x_BR_SpecialListIdBankBad;
    //通过身份证查询银行短时逾期
    private String x_BR_SpecialListIdBankOverdue;
    //通过身份证查询银行欺诈
    private String x_BR_SpecialListIdBankFraud;
    //通过身份证查询银行失联
    private String x_BR_SpecialListIdBankLost;
    //通过身份证查询银行拒绝
    private String x_BR_SpecialListIdBankRefuse;
    //通过身份证查询资信不良
    private String x_BR_SpecialListIdCreditBad;
    //通过身份证查询小贷或P2P短时逾期
    private String x_BR_SpecialListIdP2PBad;
    //通过身份证查询小贷或P2P短时逾期
    private String x_BR_SpecialListIdP2POverdue;
    //通过身份证查询小贷或P2P欺诈
    private String x_BR_SpecialListIdP2PFraud;
    //通过身份证查询小贷或P2P失联
    private String x_BR_SpecialListIdP2PLost;
    //通过身份证查询小贷或P2P拒绝
    private String x_BR_SpecialListIdP2PRefuse;
    //通过身份证查询电信欠费
    private String x_BR_SpecialListIdPhoneOverdue;
    // 通过身份证查询保险骗保
    private String x_BR_SpecialListIdInsFraud;
    //通过身份证查询法院失信人
    private String x_BR_SpecialListIdCourtBad;
    //通过身份证查询法院被执行人
    private String x_BR_SpecialListIdCourtExecuted;
    //通过手机查询银行不良
    private String x_BR_SpecialListCellBankBad;
    //通过手机查询银行短时逾期
    private String x_BR_SpecialListCellBankOverdue;
    //通过手机查询银行欺诈
    private String x_BR_SpecialListCellBankFraud;
    //通过手机查询银行失联
    private String x_BR_SpecialListCellBankLost;
    //通过手机查询银行拒绝
    private String x_BR_SpecialListCellBankRefuse;
    //通过手机查询小贷或P2P不良
    private String x_BR_SpecialListCellP2PBad;
    //通过手机查询小贷或P2P短时逾期
    private String x_BR_SpecialListCellP2POverdue;
    //通过手机查询小贷或P2P欺诈
    private String x_BR_SpecialListCellP2PFraud;
    //通过手机查询小贷或P2P失联
    private String x_BR_SpecialListCellP2PLost;
    //通过手机查询小贷或P2P拒绝
    private String x_BR_SpecialListCellP2PRefuse;
    //通过手机查询电信欠费
    private String x_BR_SpecialListCellPhoneOverdue;
    //通过手机查询保险骗保
    private String x_BR_SpecialListCellInsFraud;
    //风险规则：
    //命中取值为1，未命中取值为空。
    //命中规则：近6个月是否有连续2次及以上逾期，或近3个月是否有2次及以上逾期
    private String x_BR_RJ_Rule;
    //身份证特殊名单校验：
    //身份证是否被特殊名单库击中，取“空/1”。其中空表示未命中，1表示命中。
    private String x_BR_sl_id;
    //手机特殊名单校验：手机是否被特殊名单库击中，取“空/1”。其中空表示未命中，1表示命中。
    private String x_BR_sl_cell;

    public String getX_BR_SpecialListIdBankBad() {
        return x_BR_SpecialListIdBankBad;
    }

    public void setX_BR_SpecialListIdBankBad(String x_BR_SpecialListIdBankBad) {
        this.x_BR_SpecialListIdBankBad = x_BR_SpecialListIdBankBad;
    }

    public String getX_BR_SpecialListIdBankOverdue() {
        return x_BR_SpecialListIdBankOverdue;
    }

    public void setX_BR_SpecialListIdBankOverdue(String x_BR_SpecialListIdBankOverdue) {
        this.x_BR_SpecialListIdBankOverdue = x_BR_SpecialListIdBankOverdue;
    }

    public String getX_BR_SpecialListIdBankFraud() {
        return x_BR_SpecialListIdBankFraud;
    }

    public void setX_BR_SpecialListIdBankFraud(String x_BR_SpecialListIdBankFraud) {
        this.x_BR_SpecialListIdBankFraud = x_BR_SpecialListIdBankFraud;
    }

    public String getX_BR_SpecialListIdBankLost() {
        return x_BR_SpecialListIdBankLost;
    }

    public void setX_BR_SpecialListIdBankLost(String x_BR_SpecialListIdBankLost) {
        this.x_BR_SpecialListIdBankLost = x_BR_SpecialListIdBankLost;
    }

    public String getX_BR_SpecialListIdBankRefuse() {
        return x_BR_SpecialListIdBankRefuse;
    }

    public void setX_BR_SpecialListIdBankRefuse(String x_BR_SpecialListIdBankRefuse) {
        this.x_BR_SpecialListIdBankRefuse = x_BR_SpecialListIdBankRefuse;
    }

    public String getX_BR_SpecialListIdCreditBad() {
        return x_BR_SpecialListIdCreditBad;
    }

    public void setX_BR_SpecialListIdCreditBad(String x_BR_SpecialListIdCreditBad) {
        this.x_BR_SpecialListIdCreditBad = x_BR_SpecialListIdCreditBad;
    }

    public String getX_BR_SpecialListIdP2PBad() {
        return x_BR_SpecialListIdP2PBad;
    }

    public void setX_BR_SpecialListIdP2PBad(String x_BR_SpecialListIdP2PBad) {
        this.x_BR_SpecialListIdP2PBad = x_BR_SpecialListIdP2PBad;
    }

    public String getX_BR_SpecialListIdP2POverdue() {
        return x_BR_SpecialListIdP2POverdue;
    }

    public void setX_BR_SpecialListIdP2POverdue(String x_BR_SpecialListIdP2POverdue) {
        this.x_BR_SpecialListIdP2POverdue = x_BR_SpecialListIdP2POverdue;
    }

    public String getX_BR_SpecialListIdP2PFraud() {
        return x_BR_SpecialListIdP2PFraud;
    }

    public void setX_BR_SpecialListIdP2PFraud(String x_BR_SpecialListIdP2PFraud) {
        this.x_BR_SpecialListIdP2PFraud = x_BR_SpecialListIdP2PFraud;
    }

    public String getX_BR_SpecialListIdP2PLost() {
        return x_BR_SpecialListIdP2PLost;
    }

    public void setX_BR_SpecialListIdP2PLost(String x_BR_SpecialListIdP2PLost) {
        this.x_BR_SpecialListIdP2PLost = x_BR_SpecialListIdP2PLost;
    }

    public String getX_BR_SpecialListIdP2PRefuse() {
        return x_BR_SpecialListIdP2PRefuse;
    }

    public void setX_BR_SpecialListIdP2PRefuse(String x_BR_SpecialListIdP2PRefuse) {
        this.x_BR_SpecialListIdP2PRefuse = x_BR_SpecialListIdP2PRefuse;
    }

    public String getX_BR_SpecialListIdPhoneOverdue() {
        return x_BR_SpecialListIdPhoneOverdue;
    }

    public void setX_BR_SpecialListIdPhoneOverdue(String x_BR_SpecialListIdPhoneOverdue) {
        this.x_BR_SpecialListIdPhoneOverdue = x_BR_SpecialListIdPhoneOverdue;
    }

    public String getX_BR_SpecialListIdInsFraud() {
        return x_BR_SpecialListIdInsFraud;
    }

    public void setX_BR_SpecialListIdInsFraud(String x_BR_SpecialListIdInsFraud) {
        this.x_BR_SpecialListIdInsFraud = x_BR_SpecialListIdInsFraud;
    }

    public String getX_BR_SpecialListIdCourtBad() {
        return x_BR_SpecialListIdCourtBad;
    }

    public void setX_BR_SpecialListIdCourtBad(String x_BR_SpecialListIdCourtBad) {
        this.x_BR_SpecialListIdCourtBad = x_BR_SpecialListIdCourtBad;
    }

    public String getX_BR_SpecialListIdCourtExecuted() {
        return x_BR_SpecialListIdCourtExecuted;
    }

    public void setX_BR_SpecialListIdCourtExecuted(String x_BR_SpecialListIdCourtExecuted) {
        this.x_BR_SpecialListIdCourtExecuted = x_BR_SpecialListIdCourtExecuted;
    }

    public String getX_BR_SpecialListCellBankBad() {
        return x_BR_SpecialListCellBankBad;
    }

    public void setX_BR_SpecialListCellBankBad(String x_BR_SpecialListCellBankBad) {
        this.x_BR_SpecialListCellBankBad = x_BR_SpecialListCellBankBad;
    }

    public String getX_BR_SpecialListCellBankOverdue() {
        return x_BR_SpecialListCellBankOverdue;
    }

    public void setX_BR_SpecialListCellBankOverdue(String x_BR_SpecialListCellBankOverdue) {
        this.x_BR_SpecialListCellBankOverdue = x_BR_SpecialListCellBankOverdue;
    }

    public String getX_BR_SpecialListCellBankFraud() {
        return x_BR_SpecialListCellBankFraud;
    }

    public void setX_BR_SpecialListCellBankFraud(String x_BR_SpecialListCellBankFraud) {
        this.x_BR_SpecialListCellBankFraud = x_BR_SpecialListCellBankFraud;
    }

    public String getX_BR_SpecialListCellBankLost() {
        return x_BR_SpecialListCellBankLost;
    }

    public void setX_BR_SpecialListCellBankLost(String x_BR_SpecialListCellBankLost) {
        this.x_BR_SpecialListCellBankLost = x_BR_SpecialListCellBankLost;
    }

    public String getX_BR_SpecialListCellBankRefuse() {
        return x_BR_SpecialListCellBankRefuse;
    }

    public void setX_BR_SpecialListCellBankRefuse(String x_BR_SpecialListCellBankRefuse) {
        this.x_BR_SpecialListCellBankRefuse = x_BR_SpecialListCellBankRefuse;
    }

    public String getX_BR_SpecialListCellP2PBad() {
        return x_BR_SpecialListCellP2PBad;
    }

    public void setX_BR_SpecialListCellP2PBad(String x_BR_SpecialListCellP2PBad) {
        this.x_BR_SpecialListCellP2PBad = x_BR_SpecialListCellP2PBad;
    }

    public String getX_BR_SpecialListCellP2POverdue() {
        return x_BR_SpecialListCellP2POverdue;
    }

    public void setX_BR_SpecialListCellP2POverdue(String x_BR_SpecialListCellP2POverdue) {
        this.x_BR_SpecialListCellP2POverdue = x_BR_SpecialListCellP2POverdue;
    }

    public String getX_BR_SpecialListCellP2PFraud() {
        return x_BR_SpecialListCellP2PFraud;
    }

    public void setX_BR_SpecialListCellP2PFraud(String x_BR_SpecialListCellP2PFraud) {
        this.x_BR_SpecialListCellP2PFraud = x_BR_SpecialListCellP2PFraud;
    }

    public String getX_BR_SpecialListCellP2PLost() {
        return x_BR_SpecialListCellP2PLost;
    }

    public void setX_BR_SpecialListCellP2PLost(String x_BR_SpecialListCellP2PLost) {
        this.x_BR_SpecialListCellP2PLost = x_BR_SpecialListCellP2PLost;
    }

    public String getX_BR_SpecialListCellP2PRefuse() {
        return x_BR_SpecialListCellP2PRefuse;
    }

    public void setX_BR_SpecialListCellP2PRefuse(String x_BR_SpecialListCellP2PRefuse) {
        this.x_BR_SpecialListCellP2PRefuse = x_BR_SpecialListCellP2PRefuse;
    }

    public String getX_BR_SpecialListCellPhoneOverdue() {
        return x_BR_SpecialListCellPhoneOverdue;
    }

    public void setX_BR_SpecialListCellPhoneOverdue(String x_BR_SpecialListCellPhoneOverdue) {
        this.x_BR_SpecialListCellPhoneOverdue = x_BR_SpecialListCellPhoneOverdue;
    }

    public String getX_BR_SpecialListCellInsFraud() {
        return x_BR_SpecialListCellInsFraud;
    }

    public void setX_BR_SpecialListCellInsFraud(String x_BR_SpecialListCellInsFraud) {
        this.x_BR_SpecialListCellInsFraud = x_BR_SpecialListCellInsFraud;
    }

    public String getX_BR_RJ_Rule() {
        return x_BR_RJ_Rule;
    }

    public void setX_BR_RJ_Rule(String x_BR_RJ_Rule) {
        this.x_BR_RJ_Rule = x_BR_RJ_Rule;
    }

    public String getX_BR_sl_id() {
        return x_BR_sl_id;
    }

    public void setX_BR_sl_id(String x_BR_sl_id) {
        this.x_BR_sl_id = x_BR_sl_id;
    }

    public String getX_BR_sl_cell() {
        return x_BR_sl_cell;
    }

    public void setX_BR_sl_cell(String x_BR_sl_cell) {
        this.x_BR_sl_cell = x_BR_sl_cell;
    }

}
