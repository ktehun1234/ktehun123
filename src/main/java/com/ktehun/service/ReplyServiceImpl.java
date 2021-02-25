package com.ktehun.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ktehun.domain.ReplyVO;
import com.ktehun.persistence.ReplyDAO;
@Service
public class ReplyServiceImpl implements ReplyService {
@Inject
private ReplyDAO dao;
	@Override
	public void create(ReplyVO vo) throws Exception {
		dao.create(vo);
	}

	@Override
	public List<ReplyVO> read(int no) throws Exception {
		
		return dao.read(no);
	}

	@Override
	public void upadte(ReplyVO vo) throws Exception {
			dao.upadte(vo);;
	}

	@Override
	public void delete(int no) throws Exception {
		dao.delete(no);
	}

}
