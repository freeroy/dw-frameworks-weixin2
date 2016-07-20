package org.developerworld.frameworks.weixin2.qy.callback.servlet;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.developerworld.frameworks.weixin2.qy.callback.dto.DecryptMessage;
import org.developerworld.frameworks.weixin2.qy.callback.dto.EncryptMessage;
import org.developerworld.frameworks.weixin2.qy.callback.dto.rep.message.AbstractMessageRep;
import org.developerworld.frameworks.weixin2.qy.callback.dto.rep.message.TextMessageRep;
import org.developerworld.frameworks.weixin2.qy.callback.dto.req.AbstractReq;
import org.developerworld.frameworks.weixin2.qy.callback.servlet.handler.ReqHandler;

/**
 * 简单消息回复
 * 
 * @author Roy Huang
 *
 */
public class SimpleSingleQYAccountCallbackServlet extends AbstractSingleQYAccountCallbackServlet {

	ConcurrentHashMap<String, ReentrantLock> reqRepLock = new ConcurrentHashMap<String, ReentrantLock>();
	LinkedHashMap<String, String> reqRepCache = new LinkedHashMap<String, String>();
	/* 等待处理锁间隔 */
	private long waitMsgHandingLockMilliSeconds = 1 * 1000;
	/* 获取处理锁次数 */
	private int getMsgHandingLockTimes = 5;
	/* 最大缓存响应 */
	private int maxCacheRep = 1000;
	/* 默认响应消息内容 */
	private String responseMessage = "Unsupport message request!";

	public long getWaitMsgHandingLockMilliSeconds() {
		return waitMsgHandingLockMilliSeconds;
	}

	public void setWaitMsgHandingLockMilliSeconds(long waitMsgHandingLockMilliSeconds) {
		this.waitMsgHandingLockMilliSeconds = waitMsgHandingLockMilliSeconds;
	}

	public int getGetMsgHandingLockTimes() {
		return getMsgHandingLockTimes;
	}

	public void setGetMsgHandingLockTimes(int waitMsgHandingLockTimes) {
		this.getMsgHandingLockTimes = waitMsgHandingLockTimes;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public int getMaxCacheRep() {
		return maxCacheRep;
	}

	public void setMaxCacheRep(int maxCacheRep) {
		this.maxCacheRep = maxCacheRep;
	}

	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		if (servletConfig.getInitParameter("responseMessage") != null)
			setResponseMessage(servletConfig.getInitParameter("responseMessage"));
		if (servletConfig.getInitParameter("maxCacheRep") != null)
			setMaxCacheRep(Integer.parseInt(servletConfig.getInitParameter("maxCacheRep")));
		if (servletConfig.getInitParameter("waitMsgHandingLockMilliSeconds") != null)
			setWaitMsgHandingLockMilliSeconds(
					Long.parseLong(servletConfig.getInitParameter("waitMsgHandingLockMilliSeconds")));
		if (servletConfig.getInitParameter("getMsgHandingLockTimes") != null)
			setGetMsgHandingLockTimes(Integer.parseInt(servletConfig.getInitParameter("getMsgHandingLockTimes")));
	}

	@Override
	protected Map<Class<? extends AbstractReq>, ReqHandler> getRequestReqHandlers(HttpServletRequest request,
			String token, EncryptMessage encryptMessage, DecryptMessage decryptMessage, AbstractReq req) {
		return null;
	}

	@Override
	protected Map<Class<? extends AbstractReq>, ReqHandler> getGlobalReqHandlers(ServletConfig servletConfig,
			ServletContext servletContext) {
		return null;
	}

	@Override
	protected ReqHandler getDefaultReqHandler(ServletConfig servletConfig, ServletContext servletContext) {
		return new ReqHandler() {

			@Override
			public AbstractMessageRep handle(HttpServletRequest request, String token, EncryptMessage encryptMessage,
					DecryptMessage decryptMessage, AbstractReq req) {
				TextMessageRep rst = new TextMessageRep();
				rst.setFromUserName(req.getToUserName());
				rst.setToUserName(req.getFromUserName());
				rst.setCreateTime(System.currentTimeMillis());
				rst.setContent(getResponseMessage());
				return rst;
			}

		};
	}

	@Override
	protected String waitReqHandle(HttpServletRequest request, String token, EncryptMessage encryptMessage,
			DecryptMessage decryptMessage, AbstractReq req) {
		String rst = null;
		try {
			String reqKey = getReqUniqueKey(req);
			ReentrantLock lock = reqRepLock.get(reqKey);
			if (lock != null) {
				try {
					int times = 0;
					int maxTimes = getGetMsgHandingLockTimes();
					while (times < maxTimes) {
						boolean canLock = lock.tryLock(getWaitMsgHandingLockMilliSeconds(), TimeUnit.MILLISECONDS);
						// 能够获取锁
						if (canLock) {
							rst = reqRepCache.get(reqKey);
							break;
						}
						++times;
					}
				} finally {
					lock.unlock();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rst;
	}

	@Override
	protected boolean lockReqHandle(HttpServletRequest request, String token, EncryptMessage encryptMessage,
			DecryptMessage decryptMessage, AbstractReq req) {
		String reqKey = getReqUniqueKey(req);
		ReentrantLock lock = new ReentrantLock();
		ReentrantLock _lock = reqRepLock.putIfAbsent(reqKey, lock);
		return lock.equals(_lock);
	}

	@Override
	protected void cacheReqRep(HttpServletRequest request, String token, EncryptMessage encryptMessage,
			DecryptMessage decryptMessage, AbstractReq req, String output) {
		reqRepCache.put(getReqUniqueKey(req), output);
		// 若存储缓存数大于设定值，删除缓存
		if (reqRepCache.size() > getMaxCacheRep()) {
			int delCount = reqRepCache.size() - getMaxCacheRep();
			Iterator<Entry<String, String>> iterator = reqRepCache.entrySet().iterator();
			// 以先入先出方式删除
			while (iterator.hasNext() && --delCount >= 0) {
				try {
					Entry<String, String> entry = iterator.next();
					// 删除锁信息
					reqRepLock.remove(entry.getKey());
					// 删除缓存信息
					iterator.remove();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	protected void unlockReqHandle(HttpServletRequest request, String token, EncryptMessage encryptMessage,
			DecryptMessage decryptMessage, AbstractReq req) {
		String reqKey = getReqUniqueKey(req);
		if (reqRepLock.containsKey(reqKey))
			reqRepLock.get(reqKey).unlock();
	}

}
