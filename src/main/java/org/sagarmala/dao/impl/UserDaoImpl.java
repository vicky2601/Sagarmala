package org.sagarmala.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.sagarmala.bean.SearchRoleBean;
import org.sagarmala.bean.SearchUserBean;
import org.sagarmala.dao.IRoleServiceDao;
import org.sagarmala.dao.MasterDao;
import org.sagarmala.dao.UserDao;
import org.sagarmala.enumtype.constants.ActiveDactiveStatus;
import org.sagarmala.model.Agency;
import org.sagarmala.model.CommonMasters;
import org.sagarmala.model.DynamicAddUserMenuEntity;
import org.sagarmala.model.LoginTrail;
import org.sagarmala.model.MinorPortMaster;
import org.sagarmala.model.OgatizationMaster;
import org.sagarmala.model.OrgRoleMapping;
import org.sagarmala.model.PORT;
import org.sagarmala.model.RoleAuthorization;
import org.sagarmala.model.RoleMasterr;
import org.sagarmala.model.StateMaster;
import org.sagarmala.model.UserDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository(value = "userDao")
@Transactional
public class UserDaoImpl implements UserDao, IRoleServiceDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);
	
	@Autowired
	private MasterDao masterDao;

	@Override
	public UserDetail findByUserName(String username) {
		UserDetail userDetail=new UserDetail();
		try{
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<UserDetail> cr = cb.createQuery(UserDetail.class);
		Root<UserDetail> root = cr.from(UserDetail.class);
		cr.select(root).where(cb.equal(root.get("userName"), username));
		Query<UserDetail> query = session.createQuery(cr);
		LOGGER.info("QUery"+query.getSingleResult());
		userDetail=query.getSingleResult();
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return userDetail;
	}

	@Override
	public int loginTrail(LoginTrail loginTrail) {
		Session session = sessionFactory.getCurrentSession();
		if (loginTrail.getLoginId() == 0) {
			return (int) session.save(loginTrail);
		} else {
			loginTrail = session.get(LoginTrail.class, loginTrail.getLoginId());
			loginTrail.setIslogIn(0);
			session.update(loginTrail);
			return loginTrail.getLoginId();
		}
	}

	@Override
	public void saveUser(UserDetail userDetail) {
		System.out.println(userDetail.toString());
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().merge(userDetail);
	}

	@Override
	public List<OrgRoleMapping> fetchRole(SearchRoleBean searchRoleBean, LinkedHashMap<Object, Object> response) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<OrgRoleMapping> cr = cb.createQuery(OrgRoleMapping.class);
		Root<OrgRoleMapping> root = cr.from(OrgRoleMapping.class);
		List<Predicate> restrictions = new ArrayList<>();
		
		restrictions.add(cb.equal(root.get("isActive"),ActiveDactiveStatus.ACTIVE.getId()));
		
		if (searchRoleBean.getOrganisationId() != 0) {
			restrictions.add(cb.equal(root.get("organisation"), searchRoleBean.getOrganisationId()));
		}
		if (searchRoleBean.getRoleId() != 0) {
			restrictions.add(cb.equal(root.get("roleMaster"), searchRoleBean.getRoleId()));
		}
		if (searchRoleBean.getOrgRoleId() != 0) {
			restrictions.add(cb.equal(root.get("orgRoleId"), searchRoleBean.getOrgRoleId()));
		}
		if (searchRoleBean.getUserRoleId() != null && searchRoleBean.getUserRoleId() != 0 ) {
			
			List<Integer> a=new ArrayList<>();
			RoleMasterr roleMasterr =null;
			List<RoleMasterr> roleMaster =null;
			try{
			roleMasterr = masterDao.fetchRoleByRoleId(searchRoleBean.getUserRoleId());
			roleMaster=masterDao.fetchRoleBysorting(roleMasterr.getSortId());
			for(int i=0;i<roleMaster.size();i++){
				a.add(roleMaster.get(i).getRoleId());
			}
			if(roleMaster!=null)
				restrictions.add(cb.and(root.get("roleMaster").in(a)));
			}
			catch(Exception ex){
				LOGGER.error("Exception occurred in fetchRole method : " + ex);
			}
		}
		Predicate[] restriction = restrictions.toArray(new Predicate[restrictions.size()]);
		cr.select(root).where(cb.and(restriction));
		Query<OrgRoleMapping> query = session.createQuery(cr);
		List<OrgRoleMapping> roles = query.list();
		//response.put("totalSize", roles.size());
		/*
		 * query.setFirstResult(searchRoleBean.getStartResult()); if
		 * (searchRoleBean.getMaxResult() == 0) {
		 * query.setMaxResults(query.list().size()); } else {
		 * query.setMaxResults(searchRoleBean.getMaxResult()); }
		 */

		return roles;
	}

	@Override
	public List<UserDetail> fetchUser(SearchUserBean searchUserBean, LinkedHashMap<Object, Object> response) {
		Session session = sessionFactory.getCurrentSession();
		List<UserDetail> userDetails = null;
		if(searchUserBean.getSearchByUserId() == 1) {
			System.err.println("Id ="+searchUserBean);
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<UserDetail> cr = cb.createQuery(UserDetail.class);
			Root<UserDetail> root = cr.from(UserDetail.class);
			List<Predicate> restrictions = new ArrayList<>();
			if (searchUserBean.getOrganisationId() != 0) {
				restrictions.add(cb.equal(root.get("organisationId"), searchUserBean.getOrganisationId()));
			}
			if (searchUserBean.getStateId() != 0) {
				System.err.println("Id ="+searchUserBean.getStateId());
				restrictions.add(cb.equal(root.get("state"), searchUserBean.getStateId()));
			}
			if (searchUserBean.getPortId() != 0) {
				restrictions.add(cb.equal(root.get("port"), searchUserBean.getPortId()));
			}
			if (searchUserBean.getDepartmentId() != 0) {
				restrictions.add(cb.equal(root.get("departmentId"), searchUserBean.getDepartmentId()));
			}
			if (searchUserBean.getDesignation() != 0) {
				restrictions.add(cb.like(root.get("designation"), "%" + searchUserBean.getDesignation() + "%"));
			}
			if (searchUserBean.getUsername() != null && !searchUserBean.getUsername().equalsIgnoreCase("0")) {
				restrictions.add(cb.like(root.get("name"), "%" + searchUserBean.getUsername() + "%"));
			}
			if (searchUserBean.getRoleId() != 0) {
				restrictions.add(cb.equal(root.get("role"), searchUserBean.getRoleId()));
			}
			if (searchUserBean.getAgencyId() != 0) {
				restrictions.add(cb.equal(root.get("agency"), searchUserBean.getAgencyId()));
			}
			if (searchUserBean.getStatus() != 2) {
				restrictions.add(cb.equal(root.get("isActive"), searchUserBean.getStatus()));
			}
			if (searchUserBean.getLineMinistryId() != 0) {
				restrictions.add(cb.equal(root.get("lineMinistryId"), searchUserBean.getLineMinistryId()));
			}
			if (searchUserBean.getMaritimeBoardId() != 0) {
				restrictions.add(cb.equal(root.get("maritimeBoardId"), searchUserBean.getMaritimeBoardId()));
			}
			if(searchUserBean.getMinnorPortList()!=null && searchUserBean.getMinnorPortList().size()!=0){
				System.err.println(searchUserBean.getMinnorPortList().size());
				//List<Integer> v=new ArrayList<>();
				//v.add(1);
				Set<MinorPortMaster> miPSet=new HashSet<>(searchUserBean.getMinnorPortList());
				System.err.println("================="+miPSet);
				//Expression<MinorPortMaster> exp = root.get("minorPorts").in(searchUserBean.getMinnorPortList());
				
				//restrictions.add(root.get("minorPorts").in(miPSet));
			}
			Predicate[] restriction = restrictions.toArray(new Predicate[restrictions.size()]);
			cr.select(root).where(cb.and(cb.notEqual(root.get("role"), -1), cb.and(restriction)));
			Query<UserDetail> query = session.createQuery(cr);
			userDetails = query.list();
			response.put("totalSize", userDetails.size());
		}else {
			
			String query ="select * from (select  * from (select * from user_data ) user_data,       \r\n" + 
					"(select @pv \\:= '"+searchUserBean.getSearchByUserId()+"') initialisation where   find_in_set(createdBy, @pv) > 0 \r\n" + 
					"and     @pv \\:= concat(@pv, ',', user_Id)) a where deleted=0 ";
			if (searchUserBean.getOrganisationId() != 0) {
				query =query + " and organisationId ="+searchUserBean.getOrganisationId();
			}
			if (searchUserBean.getStateId() != 0) {				
				query =query + " and state ="+ searchUserBean.getStateId();
			}
			if (searchUserBean.getPortId() != 0) {				
				query =query + " and port ="+ searchUserBean.getPortId();
			}
			if (searchUserBean.getDepartmentId() != 0) {				
				query =query + " and department_Id ="+ searchUserBean.getDepartmentId();
			}
			if (searchUserBean.getDesignation() != 0) {				
				query =query + " and designation like '%"+ searchUserBean.getDesignation()+ "%'";
			}
			if (searchUserBean.getUsername() != null && !searchUserBean.getUsername().equalsIgnoreCase("0")) {				
				query =query + " and name like '%"+ searchUserBean.getUsername()+ "%'";
			}
			if (searchUserBean.getRoleId() != 0) {				
				query =query + " and role ="+ searchUserBean.getRoleId();
			}
			if (searchUserBean.getAgencyId() != 0) {				
				query =query + " and agency ="+ searchUserBean.getAgencyId();
			}
			if (searchUserBean.getStatus() != 2) {				
				query =query + " and is_active ="+ searchUserBean.getStatus();
			}
			userDetails = session.createNativeQuery(query,UserDetail.class)
		               .list();			
			response.put("totalSize", userDetails.size());
		}
		
		/*
		 * query.setFirstResult(searchUserBean.getStartResult());
		 * query.setMaxResults(searchUserBean.getMaxResult());
		 */
		return userDetails;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int saveUserDetails(UserDetail userDetail) {
		return (int) sessionFactory.getCurrentSession().save(userDetail);
	}

	@Override
	public void updateUserDeatils(UserDetail userDetail) {
		sessionFactory.getCurrentSession().update(userDetail);
	}

	@Override
	public boolean saveRoleAuthorization(List<RoleAuthorization> roleAuthorization) {
		boolean flag = false;
		try {
			for (int i = 0; i < roleAuthorization.size(); i++) {
				sessionFactory.getCurrentSession().saveOrUpdate(roleAuthorization.get(i));
			}

			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public UserDetail findByUserId(int userId) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<UserDetail> cr = cb.createQuery(UserDetail.class);
		Root<UserDetail> root = cr.from(UserDetail.class);
		cr.select(root).where(cb.equal(root.get("userId"), userId));
		Query<UserDetail> query = session.createQuery(cr);
		return query.getSingleResult();
	}

	@Override
	public List<RoleAuthorization> fetchRoleAuthorization() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<RoleAuthorization> cr = cb.createQuery(RoleAuthorization.class);

		Root<RoleAuthorization> root = cr.from(RoleAuthorization.class);
		cr.select(root);
		Query<RoleAuthorization> query = session.createQuery(cr);
		return query.list();
	}

	@Override
	public UserDetail findByEmail(String email) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<UserDetail> cr = cb.createQuery(UserDetail.class);
		Root<UserDetail> root = cr.from(UserDetail.class);
		cr.select(root).where(cb.equal(root.get("email"), email));
		Query<UserDetail> query = session.createQuery(cr);
		return query.getSingleResult();
	}

	@Override
	public UserDetail findByPhoneNumber(String phoneNumber) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<UserDetail> cr = cb.createQuery(UserDetail.class);
		Root<UserDetail> root = cr.from(UserDetail.class);
		cr.select(root).where(cb.equal(root.get("mobileNumber"), phoneNumber));
		Query<UserDetail> query = session.createQuery(cr);
		query.setFirstResult(0);
		query.setMaxResults(1);
		return query.getSingleResult();
	}

	@Override
	public UserDetail findLastUserByCondition(SearchUserBean userBean) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<UserDetail> cr = cb.createQuery(UserDetail.class);
		Root<UserDetail> root = cr.from(UserDetail.class);
		List<Predicate> restrictions = new ArrayList<>();
		if (userBean.getDepartmentId() == 0) {
			restrictions.add(cb.equal(root.get("levelId"), userBean.getLevelId()));
		} else if (userBean.getDepartmentId() == 0) {
			restrictions.add(cb.equal(root.get("levelId"), userBean.getLevelId()));
		} else if (userBean.getDepartmentId() == 0) {
			restrictions.add(cb.equal(root.get("levelId"), userBean.getLevelId()));
		} else if (userBean.getDepartmentId() == 0) {
			restrictions.add(cb.equal(root.get("levelId"), userBean.getLevelId()));
		} else if (userBean.getDepartmentId() == 0) {
			restrictions.add(cb.equal(root.get("levelId"), userBean.getLevelId()));
		} else if (userBean.getDepartmentId() == 0) {
			restrictions.add(cb.equal(root.get("levelId"), userBean.getLevelId()));
		}
		Predicate[] restriction = restrictions.toArray(new Predicate[restrictions.size()]);
		cr.select(root).where(cb.and(restriction));
		Query<UserDetail> query = session.createQuery(cr);
		query.setFirstResult(0);
		query.setMaxResults(1);
		return query.getSingleResult();
	}

	@Override
	public CommonMasters findDepartmentByMasterId(int masterId) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<CommonMasters> cr = cb.createQuery(CommonMasters.class);
		Root<CommonMasters> root = cr.from(CommonMasters.class);
		cr.select(root).where(cb.and(cb.equal(root.get("masterId"), masterId)),cb.equal(root.get("isActive"), ActiveDactiveStatus.ACTIVE.getId()));
		Query<CommonMasters> query = session.createQuery(cr);
		return query.getSingleResult();
	}

	@Override
	public UserDetail findByUserByDepartementId(SearchUserBean userBean) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<UserDetail> cr = cb.createQuery(UserDetail.class);
		Root<UserDetail> root = cr.from(UserDetail.class);
		List<Predicate> restrictions = new ArrayList<>();
		// MOS
		if (userBean.getOrganisationId() == 34) {
			restrictions.add(cb.equal(root.get("organisationId"), userBean.getOrganisationId()));
		}
		// SDCL
		else if (userBean.getOrganisationId() == 35) {
			restrictions.add(cb.equal(root.get("organisationId"), userBean.getOrganisationId()));
		}
		// Line Ministry
		else if (userBean.getOrganisationId() == 36) {
			restrictions.add(cb.equal(root.get("organisationId"), userBean.getOrganisationId()));
			restrictions.add(cb.equal(root.get("lineMinistryId"), userBean.getLineMinistryId()));
		}
		// Major Port Official
		else if (userBean.getOrganisationId() == 37) {
			restrictions.add(cb.equal(root.get("organisationId"), userBean.getOrganisationId()));
			restrictions.add(cb.equal(root.get("port"), userBean.getPortId()));
		}
		// Maritime Board Official
		else if (userBean.getOrganisationId() == 38) {
			restrictions.add(cb.equal(root.get("organisationId"), userBean.getOrganisationId()));
			restrictions.add(cb.equal(root.get("maritimeBoardId"), userBean.getMaritimeBoardId()));
		}
		// State official
		else if (userBean.getOrganisationId() == 39) {
			restrictions.add(cb.equal(root.get("organisationId"), userBean.getOrganisationId()));
			restrictions.add(cb.equal(root.get("state"), userBean.getStateId()));
		}
		// Implementation Agency User
		else if (userBean.getOrganisationId() == 40) {
			restrictions.add(cb.equal(root.get("organisationId"), userBean.getOrganisationId()));
			restrictions.add(cb.equal(root.get("agency"), userBean.getAgencyId()));
		}
		else if (userBean.getOrganisationId() == 41) {
			restrictions.add(cb.equal(root.get("organisationId"), userBean.getOrganisationId()));
			//restrictions.add(cb.equal(root.get("minorPorts"), userBean.getMinnorPortId()));
		}
		Predicate[] restriction = restrictions.toArray(new Predicate[restrictions.size()]);
		System.out.println(restrictions.toString());
		cr.select(root).where(cb.and(restriction)).orderBy(cb.desc(root.get("userId")));
		Query<UserDetail> query = session.createQuery(cr);
		query.setMaxResults(1);
		return query.uniqueResult();
	}

	@Override
	public List<RoleAuthorization> fetchRoleAuthByRoleId(Long roledId) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<RoleAuthorization> cr = cb.createQuery(RoleAuthorization.class);

		Root<RoleAuthorization> root = cr.from(RoleAuthorization.class);
		cr.select(root).where((cb.equal(root.get("orgRoleId"), roledId)));
		Query<RoleAuthorization> query = session.createQuery(cr);
		try {
			System.out.println(new ObjectMapper().writeValueAsString(query.list()));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return query.list();
	}

	@Override
	public List<DynamicAddUserMenuEntity> findAllSubMenusByMainMenuId(int id) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<DynamicAddUserMenuEntity> cr = cb.createQuery(DynamicAddUserMenuEntity.class);
		Root<DynamicAddUserMenuEntity> root = cr.from(DynamicAddUserMenuEntity.class);
		cr.select(root).where((cb.equal(root.get("id"), id)));
		Query<DynamicAddUserMenuEntity> query = session.createQuery(cr);
		return query.list();
	}

	/*@Override
	public List<Role> fetchAllRole() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Role> cr = cb.createQuery(Role.class);
		Root<Role> root = cr.from(Role.class);
		cr.select(root).where(cb.notEqual(root.get("roleId"), -1)).distinct(true);
		Query<Role> query = session.createQuery(cr);
		return query.getResultList();
	}*/
	@Override
	public List<RoleMasterr> fetchAllRole() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<RoleMasterr> cr = cb.createQuery(RoleMasterr.class);
		Root<RoleMasterr> root = cr.from(RoleMasterr.class);
		cr.select(root).where(cb.and(cb.notEqual(root.get("roleId"), -1)),cb.equal(root.get("isActive"), ActiveDactiveStatus.ACTIVE.getId())).distinct(true);
		Query<RoleMasterr> query = session.createQuery(cr);
		return query.getResultList();
	}
	
	@Override
	public void saveAgency(Agency userDetail) {
		sessionFactory.getCurrentSession().saveOrUpdate(userDetail);
	}

	@Override
	public OgatizationMaster findOganizationByMasterId(int masterId) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<OgatizationMaster> cr = cb.createQuery(OgatizationMaster.class);
		Root<OgatizationMaster> root = cr.from(OgatizationMaster.class);
		cr.select(root).where(cb.and(cb.equal(root.get("masterId"), masterId)),cb.equal(root.get("isActive"), ActiveDactiveStatus.ACTIVE.getId()));//(cb.equal(root.get("masterId"), masterId));
		Query<OgatizationMaster> query = session.createQuery(cr);
		return query.getSingleResult();
	}

	@Override
	public PORT findPortByMasterId(int masterId) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<PORT> cr = cb.createQuery(PORT.class);
		Root<PORT> root = cr.from(PORT.class);
		cr.select(root).where(cb.and(cb.equal(root.get("portId"), masterId)),cb.equal(root.get("isActive"), ActiveDactiveStatus.ACTIVE.getId()));//(cb.equal(root.get("masterId"), masterId));
		Query<PORT> query = session.createQuery(cr);
		return query.getSingleResult();
	}

	@Override
	public StateMaster findStateByMasterId(int masterId) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<StateMaster> cr = cb.createQuery(StateMaster.class);
		Root<StateMaster> root = cr.from(StateMaster.class);
		cr.select(root).where(cb.and(cb.equal(root.get("masterId"), masterId)),cb.equal(root.get("isActive"), ActiveDactiveStatus.ACTIVE.getId()));//(cb.equal(root.get("masterId"), masterId));
		Query<StateMaster> query = session.createQuery(cr);
		return query.getSingleResult();
	}

	@Override
	public Agency findAgencyByMasterId(int masterId) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Agency> cr = cb.createQuery(Agency.class);
		Root<Agency> root = cr.from(Agency.class);
		cr.select(root).where(cb.and(cb.equal(root.get("masterId"), masterId)),cb.equal(root.get("isActive"), ActiveDactiveStatus.ACTIVE.getId()));//(cb.equal(root.get("masterId"), masterId));
		Query<Agency> query = session.createQuery(cr);
		return query.getSingleResult();
	}

	@Override
	public MinorPortMaster findMinnerPortMasterByMasterId(int masterId) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<MinorPortMaster> cr = cb.createQuery(MinorPortMaster.class);
		Root<MinorPortMaster> root = cr.from(MinorPortMaster.class);
		cr.select(root).where(cb.and(cb.equal(root.get("masterId"), masterId)),cb.equal(root.get("isActive"), ActiveDactiveStatus.ACTIVE.getId()));//(cb.equal(root.get("masterId"), masterId));
		Query<MinorPortMaster> query = session.createQuery(cr);
		return query.getSingleResult();
	}

}
