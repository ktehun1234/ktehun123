package com.ktehun.springproj;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.ktehun.domain.ReplyVO;
import com.ktehun.service.ReplyService;

@Controller // 이 클래스가 REST방식의 컨트로러임을 컴파일러에게 전달
@RequestMapping("/replies")
public class ReplyController {
	@Inject
	private ReplyService service;
	
	@RequestMapping(value = "",method=RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody ReplyVO vo) {
		System.out.println("Rplies.........POST방식 연결");
		ResponseEntity<String> entity = null;
		try {
			service.create(vo);
			entity = new ResponseEntity<String>("Success",HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	@RequestMapping(value = "/all/{bno}",method = RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("bno") int bno){
		
		 ResponseEntity<List<ReplyVO>> entity = null;
		
		 try{
			 entity = new ResponseEntity<List<ReplyVO>>(service.read(bno),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST); //예외가 발생하면 list<RdplyVO)는 null이므로 ..>> ResoinseEntity<>
		}
		 return entity;
		
	}
	@RequestMapping(value="/{no}",method = RequestMethod.PUT)
	public ResponseEntity<String> update(@PathVariable("no") int no,@RequestBody ReplyVO vo){
		//no : 댓글 번호
		//@RequestBody ReplyVO vo : 유저가 수정한 데이터를 json으로 받아 ReplyVO vo에 저장
		ResponseEntity<String> entity = null;
		try {
			vo.setNo(no);
			service.upadte(vo);
			entity = new ResponseEntity<String>("Success",HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	@RequestMapping(value = "/{no}",method = RequestMethod.DELETE)
	public ResponseEntity<String> remove(@PathVariable("no") int no){
		ResponseEntity<String> entity = null;
		try {
			System.out.println(no);
			service.delete(no);
			entity = new ResponseEntity<String>("Success",HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

}
