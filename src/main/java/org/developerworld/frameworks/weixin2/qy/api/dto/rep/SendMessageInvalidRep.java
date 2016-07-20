package org.developerworld.frameworks.weixin2.qy.api.dto.rep;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.ListUtils;
import org.developerworld.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * 消息发送失败反馈
 * 
 * @author Roy Huang
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SendMessageInvalidRep {

	private String invaliduser;
	private String invalidparty;
	private String invalidtag;

	public String getInvaliduser() {
		return invaliduser;
	}

	public void setInvaliduser(String invaliduser) {
		this.invaliduser = invaliduser;
	}

	public List<String> getInvaliduserList() {
		List<String> rst = null;
		if (StringUtils.isNotBlank(invaliduser))
			rst = Arrays.asList(invaliduser.split("\\|"));
		return rst;
	}

	public String getInvalidparty() {
		return invalidparty;
	}

	public void setInvalidparty(String invalidparty) {
		this.invalidparty = invalidparty;
	}

	public List<Integer> getInvalidpartyList() {
		List<Integer> rst = null;
		if (StringUtils.isNotBlank(invalidparty))
			rst = ListUtils.typedList(Arrays.asList(invalidparty.split("\\|")), Integer.class);
		return rst;
	}

	public String getInvalidtag() {
		return invalidtag;
	}

	public void setInvalidtag(String invalidtag) {
		this.invalidtag = invalidtag;
	}

	public List<Integer> getInvalidtagList() {
		List<Integer> rst = null;
		if (StringUtils.isNotBlank(invalidtag))
			rst = ListUtils.typedList(Arrays.asList(invalidtag.split("\\|")), Integer.class);
		return rst;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((invalidparty == null) ? 0 : invalidparty.hashCode());
		result = prime * result + ((invalidtag == null) ? 0 : invalidtag.hashCode());
		result = prime * result + ((invaliduser == null) ? 0 : invaliduser.hashCode());
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
		SendMessageInvalidRep other = (SendMessageInvalidRep) obj;
		if (invalidparty == null) {
			if (other.invalidparty != null)
				return false;
		} else if (!invalidparty.equals(other.invalidparty))
			return false;
		if (invalidtag == null) {
			if (other.invalidtag != null)
				return false;
		} else if (!invalidtag.equals(other.invalidtag))
			return false;
		if (invaliduser == null) {
			if (other.invaliduser != null)
				return false;
		} else if (!invaliduser.equals(other.invaliduser))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SendMessageInvalidRep [invaliduser=" + invaliduser + ", invalidparty=" + invalidparty + ", invalidtag="
				+ invalidtag + "]";
	}

}
