package org.developerworld.frameworks.weixin2.qy.callback.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.developerworld.frameworks.weixin2.qy.api.AccessTokenApi;
import org.developerworld.frameworks.weixin2.qy.callback.dto.DecryptMessage;

/**
 * 单企业号消息回调servlet
 * 
 * @author Roy Huang
 *
 */
public abstract class AbstractSingleQYAccountCallbackServlet extends AbstractCallbackHandlerServlet {

	/*企业号corpID*/
	private String corpID;
	/*企业号管理组secret*/
	private String secret;
	/*企业号回调加密key*/
	private String encodingAESKey;
	/*企业号回调token*/
	private String token;

	public String getCorpID() {
		return corpID;
	}

	public void setCorpID(String corpID) {
		this.corpID = corpID;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getEncodingAESKey() {
		return encodingAESKey;
	}

	public void setEncodingAESKey(String encodingAESKey) {
		this.encodingAESKey = encodingAESKey;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		setCorpID(servletConfig.getInitParameter("corpID"));
		setSecret(servletConfig.getInitParameter("secret"));
		setEncodingAESKey(servletConfig.getInitParameter("encodingAESKey"));
		setToken(servletConfig.getInitParameter("token"));
	}

	@Override
	protected String getToken(HttpServletRequest request) {
		return getToken();
	}

	@Override
	protected String getEncodingAESKey(HttpServletRequest request, String token) {
		return getEncodingAESKey();
	}

	@Override
	protected boolean verifyCorpID(HttpServletRequest request, DecryptMessage decryptMessage, String corpID) {
		return this.corpID.equals(corpID);
	}

	@Override
	protected String getAccessToken(HttpServletRequest request, String token, DecryptMessage decryptMessage) {
		return AccessTokenApi.getAccessToken(corpID, secret).getResponseObject().getAccessToken();
	}

}
