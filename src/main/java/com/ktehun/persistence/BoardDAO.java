package com.ktehun.persistence;

import java.util.List;

import com.ktehun.domain.BoardVO;

public interface BoardDAO {
	//글 쓰기
	public int insert(BoardVO vo) throws Exception;
	
	//상세 글 조회
	public BoardVO readBoard(int no) throws Exception;
	
	public int update(BoardVO vo)throws Exception;
	
	public int delete(int no) throws Exception;
	
	public List<BoardVO> listBoard() throws Exception;
}
