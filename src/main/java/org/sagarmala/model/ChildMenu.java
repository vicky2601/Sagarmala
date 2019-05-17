package org.sagarmala.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "mst_menu_child")
public class ChildMenu implements Serializable{
	
	/** Primary key. */
	private static final long serialVersionUID = 1L;
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "child_menu_id")
	private int childMenuId;

	@ManyToOne	
	private ParentMenu parentMenu;

	@Column(name = "child_name")
	private String childName;
	
	@Column(name = "has_add")
	private int hasAdd;

	@Column(name = "has_edit")
	private int hasEdit;
	
	@Column(name = "has_view")
	private int hasView;
	
	@Column(name = "has_approve")
	private int hasApprove;
	
	@Column(name = "has_reject")
	private int hasReject;
	public int getChildMenuId() {
		return childMenuId;
	}

	public void setChildMenuId(int childMenuId) {
		this.childMenuId = childMenuId;
	}

	public ParentMenu getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(ParentMenu parentMenu) {
		this.parentMenu = parentMenu;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	public int getHasAdd() {
		return hasAdd;
	}

	public void setHasAdd(int hasAdd) {
		this.hasAdd = hasAdd;
	}

	public int getHasEdit() {
		return hasEdit;
	}

	public void setHasEdit(int hasEdit) {
		this.hasEdit = hasEdit;
	}

	public int getHasView() {
		return hasView;
	}

	public void setHasView(int hasView) {
		this.hasView = hasView;
	}

	public int getHasApprove() {
		return hasApprove;
	}

	public void setHasApprove(int hasApprove) {
		this.hasApprove = hasApprove;
	}

	public int getHasReject() {
		return hasReject;
	}

	public void setHasReject(int hasReject) {
		this.hasReject = hasReject;
	}


}
