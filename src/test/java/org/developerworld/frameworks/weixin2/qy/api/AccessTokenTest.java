package org.developerworld.frameworks.weixin2.qy.api;

import org.developerworld.frameworks.weixin2.commons.api.ApiException;
import org.developerworld.frameworks.weixin2.commons.api.ApiResponse;
import org.developerworld.frameworks.weixin2.qy.api.AccessTokenApi;
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.AccessTokenRep;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
@Ignore
public class AccessTokenTest extends AbstractTestSupport {

	@Test
	public void testGetAccessTokenStringString() throws ApiException{
		ApiResponse<AccessTokenRep> response=AccessTokenApi.getAccessToken(testCorpID,testSecret);
		if(response.isError())
			System.out.println("调用出错:"+response.getResponseException());
		else
			System.out.println("调用成功:"+response.getResponseObject());
		Assert.assertTrue(true);
	}
}
