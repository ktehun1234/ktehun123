package com.ktehun.service;

import java.util.List;

import javax.inject.Inject;


import org.springframework.stereotype.Service;


import com.ktehun.domain.BoardVO;
import com.ktehun.persistence.BoardDAO;
@Service
public class BoardServiceImpl implements BoardService {
	@Inject
	private BoardDAO dao;
	@Override
	public boolean insert(BoardVO vo) throws Exception {
		boolean result = false;
		int i = dao.insert(vo);
		if(i ==1) {
			result = true;
		}
		return result;
	}

	@Override
	public BoardVO read(int no) throws Exception {
		
		return dao.readBoard(no);
	}

	@Override
	public boolean modify(BoardVO vo) throws Exception {
		boolean result = false;
		int i = dao.update(vo);
		if(i ==1) {
			result = true;
		
		}
		return result;
	}

	@Override
	public boolean remove(int no) throws Exception {
		boolean result = false;
		int i = dao.delete(no);
		if(i ==1) {
			result = true;
		
		}
		return result;
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		
	List<BoardVO> lst =dao.listBoard();
			
			for (BoardVO boardVO : lst) {
				System.out.println(boardVO.toString());
			}
			return null;
		}

}
