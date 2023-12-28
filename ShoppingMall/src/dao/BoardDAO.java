package dao;

import java.util.ArrayList;
import java.util.List;

import dto.Board;

public class BoardDAO {
	private static BoardDAO instance = new BoardDAO();
	private static ArrayList<Board> bList;
	private int count = 0; // 전체 게시글 수
	private int pageSize = 5; // 한 페이지에 보여줄 게시글 수
	private int curPageNum = 1; // 현재 페이지 번호
	private int pageCount = 0; // 전체 페이지 개수
	private int startRow = 0; // 현재 페이지의 게시글 시작 번호
	private int endRow = 0; // 현재 페이지의 게시글 마지막 번호

	public static BoardDAO getInstance() {
		return instance;
	}

	public int getStartRow() {
		return startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public int getCurPageNum() {
		return curPageNum;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setCurPageNum(int curPageNum) {
		this.curPageNum = curPageNum;
	}

	public BoardDAO() {
		bList = new ArrayList<Board>();
	}

	public ArrayList<Board> getbList() {
		return bList;
	}

	public void PageCalculate() {
		count = bList.size();
		pageCount = count / pageSize;
		if (count % pageSize > 0) {
			pageCount += 1;
		}
		pageCount = pageCount == 0 ? 1 : pageCount;
		startRow = (curPageNum - 1) * pageSize;
		endRow = startRow + pageSize;
		if (endRow > count)
			endRow = count;
	}

//	게시판 출력
	public void PrintBoard() {
		System.out.println("총 게시글 : %d 개".formatted(count));
		System.out.println("[%d/%d]".formatted(curPageNum, pageCount));
		for (int i = startRow; i < endRow; i += 1) {
			System.out.print("(%d)".formatted(i + 1));
			bList.get(i).PrintBoard();
		}
		System.out.println("==========");
	}

//	게시글 하나 삭제
	public boolean DedetePage(int idx, String id) {
		if (!id.equals("관리자") && !bList.get(idx).getId().equals(id)) {
			return true;
		}
		bList.remove(idx);
		return false;
	}

//	텍스트 파일 저장용 데이터 만들기
	public static String DataToFile() {
		String data = "";
		if (bList.size() == 0)
			return data;
		for (Board b : bList) {
			data += b.DataToFile() + "\n";
		}
		data = data.substring(0, data.length() - 1);
		return data;
	}

//	텍스트파일에서 문자열 받아와서 데이터 넣기
	public static void FileToData(List<String> bData) {
		if (bData.isEmpty())
			return;

		int maxBoardNum = 0;
		for (int i = 0; i < bData.size(); i += 1) {
			Board b = new Board();
			String[] info = bData.get(i).split("/");
			b = b.CreateBoard(info);
			bList.add(b);
			if (maxBoardNum < Integer.parseInt(info[0])) {
				maxBoardNum = Integer.parseInt(info[0]);
			}
		}
		Board.setNum(maxBoardNum);
	}

//	게시글을 선택했을때 내용 보여주기
	public void BoardViewPage(int idx) {
		bList.get(idx).ViewPage();
	}
}