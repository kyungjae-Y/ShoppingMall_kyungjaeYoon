package menu_mall;

import _mall.MenuCommand;
import controller.MallController;
import dao.MemberDAO;
import util.Util;

public class MallLogin implements MenuCommand {
	private MallController cont;

	@Override
	public void init() {
		System.out.println("===== [ 로그인 ] =====");
		cont = MallController.getInstance();
	}

	@Override
	public boolean update() {
		String id = Util.getValue("아이디");
		int idIdx = cont.getmDAO().idValue(id);
		if (idIdx == -1) {
			System.out.println("ID가 존재하지 않습니다");
			return false;
		}
		String pw = Util.getValue("패스워드");
		int pwIdx = cont.getmDAO().pwValue(pw);
		if (idIdx != pwIdx) {
			System.out.println("비밀번호가 틀렸습니다");
			return false;
		}
		System.out.println("로그인 성공");
		cont.setLoginId(id);
		if (idIdx == 0) {
			cont.setNext("AdminMain");
		}
		if (idIdx > 0) {
			cont.setNext("MemberMain");
		}
		return false;
	}
}