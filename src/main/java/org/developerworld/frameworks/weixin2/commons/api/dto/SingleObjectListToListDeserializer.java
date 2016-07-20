package org.developerworld.frameworks.weixin2.commons.api.dto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * 单一包裹数组对象反序列化为集合
 * @author Roy Huang
 *
 */
public class SingleObjectListToListDeserializer extends JsonDeserializer<List> {

	@Override
	public List deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		List rst=null;
		TreeNode root=p.getCodec().readTree(p);
		if(root.isObject() && root.size()>0){
			String nodeName=root.fieldNames().next();
			TreeNode listNode=root.get(nodeName);
			if(listNode!=null && listNode.isArray()){
				rst=new ArrayList();
				for(int i=0;i<listNode.size();i++)
					rst.add(listNode.get(i));
			}
		}
		return rst;
	}

}
