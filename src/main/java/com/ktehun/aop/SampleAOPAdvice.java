package com.ktehun.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

@Component //현재 클래스가 스프링의 빈으로 인식되도록
@Aspect //AOP기능을 하는 클래스임을 선언
public class SampleAOPAdvice {
	private static final Logger logger = LoggerFactory.getLogger(SampleAOPAdvice.class);
	@Before("execution(* com.ktehun.service.MessageService*.*(..))")
	// com.ktehun.service.MessageService로 시작하는 모든 클래스의 모든 메서드를 지정
	public void startLog(JoinPoint jp) {
		logger.info("-------------------------------------------");
		logger.info("-------------------------------------------");
		logger.info(Arrays.toString(jp.getArgs()));
		
	}
	@Around("execution(* com.ktehun.service.MessageService*.*(..))")
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
		long startTime = System.currentTimeMillis();
		logger.info(Arrays.toString(pjp.getArgs()));
		
		Object result = pjp.proceed();
		
		long endTime = System.currentTimeMillis();
		logger.info(pjp.getSignature().getName() + " : "+(endTime - startTime));
		logger.info("==========================================================");
		return result;	
	}
}
