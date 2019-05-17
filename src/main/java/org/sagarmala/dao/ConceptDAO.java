//
package org.sagarmala.dao;

import java.util.List;

import org.sagarmala.bean.AddConceptBean;
import org.sagarmala.model.AddConcept;
import org.sagarmala.model.UserDetail;

public interface ConceptDAO {
	public Integer saveConcept(AddConcept addConcept);
	public List<AddConcept> fetchConceptDetails();
	public void updateConceptDetails(AddConceptBean addConceptBean);
	public AddConcept fetchConceptById(String conceptId);

	public List<UserDetail> fetchNodalByOrganisationId(Integer organisationId);
	public void updateStatusAndRemarkByConceptId(AddConceptBean addConceptBean);
}
