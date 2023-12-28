package menu_admin;

import _mall.MenuCommand;
import controller.MallController;
import util.Util;

public class AdminBoard implements MenuCommand {
	private MallController cont = MallController.getInstance();

	@Override
	public boolean update() {
		System.out.println("===== [ 관리자 게시판 관리 ] =====");
		System.out.println("[1] 게시글 목록");
		System.out.println("[2] 게시글 삭제");
		System.out.println("[3] 뒤로가기");
		System.out.println("[0] 종료");
		int sel = Util.getValue("메뉴 입력", 0, 3);
		if (sel == 0) {
			cont.setNext("");
		} else if (sel == 1) {
			cont.setNext("AdminBoardList");
		} else if (sel == 2) {
			cont.setNext("AdminBoardDelete");
		} else if (sel == 3) {
			cont.setNext("AdminMain");
		}
		return false;
	}
}
