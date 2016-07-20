package org.developerworld.frameworks.weixin2.qy.callback.servlet.handler;

import javax.servlet.http.HttpServletRequest;

import org.developerworld.frameworks.weixin2.qy.callback.dto.DecryptMessage;
import org.developerworld.frameworks.weixin2.qy.callback.dto.EncryptMessage;
import org.developerworld.frameworks.weixin2.qy.callback.dto.rep.message.AbstractMessageRep;
import org.developerworld.frameworks.weixin2.qy.callback.dto.req.AbstractReq;

/**
 * 消息处理器接口
 * 
 * @author Roy Huang
 *
 * @param <T>
 */
public interface ReqHandler<T extends AbstractReq> {

	/**
	 * 处理消息
	 * 
	 * @param request
	 * @param token
	 * @param corpID
	 * @param encryptMessage
	 * @param decryptMessage
	 * @param req
	 * @return
	 */
	public AbstractMessageRep handle(HttpServletRequest request, String token, EncryptMessage encryptMessage,
			DecryptMessage decryptMessage, T req);

}
