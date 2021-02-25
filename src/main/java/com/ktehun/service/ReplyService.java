package com.ktehun.service;

import java.util.List;

import com.ktehun.domain.ReplyVO;

public interface ReplyService {
public void create(ReplyVO vo) throws Exception;
	
	public List<ReplyVO> read(int no) throws Exception;
	
	public void upadte(ReplyVO vo) throws Exception;
	
	public void delete(int no) throws Exception;
}
