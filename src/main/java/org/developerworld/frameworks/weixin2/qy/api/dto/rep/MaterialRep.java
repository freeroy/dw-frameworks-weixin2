package org.developerworld.frameworks.weixin2.qy.api.dto.rep;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.developerworld.frameworks.weixin2.qy.api.dto.enums.MediaType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * 永久素材对象
 * 
 * @author Roy Huang
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class MaterialRep {

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	public static class MpnewsDeserializer extends JsonDeserializer<List<MpnewsArticleRep>> {

		@Override
		public List<MpnewsArticleRep> deserialize(JsonParser p, DeserializationContext ctxt)
				throws IOException, JsonProcessingException {
			List<MpnewsArticleRep> rst = null;
			TreeNode root = p.getCodec().readTree(p);
			TreeNode articles = root.path("articles");
			if (articles != null && !articles.isMissingNode() && articles.isArray()) {
				rst = new ArrayList<MpnewsArticleRep>();
				for (int i = 0; i < articles.size(); i++) {
					MpnewsArticleRep mpnewsAtricleRep = OBJECT_MAPPER.readValue(articles.get(i).toString(),
							MpnewsArticleRep.class);
					rst.add(mpnewsAtricleRep);
				}
			}
			return rst;
		}

	}

	private MediaType type;
	@JsonDeserialize(using = MpnewsDeserializer.class)
	private List<MpnewsArticleRep> mpnews;
	private MediaFileRep mediaFile;

	public MediaType getType() {
		return type;
	}

	public void setType(MediaType type) {
		this.type = type;
	}

	public List<MpnewsArticleRep> getMpnews() {
		return mpnews;
	}

	public void setArticles(List<MpnewsArticleRep> mpnews) {
		this.mpnews = mpnews;
	}

	public MediaFileRep getMediaFile() {
		return mediaFile;
	}

	public void setMediaFile(MediaFileRep mediaFile) {
		this.mediaFile = mediaFile;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mpnews == null) ? 0 : mpnews.hashCode());
		result = prime * result + ((mediaFile == null) ? 0 : mediaFile.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MaterialRep other = (MaterialRep) obj;
		if (mpnews == null) {
			if (other.mpnews != null)
				return false;
		} else if (!mpnews.equals(other.mpnews))
			return false;
		if (mediaFile == null) {
			if (other.mediaFile != null)
				return false;
		} else if (!mediaFile.equals(other.mediaFile))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MaterialRep [type=" + type + ", mpnews=" + mpnews + ", mediaFile=" + mediaFile + "]";
	}

}
