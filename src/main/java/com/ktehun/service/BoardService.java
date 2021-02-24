package com.ktehun.service;

import java.util.List;

import com.ktehun.domain.BoardVO;
import com.ktehun.domain.PagingCriteria;

public interface BoardService {
	public boolean insert(BoardVO vo) throws Exception;
	
	public BoardVO read(int no) throws Exception;
	
	public boolean modify(BoardVO vo) throws Exception;
	
	public boolean remove(int no) throws Exception;
	
	public List<BoardVO> listAll() throws Exception;
	
	public List<BoardVO> listCriteria(PagingCriteria cri) throws Exception;

}
