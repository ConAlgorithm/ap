/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package network.relationship.businessdomain;

/**
 * 〈PD系列逾期情况〉
 *
 * @author dengw
 * @version OverDueInfo.java, V1.0 2017年5月18日 下午5:33:39
 */
public class OverDueInfoModel {
	// 申请appId
	private String appId;
	// 用户userId
	private String userId;
	// fpd1
	private int fpd1;
	// fpd7
	private int fpd7;
	// fpd30
	private int fpd30;
	// spd30
	private int spd30;
	// tpd30
	private int tpd30;
	// qpd30
	private int qpd30;
	// m3
	private int m3;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getFpd1() {
		return fpd1;
	}

	public void setFpd1(int fpd1) {
		this.fpd1 = fpd1;
	}

	public int getFpd7() {
		return fpd7;
	}

	public void setFpd7(int fpd7) {
		this.fpd7 = fpd7;
	}

	public int getFpd30() {
		return fpd30;
	}

	public void setFpd30(int fpd30) {
		this.fpd30 = fpd30;
	}

	public int getSpd30() {
		return spd30;
	}

	public void setSpd30(int spd30) {
		this.spd30 = spd30;
	}

	public int getTpd30() {
		return tpd30;
	}

	public void setTpd30(int tpd30) {
		this.tpd30 = tpd30;
	}

	public int getQpd30() {
		return qpd30;
	}

	public void setQpd30(int qpd30) {
		this.qpd30 = qpd30;
	}

	public int getM3() {
		return m3;
	}

	public void setM3(int m3) {
		this.m3 = m3;
	}
}
