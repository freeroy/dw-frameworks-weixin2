package org.developerworld.frameworks.weixin2.qy.callback.servlet;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.developerworld.commons.lang.StringUtils;
import org.developerworld.commons.servlet.http.RequestUtils;
import org.developerworld.frameworks.weixin2.qy.api.CallbackIPApi;
import org.developerworld.frameworks.weixin2.qy.callback.dto.DecryptMessage;
import org.developerworld.frameworks.weixin2.qy.callback.dto.EncryptMessage;
import org.developerworld.frameworks.weixin2.qy.callback.dto.rep.AbstractRep;
import org.developerworld.frameworks.weixin2.qy.callback.dto.req.AbstractReq;
import org.developerworld.frameworks.weixin2.qy.callback.dto.req.event.AbstractEventReq;
import org.developerworld.frameworks.weixin2.qy.callback.dto.req.message.AbstractMessageReq;
import org.developerworld.frameworks.weixin2.qy.callback.utils.RequestMessageUtils;
import org.developerworld.frameworks.weixin2.qy.callback.utils.ResponseMessageUtils;

/**
 * 抽象消息回调处理servlet
 * 
 * @author Roy Huang
 *
 */
public abstract class AbstractCallbackServlet extends HttpServlet {

	/* 发生异常是否抛出 */
	private boolean catchExceptionThrow = true;
	/* 是否检查 */
	private boolean checkRemoteAddr = false;
	private List<String> wxQyIpListCache;

	public boolean isCheckRemoteAddr() {
		return checkRemoteAddr;
	}

	public void setCheckRemoteAddr(boolean checkRemoteAddr) {
		this.checkRemoteAddr = checkRemoteAddr;
	}

	public boolean isCatchExceptionThrow() {
		return catchExceptionThrow;
	}

	public void setCatchExceptionThrow(boolean catchExceptionThrow) {
		this.catchExceptionThrow = catchExceptionThrow;
	}

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		if (servletConfig.getInitParameter("catchExceptionThrow") != null)
			setCatchExceptionThrow(Boolean.parseBoolean(servletConfig.getInitParameter("catchExceptionThrow")));
		if (servletConfig.getInitParameter("checkRemoteAddr") != null)
			setCheckRemoteAddr(Boolean.parseBoolean(servletConfig.getInitParameter("checkRemoteAddr")));
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
			// 进校接入校验
			verifyJoinUp(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			if (catchExceptionThrow)
				throw new ServletException(e);
		}
	}

	/**
	 * 接入校验
	 * 
	 * @param request
	 * @param response
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws InvalidAlgorithmParameterException
	 * @throws InvalidKeyException
	 * @throws IOException
	 */
	protected void verifyJoinUp(HttpServletRequest request, HttpServletResponse response)
			throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException,
			BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, IOException {
		// 获取参数
		String msgSignature = request.getParameter("msg_signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		// 判断参数是否为空
		if (StringUtils.isBlank(msgSignature) || StringUtils.isBlank(timestamp) || StringUtils.isBlank(nonce)
				|| StringUtils.isBlank(echostr))
			throw new IllegalArgumentException(
					"The request parameters(msgSignature,timestamp,nonce,echostr) can not be blank!");
		// 获取token
		String token = getToken(request);
		if (StringUtils.isBlank(token))
			throw new RuntimeException("Can not find token!");
		// 校验msgSignature
		if (!RequestMessageUtils.verifyJoinUpMsgSignature(msgSignature, timestamp, nonce, echostr, token))
			throw new RuntimeException("Verify msg_signature error!");
		// 获取解密密匙
		String encodingAESKey = getEncodingAESKey(request, token);
		if (StringUtils.isBlank(encodingAESKey))
			throw new RuntimeException("Can not find encodingAESKey!");
		// 解密内容
		DecryptMessage decryptMessage = RequestMessageUtils.decryptMessage(encodingAESKey, echostr);
		if (decryptMessage == null)
			throw new RuntimeException("Decrypt message error!");
		// 校验corpId
		if (!verifyCorpID(request, decryptMessage, decryptMessage.getCorpID()))
			throw new RuntimeException("Verify corpId error!");
		// 校验来源ip是否合法
		if (!verifyRemoteAddr(request, token, decryptMessage, getRemoteAddr(request)))
			throw new RuntimeException("Verify remote addr error!");
		// 输出校验码内容
		response.getWriter().print(decryptMessage.getMsg());
	}

	/**
	 * 获取请求ip地址
	 * 
	 * @param request
	 * @return
	 */
	protected String getRemoteAddr(HttpServletRequest request) {
		return new RequestUtils(request).getRemoteAddr();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
			// 处理请求消息
			handleMessageRequest(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			if (catchExceptionThrow)
				throw new ServletException(e);
		}
	}

	/**
	 * 处理请求消息
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	protected void handleMessageRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String msgSignature = request.getParameter("msg_signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		// 判断参数是否为空
		if (StringUtils.isBlank(msgSignature) || StringUtils.isBlank(timestamp) || StringUtils.isBlank(nonce))
			throw new IllegalArgumentException(
					"The request parameters(msgSignature,timestamp,nonce) can not be blank!");
		// 获取token
		String token = getToken(request);
		if (StringUtils.isBlank(token))
			throw new RuntimeException("Can not find token!");
		// 判断请求消息体是否为空
		String messageReqXml = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
		if (StringUtils.isBlank(messageReqXml))
			throw new IllegalArgumentException("The request message can not be blank!");
		// 构建加密消息对象
		EncryptMessage encryptMessage = RequestMessageUtils.buildEncryptMessage(messageReqXml);
		if (encryptMessage == null)
			throw new RuntimeException("The request message format is error!");
		// 校验消息
		if (!RequestMessageUtils.verifyMsgSignature(token, timestamp, nonce, encryptMessage.getEncrypy(), msgSignature))
			throw new RuntimeException("Verify msg_signature error!");
		// 获取解密密匙
		String encodingAESKey = getEncodingAESKey(request, token);
		if (StringUtils.isBlank(encodingAESKey))
			throw new RuntimeException("Can not find encodingAESKey!");
		// 解密内容
		DecryptMessage decryptMessage = RequestMessageUtils.decryptMessage(encodingAESKey, encryptMessage.getEncrypy());
		if (decryptMessage == null)
			throw new RuntimeException("Decrypt message error!");
		// 校验corpId
		if (!verifyCorpID(request, decryptMessage, decryptMessage.getCorpID()))
			throw new RuntimeException("Verify corpId error!");
		// 校验来源ip是否合法
		if (!verifyRemoteAddr(request, token, decryptMessage, getRemoteAddr(request)))
			throw new RuntimeException("Verify remote addr error!");
		// 根据解密信息，构建请求信息对象
		AbstractReq req = RequestMessageUtils.buildReq(decryptMessage.getMsg());
		if (req == null)
			throw new RuntimeException("Can not deserialize message xml!");

		String output = null;
		// 尝试锁定请求消息并安排处理
		if (lockReqHandle(request, token, encryptMessage, decryptMessage, req)) {
			try {
				AbstractRep rep = handleMessageRequest(request, token, encryptMessage, decryptMessage, req);
				output = getEncryptRepMessage(request, token, encodingAESKey, decryptMessage.getCorpID(), nonce,
						encryptMessage, decryptMessage, req, rep);
				cacheReqRep(request, token, encryptMessage, decryptMessage, req, output);
			} finally {
				unlockReqHandle(request, token, encryptMessage, decryptMessage, req);
			}
		}
		// 若锁定失败，有可能是被其它对象锁定了,那么尝试再获取一次请求响应
		else
			output = waitReqHandle(request, token, encryptMessage, decryptMessage, req);
		if (output != null)
			response.getWriter().println(output);
	}

	/**
	 * 等待请求处理
	 * 
	 * @param request
	 * @param token
	 * @param encryptMessage
	 * @param decryptMessage
	 * @param req
	 * @return
	 */
	protected abstract String waitReqHandle(HttpServletRequest request, String token, EncryptMessage encryptMessage,
			DecryptMessage decryptMessage, AbstractReq req);

	/**
	 * 错定消息处理
	 * 
	 * @param request
	 * @param token
	 * @param encryptMessage
	 * @param decryptMessage
	 * @param req
	 */
	protected abstract boolean lockReqHandle(HttpServletRequest request, String token, EncryptMessage encryptMessage,
			DecryptMessage decryptMessage, AbstractReq req);

	/**
	 * 缓存请求响应
	 * 
	 * @param request
	 * @param token
	 * @param encryptMessage
	 * @param decryptMessage
	 * @param req
	 * @param output
	 */
	protected abstract void cacheReqRep(HttpServletRequest request, String token, EncryptMessage encryptMessage,
			DecryptMessage decryptMessage, AbstractReq req, String output);

	/**
	 * 解锁消息处理
	 * 
	 * @param request
	 * @param token
	 * @param encryptMessage
	 * @param decryptMessage
	 * @param req
	 */
	protected abstract void unlockReqHandle(HttpServletRequest request, String token, EncryptMessage encryptMessage,
			DecryptMessage decryptMessage, AbstractReq req);

	/**
	 * 获取消息的唯一key
	 * 
	 * @param req
	 * @return
	 */
	protected String getReqUniqueKey(AbstractReq req) {
		String rst = null;
		if (req instanceof AbstractMessageReq)
			rst = "msg:" + ((AbstractMessageReq) req).getMsgId().toString();
		else if (req instanceof AbstractEventReq) {
			AbstractEventReq _req = (AbstractEventReq) req;
			rst = "evt:" + _req.getFromUserName() + _req.getCreateTime();
		}
		return rst;
	}

	/**
	 * 有响应消息处理
	 * 
	 * @param request
	 * @param token
	 * @param encodingAESKey
	 * @param corpID
	 * @param nonce
	 * @param encryptMessage
	 * @param decryptMessage
	 * @param req
	 * @param rep
	 * @return
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws InvalidAlgorithmParameterException
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws IOException
	 * @throws JAXBException
	 */
	private String getEncryptRepMessage(HttpServletRequest request, String token, String encodingAESKey, String corpID,
			String nonce, EncryptMessage encryptMessage, DecryptMessage decryptMessage, AbstractReq req,
			AbstractRep rep) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
					InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException,
					JAXBException {
		// 根据对象获取响应信息原文
		String rst = null;
		if (rep != null) {
			String srcRepXml = ResponseMessageUtils.buildMessageRepXml(rep);
			if (StringUtils.isBlank(srcRepXml))
				throw new RuntimeException("Can not serialize message xml!");
			String random = RandomStringUtils.randomAlphanumeric(16);
			String timestamp = Long.toString(System.currentTimeMillis());
			// 对响应信息进行解密
			String encryptRepXml = ResponseMessageUtils.encryptSrcRepXml(random, timestamp, nonce, corpID,
					encodingAESKey, token, srcRepXml);
			if (StringUtils.isBlank(encryptRepXml))
				throw new RuntimeException("Can not encrypt message!");
			rst = encryptRepXml;
		}
		return rst == null ? "" : rst;
	}

	/**
	 * 校验请求ip(若希望提升性能，可降低校验级别，直接实现返回true方法)
	 * 
	 * @param request
	 * @param corpID
	 * @param ip
	 * @return
	 */
	protected boolean verifyRemoteAddr(HttpServletRequest request, String token, DecryptMessage decryptMessage,
			String remoteAddr) {
		if (checkRemoteAddr) {
			// 第一次利用缓存ip校验，无法通过则重新加载ip列表后再校验
			boolean[] reloads = { false, true };
			for (boolean reload : reloads) {
				String accessToken = null;
				if (reload)
					accessToken = getAccessToken(request, token, decryptMessage);
				List<String> iplist = getWxQyIpListCache(accessToken, reload);
				for (String ipExpression : iplist) {
					// 进行通配符匹配
					if (StringUtils.wildcardCapture(ipExpression, remoteAddr))
						return true;
				}
			}
			return false;
		}
		return true;
	}

	/**
	 * 获取微信企业号ip列表缓存
	 * 
	 * @return
	 */
	private List<String> getWxQyIpListCache(String accessToken, boolean reload) {
		if (wxQyIpListCache == null || wxQyIpListCache.size() <= 0 || reload)
			wxQyIpListCache = CallbackIPApi.getCallbackIP(accessToken).getResponseObject();
		return new ArrayList<String>(wxQyIpListCache);
	}

	/**
	 * 根据请求消息，获取token
	 * 
	 * @param request
	 * @return
	 */
	protected abstract String getToken(HttpServletRequest request);

	/**
	 * 根据请求信息，获取解密钥匙
	 * 
	 * @param request
	 * @param token
	 * @return
	 */
	protected abstract String getEncodingAESKey(HttpServletRequest request, String token);

	/**
	 * 检验企业号信息
	 * 
	 * @param request
	 * @param decryptMessage
	 * @param corpID
	 * @return
	 */
	protected abstract boolean verifyCorpID(HttpServletRequest request, DecryptMessage decryptMessage, String corpID);

	/**
	 * 获取accessToken
	 * 
	 * @param request
	 * @param token
	 * @param decryptMessage
	 * @return
	 */
	protected abstract String getAccessToken(HttpServletRequest request, String token, DecryptMessage decryptMessage);

	/**
	 * 处理消息
	 * 
	 * @param request
	 * @param token
	 * @param encryptMessage
	 * @param decryptMessage
	 * @param req
	 * @return
	 */
	protected abstract AbstractRep handleMessageRequest(HttpServletRequest request, String token,
			EncryptMessage encryptMessage, DecryptMessage decryptMessage, AbstractReq req);
}
