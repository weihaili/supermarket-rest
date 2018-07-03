package com.supermarket.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.supermarket.common.utils.KklResult;
import com.supermarket.rest.service.RedisService;

/**
 * @author Admin
 * cache synchronized
 */
@Controller
@RequestMapping("/cache/sync")
public class RedisController {
	
	@Autowired
	private RedisService redisService;
	
	@RequestMapping(value="/content/{contentCid}")
	@ResponseBody
	public KklResult contentCacheSync(@PathVariable Long contentCid) {
		KklResult result = redisService.syncContent(contentCid);
		return result;
	}
	
	

}
