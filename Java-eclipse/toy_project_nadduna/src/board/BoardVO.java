package board;

import java.sql.Date;

public class BoardVO {
	
	private int seq;
	private String bo_id;
	private String bo_pw;
	private String bo_title;
	private String bo_content;
	private String bo_image;
	private String bo_list;
	private Date bo_date;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getBo_id() {
		return bo_id;
	}
	public void setBo_id(String bo_id) {
		this.bo_id = bo_id;
	}
	public String getBo_pw() {
		return bo_pw;
	}
	public void setBo_pw(String bo_pw) {
		this.bo_pw = bo_pw;
	}
	public String getBo_title() {
		return bo_title;
	}
	public void setBo_title(String bo_title) {
		this.bo_title = bo_title;
	}
	public String getBo_content() {
		return bo_content;
	}
	public void setBo_content(String bo_content) {
		this.bo_content = bo_content;
	}
	@Override
	public String toString() {
		return "BoardVO [seq=" + seq + ", bo_id=" + bo_id + ", bo_pw=" + bo_pw + ", bo_title=" + bo_title
				+ ", bo_content=" + bo_content + ", bo_image=" + bo_image + ", bo_list=" + bo_list + ", bo_date="
				+ bo_date + "]";
	}
	public String getBo_image() {
		return bo_image;
	}
	public void setBo_image(String bo_image) {
		this.bo_image = bo_image;
	}
	public String getBo_list() {
		return bo_list;
	}
	public void setBo_list(String bo_list) {
		this.bo_list = bo_list;
	}
	public Date getBo_date() {
		return bo_date;
	}
	public void setBo_date(Date bo_date) {
		this.bo_date = bo_date;
	}
	
	
	
}
