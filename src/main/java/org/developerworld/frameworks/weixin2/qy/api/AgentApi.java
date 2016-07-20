package org.developerworld.frameworks.weixin2.qy.api;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.developerworld.commons.lang.StringUtils;
import org.developerworld.frameworks.weixin2.commons.api.ApiResponse;
import org.developerworld.frameworks.weixin2.commons.api.ApiSupport;
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.AgentRep;
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.AgentSimpleRep;
import org.developerworld.frameworks.weixin2.qy.api.dto.req.AgentReq;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * 应用api
 * 
 * @author Roy Huang
 *
 */
public class AgentApi extends ApiSupport {

	/**
	 * 获取企业号应用
	 * 
	 * @param accessToken
	 * @param agentid
	 * @return
	 */
	public static ApiResponse<AgentRep> getAgent(String accessToken, int agentid) {
		if(StringUtils.isBlank(accessToken) || agentid<=0)
			throw new IllegalArgumentException("The accessToken can not be blank and agentid must true!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		params.add(new BasicNameValuePair("agentid", agentid+""));
		String query=URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/agent/get?"+query;
		return doGetAndReturn(url, AgentRep.class);
	}

	/**
	 * 设置企业号应用
	 * 
	 * @param accessToken
	 * @param agent
	 * @return
	 */
	public static ApiResponse<Boolean> setAgent(String accessToken, AgentReq agent) {
		if(StringUtils.isBlank(accessToken) || agent==null)
			throw new IllegalArgumentException("The accessToken can not be blank and agent can not be null!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		String query=URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/agent/set?"+query;
		String json=object2Json(agent);
		return doPostAndReturnBoolean(url, json);
	}

	/**
	 * 获取应用概况列表
	 * 
	 * @param accessToken
	 * @return
	 */
	public static ApiResponse<List<AgentSimpleRep>> getAgentList(String accessToken) {
		if(StringUtils.isBlank(accessToken))
			throw new IllegalArgumentException("The accessToken can not be blank!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		String query=URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/agent/list?"+query;
		String response = doGet(url);
		ApiResponse<List<AgentSimpleRep>> rst = new ApiResponse<List<AgentSimpleRep>>();
		rst.setResponseString(response);
		if (ApiResponse.isErrorResponseString(response))
			rst.setResponseException(ApiResponse.buildResponseException(response));
		else {
			try {
				JsonNode root = OBJECT_MAPPER.readTree(response);
				JsonNode agentlistNode = root.get("agentlist");
				if (agentlistNode != null && agentlistNode.isArray()) {
					List<AgentSimpleRep> agentSimples = new ArrayList<AgentSimpleRep>();
					for (int i = 0; i < agentlistNode.size(); i++) {
						String nodeStr = agentlistNode.get(i).toString();
						agentSimples.add(json2Object(nodeStr, AgentSimpleRep.class));
					}
					rst.setResponseObject(agentSimples);
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return rst;
	}
}
