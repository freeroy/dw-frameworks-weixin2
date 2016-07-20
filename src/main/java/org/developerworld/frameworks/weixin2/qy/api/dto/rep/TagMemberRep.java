package org.developerworld.frameworks.weixin2.qy.api.dto.rep;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * 标签成员对象
 * @author Roy Huang
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TagMemberRep {
	
	public static class TagUser{
		private String userid;
		private String name;
		public String getUserid() {
			return userid;
		}
		public void setUserid(String userid) {
			this.userid = userid;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result + ((userid == null) ? 0 : userid.hashCode());
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
			TagUser other = (TagUser) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			if (userid == null) {
				if (other.userid != null)
					return false;
			} else if (!userid.equals(other.userid))
				return false;
			return true;
		}
		@Override
		public String toString() {
			return "TagUser [userid=" + userid + ", name=" + name + "]";
		}
		
	}

	private List<TagUser> userlist;
	
	private List<Integer> partylist;

	public List<TagUser> getUserlist() {
		return userlist;
	}

	public void setUserlist(List<TagUser> userlist) {
		this.userlist = userlist;
	}

	public List<Integer> getPartylist() {
		return partylist;
	}

	public void setPartylist(List<Integer> partylist) {
		this.partylist = partylist;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((partylist == null) ? 0 : partylist.hashCode());
		result = prime * result + ((userlist == null) ? 0 : userlist.hashCode());
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
		TagMemberRep other = (TagMemberRep) obj;
		if (partylist == null) {
			if (other.partylist != null)
				return false;
		} else if (!partylist.equals(other.partylist))
			return false;
		if (userlist == null) {
			if (other.userlist != null)
				return false;
		} else if (!userlist.equals(other.userlist))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TagMember [userlist=" + userlist + ", partylist=" + partylist + "]";
	}
	
	
}
