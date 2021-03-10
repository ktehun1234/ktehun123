package com.ktehun.persistence;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ktehun.DTO.LoginDTO;
import com.ktehun.domain.UserVO;
@Repository
public class UserDAOImpl implements UserDAO {
	@Inject
	private SqlSession ses;
	
	private static String namespace = "com.ktehun.mapper.UserMapper";
	
	@Override
	public UserVO login(LoginDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return ses.selectOne(namespace+".login",dto);
	}

	@Override
	public UserVO checkUserWitrhSesKey(String sesKey) throws Exception {
		// TODO Auto-generated method stub
		return ses.selectOne(namespace+".checkUserWithSesKey",sesKey);
	}

	@Override
	public void keepLogin(String uid, String sesId, Date cookieAge) throws Exception {
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("uid",uid);
		paraMap.put("sesid",sesId);
		paraMap.put("cookieAge",cookieAge);
		ses.update(namespace+".keepLogin",paraMap);
	}

}
