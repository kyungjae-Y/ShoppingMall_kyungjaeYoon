package menu_admin;

import _mall.MenuCommand;
import controller.MallController;
import util.Util;

public class AdminItem implements MenuCommand {
	private MallController cont = MallController.getInstance();

	@Override
	public boolean update() {
		cont.setNext("AdminItem");
		System.out.println("===== [ 관리자 : 아이템 관리] =====");
		System.out.println("[1] 아이템 추가");
		System.out.println("[2] 아이템 삭제");
		System.out.println("[3] 총 매출");
		System.out.println("[4] 뒤로 가기");
		System.out.println("[0] 종료");
		int sel = Util.getValue("메뉴 입력", 0, 4);
		if (sel == 1) {
			cont.setNext("AdminItemAdd");
		} else if (sel == 2) {
			cont.setNext("AdminItemDelete");
		} else if (sel == 3) {
			cont.setNext("AdminItemRevenue");
		} else if (sel == 4) {
			cont.setNext("AdminMain");
		} else if (sel == 0) {
			cont.setNext("");
		}
		return false;
	}
}