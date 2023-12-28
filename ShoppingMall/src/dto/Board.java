package dto;

import java.time.LocalDate;

public class Board {
	private static int num;
	private int boardNum;
	private String title;
	private String contents;
	private String id;
	private String date;
	private int hits;

	public Board() {
	}

	public static void setNum(int num) {
		Board.num = num;
	}

	public int getBoardNum() {
		return boardNum;
	}

	public String getTitle() {
		return title;
	}

	public String getId() {
		return id;
	}

	public String getDate() {
		return date;
	}

	public String getContents() {
		return contents;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public Board(String title, String contents, String id) {
		this.boardNum = ++num;
		this.title = title;
		this.contents = contents;
		this.id = id;
		this.date = LocalDate.now().toString();
		this.hits = 0;
	}

	private Board(String boardNum, String title, String id, String date, String contents, String hits) {
		this.boardNum = Integer.parseInt(boardNum);
		this.title = title;
		this.id = id;
		this.date = date;
		this.contents = contents;
		this.hits = Integer.parseInt(hits);
	}

	public Board CreateBoard(String[] info) {
		if (info == null || info.length == 0)
			return null;

		return new Board(info[0], info[1], info[2], info[3], info[4], info[5]);
	}

	public void PrintBoard() {
		System.out.println("[ 제목 : %s\t작성자 : %s\t날짜 : %s\t조회수 : %d".formatted(title, id, date, hits));
	}

	public String DataToFile() {
		return "%d/%s/%s/%s/%s/%d".formatted(boardNum, title, id, date, contents, hits);
	}

//	게시글 보여주기
	public void ViewPage() {
		System.out.printf("[ 제목 : %10s 작성자 : %10s 날짜 : %11s 조회수 : %d]\n", title, id, date, hits);
		System.out.println("---------------------------------------------");
		System.out.println(contents);
	}
}