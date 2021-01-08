package eat;

public class EatVO {
	
	private String eat_code;
	private String eat_name;
	private String eat_address;
	private String eat_tel;
	private String eat_info;
	private String eat_image;
	private String eat_dist;
	private String eat_time;
	
	public String getEat_code() {
		return eat_code;
	}
	public void setEat_code(String eat_code) {
		this.eat_code = eat_code;
	}
	public String getEat_name() {
		return eat_name;
	}
	public void setEat_name(String eat_name) {
		this.eat_name = eat_name;
	}
	public String getEat_address() {
		return eat_address;
	}
	public void setEat_address(String eat_address) {
		this.eat_address = eat_address;
	}
	public String getEat_tel() {
		return eat_tel;
	}
	public void setEat_tel(String eat_tel) {
		this.eat_tel = eat_tel;
	}
	public String getEat_info() {
		return eat_info;
	}
	public void setEat_info(String eat_info) {
		this.eat_info = eat_info;
	}
	public String getEat_image() {
		return eat_image;
	}
	public void setEat_image(String eat_image) {
		this.eat_image = eat_image;
	}
	public String getEat_dist() {
		return eat_dist;
	}
	public void setEat_dist(String eat_dist) {
		this.eat_dist = eat_dist;
	}
	public String getEat_time() {
		return eat_time;
	}
	public void setEat_time(String eat_time) {
		this.eat_time = eat_time;
	}
	
	@Override
	public String toString() {
		return "EatVO [eat_code=" + eat_code + ", eat_name=" + eat_name + ", eat_address=" + eat_address + ", eat_tel="
				+ eat_tel + ", eat_info=" + eat_info + ", eat_image=" + eat_image + ", eat_dist=" + eat_dist
				+ ", eat_time=" + eat_time + "]";
	}
	
	
	
}
