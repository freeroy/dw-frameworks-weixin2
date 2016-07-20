package org.developerworld.frameworks.weixin2.qy.api.dto.req;

import org.developerworld.frameworks.weixin2.qy.api.dto.base.MpnewsArticleBase;

/**
 * 图文对象
 * 
 * @author Roy Huang
 *
 */
public class MpnewsArticleReq extends MpnewsArticleBase{

	@Override
	public String toString() {
		return "MpnewsArticleReq [title=" + title + ", thumbMediaId=" + thumbMediaId + ", author=" + author
				+ ", contentSourceUrl=" + contentSourceUrl + ", content=" + content + ", digest=" + digest
				+ ", showCoverPic=" + showCoverPic + "]";
	}
	
}
