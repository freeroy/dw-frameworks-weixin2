/**
 * 
 */
package org.developerworld.frameworks.weixin2.qy.api;

import java.io.File;
import java.io.IOException;

import org.developerworld.frameworks.weixin2.commons.api.ApiResponse;
import org.developerworld.frameworks.weixin2.qy.api.MediaApi;
import org.developerworld.frameworks.weixin2.qy.api.dto.enums.MediaType;
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.MediaFileRep;
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.UploadMediaRep;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Roy
 *
 */
@Ignore
public class MediaApiTest extends AbstractTestSupport {

	private ApiResponse<UploadMediaRep> buildMedia() throws IOException {
		File mediaFile = new File(MediaApiTest.class.getResource("/media/image.jpg").getFile());
		return MediaApi.uploadMedia(accessToken, MediaType.IMAGE, mediaFile);
	}

	@Test
	public void testUploadMedia() throws IOException {
		ApiResponse<UploadMediaRep> response = buildMedia();
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
		Assert.assertTrue(true);
	}

	@Test
	public void testGetMediaUrl() throws IOException {
		ApiResponse<UploadMediaRep> response = buildMedia();
		String mediaUrl = MediaApi.getMediaUrl(accessToken, response.getResponseObject().getMediaId());
		System.out.println("素材地址：" + mediaUrl);
		Assert.assertTrue(true);
	}

	@Test
	public void testGetMedia() throws IOException {
		ApiResponse<UploadMediaRep> response = buildMedia();
		ApiResponse<MediaFileRep> response2 = MediaApi.getMedia(accessToken, response.getResponseObject().getMediaId());
		if (response2.isError())
			System.out.println("调用出错:" + response2.getResponseException());
		else
			System.out.println("调用成功:" + response2.getResponseObject());
		Assert.assertTrue(true);
	}
	
	@Test
	public void testUploadImg(){
		File mediaFile = new File(MediaApiTest.class.getResource("/media/image.jpg").getFile());
		ApiResponse<String> response=MediaApi.uploadImg(accessToken, mediaFile);
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
	}

}
