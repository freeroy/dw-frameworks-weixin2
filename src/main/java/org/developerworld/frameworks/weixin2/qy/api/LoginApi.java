package org.developerworld.frameworks.weixin2.qy.api;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.developerworld.commons.lang.StringUtils;
import org.developerworld.frameworks.weixin2.commons.api.ApiResponse;
import org.developerworld.frameworks.weixin2.commons.api.ApiSupport;
import org.developerworld.frameworks.weixin2.qy.api.dto.enums.LoginTarget;
import org.developerworld.frameworks.weixin2.qy.api.dto.enums.LoginUserType;
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.LoginInfoRep;
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.LoginUrlRep;
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.UserInfoRep;

/**
 * 登录相关api
 * 
 * @author Roy Huang
 *
 */
public class LoginApi extends ApiSupport {

	/**
	 * OAuth验证获取成员信息
	 * 
	 * @param accessToken
	 * @param code
	 * @return
	 */
	public static ApiResponse<UserInfoRep> getUserInfo(String accessToken, String code) {
		if (StringUtils.isBlank(accessToken) || StringUtils.isBlank(code))
			throw new IllegalArgumentException("The accessToken,code can not be blank!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		params.add(new BasicNameValuePair("code", code));
		String query = URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?" + query;
		return doGetAndReturn(url, UserInfoRep.class);
	}

	/**
	 * 获取成员登录授权信息
	 * 
	 * @param accessToken
	 * @param authCode
	 * @return
	 */
	public static ApiResponse<LoginInfoRep> getLoginInfo(String accessToken, String authCode) {
		if (StringUtils.isBlank(accessToken) || StringUtils.isBlank(authCode))
			throw new IllegalArgumentException("The accessToken,authCode can not be blank!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		String query = URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/service/get_login_info?" + query;
		Map<String, Object> jsonMap = new LinkedHashMap<String, Object>();
		jsonMap.put("auth_code", authCode);
		String json = object2Json(jsonMap);
		return doPostAndReturn(url, json, LoginInfoRep.class);
	}

	/**
	 * 获取登录企业号官网url
	 * 
	 * @param accessToken
	 * @param loginTicket
	 * @param target
	 * @return
	 */
	public static ApiResponse<LoginUrlRep> getLoginUrl(String accessToken, String loginTicket, LoginTarget target) {
		return getLoginUrl(accessToken, loginTicket, target, null);
	}

	/**
	 * 获取登录企业号官网url
	 * 
	 * @param loginTicket
	 * @param target
	 * @param agentid
	 * @return
	 * 
	 */
	public static ApiResponse<LoginUrlRep> getLoginUrl(String accessToken, String loginTicket, LoginTarget target,
			Integer agentid) {
		if (StringUtils.isBlank(accessToken) || StringUtils.isBlank(loginTicket) || target == null)
			throw new IllegalArgumentException(
					"The accessToken,loginTicket can not be blank and target can not be null!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		String query = URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/service/get_login_url?" + query;
		Map<String, Object> jsonMap = new LinkedHashMap<String, Object>();
		jsonMap.put("login_ticket", loginTicket);
		jsonMap.put("target", target.value());
		if (agentid != null)
			jsonMap.put("agentid", agentid);
		String json = object2Json(jsonMap);
		return doPostAndReturn(url, json, LoginUrlRep.class);
	}

	/**
	 * 通过二次验证
	 * 
	 * @param accessToken
	 * @param userid
	 * @return
	 */
	public static ApiResponse<Boolean> authsuccUser(String accessToken, String userid) {
		if (StringUtils.isBlank(accessToken) || StringUtils.isBlank(userid))
			throw new IllegalArgumentException("The accessToken,userid can not be blank!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		params.add(new BasicNameValuePair("userid", userid));
		String query = URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/authsucc?" + query;
		return doGetAndReturnBoolean(url);
	}

	/**
	 * 获取成员单点登录连接
	 * @param corpID
	 * @param redirectUri
	 * @param state
	 * @return
	 */
	public static String getAuthorizeUrl(String corpID, String redirectUri, String state) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("appid", corpID));
		params.add(new BasicNameValuePair("redirect_uri", redirectUri));
		params.add(new BasicNameValuePair("response_type", "code"));
		params.add(new BasicNameValuePair("scope", "SCOPE"));
		params.add(new BasicNameValuePair("state", state));
		String query = URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" + query + "#wechat_redirect";
		return url;
	}
	
	/**
	 * 获取第三方授权登录链接
	 * @param corpID
	 * @param redirectUri
	 * @param state
	 * @param userType
	 * @return
	 */
	public static String getLoginpageUrl(String corpID,String redirectUri,String state,LoginUserType userType){
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("corp_id", corpID));
		params.add(new BasicNameValuePair("redirect_uri", redirectUri));
		params.add(new BasicNameValuePair("state", state));
		params.add(new BasicNameValuePair("usertype", userType.value()));
		String query = URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url="https://qy.weixin.qq.com/cgi-bin/loginpage?"+query;
		return url;
	}
}
