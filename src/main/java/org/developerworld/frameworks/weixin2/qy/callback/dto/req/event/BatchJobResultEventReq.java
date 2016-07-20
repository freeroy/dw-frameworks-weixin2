package org.developerworld.frameworks.weixin2.qy.callback.dto.req.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.developerworld.frameworks.weixin2.qy.callback.dto.enums.JobType;

@XmlRootElement(name = "xml")
public class BatchJobResultEventReq extends AbstractEventReq {

	@XmlRootElement
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class BatchJob {
		
		@XmlElement(name = "JobId")
		private String jobId;
		@XmlElement(name = "JobType")
		private JobType jobType;
		@XmlElement(name = "ErrCode")
		private Integer errCode;
		@XmlElement(name = "ErrMsg")
		private String errMsg;

		public Integer getErrCode() {
			return errCode;
		}

		public void setErrCode(Integer errCode) {
			this.errCode = errCode;
		}

		public String getErrMsg() {
			return errMsg;
		}

		public void setErrMsg(String errMsg) {
			this.errMsg = errMsg;
		}

		public String getJobId() {
			return jobId;
		}

		public JobType getJobType() {
			return jobType;
		}

		public void setJobId(String jobId) {
			this.jobId = jobId;
		}

		public void setJobType(JobType jobType) {
			this.jobType = jobType;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((errCode == null) ? 0 : errCode.hashCode());
			result = prime * result + ((errMsg == null) ? 0 : errMsg.hashCode());
			result = prime * result + ((jobId == null) ? 0 : jobId.hashCode());
			result = prime * result + ((jobType == null) ? 0 : jobType.hashCode());
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
			BatchJob other = (BatchJob) obj;
			if (errCode == null) {
				if (other.errCode != null)
					return false;
			} else if (!errCode.equals(other.errCode))
				return false;
			if (errMsg == null) {
				if (other.errMsg != null)
					return false;
			} else if (!errMsg.equals(other.errMsg))
				return false;
			if (jobId == null) {
				if (other.jobId != null)
					return false;
			} else if (!jobId.equals(other.jobId))
				return false;
			if (jobType != other.jobType)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "BatchJob [jobId=" + jobId + ", jobType=" + jobType + ", errCode=" + errCode + ", errMsg=" + errMsg
					+ "]";
		}
	}

	@XmlElement(name = "BatchJob")
	private BatchJob batchJob;

	public BatchJob getBatchJob() {
		return batchJob;
	}

	public void setBatchJob(BatchJob batchJob) {
		this.batchJob = batchJob;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((batchJob == null) ? 0 : batchJob.hashCode());
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
		BatchJobResultEventReq other = (BatchJobResultEventReq) obj;
		if (batchJob == null) {
			if (other.batchJob != null)
				return false;
		} else if (!batchJob.equals(other.batchJob))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BatchJobResultEventReq [batchJob=" + batchJob + "]";
	}

}
