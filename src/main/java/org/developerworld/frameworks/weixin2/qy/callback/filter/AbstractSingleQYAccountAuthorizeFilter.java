package org.developerworld.frameworks.weixin2.qy.callback.filter;

import java.io.IOException;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;

import org.developerworld.frameworks.weixin2.commons.api.ApiResponse;
import org.developerworld.frameworks.weixin2.qy.api.AccessTokenApi;
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.AccessTokenRep;

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
	private AccessTokenRep accessToken;
	private Long getAccessTokenDateTime;

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
		if (accessToken == null || getAccessTokenDateTime == null
				|| (System.currentTimeMillis() - getAccessTokenDateTime) > (accessToken.getExpiresIn() * 1000)) {
			// 获取accessToken采用同步方式
			synchronized (this) {
				if (accessToken == null || getAccessTokenDateTime == null || (System.currentTimeMillis()
						- getAccessTokenDateTime) > (accessToken.getExpiresIn() * 1000)) {
					accessToken = null;
					getAccessTokenDateTime = null;
					try {
						ApiResponse<AccessTokenRep> apiRes = AccessTokenApi.getAccessToken(getCorpID(request),
								getSecret());
						if (!apiRes.isError()) {
							accessToken = apiRes.getResponseObject();
							getAccessTokenDateTime = System.currentTimeMillis();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return accessToken == null ? null : accessToken.getAccessToken();
	}
}
