package org.developerworld.frameworks.weixin2.qy.api;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.developerworld.commons.lang.MapBuilder;
import org.developerworld.commons.lang.StringUtils;
import org.developerworld.frameworks.weixin2.commons.api.ApiResponse;
import org.developerworld.frameworks.weixin2.commons.api.ApiSupport;
import org.developerworld.frameworks.weixin2.commons.api.exception.HttpCallException;
import org.developerworld.frameworks.weixin2.commons.api.exception.HttpCallStatusException;
import org.developerworld.frameworks.weixin2.qy.api.dto.enums.MediaType;
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.MediaFileRep;
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.UploadMediaRep;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * 素材api
 * 
 * @author Roy Huang
 *
 */
public class MediaApi extends ApiSupport {

	/**
	 * 上传临时素材文件
	 * 
	 * @param accessToken
	 * @param mediaType
	 * @param media
	 * @return
	 */
	public static ApiResponse<UploadMediaRep> uploadMedia(String accessToken, MediaType mediaType, File media) {
		if(StringUtils.isBlank(accessToken) || mediaType==null || media==null || !media.isFile())
			throw new IllegalArgumentException("The accessToken can not be blank and mediaType、media can not be null!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		params.add(new BasicNameValuePair("type", mediaType.value()));
		String query=URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/media/upload?" + query;
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("media", media);
		return doPostAndReturn(url, jsonMap, UploadMediaRep.class);
	}

	/**
	 * 获取临时素材下载链接
	 * 
	 * @param accessToken
	 * @param mediaId
	 * @return
	 */
	public static String getMediaUrl(String accessToken, String mediaId) {
		if(StringUtils.isBlank(accessToken) || StringUtils.isBlank(mediaId))
			throw new IllegalArgumentException("The accessToken、mediaId can not be blank!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		params.add(new BasicNameValuePair("media_id", mediaId));
		String query=URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/media/get?" + query;
		return url;
	}

	/**
	 * 获取临时素材
	 * 
	 * @param accessToken
	 * @param mediaId
	 * @return
	 */
	public static ApiResponse<MediaFileRep> getMedia(String accessToken, String mediaId) {
		if(StringUtils.isBlank(accessToken) || StringUtils.isBlank(mediaId))
			throw new IllegalArgumentException("The accessToken、mediaId can not be blank!");
		ApiResponse<MediaFileRep> rst = null;
		String url = getMediaUrl(accessToken, mediaId);
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = null;
		try {
			try {
				response = httpClient.execute(httpGet);
				int status = response.getStatusLine().getStatusCode();
				if (status < 200 || status >= 300)
					throw new HttpCallStatusException(response.getStatusLine().getStatusCode());
				rst = new ApiResponse<MediaFileRep>();
				// 判断返回响应类型
				Header contentType = response.getFirstHeader("Content-Type");
				// 返回错误信息
				if (contentType != null && StringUtils.isNotBlank(contentType.getValue())
						&& contentType.getValue().trim().toLowerCase().indexOf("application/json") != -1) {
					String responseTxt = EntityUtils.toString(response.getEntity());
					rst.setResponseString(responseTxt);
					rst.setResponseException(ApiResponse.buildResponseException(responseTxt));
				} else {
					MediaFileRep mf = new MediaFileRep();
					if (response.getFirstHeader("Content-disposition") != null) {
						mf.setContentDisposition(response.getFirstHeader("Content-disposition").getValue());
						if (StringUtils.isNotBlank(mf.getContentDisposition())) {
							String key = "attachment; filename=\"";
							int idx = mf.getContentDisposition().indexOf(key);
							if (idx != -1)
								mf.setFilename(mf.getContentDisposition().substring(idx + key.length(),
										mf.getContentDisposition().lastIndexOf("\"")));
						}
					}
					mf.setContentLength(Long.parseLong(response.getFirstHeader("Content-Length").getValue()));
					mf.setContentType(response.getFirstHeader("Content-Type").getValue());
					mf.setContentBody(EntityUtils.toByteArray(response.getEntity()));
					rst.setResponseObject(mf);
				}
			} catch (Exception e) {
				throw new HttpCallException(e);
			}
		} finally {
			httpGet.releaseConnection();
			try {
				if (response != null)
					response.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			try {
				httpClient.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return rst;
	}
	


	/**
	 * 上传图文消息内的图片
	 * 
	 * @param accessToken
	 * @param media
	 * @return
	 */
	public static ApiResponse<String> uploadImg(String accessToken, File media) {
		if(StringUtils.isBlank(accessToken) || media==null)
			throw new IllegalArgumentException("The accessToken can not be blank and media can not be null!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		String query=URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/media/uploadimg?" + query;
		Map<String, Object> jsonMap = new MapBuilder<String, Object>().put("media", media).map();
		String response = doPost(url, jsonMap);
		ApiResponse<String> rst = new ApiResponse<String>();
		rst.setResponseString(response);
		if (ApiResponse.isErrorResponseString(response))
			rst.setResponseException(ApiResponse.buildResponseException(response));
		else {
			try {
				JsonNode root = OBJECT_MAPPER.readTree(response);
				rst.setResponseObject(root.get("url").textValue());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return rst;
	}
}
