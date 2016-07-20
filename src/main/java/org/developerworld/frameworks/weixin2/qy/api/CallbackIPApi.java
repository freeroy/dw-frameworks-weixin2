package org.developerworld.frameworks.weixin2.qy.api;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.developerworld.commons.lang.StringUtils;
import org.developerworld.frameworks.weixin2.commons.api.ApiResponse;
import org.developerworld.frameworks.weixin2.commons.api.ApiSupport;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * 获取微信企业号服务器ip地址
 * 
 * @author Roy Huang
 *
 */
public class CallbackIPApi extends ApiSupport {

	/**
	 * 获取ip地址
	 * 
	 * @param accessToken
	 * @return
	 */
	public static ApiResponse<List<String>> getCallbackIP(String accessToken) {
		if(StringUtils.isBlank(accessToken))
			throw new IllegalArgumentException("The accessToken can not be blank!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		String query=URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/getcallbackip?"+query;
		String response = doGet(url);
		ApiResponse<List<String>> rst = new ApiResponse<List<String>>();
		rst.setResponseString(response);
		if (ApiResponse.isErrorResponseString(response))
			rst.setResponseException(ApiResponse.buildResponseException(response));
		else {
			try {
				List<String> ips = new ArrayList<String>();
				JsonNode rootNode = OBJECT_MAPPER.readTree(response);
				JsonNode iplist = rootNode.get("ip_list");
				if (iplist != null && iplist.isArray())
					ips = json2Object(iplist.toString(), List.class);
				rst.setResponseObject(ips);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return rst;
	}
}
