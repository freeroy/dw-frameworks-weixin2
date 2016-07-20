package org.developerworld.frameworks.weixin2.qy.api;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.developerworld.frameworks.weixin2.commons.api.ApiResponse;
import org.developerworld.frameworks.weixin2.qy.api.dto.enums.MediaType;
import org.developerworld.frameworks.weixin2.qy.api.dto.enums.UserGender;
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.UploadMediaRep;
import org.developerworld.frameworks.weixin2.qy.api.dto.req.DepartmentReq;
import org.developerworld.frameworks.weixin2.qy.api.dto.req.MpnewsArticleReq;
import org.developerworld.frameworks.weixin2.qy.api.dto.req.UserReq;
import org.junit.BeforeClass;

/**
 * 测试基础支持类
 * @author Roy Huang
 *
 */
public abstract class AbstractTestSupport {
	
	protected static String testCorpID="wx1ed64fa1ddc20e56";
	protected static String testSecret="brQNEwBaXvL0GgVhsNWslXmdH3oM1P-h3ywqPd7RNYksEvh88-5FicjioDXEnnTl";
	protected static Integer testAgentid = 24;
	protected static Integer testDepartmentId=1091;
	protected static String testOpenid = "oW18Gj1G9-3ZI2JLdw9UTRkSglBc";
	protected static String testUserid="roy";
	protected static String accessToken = null;
	
	/**
	 * 获取accessToken
	 * @return
	 */
	protected static String getAccessToken(){
		return AccessTokenApi.getAccessToken(testCorpID, testSecret).getResponseObject().getAccessToken();
	}

	@BeforeClass
	public static void beforeClass() {
		accessToken = getAccessToken();
	}
	
	protected String buildTempImageMediaId(){
		File mediaFile = new File(MediaApiTest.class.getResource("/media/image.jpg").getFile());
		ApiResponse<UploadMediaRep> response=MediaApi.uploadMedia(accessToken, MediaType.IMAGE, mediaFile);
		return response.getResponseObject().getMediaId();
	}
	
	/**
	 * 创建图素材
	 * @return
	 */
	protected String buildImageMediaId(){
		File mediaFile = new File(MediaApiTest.class.getResource("/media/image.jpg").getFile());
		ApiResponse<String> response=MaterialApi.addMaterial(accessToken, testAgentid, MediaType.IMAGE, mediaFile);
		return response.getResponseObject();
	}
	

	protected String buildVideoMediaId(){
		File mediaFile = new File(MediaApiTest.class.getResource("/media/video.mp4").getFile());
		ApiResponse<String> response=MaterialApi.addMaterial(accessToken, testAgentid, MediaType.VIDEO, mediaFile);
		return response.getResponseObject();
	}
	
	protected String buildVoiceMediaId(){
		File mediaFile = new File(MediaApiTest.class.getResource("/media/voice.amr").getFile());
		ApiResponse<String> response=MaterialApi.addMaterial(accessToken, testAgentid, MediaType.VOICE, mediaFile);
		return response.getResponseObject();
	}
	
	protected String buildFileMediaId(){
		File mediaFile = new File(MediaApiTest.class.getResource("/media/voice.mp3").getFile());
		ApiResponse<String> response=MaterialApi.addMaterial(accessToken, testAgentid, MediaType.FILE, mediaFile);
		return response.getResponseObject();
	}
	
	protected String buildMpnewsMediaId(){
		ApiResponse<String> response=MaterialApi.addMpnewsMaterial(accessToken, testAgentid, buildMpnewsReq());
		return response.getResponseObject();
	}
	
	protected List<MpnewsArticleReq> buildMpnewsReq(){
		String mediaId=buildTempImageMediaId();
		List<MpnewsArticleReq> mpnews=new ArrayList<MpnewsArticleReq>();
		MpnewsArticleReq mpnewsArticleReq=new MpnewsArticleReq();
		mpnewsArticleReq.setAuthor("作者1");
		mpnewsArticleReq.setContent("内容1");
		mpnewsArticleReq.setContentSourceUrl("http://www.163.com");
		mpnewsArticleReq.setDigest("简要1");
		mpnewsArticleReq.setShowCoverPic(true);
		mpnewsArticleReq.setThumbMediaId(mediaId);
		mpnewsArticleReq.setTitle("标题1");
		mpnews.add(mpnewsArticleReq);
		mpnewsArticleReq=new MpnewsArticleReq();
		mpnewsArticleReq.setAuthor("作者2");
		mpnewsArticleReq.setContent("内容2");
		mpnewsArticleReq.setContentSourceUrl("http://www.baidu.com");
		mpnewsArticleReq.setDigest("简要2");
		mpnewsArticleReq.setShowCoverPic(true);
		mpnewsArticleReq.setThumbMediaId(mediaId);
		mpnewsArticleReq.setTitle("标题2");
		mpnews.add(mpnewsArticleReq);
		return mpnews;
	}
	
	protected DepartmentReq buildDepartmentReq(){
		//构建部门数据
		DepartmentReq d=new DepartmentReq();
		//d.setId(departmentId);
		d.setName("测试部门"+RandomStringUtils.randomAlphanumeric(4));
		d.setParentid(testDepartmentId);
		d.setOrder(1);
		return d;
	}
	
	protected Integer buildDepartment(){
		return DepartmentApi.createDepartment(accessToken, buildDepartmentReq()).getResponseObject();
	}
	
	protected UserReq buildUserReq() {
		UserReq user = new UserReq();
		user.setUserid(RandomStringUtils.randomAlphanumeric(10));
		user.setWeixinid(null);
		user.setName("name"+RandomStringUtils.randomAlphanumeric(4));
		user.setEmail(user.getName()+"@gzgi.com");
		user.setMobile(RandomStringUtils.randomNumeric(11));
		user.setGender(UserGender.MAN);
		user.setPosition("position");
		user.setDepartment(Arrays.asList(new Integer[] { testDepartmentId }));
		user.setAvatarMediaid(buildTempImageMediaId());
		// userParameter.setEnable(true);
		Map<String, String> extattr = new HashMap<String, String>();
		extattr.put("爱好", "打机");
		extattr.put("兴趣", "编程");
		user.setExtattr(extattr);
		return user;
	}
	
	protected UserReq buildUser() {
		UserReq u=buildUserReq();
		UserApi.createUser(accessToken, u);
		return u;
	}
}
