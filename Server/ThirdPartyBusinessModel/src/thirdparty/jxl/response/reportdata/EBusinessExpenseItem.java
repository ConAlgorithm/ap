package thirdparty.jxl.response.reportdata;

import com.google.gson.annotations.SerializedName;

public class EBusinessExpenseItem {

	/*
		�����·�	trans_mth	string	�����·�
		���˹�����	owner_amount	float	���˹�����
		���˹������	owner_count	int	���˹������
		����+ͬס�˹�����	family_amount	float	����+ͬס�˹�����
		����+ͬס�˹������	family_count	int	����+ͬס�˹������
		�ܹ�����	all_amount	float	�ܹ�����
		�ܹ������	all_count	int	�ܹ������
    */
	
	//�����·�
	@SerializedName("trans_mth")
	private String transMth;
		
	//���˹�����
	@SerializedName("owner_amount")
	private float ownerAmount;
	
	//���˹������
	@SerializedName("owner_count")
	private int ownerCount;
	
	//����+ͬס�˹�����
	@SerializedName("family_amount")
	private float familyAmount;
	
	//����+ͬס�˹������
	@SerializedName("family_count")
	private int familyCount;
	
	//�ܹ�����
	@SerializedName("all_amount")
	private float allAmount;
	
	//�ܹ������
	@SerializedName("all_count")
	private int allCount;

	public String getTransMth() {
		return transMth;
	}

	public void setTransMth(String transMth) {
		this.transMth = transMth;
	}

	public float getOwnerAmount() {
		return ownerAmount;
	}

	public void setOwnerAmount(float ownerAmount) {
		this.ownerAmount = ownerAmount;
	}

	public int getOwnerCount() {
		return ownerCount;
	}

	public void setOwnerCount(int ownerCount) {
		this.ownerCount = ownerCount;
	}

	public float getFamilyAmount() {
		return familyAmount;
	}

	public void setFamilyAmount(float familyAmount) {
		this.familyAmount = familyAmount;
	}

	public int getFamilyCount() {
		return familyCount;
	}

	public void setFamilyCount(int familyCount) {
		this.familyCount = familyCount;
	}

	public float getAllAmount() {
		return allAmount;
	}

	public void setAllAmount(float allAmount) {
		this.allAmount = allAmount;
	}

	public int getAllCount() {
		return allCount;
	}

	public void setAllCount(int allCount) {
		this.allCount = allCount;
	}	
}
