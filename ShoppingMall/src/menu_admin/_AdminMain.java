package menu_admin;

import _mall.MenuCommand;
import controller.MallController;
import util.Util;

public class _AdminMain implements MenuCommand {
	private MallController cont;

	@Override
	public void init() {
		cont = MallController.getInstance();
	}

	public boolean update() {
		System.out.println("[1] 회원 관리");
		System.out.println("[2] 상품 관리");
		System.out.println("[3] 게시판 관리");
		System.out.println("[0] 로그아웃");
		int sel = Util.getValue("메뉴 입력", 0, 3);
		if (sel == 0) {
			cont.setNext("MallMain");
		}
		if (sel == 1) {
			cont.setNext("AdminMember");
		} else if (sel == 2) {
			cont.setNext("AdminItem");
		} else if (sel == 3) {
			cont.setNext("AdminBoard");
		}
		return false;
	}
}
