package org.developerworld.frameworks.weixin2.qy.api.dto.base;

import org.developerworld.frameworks.weixin2.commons.api.dto.BooleanToIntegerSerializer;
import org.developerworld.frameworks.weixin2.qy.api.dto.enums.ReportLocationFlag;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 企业号应用公共类
 * @author Roy Huang
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public abstract class AgentBase {
	
	protected Integer agentid;
	protected String name;
	protected String description;
	protected String redirectDomain;
	@JsonSerialize(using = BooleanToIntegerSerializer.class)
	protected Boolean isreportuser;
	@JsonSerialize(using = BooleanToIntegerSerializer.class)
	protected Boolean isreportenter;
	protected ReportLocationFlag reportLocationFlag;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRedirectDomain() {
		return redirectDomain;
	}

	public void setRedirectDomain(String redirectDomain) {
		this.redirectDomain = redirectDomain;
	}

	public Boolean getIsreportuser() {
		return isreportuser;
	}

	public void setIsreportuser(Boolean isreportuser) {
		this.isreportuser = isreportuser;
	}

	public Boolean getIsreportenter() {
		return isreportenter;
	}

	public void setIsreportenter(Boolean isreportenter) {
		this.isreportenter = isreportenter;
	}

	public ReportLocationFlag getReportLocationFlag() {
		return reportLocationFlag;
	}

	public void setReportLocationFlag(ReportLocationFlag reportLocationFlag) {
		this.reportLocationFlag = reportLocationFlag;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agentid == null) ? 0 : agentid.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((isreportenter == null) ? 0 : isreportenter.hashCode());
		result = prime * result + ((isreportuser == null) ? 0 : isreportuser.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((redirectDomain == null) ? 0 : redirectDomain.hashCode());
		result = prime * result + ((reportLocationFlag == null) ? 0 : reportLocationFlag.hashCode());
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
		AgentBase other = (AgentBase) obj;
		if (agentid == null) {
			if (other.agentid != null)
				return false;
		} else if (!agentid.equals(other.agentid))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (isreportenter == null) {
			if (other.isreportenter != null)
				return false;
		} else if (!isreportenter.equals(other.isreportenter))
			return false;
		if (isreportuser == null) {
			if (other.isreportuser != null)
				return false;
		} else if (!isreportuser.equals(other.isreportuser))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (redirectDomain == null) {
			if (other.redirectDomain != null)
				return false;
		} else if (!redirectDomain.equals(other.redirectDomain))
			return false;
		if (reportLocationFlag != other.reportLocationFlag)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AgentBase [agentid=" + agentid + ", name=" + name + ", description=" + description + ", redirectDomain="
				+ redirectDomain + ", isreportuser=" + isreportuser + ", isreportenter=" + isreportenter
				+ ", reportLocationFlag=" + reportLocationFlag + "]";
	}

}
