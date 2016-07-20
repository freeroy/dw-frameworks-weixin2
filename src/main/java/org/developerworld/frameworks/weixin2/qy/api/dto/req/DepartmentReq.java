package org.developerworld.frameworks.weixin2.qy.api.dto.req;

import org.developerworld.frameworks.weixin2.qy.api.dto.base.DepartmentBase;

/**
 * 部门参数对象
 * @author Roy Huang
 *
 */
public class DepartmentReq extends DepartmentBase{

	@Override
	public String toString() {
		return "DepartmentReq [id=" + id + ", name=" + name + ", parentid=" + parentid + ", order=" + order + "]";
	}


}
