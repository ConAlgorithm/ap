package thirdparty.jxl.response.reportdata;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ReportData {
	
	//暂不获取
	//电商信息
	@SerializedName("ebusiness_expense")
	private List<EBusinessExpenseItem> eBusinessList;
	
	//finished!
	//用户信息 
	@SerializedName("person")
	private Person person;
	
	//finished!
	//绑定数据源信息
	@SerializedName("data_source")
	private List<BindDataSourceItem> dataSourceList;
	
	//finished!
	//行为检查点
	@SerializedName("behavior_check")
	private List<CheckPointItem> behaviorCheckPointList;
	
	//finished!
	//数据检查点
	@SerializedName("application_check")
	private List<CheckPointItem> applicationCheckPointList;
	
	//finished!
	//联系人区域汇总
	@SerializedName("contact_region")
	private List<ContactRegionItem> contactRegionList;
	
	//finished!
	//运营商联系人列表
	@SerializedName("contact_list")
	private List<ContactListItem> contactList;
	
	//finished!
	//通话行为分析
	@SerializedName("cell_behavior")
	private List<CellPhoneBehaviorItem> cellPhoneBehaviorList;
	
	//联系人信息
	@SerializedName("collection_contact")
	private List<CollectionContactItem> collectionContactList;

	public List<EBusinessExpenseItem> geteBusinessList() {
		return eBusinessList;
	}

	public void seteBusinessList(List<EBusinessExpenseItem> eBusinessList) {
		this.eBusinessList = eBusinessList;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<BindDataSourceItem> getDataSourceList() {
		return dataSourceList;
	}

	public void setDataSourceList(List<BindDataSourceItem> dataSourceList) {
		this.dataSourceList = dataSourceList;
	}

	public List<CheckPointItem> getBehaviorCheckPointList() {
		return behaviorCheckPointList;
	}

	public void setBehaviorCheckPointList(
			List<CheckPointItem> behaviorCheckPointList) {
		this.behaviorCheckPointList = behaviorCheckPointList;
	}

	public List<CheckPointItem> getApplicationCheckPointList() {
		return applicationCheckPointList;
	}

	public void setApplicationCheckPointList(
			List<CheckPointItem> applicationCheckPointList) {
		this.applicationCheckPointList = applicationCheckPointList;
	}

	public List<ContactRegionItem> getContactRegionList() {
		return contactRegionList;
	}

	public void setContactRegionList(List<ContactRegionItem> contactRegionList) {
		this.contactRegionList = contactRegionList;
	}

	public List<ContactListItem> getContactList() {
		return contactList;
	}

	public void setContactList(List<ContactListItem> contactList) {
		this.contactList = contactList;
	}

	public List<CellPhoneBehaviorItem> getCellPhoneBehaviorList() {
		return cellPhoneBehaviorList;
	}

	public void setCellPhoneBehaviorList(
			List<CellPhoneBehaviorItem> cellPhoneBehaviorList) {
		this.cellPhoneBehaviorList = cellPhoneBehaviorList;
	}

	public List<CollectionContactItem> getCollectionContactList() {
		return collectionContactList;
	}

	public void setCollectionContactList(
			List<CollectionContactItem> collectionContactList) {
		this.collectionContactList = collectionContactList;
	}
}
