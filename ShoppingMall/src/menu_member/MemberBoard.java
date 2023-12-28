package menu_member;

import _mall.MenuCommand;
import controller.MallController;
import dao.BoardDAO;
import util.Util;

public class MemberBoard implements MenuCommand {
	private MallController cont = MallController.getInstance();

	@Override
	public boolean update() {
		BoardDAO bDAO = BoardDAO.getInstance();
		cont.setNext("MemberBoard");
		bDAO.PageCalculate();
		bDAO.PrintBoard();
		System.out.println("[1] 게시글 목록");
		System.out.println("[2] 게시글 추가");
		System.out.println("[3] 게시글 삭제");
		System.out.println("[4] 뒤로 가기");
		System.out.println("[0] 종료");
		int sel = Util.getValue("선택", 0, 6);
		if (sel == 1) {
			cont.setNext("BoardList");
		} else if (sel == 2) {
			cont.setNext("BoardAddPage");
		} else if (sel == 3) {
			cont.setNext("BoardDeletePage");
		} else if (sel == 4) {
			cont.setNext("MemberMain");
		} else if (sel == 0) {
			cont.setNext("");
		}
		return false;
	}
}