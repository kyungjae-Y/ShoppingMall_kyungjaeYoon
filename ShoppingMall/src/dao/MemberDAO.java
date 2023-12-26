package dao;

import java.util.ArrayList;

import dto.Member;

public class MemberDAO {
	public ArrayList<Member> mList;
	private int memberNum = 1000;

	public ArrayList<Member> getmList() {
		return mList;
	}

	public MemberDAO() {
		mList = new ArrayList<Member>();
		String[] info = { "" + (memberNum++), "admin", "admin", "관리자" };
		mList.add(Member.CreateMember(info));
	}

	public int idValue(String id) {
		for (int i = 0; i < mList.size(); i++) {
			if (id.equals(mList.get(i).getId())) {
				return i;
			}
		}
		return -1;
	}

	public int pwValue(String pw) {
		for (int i = 0; i < mList.size(); i++) {
			if (pw.equals(mList.get(i).getPw())) {
				return i;
			}
		}
		return -1;
	}

	// 회원 가입
	public void CreateMember(String id, String pw, String name) {
		String[] info = { "" + (memberNum++), id, pw, name };
		mList.add(Member.CreateMember(info));
	}
}