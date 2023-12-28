package menu_mall;

import _mall.MenuCommand;
import controller.MallController;
import util.Util;

public class _MallMain implements MenuCommand {
	private MallController cont = MallController.getInstance();

	@Override
	public boolean update() {
		cont.setNext("MallMain");
		System.out.println("===[ 쇼핑몰 ]===");
		System.out.println("[1] 회원가입");
		System.out.println("[2] 로그인");
		System.out.println("[0] 종료");
		int sel = Util.getValue("메뉴 입력", 0, 2);
		if (sel == 1) {
			cont.setNext("MallJoin");
		} else if (sel == 2) {
			cont.setNext("MallLogin");
		} else if (sel == 0) {
			cont.setNext("");
		}
		return false;
	}
}