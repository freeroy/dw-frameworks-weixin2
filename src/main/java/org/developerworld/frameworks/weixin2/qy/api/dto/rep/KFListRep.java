package org.developerworld.frameworks.weixin2.qy.api.dto.rep;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class KFListRep {

	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
	public static class KFInfo {
		private List<String> user;
		private List<Integer> party;
		private List<Integer> tag;

		public List<String> getUser() {
			return user;
		}

		public void setUser(List<String> user) {
			this.user = user;
		}

		public List<Integer> getParty() {
			return party;
		}

		public void setParty(List<Integer> party) {
			this.party = party;
		}

		public List<Integer> getTag() {
			return tag;
		}

		public void setTag(List<Integer> tag) {
			this.tag = tag;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((party == null) ? 0 : party.hashCode());
			result = prime * result + ((tag == null) ? 0 : tag.hashCode());
			result = prime * result + ((user == null) ? 0 : user.hashCode());
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
			KFInfo other = (KFInfo) obj;
			if (party == null) {
				if (other.party != null)
					return false;
			} else if (!party.equals(other.party))
				return false;
			if (tag == null) {
				if (other.tag != null)
					return false;
			} else if (!tag.equals(other.tag))
				return false;
			if (user == null) {
				if (other.user != null)
					return false;
			} else if (!user.equals(other.user))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "KFInfo [user=" + user + ", party=" + party + ", tag=" + tag + "]";
		}

	}

	private KFInfo internal;
	private KFInfo external;

	public KFInfo getInternal() {
		return internal;
	}

	public void setInternal(KFInfo internal) {
		this.internal = internal;
	}

	public KFInfo getExternal() {
		return external;
	}

	public void setExternal(KFInfo external) {
		this.external = external;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((external == null) ? 0 : external.hashCode());
		result = prime * result + ((internal == null) ? 0 : internal.hashCode());
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
		KFListRep other = (KFListRep) obj;
		if (external == null) {
			if (other.external != null)
				return false;
		} else if (!external.equals(other.external))
			return false;
		if (internal == null) {
			if (other.internal != null)
				return false;
		} else if (!internal.equals(other.internal))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "KFListRep [internal=" + internal + ", external=" + external + "]";
	}

}
