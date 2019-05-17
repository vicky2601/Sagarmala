package org.sagarmala.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.sagarmala.dao.ProjectDao;
import org.sagarmala.model.Projects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class ProjectDaoImpl implements ProjectDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Projects fetchProject(int projectId) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Projects> cr = cb.createQuery(Projects.class);
		Root<Projects> root = cr.from(Projects.class);
		cr.select(root).where(cb.equal(root.get("projectId"), projectId));
		Query<Projects> query = session.createQuery(cr);
		return query.getSingleResult();
	}

	@Override
	public List<Projects> fetchProject() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Projects> cr = cb.createQuery(Projects.class);
		Root<Projects> root = cr.from(Projects.class);
		cr.select(root);
		Query<Projects> query = session.createQuery(cr);
		return query.getResultList();
	}

}
