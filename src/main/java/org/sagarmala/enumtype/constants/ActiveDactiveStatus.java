package org.sagarmala.enumtype.constants;
public enum ActiveDactiveStatus {
	ACTIVE(1,"Active"),DEACTIVE(0,"InActive"),BLANK(-1,"blank");
	
	private int id;
	private String description;
	private ActiveDactiveStatus(int id, String description) {
		this.id = id;
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}