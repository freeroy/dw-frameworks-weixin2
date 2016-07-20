package org.developerworld.frameworks.weixin2.qy.api;

import java.io.File;
import java.util.List;

import org.developerworld.frameworks.weixin2.commons.api.ApiResponse;
import org.developerworld.frameworks.weixin2.qy.api.dto.enums.MediaType;
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.MaterialCountRep;
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.MaterialListRep;
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.MaterialRep;
import org.developerworld.frameworks.weixin2.qy.api.dto.req.MpnewsArticleReq;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class MaterialApiTest extends AbstractTestSupport{

	@Test
	public void testAddMpnewsMaterial() {
		ApiResponse<String> response=MaterialApi.addMpnewsMaterial(accessToken, testAgentid, buildMpnewsReq());
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
		MaterialApi.deleteMaterial(accessToken, testAgentid, response.getResponseObject());
	}

	@Test
	public void testAddMaterial() {
		File mediaFile = new File(MediaApiTest.class.getResource("/media/image.jpg").getFile());
		ApiResponse<String> response=MaterialApi.addMaterial(accessToken, testAgentid, MediaType.IMAGE, mediaFile);
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
		MaterialApi.deleteMaterial(accessToken, testAgentid, response.getResponseObject());
	}

	@Test
	public void testGetMaterial() {
		String mediaId=buildImageMediaId();
		ApiResponse<MaterialRep> response=MaterialApi.getMaterial(accessToken, testAgentid,mediaId);
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
		MaterialApi.deleteMaterial(accessToken, testAgentid, mediaId);

		mediaId=buildFileMediaId();
		response=MaterialApi.getMaterial(accessToken, testAgentid, mediaId);
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
		MaterialApi.deleteMaterial(accessToken, testAgentid, mediaId);

		mediaId=buildVoiceMediaId();
		response=MaterialApi.getMaterial(accessToken, testAgentid,mediaId);
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
		MaterialApi.deleteMaterial(accessToken, testAgentid, mediaId);

		mediaId=buildVideoMediaId();
		response=MaterialApi.getMaterial(accessToken, testAgentid, mediaId);
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
		MaterialApi.deleteMaterial(accessToken, testAgentid, mediaId);
		
		mediaId=buildMpnewsMediaId();
		response=MaterialApi.getMaterial(accessToken, testAgentid,mediaId);
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
		MaterialApi.deleteMaterial(accessToken, testAgentid, mediaId);
	}

	@Test
	public void testDeleteMaterial() {
		ApiResponse<Boolean> response=MaterialApi.deleteMaterial(accessToken, testAgentid, buildImageMediaId());
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
	}

	@Test
	public void testUpdateMpnewsMaterial() {
		List<MpnewsArticleReq> mpnews=buildMpnewsReq();
		ApiResponse<String> response=MaterialApi.addMpnewsMaterial(accessToken, testAgentid, mpnews);
		mpnews.get(0).setTitle("修改标题");
		MaterialApi.updateMpnewsMaterial(accessToken, testAgentid, response.getResponseObject(), mpnews);
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
		MaterialApi.deleteMaterial(accessToken, testAgentid, response.getResponseObject());
	}

	@Test
	public void testGetMaterialCount() {
		ApiResponse<MaterialCountRep> response=MaterialApi.getMaterialCount(accessToken, testAgentid);
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
	}

	@Test
	public void testGetMaterialList() {
		ApiResponse<MaterialListRep> response=MaterialApi.getMaterialList(accessToken, testAgentid, MediaType.IMAGE, 0, 999);
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
		response=MaterialApi.getMaterialList(accessToken, testAgentid, MediaType.MPNEWS, 0, 999);
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
	}

}
