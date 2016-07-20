/**
 * 
 */
package org.developerworld.frameworks.weixin2.qy.api;

import org.developerworld.frameworks.weixin2.commons.api.ApiResponse;
import org.developerworld.frameworks.weixin2.qy.api.dto.enums.LoginTarget;
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.LoginInfoRep;
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.LoginUrlRep;
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.UserInfoRep;
import org.developerworld.frameworks.weixin2.qy.api.dto.req.UserReq;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Roy Huang
 * TODO 需要结合web环境获取数据后，再进行测试（当前测试只验证接口连通）
 */
@Ignore
public class LoginApiTest extends AbstractTestSupport {

	private String code = "code";
	private String authCode = "authCode";
	private String loginTicket = "loginTicket";
	private LoginTarget target = LoginTarget.CONTACT;

	@Test
	public void testGetUserInfo() {
		ApiResponse<UserInfoRep> response = LoginApi.getUserInfo(accessToken, code);
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
		Assert.assertTrue(true);
	}

	@Test
	public void testGetLoginInfo() {
		ApiResponse<LoginInfoRep> response = LoginApi.getLoginInfo(accessToken, authCode);
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
		Assert.assertTrue(true);
	}

	@Test
	public void testGetLoginUrlStringStringLoginTarget() {
		ApiResponse<LoginUrlRep> response = LoginApi.getLoginUrl(accessToken, loginTicket, target);
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
		Assert.assertTrue(true);
	}

	@Test
	public void testGetLoginUrlStringStringLoginTargetInteger() {
		ApiResponse<LoginUrlRep> response = LoginApi.getLoginUrl(accessToken, loginTicket, target, testAgentid);
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
		Assert.assertTrue(true);
	}

	@Test
	public void testAuthsuccUser() {
		UserReq u=buildUser();
		ApiResponse<Boolean> response = LoginApi.authsuccUser(accessToken, u.getUserid());
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
		Assert.assertTrue(true);
		UserApi.deleteUser(code,u.getUserid());
	}

}
