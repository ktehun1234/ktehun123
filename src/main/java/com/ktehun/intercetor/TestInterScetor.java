package com.ktehun.intercetor;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ktehun.domain.BoardVO;

public class TestInterScetor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("************ Test Interceptor **************");
		System.out.println(request.getMethod());
		
		return true;
	}

//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//		System.out.println("************ Test Interceptor(controller 다녀옴) **************");
//		Map<String,Object> mpdelMap = modelAndView.getModel();
//		List<BoardVO> lst = (List<BoardVO>) mpdelMap.get("boardList");
//		System.out.println(lst.toString());
//	}
	
	
}
