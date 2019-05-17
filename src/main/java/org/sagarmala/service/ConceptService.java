package org.sagarmala.service;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import org.sagarmala.bean.AddConceptBean;
import org.sagarmala.dto.ServiceResponse;

public interface ConceptService {
	public Integer saveConcept(AddConceptBean addConceptBean)
			throws IllegalAccessException, InvocationTargetException, ParseException;

	void updateConceptDetails(AddConceptBean addConceptBean);

	public ServiceResponse fetchConceptDetails() throws IllegalAccessException, InvocationTargetException;

	public ServiceResponse fetchConceptById(String conceptId);

	public ServiceResponse fetchNodalByOrganisationId(Integer organisationId);

	public void updateStatusAndRemarkById(AddConceptBean addConceptBean);

	//public ServiceResponse fetchConceptDetails(SearchConceptBean searchConceptBean);
}
