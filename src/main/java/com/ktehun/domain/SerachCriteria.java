package com.ktehun.domain;

public class SerachCriteria {
	private String searchType; //검색 종류
	private String searchWord; // 검색어
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	@Override
	public String toString() {
		return "SerachCriteria [searchType=" + searchType + ", searchWord=" + searchWord + "]";
	}
	
	
}
