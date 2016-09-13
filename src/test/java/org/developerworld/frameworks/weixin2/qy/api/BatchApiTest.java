package org.developerworld.frameworks.weixin2.qy.api;

import java.io.File;
import java.io.IOException;

import org.developerworld.frameworks.weixin2.commons.api.ApiResponse;
import org.developerworld.frameworks.weixin2.qy.api.dto.enums.MediaType;
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.BatchResultRep;
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.UploadMediaRep;
import org.developerworld.frameworks.weixin2.qy.api.dto.req.BatchCallbackReq;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class BatchApiTest extends AbstractTestSupport {
	
	private ApiResponse<UploadMediaRep> buildBatchPartySampleMedia() throws IOException {
		File mediaFile = new File(BatchApiTest.class.getResource("/media/batch_party_sample.csv").getFile());
		return MediaApi.uploadMedia(accessToken, MediaType.FILE, mediaFile);
	}
	
	private ApiResponse<UploadMediaRep> buildBatchUserSampleMedia() throws IOException {
		File mediaFile = new File(BatchApiTest.class.getResource("/media/batch_user_sample.csv").getFile());
		return MediaApi.uploadMedia(accessToken, MediaType.FILE, mediaFile);
	}

	private BatchCallbackReq buildBatchCallbackReq() {
		BatchCallbackReq rst=new BatchCallbackReq();
		rst.setEncodingaeskey("abc");
		rst.setToken("kkkk");
		rst.setUrl("http://www.163.com");
		return rst;
	}

	@Test
	public void testBatchSyncUser() throws IOException {
		ApiResponse<UploadMediaRep> response=buildBatchUserSampleMedia();
		ApiResponse<String> response2=BatchApi.batchSyncUser(accessToken, response.getResponseObject().getMediaId(), buildBatchCallbackReq());
		if (response2.isError())
			System.out.println("调用出错:" + response2.getResponseException());
		else
			System.out.println("调用成功:" + response2.getResponseObject());
		Assert.assertTrue(true);
	}

	@Test
	public void testBatchReplaceUser() throws IOException {
		ApiResponse<UploadMediaRep> response=buildBatchUserSampleMedia();
		ApiResponse<String> response2=BatchApi.batchReplaceUser(accessToken, response.getResponseObject().getMediaId(), buildBatchCallbackReq());
		if (response2.isError())
			System.out.println("调用出错:" + response2.getResponseException());
		else
			System.out.println("调用成功:" + response2.getResponseObject());
		Assert.assertTrue(true);
	}

	@Test
	public void testBatchReplaceParty() throws IOException {
		ApiResponse<UploadMediaRep> response=buildBatchPartySampleMedia();
		ApiResponse<String> response2=BatchApi.batchReplaceParty(accessToken, response.getResponseObject().getMediaId(), buildBatchCallbackReq());
		if (response2.isError())
			System.out.println("调用出错:" + response2.getResponseException());
		else
			System.out.println("调用成功:" + response2.getResponseObject());
		Assert.assertTrue(true);
	}

	@Test
	public void testGetBatchResult() throws IOException {
		ApiResponse<UploadMediaRep> response=buildBatchPartySampleMedia();
		ApiResponse<String> response2=BatchApi.batchReplaceParty(accessToken, response.getResponseObject().getMediaId(), buildBatchCallbackReq());
		if (response2.isError())
			System.out.println("调用出错:" + response2.getResponseException());
		else{
			System.out.println("调用成功:" + response2.getResponseObject());
			ApiResponse<BatchResultRep> response3=BatchApi.getBatchResult(accessToken,response2.getResponseObject());
			if (response3.isError())
				System.out.println("调用出错:" + response3.getResponseException());
			else
				System.out.println("调用成功:" + response3.getResponseObject());
		}
		Assert.assertTrue(true);
	}

}
