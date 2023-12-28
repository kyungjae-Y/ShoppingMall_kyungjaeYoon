package dao;

import java.util.ArrayList;
import java.util.List;

import dto.Member;

public class MemberDAO {
	private static MemberDAO instance = new MemberDAO();
	private static ArrayList<Member> mList;

	public ArrayList<Member> getmList() {
		return mList;
	}

	public static MemberDAO getInstance() {
		return instance;
	}

	public MemberDAO() {
		mList = new ArrayList<Member>();
		if (mList.size() == 0)
			AdminJoin();
	}

//	관리자 자동생성
	public void AdminJoin() {
		String[] info = { 1000 + "", "admin", "admin", "관리자" };
		mList.add(Member.CreateMember(info));
	}

//	id 중복 확인
	public int idValue(String id) {
		for (int i = 0; i < mList.size(); i += 1) {
			if (mList.get(i).getId().equals(id)) {
				return i;
			}
		}
		return -1;
	}

//	pw 중복 확인
	public int pwValue(String pw) {
		for (int i = 0; i < mList.size(); i += 1) {
			if (mList.get(i).getPw().equals(pw)) {
				return i;
			}
		}
		return -1;
	}

//	멤버 목록 출력용
	public void MemberList() {
		mList.forEach(System.out::println);
	}

//	회원 탈퇴
	public void MemberDelete(int idx) {
		mList.remove(idx);
	}

//	텍스트 파일 저장용 데이터 만들기
	public static String DataToFile() {
		String data = "";
		if (mList.size() == 0)
			return data;
		for (Member m : mList) {
			if (m.getMemberNum() != 1000) {
				data += m.DataToFile() + "\n";
			}
		}
		data = data.substring(0, data.length() - 1);
		return data;
	}

//	텍스트파일에서 문자열 받아와서 데이터 넣기
	public static void FileToData(List<String> mData) {
		if (mData.isEmpty())
			return;
		int maxMemberNum = 0;
		for (int i = 0; i < mData.size(); i += 1) {
			String[] info = mData.get(i).split("/");
			if (!info[0].equals("1000")) {
				mList.add(Member.CreateMember(info));
				if (maxMemberNum < Integer.parseInt(info[0])) {
					maxMemberNum = Integer.parseInt(info[0]);
				}
			}
		}
		Member.setNum(maxMemberNum);
	}
}