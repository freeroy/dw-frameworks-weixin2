package org.developerworld.frameworks.weixin2.commons.api.dto;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * 单一包裹数组对象反序列化为集合
 * 
 * @author Roy Huang
 *
 */
public class SingleObjectMapToObjectDeserializer extends JsonDeserializer<Object> {

	@Override
	public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		Object rst = null;
		TreeNode root = p.getCodec().readTree(p);
		if (root.isObject() && root.size() > 0) {
			String nodeName = root.fieldNames().next();
			rst = root.get(nodeName);
		}
		return rst;
	}

}
