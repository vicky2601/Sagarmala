package org.sagarmala.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "add_user_dynamic")
public class DynamicAddUserMenuEntity implements Serializable{

	/** Primary key. */
	private static final long serialVersionUID = 1L;
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "organization_id")
	private Integer organizationId;
	@Column(name = "sub_menu")
	private String subMenu;
	@Column(name = "sub_menu_type")
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
