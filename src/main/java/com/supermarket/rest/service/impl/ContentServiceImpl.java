package com.supermarket.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supermarket.mapper.TbContentMapper;
import com.supermarket.pojo.TbContent;
import com.supermarket.pojo.TbContentExample;
import com.supermarket.pojo.TbContentExample.Criteria;
import com.supermarket.rest.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService{
	
	@Autowired
	private TbContentMapper contentMapper;

	@Override
	public List<TbContent> getContentByParentId(Long categoryId) {
		TbContentExample example=new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
		return list;
	}

	
}
