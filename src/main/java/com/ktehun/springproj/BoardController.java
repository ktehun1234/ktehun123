package com.ktehun.springproj;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ktehun.domain.BoardVO;
import com.ktehun.service.BoardService;

@Controller
@RequestMapping("/board/*") // /board/의 하위 모든 URI에 대해 현재 클래스가 동일하게 적용
public class BoardController {
	
	@Inject
	private BoardService service;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@RequestMapping(value = "/register",method = RequestMethod.GET)
	public String registerGet(BoardVO vo,Model model) {
		logger.info("/register...get호출");
		return "registerBoard";
	}
	@RequestMapping(value = "register",method = RequestMethod.POST)
	public String registerPOST(BoardVO vo,Model model) throws Exception {
		logger.info("/register...POST호출");
		logger.debug(vo.toString());

		if(service.insert(vo)) {
			model.addAttribute("result","success");
		}
		
		return "/board/success";
	}
}
