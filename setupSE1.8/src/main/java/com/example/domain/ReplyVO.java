package com.example.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReplyVO {
 private int rno;
 private int bno;
 
 private String reply;
 private String replyer;
 @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
 private Date replydate;
 @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
 private Date updatedate;
public int getRno() {
	return rno;
}
public void setRno(int rno) {
	this.rno = rno;
}
public int getBno() {
	return bno;
}
public void setBno(int bno) {
	this.bno = bno;
}
public String getReply() {
	return reply;
}
public void setReply(String reply) {
	this.reply = reply;
}
public String getReplyer() {
	return replyer;
}
public void setReplyer(String replyer) {
	this.replyer = replyer;
}
public Date getReplydate() {
	return replydate;
}
public void setReplydate(Date replydate) {
	this.replydate = replydate;
}
public Date getUpdatedate() {
	return updatedate;
}
public void setUpdatedate(Date updatedate) {
	this.updatedate = updatedate;
}
@Override
public String toString() {
	return "ReplyVO [rno=" + rno + ", bno=" + bno + ", reply=" + reply + ", replyer=" + replyer + ", replydate="
			+ replydate + ", updatedate=" + updatedate + "]";
}

}
