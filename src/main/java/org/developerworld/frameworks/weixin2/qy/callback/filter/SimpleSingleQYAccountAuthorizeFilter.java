package org.developerworld.frameworks.weixin2.qy.callback.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.developerworld.commons.servlet.http.CookieUtils;
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.UserInfoRep;

/**
 * 简单单一企业号成员单点登录过滤器
 * 
 * @author Roy Huang
 *
 */
public class SimpleSingleQYAccountAuthorizeFilter extends AbstractSingleQYAccountAuthorizeFilter {

	private String accessTokenNullErrRedirectUrl;
	private String codeNullErrRedirectUrl;
	private String getUserInfoErrUrl;
	private String userIdCookieName = "wxqy_userid";
	private String openIdCookieName = "wxqy_openid";
	private String deviceIdCookieName = "wxqy_deviceid";
	private String authorizeVerifyCodeCookieName = "wxqy_authorize_verify_code";
	private boolean encodeCookieValue = false;
	private String cookiePath = "/";
	private int cookieMaxAge = -1;
	private String cookieDomain;
	private boolean cookieSecure = false;

	public String getCookiePath() {
		return cookiePath;
	}

	public void setCookiePath(String cookiePath) {
		this.cookiePath = cookiePath;
	}

	public int getCookieMaxAge() {
		return cookieMaxAge;
	}

	public void setCookieMaxAge(int cookieMaxAge) {
		this.cookieMaxAge = cookieMaxAge;
	}

	public String getCookieDomain() {
		return cookieDomain;
	}

	public void setCookieDomain(String cookieDomain) {
		this.cookieDomain = cookieDomain;
	}

	public boolean isCookieSecure() {
		return cookieSecure;
	}

	public void setCookieSecure(boolean cookieSecure) {
		this.cookieSecure = cookieSecure;
	}

	public String getUserIdCookieName() {
		return userIdCookieName;
	}

	public void setUserIdCookieName(String userIdCookieName) {
		this.userIdCookieName = userIdCookieName;
	}

	public String getOpenIdCookieName() {
		return openIdCookieName;
	}

	public void setOpenIdCookieName(String openIdCookieName) {
		this.openIdCookieName = openIdCookieName;
	}

	public String getDeviceIdCookieName() {
		return deviceIdCookieName;
	}

	public void setDeviceIdCookieName(String deviceIdCookieName) {
		this.deviceIdCookieName = deviceIdCookieName;
	}

	public String getCodeNullErrRedirectUrl() {
		return codeNullErrRedirectUrl;
	}

	public void setCodeNullErrRedirectUrl(String codeNullErrRedirectUrl) {
		this.codeNullErrRedirectUrl = codeNullErrRedirectUrl;
	}

	public String getAccessTokenNullErrRedirectUrl() {
		return accessTokenNullErrRedirectUrl;
	}

	public void setAccessTokenNullErrRedirectUrl(String accessTokenNullErrRedirectUrl) {
		this.accessTokenNullErrRedirectUrl = accessTokenNullErrRedirectUrl;
	}

	public String getGetUserInfoErrUrl() {
		return getUserInfoErrUrl;
	}

	public void setGetUserInfoErrUrl(String getUserInfoErrUrl) {
		this.getUserInfoErrUrl = getUserInfoErrUrl;
	}

	public String getAuthorizeVerifyCodeCookieName() {
		return authorizeVerifyCodeCookieName;
	}

	public void setAuthorizeVerifyCodeCookieName(String authorizeVerifyCodeCookieName) {
		this.authorizeVerifyCodeCookieName = authorizeVerifyCodeCookieName;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		super.init(filterConfig);
		if (StringUtils.isNotBlank(filterConfig.getInitParameter("cookiePath")))
			setCookiePath(filterConfig.getInitParameter("cookiePath"));
		if (StringUtils.isNotBlank(filterConfig.getInitParameter("cookieDomain")))
			setCookieDomain(filterConfig.getInitParameter("cookieDomain"));
		if (StringUtils.isNotBlank(filterConfig.getInitParameter("cookieMaxAge")))
			setCookieMaxAge(Integer.parseInt(filterConfig.getInitParameter("cookieMaxAge")));
		if (StringUtils.isNotBlank(filterConfig.getInitParameter("cookieSecure")))
			setCookieSecure(Boolean.parseBoolean(filterConfig.getInitParameter("cookieSecure")));
		if (StringUtils.isNotBlank(filterConfig.getInitParameter("deviceIdCookieName")))
			setDeviceIdCookieName(filterConfig.getInitParameter("deviceIdCookieName"));
		if (StringUtils.isNotBlank(filterConfig.getInitParameter("openIdCookieName")))
			setOpenIdCookieName(filterConfig.getInitParameter("openIdCookieName"));
		if (StringUtils.isNotBlank(filterConfig.getInitParameter("userIdCookieName")))
			setUserIdCookieName(filterConfig.getInitParameter("userIdCookieName"));
		if (StringUtils.isNotBlank(filterConfig.getInitParameter("authorizeVerifyCodeCookieName")))
			setAuthorizeVerifyCodeCookieName(filterConfig.getInitParameter("authorizeVerifyCodeCookieName"));
		if (StringUtils.isNotBlank(filterConfig.getInitParameter("encodeCookieValue")))
			setEncodeCookieValue(Boolean.parseBoolean(filterConfig.getInitParameter("encodeCookieValue")));
		setCodeNullErrRedirectUrl(filterConfig.getInitParameter("codeNullErrRedirectUrl"));
		setAccessTokenNullErrRedirectUrl(filterConfig.getInitParameter("accessTokenNullErrRedirectUrl"));
		setGetUserInfoErrUrl(filterConfig.getInitParameter("getUserInfoErrUrl"));
	}

	@Override
	protected boolean isLogined(ServletRequest request) throws IOException, ServletException {
		HttpServletRequest _request = (HttpServletRequest) request;
		Cookie deviceIdCookie = CookieUtils.getCookie(_request, getDeviceIdCookieName());
		Cookie openIdCookie = CookieUtils.getCookie(_request, getOpenIdCookieName());
		Cookie userIdCookie = CookieUtils.getCookie(_request, getUserIdCookieName());
		Cookie authorizeVerifyCodeCookie = CookieUtils.getCookie(_request, getAuthorizeVerifyCodeCookieName());
		// 无校验码，返回false
		if (authorizeVerifyCodeCookie == null || StringUtils.isBlank(authorizeVerifyCodeCookie.getValue()))
			return false;
		else {
			UserInfoRep userInfo = new UserInfoRep();
			if (deviceIdCookie != null)
				userInfo.setDeviceId(getSelectCookieValue(deviceIdCookie.getValue()));
			if (openIdCookie != null)
				userInfo.setOpenId(getSelectCookieValue(openIdCookie.getValue()));
			if (userIdCookie != null)
				userInfo.setUserId(getSelectCookieValue(userIdCookie.getValue()));
			return buildAuthorizeVerifyCode(userInfo)
					.equals(getSelectCookieValue(authorizeVerifyCodeCookie.getValue()));
		}
	}

	@Override
	protected void handleCodeNullErr(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String url = getCodeNullErrRedirectUrl();
		if (StringUtils.isNotBlank(url))
			((HttpServletResponse) response).sendRedirect(url);
	}

	@Override
	protected void handleAccessTokenNullErr(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String url = getAccessTokenNullErrRedirectUrl();
		if (StringUtils.isNotBlank(url))
			((HttpServletResponse) response).sendRedirect(url);
	}

	/**
	 * 根据用户信息，后去授权校验码
	 * 
	 * @param userInfo
	 * @return
	 */
	protected String buildAuthorizeVerifyCode(UserInfoRep userInfo) {
		return "hashCode_" + userInfo.hashCode();
	}

	@Override
	protected void handleGetUserInfoSucc(ServletRequest request, ServletResponse response, FilterChain chain,
			UserInfoRep userInfo) throws IOException, ServletException {
		// 执行信息写cookie
		HttpServletResponse _response = (HttpServletResponse) response;
		if (StringUtils.isNotBlank(userInfo.getOpenId())) {
			Cookie openIdCookie = new Cookie(getOpenIdCookieName(), getInsertCookieValue(userInfo.getOpenId()));
			openIdCookie.setDomain(getCookieDomain());
			openIdCookie.setMaxAge(getCookieMaxAge());
			openIdCookie.setPath(getCookiePath());
			openIdCookie.setSecure(isCookieSecure());
			CookieUtils.addCookie(_response, openIdCookie);
		}
		if (StringUtils.isNotBlank(userInfo.getUserId())) {
			Cookie userIdCookie = new Cookie(getUserIdCookieName(), getInsertCookieValue(userInfo.getUserId()));
			userIdCookie.setDomain(getCookieDomain());
			userIdCookie.setMaxAge(getCookieMaxAge());
			userIdCookie.setPath(getCookiePath());
			userIdCookie.setSecure(isCookieSecure());
			CookieUtils.addCookie(_response, userIdCookie);
		}
		if (StringUtils.isNotBlank(userInfo.getDeviceId())) {
			Cookie deviceIdCookie = new Cookie(getDeviceIdCookieName(), getInsertCookieValue(userInfo.getDeviceId()));
			deviceIdCookie.setDomain(getCookieDomain());
			deviceIdCookie.setMaxAge(getCookieMaxAge());
			deviceIdCookie.setPath(getCookiePath());
			deviceIdCookie.setSecure(isCookieSecure());
			CookieUtils.addCookie(_response, deviceIdCookie);
		}
		// 增加一个cookie，用于校验cookie
		Cookie authorizeTokenCookie = new Cookie(getAuthorizeVerifyCodeCookieName(),
				getInsertCookieValue(buildAuthorizeVerifyCode(userInfo)));
		authorizeTokenCookie.setDomain(getCookieDomain());
		authorizeTokenCookie.setMaxAge(getCookieMaxAge());
		authorizeTokenCookie.setPath(getCookiePath());
		authorizeTokenCookie.setSecure(isCookieSecure());
		CookieUtils.addCookie(_response, authorizeTokenCookie);
		// 向下执行程序
		//chain.doFilter(request, response);
		//由于cookie需要刷新请求后才能被过滤连下级获取，所以这里“刷新”一次
		((HttpServletResponse)response).sendRedirect(getRedirectUri(request));
	}

	private String getSelectCookieValue(String cookieValue) throws UnsupportedEncodingException {
		if (StringUtils.isNotBlank(cookieValue) && isEncodeCookieValue())
			cookieValue = URLDecoder.decode(cookieValue, "utf-8");
		return cookieValue;
	}

	private String getInsertCookieValue(String cookieValue) throws UnsupportedEncodingException {
		if (StringUtils.isNotBlank(cookieValue) && isEncodeCookieValue())
			cookieValue = URLEncoder.encode(cookieValue, "utf-8");
		return cookieValue;
	}

	@Override
	protected void handleGetUserInfoErr(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String url = getGetUserInfoErrUrl();
		if (StringUtils.isNotBlank(url))
			((HttpServletResponse) response).sendRedirect(url);
	}

	public boolean isEncodeCookieValue() {
		return encodeCookieValue;
	}

	public void setEncodeCookieValue(boolean encodeCookieValue) {
		this.encodeCookieValue = encodeCookieValue;
	}
}
