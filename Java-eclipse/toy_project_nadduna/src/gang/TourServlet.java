package gang;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import database.SqlVO;



@WebServlet(value={"/tour/yelist.json","/tour/onlist.json","/tour/selist.json","/tour/nalist.json","/tour/milist.json","/tour/julist.json","/tour/dolist.json","/tour/list.json","/tour/gylist.json","/tour/read","/tour/bulist.json"})
public class TourServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
   
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8"); //한글 깨짐 방지
      PrintWriter out = response.getWriter(); //브라우저 객체
      TourDAO tdao = new TourDAO();
      
      switch(request.getServletPath()) {
      case "/tour/list.json":
         SqlVO svo = new SqlVO();
         String key = request.getParameter("key")==null? "tour_code":request.getParameter("key");
         String word = request.getParameter("word")==null? "강화군":request.getParameter("word");
         int page = request.getParameter("page")==null? 1:Integer.parseInt(request.getParameter("page"));
         int perPage = request.getParameter("perPage")==null? 2:Integer.parseInt(request.getParameter("perPage"));
         svo.setKey(key);
         svo.setWord(word);
         svo.setPage(page);
         svo.setPerPage(perPage);
         out.println(tdao.galist(svo).toJSONString());
      break;
      
      case "/tour/gylist.json":
         svo = new SqlVO();
         key = request.getParameter("key")==null? "tour_code":request.getParameter("key");
         word = request.getParameter("word")==null? "계양구":request.getParameter("word");
         page = request.getParameter("page")==null? 1:Integer.parseInt(request.getParameter("page"));
         perPage = request.getParameter("perPage")==null? 2:Integer.parseInt(request.getParameter("perPage"));
         svo.setKey(key);
         svo.setWord(word);
         svo.setPage(page);
         svo.setPerPage(perPage);
         out.println(tdao.galist(svo).toJSONString());
      break;
      
      case "/tour/bulist.json":
         svo = new SqlVO();
         key = request.getParameter("key")==null? "tour_code":request.getParameter("key");
         word = request.getParameter("word")==null? "부평구":request.getParameter("word");
         page = request.getParameter("page")==null? 1:Integer.parseInt(request.getParameter("page"));
         perPage = request.getParameter("perPage")==null? 2:Integer.parseInt(request.getParameter("perPage"));
         svo.setKey(key);
         svo.setWord(word);
         svo.setPage(page);
         svo.setPerPage(perPage);
         out.println(tdao.galist(svo).toJSONString());
      break;
      
      case "/tour/dolist.json":
         svo = new SqlVO();
         key = request.getParameter("key")==null? "tour_code":request.getParameter("key");
         word = request.getParameter("word")==null? "동구":request.getParameter("word");
         page = request.getParameter("page")==null? 1:Integer.parseInt(request.getParameter("page"));
         perPage = request.getParameter("perPage")==null? 2:Integer.parseInt(request.getParameter("perPage"));
         svo.setKey(key);
         svo.setWord(word);
         svo.setPage(page);
         svo.setPerPage(perPage);
         out.println(tdao.galist(svo).toJSONString());
      break;
      
      case "/tour/julist.json":
         svo = new SqlVO();
         key = request.getParameter("key")==null? "tour_code":request.getParameter("key");
         word = request.getParameter("word")==null? "중구":request.getParameter("word");
         page = request.getParameter("page")==null? 1:Integer.parseInt(request.getParameter("page"));
         perPage = request.getParameter("perPage")==null? 2:Integer.parseInt(request.getParameter("perPage"));
         svo.setKey(key);
         svo.setWord(word);
         svo.setPage(page);
         svo.setPerPage(perPage);
         out.println(tdao.galist(svo).toJSONString());
      break;
      
      case "/tour/milist.json":
         svo = new SqlVO();
         key = request.getParameter("key")==null? "tour_code":request.getParameter("key");
         word = request.getParameter("word")==null? "미추홀":request.getParameter("word");
         page = request.getParameter("page")==null? 1:Integer.parseInt(request.getParameter("page"));
         perPage = request.getParameter("perPage")==null? 2:Integer.parseInt(request.getParameter("perPage"));
         svo.setKey(key);
         svo.setWord(word);
         svo.setPage(page);
         svo.setPerPage(perPage);
         out.println(tdao.galist(svo).toJSONString());
      break;
      
      case "/tour/nalist.json":
         svo = new SqlVO();
         key = request.getParameter("key")==null? "tour_code":request.getParameter("key");
         word = request.getParameter("word")==null? "남동구":request.getParameter("word");
         page = request.getParameter("page")==null? 1:Integer.parseInt(request.getParameter("page"));
         perPage = request.getParameter("perPage")==null? 2:Integer.parseInt(request.getParameter("perPage"));
         svo.setKey(key);
         svo.setWord(word);
         svo.setPage(page);
         svo.setPerPage(perPage);
         out.println(tdao.galist(svo).toJSONString());
      break;
      
      case "/tour/onlist.json":
         svo = new SqlVO();
         key = request.getParameter("key")==null? "tour_code":request.getParameter("key");
         word = request.getParameter("word")==null? "옹진군":request.getParameter("word");
         page = request.getParameter("page")==null? 1:Integer.parseInt(request.getParameter("page"));
         perPage = request.getParameter("perPage")==null? 2:Integer.parseInt(request.getParameter("perPage"));
         svo.setKey(key);
         svo.setWord(word);
         svo.setPage(page);
         svo.setPerPage(perPage);
         out.println(tdao.galist(svo).toJSONString());
      break;
      
      case "/tour/selist.json":
         svo = new SqlVO();
         key = request.getParameter("key")==null? "tour_code":request.getParameter("key");
         word = request.getParameter("word")==null? "서구":request.getParameter("word");
         page = request.getParameter("page")==null? 1:Integer.parseInt(request.getParameter("page"));
         perPage = request.getParameter("perPage")==null? 2:Integer.parseInt(request.getParameter("perPage"));
         svo.setKey(key);
         svo.setWord(word);
         svo.setPage(page);
         svo.setPerPage(perPage);
         out.println(tdao.galist(svo).toJSONString());
      break;
      
      case "/tour/yelist.json":
         svo = new SqlVO();
         key = request.getParameter("key")==null? "tour_code":request.getParameter("key");
         word = request.getParameter("word")==null? "연수구":request.getParameter("word");
         page = request.getParameter("page")==null? 1:Integer.parseInt(request.getParameter("page"));
         perPage = request.getParameter("perPage")==null? 2:Integer.parseInt(request.getParameter("perPage"));
         svo.setKey(key);
         svo.setWord(word);
         svo.setPage(page);
         svo.setPerPage(perPage);
         out.println(tdao.galist(svo).toJSONString());
      break;
      
      case "/tour/read":
         request.setAttribute("vo", tdao.read(request.getParameter("tour_code")));
         RequestDispatcher dis = request.getRequestDispatcher("read.jsp");
         dis.forward(request, response);
         break;
      }
   }

   
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String path= "c:/image";
      //사진 업로드 받을때 옵션들
      MultipartRequest multi = new MultipartRequest(
            request, path, 1024 * 1024 * 10, "UTF-8", new DefaultFileRenamePolicy());
      
      TourVO vo = new TourVO();
      vo.setTour_code(multi.getParameter("tour_code"));
      vo.setTour_name(multi.getParameter("tour_name"));
      vo.setTour_address(multi.getParameter("tour_address"));
      vo.setTour_tel(multi.getParameter("tour_tel"));
      vo.setTour_image(multi.getParameter("tour_image"));
      vo.setTour_info(multi.getParameter("tour_info"));
      vo.setTour_dist(multi.getParameter("tour_dist"));
      System.out.println(vo.toString());
   }

}