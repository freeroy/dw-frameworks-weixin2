package org.developerworld.frameworks.weixin2.qy.api;

import java.util.Arrays;
import java.util.List;

import org.developerworld.frameworks.weixin2.commons.api.ApiResponse;
import org.developerworld.frameworks.weixin2.qy.api.dto.enums.UserGender;
import org.developerworld.frameworks.weixin2.qy.api.dto.enums.UserStatus;
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.UserRep;
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.UserSimpleRep;
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.UseridConvertOpenidRep;
import org.developerworld.frameworks.weixin2.qy.api.dto.req.UserReq;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class UserApiTest extends AbstractTestSupport {


	@Test
	public void testConvertToOpenidStringStringInteger() {
		ApiResponse<UseridConvertOpenidRep> response = UserApi.convertToOpenid(accessToken, testUserid, testAgentid);
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
		Assert.assertTrue(true);
	}

	@Test
	public void testConvertToUseridStringString() {
		ApiResponse<String> response = UserApi.convertToUserid(accessToken, testOpenid);
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
		Assert.assertTrue(true);
	}

	@Test
	public void testCreateUser() {
		UserReq u = buildUserReq();
		ApiResponse<Boolean> response = UserApi.createUser(accessToken, u);
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
		Assert.assertTrue(true);
		UserApi.deleteUser(accessToken, u.getUserid());
	}

	@Test
	public void testUpdateUser() {
		UserReq u = buildUser();
		u.setGender(UserGender.LADY);
		ApiResponse<Boolean> response = UserApi.updateUser(accessToken, u);
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
		Assert.assertTrue(true);
		UserApi.deleteUser(accessToken, u.getUserid());
	}

	@Test
	public void testDeleteUser() {
		UserReq u = buildUser();
		ApiResponse<Boolean> response = UserApi.deleteUser(accessToken, u.getUserid());
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
		Assert.assertTrue(true);
	}

	@Test
	public void testBathDeleteUser() {
		UserReq u = buildUser();
		ApiResponse<Boolean> response = UserApi.batchDeleteUser(accessToken,
				Arrays.asList(new String[] { u.getUserid() }));
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
		Assert.assertTrue(true);
	}

	@Test
	public void testGetUser() {
		UserReq u = buildUser();
		ApiResponse<UserRep> response = UserApi.getUser(accessToken, u.getUserid());
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
		Assert.assertTrue(true);
		UserApi.deleteUser(accessToken, u.getUserid());
	}

	@Test
	public void testGetUserSimpleListStringIntBooleanUserStatus() {
		ApiResponse<List<UserSimpleRep>> response = UserApi.getUserSimpleList(accessToken, testDepartmentId, true,
				new UserStatus[] { UserStatus.SUBSCRIPE, UserStatus.DISABLED, UserStatus.UNSUBSCRIPE });
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
		Assert.assertTrue(true);
	}

	@Test
	public void testGetUserListStringIntBooleanUserStatus() {
		ApiResponse<List<UserRep>> response = UserApi.getUserList(accessToken, testDepartmentId, true,
				new UserStatus[] { UserStatus.SUBSCRIPE, UserStatus.DISABLED, UserStatus.UNSUBSCRIPE });
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
		Assert.assertTrue(true);
	}

}
