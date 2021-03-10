package com.ktehun.persistence;

import java.util.Date;

import com.ktehun.DTO.LoginDTO;
import com.ktehun.domain.UserVO;

public interface UserDAO {
	public UserVO login(LoginDTO dto) throws Exception;
	//유저의 세션 ID를 체크
	public UserVO checkUserWitrhSesKey(String sesKey) throws Exception;
	
	//로그인 유지
	public void keepLogin(String uid,String sesId,Date cookieAge) throws Exception;
}
