package com.supermarket.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.supermarket.common.utils.ExceptionUtil;
import com.supermarket.common.utils.KklResult;
import com.supermarket.pojo.TbContent;
import com.supermarket.rest.service.ContentService;

/**
 * @author Admin
 * content manager controller
 */
@Controller
public class ContentController {
	
	@Autowired
	private ContentService service;
	
	/**
	 * url:/rest/content/list/{categoryId}
	 * get content list
	 * @return
	 */
	@RequestMapping("/content/list/{categoryId}")
	@ResponseBody
	public KklResult getContentList(@PathVariable Long categoryId) {
		List<TbContent> list=null;
		try {
			list = service.getContentByParentId(categoryId);
		} catch (Exception e) {
			e.printStackTrace();
			return KklResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return KklResult.ok(list);
	}

}
