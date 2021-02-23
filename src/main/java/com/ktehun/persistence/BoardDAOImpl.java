package com.ktehun.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ktehun.domain.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Inject
	private SqlSession ses;
	private String namespace ="com.ktehun.mapper.BoardMapper";
	
	@Override
	public int insert(BoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		return ses.insert(namespace + ".insertBoard", vo);
	}

	@Override
	public BoardVO readBoard(int no) throws Exception {
		// TODO Auto-generated method stub
		return ses.selectOne(namespace+".readBoard",no);
	}

	@Override
	public int update(BoardVO vo) throws Exception {
		System.out.println("DAO단 : "+vo);
		return ses.update(namespace + ".updateBoard", vo);
	}

	@Override
	public int delete(int no) throws Exception {
		// TODO Auto-generated method stub
		return ses.delete(namespace+".deleteBoard",no);
	}

	@Override
	public List<BoardVO> listBoard() throws Exception {
		// TODO Auto-generated method stub
		return ses.selectList(namespace+".list");
	}

}
