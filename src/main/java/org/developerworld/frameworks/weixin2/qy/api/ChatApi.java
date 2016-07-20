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
import org.developerworld.frameworks.weixin2.qy.api.dto.enums.ChatType;
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.ChatInfoRep;
import org.developerworld.frameworks.weixin2.qy.api.dto.req.ChatInfoReq;
import org.developerworld.frameworks.weixin2.qy.api.dto.req.ChatMessageReq;
import org.developerworld.frameworks.weixin2.qy.api.dto.req.UpdateChatInfoReq;
import org.developerworld.frameworks.weixin2.qy.api.dto.req.UserMuteReq;

import com.fasterxml.jackson.databind.JsonNode;

public class ChatApi extends ApiSupport {

	/**
	 * 创建会话
	 * 
	 * @param accessToken
	 * @param chatInfo
	 * @return
	 */
	public static ApiResponse<Boolean> createChat(String accessToken, ChatInfoReq chatInfo) {
		if (StringUtils.isBlank(accessToken) || chatInfo == null)
			throw new IllegalArgumentException("The accessToken can not be blank and chatInfo can not null!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		String query = URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/chat/create?" + query;
		String json = object2Json(chatInfo);
		return doPostAndReturnBoolean(url, json);
	}

	/**
	 * 获取会话
	 * 
	 * @param accessToken
	 * @param chatId
	 * @return
	 */
	public static ApiResponse<ChatInfoRep> getChat(String accessToken, String chatId) {
		if (StringUtils.isBlank(accessToken) || StringUtils.isBlank(chatId))
			throw new IllegalArgumentException("The accessToken,chatId can not be blank!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		params.add(new BasicNameValuePair("chatid", chatId));
		String query = URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/chat/get?" + query;
		String response = doGet(url);
		ApiResponse<ChatInfoRep> rst = new ApiResponse<ChatInfoRep>();
		rst.setResponseString(response);
		if (ApiResponse.isErrorResponseString(response))
			rst.setResponseException(ApiResponse.buildResponseException(response));
		else {
			try {
				JsonNode root = OBJECT_MAPPER.readTree(response);
				JsonNode chatInfoNode = root.get("chat_info");
				if (chatInfoNode != null)
					rst.setResponseObject(json2Object(chatInfoNode.toString(), ChatInfoRep.class));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return rst;
	}

	/**
	 * 修改会话
	 * 
	 * @param accessToken
	 * @param updateChatInfo
	 * @return
	 */
	public static ApiResponse<Boolean> updateChat(String accessToken, UpdateChatInfoReq updateChatInfo) {
		if (StringUtils.isBlank(accessToken) || updateChatInfo == null)
			throw new IllegalArgumentException("The accessToken can not be blank and updateChatInfo can not null!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		String query = URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/chat/update?" + query;
		String json = object2Json(updateChatInfo);
		return doPostAndReturnBoolean(url, json);
	}

	/**
	 * 退出会话
	 * 
	 * @param accessToken
	 * @param chatId
	 * @param opUser
	 * @return
	 */
	public static ApiResponse<Boolean> quitChat(String accessToken, String chatId, String opUser) {
		if (StringUtils.isBlank(accessToken) || StringUtils.isBlank(chatId) || StringUtils.isBlank(opUser))
			throw new IllegalArgumentException(
					"The accessToken,chatId,opUser can not be blank and updateChatInfo can not null!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		String query = URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/chat/quit?" + query;
		Map<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put("chatid", chatId);
		jsonMap.put("op_user", opUser);
		String jsonParam = object2Json(jsonMap);
		return doPostAndReturnBoolean(url, jsonParam);
	}

	/**
	 * 清除会话未读状态
	 * 
	 * @param accessToken
	 * @param opUser
	 * @param type
	 * @param id
	 * @return
	 */
	public static ApiResponse<Boolean> clearChatnotify(String accessToken, String opUser, ChatType type, String id) {
		if (StringUtils.isBlank(accessToken) || StringUtils.isBlank(opUser) || type == null || StringUtils.isBlank(id))
			throw new IllegalArgumentException("The accessToken,opUser,id can not be blank and type can not null!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		String query = URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/chat/clearnotify?" + query;
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("op_user", opUser);
		jsonMap.put("chat", new MapBuilder<String, String>().put("type", type.value()).put("id", id).map());
		String jsonParam = object2Json(jsonMap);
		return doPostAndReturnBoolean(url, jsonParam);
	}

	/**
	 * 发
	 * 
	 * @param accessToken
	 * @param receiverType
	 * @param receiverId
	 * @param sender
	 * @param chatMessage
	 * @return
	 */
	public static ApiResponse<Boolean> sendChatMsg(String accessToken, ChatType receiverType, String receiverId,
			String sender, ChatMessageReq chatMessage) {
		if (StringUtils.isBlank(accessToken) || receiverType == null || StringUtils.isBlank(receiverId)
				|| StringUtils.isBlank(sender) || chatMessage == null)
			throw new IllegalArgumentException(
					"The accessToken、receiverId、sender can not be blank and receiverType、chatMessage can not null!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		String query = URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/chat/send?" + query;
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("receiver",
				new MapBuilder<String, String>().put("type", receiverType.value()).put("id", receiverId).map());
		jsonMap.put("sender", sender);
		jsonMap.put("msgtype", chatMessage.getMsgtype().value());
		jsonMap.put(chatMessage.getMsgtype().value(), object2Json(chatMessage));
		String json = object2Json(jsonMap);
		return doPostAndReturnBoolean(url, json);
	}

	/**
	 * 设置成员新消息免打扰
	 * 
	 * @param accessToken
	 * @param userMutes
	 * @return
	 */
	public static ApiResponse<Boolean> setChatMute(String accessToken, List<UserMuteReq> userMutes) {
		if (StringUtils.isBlank(accessToken) || userMutes == null || userMutes.size() <= 0)
			throw new IllegalArgumentException(
					"The accessToken、receiverId、sender can not be blank and userMutes size must > 0!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		String query = URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/chat/setmute?" + query;
		Map<String, List<UserMuteReq>> jsonMap = new HashMap<String, List<UserMuteReq>>();
		jsonMap.put("user_mute_list", userMutes);
		String json = object2Json(jsonMap);
		return doPostAndReturnBoolean(url, json);
	}
}
