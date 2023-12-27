package controller;

import java.util.HashMap;
import java.util.Map;

import _mall.MenuCommand;
import dao.*;
import menu_admin.*;
import menu_mall.*;
import menu_member.*;
import menu_member_cart.*;

public class MallController {
	private static MallController instance = new MallController();

	public static MallController getInstance() {
		return instance;
	}

	private BoardDAO bDAO;
	private CartDAO cDAO;
	private ItemDAO iDAO;
	private MemberDAO mDAO;
	private CategoryDAO cgDAO;
	private String next;
	private String id;
	private MenuCommand menuCom;
	public Map<String, MenuCommand> mapCont;

	public MemberDAO getmDAO() {
		return mDAO;
	}

	public CategoryDAO getCgDAO() {
		return cgDAO;
	}

	public ItemDAO getiDAO() {
		return iDAO;
	}

	public BoardDAO getbDAO() {
		return bDAO;
	}

	public CartDAO getcDAO() {
		return cDAO;
	}

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void init() {
		bDAO = new BoardDAO();
		cDAO = new CartDAO();
		iDAO = new ItemDAO();
		mDAO = new MemberDAO();
		cgDAO = new CategoryDAO();
//		FileDAO.getInstance().loadFile();
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
		mapCont.put("MemberCartBuy", new MemberCartBuy());
		mapCont.put("MemberInfo", new MemberInfo());
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
