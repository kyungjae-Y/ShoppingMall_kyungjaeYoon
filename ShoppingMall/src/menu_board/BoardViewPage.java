package menu_board;

import _mall.MenuCommand;
import controller.MallController;
import dao.BoardDAO;
import util.Util;

public class BoardViewPage implements MenuCommand {
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
			System.out.println("[%s] 게시글이 존재하지 않습니다".formatted(id));
			return false;
		}
		bDAO.PageCalculate();
		bDAO.PrintBoard();
		int startRow = bDAO.getStartRow();
		int endRow = bDAO.getEndRow();
		int idx = Util.getValue("선택", startRow + 1, endRow);
		if (idx == -1) {
			System.out.println("[%s] 해당 페이지 글만 선택 가능합니다".formatted(id));
			return false;
		}
		idx -= 1;
		bDAO.BoardViewPage(idx);
		int getHits = bDAO.getbList().get(idx).getHits();
		bDAO.getbList().get(idx).setHits(getHits + 1);
		return false;
	}
}