package menu_board;

import _mall.MenuCommand;
import controller.MallController;
import dao.BoardDAO;
import util.Util;

public class BoardList implements MenuCommand {
	private MallController cont = MallController.getInstance();

	@Override
	public boolean update() {
		BoardDAO bDAO = BoardDAO.getInstance();
		cont.setNext("BoardList");
		String name = cont.getId().equals("admin") ? "관리자" : cont.getId();
		if (bDAO.getbList().size() == 0) {
			System.out.printf("[%s] 게시판에 글이 없습니다\n", name);
			return false;
		}
		bDAO.PageCalculate();
		bDAO.PrintBoard();
		System.out.println("[1] 이전");
		System.out.println("[2] 이후");
		System.out.println("[3] 게시글보기");
		System.out.println("[4] 뒤로 가기");
		System.out.println("[0] 종료");
		int sel = Util.getValue("선택", 0, 4);
		if (sel == 1) {
			cont.setNext("BoardBeforePage");
		} else if (sel == 2) {
			cont.setNext("BoardAfterPage");
		} else if (sel == 3) {
			cont.setNext("BoardViewPage");
		} else if (sel == 4) {
			if (cont.getId().equals("admin")) {
				cont.setNext("AdminBoard");
			} else {
				cont.setNext("MemberBoard");
			}
		} else if (sel == 0) {
			cont.setNext("");
		}
		return false;
	}
}