package menu_mall;

import _mall.MenuCommand;
import controller.MallController;
import dao.MemberDAO;
import dto.Member;
import util.Util;

public class MallJoin implements MenuCommand {
	private MallController cont = MallController.getInstance();;

	@Override
	public boolean update() {
		MemberDAO mDAO = MemberDAO.getInstance();
		cont.setNext("MallMain");
		System.out.println("===== [ 회원 가입 ] ===");
		String id = Util.getValue("ID : ");
		int idIdx = mDAO.idValue(id);
		if (idIdx != -1) {
			System.out.println("중복 ID가 존재합니다");
			return false;
		}
		String pw = Util.getValue("PW : ");
		String name = Util.getValue("이름 : ");
		mDAO.getmList().add(new Member(id, pw, name));
		System.out.println("가입 완료");
		return false;
	}
}