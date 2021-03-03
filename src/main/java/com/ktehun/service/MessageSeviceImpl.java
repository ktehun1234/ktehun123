package com.ktehun.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktehun.domain.MessageVO;
import com.ktehun.persistence.MessageDAO;
import com.ktehun.persistence.PointDAO;
@Service
public class MessageSeviceImpl implements MessageService {
	@Inject
	private MessageDAO mdao;
	
	@Inject
	private PointDAO pdao;
	@Transactional
	@Override
	public void addMessage(MessageVO vo) throws Exception {
		mdao.crate(vo);
		pdao.updatePoint(vo.getSender(),10);

	}

	@Override
	public MessageVO readMessage(String uid,int mid) throws Exception {
		mdao.upadateState(mid);
		pdao.updatePoint(uid,5);
		return mdao.readMessage(mid);
	}

}
