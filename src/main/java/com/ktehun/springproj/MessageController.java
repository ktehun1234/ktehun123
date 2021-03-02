package com.ktehun.springproj;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ktehun.domain.MessageVO;
import com.ktehun.service.MessageService;

@RestController //Rest방식의 Controller임을 명식
@RequestMapping("/messages")
public class MessageController {
	@Inject
	private MessageService service;
	@RequestMapping(value = "/",method = RequestMethod.POST)
	public ResponseEntity<String> addMessage(@RequestBody MessageVO vo){
		ResponseEntity<String> entity = null;
		
		try {
			service.addMessage(vo);
			entity = new ResponseEntity<>("Success",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>("Success",HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
