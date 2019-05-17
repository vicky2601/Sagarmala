package org.sagarmala.service;

import org.sagarmala.dto.ServiceResponse;

public interface IOrgRoleMappingService {
	public ServiceResponse findRoleByOrgRoleId(Integer id);
	public ServiceResponse findOrgByOrgIdId(Integer id);
}
