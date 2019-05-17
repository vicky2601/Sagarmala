package org.sagarmala.bean;

import java.util.List;

import org.sagarmala.model.OrgRoleMapping;
import org.sagarmala.model.RoleAuthorization;


public class RoleAuthorizationBean {

	
	private OrgRoleMapping roleID;
	private int menuID;
	private int addRight;
	private int editRight;
	private int viewRight;
	private int approveRight;
	private int query;
	private int deleteRight;
	private int id;
   
	
	public OrgRoleMapping getRoleID() {
		return roleID;
	}
	public void setRoleID(OrgRoleMapping roleID) {
		this.roleID = roleID;
	}
	public int getMenuID() {
		return menuID;
	}
	public void setMenuID(int menuID) {
		this.menuID = menuID;
	}
	public int getAddRight() {
		return addRight;
	}
	public void setAddRight(int addRight) {
		this.addRight = addRight;
	}
	public int getEditRight() {
		return editRight;
	}
	public void setEditRight(int editRight) {
		this.editRight = editRight;
	}
	public int getViewRight() {
		return viewRight;
	}
	public void setViewRight(int viewRight) {
		this.viewRight = viewRight;
	}
	public int getApproveRight() {
		return approveRight;
	}
	public void setApproveRight(int approveRight) {
		this.approveRight = approveRight;
	}
	public int getQuery() {
		return query;
	}
	public void setQuery(int query) {
		this.query = query;
	}
	public int getDeleteRight() {
		return deleteRight;
	}
	public void setDeleteRight(int deleteRight) {
		this.deleteRight = deleteRight;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	

	
	
}
