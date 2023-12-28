package dao;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileDAO {
	private String txtPath = "src/files/";
	private Charset charSet = StandardCharsets.UTF_8;

	enum FileName {
		BOARD("board.txt"), MEMBER("member.txt"), ITEM("item.txt"), CART("cart.txt");

		private String name;

		FileName(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	public FileDAO() {
		init();
	}

	private static FileDAO instance = new FileDAO();

	static public FileDAO getInstance() {
		return instance;
	}

//	텍스트 파일이 없으면 만들기
	private static void createFile(FileName name) {
		Path path = Paths.get("src/files/" + name.getName());
		try {
			Files.createFile(path);
		} catch (IOException e) {
			// System.out.println("파일이 이미 있음");
		}
	}

//	메인 실행할때 컨트롤러 안에서 불러오면 파일 로드함
	private void init() {
		createFile(FileName.BOARD);
		createFile(FileName.MEMBER);
		createFile(FileName.ITEM);
		createFile(FileName.CART);
		try {
			List<String> bData = FileLoad(FileName.BOARD);
			BoardDAO.FileToData(bData);
			List<String> mData = FileLoad(FileName.MEMBER);
			MemberDAO.FileToData(mData);
			List<String> iData = FileLoad(FileName.ITEM);
			ItemDAO.FileToData(iData);
			List<String> cData = FileLoad(FileName.CART);
			CartDAO.FileToData(cData);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	이걸로 파일 전체 불러올 수 있음
	private List<String> FileLoad(FileName name) throws IOException {
		return Files.readAllLines(Paths.get(txtPath + name.getName()));
	}

	public void FileSave(String txtName, String data) throws IOException {
		Files.writeString(Paths.get(txtPath + txtName), data, charSet);
	}
}