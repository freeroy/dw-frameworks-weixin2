/**
 * 
 */
package org.developerworld.frameworks.weixin2.qy.api;

import java.util.ArrayList;
import java.util.List;

import org.developerworld.frameworks.weixin2.commons.api.ApiResponse;
import org.developerworld.frameworks.weixin2.qy.api.dto.enums.MenuType;
import org.developerworld.frameworks.weixin2.qy.api.dto.rep.MenuButtonRep;
import org.developerworld.frameworks.weixin2.qy.api.dto.req.MenuButtonReq;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Roy Huang
 *
 */
@Ignore
public class MenuApiTest extends AbstractTestSupport {

	private List<MenuButtonReq> buildMenusReq() {
		// 创建菜单对象
		List<MenuButtonReq> rst = new ArrayList<MenuButtonReq>();
		// 一级菜单
		rst.add(new MenuButtonReq().name("普通菜单"));
		{
			List<MenuButtonReq> subButton = new ArrayList<MenuButtonReq>();
			subButton.add(new MenuButtonReq().type(MenuType.CLICK).name("菜单key").key(MenuType.CLICK.value()));
			subButton.add(new MenuButtonReq().type(MenuType.VIEW).name("跳转到网页").key(MenuType.VIEW.value())
					.url("http://www.baidu.com"));
			rst.get(0).setSubButton(subButton);
		}
		rst.add(new MenuButtonReq().name("扫码及地理"));
		{
			List<MenuButtonReq> subButton = new ArrayList<MenuButtonReq>();
			subButton.add(new MenuButtonReq().type(MenuType.LOCATION_SELECT).name("地理位置选择器")
					.key(MenuType.LOCATION_SELECT.value()));
			subButton.add(
					new MenuButtonReq().type(MenuType.SCANCODE_PUSH).name("扫码推事件").key(MenuType.SCANCODE_PUSH.value()));
			subButton.add(new MenuButtonReq().type(MenuType.SCANCODE_WAITMSG).name("扫码推事件(弹框)")
					.key(MenuType.SCANCODE_WAITMSG.value()));
			rst.get(1).setSubButton(subButton);
		}
		rst.add(new MenuButtonReq().name("相册"));
		{
			List<MenuButtonReq> subButton = new ArrayList<MenuButtonReq>();
			subButton.add(new MenuButtonReq().type(MenuType.PIC_PHOTO_OR_ALBUM).name("拍照或相册")
					.key(MenuType.PIC_PHOTO_OR_ALBUM.value()));
			subButton.add(
					new MenuButtonReq().type(MenuType.PIC_SYSPHOTO).name("系统拍照").key(MenuType.PIC_SYSPHOTO.value()));
			subButton.add(new MenuButtonReq().type(MenuType.PIC_WEIXIN).name("微信发图器").key(MenuType.PIC_WEIXIN.value()));
			rst.get(2).setSubButton(subButton);
		}
		return rst;
	}

	private List<MenuButtonReq> buildMenusReq(List<MenuButtonRep> menuReps) {
		// 重新创建原来的菜单
		List<MenuButtonReq> rst = new ArrayList<MenuButtonReq>();
		for (MenuButtonRep menuRep : menuReps) {
			MenuButtonReq menuReq = new MenuButtonReq().key(menuRep.getKey()).name(menuRep.getName())
					.type(menuRep.getType()).url(menuRep.getUrl());
			if (menuRep.getSubButton() != null && menuRep.getSubButton().size() > 0) {
				menuReq.setSubButton(new ArrayList<MenuButtonReq>());
				// 二级菜单
				for (MenuButtonRep menuRep2 : menuRep.getSubButton()) {
					MenuButtonReq menuReq2 = new MenuButtonReq().key(menuRep2.getKey()).name(menuRep2.getName())
							.type(menuRep2.getType()).url(menuRep2.getUrl());
					menuReq.getSubButton().add(menuReq2);
				}
			}
			rst.add(menuReq);
		}
		return rst;
	}

	@Test
	public void testCreateMenu() {
		ApiResponse<List<MenuButtonRep>> response = MenuApi.getMenu(accessToken, testAgentid);
		ApiResponse<Boolean> response2 = MenuApi.createMenu(accessToken, testAgentid, buildMenusReq());
		if (response2.isError())
			System.out.println("调用出错:" + response2.getResponseException());
		else
			System.out.println("调用成功:" + response2.getResponseObject());
		Assert.assertTrue(true);
		MenuApi.deleteMenu(accessToken, testAgentid);
		// 还原原来菜单
		if (!response.isError() && response.getResponseObject() != null) {
			List<MenuButtonReq> menus=buildMenusReq(response.getResponseObject());
			MenuApi.createMenu(accessToken, testAgentid, menus);
		}
	}

	@Test
	public void testDeleteMenu() {
		//获取原菜单
		ApiResponse<List<MenuButtonRep>> response = MenuApi.getMenu(accessToken, testAgentid);
		//原来无菜单则临时创建
		if(response.isError() || response.getResponseObject().size()<=0)
			MenuApi.createMenu(accessToken, testAgentid, buildMenusReq());
		ApiResponse<Boolean> response2 = MenuApi.deleteMenu(accessToken, testAgentid);
		if (response2.isError())
			System.out.println("调用出错:" + response2.getResponseException());
		else
			System.out.println("调用成功:" + response2.getResponseObject());
		//若原来有菜单，则重新创建
		if (!response.isError() && response.getResponseObject().size() > 0) {
			// 重新创建原来的菜单
			List<MenuButtonReq> newMenus = buildMenusReq(response.getResponseObject());
			MenuApi.createMenu(accessToken, testAgentid, newMenus);
		}
		Assert.assertTrue(true);
	}

	@Test
	public void testGetMenu() {
		ApiResponse<List<MenuButtonRep>> response = MenuApi.getMenu(accessToken, testAgentid);
		//原来无菜单，临时创建
		if(response.isError() || response.getResponseObject().size()<=0)
			MenuApi.createMenu(accessToken, testAgentid, buildMenusReq());
		ApiResponse<List<MenuButtonRep>> response2 = MenuApi.getMenu(accessToken, testAgentid);
		if (response2.isError())
			System.out.println("调用出错:" + response2.getResponseException());
		else
			System.out.println("调用成功:" + response2.getResponseObject());
		Assert.assertTrue(true);
		//若临时创建菜单，则删除
		if(response.isError() || response.getResponseObject().size()<=0)
			MenuApi.deleteMenu(accessToken, testAgentid);
	}

}
