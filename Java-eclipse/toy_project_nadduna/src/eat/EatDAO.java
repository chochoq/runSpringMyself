package eat;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.Database;
import database.SqlVO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class EatDAO {

	//상품 읽기
	public EatVO read(String eat_code) {
		EatVO vo = new EatVO();
		try {
			String sql = "select * from et where eat_code=?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, eat_code);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
				vo.setEat_code(rs.getString("eat_code"));
				vo.setEat_name(rs.getString("eat_name"));
				vo.setEat_address(rs.getString("eat_address"));
				vo.setEat_tel(rs.getString("eat_tel"));
				vo.setEat_info(rs.getString("eat_info"));
				vo.setEat_image(rs.getString("eat_image"));
				vo.setEat_dist(rs.getString("eat_dist"));
				vo.setEat_time(rs.getString("eat_time"));		
				}
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		return vo;
	}	
	
	// 맛집 목록 출력
	public JSONObject list(SqlVO svo) {
		JSONObject jo = new JSONObject();
		try {
			String sql = "{call bomi.list(?,?,?,?,?,?,?)}";
			CallableStatement cs = Database.CON.prepareCall(sql);
			cs.setString(1, "et");
			cs.setString(2, svo.getKey());
			cs.setString(3, svo.getWord());
			cs.setInt(4, svo.getPage());
			cs.setInt(5, svo.getPerPage());
			cs.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
			cs.registerOutParameter(7, oracle.jdbc.OracleTypes.INTEGER);
			cs.execute();

			jo.put("count", cs.getInt(7));
			ResultSet rs = (ResultSet) cs.getObject(6);

			int lastPage = cs.getInt(7) % svo.getPerPage() == 0 ? cs.getInt(7) / svo.getPerPage()
					: cs.getInt(7) / svo.getPerPage() + 1;
			jo.put("lastPage", lastPage);

			JSONArray ja = new JSONArray();
			while (rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("eat_code", rs.getString("eat_code"));
				obj.put("eat_name", rs.getString("eat_name"));
				obj.put("eat_address", rs.getString("eat_address"));
				obj.put("eat_tel", rs.getString("eat_tel"));
				obj.put("eat_info", rs.getString("eat_info"));
				obj.put("eat_image", rs.getString("eat_image"));
				obj.put("eat_dist", rs.getString("eat_dist"));
				obj.put("eat_time", rs.getString("eat_time"));
				ja.add(obj);
			}
			jo.put("list", ja);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return jo;
	}

}
