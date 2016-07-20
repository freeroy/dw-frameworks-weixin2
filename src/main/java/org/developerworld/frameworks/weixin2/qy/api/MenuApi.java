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
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.MenuButtonRep;
import org.developerworld.frameworks.weixin2.qy.api.dto.req.MenuButtonReq;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * 菜单API
 * 
 * @author Roy Huang
 *
 */
public class MenuApi extends ApiSupport {

	/**
	 * 创建菜单
	 * 
	 * @param accessToken
	 * @param agentid
	 * @param menu
	 * @return
	 */
	public static ApiResponse<Boolean> createMenu(String accessToken, int agentid, List<MenuButtonReq> menus) {
		if (StringUtils.isBlank(accessToken) || agentid <= 0 || menus == null)
			throw new IllegalArgumentException(
					"The accessToken can not be blank and agentid must > 0 and menus can not be null!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		params.add(new BasicNameValuePair("agentid", agentid+""));
		String query=URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/menu/create?" + query;
		Map<String, List<MenuButtonReq>> jsonMap = new HashMap<String, List<MenuButtonReq>>();
		jsonMap.put("button", menus);
		String jsonParam = object2Json(jsonMap);
		return doPostAndReturnBoolean(url, jsonParam);
	}

	/**
	 * 删除菜单
	 * 
	 * @param accessToken
	 * @param agentid
	 * @return
	 */
	public static ApiResponse<Boolean> deleteMenu(String accessToken, int agentid) {
		if (StringUtils.isBlank(accessToken) || agentid <= 0)
			throw new IllegalArgumentException("The accessToken can not be blank and agentid must > 0!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		params.add(new BasicNameValuePair("agentid", agentid+""));
		String query=URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/menu/delete?" + query;
		return doGetAndReturnBoolean(url);
	}

	/**
	 * 获取菜单信息
	 * 
	 * @param accessToken
	 * @param agentid
	 * @return
	 */
	public static ApiResponse<List<MenuButtonRep>> getMenu(String accessToken, int agentid) {
		if (StringUtils.isBlank(accessToken) || agentid <= 0)
			throw new IllegalArgumentException("The accessToken can not be blank and agentid must > 0!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		params.add(new BasicNameValuePair("agentid", agentid+""));
		String query=URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/menu/get?" + query;
		String response = doGet(url);
		ApiResponse<List<MenuButtonRep>> rst = new ApiResponse<List<MenuButtonRep>>();
		rst.setResponseString(response);
		if (ApiResponse.isErrorResponseString(response))
			rst.setResponseException(ApiResponse.buildResponseException(response));
		else {
			try {
				JsonNode root = OBJECT_MAPPER.readTree(response);
				JsonNode menuNode=root.get("menu");
				if(menuNode!=null){
					JsonNode buttonNode = menuNode.get("button");
					if (buttonNode != null && buttonNode.isArray()) {
						List<MenuButtonRep> menuButtons = new ArrayList<MenuButtonRep>();
						for (int i = 0; i < buttonNode.size(); i++) {
							String nodeStr = buttonNode.get(i).toString();
							menuButtons.add(json2Object(nodeStr, MenuButtonRep.class));
						}
						rst.setResponseObject(menuButtons);
					}
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return rst;
	}
}
