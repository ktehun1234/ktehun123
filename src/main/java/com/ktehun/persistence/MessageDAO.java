package com.ktehun.persistence;

import com.ktehun.domain.MessageVO;

public interface MessageDAO {
	public void	crate(MessageVO vo) throws Exception;
	public MessageVO readMessage(int mid)throws Exception;
	
	public void upadateState(int mid)throws Exception;
}	

