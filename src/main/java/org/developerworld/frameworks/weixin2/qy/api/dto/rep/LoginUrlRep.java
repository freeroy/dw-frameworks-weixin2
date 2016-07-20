package org.developerworld.frameworks.weixin2.qy.api.dto.rep;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class LoginUrlRep {

	private Integer errcode;
	private String errmsg;
	private String loginUrl;
	private Integer expiresIn;

	public Integer getErrcode() {
		return errcode;
	}

	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public Integer getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((errcode == null) ? 0 : errcode.hashCode());
		result = prime * result + ((errmsg == null) ? 0 : errmsg.hashCode());
		result = prime * result + ((expiresIn == null) ? 0 : expiresIn.hashCode());
		result = prime * result + ((loginUrl == null) ? 0 : loginUrl.hashCode());
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
		LoginUrlRep other = (LoginUrlRep) obj;
		if (errcode == null) {
			if (other.errcode != null)
				return false;
		} else if (!errcode.equals(other.errcode))
			return false;
		if (errmsg == null) {
			if (other.errmsg != null)
				return false;
		} else if (!errmsg.equals(other.errmsg))
			return false;
		if (expiresIn == null) {
			if (other.expiresIn != null)
				return false;
		} else if (!expiresIn.equals(other.expiresIn))
			return false;
		if (loginUrl == null) {
			if (other.loginUrl != null)
				return false;
		} else if (!loginUrl.equals(other.loginUrl))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LoginUrlRep [errcode=" + errcode + ", errmsg=" + errmsg + ", loginUrl=" + loginUrl + ", expiresIn="
				+ expiresIn + "]";
	}

}
