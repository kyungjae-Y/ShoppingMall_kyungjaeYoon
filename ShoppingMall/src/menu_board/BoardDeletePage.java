package menu_board;

import _mall.MenuCommand;
import controller.MallController;
import dao.BoardDAO;
import util.Util;

public class BoardDeletePage implements MenuCommand {
	private MallController cont = MallController.getInstance();

	@Override
	public boolean update() {
		BoardDAO bDAO = BoardDAO.getInstance();
		String id = cont.getId().equals("admin") ? "관리자" : cont.getId();
		if (cont.getId().equals("admin")) {
			cont.setNext("AdminBoard");
		} else {
			cont.setNext("MemberBoard");
		}
		if (bDAO.getbList().size() == 0) {
			System.out.printf("[%s] 게시판에 글이 없습니다\n", id);
			return false;
		}
		while (true) {
			int curPageNum = bDAO.getCurPageNum();
			bDAO.PageCalculate();
			bDAO.PrintBoard();
			System.out.println("[1] 이전");
			System.out.println("[2] 이후");
			System.out.println("[3] 삭제");
			System.out.println("[4] 뒤로가기");
			System.out.println("[0] 종료");
			int sel = Util.getValue("선택", 0, 4);
			if (sel == 1) {
				if (curPageNum == 1) {
					System.out.println("이전페이지 없음");
					continue;
				}
				bDAO.setCurPageNum(curPageNum - 1);
			} else if (sel == 2) {
				if (curPageNum == bDAO.getPageCount()) {
					System.out.println("이후페이지 없음");
					continue;
				}
				bDAO.setCurPageNum(curPageNum + 1);
			} else if (sel == 3) {
				int startRow = bDAO.getStartRow();
				int endRow = bDAO.getEndRow();
				int bIdx = Util.getValue("게시글 선택", startRow + 1, endRow);
				if (bIdx == -1) {
					continue;
				}
				bIdx -= 1;
				if (bDAO.DedetePage(bIdx, id)) {
					System.out.println("[%s] 본인 게시글만 삭제하실 수 있습니다".formatted(id));
					break;
				}
				System.out.printf("[%s] 게시글 삭제 완료\n", id);
				break;
			} else if (sel == 4) {
				break;
			} else if (sel == 0) {
				cont.setNext("");
				return false;
			}
		}
		return false;
	}
}