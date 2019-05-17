package org.sagarmala.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.sagarmala.bean.CommonMasterBean;
import org.sagarmala.dao.MasterDao;
import org.sagarmala.enumtype.constants.ActiveDactiveStatus;
import org.sagarmala.model.Agency;
import org.sagarmala.model.CommonMasters;
import org.sagarmala.model.Department;
import org.sagarmala.model.DirectorateOfPortsMaster;
import org.sagarmala.model.FundingAgency;
import org.sagarmala.model.LocationMaster;
import org.sagarmala.model.MaritimeBoard;
import org.sagarmala.model.MinorPortMaster;
import org.sagarmala.model.OgatizationMaster;
import org.sagarmala.model.PORT;
import org.sagarmala.model.ParentMenu;
import org.sagarmala.model.Projects;
import org.sagarmala.model.RoleMasterr;
import org.sagarmala.model.StateMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository(value = "masterDao")
@Transactional
public class MasterDaoImpl implements MasterDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<CommonMasters> fetchCommonMasters() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<CommonMasters> cr = cb.createQuery(CommonMasters.class);
		Root<CommonMasters> root = cr.from(CommonMasters.class);
		cr.select(root).where(cb.equal(root.get("isActive"), ActiveDactiveStatus.ACTIVE.getId()));
		Query<CommonMasters> query = session.createQuery(cr);
		return query.list();
	}

	@Override
	public List<RoleMasterr> fetchRoleByOrganisation(int organisationId, int roleType) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<RoleMasterr> cr = cb.createQuery(RoleMasterr.class);
		Root<RoleMasterr> root = cr.from(RoleMasterr.class);
		cr.select(root).where(cb.and(cb.equal(root.get("roleId"), roleType)),
				cb.equal(root.get("isActive"), ActiveDactiveStatus.ACTIVE.getId()));
		Query<RoleMasterr> query = session.createQuery(cr);
		return query.list();
	}

	@Override
	public List<Department> fetchSubDepartmentByState(int stateId) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Department> cr = cb.createQuery(Department.class);
		Root<Department> root = cr.from(Department.class);
		cr.select(root).where(cb.and(cb.equal(root.get("stateId"), stateId)),
				cb.equal(root.get("isActive"), ActiveDactiveStatus.ACTIVE.getId()));
		Query<Department> query = session.createQuery(cr);
		return query.list();
	}

	@Override
	public List<MaritimeBoard> fetchMaritimeBoardByState(int stateId) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<MaritimeBoard> cr = cb.createQuery(MaritimeBoard.class);
		Root<MaritimeBoard> root = cr.from(MaritimeBoard.class);
		cr.select(root).where(cb.and(cb.equal(root.get("stateId"), stateId)),
				cb.equal(root.get("isActive"), ActiveDactiveStatus.ACTIVE.getId()));
		Query<MaritimeBoard> query = session.createQuery(cr);
		return query.list();
	}

	@Override
	public MaritimeBoard fetchMaritimeBoardById(int maritimeId) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<MaritimeBoard> cr = cb.createQuery(MaritimeBoard.class);
		Root<MaritimeBoard> root = cr.from(MaritimeBoard.class);
		cr.select(root)
				.where(cb.and(cb.notEqual(root.get("maritimeBoardId"), -1),
						cb.equal(root.get("maritimeBoardId"), maritimeId),
						cb.equal(root.get("isActive"), ActiveDactiveStatus.ACTIVE.getId())));
		Query<MaritimeBoard> query = session.createQuery(cr);
		return query.getSingleResult();
	}

	@Override
	public List<PORT> fetchPortByState(int stateId) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<PORT> cr = cb.createQuery(PORT.class);
		Root<PORT> root = cr.from(PORT.class);
		cr.select(root).where(cb.and(cb.notEqual(root.get("portId"), -1), cb.equal(root.get("stateId"), stateId),
				cb.equal(root.get("isActive"), ActiveDactiveStatus.ACTIVE.getId())));
		Query<PORT> query = session.createQuery(cr);
		return query.getResultList();
	}

	@Override
	public List<ParentMenu> fetchParentMenuAlongWithChild() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<ParentMenu> cr = cb.createQuery(ParentMenu.class);
		Root<ParentMenu> root = cr.from(ParentMenu.class);
		Query<ParentMenu> query = session.createQuery(cr);
		List<ParentMenu> pMenu = query.getResultList();
		return pMenu;
	}

	@Override
	public List<PORT> fetchAllPorts() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<PORT> cr = cb.createQuery(PORT.class);
		Root<PORT> root = cr.from(PORT.class);
		cr.select(root).where(cb.and(cb.notEqual(root.get("portId"), ActiveDactiveStatus.BLANK.getId()),
				cb.equal(root.get("isActive"), ActiveDactiveStatus.ACTIVE.getId())));
		Query<PORT> query = session.createQuery(cr);
		return query.getResultList();
	}

	@Override
	public List<MaritimeBoard> fetchAllMaritimeBoard() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<MaritimeBoard> cr = cb.createQuery(MaritimeBoard.class);
		Root<MaritimeBoard> root = cr.from(MaritimeBoard.class);
		cr.select(root).where(cb.and(cb.notEqual(root.get("maritimeBoardId"), ActiveDactiveStatus.BLANK.getId()),
				cb.equal(root.get("isActive"), ActiveDactiveStatus.ACTIVE.getId())));
		Query<MaritimeBoard> query = session.createQuery(cr);
		return query.list();
	}

	@Override
	public List<Department> fetchAllSubDepartment() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Department> cr = cb.createQuery(Department.class);
		Root<Department> root = cr.from(Department.class);
		cr.select(root).where(cb.and(cb.notEqual(root.get("departmentId"), ActiveDactiveStatus.BLANK.getId()),
				cb.equal(root.get("isActive"), ActiveDactiveStatus.ACTIVE.getId())));
		Query<Department> query = session.createQuery(cr);
		return query.list();
	}

	@Override
	public List<OgatizationMaster> fetchOrgatnization() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<OgatizationMaster> cr = cb.createQuery(OgatizationMaster.class);
		Root<OgatizationMaster> root = cr.from(OgatizationMaster.class);
		cr.select(root).where(cb.and(cb.notEqual(root.get("masterId"), ActiveDactiveStatus.BLANK.getId()),
				cb.equal(root.get("isActive"), ActiveDactiveStatus.ACTIVE.getId())));
		Query<OgatizationMaster> query = session.createQuery(cr);
		return query.list();
	}

	@Override
	public List<RoleMasterr> fetchRole() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<RoleMasterr> cr = cb.createQuery(RoleMasterr.class);
		Root<RoleMasterr> root = cr.from(RoleMasterr.class);
		cr.select(root).where(cb.and(cb.notEqual(root.get("roleId"), ActiveDactiveStatus.BLANK.getId()),
				cb.equal(root.get("isActive"), ActiveDactiveStatus.ACTIVE.getId())));
		Query<RoleMasterr> query = session.createQuery(cr);
		return query.list();
	}

	@Override
	public List<Agency> fetchAgency() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Agency> cr = cb.createQuery(Agency.class);
		Root<Agency> root = cr.from(Agency.class);
		cr.select(root).where(cb.and(cb.notEqual(root.get("masterId"), ActiveDactiveStatus.BLANK.getId()),
				cb.equal(root.get("isActive"), ActiveDactiveStatus.ACTIVE.getId())));
		Query<Agency> query = session.createQuery(cr);
		return query.list();
	}

	@Override
	public List<StateMaster> fetchStates() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<StateMaster> cr = cb.createQuery(StateMaster.class);
		Root<StateMaster> root = cr.from(StateMaster.class);
		cr.select(root).where(cb.and(cb.notEqual(root.get("masterId"), ActiveDactiveStatus.BLANK.getId()),
				cb.equal(root.get("isActive"), ActiveDactiveStatus.ACTIVE.getId())));// .where(cb.equal(root.get("stateId"),
																						// stateId));
		Query<StateMaster> query = session.createQuery(cr);
		return query.list();
	}

	@Override
	public Agency fetchAgencyById(Integer agencyId) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Agency> cr = cb.createQuery(Agency.class);
		Root<Agency> root = cr.from(Agency.class);
		cr.select(root).where(cb.and(cb.notEqual(root.get("masterId"), -1), cb.equal(root.get("masterId"), agencyId)));
		Query<Agency> query = session.createQuery(cr);
		return query.getSingleResult();
	}

	@Override
	public boolean saveOrUpdateAgency(Agency agencyData) {
		boolean success = false;
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(agencyData);
			success = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return success;
	}

	@Override
	public RoleMasterr fetchRoleByRoleId(int roleId) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<RoleMasterr> cr = cb.createQuery(RoleMasterr.class);
		Root<RoleMasterr> root = cr.from(RoleMasterr.class);
		cr.select(root).where(cb.and(cb.equal(root.get("roleId"), roleId),
				cb.equal(root.get("isActive"), ActiveDactiveStatus.ACTIVE.getId())));
		Query<RoleMasterr> query = session.createQuery(cr);
		return query.getSingleResult();
	}

	@Override
	public List<RoleMasterr> fetchRoleBysorting(int sortId) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<RoleMasterr> cr = cb.createQuery(RoleMasterr.class);
		Root<RoleMasterr> root = cr.from(RoleMasterr.class);
		cr.select(root)
				.where(cb.and(cb.greaterThan(root.get("sortId"), sortId),
						cb.equal(root.get("isActive"), ActiveDactiveStatus.ACTIVE.getId())))
				.orderBy(cb.asc(root.get("sortId")));
		Query<RoleMasterr> query = session.createQuery(cr);
		return query.getResultList();

	}

	@Override
	public List<MinorPortMaster> fetchAllMinorPortMasters() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<MinorPortMaster> cr = cb.createQuery(MinorPortMaster.class);
		Root<MinorPortMaster> root = cr.from(MinorPortMaster.class);
		cr.select(root).where(cb.and(cb.notEqual(root.get("masterId"), ActiveDactiveStatus.BLANK.getId()),
				cb.equal(root.get("isActive"), ActiveDactiveStatus.ACTIVE.getId())));// .where(cb.equal(root.get("stateId"),
																						// stateId));
		Query<MinorPortMaster> query = session.createQuery(cr);
		return query.list();
	}

	@Override
	public List<MinorPortMaster> fetchMinnorPortsByState(int stateId) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<MinorPortMaster> cr = cb.createQuery(MinorPortMaster.class);
		Root<MinorPortMaster> root = cr.from(MinorPortMaster.class);
		cr.select(root).where(cb.and(cb.equal(root.get("stateId"), stateId)),
				cb.equal(root.get("isActive"), ActiveDactiveStatus.ACTIVE.getId()));
		Query<MinorPortMaster> query = session.createQuery(cr);
		return query.list();
	}

	@Override
	public boolean saveOrUpdateDepartment(Department department) {
		boolean success = false;
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(department);
			success = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return success;
	}

	@Override
	public Department fetchDepartmentById(Integer departmentId) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Department> cr = cb.createQuery(Department.class);
		Root<Department> root = cr.from(Department.class);
		cr.select(root)
				.where(cb.and(cb.notEqual(root.get("departmentId"), -1),
						cb.equal(root.get("departmentId"), departmentId)));
		Query<Department> query = session.createQuery(cr);
		return query.getSingleResult();
	}

	@Override
	public int fetchMaxDepartment() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Integer> criteriaQuery2 = builder.createQuery(Integer.class);
		Root<Department> root2 = criteriaQuery2.from(Department.class);
		criteriaQuery2.select(builder.max(root2.get("departmentCode")));
		Query<Integer> query2 = session.createQuery(criteriaQuery2);
		int maxCode = query2.getSingleResult();
		System.out.println("Max maxCode = " + maxCode);

		return maxCode;
	}

	@Override
	public int fetchMaxAgency() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Integer> criteriaQuery2 = builder.createQuery(Integer.class);
		Root<Agency> root2 = criteriaQuery2.from(Agency.class);
		criteriaQuery2.select(builder.max(root2.get("masterUniqueCode")));
		Query<Integer> query2 = session.createQuery(criteriaQuery2);
		int maxCode = query2.getSingleResult();
		System.out.println("Max maxCode = " + maxCode);

		return maxCode;
	}

	@Override
	public List<Department> fetchDepartmentByStateIdOrDepartmentName(CommonMasterBean commonMasterBean) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Department> cr = cb.createQuery(Department.class);
		Root<Department> root = cr.from(Department.class);
		List<Predicate> restrictions = new ArrayList<>();

		//restrictions.add(cb.equal(root.get("isActive"), ActiveDactiveStatus.ACTIVE.getId()));
		restrictions.add(cb.notEqual(root.get("departmentId"), -1));
		if (commonMasterBean.getStateId() != 0) {
			restrictions.add(cb.equal(root.get("stateId"), commonMasterBean.getStateId()));
		}
		if (commonMasterBean.getMasterName() != null && !commonMasterBean.getMasterName().equalsIgnoreCase("0")) {
			restrictions.add(cb.like(root.get("departmentName"), "%" + commonMasterBean.getMasterName() + "%"));
		}

		Predicate[] restriction = restrictions.toArray(new Predicate[restrictions.size()]);
		cr.select(root).where(cb.and(restriction));
		Query<Department> query = session.createQuery(cr);
		List<Department> department = query.list();

		return department;
	}

	@Override
	public List<Agency> fetchAgencyByStateIdOrAgencyName(CommonMasterBean commonMasterBean) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Agency> cr = cb.createQuery(Agency.class);
		Root<Agency> root = cr.from(Agency.class);
		List<Predicate> restrictions = new ArrayList<>();

		//restrictions.add(cb.equal(root.get("isActive"), ActiveDactiveStatus.ACTIVE.getId()));
		restrictions.add(cb.notEqual(root.get("masterId"), -1));

		if (commonMasterBean.getStateId() != 0) {
			restrictions.add(cb.equal(root.get("stateId"), commonMasterBean.getStateId()));
		}
		if (commonMasterBean.getMasterName() != null && !commonMasterBean.getMasterName().equalsIgnoreCase("0")) {
			restrictions.add(cb.like(root.get("masterName"), "%" + commonMasterBean.getMasterName() + "%"));
		}

		Predicate[] restriction = restrictions.toArray(new Predicate[restrictions.size()]);
		cr.select(root).where(cb.and(restriction));
		Query<Agency> query = session.createQuery(cr);
		List<Agency> agency = query.list();

		return agency;
	}

	@Override
	public CommonMasters fetchLineMinistryById(Integer lineministryId) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<CommonMasters> cr = cb.createQuery(CommonMasters.class);
		Root<CommonMasters> root = cr.from(CommonMasters.class);
		cr.select(root).where(cb.and(cb.notEqual(root.get("masterId"), -1), cb.equal(root.get("masterId"), lineministryId)));
		Query<CommonMasters> query = session.createQuery(cr);
		return query.getSingleResult();
	}

	@Override
	public boolean saveOrUpdateCommonMasters(CommonMasters commonMasters) {
		boolean success = false;
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(commonMasters);
			success = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return success;
	}

	@Override
	public int fetchMaxLineMinistry() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Integer> criteriaQuery2 = builder.createQuery(Integer.class);
		Root<CommonMasters> root2 = criteriaQuery2.from(CommonMasters.class);
		criteriaQuery2.select(builder.max(root2.get("masterCode")));
		Query<Integer> query2 = session.createQuery(criteriaQuery2);
		int maxCode = query2.getSingleResult();
		System.out.println("Max maxCode = " + maxCode);

		return maxCode;
	}

	@Override
	public List<CommonMasters> fetchCommonMastersListByName(CommonMasterBean commonMasterBean) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<CommonMasters> cr = cb.createQuery(CommonMasters.class);
		Root<CommonMasters> root = cr.from(CommonMasters.class);
		List<Predicate> restrictions = new ArrayList<>();

		//restrictions.add(cb.equal(root.get("isActive"), ActiveDactiveStatus.ACTIVE.getId()));
		restrictions.add(cb.notEqual(root.get("masterId"), -1));
		
		if (commonMasterBean.getMasterName() != null && !commonMasterBean.getMasterName().equalsIgnoreCase("0")) {
			restrictions.add(cb.like(root.get("masterName"), "%" + commonMasterBean.getMasterName() + "%"));
		}

		Predicate[] restriction = restrictions.toArray(new Predicate[restrictions.size()]);
		cr.select(root).where(cb.and(restriction));
		Query<CommonMasters> query = session.createQuery(cr);
		List<CommonMasters> commonMasters = query.list();

		return commonMasters;
	}

	@Override
	public List<CommonMasters> fetchLineMinistryByTypeCode(String masterType) {
		try{
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<CommonMasters> cr = cb.createQuery(CommonMasters.class);
		Root<CommonMasters> root = cr.from(CommonMasters.class);
		cr.select(root).where(cb.and(cb.notEqual(root.get("masterId"), -1), cb.equal(root.get("masterTypeCode"), masterType),
				cb.equal(root.get("isActive"), ActiveDactiveStatus.ACTIVE.getId())));
		Query<CommonMasters> query = session.createQuery(cr);
		return query.list();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<CommonMasters> fetchLineMinistryByName(String masterName) {
		try {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<CommonMasters> cr = cb.createQuery(CommonMasters.class);
		Root<CommonMasters> root = cr.from(CommonMasters.class);
		cr.select(root).where(cb.and(cb.notEqual(root.get("masterId"), -1), cb.equal(root.get("masterName"), masterName),
				cb.equal(root.get("isActive"), ActiveDactiveStatus.ACTIVE.getId())));
		Query<CommonMasters> query = session.createQuery(cr);
		return query.list();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Department> fetchDepartmentByName(String masterName) {
		try {
			Session session = sessionFactory.getCurrentSession();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Department> cr = cb.createQuery(Department.class);
			Root<Department> root = cr.from(Department.class);
			cr.select(root).where(cb.and(cb.notEqual(root.get("departmentId"), -1), cb.equal(root.get("departmentName"), masterName),
					cb.equal(root.get("isActive"), ActiveDactiveStatus.ACTIVE.getId())));
			Query<Department> query = session.createQuery(cr);
			return query.list();
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}

	}

	@Override
	public List<Department> fetchDepartmentByTypeCode(String masterType) {
		try{
			Session session = sessionFactory.getCurrentSession();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Department> cr = cb.createQuery(Department.class);
			Root<Department> root = cr.from(Department.class);
			cr.select(root).where(cb.and(cb.notEqual(root.get("departmentId"), -1), cb.equal(root.get("departmentTypeCode"), masterType),
					cb.equal(root.get("isActive"), ActiveDactiveStatus.ACTIVE.getId())));
			Query<Department> query = session.createQuery(cr);
			return query.list();
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
	}

	@Override
	public List<Agency> fetchAgencytByName(String masterName) {
		try {
			Session session = sessionFactory.getCurrentSession();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Agency> cr = cb.createQuery(Agency.class);
			Root<Agency> root = cr.from(Agency.class);
			cr.select(root).where(cb.and(cb.notEqual(root.get("masterId"), -1), cb.equal(root.get("masterName"), masterName),
					cb.equal(root.get("isActive"), ActiveDactiveStatus.ACTIVE.getId())));
			Query<Agency> query = session.createQuery(cr);
			return query.list();
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
	}

	@Override
	public List<Agency> fetchAgencyByTypeCode(String masterType) {
		try{
			Session session = sessionFactory.getCurrentSession();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Agency> cr = cb.createQuery(Agency.class);
			Root<Agency> root = cr.from(Agency.class);
			cr.select(root).where(cb.and(cb.notEqual(root.get("masterId"), -1), cb.equal(root.get("masterCode"), masterType),
					cb.equal(root.get("isActive"), ActiveDactiveStatus.ACTIVE.getId())));
			Query<Agency> query = session.createQuery(cr);
			return query.list();
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
	}
	@Override
	public List<Projects> fetchAllProjects() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Projects> cr = cb.createQuery(Projects.class);
		Root<Projects> root = cr.from(Projects.class);
		cr.select(root).where(cb.equal(root.get("isActive"), ActiveDactiveStatus.ACTIVE.getId()));// .where(cb.equal(root.get("stateId"),																			// stateId));
		Query<Projects> query = session.createQuery(cr);
		return query.list();
	}

	@Override
	public List<FundingAgency> fetchFundingAgencytByName(String masterName) {
		try {
			Session session = sessionFactory.getCurrentSession();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<FundingAgency> cr = cb.createQuery(FundingAgency.class);
			Root<FundingAgency> root = cr.from(FundingAgency.class);
			cr.select(root).where(cb.and(cb.notEqual(root.get("masterId"), -1), cb.equal(root.get("masterName"), masterName),
					cb.equal(root.get("isActive"), ActiveDactiveStatus.ACTIVE.getId())));
			Query<FundingAgency> query = session.createQuery(cr);
			return query.list();
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
	}

	@Override
	public List<FundingAgency> fetchFundingAgencyByTypeCode(String masterType) {
		try{
			Session session = sessionFactory.getCurrentSession();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<FundingAgency> cr = cb.createQuery(FundingAgency.class);
			Root<FundingAgency> root = cr.from(FundingAgency.class);
			cr.select(root).where(cb.and(cb.notEqual(root.get("masterId"), -1), cb.equal(root.get("masterCode"), masterType),
					cb.equal(root.get("isActive"), ActiveDactiveStatus.ACTIVE.getId())));
			Query<FundingAgency> query = session.createQuery(cr);
			return query.list();
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
	}

	@Override
	public int fetchMaxFundingAgency() {
		int maxCode=0;
		try{
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Integer> criteriaQuery2 = builder.createQuery(Integer.class);
		Root<FundingAgency> root2 = criteriaQuery2.from(FundingAgency.class);
		criteriaQuery2.select(builder.max(root2.get("masterUniqueCode")));
		Query<Integer> query2 = session.createQuery(criteriaQuery2);
		maxCode = query2.getSingleResult();
		System.out.println("Max maxCode = " + maxCode);
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return maxCode;
	}

	@Override
	public boolean saveOrUpdateFundingAgency(FundingAgency fundingAgency) {
		boolean success = false;
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(fundingAgency);
			success = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return success;	}

	@Override
	public FundingAgency fetchFundingAgencyById(Integer agencyId) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<FundingAgency> cr = cb.createQuery(FundingAgency.class);
		Root<FundingAgency> root = cr.from(FundingAgency.class);
		cr.select(root).where(cb.and(cb.notEqual(root.get("masterId"), -1), cb.equal(root.get("masterId"), agencyId)));
		Query<FundingAgency> query = session.createQuery(cr);
		return query.getSingleResult();
	}

	@Override
	public List<FundingAgency> fetchFundingAgencyByStateIdOrAgencyName(CommonMasterBean commonMasterBean) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<FundingAgency> cr = cb.createQuery(FundingAgency.class);
		Root<FundingAgency> root = cr.from(FundingAgency.class);
		List<Predicate> restrictions = new ArrayList<>();
		restrictions.add(cb.notEqual(root.get("masterId"), -1));
		if (commonMasterBean.getStateId() != 0) {
			restrictions.add(cb.equal(root.get("stateId"), commonMasterBean.getStateId()));
		}
		if (commonMasterBean.getMasterName() != null && !commonMasterBean.getMasterName().equalsIgnoreCase("0")) {
			restrictions.add(cb.like(root.get("masterName"), "%" + commonMasterBean.getMasterName() + "%"));
		}

		Predicate[] restriction = restrictions.toArray(new Predicate[restrictions.size()]);
		cr.select(root).where(cb.and(restriction));
		Query<FundingAgency> query = session.createQuery(cr);
		List<FundingAgency> fundingAgency = query.list();

		return fundingAgency;
	}

	@Override
	public List<Agency> fetchAgenciesByState(int stateId) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Agency> cr = cb.createQuery(Agency.class);
		Root<Agency> root = cr.from(Agency.class);
		cr.select(root).where(cb.and(cb.equal(root.get("stateId"), stateId)),
				cb.equal(root.get("isActive"), ActiveDactiveStatus.ACTIVE.getId()));
		Query<Agency> query = session.createQuery(cr);
		return query.list();
	}
	
	@Override
	public List<MinorPortMaster> fetchAllMinnorPorts() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<MinorPortMaster> cr = cb.createQuery(MinorPortMaster.class);
		Root<MinorPortMaster> root = cr.from(MinorPortMaster.class);
		cr.select(root).where(cb.equal(root.get("isActive"), ActiveDactiveStatus.ACTIVE.getId()));
		Query<MinorPortMaster> query = session.createQuery(cr);
		return query.list();
	}

	@Override
	public List<MinorPortMaster> fetchMinnorPortsByIds(List<Integer> ids) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<MinorPortMaster> cr = cb.createQuery(MinorPortMaster.class);
		Root<MinorPortMaster> root = cr.from(MinorPortMaster.class);
		cr.select(root).where(cb.and(cb.equal(root.get("isActive"), ActiveDactiveStatus.ACTIVE.getId()),root.get("masterId").in(ids)));
		Query<MinorPortMaster> query = session.createQuery(cr);
		return query.list();
	}

	@Override
	public List<LocationMaster> fetchLocationMasterByStateIdOrLocationName(CommonMasterBean commonMasterBean) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<LocationMaster> cr = cb.createQuery(LocationMaster.class);
		Root<LocationMaster> root = cr.from(LocationMaster.class);
		List<Predicate> restrictions = new ArrayList<>();
		restrictions.add(cb.notEqual(root.get("locationId"), -1));
		if (commonMasterBean.getStateId() != 0) {
			restrictions.add(cb.equal(root.get("stateId"), commonMasterBean.getStateId()));
		}
		if (commonMasterBean.getMasterName() != null && !commonMasterBean.getMasterName().equalsIgnoreCase("0")) {
			restrictions.add(cb.like(root.get("locationName"), "%" + commonMasterBean.getMasterName() + "%"));
		}

		Predicate[] restriction = restrictions.toArray(new Predicate[restrictions.size()]);
		cr.select(root).where(cb.and(restriction));
		Query<LocationMaster> query = session.createQuery(cr);
		List<LocationMaster> locationMaster = query.list();

		return locationMaster;
	}

	@Override
	public boolean saveOrUpdateLocationMaster(LocationMaster locationMaster) {
		boolean success = false;
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(locationMaster);
			success = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return success;	
	}

	@Override
	public List<LocationMaster> fetchLocationMasterByName(String locationname) {
		try {
			Session session = sessionFactory.getCurrentSession();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<LocationMaster> cr = cb.createQuery(LocationMaster.class);
			Root<LocationMaster> root = cr.from(LocationMaster.class);
			cr.select(root).where(cb.and(cb.notEqual(root.get("locationId"), -1), cb.equal(root.get("locationName"), locationname),
					cb.equal(root.get("isActive"), ActiveDactiveStatus.ACTIVE.getId())));
			Query<LocationMaster> query = session.createQuery(cr);
			return query.list();
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
	}

	@Override
	public LocationMaster fetchLocationMasterById(int locationId) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<LocationMaster> cr = cb.createQuery(LocationMaster.class);
		Root<LocationMaster> root = cr.from(LocationMaster.class);
		cr.select(root).where(cb.and(cb.notEqual(root.get("locationId"), -1), cb.equal(root.get("locationId"), locationId)));
		Query<LocationMaster> query = session.createQuery(cr);
		return query.getSingleResult();
	}

	@Override
	public List<LocationMaster> fetchAllLocations() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<LocationMaster> cr = cb.createQuery(LocationMaster.class);
		Root<LocationMaster> root = cr.from(LocationMaster.class);
		cr.select(root);
		Query<LocationMaster> query = session.createQuery(cr);
		return query.getResultList();
	}

	@Override
	public List<LocationMaster> fetchLocationByState(int stateId) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<LocationMaster> cr = cb.createQuery(LocationMaster.class);
		Root<LocationMaster> root = cr.from(LocationMaster.class);
		cr.select(root).where(cb.and(cb.notEqual(root.get("locationId"), -1), cb.equal(root.get("stateId"), stateId),
				cb.equal(root.get("isActive"), ActiveDactiveStatus.ACTIVE.getId())));
		Query<LocationMaster> query = session.createQuery(cr);
		return query.getResultList();
	}

	@Override
	public List<OgatizationMaster> fetchOgatizationMasterByName(String organizationName) {
		try {
			Session session = sessionFactory.getCurrentSession();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<OgatizationMaster> cr = cb.createQuery(OgatizationMaster.class);
			Root<OgatizationMaster> root = cr.from(OgatizationMaster.class);
			cr.select(root).where(cb.and(cb.notEqual(root.get("masterId"), -1), cb.equal(root.get("masterName"), organizationName),
					cb.equal(root.get("isActive"), ActiveDactiveStatus.ACTIVE.getId())));
			Query<OgatizationMaster> query = session.createQuery(cr);
			return query.list();
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
	}

	@Override
	public List<OgatizationMaster> fetchOrganizationByTypeCode(String masterType) {
		try{
			Session session = sessionFactory.getCurrentSession();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<OgatizationMaster> cr = cb.createQuery(OgatizationMaster.class);
			Root<OgatizationMaster> root = cr.from(OgatizationMaster.class);
			cr.select(root).where(cb.and(cb.notEqual(root.get("masterId"), -1), cb.equal(root.get("masterCode"), masterType),
					cb.equal(root.get("isActive"), ActiveDactiveStatus.ACTIVE.getId())));
			Query<OgatizationMaster> query = session.createQuery(cr);
			return query.list();
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
	}

	@Override
	public int fetchMaxOrganization() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Integer> criteriaQuery2 = builder.createQuery(Integer.class);
		Root<OgatizationMaster> root2 = criteriaQuery2.from(OgatizationMaster.class);
		criteriaQuery2.select(builder.max(root2.get("masterUniquecode")));
		Query<Integer> query2 = session.createQuery(criteriaQuery2);
		int maxCode = query2.getSingleResult();
		System.out.println("Max maxCode = " + maxCode);

		return maxCode;
	}

	@Override
	public boolean saveOrUpdateOrganizationMasters(OgatizationMaster ogatizationMaster) {
		boolean success = false;
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(ogatizationMaster);
			success = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return success;
	}

	@Override
	public OgatizationMaster fetchOrganizationById(int masterId) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<OgatizationMaster> cr = cb.createQuery(OgatizationMaster.class);
		Root<OgatizationMaster> root = cr.from(OgatizationMaster.class);
		cr.select(root).where(cb.and(cb.notEqual(root.get("masterId"), -1), cb.equal(root.get("masterId"), masterId)));
		Query<OgatizationMaster> query = session.createQuery(cr);
		return query.getSingleResult();
	}
	
	/**
	 * @developer: Abhishek Tyagi
	 * @createdOn: 19/03/2019
	 * @purpose: for fetching common masters list of organisation from database.
	 * @return: List
	 */
	public List<OgatizationMaster> fetchOrganisationMaster(CommonMasterBean commonMasterBean) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<OgatizationMaster> cr = cb.createQuery(OgatizationMaster.class);
		Root<OgatizationMaster> root = cr.from(OgatizationMaster.class);
		List<Predicate> restrictions = new ArrayList<>();
		restrictions.add(cb.notEqual(root.get("masterId"), -1));
		if (commonMasterBean.getMasterName() != null && !commonMasterBean.getMasterName().equalsIgnoreCase("0")) {
			restrictions.add(cb.like(root.get("masterName"), "%" + commonMasterBean.getMasterName() + "%"));
		}
		Predicate[] restriction = restrictions.toArray(new Predicate[restrictions.size()]);
		cr.select(root).where(cb.and(restriction));
		Query<OgatizationMaster> query = session.createQuery(cr);
		List<OgatizationMaster> ogatizationMaster = query.list();
		return ogatizationMaster;
	}
    
	/**
	 * @developer: Md Rashid Alam
	 * @createdOn: 22/03/2019
	 * @purpose: for fetching common masters list of fetchDirectorateOfPortsByName from database.
	 * @return: List
	 */
	@Override
	public List<DirectorateOfPortsMaster> fetchDirectorateOfPortsByName(String directorateOfPortName) {
		try {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<DirectorateOfPortsMaster> cr = cb.createQuery(DirectorateOfPortsMaster.class);
		Root<DirectorateOfPortsMaster> root = cr.from(DirectorateOfPortsMaster.class);
		cr.select(root).where(cb.and(cb.notEqual(root.get("masterId"), -1), cb.equal(root.get("masterName"),directorateOfPortName ),
				cb.equal(root.get("isActive"), ActiveDactiveStatus.ACTIVE.getId())));
		Query<DirectorateOfPortsMaster> query = session.createQuery(cr);
		return query.list();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @developer: Md Rashid Alam
	 * @createdOn: 22/03/2019
	 * @purpose: for fetching common masters list of fetchDirectorateOfPortsByTypeCode from database.
	 * @return: List
	 */
	@Override
	public List<DirectorateOfPortsMaster> fetchDirectorateOfPortsByTypeCode(String directorateOfPortCode) {
		try{
			Session session = sessionFactory.getCurrentSession();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<DirectorateOfPortsMaster> cr = cb.createQuery(DirectorateOfPortsMaster.class);
			Root<DirectorateOfPortsMaster> root = cr.from(DirectorateOfPortsMaster.class);
			cr.select(root).where(cb.and(cb.notEqual(root.get("masterId"), -1), cb.equal(root.get("masterCode"), directorateOfPortCode),
					cb.equal(root.get("isActive"), ActiveDactiveStatus.ACTIVE.getId())));
			Query<DirectorateOfPortsMaster> query = session.createQuery(cr);
			return query.list();
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
	}
	
	/**
	 * @developer: Md Rashid Alam
	 * @createdOn: 22/03/2019
	 * @purpose: for fetching common masters max fetchMaxDirectorateOfPorts  from database.
	 * @return: int
	 */
	@Override
	public int fetchMaxDirectorateOfPorts() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Integer> criteriaQuery2 = builder.createQuery(Integer.class);
		Root<DirectorateOfPortsMaster> root2 = criteriaQuery2.from(DirectorateOfPortsMaster.class);
		criteriaQuery2.select(builder.max(root2.get("masterUniqueCode")));
		Query<Integer> query2 = session.createQuery(criteriaQuery2);
		int maxCode = query2.getSingleResult();
		System.out.println("Max maxCode = " + maxCode);
		return maxCode;
	}
	
	/**
	 * @developer: Md Rashid Alam
	 * @createdOn: 22/03/2019
	 * @purpose: saveOrUpdateDirectorateOfPorts DirectorateOfPortsMaster to database.
	 * @return: boolean
	 */

	@Override
	public boolean saveOrUpdateDirectorateOfPorts(DirectorateOfPortsMaster directorateOfPortsMaster) {
		boolean success = false;
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(directorateOfPortsMaster);
			success = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return success;
	}
	/**
	 * @developer: Md Rashid Alam
	 * @createdOn: 22/03/2019
	 * @purpose: for fetching common masters of fetchDirectorateOfPortsByStateIdOrDirectorateOfPortsName from database.
	 * @return: int
	 */
	@Override
	public List<DirectorateOfPortsMaster> fetchDirectorateOfPortsByStateIdOrDirectorateOfPortsName(
			CommonMasterBean commonMasterBean) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<DirectorateOfPortsMaster> cr = cb.createQuery(DirectorateOfPortsMaster.class);
		Root<DirectorateOfPortsMaster> root = cr.from(DirectorateOfPortsMaster.class);
		List<Predicate> restrictions = new ArrayList<>();
		
		//restrictions.add(cb.equal(root.get("isActive"), ActiveDactiveStatus.ACTIVE.getId()));
		restrictions.add(cb.notEqual(root.get("masterId"), -1));

		if (commonMasterBean.getStateId() != 0) {
			restrictions.add(cb.equal(root.get("stateId"), commonMasterBean.getStateId()));
		}
		if (commonMasterBean.getMasterName() != null && !commonMasterBean.getMasterName().equalsIgnoreCase("0")) {
			restrictions.add(cb.like(root.get("masterName"), "%" + commonMasterBean.getMasterName() + "%"));
		}

		Predicate[] restriction = restrictions.toArray(new Predicate[restrictions.size()]);
		cr.select(root).where(cb.and(restriction));
		Query<DirectorateOfPortsMaster> query = session.createQuery(cr);
		List<DirectorateOfPortsMaster> directorateOfPortsMasters = query.list();

		return directorateOfPortsMasters;
	}

	@Override
	public DirectorateOfPortsMaster fetchDirectorateOfPortsById(int masterId) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<DirectorateOfPortsMaster> cr = cb.createQuery(DirectorateOfPortsMaster.class);
		Root<DirectorateOfPortsMaster> root = cr.from(DirectorateOfPortsMaster.class);
		cr.select(root).where(cb.and(cb.notEqual(root.get("masterId"), -1), cb.equal(root.get("masterId"), masterId)));
		Query<DirectorateOfPortsMaster> query = session.createQuery(cr);
		return query.getSingleResult();
	}
}
