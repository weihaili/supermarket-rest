package com.supermarket.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.supermarket.common.utils.ExceptionUtil;
import com.supermarket.common.utils.KklResult;
import com.supermarket.rest.dao.JedisClient;
import com.supermarket.rest.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService {
	@Autowired
	@Qualifier("jedisClinetSingle")
	private JedisClient jedisClient;
	
	@Value("${INDEX_CONTENT_CATEGORY_ID_KEY}")
	private String INDEX_CONTENT_CATEGORY_ID_KEY;

	@Override
	public KklResult syncContent(long contentCid) {
		long result=0;
		try {
			result = jedisClient.hdel(INDEX_CONTENT_CATEGORY_ID_KEY, contentCid+"");
			
		} catch (Exception e) {
			e.printStackTrace();
			return KklResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return KklResult.ok(result);
	}

}
