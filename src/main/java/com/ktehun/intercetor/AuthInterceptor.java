package com.ktehun.intercetor;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.ktehun.domain.UserVO;
import com.ktehun.service.UserService;

import sun.util.logging.resources.logging;

public class AuthInterceptor extends HandlerInterceptorAdapter{

	@Inject
	private UserService uService;
	
	private static Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession ses = request.getSession();
		if(ses.getAttribute("loginMember") == null) {
			logger.info("현재 유저는 login 하지 않았다...");
			
			writeDest(request, ses);
			
			Cookie loggingCook = WebUtils.getCookie(request,"ssid");
			if(loggingCook != null) {
				// 로그인 쿠키가 있다면 (자동 로그인을 체크하고 7일 이내에 로그인 했던 유저)
				UserVO vo = uService.checkUserWitrhSesKey(loggingCook.getValue());
				
				logger.info("자동 로그인을 체크하고 7일 이내 로그인을 했던 유저 "+vo);
				if(vo != null) {
					ses.setAttribute("loginMember",vo); //로그인 처리
					return true;
				}
				
			}
			response.sendRedirect("/user/login"); // /user/login URI를 GET방식으로 호출 (login.jsp로 이동)
			return false;
				
		}
		return true;
	} 
	/**
	 *@Method Name : writeDest
	 *@작성일	2021. 3. 9.
	 *@작성자 goott6
	 *@변경이력 :
	 *@Method 설명 :
	 *@param request
	 */
	private void writeDest(HttpServletRequest request,HttpSession ses) {
		String uri = request.getRequestURI(); // 요청된 주소를 얻어옴
		String queryStr = request.getQueryString(); // 쿼리스트링을 얻어옴
		
		if(queryStr == null || queryStr.equals("")) {
			queryStr = "";
			
		}else {
			queryStr="?"+queryStr;
		}
		if(request.getMethod().toUpperCase().equals("GET")) {
			logger.info("최종 목적지 : "+(uri+queryStr));
			ses.setAttribute("dest", uri+queryStr);
		}
	}
	
	
	
	
}
