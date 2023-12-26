package controller;

import java.util.HashMap;
import java.util.Map;

import _mall.MenuCommand;
import menu_mall.MallJoin;
import menu_mall.MallLogin;
import menu_mall._MainMall;

public class MallController {

	private MallController() {
		init();
	}

	private static MallController instance = new MallController();

	public static MallController getInstance() {
		return instance;
	}

	private Map<String, MenuCommand> mallList;

	public void init() {
		mallList = new HashMap<>();
		mallList.put("MallMain", new _MainMall());
		mallList.put("MallJoin", new MallJoin());
		mallList.put("MallLogin", new MallLogin());
	}

	public MenuCommand setNext(String key) {
		return mallList.get(key);
	}
}
