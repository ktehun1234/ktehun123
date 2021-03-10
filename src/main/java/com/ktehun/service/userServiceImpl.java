package com.ktehun.service;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ktehun.DTO.LoginDTO;
import com.ktehun.domain.UserVO;
import com.ktehun.persistence.UserDAO;

@Service
public class userServiceImpl implements UserService {
	@Inject
	private UserDAO dao;
	
	public UserVO login(LoginDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return dao.login(dto);
	}

	@Override
	public UserVO checkUserWitrhSesKey(String sesKey) throws Exception {
		// TODO Auto-generated method stub
		return dao.checkUserWitrhSesKey(sesKey);
	}

	@Override
	public void keepLogin(String uid, String sesId, Date cookieAge) throws Exception {
		dao.keepLogin(uid, sesId, cookieAge);
		
	}

}
