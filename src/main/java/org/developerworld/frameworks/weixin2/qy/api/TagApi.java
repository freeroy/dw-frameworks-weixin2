package org.developerworld.frameworks.weixin2.qy.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.developerworld.commons.lang.StringUtils;
import org.developerworld.frameworks.weixin2.commons.api.ApiResponse;
import org.developerworld.frameworks.weixin2.commons.api.ApiSupport;
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.TagMemberRep;
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.TagRep;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * 标签API
 * 
 * @author Roy Huang
 *
 */
public class TagApi extends ApiSupport {
	
	/**
	 * 创建标签
	 * @param accessToken
	 * @param tagname
	 * @return
	 */
	public static ApiResponse<Integer> createTag(String accessToken, String tagname) {
		return createTag(accessToken,null,tagname);
	}

	/**
	 * 创建标签
	 * 
	 * @param accessToken
	 * @param tagid
	 * @param tagname
	 * @return
	 */
	public static ApiResponse<Integer> createTag(String accessToken, Integer tagid, String tagname) {
		if (StringUtils.isBlank(accessToken) || StringUtils.isBlank(tagname))
			throw new IllegalArgumentException(
					"The accessToken、tagname can not be blank!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		String query=URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/tag/create?" + query;
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("tagname", tagname);
		if (tagid != null)
			jsonMap.put("tagid", tagid);
		String json = object2Json(jsonMap);
		String response = doPost(url, json);
		ApiResponse<Integer> rst = new ApiResponse<Integer>();
		rst.setResponseString(response);
		if (ApiResponse.isErrorResponseString(response))
			rst.setResponseException(ApiResponse.buildResponseException(response));
		else {
			try {
				JsonNode rootNode = OBJECT_MAPPER.readTree(response);
				JsonNode tagidNode = rootNode.get("tagid");
				if (tagidNode != null)
					rst.setResponseObject(tagidNode.asInt());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return rst;
	}

	/**
	 * 更新标签
	 * 
	 * @param accessToken
	 * @param tagid
	 * @param tagname
	 * @return
	 */
	public static ApiResponse<Boolean> updateTag(String accessToken, int tagid, String tagname) {
		if (StringUtils.isBlank(accessToken) || StringUtils.isBlank(tagname) || tagid<=0)
			throw new IllegalArgumentException(
					"The accessToken、tagname can not be blank and tagid must >0!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		String query=URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/tag/update?" + query;
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("tagname", tagname);
		jsonMap.put("tagid", tagid);
		String json = object2Json(jsonMap);
		return doPostAndReturnBoolean(url, json);
	}

	/**
	 * 删除标签
	 * 
	 * @param accessToken
	 * @param tagid
	 * @return
	 */
	public static ApiResponse<Boolean> deleteTag(String accessToken, int tagid) {
		if (StringUtils.isBlank(accessToken) || tagid<=0)
			throw new IllegalArgumentException(
					"The accessToken can not be blank and tagid must >0!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		params.add(new BasicNameValuePair("tagid", tagid+""));
		String query=URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/tag/delete?" + query;
		return doGetAndReturnBoolean(url);
	}

	/**
	 * 获取标签列表
	 * 
	 * @param accessToken
	 * @return
	 */
	public static ApiResponse<List<TagRep>> getTagList(String accessToken) {
		if (StringUtils.isBlank(accessToken) )
			throw new IllegalArgumentException(
					"The accessToken can not be blank!");
		ApiResponse<List<TagRep>> rst = null;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		String query=URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/tag/list?" + query;
		String response = doGet(url);
		rst = new ApiResponse<List<TagRep>>();
		rst.setResponseString(response);
		if (ApiResponse.isErrorResponseString(response))
			rst.setResponseException(ApiResponse.buildResponseException(response));
		else {
			try {
				JsonNode rootNode = OBJECT_MAPPER.readTree(response);
				JsonNode taglist = rootNode.path("taglist");
				if (taglist != null && taglist.isArray()) {
					List<TagRep> tags = new ArrayList<TagRep>();
					for (int i = 0; i < taglist.size(); i++) {
						tags.add(json2Object(taglist.get(i).toString(), TagRep.class));
					}
					rst.setResponseObject(tags);
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return rst;
	}

	/**
	 * 获取标签成员
	 * 
	 * @param accessToken
	 * @param tagid
	 * @return
	 */
	public static ApiResponse<TagMemberRep> getTagMember(String accessToken, int tagid) {
		if (StringUtils.isBlank(accessToken) || tagid<=0)
			throw new IllegalArgumentException(
					"The accessToken can not be blank and tagid must >0!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		params.add(new BasicNameValuePair("tagid", tagid+""));
		String query=URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/tag/get?" + query;
		return doGetAndReturn(url, TagMemberRep.class);
	}

	/**
	 * 增加标签成员
	 * 
	 * @param accessToken
	 * @param tagid
	 * @param departmentIds
	 * @return
	 */
	public static ApiResponse<Boolean> addDepartmentTagMember(String accessToken, int tagid,
			List<Integer> departmentIds) {
		return addTagMember(accessToken, tagid, null, departmentIds);
	}

	/**
	 * 增加标签成员
	 * 
	 * @param accessToken
	 * @param tagid
	 * @param userids
	 * @return
	 */
	public static ApiResponse<Boolean> addUserTagMember(String accessToken, int tagid, List<String> userids) {
		return addTagMember(accessToken, tagid, userids, null);
	}

	/**
	 * 增加标签成员
	 * 
	 * @param accessToken
	 * @param tagid
	 * @param userids
	 * @param departmentIds
	 * @return
	 */
	public static ApiResponse<Boolean> addTagMember(String accessToken, int tagid, List<String> userids,
			List<Integer> departmentIds) {
		if (StringUtils.isBlank(accessToken) || tagid<=0)
			throw new IllegalArgumentException(
					"The accessToken can not be blank and tagid must >0!");
		if ((userids==null || userids.size()<=0) && (departmentIds==null || departmentIds.size()<=0))
			throw new IllegalArgumentException(
					"The userids、departmentIds must set one!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		String query=URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/tag/addtagusers?" + query;
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("tagid", tagid);
		if (userids != null && userids.size() > 0)
			jsonMap.put("userlist", userids);
		if (departmentIds != null && departmentIds.size() > 0)
			jsonMap.put("partylist", departmentIds);
		String json = object2Json(jsonMap);
		return doPostAndReturnBoolean(url, json);
	}

	/**
	 * 
	 * @param accessToken
	 * @param tagid
	 * @param departmentIds
	 * @return
	 */
	public static ApiResponse<Boolean> deleteDepartmentTagMember(String accessToken, int tagid,
			List<Integer> departmentIds) {
		return deleteTagMember(accessToken, tagid, null, departmentIds);
	}

	/**
	 * 删除标签成员
	 * 
	 * @param accessToken
	 * @param tagid
	 * @param userids
	 * @return
	 */
	public static ApiResponse<Boolean> deleteUserTagMember(String accessToken, int tagid, List<String> userids) {
		return deleteTagMember(accessToken, tagid, userids, null);
	}

	/**
	 * 删除标签成员
	 * 
	 * @param accessToken
	 * @param tagid
	 * @param userids
	 * @param departmentIds
	 * @return
	 */
	public static ApiResponse<Boolean> deleteTagMember(String accessToken, int tagid, List<String> userids,
			List<Integer> departmentIds) {
		if (StringUtils.isBlank(accessToken) || tagid<=0)
			throw new IllegalArgumentException(
					"The accessToken can not be blank and tagid must >0!");
		if ((userids==null || userids.size()<=0) && (departmentIds==null || departmentIds.size()<=0))
			throw new IllegalArgumentException(
					"The userids、departmentIds must set one!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		String query=URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/tag/deltagusers?" + query;
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("tagid", tagid);
		if (userids != null && userids.size() > 0)
			jsonMap.put("userlist", userids);
		if (departmentIds != null && departmentIds.size() > 0)
			jsonMap.put("partylist", departmentIds);
		String json = object2Json(jsonMap);
		return doPostAndReturnBoolean(url, json);
	}
}
