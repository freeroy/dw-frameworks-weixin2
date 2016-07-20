package org.developerworld.frameworks.weixin2.commons.api.exception;

/**
 * http调用返回状态码异常
 * @author Roy Huang
 *
 */
public class HttpCallStatusException extends RuntimeException {

	private int status;
	
	public HttpCallStatusException(int status){
		this.status=status;
	}
	
	public int getStatus(){
		return status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + status;
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
		HttpCallStatusException other = (HttpCallStatusException) obj;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HttpStatusException [status=" + status + "]";
	}
	
}
