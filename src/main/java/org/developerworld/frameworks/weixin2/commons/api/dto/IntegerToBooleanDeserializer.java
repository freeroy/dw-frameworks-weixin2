package org.developerworld.frameworks.weixin2.commons.api.dto;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * int转boolean反序列化
 * 
 * @author Roy Huang
 *
 */
public class IntegerToBooleanDeserializer extends JsonDeserializer<Boolean> {

	@Override
	public Boolean deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		return p.getValueAsInt() == 0 ? false : true;
	}

}
