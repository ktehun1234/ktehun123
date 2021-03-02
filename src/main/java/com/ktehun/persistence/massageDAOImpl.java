package com.ktehun.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ktehun.domain.MessageVO;
@Repository
public class massageDAOImpl implements MessageDAO {
	@Inject
	private SqlSession ses;
	private String namespace ="com.ktehun.mapper.MessageMapper";
	@Override
	public void crate(MessageVO vo) throws Exception {
		ses.insert(namespace+".create",vo);
			
	}

	@Override
	public MessageVO eadMessage(int mid) throws Exception {

		return ses.selectOne(namespace+".readMessage",mid);
	}

	@Override
	public void upadateState(int mid) throws Exception {
		ses.update(namespace+".update",mid);
	}

}
