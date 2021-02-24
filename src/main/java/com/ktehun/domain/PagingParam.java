package com.ktehun.domain;



public class PagingParam {
	private int totalCount; //전체 게시글 수
	private int startPage; // (보여주는 페이징 번호의)시작 페이지
	private int endPage; // (보여주는 페이징 번호의)끝 페이지
	private boolean prev;// 이전 페이지로
	private boolean next; // 다음 페이지로
	
	private int displayPageNum = 10; //보여줄 페이지 수 (block)
	
	private PagingCriteria cri;
	
	public void setCri(PagingCriteria cri) {
		this.cri = cri;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		
		this.calcParm();

	}

	private void calcParm() {
		this.endPage =(int)(Math.ceil( cri.getPage() / (double)this.displayPageNum)*displayPageNum); // 블럭화를 했을경우 실제 end페이지
		// (11/10)*10 = 20
		
		// 1 / 10 * 10 = 10
		// 2/10 * 10 = 10
		int tempEndPage = (int)(Math.ceil( this.totalCount / (double)cri.getPerPageNum())); //게심ㄹ 수에 따른 실제 endpage
		// (112 / 10) = 12
		this.startPage =(this.endPage -this.displayPageNum)+ 1;
		
		//11
		if(this.endPage > tempEndPage) {
			 this.endPage =tempEndPage;
		}
		//endPage = 12
		//endPage = 10
		
		// 12 - 10 + 1 = 3
		//10-10+1 = 1
		this.prev = (cri.getPage() == 1) ? false : true;
		// 11 = 1
		this.next = ((cri.getPage()  * cri.getPerPageNum()) >= this.totalCount) ? false : true;
		//11 * 10 > 112 =   fals : true ? true
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public boolean isNext() {
		return next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public PagingCriteria getCri() {
		return cri;
	}

	@Override
	public String toString() {
		return "PagingParam [totalCount=" + totalCount + ", startPage=" + startPage + ", endPage=" + endPage + ", prev="
				+ prev + ", next=" + next + ", displayPageNum=" + displayPageNum + ", cri=" + cri + "]";
	}
	
}
