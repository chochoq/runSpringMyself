package com.example.a1106;

public class AddrVO {
	private int seq;
	private String name;
	private String tel;
	private String addr;
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	@Override
	public String toString() {
		return "AddrVO [seq=" + seq + ", name=" + name + ", tel=" + tel + ", addr=" + addr + "]";
	}
	
	
	

}
