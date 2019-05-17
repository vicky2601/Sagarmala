package org.sagarmala.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "mst_role_auth")
public class RoleAuthorization implements Serializable{

	/** Primary key. */
	private static final long serialVersionUID = 1L;
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@JoinColumn(name = "role_org_id")
	@OneToOne(fetch = FetchType.EAGER)
	private OrgRoleMapping orgRoleId;

	@Column(name = "MenuID")
	private int menuID;

	@Column(name = "AddRight")
	private int addRight;

	@Column(name = "EditRight")
	private int editRight;

	@Column(name = "ViewRight")
	private int viewRight;

	@Column(name = "ApproveRight")
	private int approveRight;

	@Column(name = "Query")
	private int query;

	@Column(name = "DeleteRight")
	private int deleteRight;

	
   /* @Enumerated(EnumType.STRING)
    private rights allRights;*/
	

	public int getMenuID() {
		return menuID;
	}

	public OrgRoleMapping getOrgRoleId() {
		return orgRoleId;
	}

	public void setOrgRoleId(OrgRoleMapping orgRoleId) {
		this.orgRoleId = orgRoleId;
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

	@Override
	public String toString() {
		return "RoleAuthorization [id=" + id + ", orgRoleId=" + orgRoleId + ", menuID=" + menuID + ", addRight="
				+ addRight + ", editRight=" + editRight + ", viewRight=" + viewRight + ", approveRight=" + approveRight
				+ ", query=" + query + ", deleteRight=" + deleteRight + "]";
	}
}
