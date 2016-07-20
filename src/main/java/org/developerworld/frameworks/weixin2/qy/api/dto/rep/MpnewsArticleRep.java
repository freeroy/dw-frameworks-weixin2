package org.developerworld.frameworks.weixin2.qy.api.dto.rep;

import org.developerworld.frameworks.weixin2.qy.api.dto.base.MpnewsArticleBase;

/**
 * 图文对象
 * 
 * @author Roy Huang
 *
 */
public class MpnewsArticleRep extends MpnewsArticleBase {

	@Override
	public String toString() {
		return "MpnewsArticleRep [title=" + title + ", thumbMediaId=" + thumbMediaId + ", author=" + author
				+ ", contentSourceUrl=" + contentSourceUrl + ", content=" + content + ", digest=" + digest
				+ ", showCoverPic=" + showCoverPic + "]";
	}

}
