package menu_mall;

import _mall.MenuCommand;
import controller.MallController;
import util.Util;

public class MallJoin implements MenuCommand {
	private MallController cont;

	public MallJoin() {
		init();
	}

	@Override
	public void init() {
		cont = MallController.getInstance();
	}

	@Override
	public boolean update() {
		System.out.println("===== [ 회원가입 ] =====");
		String id = Util.getValue("아이디");
		int idIdx = cont.getmDAO().idValue(id);
		if (idIdx != -1) {
			System.out.println("중복 ID 가 존재합니다");
			return false;
		}
		String pw = Util.getValue("비밀번호");
		String name = Util.getValue("이름");
		cont.getmDAO().CreateMember(id, pw, name);
		System.out.println("가입 완료");
		cont.setNext("MallMain");
		return false;
	}
}
