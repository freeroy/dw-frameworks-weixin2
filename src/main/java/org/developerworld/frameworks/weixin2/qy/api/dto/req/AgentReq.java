package org.developerworld.frameworks.weixin2.qy.api.dto.req;

import org.developerworld.frameworks.weixin2.qy.api.dto.base.AgentBase;

/**
 * 企业号应用设置对象
 * 
 * @author Roy Huang
 *
 */
public class AgentReq extends AgentBase {

	private String logoMediaid;
	private String homeUrl;

	public void setLogoMediaid(String logoMediaid) {
		this.logoMediaid = logoMediaid;
	}

	public void setHomeUrl(String homeUrl) {
		this.homeUrl = homeUrl;
	}

	public String getLogoMediaid() {
		return logoMediaid;
	}

	public String getHomeUrl() {
		return homeUrl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((homeUrl == null) ? 0 : homeUrl.hashCode());
		result = prime * result + ((logoMediaid == null) ? 0 : logoMediaid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		AgentReq other = (AgentReq) obj;
		if (homeUrl == null) {
			if (other.homeUrl != null)
				return false;
		} else if (!homeUrl.equals(other.homeUrl))
			return false;
		if (logoMediaid == null) {
			if (other.logoMediaid != null)
				return false;
		} else if (!logoMediaid.equals(other.logoMediaid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AgentReq [logoMediaid=" + logoMediaid + ", homeUrl=" + homeUrl + ", agentid=" + agentid + ", name="
				+ name + ", description=" + description + ", redirectDomain=" + redirectDomain + ", isreportuser="
				+ isreportuser + ", isreportenter=" + isreportenter + ", reportLocationFlag=" + reportLocationFlag
				+ "]";
	}

}
