package org.developerworld.frameworks.weixin2.qy.api.dto.rep;

import org.developerworld.frameworks.weixin2.qy.api.dto.base.DepartmentBase;

/**
 * 部门返回对象
 * @author Roy Huang
 *
 */
public class DepartmentRep extends DepartmentBase{

	@Override
	public String toString() {
		return "DepartmentRep [id=" + id + ", name=" + name + ", parentid=" + parentid + ", order=" + order + "]";
	}

	
}
