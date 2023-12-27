package menu_member;

import _mall.MenuCommand;
import controller.MallController;
import util.Util;

public class MemberBoard implements MenuCommand {
	private MallController cont;

	@Override
	public void init() {
		cont = MallController.getInstance();
	}

	@Override
	public boolean update() {
		System.out.println("[1] 게시글 목록");
		System.out.println("[2] 게시글 추가");
		System.out.println("[3] 게시글 삭제");
		System.out.println("[4] 뒤로가기");
		System.out.println("[0] 종료");
		int sel = Util.getValue("메뉴 입력", 0, 4);
		if (sel == 0) {
			cont.setNext("");
		} else if (sel == 1) {
			cont.setNext("MemberBoardList");
		} else if (sel == 2) {
			cont.setNext("MemberBoardAdd");
		} else if (sel == 3) {
			cont.setNext("MemberBoardDelete");
		} else if (sel == 4) {
			cont.setNext("MemberMain");
		}
		return false;
	}
}
