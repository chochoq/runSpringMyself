package board;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import database.SqlVO;

@WebServlet(value = { "/board/list.json", "/board/insert", "/board/read", "/board/update", "/board/delete" })
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8"); // 한글 깨짐 방지
		PrintWriter out = response.getWriter(); // 브라우저 객체
		BoardDAO bdao = new BoardDAO();
		BoardVO vo = new BoardVO();

		switch (request.getServletPath()) {

		case "/product/delete":
			// 이미지삭제를위해 이미지를 읽어온다
			BoardVO oldVO = bdao.read(Integer.parseInt(request.getParameter("seq")));
			// 카운트삭제?
			int count = bdao.delete(Integer.parseInt(request.getParameter("seq")));
			out.println(count);

			// count가0이고 이미지가 null일때 삭제할수있게한다
			if (count == 0 && oldVO.getBo_image() != null) {
				String image = "c:/image/" + oldVO.getBo_image();
				File file = new File(image);
				file.delete();
			}
			break;

		case "/board/read":
			request.setAttribute("vo", bdao.read(Integer.parseInt(request.getParameter("seq"))));
			RequestDispatcher dis = request.getRequestDispatcher("read.jsp");
			dis.forward(request, response);
			break;

		case "/login":
			vo = bdao.read(Integer.parseInt(request.getParameter("seq")));
			String id = request.getParameter("bo_id");
			String pw = request.getParameter("bo_pw");
			vo.setBo_id(id);
			if (!vo.getBo_id().equals(id)) {
				out.println("0"); // 아이디없음
			} else if (!vo.getBo_pw().equals(pw)) {
				out.println("1"); // 비번틀림
			} else {
				out.println("2"); // 로그인성공
				HttpSession session = request.getSession();
				session.setAttribute("BoardVO", vo);
			}
			break;

		case "/board/list.json":
			SqlVO svo = new SqlVO();
			String key = request.getParameter("key") == null ? "bo_id" : request.getParameter("key");
			String word = request.getParameter("word") == null ? "" : request.getParameter("word");
			int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
			int perPage = request.getParameter("perPage") == null ? 1000
					: Integer.parseInt(request.getParameter("perPage"));
			svo.setKey("bo_id");
			svo.setWord("");
			svo.setPage(1);
			svo.setPerPage(100);
			out.println(bdao.list2(svo));
			break;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8"); // 한글 깨짐 방지
		PrintWriter out = response.getWriter(); // 브라우저 객체
		BoardDAO bdao = new BoardDAO();
		RequestDispatcher rd;

		// 파일업로드
		String path = "c:/image";// 이미지업로드패스
		MultipartRequest multi = new MultipartRequest( // 사용하기위해cos.jar파일이 필요함
				request, path, 1024 * 1024 * 10, // 10mb
				"UTF-8", // 파일이름이 한글이름일경우 깨지지않기위해
				new DefaultFileRenamePolicy() // 중복된 파일이름일경우 자동으로 바꿔줌
		);

		// vo에 입력한값 입력
		BoardVO vo = new BoardVO();
		vo.setBo_id(multi.getParameter("bo_id"));
		vo.setBo_pw(multi.getParameter("bo_pw"));
		vo.setBo_title(multi.getParameter("bo_title"));
		vo.setBo_content(multi.getParameter("bo_content"));
		vo.setBo_image(multi.getParameter("bo_image"));
		vo.setBo_list(multi.getParameter("bo_list"));
		

		switch (request.getServletPath()) {
		case "/board/insert":
			bdao.insert(vo);
			response.sendRedirect("list.jsp");
			break;

		case "/board/update":
			System.out.println("asdf");
			System.out.println(multi.getParameter("bo_seq"));
			vo.setSeq(Integer.parseInt(multi.getParameter("bo_seq")));
			
			BoardVO oldVO = bdao.read(vo.getSeq());
			if (multi.getFilesystemName("image") == null) {
				// 수정이미지가 없을 때 전의 이미지를 사용
				vo.setBo_image(oldVO.getBo_image());
			} else {
				// 수정이미지가 있는경우
				if (oldVO.getBo_image() != null) {// 예전이미지가 있을 경우 삭제
					String delImage = path + "/" + oldVO.getBo_image();
					File file = new File(delImage);
					file.delete();
				}
			}
			System.out.println(oldVO);
			bdao.update(oldVO);// 수정이미지 업데이트
			request.setAttribute("vo", oldVO);
			rd = request.getRequestDispatcher("update.jsp");
			rd.forward(request, response);
			
			break;
		}
	}

}
