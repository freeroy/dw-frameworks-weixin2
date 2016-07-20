package org.developerworld.frameworks.weixin2.commons.api;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 微信Api调用返回对象
 * 
 * @author Roy Huang
 *
 * @param <T>
 */
public class ApiResponse<T> {

	private final static ObjectMapper OM = new ObjectMapper();

	private T responseObject;
	private String responseString;
	private ApiException responseException;

	public T getResponseObject() {
		return responseObject;
	}

	public void setResponseObject(T responseObject) {
		this.responseObject = responseObject;
	}

	public String getResponseString() {
		return responseString;
	}

	public void setResponseString(String responseString) {
		this.responseString = responseString;
	}

	public ApiException getResponseException() {
		return responseException;
	}

	public void setResponseException(ApiException responseException) {
		this.responseException = responseException;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((responseException == null) ? 0 : responseException.hashCode());
		result = prime * result + ((responseObject == null) ? 0 : responseObject.hashCode());
		result = prime * result + ((responseString == null) ? 0 : responseString.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApiResponse other = (ApiResponse) obj;
		if (responseException == null) {
			if (other.responseException != null)
				return false;
		} else if (!responseException.equals(other.responseException))
			return false;
		if (responseObject == null) {
			if (other.responseObject != null)
				return false;
		} else if (!responseObject.equals(other.responseObject))
			return false;
		if (responseString == null) {
			if (other.responseString != null)
				return false;
		} else if (!responseString.equals(other.responseString))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ApiResponse [responseObject=" + responseObject + ", responseString=" + responseString
				+ ", responseException=" + responseException + "]";
	}

	/**
	 * 是否存在异常
	 * 
	 * @return
	 */
	public boolean isError() {
		return getResponseException() != null;
	}

	/**
	 * 判断响应信息是否异常报错
	 * 
	 * @param responseText
	 * @return
	 */
	public static boolean isErrorResponseString(String responseText) {
		return responseText.indexOf("\"errcode\":") != -1 && responseText.indexOf("\"errcode\":0,") == -1;
	}

	/**
	 * 从响应信息中获取异常对象
	 * 
	 * @param responseString
	 * @return
	 */
	public static ApiException buildResponseException(String responseString) {
		ApiException rst = null;
		if (isErrorResponseString(responseString))
			rst = buildResponseObject(ApiException.class, responseString);
		return rst;
	}

	/**
	 * 构建map响应
	 * 
	 * @param responseString
	 * @return
	 */
	public static Map buildResponseMap(String responseString) {
		return buildResponseObject(Map.class, responseString);
	}

	/**
	 * 从响应信息转化为对象
	 * 
	 * @param responseObjectClass
	 * @param responseString
	 * 
	 * @return
	 */
	public static <E> E buildResponseObject(Class<E> responseObjectClass, String responseString) {
		try {
			return OM.readValue(responseString, responseObjectClass);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 构建响应对象
	 * 
	 * @param responseObjectClass
	 * @param responseString
	 * @return
	 */
	public static <E> ApiResponse<E> buildResponse(Class<E> responseObjectClass, String responseString) {
		ApiResponse<E> rst = new ApiResponse<E>();
		rst.setResponseString(responseString);
		if (isErrorResponseString(responseString))
			rst.setResponseException(buildResponseException(responseString));
		else
			rst.setResponseObject(buildResponseObject(responseObjectClass, responseString));
		return rst;
	}

}
