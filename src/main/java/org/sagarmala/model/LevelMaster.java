package org.sagarmala.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mst_level")
public class LevelMaster implements Serializable {
	
	/** Primary key. */
	private static final long serialVersionUID = 1L;
    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="level_id")
	private int department_d;
	
	@Column(name = "level_code")
	private String levelCode;
	
	@Column(name = "level_name")
	private String levelName;
	
	@Column(name = "priority")
	private int priority;
	
	@Column(name = "is_active")
	private int isActive;

	public int getDepartment_d() {
		return department_d;
	}

	public void setDepartment_d(int department_d) {
		this.department_d = department_d;
	}

	public String getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "LevelMaster [department_d=" + department_d + ", levelCode=" + levelCode + ", levelName=" + levelName
				+ ", priority=" + priority + ", isActive=" + isActive + "]";
	}
	
}
