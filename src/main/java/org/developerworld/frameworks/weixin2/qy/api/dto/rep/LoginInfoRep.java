package org.developerworld.frameworks.weixin2.qy.api.dto.rep;

import java.util.List;

import org.developerworld.frameworks.weixin2.qy.api.dto.enums.AuthType;
import org.developerworld.frameworks.weixin2.qy.api.dto.enums.UserType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * 成员登录授权信息
 * 
 * @author Roy Huang
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class LoginInfoRep {

	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
	public static class UserInfo {
		private String userid;
		private String email;
		private String name;
		private String avatar;

		public String getUserid() {
			return userid;
		}

		public void setUserid(String userid) {
			this.userid = userid;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAvatar() {
			return avatar;
		}

		public void setAvatar(String avatar) {
			this.avatar = avatar;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((avatar == null) ? 0 : avatar.hashCode());
			result = prime * result + ((email == null) ? 0 : email.hashCode());
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
			UserInfo other = (UserInfo) obj;
			if (avatar == null) {
				if (other.avatar != null)
					return false;
			} else if (!avatar.equals(other.avatar))
				return false;
			if (email == null) {
				if (other.email != null)
					return false;
			} else if (!email.equals(other.email))
				return false;
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
			return "UserInfo [userid=" + userid + ", email=" + email + ", name=" + name + ", avatar=" + avatar + "]";
		}

	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
	public static class CorpInfo {
		private String corpid;

		public String getCorpid() {
			return corpid;
		}

		public void setCorpid(String corpid) {
			this.corpid = corpid;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((corpid == null) ? 0 : corpid.hashCode());
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
			CorpInfo other = (CorpInfo) obj;
			if (corpid == null) {
				if (other.corpid != null)
					return false;
			} else if (!corpid.equals(other.corpid))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "CorpInfo [corpid=" + corpid + "]";
		}

	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
	public static class Agent {
		private Integer agentid;
		private AuthType authType;

		public Integer getAgentid() {
			return agentid;
		}

		public void setAgentid(Integer agentid) {
			this.agentid = agentid;
		}

		public AuthType getAuthType() {
			return authType;
		}

		public void setAuthType(AuthType authType) {
			this.authType = authType;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((agentid == null) ? 0 : agentid.hashCode());
			result = prime * result + ((authType == null) ? 0 : authType.hashCode());
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
			Agent other = (Agent) obj;
			if (agentid == null) {
				if (other.agentid != null)
					return false;
			} else if (!agentid.equals(other.agentid))
				return false;
			if (authType != other.authType)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Agent [agentid=" + agentid + ", authType=" + authType + "]";
		}

	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
	public static class AuthInfo {

		@JsonIgnoreProperties(ignoreUnknown = true)
		@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
		public static class Department {
			private Integer id;
			private String writable;

			public Integer getId() {
				return id;
			}

			public void setId(Integer id) {
				this.id = id;
			}

			public String getWritable() {
				return writable;
			}

			public void setWritable(String writable) {
				this.writable = writable;
			}

			@Override
			public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime * result + ((id == null) ? 0 : id.hashCode());
				result = prime * result + ((writable == null) ? 0 : writable.hashCode());
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
				Department other = (Department) obj;
				if (id == null) {
					if (other.id != null)
						return false;
				} else if (!id.equals(other.id))
					return false;
				if (writable == null) {
					if (other.writable != null)
						return false;
				} else if (!writable.equals(other.writable))
					return false;
				return true;
			}

			@Override
			public String toString() {
				return "Department [id=" + id + ", writable=" + writable + "]";
			}

		}

		private List<Department> department;

		public List<Department> getDepartment() {
			return department;
		}

		public void setDepartment(List<Department> department) {
			this.department = department;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((department == null) ? 0 : department.hashCode());
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
			AuthInfo other = (AuthInfo) obj;
			if (department == null) {
				if (other.department != null)
					return false;
			} else if (!department.equals(other.department))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "AuthInfo [department=" + department + "]";
		}

	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
	public static class RedirectLoginInfo {

		private String loginTicket;
		private Integer expiresIn;

		public String getLoginTicket() {
			return loginTicket;
		}

		public void setLoginTicket(String loginTicket) {
			this.loginTicket = loginTicket;
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
			result = prime * result + ((expiresIn == null) ? 0 : expiresIn.hashCode());
			result = prime * result + ((loginTicket == null) ? 0 : loginTicket.hashCode());
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
			RedirectLoginInfo other = (RedirectLoginInfo) obj;
			if (expiresIn == null) {
				if (other.expiresIn != null)
					return false;
			} else if (!expiresIn.equals(other.expiresIn))
				return false;
			if (loginTicket == null) {
				if (other.loginTicket != null)
					return false;
			} else if (!loginTicket.equals(other.loginTicket))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "RedirectLoginInfo [loginTicket=" + loginTicket + ", expiresIn=" + expiresIn + "]";
		}

	}

	private UserType usertype;
	private UserInfo userInfo;
	private CorpInfo corpInfo;
	private List<Agent> agent;
	private AuthInfo authInfo;
	private RedirectLoginInfo redirectLoginInfo;

	public UserType getUsertype() {
		return usertype;
	}

	public void setUsertype(UserType usertype) {
		this.usertype = usertype;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public List<Agent> getAgent() {
		return agent;
	}

	public void setAgent(List<Agent> agent) {
		this.agent = agent;
	}

	public CorpInfo getCorpInfo() {
		return corpInfo;
	}

	public void setCorpInfo(CorpInfo corpInfo) {
		this.corpInfo = corpInfo;
	}

	public AuthInfo getAuthInfo() {
		return authInfo;
	}

	public void setAuthInfo(AuthInfo authInfo) {
		this.authInfo = authInfo;
	}

	public RedirectLoginInfo getRedirectLoginInfo() {
		return redirectLoginInfo;
	}

	public void setRedirectLoginInfo(RedirectLoginInfo redirectLoginInfo) {
		this.redirectLoginInfo = redirectLoginInfo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agent == null) ? 0 : agent.hashCode());
		result = prime * result + ((authInfo == null) ? 0 : authInfo.hashCode());
		result = prime * result + ((corpInfo == null) ? 0 : corpInfo.hashCode());
		result = prime * result + ((redirectLoginInfo == null) ? 0 : redirectLoginInfo.hashCode());
		result = prime * result + ((userInfo == null) ? 0 : userInfo.hashCode());
		result = prime * result + ((usertype == null) ? 0 : usertype.hashCode());
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
		LoginInfoRep other = (LoginInfoRep) obj;
		if (agent == null) {
			if (other.agent != null)
				return false;
		} else if (!agent.equals(other.agent))
			return false;
		if (authInfo == null) {
			if (other.authInfo != null)
				return false;
		} else if (!authInfo.equals(other.authInfo))
			return false;
		if (corpInfo == null) {
			if (other.corpInfo != null)
				return false;
		} else if (!corpInfo.equals(other.corpInfo))
			return false;
		if (redirectLoginInfo == null) {
			if (other.redirectLoginInfo != null)
				return false;
		} else if (!redirectLoginInfo.equals(other.redirectLoginInfo))
			return false;
		if (userInfo == null) {
			if (other.userInfo != null)
				return false;
		} else if (!userInfo.equals(other.userInfo))
			return false;
		if (usertype != other.usertype)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LoginInfoRep [usertype=" + usertype + ", userInfo=" + userInfo + ", corpInfo=" + corpInfo + ", agent="
				+ agent + ", authInfo=" + authInfo + ", redirectLoginInfo=" + redirectLoginInfo + "]";
	}

}
