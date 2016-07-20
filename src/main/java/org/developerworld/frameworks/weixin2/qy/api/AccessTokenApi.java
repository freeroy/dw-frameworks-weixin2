package org.developerworld.frameworks.weixin2.qy.api;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.developerworld.commons.lang.StringUtils;
import org.developerworld.frameworks.weixin2.commons.api.ApiResponse;
import org.developerworld.frameworks.weixin2.commons.api.ApiSupport;
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.AccessTokenRep;

/**
 * AccessToken 接口
 * 
 * @author Roy Huang
 *
 */
public class AccessTokenApi extends ApiSupport {

	/**
	 * 获取微信access token
	 * 
	 * @param corpID
	 * @param secret
	 * @return
	 */
	public static ApiResponse<AccessTokenRep> getAccessToken(String corpID, String secret) {
		if(StringUtils.isBlank(corpID) || StringUtils.isBlank(secret))
			throw new IllegalArgumentException("The args(corpID,secret) can not be blank!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("corpid", corpID));
		params.add(new BasicNameValuePair("corpsecret", secret));
		String query=URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?"+query;
		return doGetAndReturn(url, AccessTokenRep.class);
	}
}