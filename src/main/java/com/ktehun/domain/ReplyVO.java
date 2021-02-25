package com.ktehun.domain;

import java.util.Date;

public class ReplyVO {
	private int no;
	private int bno;
	private String replytext;
	private String replayer;
	private Date regdate;
	private Date upadtedate;
	
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	
	public String getReplytext() {
		return replytext;
	}
	public void setReplytext(String replytext) {
		this.replytext = replytext;
	}
	public String getReplayer() {
		return replayer;
	}
	public void setReplayer(String replayer) {
		this.replayer = replayer;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Date getUpadtedate() {
		return upadtedate;
	}
	public void setUpadtedate(Date upadtedate) {
		this.upadtedate = upadtedate;
	}
	@Override
	public String toString() {
		return "ReplyVO [no=" + no + ", bno=" + bno + ", replytext=" + replytext + ", replayer=" + replayer
				+ ", regdate=" + regdate + ", upadtedate=" + upadtedate + "]";
	}
	
}
