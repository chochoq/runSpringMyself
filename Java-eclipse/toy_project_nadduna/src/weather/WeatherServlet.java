package weather;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


@WebServlet("/weather.json")
public class WeatherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		switch(request.getServletPath()) {
		case "/weather.json":
			JSONArray jArray=new JSONArray();
			try {
				Document doc=Jsoup.connect("https://www.daum.net/").get();
				Elements es=doc.select(".list_weather");
				for(Element e:es.select("li")) {
					String part=e.select(".txt_part").text();
					String temper=e.select(".txt_temper").text();
					String wa=e.select(".ir_wa").text();
					
					
					JSONObject obj=new JSONObject();
					obj.put("part", part);
					obj.put("temper", temper);
					obj.put("wa", wa);
					
					jArray.add(obj);
				}
				out.println(jArray.toJSONString());
			}catch(Exception e) {
				System.out.println(e.toString());
			}
			break;
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
