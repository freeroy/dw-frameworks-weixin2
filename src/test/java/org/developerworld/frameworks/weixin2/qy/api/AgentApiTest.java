package org.developerworld.frameworks.weixin2.qy.api;

import java.util.List;

import org.developerworld.frameworks.weixin2.commons.api.ApiResponse;
import org.developerworld.frameworks.weixin2.qy.api.dto.enums.ReportLocationFlag;
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.AgentRep;
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.AgentSimpleRep;
import org.developerworld.frameworks.weixin2.qy.api.dto.req.AgentReq;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class AgentApiTest extends AbstractTestSupport {

	@Test
	public void testGetAgent() {
		ApiResponse<AgentRep> response = AgentApi.getAgent(accessToken, testAgentid);
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
		Assert.assertTrue(true);
	}

	@Test
	public void testSetAgent() {
		AgentReq agentSet = new AgentReq();
		agentSet.setAgentid(testAgentid);
		agentSet.setDescription("description");
		agentSet.setHomeUrl("homeUrl");
		agentSet.setIsreportenter(true);
		agentSet.setIsreportuser(true);
		agentSet.setLogoMediaid(buildImageMediaId());
		agentSet.setName("应用名称");
		agentSet.setRedirectDomain("http://www.baidu.com");
		agentSet.setReportLocationFlag(ReportLocationFlag.ON);
		ApiResponse<Boolean> response = AgentApi.setAgent(accessToken, agentSet);
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
		Assert.assertTrue(true);
	}

	@Test
	public void testGetAgentList() {
		ApiResponse<List<AgentSimpleRep>> response = AgentApi.getAgentList(accessToken);
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
		Assert.assertTrue(true);
	}

}
