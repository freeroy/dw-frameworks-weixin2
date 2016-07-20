package org.developerworld.frameworks.weixin2.commons.api;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.developerworld.frameworks.weixin2.commons.api.exception.HttpCallException;
import org.developerworld.frameworks.weixin2.commons.api.exception.HttpCallStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiSupport {

	protected final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	/*
	 * 全局请求参数格式
	 */
	 protected final static String REQUEST_CONTENT_CHARSET = "UTF-8";

	/**
	 * 执行get调用并返回数据
	 * 
	 * @param url
	 * @param responseObjectClass
	 * @return
	 */
	protected static <T> ApiResponse<T> doGetAndReturn(String url, Class<T> responseObjectClass) {
		String response = doGet(url);
		return ApiResponse.buildResponse(responseObjectClass, response);
	}

	/**
	 * 执行get调用并返回boolean响应
	 * 
	 * @param url
	 * @return
	 */
	protected static ApiResponse<Boolean> doGetAndReturnBoolean(String url) {
		String response = doGet(url);
		ApiResponse<Boolean> rst = new ApiResponse<Boolean>();
		rst.setResponseString(response);
		if (ApiResponse.isErrorResponseString(response))
			rst.setResponseException(ApiResponse.buildResponseException(response));
		else
			rst.setResponseObject(true);
		return rst;
	}

	/**
	 * 执行get请求
	 * 
	 * @param url
	 * @return
	 */
	protected static String doGet(String url) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpGet);
			int status = response.getStatusLine().getStatusCode();
			if (status < 200 || status >= 300)
				throw new HttpCallStatusException(response.getStatusLine().getStatusCode());
			HttpEntity entity = response.getEntity();
			if (entity != null)
				return EntityUtils.toString(entity);
			return null;
		} catch (Exception e) {
			throw new HttpCallException(e);
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
	}

	/**
	 * 执行post请求
	 * 
	 * @param url
	 * @param content
	 * @param responseObjectClass
	 * @return
	 */
	protected static <T> ApiResponse<T> doPostAndReturn(String url, String content, Class<T> responseObjectClass) {
		String response = doPost(url, content);
		return ApiResponse.buildResponse(responseObjectClass, response);
	}

	/**
	 * 执行post请求
	 * 
	 * @param url
	 * @param params
	 * @param responseObjectClass
	 * @return
	 */
	protected static <T> ApiResponse<T> doPostAndReturn(String url, Map<String, Object> params,
			Class<T> responseObjectClass) {
		String response = doPost(url, params);
		ApiResponse<T> rst = new ApiResponse<T>();
		rst.setResponseString(response);
		if (ApiResponse.isErrorResponseString(response))
			rst.setResponseException(ApiResponse.buildResponseException(response));
		else
			rst.setResponseObject(json2Object(response, responseObjectClass));
		return rst;
	}

	/**
	 * 执行post请求
	 * 
	 * @param url
	 * @param content
	 * @return
	 */
	protected static ApiResponse<Boolean> doPostAndReturnBoolean(String url, String content) {
		String response = doPost(url, content);
		ApiResponse<Boolean> rst = new ApiResponse<Boolean>();
		rst.setResponseString(response);
		if (ApiResponse.isErrorResponseString(response))
			rst.setResponseException(ApiResponse.buildResponseException(response));
		else
			rst.setResponseObject(true);
		return rst;
	}

	/**
	 * 执行post请求
	 * 
	 * @param url
	 * @param content
	 * @return
	 */
	protected static String doPost(String url, String content) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		CloseableHttpResponse response = null;
		try {
			httpPost.setEntity(new ByteArrayEntity(content.getBytes()));
			response = httpClient.execute(httpPost);
			int status = response.getStatusLine().getStatusCode();
			if (status < 200 || status >= 300)
				throw new HttpCallStatusException(response.getStatusLine().getStatusCode());
			HttpEntity entity = response.getEntity();
			if (entity != null)
				return EntityUtils.toString(entity);
			return null;
		} catch (Exception e) {
			throw new HttpCallException(e);
		} finally {
			httpPost.releaseConnection();
			try {
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
	}

	/**
	 * 执行post请求
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	protected static String doPost(String url, Map<String, Object> params) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		CloseableHttpResponse response = null;
		try {
			MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
			if (params != null) {
				Iterator<Entry<String, Object>> iterator = params.entrySet().iterator();
				while (iterator.hasNext()) {
					Entry<String, Object> entry = iterator.next();
					if (entry.getValue() instanceof File)
						entityBuilder.addBinaryBody(entry.getKey(), (File) entry.getValue());
					else
						entityBuilder.addTextBody(entry.getKey(), entry.getValue().toString());
				}
			}
			httpPost.setEntity(entityBuilder.build());
			response = httpClient.execute(httpPost);
			int status = response.getStatusLine().getStatusCode();
			if (status < 200 || status >= 300)
				throw new HttpCallStatusException(response.getStatusLine().getStatusCode());
			HttpEntity entity = response.getEntity();
			if (entity != null)
				return EntityUtils.toString(entity);
			return null;
		} catch (Exception e) {
			throw new HttpCallException(e);
		} finally {
			httpPost.releaseConnection();
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
	}

	/**
	 * 对象转json
	 * 
	 * @param object
	 * @return
	 */
	protected static String object2Json(Object object) {
		try {
			return OBJECT_MAPPER.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * json转对象
	 * 
	 * @param json
	 * @param objectClass
	 * @return
	 */
	protected static <T> T json2Object(String json, Class<T> objectClass) {
		try {
			return OBJECT_MAPPER.readValue(json, objectClass);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
