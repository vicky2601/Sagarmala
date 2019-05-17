package org.sagarmala.dao.impl;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.sagarmala.bean.AddConceptBean;
import org.sagarmala.dao.ConceptDAO;
import org.sagarmala.enumtype.constants.ActiveDactiveStatus;
import org.sagarmala.model.AddConcept;
import org.sagarmala.model.UserDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository(value = "conceptDAO")
@Transactional
public class ConceptDAOImpl implements ConceptDAO {
	@Autowired
	private SessionFactory sessionFactory;
	private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);
	@Override
	public Integer saveConcept(AddConcept addConcept) {
		try {
		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(addConcept);
	}
		 catch (Exception e) {
			
				e.printStackTrace();
				return null;
			}
		return addConcept.getId(); 
		
		
	}
	@Override
	public void updateConceptDetails(AddConceptBean addConceptBean) {
		try
		{
			
			
			Session session = sessionFactory.getCurrentSession();
			AddConcept allConceptData = session.get(AddConcept.class, addConceptBean.getId());
			//allConceptData.setStartDate(timestamp);
			//allConceptData.setEndDate(timestamp);
			allConceptData.setAimsAndObjectives(addConceptBean.getAimsAndObjectives());
			allConceptData.setConceptId(addConceptBean.getConceptId());
			allConceptData.setDocument(addConceptBean.getDocument());
			allConceptData.setStartDate(addConceptBean.getStartDate());
			allConceptData.setEndDate(addConceptBean.getEndDate());
			allConceptData.setEstimatedCost(addConceptBean.getEstimatedCost());
			allConceptData.setId(addConceptBean.getId());
			allConceptData.setPortName(addConceptBean.getPortName());
			allConceptData.setPortType(addConceptBean.getPortType());
			allConceptData.setProjectBrief(addConceptBean.getProjectBrief());
			allConceptData.setProjectLocation(addConceptBean.getProjectLocation());
			allConceptData.setProjectName(addConceptBean.getProjectName());
			allConceptData.setProjectProponent(addConceptBean.getProjectProponent());
			allConceptData.setProjectType(addConceptBean.getProjectType());
			allConceptData.setScopeOfWork(addConceptBean.getScopeOfWork());
			allConceptData.setStateId(addConceptBean.getStateId());
			allConceptData.setUpdatedBy(addConceptBean.getUpdatedBy());
			allConceptData.setUpdatedDate(addConceptBean.getUpdatedDate());
			allConceptData.setNodalOfficerDesignation(addConceptBean.getNodalOfficerDesignation());
			allConceptData.setNodalOfficerEmail(addConceptBean.getNodalOfficerEmail());
			allConceptData.setNodalOfficerLandline(addConceptBean.getNodalOfficerLandline());
			allConceptData.setNodalOfficerMobile(addConceptBean.getNodalOfficerMobile());
			allConceptData.setNodalOfficerName(addConceptBean.getNodalOfficerName());
			allConceptData.setNodalOfficerPostalAddress(addConceptBean.getNodalOfficerPostalAddress());
			allConceptData.setStatus(addConceptBean.getStatus());
			session.saveOrUpdate(allConceptData);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	@Override
	public List<AddConcept> fetchConceptDetails() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<AddConcept> cr = cb.createQuery(AddConcept.class);
		Root<AddConcept> root = cr.from(AddConcept.class);
		cr.select(root);
		Query<AddConcept> query = session.createQuery(cr);
		LOGGER.info("fetch concept :-"+query.list());
		return query.list();
	}
	/*@Override
	public List<AddConcept> fetchConceptDetails(SearchConceptBean searchConceptBean) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<AddConcept> cr = cb.createQuery(AddConcept.class);
		Root<AddConcept> root = cr.from(AddConcept.class);
		List<Predicate> restrictions = new ArrayList<>();
		restrictions.add(cb.notEqual(root.get("conceptId"), -1));
		
		if (searchConceptBean.getStateId() != null && !searchConceptBean.getStateId().equals("0")) {
			restrictions.add(cb.equal(root.get("stateId"), searchConceptBean.getStateId()));
		}
		if (searchConceptBean.getConceptId() != null && !searchConceptBean.getConceptId().equals("0")) {
			restrictions.add(cb.equal(root.get("conceptId"), searchConceptBean.getConceptId()));
		}
		if (searchConceptBean.getStatus() != null && !searchConceptBean.getStatus().equals("0")) {
			restrictions.add(cb.equal(root.get("status"), searchConceptBean.getConceptId()));
		}
		if (searchConceptBean.getProjectName() != null && !searchConceptBean.getProjectName().equalsIgnoreCase("0")) {
			restrictions.add(cb.like(root.get("projectName"), "%" + searchConceptBean.getProjectName() + "%"));
		}
		Predicate[] restriction = restrictions.toArray(new Predicate[restrictions.size()]);
		cr.select(root).where(cb.and(restriction));
		Query<AddConcept> query = session.createQuery(cr);
		List<AddConcept> addConcept = query.list();
		return addConcept;
	}*/
	
	@Override
	public AddConcept fetchConceptById(String conceptId) {
		Session session=sessionFactory.getCurrentSession();
		CriteriaBuilder cb=session.getCriteriaBuilder();
		CriteriaQuery<AddConcept> cr=cb.createQuery(AddConcept.class);
		Root<AddConcept> root=cr.from(AddConcept.class);
		cr.select(root).where(cb.equal(root.get("conceptId"), conceptId));
		Query<AddConcept> query = session.createQuery(cr);
		return query.getSingleResult();
		
	}
	
	@Override
	public List<UserDetail> fetchNodalByOrganisationId(Integer organisationId) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<UserDetail> cr = cb.createQuery(UserDetail.class);
		Root<UserDetail> root = cr.from(UserDetail.class);
		List<Predicate> restrictions = new ArrayList<>();		
		restrictions.add(cb.notEqual(root.get("organisationId"), -1));
		restrictions.add(cb.equal(root.get("organisationId"), organisationId));
		restrictions.add(cb.equal(root.get("role"),2));
		restrictions.add(cb.equal(root.get("isActive"), ActiveDactiveStatus.ACTIVE.getId()));
		Predicate[] restriction = restrictions.toArray(new Predicate[restrictions.size()]);
		cr.select(root).where(cb.and(restriction));
		Query<UserDetail> query = session.createQuery(cr);
		List<UserDetail> userDetail = query.list();

		return userDetail;
	}
	@Override
	public void updateStatusAndRemarkByConceptId(AddConceptBean addConceptBean) {
		try {
			Session session = sessionFactory.getCurrentSession();
			int id=addConceptBean.getId();
			System.out.println("Concept ID:- "+id);
			
			AddConcept addConcept=session.get( AddConcept.class,id);
			addConcept.setStatus(addConceptBean.getStatus());
			addConcept.setRemark(addConceptBean.getRemark());
			addConcept.setUpdatedBy(addConceptBean.getUpdatedBy());
			addConcept.setUpdatedDate(addConceptBean.getUpdatedDate());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
