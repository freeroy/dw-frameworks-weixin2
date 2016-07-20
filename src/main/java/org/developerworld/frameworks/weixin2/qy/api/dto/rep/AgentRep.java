package org.developerworld.frameworks.weixin2.qy.api.dto.rep;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.developerworld.frameworks.weixin2.commons.api.dto.SingleObjectListToListDeserializer;
import org.developerworld.frameworks.weixin2.qy.api.dto.base.AgentBase;
import org.developerworld.frameworks.weixin2.qy.api.dto.enums.AgentType;
import org.developerworld.frameworks.weixin2.qy.api.dto.enums.UserStatus;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * 企业号应用对象
 * 
 * @author Roy Huang
 *
 */
public class AgentRep extends AgentBase {

	public static class AllowUserinfosDeserialize extends JsonDeserializer<Map<String, UserStatus>> {

		@Override
		public Map<String, UserStatus> deserialize(JsonParser p, DeserializationContext ctxt)
				throws IOException, JsonProcessingException {
			Map<String, UserStatus> rst = null;
			TreeNode root = p.getCodec().readTree(p);
			if (root.get("user") != null) {
				rst = new HashMap<String, UserStatus>();
				TreeNode usersNode = root.get("user");
				for (int i = 0; i < usersNode.size(); i++)
					rst.put(usersNode.get(i).get("userid").toString(),
							UserStatus.valueOfValue(Integer.parseInt(usersNode.get(i).get("status").toString())));
			}
			return rst;
		}

	}

	private String squareLogoUrl;
	private String roundLogoUrl;
	@JsonDeserialize(using = AllowUserinfosDeserialize.class)
	private Map<String, UserStatus> allowUserinfos;
	@JsonDeserialize(using = SingleObjectListToListDeserializer.class)
	private List<Integer> allowPartys;
	@JsonDeserialize(using = SingleObjectListToListDeserializer.class)
	private List<Integer> allowTags;
	private Boolean close;
	private AgentType type;

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

	public Map<String, UserStatus> getAllowUserinfos() {
		return allowUserinfos;
	}

	public void setAllowUserinfos(Map<String, UserStatus> allowUserinfos) {
		this.allowUserinfos = allowUserinfos;
	}

	public List<Integer> getAllowPartys() {
		return allowPartys;
	}

	public void setAllowPartys(List<Integer> allowPartys) {
		this.allowPartys = allowPartys;
	}

	public List<Integer> getAllowTags() {
		return allowTags;
	}

	public void setAllowTags(List<Integer> allowTags) {
		this.allowTags = allowTags;
	}

	public Boolean getClose() {
		return close;
	}

	public void setClose(Boolean close) {
		this.close = close;
	}

	public AgentType getType() {
		return type;
	}

	public void setType(AgentType type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((allowPartys == null) ? 0 : allowPartys.hashCode());
		result = prime * result + ((allowTags == null) ? 0 : allowTags.hashCode());
		result = prime * result + ((allowUserinfos == null) ? 0 : allowUserinfos.hashCode());
		result = prime * result + ((close == null) ? 0 : close.hashCode());
		result = prime * result + ((roundLogoUrl == null) ? 0 : roundLogoUrl.hashCode());
		result = prime * result + ((squareLogoUrl == null) ? 0 : squareLogoUrl.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		AgentRep other = (AgentRep) obj;
		if (allowPartys == null) {
			if (other.allowPartys != null)
				return false;
		} else if (!allowPartys.equals(other.allowPartys))
			return false;
		if (allowTags == null) {
			if (other.allowTags != null)
				return false;
		} else if (!allowTags.equals(other.allowTags))
			return false;
		if (allowUserinfos == null) {
			if (other.allowUserinfos != null)
				return false;
		} else if (!allowUserinfos.equals(other.allowUserinfos))
			return false;
		if (close == null) {
			if (other.close != null)
				return false;
		} else if (!close.equals(other.close))
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
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AgentRep [squareLogoUrl=" + squareLogoUrl + ", roundLogoUrl=" + roundLogoUrl + ", allowUserinfos="
				+ allowUserinfos + ", allowPartys=" + allowPartys + ", allowTags=" + allowTags + ", close=" + close
				+ ", type=" + type + ", agentid=" + agentid + ", name=" + name + ", description=" + description
				+ ", redirectDomain=" + redirectDomain + ", isreportuser=" + isreportuser + ", isreportenter="
				+ isreportenter + ", reportLocationFlag=" + reportLocationFlag + "]";
	}

}
