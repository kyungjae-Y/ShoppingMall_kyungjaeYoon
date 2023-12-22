package controller;

import java.util.HashMap;
import java.util.Map;

import _mall.MenuCommand;
import menu_mall.MallJoin;
import menu_mall.MallLogin;

public class MallController {

	private MallController() {
		init();
	}

	private static MallController instance = new MallController();

	public static MallController getInstance() {
		return instance;
	}

	private Map<String, MenuCommand> mallList;

	private void init() {
		mallList = new HashMap<>();
		mallList.put("MallJoin", new MallJoin());
		mallList.put("MallLogin", new MallLogin());
	}

	public MenuCommand setNext(String key) {
		return mallList.get(key);
	}
}
