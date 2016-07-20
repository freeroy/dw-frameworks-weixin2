package org.developerworld.frameworks.weixin2.commons.api.dto;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * boolean转int序列化
 * @author Roy Huang
 *
 */
public class BooleanToIntegerSerializer extends JsonSerializer<Boolean>{

	public void serialize(Boolean value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException, JsonProcessingException {
		if(value!=null)
			gen.writeObject(value?1:0);
	}
}
