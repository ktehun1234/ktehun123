package com.ktehun.domain;

import java.util.Date;

public class BoardVO {
	private int no;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private int viewnt;
	private String isdelete;
	private int replycnt;
	
	public BoardVO() {
		super();
	}
	public BoardVO(int no, String title, String content, String writer, Date regdate, int viewnt,String isdelete,int replycnt) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.regdate = regdate;
		this.viewnt = viewnt;
		this.isdelete = isdelete;
		this.replycnt =replycnt;
	}
	
	public int getReplycnt() {
		return replycnt;
	}
	public void setReplycnt(int replycnt) {
		this.replycnt = replycnt;
	}
	public String getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(String isdelete) {
		this.isdelete = isdelete;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getViewnt() {
		return viewnt;
	}
	public void setViewcnt(int viewcnt) {
		this.viewnt = viewcnt;
	}
	@Override
	public String toString() {
		return "BoardVO [no=" + no + ", title=" + title + ", content=" + content + ", writer=" + writer + ", regdate="
				+ regdate + ", viewnt=" + viewnt + ", isdelete=" + isdelete + ", replycnt=" + replycnt + "]";
	}

	
	
}
