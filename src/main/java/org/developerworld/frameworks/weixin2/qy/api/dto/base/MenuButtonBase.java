package org.developerworld.frameworks.weixin2.qy.api.dto.base;

import java.util.List;

import org.developerworld.frameworks.weixin2.qy.api.dto.enums.MenuType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * 菜单对象
 * 
 * @author Roy Huang
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public abstract class MenuButtonBase<T extends MenuButtonBase<?>> {

	protected String name;
	protected MenuType type;
	protected String key;
	protected String url;
	protected List<T> subButton;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public T name(String name) {
		setName(name);
		return currObject();
	}

	public MenuType getType() {
		return type;
	}

	public void setType(MenuType type) {
		this.type = type;
	}

	public T type(MenuType type) {
		setType(type);
		return currObject();
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public T key(String key) {
		setKey(key);
		return currObject();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public T url(String url) {
		setUrl(url);
		return currObject();
	}

	public List<T> getSubButton() {
		return subButton;
	}

	public void setSubButton(List<T> subButton) {
		this.subButton = subButton;
	}

	public T subButton(List<T> subButton) {
		setSubButton(subButton);
		return currObject();
	}

	private T currObject() {
		return (T) this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((subButton == null) ? 0 : subButton.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
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
		MenuButtonBase other = (MenuButtonBase) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (subButton == null) {
			if (other.subButton != null)
				return false;
		} else if (!subButton.equals(other.subButton))
			return false;
		if (type != other.type)
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MenuButtonBase [name=" + name + ", type=" + type + ", key=" + key + ", url=" + url + ", subButton="
				+ subButton + "]";
	}

}
