package com.supermarket.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.supermarket.common.utils.JsonUtils;
import com.supermarket.mapper.TbContentMapper;
import com.supermarket.pojo.TbContent;
import com.supermarket.pojo.TbContentExample;
import com.supermarket.pojo.TbContentExample.Criteria;
import com.supermarket.rest.dao.JedisClient;
import com.supermarket.rest.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService{
	
	@Autowired
	@Qualifier("jedisClinetSingle")
	private JedisClient jedisClient;
	
	@Autowired
	private TbContentMapper contentMapper;
	
	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;

	@Override
	public List<TbContent> getContentByParentId(long categoryId) {
		//get data from redis
		try {
			String result = jedisClient.hget(INDEX_CONTENT_REDIS_KEY, categoryId+"");
			if (!StringUtils.isBlank(result)) {
				List<TbContent> cacheList = JsonUtils.jsonToList(result, TbContent.class);
				return cacheList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		TbContentExample example=new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
		//add data to redis
		try {
			//1. convert list to string
			String cacheString = JsonUtils.objectToJson(list);
			//2. save in redis
			jedisClient.hset(INDEX_CONTENT_REDIS_KEY, categoryId+"", cacheString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	
}
