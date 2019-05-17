package org.sagarmala.dao;

import java.util.List;

import org.sagarmala.model.Projects;

public interface ProjectDao {

	Projects fetchProject(int projectId);

	List<Projects> fetchProject();
}
