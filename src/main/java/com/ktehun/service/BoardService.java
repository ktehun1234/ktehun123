package com.ktehun.service;

import java.util.List;

import com.ktehun.domain.BoardVO;
import com.ktehun.domain.PagingCriteria;
import com.ktehun.domain.SerachCriteria;

public interface BoardService {
	public int getToTablBoardCnt()throws Exception;

	public boolean insert(BoardVO vo) throws Exception;
	
	public BoardVO read(int no) throws Exception;
	
	public boolean modify(BoardVO vo) throws Exception;
	
	public boolean remove(int no) throws Exception;
	
	public List<BoardVO> listAll() throws Exception;
	
	public List<BoardVO> listCriteria(PagingCriteria cri) throws Exception;

	public List<BoardVO>  goSearch(SerachCriteria scri,PagingCriteria cri) throws Exception;
	public int serchBoardCnt(SerachCriteria scri)throws Exception;
	
}
