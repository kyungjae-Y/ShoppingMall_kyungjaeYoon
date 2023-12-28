package controller;

import java.util.HashMap;
import java.util.Map;

import _mall.MenuCommand;
import dao.FileDAO;
import files.AdminFileSave;
import menu_admin.*;
import menu_admin_item.*;
import menu_admin_member.*;
import menu_board.*;
import menu_mall.*;
import menu_member.*;
import menu_member_info.*;

public class MallController {

	private static MallController instance = new MallController();
	private Map<String, MenuCommand> mcMap;
	private String id;
	private String next;
	private MenuCommand mc;

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
		mcMap = new HashMap<>();
		mcMap.put("MallMain", new _MallMain());
		mcMap.put("MallJoin", new MallJoin());
		mcMap.put("MallLogin", new MallLogin());
		mcMap.put("AdminMain", new _AdminMain());
		mcMap.put("AdminMember", new AdminMember());
		mcMap.put("AdminMemberList", new AdminMemberList());
		mcMap.put("AdminMemberDelete", new AdminMemberDelete());
		mcMap.put("AdminItem", new AdminItem());
		mcMap.put("AdminItemAdd", new AdminItemAdd());
		mcMap.put("AdminItemDelete", new AdminItemDelete());
		mcMap.put("AdminItemRevenue", new AdminItemRevenue());
		mcMap.put("AdminBoard", new AdminBoard());
		mcMap.put("BoardList", new BoardList());
		mcMap.put("BoardDeletePage", new BoardDeletePage());
		mcMap.put("BoardBeforePage", new BoardBeforePage());
		mcMap.put("BoardAfterPage", new BoardAfterPage());
		mcMap.put("BoardViewPage", new BoardViewPage());
		mcMap.put("BoardAddPage", new BoardAddPage());
		mcMap.put("AdminFileSave", new AdminFileSave());
		mcMap.put("MemberMain", new _MemberMain());
		mcMap.put("MemberShopping", new MemberShopping());
		mcMap.put("MemberCart", new MemberCart());
		mcMap.put("MemberBoard", new MemberBoard());
		mcMap.put("MemberInfo", new MemberInfo());
		mcMap.put("MemberInfoPwUpdate", new MemberInfoPwUpdate());
		mcMap.put("MemberQuit", new MemberQuit());
		mc = mcMap.get("MallMain");
		FileDAO.getInstance();
		update();
	}

	public void update() {
		while (true) {
			if (!mc.update()) {
				if (!next.equals("")) {
					mc = mcMap.get(next);
				} else {
					return;
				}
			}
		}
	}
}