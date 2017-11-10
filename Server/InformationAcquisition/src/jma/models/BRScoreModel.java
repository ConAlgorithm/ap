/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.models;

/**
 * 〈百融结果〉
 *
 * @author dengw
 * @version BRScoreModel.java, V1.0 2017年5月5日
 */
public class BRScoreModel {
	// 响应标识
	private String code;
	// 流水号
	private String swift_number;
	// 百融评分输出标识1(输出成功),0(未匹配上无输出),98(用户输入信息不足),99(系统异常)
	private Integer flag_score;
	// 非银消费贷线下评分V2.0 评分取值范围[300,1000]，分数越高，客户信用越好
	private Integer brScore;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSwift_number() {
		return swift_number;
	}

	public void setSwift_number(String swift_number) {
		this.swift_number = swift_number;
	}

	public Integer getFlag_score() {
		return flag_score;
	}

	public void setFlag_score(Integer flag_score) {
		this.flag_score = flag_score;
	}

	public Integer getBrScore() {
		return brScore;
	}

	public void setBrScore(Integer brScore) {
		this.brScore = brScore;
	}
}
