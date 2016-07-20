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
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.ShakeInfoRep;

import com.fasterxml.jackson.databind.JsonNode;

public class ShakeAroundApi extends ApiSupport {

	/**
	 * 获取设备及用户信息
	 * @param accessToken
	 * @param ticket
	 * @return
	 */
	public static ApiResponse<ShakeInfoRep> getShakeInfo(String accessToken, String ticket) {
		if (StringUtils.isBlank(accessToken) || StringUtils.isBlank(ticket))
			throw new IllegalArgumentException("The accessToken、ticket can not be blank!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		String query = URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/shakearound/getshakeinfo?" + query;
		Map<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put("ticket", ticket);
		String jsonStr = object2Json(jsonMap);
		String response = doPost(url, jsonStr);
		ApiResponse<ShakeInfoRep> rst = new ApiResponse<ShakeInfoRep>();
		rst.setResponseString(response);
		if (ApiResponse.isErrorResponseString(response))
			rst.setResponseException(ApiResponse.buildResponseException(response));
		else {
			try {
				JsonNode root = OBJECT_MAPPER.readTree(response);
				JsonNode data = root.get("data");
				if (data != null)
					rst.setResponseObject(json2Object(data.toString(), ShakeInfoRep.class));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return rst;
	}
}
