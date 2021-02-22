package com.ktehun.persistence;

import com.ktehun.domain.BoardVO;

public interface BoardDAO {
	//글 쓰기
	public boolean insert(BoardVO vo) throws Exception;
	
	//상세 글 조회
	public BoardVO readBoard(int no) throws Exception;
}
