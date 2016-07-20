package org.developerworld.frameworks.weixin2.qy.api;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.developerworld.commons.lang.StringUtils;
import org.developerworld.frameworks.weixin2.commons.api.ApiResponse;
import org.developerworld.frameworks.weixin2.commons.api.ApiSupport;
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.DepartmentRep;
import org.developerworld.frameworks.weixin2.qy.api.dto.req.DepartmentReq;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * 管理部门api
 * 
 * @author Roy Huang
 *
 */
public class DepartmentApi extends ApiSupport {

	/**
	 * 创建部门
	 * 
	 * @param accessToken
	 * @param department
	 * @return
	 */
	public static ApiResponse<Integer> createDepartment(String accessToken, DepartmentReq department) {
		if (StringUtils.isBlank(accessToken) || department == null)
			throw new IllegalArgumentException("The accessToken can not be blank and department can not be null!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		String query=URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/department/create?"+query;
		if (department.getParentid() == null)
			department.setParentid(1);
		String json = object2Json(department);
		String response = doPost(url, json);
		ApiResponse<Integer> rst = new ApiResponse<Integer>();
		rst.setResponseString(response);
		if (ApiResponse.isErrorResponseString(response))
			rst.setResponseException(ApiResponse.buildResponseException(response));
		else {
			try {
				JsonNode rootNode = OBJECT_MAPPER.readTree(response);
				if (rootNode.get("id") != null && rootNode.get("id").isValueNode())
					rst.setResponseObject(rootNode.get("id").asInt());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return rst;
	}

	/**
	 * 更新部门
	 * 
	 * @param accessToken
	 * @param department
	 * @return
	 */
	public static ApiResponse<Boolean> updateDepartment(String accessToken, DepartmentReq department) {
		if (StringUtils.isBlank(accessToken) || department == null)
			throw new IllegalArgumentException("The accessToken can not be blank and department can not be null!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		String query=URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/department/update?" + query;
		String json = object2Json(department);
		return doPostAndReturnBoolean(url, json);
	}

	/**
	 * 删除部门
	 * 
	 * @param accessToken
	 * @param id
	 * @return
	 */
	public static ApiResponse<Boolean> deleteDepartment(String accessToken, int id) {
		if (StringUtils.isBlank(accessToken) || id <= 0)
			throw new IllegalArgumentException("The accessToken can not be blank and id must > 0!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		params.add(new BasicNameValuePair("id", id+""));
		String query=URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/department/delete?" + query;
		return doGetAndReturnBoolean(url);
	}

	/**
	 * 获取根部门列表
	 * 
	 * @param accessToken
	 * @return
	 */
	public static ApiResponse<List<DepartmentRep>> getDepartmentList(String accessToken) {
		return getDepartmentList(accessToken, null);
	}

	/**
	 * 获取部门列表
	 * 
	 * @param accessToken
	 * @param id
	 * @return
	 */
	public static ApiResponse<List<DepartmentRep>> getDepartmentList(String accessToken, Integer id) {
		if (StringUtils.isBlank(accessToken) || (id != null && id <= 0))
			throw new IllegalArgumentException("The accessToken can not be blank and id must > 0!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		if(id!=null)
			params.add(new BasicNameValuePair("id", id.toString()));
		String query=URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/department/list?" + query;
		String response = doGet(url);
		ApiResponse<List<DepartmentRep>> rst = new ApiResponse<List<DepartmentRep>>();
		rst.setResponseString(response);
		if (ApiResponse.isErrorResponseString(response))
			rst.setResponseException(ApiResponse.buildResponseException(response));
		else {
			try {
				JsonNode rootNode = OBJECT_MAPPER.readTree(response);
				JsonNode departmentlist = rootNode.path("department");
				if (departmentlist != null && departmentlist.isArray()) {
					List<DepartmentRep> departments = new ArrayList<DepartmentRep>();
					for (int i = 0; i < departmentlist.size(); i++) {
						departmentlist.get(i);
						departments.add(json2Object(departmentlist.get(i).toString(), DepartmentRep.class));
					}
					rst.setResponseObject(departments);
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return rst;
	}
}
