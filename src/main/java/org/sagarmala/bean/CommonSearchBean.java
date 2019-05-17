package org.sagarmala.bean;

public class CommonSearchBean {

	private int masterId;
	private String masterName;
	private int masterCode;
	private String masterType;
	private int isActive;

	public int getMasterId() {
		return masterId;
	}

	public void setMasterId(int masterId) {
		this.masterId = masterId;
	}

	public String getMasterName() {
		return masterName;
	}

	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}

	public int getMasterCode() {
		return masterCode;
	}

	public void setMasterCode(int masterCode) {
		this.masterCode = masterCode;
	}

	public String getMasterType() {
		return masterType;
	}

	public void setMasterType(String masterType) {
		this.masterType = masterType;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "CommonSearchBean [masterId=" + masterId + ", masterName=" + masterName + ", masterCode=" + masterCode
				+ ", masterType=" + masterType + ", isActive=" + isActive + "]";
	}

}
