package dao;

import java.util.ArrayList;

import dto.Board;

public class BoardDAO {
	public ArrayList<Board> bList;

	public ArrayList<Board> getbList() {
		return bList;
	}

	public BoardDAO() {
		bList = new ArrayList<Board>();
	}

	public void loadData(String data) {
		String[] temp = data.split("\n");
		for (int i = 0; i < temp.length; i++) {
			String[] info = temp[i].split("/");
			Board b = new Board(info[0], info[1], info[2], info[3], info[4], info[5]);
			bList.add(b);
		}
	}
}