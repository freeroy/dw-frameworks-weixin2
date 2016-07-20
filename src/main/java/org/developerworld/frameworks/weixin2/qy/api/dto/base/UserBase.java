package org.developerworld.frameworks.weixin2.qy.api.dto.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.developerworld.frameworks.weixin2.qy.api.dto.enums.UserGender;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 用户信息详情
 * 
 * @author Roy Huang
 *
 */
public abstract class UserBase extends UserSimpleBase {

	/**
	 * 扩展属性序列化
	 * 
	 * @author Roy Huang
	 *
	 */
	public static class ExtattrSerialize extends JsonSerializer<Map<String, String>> {

		@Override
		public void serialize(Map<String, String> value, JsonGenerator gen, SerializerProvider serializers)
				throws IOException, JsonProcessingException {
			if (value != null) {
				Map<String, List<Map<String, String>>> attrMap = new HashMap<String, List<Map<String, String>>>();
				List<Map<String, String>> attrList = new ArrayList<Map<String, String>>();
				Iterator<Entry<String, String>> iterator = value.entrySet().iterator();
				while (iterator.hasNext()) {
					Entry<String, String> entry = iterator.next();
					Map<String, String> attr = new HashMap<String, String>();
					attr.put("name", entry.getKey());
					attr.put("value", entry.getValue());
					attrList.add(attr);
				}
				attrMap.put("attrs", attrList);
				gen.writeObject(attrMap);
			}
		}

	}

	public static class ExtattrDeserializer extends JsonDeserializer<Map<String, String>> {

		@Override
		public Map<String, String> deserialize(JsonParser p, DeserializationContext ctxt)
				throws IOException, JsonProcessingException {
			Map<String, String> rst = null;
			TreeNode root = p.getCodec().readTree(p);
			if (root != null && root.get("attrs") != null && root.get("attrs").isArray()) {
				rst = new HashMap<String, String>();
				TreeNode attrs = root.get("attrs");
				for (int i = 0; i < attrs.size(); i++)
					rst.put(attrs.get(i).get("name").toString(), attrs.get(i).get("value").toString());
			}
			return rst;
		}

	}

	protected String position;
	protected String mobile;
	protected UserGender gender;
	protected String email;
	protected String weixinid;
	@JsonSerialize(using = ExtattrSerialize.class)
	@JsonDeserialize(using = ExtattrDeserializer.class)
	protected Map<String, String> extattr;

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public UserGender getGender() {
		return gender;
	}

	public void setGender(UserGender gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWeixinid() {
		return weixinid;
	}

	public void setWeixinid(String weixinid) {
		this.weixinid = weixinid;
	}

	public Map<String, String> getExtattr() {
		return extattr;
	}

	public void setExtattr(Map<String, String> extattr) {
		this.extattr = extattr;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((extattr == null) ? 0 : extattr.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((weixinid == null) ? 0 : weixinid.hashCode());
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
		UserBase other = (UserBase) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (extattr == null) {
			if (other.extattr != null)
				return false;
		} else if (!extattr.equals(other.extattr))
			return false;
		if (gender != other.gender)
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (weixinid == null) {
			if (other.weixinid != null)
				return false;
		} else if (!weixinid.equals(other.weixinid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserBase [position=" + position + ", mobile=" + mobile + ", gender=" + gender + ", email=" + email
				+ ", weixinid=" + weixinid + ", extattr=" + extattr + ", userid=" + userid + ", name=" + name
				+ ", department=" + department + "]";
	}

}
