package com.ktehun.persistence;

import java.util.List;

import com.ktehun.domain.BoardVO;
import com.ktehun.domain.PagingCriteria;
import com.ktehun.domain.SerachCriteria;

public interface BoardDAO {

	//글 쓰기
	public int insert(BoardVO vo) throws Exception;
	
	//상세 글 조회
	public BoardVO readBoard(int no) throws Exception;
	
	public int update(BoardVO vo)throws Exception;
	
	public int delete(int no) throws Exception;
	
	public List<BoardVO> listBoard() throws Exception;
	
	//페이징
	public List<BoardVO> listBoardPaging(int page)throws Exception;
	public List<BoardVO> listBoardCriteria(PagingCriteria cri) throws Exception; 
	public int getTotalBoardCnt()throws Exception;
	
	//검색결과 페이징
	public	List<BoardVO> goSearch(SerachCriteria scri,PagingCriteria cri) throws Exception;
	//검색 결과 글 수 가져오기
	public int serchBoardCnt(SerachCriteria scri)throws Exception;
	
	//답글 달렸을 때 답글 수 update
	public void updateReply(int no,int amount) throws Exception;
	//조회수 증가
	public void viewcnt(int no) throws Exception;
}
