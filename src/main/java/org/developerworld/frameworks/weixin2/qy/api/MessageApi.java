package org.developerworld.frameworks.weixin2.qy.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.developerworld.commons.lang.MapBuilder;
import org.developerworld.commons.lang.StringUtils;
import org.developerworld.frameworks.weixin2.commons.api.ApiResponse;
import org.developerworld.frameworks.weixin2.commons.api.ApiSupport;
import org.developerworld.frameworks.weixin2.qy.api.dto.enums.SendMsgType;
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.SendMessageInvalidRep;
import org.developerworld.frameworks.weixin2.qy.api.dto.req.SendMsgReq;

/**
 * 发消息接口
 * 
 * @author Roy Huang
 *
 */
public class MessageApi extends ApiSupport {

	/**
	 * 格式化集合
	 * 
	 * @param list
	 * @return
	 */
	private static <T> String joinList(List<T> list) {
		String rst = null;
		if (list != null && list.size() > 0) {
			rst = "";
			for (Object o : list)
				rst += o.toString() + "|";
			rst = rst.substring(0, rst.length() - 1);
		}
		return rst;
	}

	/**
	 * 发送消息
	 * 
	 * @param accessToken
	 * @param agentid
	 * @param touser
	 * @param sendMsg
	 * @param safe
	 * @return
	 */
	public static ApiResponse<SendMessageInvalidRep> sendMessageToUser(String accessToken, int agentid,
			List<String> touser, SendMsgReq sendMsg, Boolean safe) {
		return sendMessage(accessToken, agentid, touser, null, null, sendMsg, safe);
	}

	/**
	 * 发送消息
	 * 
	 * @param accessToken
	 * @param agentid
	 * @param toparty
	 * @param sendMsg
	 * @param safe
	 * @return
	 */
	public static ApiResponse<SendMessageInvalidRep> sendMessageToParty(String accessToken, int agentid,
			List<Integer> toparty, SendMsgReq sendMsg, Boolean safe) {
		return sendMessage(accessToken, agentid, null, toparty, null, sendMsg, safe);
	}

	/**
	 * 发送消息
	 * 
	 * @param accessToken
	 * @param agentid
	 * @param totag
	 * @param sendMsg
	 * @param safe
	 * @return
	 */
	public static ApiResponse<SendMessageInvalidRep> sendMessageToTag(String accessToken, int agentid,
			List<Integer> totag, SendMsgReq sendMsg, Boolean safe) {
		return sendMessage(accessToken, agentid, null, null, totag, sendMsg, safe);
	}

	/**
	 * 发送消息
	 * 
	 * @param accessToken
	 * @param agentid
	 * @param touser
	 * @param toparty
	 * @param totag
	 * @param sendMsg
	 * @return
	 */
	public static ApiResponse<SendMessageInvalidRep> sendMessage(String accessToken, int agentid, List<String> touser,
			List<Integer> toparty, List<Integer> totag, SendMsgReq sendMsg) {
		return sendMessage(accessToken, agentid, touser, toparty, totag, sendMsg, null);
	}

	/**
	 * 发送消息
	 * 
	 * @param accessToken
	 * @param agentid
	 * @param touser
	 * @param toparty
	 * @param totag
	 * @param sendMsg
	 * @param safe
	 * @return
	 */
	public static ApiResponse<SendMessageInvalidRep> sendMessage(String accessToken, int agentid, List<String> touser,
			List<Integer> toparty, List<Integer> totag, SendMsgReq sendMsg, Boolean safe) {
		if (StringUtils.isBlank(accessToken) || agentid <= 0 || sendMsg == null)
			throw new IllegalArgumentException(
					"The accessToken can not be blank and agentid must > 0 and sendMsg must not null!");
		else if ((touser == null || touser.size() <= 0) && (toparty == null || toparty.size() <= 0)
				&& (totag == null || totag.size() <= 0))
			throw new IllegalArgumentException("The touser、toparty、totag must set one!");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token", accessToken));
		String query=URLEncodedUtils.format(params, REQUEST_CONTENT_CHARSET);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/message/send?" + query;
		Map<String, Object> jsonMap = new MapBuilder<String, Object>().put("agentid", agentid).map();
		if (touser != null && touser.size() > 0)
			jsonMap.put("touser", joinList(touser));
		if (toparty != null && toparty.size() > 0)
			jsonMap.put("toparty", joinList(toparty));
		if (totag != null && totag.size() > 0)
			jsonMap.put("totag", joinList(totag));
		SendMsgType msgType = SendMsgType.valueOfSendMsgReqClass(sendMsg.getClass());
		jsonMap.put("msgtype", msgType.value());
		if (safe != null && msgType.equals(SendMsgType.NEWS))
			throw new IllegalArgumentException("News msgType can not use safe!");
		jsonMap.put("safe", safe ? 1 : 0);
		jsonMap.put(msgType.value(), sendMsg);
		String json = object2Json(jsonMap);
		return doPostAndReturn(url, json, SendMessageInvalidRep.class);
	}
}
