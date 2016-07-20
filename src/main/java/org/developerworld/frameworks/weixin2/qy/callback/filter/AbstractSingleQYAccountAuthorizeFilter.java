package org.developerworld.frameworks.weixin2.qy.callback.filter;

import java.io.IOException;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;

import org.developerworld.frameworks.weixin2.qy.api.AccessTokenApi;

/**
 * 单一企业号成员单点登录过滤器
 * 
 * @author Roy Huang
 *
 */
public abstract class AbstractSingleQYAccountAuthorizeFilter extends AbstractAuthorizeFilter {

	/* 企业号corpID */
	private String corpID;
	/* 企业号管理组secret */
	private String secret;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		super.init(filterConfig);
		setCorpID(filterConfig.getInitParameter("corpID"));
		setSecret(filterConfig.getInitParameter("secret"));
	}

	public void setCorpID(String corpID) {
		this.corpID = corpID;
	}

	private String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	@Override
	protected String getCorpID(ServletRequest request) throws IOException, ServletException {
		return corpID;
	}

	@Override
	protected String getAccessToken(ServletRequest request) throws IOException, ServletException {
		return AccessTokenApi.getAccessToken(getCorpID(request), getSecret()).getResponseObject().getAccessToken();
	}
}
