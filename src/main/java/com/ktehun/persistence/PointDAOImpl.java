package com.ktehun.persistence;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class PointDAOImpl implements PointDAO {
	@Inject
	private SqlSession ses;
	private String namespace ="com.ktehun.mapper.PointMapper";
	@Override
	public void updatePoint(String uid, int point) throws Exception {
		 Map<String,Object> param = new HashMap<String,Object>();
		 param.put("uid",uid);
		 param.put("point",point);

		ses.update(namespace+".updatePoint",param);
		
	}

}
