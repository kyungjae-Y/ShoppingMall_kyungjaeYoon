package controller;

import java.util.HashMap;
import java.util.Map;

import _mall.MenuCommand;
import dao.*;
import menu_admin.*;
import menu_mall.*;
import menu_member.*;

public class MallController {
	private static MallController instance = new MallController();
	private Map<String, MenuCommand> mapCont;
	private String next;
	private String id;
	private MenuCommand menuCom;

	public static MallController getInstance() {
		return instance;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public void init() {
		id = "";
		next = "";
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
		mapCont.put("MemberQuit", new MemberQuit());
		mapCont.put("MemberShopping", new MemberShopping());
		menuCom = mapCont.get("MallMain");
		FileDAO.getInstance();
		update();
	}

	public void update() {
		while (true) {
			if (!menuCom.update()) {
				if (!next.equals("")) {
					menuCom = mapCont.get(next);
				} else {
					return;
				}
			}
		}
	}
}
