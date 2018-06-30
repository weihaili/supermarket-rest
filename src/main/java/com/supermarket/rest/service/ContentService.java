package com.supermarket.rest.service;

import java.util.List;

import com.supermarket.pojo.TbContent;

public interface ContentService {
	
	List<TbContent> getContentByParentId(Long categoryId);

}
