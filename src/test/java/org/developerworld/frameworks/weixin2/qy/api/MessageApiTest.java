package org.developerworld.frameworks.weixin2.qy.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.developerworld.frameworks.weixin2.commons.api.ApiResponse;
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.SendMessageInvalidRep;
import org.developerworld.frameworks.weixin2.qy.api.dto.req.NewsArticleReq;
import org.developerworld.frameworks.weixin2.qy.api.dto.req.SendMsgReq.FileMediaSendMsgReq;
import org.developerworld.frameworks.weixin2.qy.api.dto.req.SendMsgReq.ImageMediaSendMsgReq;
import org.developerworld.frameworks.weixin2.qy.api.dto.req.SendMsgReq.MpnewsSendMsgReq;
import org.developerworld.frameworks.weixin2.qy.api.dto.req.SendMsgReq.NewsSendMsgReq;
import org.developerworld.frameworks.weixin2.qy.api.dto.req.SendMsgReq.TextSendMsgReq;
import org.developerworld.frameworks.weixin2.qy.api.dto.req.SendMsgReq.VideoMediaSendMsgReq;
import org.developerworld.frameworks.weixin2.qy.api.dto.req.SendMsgReq.VoiceMediaSendMsgReq;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class MessageApiTest extends AbstractTestSupport{

	@Test
	public void testSendMessageStringIntListOfStringListOfIntegerListOfIntegerSendMsgReqBoolean() {
		
		//Integer tagId=TagApi.createTag(accessToken , "测试标签"+RandomStringUtils.randomAlphanumeric(4)).getResponseObject();
		//Integer departmentId=buildDepartment();
		List<String> touser=Arrays.asList(new String[]{testUserid});
		List<Integer> toparty=null;//Arrays.asList(new Integer[]{departmentId});
		List<Integer> totag=null;//Arrays.asList(new Integer[]{tagId});
		
		//文件消息
		String mediaId=buildFileMediaId();
		FileMediaSendMsgReq sendMsg1=new FileMediaSendMsgReq();
		sendMsg1.setMediaId(mediaId);
		ApiResponse<SendMessageInvalidRep> response=MessageApi.sendMessage(accessToken, testAgentid, touser, toparty, totag, sendMsg1, true);
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
		MaterialApi.deleteMaterial(accessToken, testAgentid, mediaId);
		
		//图片消息
		mediaId=buildImageMediaId();
		ImageMediaSendMsgReq sendMsg2=new ImageMediaSendMsgReq();
		sendMsg2.setMediaId(mediaId);
		response=MessageApi.sendMessage(accessToken, testAgentid, touser, toparty, totag, sendMsg2, true);
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
		MaterialApi.deleteMaterial(accessToken, testAgentid, mediaId);
		
		//语音消息
		mediaId=buildVoiceMediaId();
		VoiceMediaSendMsgReq sendMsg3=new VoiceMediaSendMsgReq();
		sendMsg3.setMediaId(mediaId);
		response=MessageApi.sendMessage(accessToken, testAgentid, touser, toparty, totag, sendMsg3, true);
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
		MaterialApi.deleteMaterial(accessToken, testAgentid, mediaId);
		
		//文字消息
		TextSendMsgReq sendMsg4=new TextSendMsgReq();
		sendMsg4.setContent("测试内容");
		response=MessageApi.sendMessage(accessToken, testAgentid, touser, toparty, totag, sendMsg4, true);
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
		
		//视频消息
		mediaId=buildVideoMediaId();
		VideoMediaSendMsgReq sendMsg5=new VideoMediaSendMsgReq();
		sendMsg5.setMediaId(mediaId);
		sendMsg5.setDescription("好鬼正啊");
		sendMsg5.setTitle("正野");
		response=MessageApi.sendMessage(accessToken, testAgentid, touser, toparty, totag, sendMsg5, true);
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
		MaterialApi.deleteMaterial(accessToken, testAgentid, mediaId);
		
		//图文消息
		NewsSendMsgReq sendMsg6=new NewsSendMsgReq();
		List<NewsArticleReq> newArticles=new ArrayList<NewsArticleReq>();
		NewsArticleReq newArticle=new NewsArticleReq();
		newArticle.setTitle("标题");
		newArticle.setDescription("描述");
		newArticle.setUrl("http://www.baidu.com");
		newArticle.setPicurl("http://qydev.weixin.qq.com/wiki/skins/common/images/weixin/weixin_wiki_logo.png");
		newArticles.add(newArticle);
		newArticle=new NewsArticleReq();
		newArticle.setTitle("标题2");
		newArticle.setDescription("描述2");
		newArticle.setUrl("http://www.163.com");
		newArticle.setPicurl("http://wx.qlogo.cn/mmhead/Q3auHgzwzM6dFu0GBUAsaXBEwL8yGYAtUd38qXfya3H8PTgEDMWWKg/0");
		newArticles.add(newArticle);
		sendMsg6.setArticles(newArticles);
		response=MessageApi.sendMessage(accessToken, testAgentid, touser, toparty, totag, sendMsg6, true);
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
		
		//图文消息
		mediaId=buildMpnewsMediaId();
		MpnewsSendMsgReq sendMsg7=new MpnewsSendMsgReq();
		sendMsg7.setMediaId(mediaId);
		response=MessageApi.sendMessage(accessToken, testAgentid, touser, toparty, totag, sendMsg7, true);
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
		MaterialApi.deleteMaterial(accessToken, testAgentid, mediaId);
		
		//图文消息
		MpnewsSendMsgReq sendMsg8=new MpnewsSendMsgReq();
		sendMsg8.setArticles(buildMpnewsReq());
		response=MessageApi.sendMessage(accessToken, testAgentid, touser, toparty, totag, sendMsg8, true);
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
		
		//删除临时创建的数据
//		TagApi.deleteTag(accessToken, tagId);
//		DepartmentApi.deleteDepartment(accessToken, departmentId);
	}

}
