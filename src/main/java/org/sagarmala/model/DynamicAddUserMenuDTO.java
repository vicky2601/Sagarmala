package org.sagarmala.model;

public class DynamicAddUserMenuDTO {
	private Integer id;
	private Integer organizationId;
	private String subMenu;
	private String subMenuType;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	public String getSubMenu() {
		return subMenu;
	}
	public void setSubMenu(String subMenu) {
		this.subMenu = subMenu;
	}
	public String getSubMenuType() {
		return subMenuType;
	}
	public void setSubMenuType(String subMenuType) {
		this.subMenuType = subMenuType;
	}
	
	
}
