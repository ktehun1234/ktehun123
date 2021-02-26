package com.ktehun.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ktehun.domain.ReplyVO;
@Repository
public class ReplyDAOImpl implements com.ktehun.persistence.ReplyDAO {
	@Inject
	private SqlSession ses;
	private String namespace ="com.ktehun.mapper.ReplyMapper";
	@Override
	public void create(ReplyVO vo) throws Exception {
		// TODO Auto-generated method stub
	ses.insert(namespace+".create",vo);
	}

	@Override
	public List<ReplyVO> read(int bno) throws Exception {
		// TODO Auto-generated method stub
		return ses.selectList(namespace+".read",bno);
	}

	@Override
	public void upadte(ReplyVO vo) throws Exception {
		ses.update(namespace+".update",vo);

	}

	@Override
	public void delete(int no) throws Exception {
		ses.delete(namespace+".delete",no);

	}

}
