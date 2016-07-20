package org.developerworld.frameworks.weixin2.qy.api;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.MaterialCountRep;
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.MaterialListRep;
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.MaterialRep;
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.MediaFileRep;
import org.developerworld.frameworks.weixin2.qy.api.dto.req.MpnewsArticleReq;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * 永久素材api
 * 
 * @author Roy Huang
 *
 */
public class MaterialApi extends ApiSupport {

	/**
	 * 上传永久图文素材
	 * 
	 * @param accessToken
	 * @param agentid
	 * @param mpnews
	 * @return
	 */
	public static ApiResponse<String> addMpnewsMaterial(String accessToken, int agentid,
			List<MpnewsArticleReq> mpnews) {
		if(StringUtils.isBlank(accessToken) || agentid<=0 || mpnews==null)
			throw new IllegalArgumentException("The accessToken can not be blank and agentid must >0 and mpnews can not be null!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		String query=URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/material/add_mpnews?" + query;
		Map<String, Object> jsonMap = new MapBuilder<String, Object>(new LinkedHashMap<String, Object>())
				.put("agentid", agentid)
				.put("mpnews", new MapBuilder<String, List<MpnewsArticleReq>>().put("articles", mpnews).map()).map();
		String json = object2Json(jsonMap);
		String response = doPost(url, json);
		ApiResponse<String> rst = new ApiResponse<String>();
		rst.setResponseString(response);
		if (ApiResponse.isErrorResponseString(response))
			rst.setResponseException(ApiResponse.buildResponseException(response));
		else {
			try {
				JsonNode rootNode = OBJECT_MAPPER.readTree(response);
				JsonNode mediaid = rootNode.path("media_id");
				rst.setResponseObject(mediaid.textValue());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return rst;
	}

	/**
	 * 上传其它类型永久素材
	 * 
	 * @param accessToken
	 * @param agentid
	 * @param type
	 * @param media
	 * @return
	 */
	public static ApiResponse<String> addMaterial(String accessToken, int agentid, MediaType type, File media) {
		if(StringUtils.isBlank(accessToken) || agentid<=0 || type==null || media==null || !media.isFile())
			throw new IllegalArgumentException("The accessToken can not be blank and agentid must >0 and type、media can not be null!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		params.add(new BasicNameValuePair("type", type.value()));
		params.add(new BasicNameValuePair("agentid", agentid+""));
		String query=URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/material/add_material?" + query;
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("media", media);
		String response = doPost(url, jsonMap);
		ApiResponse<String> rst = new ApiResponse<String>();
		rst.setResponseString(response);
		if (ApiResponse.isErrorResponseString(response))
			rst.setResponseException(ApiResponse.buildResponseException(response));
		else {
			try {
				JsonNode root = OBJECT_MAPPER.readTree(response);
				if (root.get("media_id") != null)
					rst.setResponseObject(root.get("media_id").textValue());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return rst;
	}

	/**
	 * 获取永久素材
	 * 
	 * @param accessToken
	 * @param agentid
	 * @param mediaId
	 * @return
	 */
	public static ApiResponse<MaterialRep> getMaterial(String accessToken, int agentid, String mediaId) {
		if(StringUtils.isBlank(accessToken) || agentid<=0 || StringUtils.isBlank(mediaId))
			throw new IllegalArgumentException("The accessToken、mediaId can not be blank and agentid must >0!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		params.add(new BasicNameValuePair("media_id",mediaId));
		params.add(new BasicNameValuePair("agentid", agentid+""));
		String query=URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/material/get?" + query;
		ApiResponse<MaterialRep> rst = null;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = null;
		try {
			try {
				response = httpClient.execute(httpGet);
				int status = response.getStatusLine().getStatusCode();
				if (status < 200 || status >= 300)
					throw new HttpCallStatusException(response.getStatusLine().getStatusCode());
				rst = new ApiResponse<MaterialRep>();
				// 判断返回响应类型
				Header contentType = response.getFirstHeader("Content-Type");
				// 返回错误信息
				if (contentType != null && StringUtils.isNotBlank(contentType.getValue())
						&& contentType.getValue().trim().toLowerCase().indexOf("application/json") != -1) {
					String responseTxt = EntityUtils.toString(response.getEntity());
					rst.setResponseString(responseTxt);
					if (ApiResponse.isErrorResponseString(responseTxt))
						rst.setResponseException(ApiResponse.buildResponseException(responseTxt));
					else
						rst.setResponseObject(json2Object(responseTxt, MaterialRep.class));
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
					mf.setContentType(contentType.getValue());
					mf.setContentBody(EntityUtils.toByteArray(response.getEntity()));
					MaterialRep materialRep = new MaterialRep();
					materialRep.setMediaFile(mf);
					if (contentType.getValue().indexOf("image/") != -1)
						materialRep.setType(MediaType.IMAGE);
					else if (contentType.getValue().indexOf("audio/amr") != -1)
						materialRep.setType(MediaType.VOICE);
					else if (contentType.getValue().indexOf("video/mpeg4") != -1)
						materialRep.setType(MediaType.VIDEO);
					else
						materialRep.setType(MediaType.FILE);
					rst.setResponseObject(materialRep);
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
	 * 删除永久素材
	 * 
	 * @param accessToken
	 * @param agentid
	 * @param mediaId
	 * @return
	 */
	public static ApiResponse<Boolean> deleteMaterial(String accessToken, int agentid, String mediaId) {
		if(StringUtils.isBlank(accessToken) || agentid<=0 || StringUtils.isBlank(mediaId))
			throw new IllegalArgumentException("The accessToken、mediaId can not be blank and agentid must >0!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		params.add(new BasicNameValuePair("media_id",mediaId));
		params.add(new BasicNameValuePair("agentid", agentid+""));
		String query=URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/material/del?" + query;
		return doGetAndReturnBoolean(url);
	}

	/**
	 * 修改永久图文素材
	 * 
	 * @param accessToken
	 * @param agentid
	 * @param mediaId
	 * @param mpnews
	 * @return
	 */
	public static ApiResponse<Boolean> updateMpnewsMaterial(String accessToken, int agentid, String mediaId,
			List<MpnewsArticleReq> mpnews) {
		if(StringUtils.isBlank(accessToken) || agentid<=0 || StringUtils.isBlank(mediaId) || mpnews==null)
			throw new IllegalArgumentException("The accessToken、mediaId can not be blank and mpnews can not be null and agentid must >0!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		String query=URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/material/update_mpnews?" + query;
		Map<String, Object> jsonMap = new MapBuilder<String, Object>(new LinkedHashMap<String, Object>())
				.put("agentid", agentid).put("media_id", mediaId)
				.put("mpnews", new MapBuilder<String, List<MpnewsArticleReq>>().put("articles", mpnews).map()).map();
		String json = object2Json(jsonMap);
		return doPostAndReturnBoolean(url, json);
	}

	/**
	 * 获取素材总数
	 * 
	 * @param accessToken
	 * @param agentid
	 * @return
	 */
	public static ApiResponse<MaterialCountRep> getMaterialCount(String accessToken, int agentid) {
		if(StringUtils.isBlank(accessToken) || agentid<=0)
			throw new IllegalArgumentException("The accessToken can not be blank and agentid must >0!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		params.add(new BasicNameValuePair("agentid", agentid+""));
		String query=URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/material/get_count?" + query;
		return doGetAndReturn(url, MaterialCountRep.class);
	}

	/**
	 * 获取素材列表
	 * 
	 * @param accessToken
	 * @param agentid
	 * @param type
	 * @param offset
	 * @param count
	 * @return
	 */
	public static ApiResponse<MaterialListRep> getMaterialList(String accessToken, int agentid, MediaType type,
			int offset, int count) {
		if(StringUtils.isBlank(accessToken) || agentid<=0)
			throw new IllegalArgumentException("The accessToken can not be blank and agentid must、count >0 and offset must　>=0 and type can not be null!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		String query=URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/material/batchget?" + query;
		Map<String, Object> jsonMap = new MapBuilder<String, Object>().put("type", type.value()).put("agentid", agentid)
				.put("offset", offset).put("count", count).map();
		return doPostAndReturn(url, object2Json(jsonMap), MaterialListRep.class);
	}

}
