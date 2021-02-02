package eat;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.SqlVO;



@WebServlet(value={"/eat/read", "/eat/list.json"})
public class EatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out =response.getWriter();
		EatDAO dao = new EatDAO();
		
		switch(request.getServletPath()) {
		case "/eat/read":
			request.setAttribute("vo", dao.read(request.getParameter("eat_code")));
			RequestDispatcher dis = request.getRequestDispatcher("read.jsp");
			dis.forward(request, response);
			break;	
			
		case "/eat/list.json":
			SqlVO svo = new SqlVO();
			
			String key = request.getParameter("key")==null? "eat_dist":request.getParameter("key");
			String word = request.getParameter("word")==null? "":request.getParameter("word");
			int page = request.getParameter("page")==null? 1:Integer.parseInt(request.getParameter("page"));
			int perPage = request.getParameter("perPage")==null? 2: Integer.parseInt(request.getParameter("perPage"));
			
			svo.setKey(key);
			svo.setWord(word);
			svo.setPage(page);
			svo.setPerPage(perPage);
			out.println(dao.list(svo).toJSONString());
			break;
		
		}	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
