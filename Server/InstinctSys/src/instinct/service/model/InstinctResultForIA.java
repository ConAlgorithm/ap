/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package instinct.service.model;

/**
 * InsinctSys返回IA的结果
 *
 * @author baowzh
 * @version InstinctResultForIA.java, V1.0 2016年9月9日 下午9:43:37
 */
public class InstinctResultForIA {

    /**
     * 决定结果
     */
    private String decisionResult;
    /**
     * 决定原因
     */
    private String decisionReason;

    public String getDecisionResult() {
        return decisionResult;
    }

    public void setDecisionResult(String decisionResult) {
        this.decisionResult = decisionResult;
    }

    public String getDecisionReason() {
        return decisionReason;
    }

    public void setDecisionReason(String decisionReason) {
        this.decisionReason = decisionReason;
    }

}
