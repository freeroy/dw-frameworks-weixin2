package org.developerworld.frameworks.weixin2.qy.callback.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.developerworld.commons.lang.StringUtils;
import org.developerworld.commons.servlet.AbstractUrlFilter;
import org.developerworld.frameworks.weixin2.commons.api.ApiResponse;
import org.developerworld.frameworks.weixin2.qy.api.LoginApi;
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.UserInfoRep;

/**
 * 成员单点登录过滤器	
 * 
 * @author Roy Huang
 *
 */
public abstract class AbstractAuthorizeFilter extends AbstractUrlFilter {

	private String state = "authorize_state_" + System.currentTimeMillis();

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		super.init(filterConfig);
		if (filterConfig.getInitParameter("state") != null)
			setState(filterConfig.getInitParameter("state"));
	}

	public void doFilterWhenUrlPass(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 若已经登录，直接放行
		if (isLogined(request))
			chain.doFilter(request, response);
		else {
			// 没有登录信息
			String state = request.getParameter("state");
			String code = request.getParameter("code");
			// 有两种情况(通过请求参数判断)
			// 1、当前请求是用户直接访问，则需要调至微信单点登录，并回调回来
			// 2、当前请求时回调请求，则通过回调信息，获取用户数据，并完成后续
			if (getState().equals(state)) {
				// 存在state，并且正确，代表从微信回调回来
				if (StringUtils.isBlank(code))
					// code返回空，代表用户校验失败，需执行后续操作
					handleCodeNullErr(request, response, chain);
				else {
					// 获取accessToken
					String accessToken = getAccessToken(request);
					if (StringUtils.isBlank(accessToken))
						handleAccessTokenNullErr(request, response, chain);
					else {
						// 利用code信息，获取登录用户信息
						ApiResponse<UserInfoRep> apiResponse = LoginApi.getUserInfo(accessToken, code);
						if (apiResponse != null && !apiResponse.isError() && apiResponse.getResponseObject() != null)
							handleGetUserInfoSucc(request, response, chain, apiResponse.getResponseObject());
						else
							handleGetUserInfoErr(request, response, chain);
					}
				}
			} else {
				// 不存在state，即假定用户是直接访问，执行授权跳转
				// appid=CORPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect
				String appid = getCorpID(request);
				// 构造回调地址
				String redirectUri = getRedirectUri(request);
				String url = LoginApi.getAuthorizeUrl(appid, redirectUri, getState());
				// 重定向至单点登录
				((HttpServletResponse) response).sendRedirect(url);
			}
		}
	}

	/**
	 * 获取重定向地址(可按需重构)
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	protected String getRedirectUri(ServletRequest request) throws IOException, ServletException {
		HttpServletRequest _request = (HttpServletRequest) request;
		StringBuffer url = _request.getRequestURL();
		if (StringUtils.isNotBlank(_request.getQueryString()))
			url.append("?").append(_request.getQueryString());
		return url.toString();
	}

	/**
	 * 判断是否已经登录
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	protected abstract boolean isLogined(ServletRequest request) throws IOException, ServletException;

	/**
	 * 根据请求信息，获取token
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	protected abstract String getAccessToken(ServletRequest request) throws IOException, ServletException;

	/**
	 * 处理无code错误
	 * 
	 * @param request
	 * @param response
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 */
	protected abstract void handleCodeNullErr(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException;

	/**
	 * 处理无accessToken错误
	 * 
	 * @param request
	 * @param response
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 */
	protected abstract void handleAccessTokenNullErr(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException;

	/**
	 * 处理获取用户信息的情况
	 * 
	 * @param request
	 * @param response
	 * @param chain
	 * @param userInfo
	 * @throws IOException
	 * @throws ServletException
	 */
	protected abstract void handleGetUserInfoSucc(ServletRequest request, ServletResponse response, FilterChain chain,
			UserInfoRep userInfo) throws IOException, ServletException;

	/**
	 * 处理获取用户信息异常
	 * 
	 * @param request
	 * @param response
	 * @param chain
	 */
	protected abstract void handleGetUserInfoErr(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException;

	/**
	 * 根据请求获取corpID信息
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	protected abstract String getCorpID(ServletRequest request) throws IOException, ServletException;

}
