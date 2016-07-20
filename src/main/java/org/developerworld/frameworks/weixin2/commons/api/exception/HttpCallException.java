package org.developerworld.frameworks.weixin2.commons.api.exception;

/**
 * Http接口调用异常
 * @author Roy Huang
 *
 */
public class HttpCallException extends RuntimeException {

	public HttpCallException() {
		super();
	}

	public HttpCallException(String message) {
		super(message);
	}

	public HttpCallException(String message, Throwable cause) {
		super(message, cause);
	}

	public HttpCallException(Throwable cause) {
		super(cause);
	}
}
