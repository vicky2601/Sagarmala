package org.sagarmala.dao;

import java.util.LinkedHashMap;
import java.util.List;

import org.sagarmala.bean.SearchRoleBean;
import org.sagarmala.bean.SearchUserBean;
import org.sagarmala.model.Agency;
import org.sagarmala.model.CommonMasters;
import org.sagarmala.model.DynamicAddUserMenuEntity;
import org.sagarmala.model.LoginTrail;
import org.sagarmala.model.MinorPortMaster;
import org.sagarmala.model.OgatizationMaster;
import org.sagarmala.model.OrgRoleMapping;
import org.sagarmala.model.PORT;
import org.sagarmala.model.RoleAuthorization;
import org.sagarmala.model.StateMaster;
import org.sagarmala.model.UserDetail;

public interface UserDao {

	UserDetail findByUserName(String username);

	int loginTrail(LoginTrail loginTrail);

	void saveUser(UserDetail userDetail);

	List<OrgRoleMapping> fetchRole(SearchRoleBean searchRoleBean, LinkedHashMap<Object, Object> response);

	List<UserDetail> fetchUser(SearchUserBean searchUserBean, LinkedHashMap<Object, Object> response);

	int saveUserDetails(UserDetail userDetail);

	void updateUserDeatils(UserDetail userDetail);

	boolean saveRoleAuthorization(List<RoleAuthorization> roleAuthorization);
	
	public List<RoleAuthorization> fetchRoleAuthByRoleId(Long roledId);

	public UserDetail findByUserId(int userId);

	List<RoleAuthorization> fetchRoleAuthorization();

	UserDetail findByEmail(String email);

	UserDetail findByPhoneNumber(String phoneNumber);

	UserDetail findLastUserByCondition(SearchUserBean userBean);

	CommonMasters findDepartmentByMasterId(int masterId);//changed

	UserDetail findByUserByDepartementId(SearchUserBean userBean);
	
	List<DynamicAddUserMenuEntity>findAllSubMenusByMainMenuId(int id);
	
	void saveAgency(Agency userDetail);
	
	OgatizationMaster findOganizationByMasterId(int masterId);
	
	PORT findPortByMasterId(int masterId);
	
	StateMaster findStateByMasterId(int masterId);
	
	Agency findAgencyByMasterId(int masterId);
	
	MinorPortMaster findMinnerPortMasterByMasterId(int masterId);
}
