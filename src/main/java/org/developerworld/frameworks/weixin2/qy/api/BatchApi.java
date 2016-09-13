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
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.BatchResultRep;
import org.developerworld.frameworks.weixin2.qy.api.dto.req.BatchCallbackReq;

import com.fasterxml.jackson.databind.JsonNode;

public class BatchApi extends ApiSupport {

	/**
	 * 批量同步用户
	 * 
	 * @param accessToken
	 * @param mediaId
	 * @param syncUserCallbackReq
	 * @return
	 */
	public static ApiResponse<String> batchSyncUser(String accessToken, String mediaId,
			BatchCallbackReq batchCallbackReq) {
		if (StringUtils.isBlank(accessToken) || StringUtils.isBlank(mediaId) || batchCallbackReq == null)
			throw new IllegalArgumentException(
					"The accessToken、userid can not be blank and syncUserCallbackReq can not be null!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		String query = URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/batch/syncuser?" + query;
		return doBatch(mediaId, batchCallbackReq, url);
	}

	/**
	 * 全量覆盖成员
	 * 
	 * @param accessToken
	 * @param mediaId
	 * @param batchCallbackReq
	 * @return
	 */
	public static ApiResponse<String> batchReplaceUser(String accessToken, String mediaId,
			BatchCallbackReq batchCallbackReq) {
		if (StringUtils.isBlank(accessToken) || StringUtils.isBlank(mediaId) || batchCallbackReq == null)
			throw new IllegalArgumentException(
					"The accessToken、userid can not be blank and syncUserCallbackReq can not be null!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		String query = URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/batch/replaceuser?" + query;
		return doBatch(mediaId, batchCallbackReq, url);
	}

	/**
	 * 全量覆盖部门
	 * 
	 * @param accessToken
	 * @param mediaId
	 * @param batchCallbackReq
	 * @return
	 */
	public static ApiResponse<String> batchReplaceParty(String accessToken, String mediaId,
			BatchCallbackReq batchCallbackReq) {
		if (StringUtils.isBlank(accessToken) || StringUtils.isBlank(mediaId) || batchCallbackReq == null)
			throw new IllegalArgumentException(
					"The accessToken、userid can not be blank and syncUserCallbackReq can not be null!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		String query = URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/batch/replaceparty?" + query;
		return doBatch(mediaId, batchCallbackReq, url);
	}

	private static ApiResponse<String> doBatch(String mediaId, BatchCallbackReq batchCallbackReq, String url) {
		Map<String, Object> jsonM = new MapBuilder<String, Object>(new LinkedHashMap<String, Object>())
				.put("media_id", mediaId).put("callback", batchCallbackReq).map();
		String json = object2Json(jsonM);
		String response = doPost(url, json);
		ApiResponse<String> rst = new ApiResponse<String>();
		rst.setResponseString(response);
		if (ApiResponse.isErrorResponseString(response))
			rst.setResponseException(ApiResponse.buildResponseException(response));
		else {
			try {
				JsonNode rootNode = OBJECT_MAPPER.readTree(response);
				JsonNode jobid = rootNode.get("jobid");
				if (jobid != null)
					rst.setResponseObject(jobid.textValue());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return rst;
	}

	/**
	 * 获取批处理反馈
	 * 
	 * @param accessToken
	 * @param jobid
	 * @return
	 */
	public static ApiResponse<BatchResultRep> getBatchResult(String accessToken, String jobid) {
		if (StringUtils.isBlank(accessToken) || StringUtils.isBlank(jobid))
			throw new IllegalArgumentException("The accessToken、jobid can not be blank!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		params.add(new BasicNameValuePair("jobid", jobid));
		String query = URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/batch/getresult?" + query;
		return doGetAndReturn(url, BatchResultRep.class);
	}
}
