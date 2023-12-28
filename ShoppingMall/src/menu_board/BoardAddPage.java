package menu_board;

import _mall.MenuCommand;
import controller.MallController;
import dao.BoardDAO;
import dto.Board;
import util.Util;

public class BoardAddPage implements MenuCommand {
	private MallController cont = MallController.getInstance();

	@Override
	public boolean update() {
		BoardDAO bDAO = BoardDAO.getInstance();
		cont.setNext("MemberBoard");
		System.out.println("===== [%s 글쓰기] =====".formatted(cont.getId()));
		String title = Util.strValue("제목 : ");
		String id = cont.getId();
		String contents = Util.strValue("내용 : ");
		bDAO.getbList().add(new Board(title, contents, id));
		System.out.println("[%s] 글쓰기 완료".formatted(cont.getId()));
		return false;
	}
}