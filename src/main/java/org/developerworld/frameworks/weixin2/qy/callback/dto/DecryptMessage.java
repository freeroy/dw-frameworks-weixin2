package org.developerworld.frameworks.weixin2.qy.callback.dto;

/**
 * 解密消息对象
 * @author Roy Huang
 *
 */
public class DecryptMessage {

	private String encodingAESKey;
	private String encryptMessage;
	private String random;
	private Integer msgLen;
	private String msg;
	private String corpID;

	public DecryptMessage(String encodingAESKey, String encryptMessage, String random, int msgLen, String msg,
			String corpID) {
		this.encodingAESKey = encodingAESKey;
		this.encryptMessage = encryptMessage;
		this.random = random;
		this.msgLen = msgLen;
		this.msg = msg;
		this.corpID = corpID;
	}

	public void setEncodingAESKey(String encodingAESKey) {
		this.encodingAESKey = encodingAESKey;
	}

	public void setEncryptMessage(String encryptMessage) {
		this.encryptMessage = encryptMessage;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public void setMsgLen(Integer msgLen) {
		this.msgLen = msgLen;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setCorpID(String corpID) {
		this.corpID = corpID;
	}

	public String getEncodingAESKey() {
		return encodingAESKey;
	}

	public String getEncryptMessage() {
		return encryptMessage;
	}

	public String getRandom() {
		return random;
	}

	public Integer getMsgLen() {
		return msgLen;
	}

	public String getMsg() {
		return msg;
	}

	public String getCorpID() {
		return corpID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((corpID == null) ? 0 : corpID.hashCode());
		result = prime * result + ((encodingAESKey == null) ? 0 : encodingAESKey.hashCode());
		result = prime * result + ((encryptMessage == null) ? 0 : encryptMessage.hashCode());
		result = prime * result + ((msg == null) ? 0 : msg.hashCode());
		result = prime * result + ((msgLen == null) ? 0 : msgLen.hashCode());
		result = prime * result + ((random == null) ? 0 : random.hashCode());
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
		DecryptMessage other = (DecryptMessage) obj;
		if (corpID == null) {
			if (other.corpID != null)
				return false;
		} else if (!corpID.equals(other.corpID))
			return false;
		if (encodingAESKey == null) {
			if (other.encodingAESKey != null)
				return false;
		} else if (!encodingAESKey.equals(other.encodingAESKey))
			return false;
		if (encryptMessage == null) {
			if (other.encryptMessage != null)
				return false;
		} else if (!encryptMessage.equals(other.encryptMessage))
			return false;
		if (msg == null) {
			if (other.msg != null)
				return false;
		} else if (!msg.equals(other.msg))
			return false;
		if (msgLen == null) {
			if (other.msgLen != null)
				return false;
		} else if (!msgLen.equals(other.msgLen))
			return false;
		if (random == null) {
			if (other.random != null)
				return false;
		} else if (!random.equals(other.random))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DecryptMessage [encodingAESKey=" + encodingAESKey + ", encryptMessage=" + encryptMessage + ", random="
				+ random + ", msgLen=" + msgLen + ", msg=" + msg + ", corpID=" + corpID + "]";
	}

}
