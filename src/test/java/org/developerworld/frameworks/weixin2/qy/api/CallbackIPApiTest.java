package org.developerworld.frameworks.weixin2.qy.api;

import java.util.List;

import org.developerworld.frameworks.weixin2.commons.api.ApiResponse;
import org.developerworld.frameworks.weixin2.qy.api.CallbackIPApi;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * CallbackIPApi 测试
 * @author Roy Huang
 *
 */
@Ignore
public class CallbackIPApiTest extends AbstractTestSupport {

	@Test
	public void testGetCallbackIPString() {
		ApiResponse<List<String>> response = CallbackIPApi.getCallbackIP(accessToken);
		if (response.isError())
			System.out.println("调用出错:" + response.getResponseException());
		else
			System.out.println("调用成功:" + response.getResponseObject());
		Assert.assertTrue(true);
	}

}
