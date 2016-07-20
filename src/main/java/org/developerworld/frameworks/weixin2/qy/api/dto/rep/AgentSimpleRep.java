package org.developerworld.frameworks.weixin2.qy.api.dto.rep;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * 企业号应用简单对象
 * 
 * @author Roy Huang
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AgentSimpleRep {

	private Integer agentid;
	private String name;
	private String squareLogoUrl;
	private String roundLogoUrl;

	public Integer getAgentid() {
		return agentid;
	}

	public void setAgentid(Integer agentid) {
		this.agentid = agentid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSquareLogoUrl() {
		return squareLogoUrl;
	}

	public void setSquareLogoUrl(String squareLogoUrl) {
		this.squareLogoUrl = squareLogoUrl;
	}

	public String getRoundLogoUrl() {
		return roundLogoUrl;
	}

	public void setRoundLogoUrl(String roundLogoUrl) {
		this.roundLogoUrl = roundLogoUrl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agentid == null) ? 0 : agentid.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((roundLogoUrl == null) ? 0 : roundLogoUrl.hashCode());
		result = prime * result + ((squareLogoUrl == null) ? 0 : squareLogoUrl.hashCode());
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
		AgentSimpleRep other = (AgentSimpleRep) obj;
		if (agentid == null) {
			if (other.agentid != null)
				return false;
		} else if (!agentid.equals(other.agentid))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (roundLogoUrl == null) {
			if (other.roundLogoUrl != null)
				return false;
		} else if (!roundLogoUrl.equals(other.roundLogoUrl))
			return false;
		if (squareLogoUrl == null) {
			if (other.squareLogoUrl != null)
				return false;
		} else if (!squareLogoUrl.equals(other.squareLogoUrl))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AgentSimpleRep [agentid=" + agentid + ", name=" + name + ", squareLogoUrl=" + squareLogoUrl
				+ ", roundLogoUrl=" + roundLogoUrl + "]";
	}

}
