package org.sagarmala.dao;

import java.util.List;
import org.sagarmala.model.RoleMasterr;

public interface IRoleServiceDao{
	List<RoleMasterr> fetchAllRole();
}
