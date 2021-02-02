package gang;

public class TourVO {
	private String tour_code;
	private String tour_name;
	private String tour_address;
	private String tour_tel;
	private String tour_image;
	private String tour_info;
	private String tour_dist;
	
	public String getTour_code() {
		return tour_code;
	}
	public void setTour_code(String tour_code) {
		this.tour_code = tour_code;
	}
	public String getTour_name() {
		return tour_name;
	}
	public void setTour_name(String tour_name) {
		this.tour_name = tour_name;
	}
	public String getTour_address() {
		return tour_address;
	}
	public void setTour_address(String tour_address) {
		this.tour_address = tour_address;
	}
	public String getTour_tel() {
		return tour_tel;
	}
	public void setTour_tel(String tour_tel) {
		this.tour_tel = tour_tel;
	}
	public String getTour_image() {
		return tour_image;
	}
	public void setTour_image(String tour_image) {
		this.tour_image = tour_image;
	}
	public String getTour_info() {
		return tour_info;
	}
	public void setTour_info(String tour_info) {
		this.tour_info = tour_info;
	}
	public String getTour_dist() {
		return tour_dist;
	}
	public void setTour_dist(String tour_dist) {
		this.tour_dist = tour_dist;
	}
	@Override
	public String toString() {
		return "TourVO [tour_code=" + tour_code + ", tour_name=" + tour_name + ", tour_address=" + tour_address
				+ ", tour_tel=" + tour_tel + ", tour_image=" + tour_image + ", tour_info=" + tour_info + ", tour_dist="
				+ tour_dist + "]";
	}
	
	
}
