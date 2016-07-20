package org.developerworld.frameworks.weixin2.qy.callback.servlet;

import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.developerworld.frameworks.weixin2.qy.callback.dto.DecryptMessage;
import org.developerworld.frameworks.weixin2.qy.callback.dto.EncryptMessage;
import org.developerworld.frameworks.weixin2.qy.callback.dto.rep.AbstractRep;
import org.developerworld.frameworks.weixin2.qy.callback.dto.req.AbstractReq;
import org.developerworld.frameworks.weixin2.qy.callback.servlet.handler.ReqHandler;

/**
 * 消息处理
 * @author Roy huang
 *
 */
public abstract class AbstractCallbackHandlerServlet extends AbstractCallbackServlet {

	private Map<Class<? extends AbstractReq>, ReqHandler> globalReqHandlers;
	private ReqHandler defaultReqHandler;

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		this.globalReqHandlers = getGlobalReqHandlers(servletConfig, getServletContext());
		this.defaultReqHandler = getDefaultReqHandler(servletConfig, getServletContext());
	}

	@Override
	protected AbstractRep handleMessageRequest(HttpServletRequest request, String token,
			EncryptMessage encryptMessage, DecryptMessage decryptMessage, AbstractReq req) {
		AbstractRep rst = null;
		Map<Class<? extends AbstractReq>, ReqHandler> reqHandlers = getRequestReqHandlers(request, token,
				encryptMessage, decryptMessage, req);
		// 优先处理请求处理器
		if (reqHandlers != null) {
			ReqHandler<AbstractReq> reqHandler = reqHandlers.get(req.getClass());
			if (reqHandler != null)
				rst = reqHandler.handle(request, token, encryptMessage, decryptMessage, req);
		}
		if (rst == null && globalReqHandlers != null) {
			ReqHandler<AbstractReq> reqHandler = globalReqHandlers.get(req.getClass());
			if (reqHandler != null)
				rst = reqHandler.handle(request, token, encryptMessage, decryptMessage, req);
		}
		if (rst == null && defaultReqHandler != null)
			rst = defaultReqHandler.handle(request, token, encryptMessage, decryptMessage, req);
		return rst;
	}

	/**
	 * 获取请求专用的消息处理器
	 * 
	 * @param request
	 * @param token
	 * @param corpID
	 * @param encryptMessage
	 * @param decryptMessage
	 * @param req
	 * @return
	 */
	protected abstract Map<Class<? extends AbstractReq>, ReqHandler> getRequestReqHandlers(HttpServletRequest request,
			String token, EncryptMessage encryptMessage, DecryptMessage decryptMessage, AbstractReq req);

	/**
	 * 获取全局请求处理器
	 * 
	 * @param request
	 * @return
	 */
	protected abstract Map<Class<? extends AbstractReq>, ReqHandler> getGlobalReqHandlers(ServletConfig servletConfig,
			ServletContext servletContext);

	/**
	 * 获取默认请求处理器
	 * 
	 * @param servletConfig
	 * @param servletContext
	 * @return
	 */
	protected abstract ReqHandler getDefaultReqHandler(ServletConfig servletConfig, ServletContext servletContext);

}
