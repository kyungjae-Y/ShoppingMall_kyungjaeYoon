package controller;

import java.util.HashMap;
import java.util.Map;

import _mall.MenuCommand;
import dao.FileDAO;
import dao.MemberDAO;
import menu_admin.*;
import menu_mall.*;
import menu_member.*;

public class MallController {
	private static MallController instance = new MallController();

	public static MallController getInstance() {
		return instance;
	}

	private MemberDAO mDAO;
	private String loginId;
	private String next;
	private MenuCommand menuCom;
	public Map<String, MenuCommand> mapCont;

	public MemberDAO getmDAO() {
		return mDAO;
	}

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public void init() {
		mDAO = new MemberDAO();
		FileDAO.getInstance().FileLoad();
		mapCont = new HashMap<>();
		mapCont.put("MallMain", new _MallMain());
		mapCont.put("MallJoin", new MallJoin());
		mapCont.put("MallLogin", new MallLogin());
		mapCont.put("AdminMain", new _AdminMain());
		mapCont.put("AdminBoard", new AdminBoard());
		mapCont.put("AdminItem", new AdminItem());
		mapCont.put("AdminMember", new AdminMember());
		mapCont.put("MemberMain", new _MemberMain());
		mapCont.put("MemberBoard", new MemberBoard());
		mapCont.put("MemberCart", new MemberCart());
		mapCont.put("MemberInfo", new MemberInfo());
		mapCont.put("MemberItem", new MemberItem());
		mapCont.put("MemberQuit", new MemberQuit());
		mapCont.put("MemberShopping", new MemberShopping());
		menuCom = mapCont.get("MallMain");
		menuCom.init();
		update();
	}

	public void update() {
		while (true) {
			if (!menuCom.update()) {
				if (next != null) {
					menuCom = mapCont.get(next);
					menuCom.init();
				} else {
					return;
				}
			}
		}
	}
}
