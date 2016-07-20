package org.developerworld.frameworks.weixin2.qy.api;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.developerworld.frameworks.weixin2.commons.api.ApiResponse;
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.TagMemberRep;
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.TagRep;
import org.developerworld.frameworks.weixin2.qy.api.dto.req.UserReq;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class TagApiTest extends AbstractTestSupport {

	private String tagname = "测试标签";

	private Integer buildTag() {
		return TagApi.createTag(accessToken, null, tagname + RandomStringUtils.randomAlphanumeric(4))
				.getResponseObject();
	}

	@Test
	public void testCreateTag() {
		ApiResponse<Integer> response = TagApi.createTag(accessToken, null,
				tagname + RandomStringUtils.randomAlphanumeric(4));
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
		Assert.assertTrue(true);
		TagApi.deleteTag(accessToken, response.getResponseObject());
	}

	@Test
	public void testUpdateTag() {
		int tagid = buildTag();
		ApiResponse<Boolean> response = TagApi.updateTag(accessToken, tagid,
				tagname + RandomStringUtils.randomAlphanumeric(4));
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
		Assert.assertTrue(true);
		TagApi.deleteTag(accessToken, tagid);
	}

	@Test
	public void testDeleteTag() {
		int tagid = buildTag();
		ApiResponse<Boolean> response = TagApi.deleteTag(accessToken, tagid);
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
		Assert.assertTrue(true);
	}

	@Test
	public void testGetTagList() {
		ApiResponse<List<TagRep>> response = TagApi.getTagList(accessToken);
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
		Assert.assertTrue(true);
	}

	@Test
	public void testGetTagMember() {
		int tagid = buildTag();
		ApiResponse<TagMemberRep> response = TagApi.getTagMember(accessToken, tagid);
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
		Assert.assertTrue(true);
		TagApi.deleteTag(accessToken, tagid);
	}

	@Test
	public void testAddTagMember() {
		UserReq u = buildUser();
		int tagid = buildTag();
		ApiResponse<Boolean> response = TagApi.addTagMember(accessToken, tagid,
				Arrays.asList(new String[] { u.getUserid() }), Arrays.asList(new Integer[] { testDepartmentId }));
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
		Assert.assertTrue(true);
		UserApi.deleteUser(accessToken, u.getUserid());
		TagApi.deleteTag(accessToken, tagid);
	}

	@Test
	public void testDeleteTagMember() {
		UserReq u = buildUser();
		int tagid = buildTag();
		TagApi.addTagMember(accessToken, tagid, Arrays.asList(new String[] { u.getUserid() }),
				Arrays.asList(new Integer[] { testDepartmentId }));
		ApiResponse<Boolean> response = TagApi.deleteTagMember(accessToken, tagid,
				Arrays.asList(new String[] { u.getUserid() }), Arrays.asList(new Integer[] { testDepartmentId }));
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
		Assert.assertTrue(true);
		UserApi.deleteUser(accessToken, u.getUserid());
		TagApi.deleteTag(accessToken, tagid);
	}
}
