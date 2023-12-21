package menu_mall;

import _mall.MenuCommand;
import controller.MallController;
import util.Util;

public class _MainMall implements MenuCommand {
	private MallController mallCont;

	@Override
	public void init() {
		mallCont = MallController.getInstance();
	}

	public boolean update() {
		System.out.println("=== 쇼핑몰 ===");
		System.out.println("[1] 회원 가입");
		System.out.println("[2] 로그인");
		System.out.println("[0] 종료");
		int sel = Util.getValue("메뉴 입력", 0, 2);
		if (sel == 0) {
			System.out.println("프로그램 종료");
			return false;
		}
		if (sel == 1) {
			mallCont.setNext("MallJoin");
		} else if (sel == 2) {
			mallCont.setNext("MallLogin");
		}
		return false;
	}
}
