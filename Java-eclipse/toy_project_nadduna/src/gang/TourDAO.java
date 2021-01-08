package gang;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import database.Database;
import database.SqlVO;


public class TourDAO {
   
   public TourVO read(String tour_code) {
      TourVO vo = new TourVO();
      try {
         String sql = "select * from tour where tour_code=?";
         PreparedStatement ps = Database.CON.prepareStatement(sql);
         ps.setString(1, tour_code);
         ResultSet rs = ps.executeQuery();
         if(rs.next()) {
            vo.setTour_code(rs.getString("tour_code"));
            vo.setTour_name(rs.getString("tour_name"));
            vo.setTour_address(rs.getString("tour_address"));
            vo.setTour_tel(rs.getString("tour_tel"));
            vo.setTour_image(rs.getString("tour_image"));
            vo.setTour_info(rs.getString("tour_info"));
            vo.setTour_dist(rs.getString("tour_dist"));

         }
      }catch(Exception e) {
         System.out.println(e.toString());
      }
      
      return vo;
   }
   public JSONObject galist(SqlVO svo) {
      JSONObject jobject = new JSONObject();
      try {
         String sql = "{call bomi.list(?,?,?,?,?,?,?)}";
         CallableStatement cs = Database.CON.prepareCall(sql);
         cs.setString(1, "tour");
         cs.setString(2, svo.getKey());
         cs.setString(3, svo.getWord());
         cs.setInt(4, svo.getPage());
         cs.setInt(5, svo.getPerPage());
         cs.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
         cs.registerOutParameter(7, oracle.jdbc.OracleTypes.INTEGER);
         cs.execute();
         
         jobject.put("count", cs.getInt(7));
         ResultSet rs = (ResultSet)cs.getObject(6);
         
         int lastPage = cs.getInt(7)%svo.getPerPage()==0?cs.getInt(7)/svo.getPerPage():cs.getInt(7)/svo.getPerPage()+1;
         jobject.put("lastPage", lastPage);
         
         JSONArray jarray = new JSONArray();
         while(rs.next()) {
            JSONObject obj = new JSONObject();
            obj.put("tour_code", rs.getString("tour_code"));
            obj.put("tour_name", rs.getString("tour_name"));
            obj.put("tour_address", rs.getString("tour_address"));
            obj.put("tour_tel", rs.getString("tour_tel"));
            obj.put("tour_image", rs.getString("tour_image"));
            obj.put("tour_info", rs.getString("tour_info"));
            obj.put("tour_dist", rs.getString("tour_dist"));
            jarray.add(obj);
         }
         jobject.put("galist", jarray);
         
      }catch(Exception e) {
         System.out.println(e.toString());
      }
      return jobject;
   }
}