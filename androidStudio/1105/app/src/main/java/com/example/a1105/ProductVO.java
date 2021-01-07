package com.example.a1105;

public class ProductVO {
	
	private String code;
	private String name;
	private String price;
	private String image;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "ProductVO{" +
				"code='" + code + '\'' +
				", name='" + name + '\'' +
				", price='" + price + '\'' +
				", image='" + image + '\'' +
				'}';
	}
}
