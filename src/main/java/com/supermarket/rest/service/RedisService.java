package com.supermarket.rest.service;

import com.supermarket.common.utils.KklResult;

public interface RedisService {
	
	KklResult syncContent(long contentCid);

}
