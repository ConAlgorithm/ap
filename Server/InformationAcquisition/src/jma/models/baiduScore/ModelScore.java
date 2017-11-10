/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.models.baiduScore;

/**
 * 〈百度评分模型〉
 *
 * @author hwei
 * @version ModelScore.java, V1.0 2017年1月12日 下午5:23:17
 */
public class ModelScore {
    /**
     * 是否匹配上百度ID，1代表能够匹配，那么mdx_usmbl返回的是含百度X和买单侠X的模型评分，0代表未匹配，那么mdx_usmbl返回的是只含买单侠X的模型评分
     */
    private String score_type;
    /**
     * 分值越大风险越高，使用时请注意该分值只是一个排序，不代表真实逾期率若score_type取值为1，则该评分是含百度X和买单侠X的模型评分，
     * 若score_type取值为0，则该评分是只含买单侠X的模型评分取值区间：(0,1)，其它值属非法，忽略
     */
    private String mdx_usmbl;

    public String getScore_type() {
        return score_type;
    }

    public void setScore_type(String score_type) {
        this.score_type = score_type;
    }

    public String getMdx_usmbl() {
        return mdx_usmbl;
    }

    public void setMdx_usmbl(String mdx_usmbl) {
        this.mdx_usmbl = mdx_usmbl;
    }

}
