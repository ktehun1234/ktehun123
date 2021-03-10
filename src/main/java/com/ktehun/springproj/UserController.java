package com.ktehun.springproj;

import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

import com.ktehun.DTO.LoginDTO;
import com.ktehun.domain.UserVO;
import com.ktehun.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {
	@Inject
	private UserService service;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public void login() {
		
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public void login(LoginDTO dto, HttpSession session, Model model) throws Exception {
		UserVO vo = service.login(dto);
		
		if (vo == null) {
			return;
		}
		
		model.addAttribute("loginMember", vo);
		if(dto.isUserCookie()) {
			int amount = 60 * 60* 24 * 7; // 일주일의 milliSecond를 남겨주고
			
			Date sesLimit = new Date(System.currentTimeMillis() + (amount*100));
			service.keepLogin(dto.getUid(), session.getId(), sesLimit);
			
		}
	}
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public void logout(HttpServletRequest request,HttpServletResponse response, HttpSession session) throws Exception {
		UserVO vo = (UserVO) session.getAttribute("loginMember");
		if(vo != null) {
			session.removeAttribute("loginMember");
			session.invalidate();
			
			Cookie loginCookie = WebUtils.getCookie(request, "ssid");
			if(loginCookie != null) {
				loginCookie.setPath("/");
				loginCookie.setMaxAge(0);
				response.addCookie(loginCookie);
				service.keepLogin(vo.getUid(),session.getId(), new Date());
			}
			
		}
		response.sendRedirect("/");
	}
}
