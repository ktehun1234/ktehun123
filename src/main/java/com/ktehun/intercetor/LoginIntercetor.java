package com.ktehun.intercetor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ktehun.domain.UserVO;



public class LoginIntercetor extends HandlerInterceptorAdapter{
	
	private static Logger logger = LoggerFactory.getLogger(LoginIntercetor.class); 
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("로그인 하기 전");
		// TODO Auto-generated method stub
		HttpSession ses = request.getSession();

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HttpSession ses = request.getSession();
		ModelMap modelMap =  modelAndView.getModelMap();
		UserVO vo = (UserVO)modelMap.get("loginMember");
		System.out.println(vo);
		if(vo != null) {
	         logger.info("로그인 성공!!!");
	         
	         logger.info("로그인한 유저 : "+vo.getUid());
	         ses.setAttribute("loginMember", vo);
	         //response.sendRedirect("/");
	         
	         if(request.getParameter("userCookie")!=null) {
	        	 logger.info("쿠키 처리...");
	        	 
	        	 Cookie loginCook = new Cookie("ssid",ses.getId());
	        	 loginCook.setPath("/");
	        	 loginCook.setMaxAge(60*60*24*7);//일주일 동안
	        	 response.addCookie(loginCook);
	         }
	         
	         String dest = (String)ses.getAttribute("dest");
	         
	         response.sendRedirect((dest != null)? dest:"/");
	      }

	}
	
	

}
