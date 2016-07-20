package org.developerworld.frameworks.weixin2.qy.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.developerworld.commons.lang.MapBuilder;
import org.developerworld.commons.lang.StringUtils;
import org.developerworld.frameworks.weixin2.commons.api.ApiResponse;
import org.developerworld.frameworks.weixin2.commons.api.ApiSupport;
import org.developerworld.frameworks.weixin2.qy.api.dto.enums.KFListType;
import org.developerworld.frameworks.weixin2.qy.api.dto.enums.KFUserType;
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.KFListRep;
import org.developerworld.frameworks.weixin2.qy.api.dto.req.KFMessageReq;

import com.fasterxml.jackson.databind.JsonNode;

public class KFApi extends ApiSupport {

	/**
	 * 向企业号客服发送客服消息
	 * 
	 * @param accessToken
	 * @param senderType
	 * @param senderId
	 * @param receiverType
	 * @param receiverId
	 * @param kfMessage
	 * @return
	 */
	public static ApiResponse<Boolean> sendKFMsg(String accessToken, KFUserType senderType, String senderId,
			KFUserType receiverType, String receiverId, KFMessageReq kfMessage) {
		if (StringUtils.isBlank(accessToken) || senderType == null || StringUtils.isBlank(senderId)
				|| receiverType == null || StringUtils.isBlank(receiverId) || kfMessage == null)
			throw new IllegalArgumentException(
					"The accessToken、senderId、receiverId can not be blank and senderType、receiverType、kfMessage must not null!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		String query = URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/kf/send?" + query;
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("sender",
				new MapBuilder<String, String>().put("type", senderType.toString()).put("id", senderId).map());
		jsonMap.put("receiver",
				new MapBuilder<String, String>().put("type", receiverType.toString()).put("id", receiverId).map());
		jsonMap.put("msgtype", kfMessage.getMsgtype().value());
		jsonMap.put(kfMessage.getMsgtype().value(), kfMessage);
		String json = object2Json(jsonMap);
		return doPostAndReturnBoolean(url, json);
	}

	/**
	 * 获取客服列表
	 * @param accessToken
	 * @param type
	 * @return
	 */
	public static ApiResponse<KFListRep> getKFList(String accessToken, KFListType type) {
		if (StringUtils.isBlank(accessToken) || type == null)
			throw new IllegalArgumentException("The accessToken can not be blank and type must not null!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		params.add(new BasicNameValuePair("type", type.value()));
		String query = URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/kf/list" + query;
		String response = doGet(url);
		ApiResponse<KFListRep> rst = new ApiResponse<KFListRep>();
		rst.setResponseString(response);
		if (ApiResponse.isErrorResponseString(response))
			rst.setResponseException(ApiResponse.buildResponseException(response));
		else {
			try {
				KFListRep kfList = new KFListRep();
				JsonNode root = OBJECT_MAPPER.readTree(response);
				JsonNode internalNode = root.get("internal");
				if (internalNode != null)
					kfList.setInternal(json2Object(internalNode.toString(), KFListRep.KFInfo.class));
				JsonNode externalNode = root.get("external");
				if (externalNode != null)
					kfList.setExternal(json2Object(externalNode.toString(), KFListRep.KFInfo.class));
				rst.setResponseObject(kfList);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return rst;
	}
}
