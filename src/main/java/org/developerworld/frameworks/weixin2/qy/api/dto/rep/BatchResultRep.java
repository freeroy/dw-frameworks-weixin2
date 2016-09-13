package org.developerworld.frameworks.weixin2.qy.api.dto.rep;

import java.util.List;

import org.developerworld.frameworks.weixin2.qy.api.dto.enums.BatchReplaceType;
import org.developerworld.frameworks.weixin2.qy.api.dto.enums.BatchResultAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * 批处理状态数据
 * 
 * @author Roy Huang
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class BatchResultRep {

	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
	public final static class Result {
		private BatchResultAction action;
		private String userid;
		private String partyid;
		private Integer errcode;
		private String errmsg;

		public BatchResultAction getAction() {
			return action;
		}

		public void setAction(BatchResultAction action) {
			this.action = action;
		}

		public String getUserid() {
			return userid;
		}

		public void setUserid(String userid) {
			this.userid = userid;
		}

		public String getPartyid() {
			return partyid;
		}

		public void setPartyid(String partyid) {
			this.partyid = partyid;
		}

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

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((action == null) ? 0 : action.hashCode());
			result = prime * result + ((errcode == null) ? 0 : errcode.hashCode());
			result = prime * result + ((errmsg == null) ? 0 : errmsg.hashCode());
			result = prime * result + ((partyid == null) ? 0 : partyid.hashCode());
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
			Result other = (Result) obj;
			if (action != other.action)
				return false;
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
			if (partyid == null) {
				if (other.partyid != null)
					return false;
			} else if (!partyid.equals(other.partyid))
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
			return "Result [action=" + action + ", userid=" + userid + ", partyid=" + partyid + ", errcode=" + errcode
					+ ", errmsg=" + errmsg + "]";
		}

	}

	private Integer status;
	private BatchReplaceType type;
	private Long total;
	private Byte percentage;
	private Long remaintime;
	private List<Result> result;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BatchReplaceType getType() {
		return type;
	}

	public void setType(BatchReplaceType type) {
		this.type = type;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Byte getPercentage() {
		return percentage;
	}

	public void setPercentage(Byte percentage) {
		this.percentage = percentage;
	}

	public Long getRemaintime() {
		return remaintime;
	}

	public void setRemaintime(Long remaintime) {
		this.remaintime = remaintime;
	}

	public List<Result> getResult() {
		return result;
	}

	public void setResult(List<Result> result) {
		this.result = result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((percentage == null) ? 0 : percentage.hashCode());
		result = prime * result + ((remaintime == null) ? 0 : remaintime.hashCode());
		result = prime * result + ((this.result == null) ? 0 : this.result.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((total == null) ? 0 : total.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		BatchResultRep other = (BatchResultRep) obj;
		if (percentage == null) {
			if (other.percentage != null)
				return false;
		} else if (!percentage.equals(other.percentage))
			return false;
		if (remaintime == null) {
			if (other.remaintime != null)
				return false;
		} else if (!remaintime.equals(other.remaintime))
			return false;
		if (result == null) {
			if (other.result != null)
				return false;
		} else if (!result.equals(other.result))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (total == null) {
			if (other.total != null)
				return false;
		} else if (!total.equals(other.total))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BatchResultRep [status=" + status + ", type=" + type + ", total=" + total + ", percentage=" + percentage
				+ ", remaintime=" + remaintime + ", result=" + result + "]";
	}

}
