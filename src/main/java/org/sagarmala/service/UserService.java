
package org.sagarmala.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.sagarmala.bean.RoleAuthorizationBean;
import org.sagarmala.bean.SearchRoleBean;
import org.sagarmala.bean.SearchUserBean;
import org.sagarmala.bean.UserBean;
import org.sagarmala.dto.ServiceResponse;
import org.sagarmala.model.Agency;
import org.sagarmala.model.DynamicAddUserMenuDTO;
import org.sagarmala.model.LoginTrail;
import org.sagarmala.model.RoleAuthorization;
import org.sagarmala.model.UserDetail;

public interface UserService {

	public UserDetail findByUserName(String username);

	int loginTrail(LoginTrail loginTrail);

	void saveUser(UserDetail userDetail);

	// 28/11/2018
	public ServiceResponse forgetPassword(UserDetail user);

	public ServiceResponse verifyTokenByUsername(UserBean userBean);

	public ServiceResponse resetPassword(UserBean userBean);

	public ServiceResponse fetchRole(SearchRoleBean searchRoleBean);

	public ServiceResponse fetchUser(SearchUserBean searchUserBean);

	ServiceResponse saveUserDetail(UserDetail user);

	public ServiceResponse roleAuthorization(List<RoleAuthorization> roleAuthorization);

	public ServiceResponse fetchRoleAuthorization();

	ServiceResponse activeOrDeactiveUser(UserBean userBean);

	public ServiceResponse changePassword(UserBean userBean);

	public ServiceResponse sendUserNameAtForgetLoginId(UserBean userBean);

	public ServiceResponse generateLoginId(SearchUserBean userBean);

	public List<RoleAuthorizationBean> findroleAuthByRoleId(Long id);

	ServiceResponse fetchRoleAuthorizationByRoleId(long roleId);

	ServiceResponse downdloadUserExcelFile(SearchUserBean searchUserBean);

	ServiceResponse downdloadUserPdfFile(SearchUserBean searchUserBean);
	
	public List<DynamicAddUserMenuDTO> fetchAllSubMenuByMainMenuId(int id) throws IllegalAccessException, InvocationTargetException;

	public ServiceResponse resetPasswordBySuperAdmin(UserBean userBean);
	
	public ServiceResponse saveA(Agency userBean);
	
	ServiceResponse updateUserForAssignProj(UserDetail user);

}
