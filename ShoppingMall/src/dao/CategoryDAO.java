package dao;

import java.util.ArrayList;

public class CategoryDAO {
	private ArrayList<String> cgList;

	public CategoryDAO() {
		cgList = new ArrayList<String>();
	}

	public ArrayList<String> getCgList() {
		return cgList;
	}

	public String getCategoryName(int cgIdx) {
		return cgList.get(cgIdx);
	}

//	카테고리 중복
	public int nameValue(String name) {
		for (int i = 0; i < cgList.size(); i += 1) {
			if (cgList.get(i).equals(name)) {
				return i;
			}
		}
		return -1;
	}

//	카테고리 추가
	public void AddCategory(String name) {
		cgList.add(name);
	}

//	카테고리 수정
	public void UpdateCategory(int idx, String name) {
		cgList.set(idx, name);
	}

//	카테고리 삭제
	public void DeleteCategory(int cgIdx) {
		cgList.remove(cgIdx);
	}

//	카테고리 목록
	public void CategoryList() {
		if (cgList.size() == 0) {
			System.out.println("[관리자] 카테고리가 존재하지 않습니다");
			return;
		}
		System.out.println("[카테고리 목록]");
		for (int i = 0; i < cgList.size(); i += 1) {
			System.out.println("[" + (i + 1) + "]" + cgList.get(i));
		}
	}
}