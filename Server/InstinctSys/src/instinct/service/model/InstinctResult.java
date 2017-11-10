/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package instinct.service.model;

import java.math.BigDecimal;

import catfish.base.StartupConfig;
import util.UuidUtil;

/**
 * InstinctResult encapsulates the synchronized online judge result and the asynchronized manual judging result from Instinct server.<p>
 * Note that, in version 1.0.0, the result fields are hardcoded and in the future version, <tt>.xml</tt> formatted response will be used 
 * instead of a string delimited by <tt>delimiter</tt>. 
 * 
 * @author guoqing
 * @version 1.0.0
 * @since 1.0.0
 * 
 */
public class InstinctResult {
    public String                  organisation    = "OMN";
    public String                  countryCode     = "CN";
    public String                  groupMemberCode = "";
    public String                  appId;
    public String                  captureDate;
    public BigDecimal              captureTime;
    public String                  appType;
    public BigDecimal              fraudScore;
    public InstinctFraudAlertType  fraudAlert;                                                      //“H” = High Fraud Potential (HFP), “S” = Suspect,“C” = Clean
    public InstinctActionTakenType actionTaken;                                                     //Null = No Action Taken “K” = Known Fraud “S” = Suspicious “U” = Under Investigation “F” = False Positive
    public String                  errorCode;                                                       // refer to instinct error table 
    public String                  userId;
    public String                  decisionReason;

    private static String          delimiter       = StartupConfig.get("instinct.delimiter") == null
        ? "|" : StartupConfig.get("instinct.delimiter");

    public InstinctResult() {

    }

    public final void clear() {
        this.appId = null;
        this.captureDate = null;
        this.captureTime = null;
        this.appType = null;
        this.fraudScore = null;
        this.fraudAlert = null;
        this.actionTaken = null;
        this.errorCode = null;
        this.userId = null;
        this.decisionReason = null;
    }

    public InstinctResult(String instinctRes) {
        String[] tmp = instinctRes.split("\\" + delimiter, -1);
        this.organisation = tmp[0];
        this.countryCode = tmp[1];
        this.groupMemberCode = tmp[2];
        this.appId = UuidUtil.uncompress(tmp[3]).toUpperCase();

        this.captureDate = tmp[4];
        this.captureTime = tmp[5].isEmpty() ? null : new BigDecimal(tmp[5]);
        this.appType = tmp[6];
        this.fraudScore = tmp[7].isEmpty() ? null : new BigDecimal(tmp[7]);
        this.fraudAlert = InstinctFraudAlertType.forValue(tmp[8]);
        this.actionTaken = InstinctActionTakenType.forValue(tmp[9]);
        this.errorCode = tmp[10];
        this.userId = tmp[11];
        this.decisionReason = tmp[12];
    }

    public InstinctResult(String instinctRes, String delim) {
        String[] tmp = instinctRes.split("\\" + delim, -1);
        this.organisation = tmp[0];
        this.countryCode = tmp[1];
        this.groupMemberCode = tmp[2];
        this.appId = UuidUtil.uncompress(tmp[3]).toUpperCase();

        this.captureDate = tmp[4];
        this.captureTime = tmp[5].isEmpty() ? null : new BigDecimal(tmp[5]);
        this.appType = tmp[6];
        this.fraudScore = tmp[7].isEmpty() ? null : new BigDecimal(tmp[7]);
        this.fraudAlert = InstinctFraudAlertType.forValue(tmp[8]);
        this.actionTaken = InstinctActionTakenType.forValue(tmp[9]);
        this.errorCode = tmp[10];
        this.userId = tmp[11];
        this.decisionReason = tmp[12];
    }

    @Override
    public final String toString() {
        return "appId: " + appId + ", fraudScore: " + fraudScore + ", fraudAlert: " + fraudAlert
               + ", actionTaken: " + actionTaken + ", decisionReason: " + decisionReason;
    }

    public String getRecisionCode() {
        // 只截取决定原因中决定代码
        String reason = "";
        if (this.decisionReason != null) {
            reason = this.decisionReason;
        }
        String[] reasonArr = reason.split(" ");
        // 决定原因编码判定
        if (reasonArr.length > 1) {
            return reasonArr[0];
        }

        return "";
    }
}
