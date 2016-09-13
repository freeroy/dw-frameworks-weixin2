package org.developerworld.frameworks.weixin2.qy.api;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.developerworld.commons.lang.MapBuilder;
import org.developerworld.commons.lang.StringUtils;
import org.developerworld.frameworks.weixin2.commons.api.ApiResponse;
import org.developerworld.frameworks.weixin2.commons.api.ApiSupport;
import org.developerworld.frameworks.weixin2.qy.api.dto.enums.UserStatus;
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.UserRep;
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.UserSimpleRep;
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.UseridConvertOpenidRep;
import org.developerworld.frameworks.weixin2.qy.api.dto.req.UserReq;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * 用户api
 * 
 * @author Roy Huang
 *
 */
public class UserApi extends ApiSupport {

	/**
	 * 获取openid信息
	 * 
	 * @param accessToken
	 * @param userid
	 * @return
	 */
	public static ApiResponse<UseridConvertOpenidRep> convertToOpenid(String accessToken, String userid) {
		return convertToOpenid(accessToken, userid, null);
	}

	/**
	 * 获取openid信息
	 * 
	 * @param accessToken
	 * @param userid
	 * @param agentid
	 * @return
	 */
	public static ApiResponse<UseridConvertOpenidRep> convertToOpenid(String accessToken, String userid,
			Integer agentid) {
		if (StringUtils.isBlank(accessToken) || StringUtils.isBlank(userid))
			throw new IllegalArgumentException("The accessToken、userid can not be blank!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		String query = URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/convert_to_openid?" + query;
		Map<String, Object> jsonParam = new LinkedHashMap<String, Object>();
		jsonParam.put("userid", userid);
		if (agentid != null)
			jsonParam.put("agentid", agentid);
		String json = object2Json(jsonParam);
		return doPostAndReturn(url, json, UseridConvertOpenidRep.class);
	}

	/**
	 * 获取userid信息
	 * 
	 * @param accessToken
	 * @param openid
	 * @return
	 */
	public static ApiResponse<String> convertToUserid(String accessToken, String openid) {
		if (StringUtils.isBlank(accessToken) || StringUtils.isBlank(openid))
			throw new IllegalArgumentException("The accessToken、userid can not be blank!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		String query = URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/convert_to_userid?" + query;
		Map<String, Object> jsonParams = new LinkedHashMap<String, Object>();
		jsonParams.put("openid", openid);
		String json = object2Json(jsonParams);
		String response = doPost(url, json);
		ApiResponse<String> rst = new ApiResponse<String>();
		rst.setResponseString(response);
		if (ApiResponse.isErrorResponseString(response))
			rst.setResponseException(ApiResponse.buildResponseException(response));
		else {
			try {
				JsonNode rootNode = OBJECT_MAPPER.readTree(response);
				JsonNode userid = rootNode.get("userid");
				if (userid != null)
					rst.setResponseObject(userid.textValue());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return rst;
	}

	/**
	 * 创建成员
	 * 
	 * @param accessToken
	 * @param user
	 * @return
	 */
	public static ApiResponse<Boolean> createUser(String accessToken, UserReq user) {
		if (StringUtils.isBlank(accessToken) || user == null)
			throw new IllegalArgumentException("The accessToken can not be blank and user can not be null!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		String query = URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/create?" + query;
		String json = object2Json(user);
		return doPostAndReturnBoolean(url, json);
	}

	/**
	 * 更新成员
	 * 
	 * @param accessToken
	 * @param user
	 * @return
	 */
	public static ApiResponse<Boolean> updateUser(String accessToken, UserReq user) {
		if (StringUtils.isBlank(accessToken) || user == null)
			throw new IllegalArgumentException("The accessToken can not be blank and user can not be null!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		String query = URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/update?" + query;
		String json = object2Json(user);
		return doPostAndReturnBoolean(url, json);
	}

	/**
	 * 删除成员
	 * 
	 * @param accessToken
	 * @param userid
	 * @return
	 */
	public static ApiResponse<Boolean> deleteUser(String accessToken, String userid) {
		if (StringUtils.isBlank(accessToken) || StringUtils.isBlank(userid))
			throw new IllegalArgumentException("The accessToken、userid can not be blank!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		params.add(new BasicNameValuePair("userid", userid));
		String query = URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/delete?" + query;
		return doGetAndReturnBoolean(url);
	}

	/**
	 * 批量删除成员
	 * 
	 * @param accessToken
	 * @param useridlist
	 * @return
	 */
	public static ApiResponse<Boolean> batchDeleteUser(String accessToken, List<String> useridlist) {
		if (StringUtils.isBlank(accessToken) || (useridlist == null || useridlist.size() <= 0))
			throw new IllegalArgumentException("The accessToken、useridlist can not be blank!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		String query = URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/batchdelete?" + query;
		String json = object2Json(new MapBuilder<String, List<String>>().put("useridlist", useridlist).map());
		return doPostAndReturnBoolean(url, json);
	}

	/**
	 * 获取成员信息
	 * 
	 * @param accessToken
	 * @param userid
	 * @return
	 */
	public static ApiResponse<UserRep> getUser(String accessToken, String userid) {
		if (StringUtils.isBlank(accessToken) || StringUtils.isBlank(userid))
			throw new IllegalArgumentException("The accessToken、userid can not be blank!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		params.add(new BasicNameValuePair("userid", userid));
		String query = URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/get?" + query;
		return doGetAndReturn(url, UserRep.class);
	}

	/**
	 * 获取部门成员
	 * 
	 * @param accessToken
	 * @param departmentId
	 * @param fetchChild
	 * @return
	 */
	public static ApiResponse<List<UserSimpleRep>> getUserSimpleList(String accessToken, int departmentId,
			boolean fetchChild) {
		return getUserSimpleList(accessToken, departmentId, fetchChild, null);
	}

	/**
	 * 获取部门成员
	 * 
	 * @param accessToken
	 * @param departmentId
	 * @param fetchChild
	 * @param userStatus
	 * @return
	 */
	public static ApiResponse<List<UserSimpleRep>> getUserSimpleList(String accessToken, int departmentId,
			boolean fetchChild, UserStatus[] userStatuses) {
		if (StringUtils.isBlank(accessToken) || departmentId <= 0)
			throw new IllegalArgumentException("The accessToken can not be blank and departmentId must > 0!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		params.add(new BasicNameValuePair("department_id", departmentId + ""));
		params.add(new BasicNameValuePair("fetch_child", (fetchChild ? "1" : "0")));
		if (userStatuses != null && userStatuses.length > 0) {
			for (UserStatus userStatus : userStatuses)
				params.add(new BasicNameValuePair("status", userStatus.value() + ""));
		}
		String query = URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?" + query;
		String response = doGet(url);
		ApiResponse<List<UserSimpleRep>> rst = new ApiResponse<List<UserSimpleRep>>();
		rst.setResponseString(response);
		if (ApiResponse.isErrorResponseString(response))
			rst.setResponseException(ApiResponse.buildResponseException(response));
		else {
			try {
				JsonNode rootNode = OBJECT_MAPPER.readTree(response);
				JsonNode userlist = rootNode.path("userlist");
				if (userlist != null && userlist.isArray()) {
					List<UserSimpleRep> userSimples = new ArrayList<UserSimpleRep>();
					for (int i = 0; i < userlist.size(); i++) {
						userSimples.add(json2Object(userlist.get(i).toString(), UserSimpleRep.class));
					}
					rst.setResponseObject(userSimples);
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return rst;
	}

	/**
	 * 获取部门成员详情
	 * 
	 * @param accessToken
	 * @param departmentId
	 * @param fetchChild
	 * @return
	 */
	public static ApiResponse<List<UserRep>> getUserList(String accessToken, int departmentId, boolean fetchChild) {
		return getUserList(accessToken, departmentId, fetchChild, null);
	}

	/**
	 * 获取部门成员详情
	 * 
	 * @param accessToken
	 * @param departmentId
	 * @param fetchChild
	 * @param userStatuses
	 * @return
	 */
	public static ApiResponse<List<UserRep>> getUserList(String accessToken, int departmentId, boolean fetchChild,
			UserStatus[] userStatuses) {
		if (StringUtils.isBlank(accessToken) || departmentId <= 0)
			throw new IllegalArgumentException("The accessToken can not be blank and departmentId must > 0!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		params.add(new BasicNameValuePair("department_id", departmentId + ""));
		params.add(new BasicNameValuePair("fetch_child", (fetchChild ? "1" : "0")));
		if (userStatuses != null && userStatuses.length > 0) {
			for (UserStatus userStatus : userStatuses)
				params.add(new BasicNameValuePair("status", userStatus.value() + ""));
		}
		String query = URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/list?" + query;
		String response = doGet(url);
		ApiResponse<List<UserRep>> rst = new ApiResponse<List<UserRep>>();
		rst.setResponseString(response);
		if (ApiResponse.isErrorResponseString(response))
			rst.setResponseException(ApiResponse.buildResponseException(response));
		else {
			try {
				JsonNode rootNode = OBJECT_MAPPER.readTree(response);
				JsonNode userlist = rootNode.path("userlist");
				if (userlist != null && userlist.isArray()) {
					List<UserRep> userSimples = new ArrayList<UserRep>();
					for (int i = 0; i < userlist.size(); i++) {
						userSimples.add(json2Object(userlist.get(i).toString(), UserRep.class));
					}
					rst.setResponseObject(userSimples);
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return rst;
	}
}
