/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.models.jxl;

/**
 * 〈用户行为检测〉
 *
 * @author hwei
 * @version BehaviorCheck.java, V1.0 2017年2月6日 上午10:33:37
 */
public class BehaviorCheck {
    // 分析点
    private String check_point;
    // 分析点中文
    private String check_point_cn;
    // 检查结果
    private String result;
    // 证据
    private String evidence;
    // 标记
    private int score;
    
    public String getCheck_point() {
        return check_point;
    }
    public void setCheck_point(String check_point) {
        this.check_point = check_point;
    }
    public String getCheck_point_cn() {
        return check_point_cn;
    }
    public void setCheck_point_cn(String check_point_cn) {
        this.check_point_cn = check_point_cn;
    }
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public String getEvidence() {
        return evidence;
    }
    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }

    
}
