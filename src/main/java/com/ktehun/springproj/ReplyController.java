package com.ktehun.springproj;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
	private ResponseEntity<String> register(@RequestBody ReplyVO vo) {
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
}
