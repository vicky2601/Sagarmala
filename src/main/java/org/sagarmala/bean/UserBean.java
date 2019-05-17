package org.sagarmala.bean;

public class UserBean {

	private String userName;
	private String token;
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
	private int userId;
	private int isActive;
	private String email;
	private long phoneNumber;
	private String fogetLoginId;
	private int createdBy;
	private int updatedBy;
    private int minnorPortId;
    
	public int getMinnorPortId() {
		return minnorPortId;
	}

	public void setMinnorPortId(int minnorPortId) {
		this.minnorPortId = minnorPortId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFogetLoginId() {
		return fogetLoginId;
	}

	public void setFogetLoginId(String fogetLoginId) {
		this.fogetLoginId = fogetLoginId;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public String toString() {
		return "UserBean [userName=" + userName + ", token=" + token + ", oldPassword=" + oldPassword + ", newPassword="
				+ newPassword + ", confirmPassword=" + confirmPassword + ", userId=" + userId + ", isActive=" + isActive
				+ ", email=" + email + ", phoneNumber=" + phoneNumber + ", fogetLoginId=" + fogetLoginId
				+ ", createdBy=" + createdBy + ", updatedBy=" + updatedBy + ", minnorPortId=" + minnorPortId + "]";
	}

}
