/**
 * 
 */
package org.developerworld.frameworks.weixin2.qy.api;

import java.util.List;

import org.developerworld.frameworks.weixin2.commons.api.ApiResponse;
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.DepartmentRep;
import org.developerworld.frameworks.weixin2.qy.api.dto.req.DepartmentReq;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * 部门接口测试
 * @author Roy Huang
 *
 */
@Ignore
public class DepartmentApiTest extends AbstractTestSupport {

	@Test
	public void testCreateDepartment() {
		//调用接口
		ApiResponse<Integer> response = DepartmentApi.createDepartment(accessToken, buildDepartmentReq());
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
		Assert.assertTrue(true);
		//删除部门
		DepartmentApi.deleteDepartment(accessToken, response.getResponseObject());
	}

	@Test
	public void testUpdateDepartment() {
		Integer departmentId=buildDepartment();
		DepartmentReq d=buildDepartmentReq();
		d.setId(departmentId);
		//调用接口
		ApiResponse<Boolean> response = DepartmentApi.updateDepartment(accessToken, d);
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
		Assert.assertTrue(true);
		DepartmentApi.deleteDepartment(accessToken, departmentId);
	}

	@Test
	public void testDeleteDepartment() {
		Integer departmentId=buildDepartment();
		ApiResponse<Boolean> response = DepartmentApi.deleteDepartment(accessToken, departmentId);
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
		Assert.assertTrue(true);
	}

	@Test
	public void testGetDepartmentListStringString() {
		Integer departmentId=buildDepartment();
		ApiResponse<List<DepartmentRep>> response = DepartmentApi.getDepartmentList(accessToken,departmentId);
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
		Assert.assertTrue(true);
		DepartmentApi.deleteDepartment(accessToken, departmentId);
	}

}
