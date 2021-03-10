package com.ktehun.service;

import java.util.Date;

import com.ktehun.DTO.LoginDTO;
import com.ktehun.domain.UserVO;

public interface UserService {
	public UserVO login(LoginDTO dto) throws Exception;
	public UserVO checkUserWitrhSesKey(String sesKey) throws Exception;
	
	//로그인 유지
	public void keepLogin(String uid,String sesId,Date cookieAge) throws Exception;
	
}
