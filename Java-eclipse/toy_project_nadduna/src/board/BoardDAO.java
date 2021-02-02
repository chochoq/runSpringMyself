package board;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import database.Database;
import database.SqlVO;

public class BoardDAO {

	//글삭제
		public int delete(int seq) {
			int count=0;
			try {
				
				if(count==0) {
					String sql="delete from bomi.board where seq=?";
					PreparedStatement ps = Database.CON.prepareStatement(sql);
					ps = Database.CON.prepareStatement(sql);
					ps.setInt(1, seq);
					ps.execute();
				}
			}catch(Exception e) {
				System.out.println(e.toString());
			}
			return count;
		}
	
	//글수정
		public void update(BoardVO vo) {
			try {
				String sql="update bomi.board set BO_TITLE=?,BO_CONTENT=?,BO_IMAGE=?,BO_LIST=? where seq=?";
				PreparedStatement ps = Database.CON.prepareStatement(sql);
				
				ps.setString(1, vo.getBo_title());
				ps.setString(2, vo.getBo_content());
				ps.setString(3, vo.getBo_image());
				ps.setString(4, vo.getBo_list());
				ps.setInt(5, vo.getSeq());
				System.out.println(vo.toString());
				ps.execute();
				ps.close();
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("업뎃실패");
			}
		}
		
		
	//글읽기
	public BoardVO read(int seq) {
		BoardVO vo = new BoardVO();
		
		try {
			String sql= "select * from bomi.board where seq=?";
			PreparedStatement ps =Database.CON.prepareStatement(sql);
			ps.setInt(1, seq);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				vo.setSeq(seq);
				vo.setBo_id(rs.getString("bo_id"));
				vo.setBo_pw(rs.getString("bo_pw"));
				vo.setBo_title(rs.getString("bo_title"));
				vo.setBo_content(rs.getString("bo_content"));
				vo.setBo_list(rs.getString("bo_list"));
				vo.setBo_date(rs.getDate("bo_date"));
				vo.setBo_image(rs.getString("bo_image"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	
	//글 입력
	public void insert(BoardVO vo) {
		try {
			String sql="insert into bomi.board(SEQ,BO_ID,BO_PW,BO_TITLE,BO_CONTENT,BO_IMAGE,BO_LIST,BO_DATE) values(seq_board.nextval,?,?,?,?,?,?,sysdate)";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, vo.getBo_id());
			ps.setString(2, vo.getBo_pw());
			ps.setString(3, vo.getBo_title());
			ps.setString(4, vo.getBo_content());
			ps.setString(5, vo.getBo_image());
			ps.setString(6, vo.getBo_list());
			ps.execute();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	

	public JSONObject list(SqlVO svo) {
		JSONObject jo = new JSONObject();

		try {
			String sql = "{call list(?, ?, ?, ?, ?, ?, ?)}";
			CallableStatement cs = Database.CON.prepareCall(sql);
			cs.setString(1, "br");
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

			JSONArray jArr = new JSONArray();

			while (rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("seq", rs.getInt("seq"));
				obj.put("bo_id", rs.getString("bo_id"));
				obj.put("bo_pw", rs.getString("bo_pw"));
				obj.put("bo_title", rs.getString("bo_title"));
				obj.put("bo_content", rs.getString("bo_content"));
				obj.put("bo_image", rs.getString("bo_image"));
				obj.put("bo_list", rs.getString("bo_list"));
				obj.put("bo_date", rs.getDate("bo_date") == null ? "" : rs.getDate("bo_date").toString());
				jArr.add(obj);
			}
			jo.put("list", jArr);

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(jo.toJSONString());
		}
		return jo;
	}

	public JSONObject list2(SqlVO vo) {
		JSONObject jo = null;
		String word = '%' + vo.getWord() + '%';
		String sql = "select * from br where ? like ? and seq between ? and ?";
		try {
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, vo.getKey());
			ps.setString(2, word);
			ps.setInt(3, (vo.getPage() - 1) * vo.getPerPage() + 1);
			ps.setInt(4, vo.getPage() * vo.getPerPage());
			ResultSet rs = ps.executeQuery();
			JSONArray jArr = new JSONArray();
			while (rs.next()) {
				jo = new JSONObject();
				jo.put("seq", rs.getInt("seq"));
				jo.put("bo_id", rs.getString("bo_id"));
				jo.put("bo_pw", rs.getString("bo_pw"));
				jo.put("bo_title", rs.getString("bo_title"));
				jo.put("bo_content", rs.getString("bo_content"));
				jo.put("bo_image", rs.getString("bo_image"));
				jo.put("bo_list", rs.getString("bo_list"));
				jo.put("bo_date", rs.getDate("bo_date") == null ? "" : rs.getDate("bo_date").toString());
				jArr.add(jo);
			}
			jo = new JSONObject();
			jo.put("list", jArr);
			int cnt = 0;
			sql = "select count(*) cnt from br where ? like ?";
			ps = Database.CON.prepareStatement(sql);
			ps.setString(1, vo.getKey());
			ps.setString(2, word);
			rs = ps.executeQuery();
			if(rs.next()) cnt = rs.getInt("cnt"); 
			int perpage = vo.getPerPage();
			int lastPage = (cnt%perpage==0)?(cnt/perpage):(cnt/perpage)+1;
			jo.put("cnt", cnt);
			jo.put("lastPage", lastPage);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return jo;
	}
}
