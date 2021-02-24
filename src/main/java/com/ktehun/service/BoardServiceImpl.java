package com.ktehun.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ktehun.domain.BoardVO;
import com.ktehun.domain.PagingCriteria;
import com.ktehun.persistence.BoardDAO;



@Service
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardDAO dao;
	
	@Override
	public boolean insert(BoardVO vo) throws Exception {
		boolean result = false;
		int i = dao.insert(vo);
		if (i == 1) {
			result = true;
		}
		
		return result;
	}

	@Override
	public BoardVO read(int no) throws Exception {
		// 이후에 조회수 증가 하는 것을 AOP의 트랜잭션 처리로 마감
		
		BoardVO vo = dao.readBoard(no);
		return vo;
		
	}

	@Override
	public boolean modify(BoardVO vo) throws Exception {
		boolean result = false;
		int i = dao.update(vo);
		System.out.println("service단 : "+vo);
		if (i == 1) {
			result = true;
		}
		
		return result;
	}

	@Override
	public boolean remove(int no) throws Exception {
		boolean result = false;
		int i = dao.delete(no);
		if (i == 1) {
			result = true;
		}
		
		return result;
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		return dao.listBoard();
	}

	@Override
	public List<BoardVO> listCriteria(PagingCriteria cri) throws Exception {
		return dao.listBoardCriteria(cri);
	}

	@Override
	public int getToTablBoardCnt() throws Exception {
		
		return dao.getTotalBoardCnt();
	}



}
