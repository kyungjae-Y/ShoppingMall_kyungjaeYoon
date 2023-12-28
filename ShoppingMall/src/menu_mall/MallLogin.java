package menu_mall;

import _mall.MenuCommand;
import controller.MallController;
import dao.MemberDAO;
import util.Util;

public class MallLogin implements MenuCommand {
	private MallController cont = MallController.getInstance();

	@Override
	public boolean update() {
		MemberDAO mDAO = MemberDAO.getInstance();
		cont.setNext("MallMain");
		System.out.println("===== [ 로그인 ] =====");
		String id = Util.getValue("ID : ");
		int idIdx = mDAO.idValue(id);
		if (idIdx == -1) {
			System.out.println("ID가 존재하지 않습니다");
			return false;
		}
		String pw = Util.getValue("PW : ");
		int pwIdx = mDAO.pwValue(pw);
		if (idIdx != pwIdx) {
			System.out.println("비밀번호가 틀렸습니다");
			return false;
		}
		System.out.println("로그인 성공");
		cont.setId(id);
		if (id.equals("admin")) {
			cont.setNext("AdminMain");
		} else {
			cont.setNext("MemberMain");
		}
		return false;
	}
}