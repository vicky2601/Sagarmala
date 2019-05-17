package org.sagarmala.service;

import org.sagarmala.dto.ServiceResponse;

public interface ProjectService {

	ServiceResponse fetchProject(int projectId);
}
