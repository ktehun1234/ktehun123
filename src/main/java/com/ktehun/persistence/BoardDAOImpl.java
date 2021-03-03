package com.ktehun.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ktehun.domain.BoardVO;
import com.ktehun.domain.PagingCriteria;
import com.ktehun.domain.SerachCriteria;

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
		return ses.selectList(namespace+".listBoard");
	}

	@Override
	public List<BoardVO> listBoardPaging(int page) throws Exception {
		if	(page <= 0) {
			page = 1;
		}
		page = (page -1)*10;
		return ses.selectList(namespace + ".listBoardPaging",page);
	}

	@Override
	public List<BoardVO> listBoardCriteria(PagingCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return ses.selectList(namespace + ".listBoardCriteria",cri);
	}
	public int getTotalBoardCnt()  throws Exception {
		return ses.selectOne(namespace+".getTotalBoardCnt");
	}

	@Override
	public List<BoardVO> goSearch(SerachCriteria scri,PagingCriteria cri) throws Exception {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("searchType",scri.getSearchType());
		param.put("searchWord",scri.getSearchWord());
		param.put("pageStart", cri.getPageStart());
		param.put("perPageNum", cri.getPerPageNum());
		return ses.selectList(namespace+".serchBoard",param);
	}
	public int serchBoardCnt(SerachCriteria scri)  throws Exception {
		return ses.selectOne(namespace+".serchBoardCnt",scri);
	}

	@Override
	public void updateReply(int no, int amount) throws Exception {
		Map<String,Object> para = new HashMap<String, Object>();
		para.put("no", no);
		para.put("amount",amount);
		ses.update(namespace+".updateReplyCnt",para);
		
	}

	@Override
	public void viewcnt(int no) throws Exception {
		ses.update(namespace+".viewcnt",no);
		
	}
	

	
	

}
