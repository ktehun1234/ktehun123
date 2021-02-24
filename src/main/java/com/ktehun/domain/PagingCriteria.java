package com.ktehun.domain;

public class PagingCriteria {
	private int page;
	private int perPageNum;
	
	public PagingCriteria(){
		this.page = 1;
		this.perPageNum = 10;
		
	}
	public void setPage(int page) {
		if(page <= 0 ) {
			this.page = 1;
			return;
		}
		this.page = page;
	}
	public void setPerPageNum(int perPageNum) {
		if(perPageNum <= 0 || perPageNum >81) {
			this.perPageNum = 10;
			return;
		}
		this.perPageNum = perPageNum;
	}
	
	public int getPageStart() {
		return (this.page-1) * 10;
	}
	
	public int getPerPageNum() {
		return this.perPageNum;
	}
	@Override
	public String toString() {
		return "PagingCriteria [page=" + page + ", perPageNum=" + perPageNum + "]";
	}
	
}
