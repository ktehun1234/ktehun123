package com.ktehun.persistence;

import java.util.List;

import com.ktehun.domain.ReplyVO;

public interface ReplyDAO {
	public void create(ReplyVO vo) throws Exception;
	
	public List<ReplyVO> read(int bno) throws Exception;
	
	public void upadte(ReplyVO vo)throws Exception;
	public void delete(int no)throws Exception;
}
