package com.ktehun.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktehun.domain.ReplyVO;
import com.ktehun.persistence.BoardDAO;
import com.ktehun.persistence.ReplyDAO;
@Service
public class ReplyServiceImpl implements ReplyService {
@Inject
private ReplyDAO rdao;
@Inject
private BoardDAO bdao;
	@Transactional
	@Override
	public void create(ReplyVO vo) throws Exception {
		rdao.create(vo);
		bdao.updateReply(vo.getBno(), 1); //댓글이 달린 부모글에 댓글 수 1 증가
	}

	@Override
	public List<ReplyVO> read(int bno) throws Exception {
		
		return rdao.read(bno);
	}

	@Override
	public void upadte(ReplyVO vo) throws Exception {
			rdao.upadte(vo);;
	}
	@Transactional
	@Override
	public void delete(int no) throws Exception {
		int bno = rdao.getBno(no);
		rdao.delete(no);
		bdao.updateReply(bno,-1);
	}
	

}
