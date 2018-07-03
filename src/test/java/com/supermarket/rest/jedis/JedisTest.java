package com.supermarket.rest.jedis;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

public class JedisTest {
	
	private String host="192.168.220.128";
	private int port=6379;
	
	private Jedis jedis;
	
	/**
	 *create jedis object 
	 */
	@Before
	public void init() {
		jedis=new Jedis(host, port);
	}
	
	@Test
	public void testJedisSingle() {
		//1. create jedis object
		//2. revoke jedis object method,the name of jedis method the same as reids command
		jedis.set("key1", "jedis test");
		String str = jedis.get("key1");
		System.out.println(str);
		//3. close jedis
	}
	
	/**
	 * close jedis
	 */
	@After
	public void destory() {
		jedis.close();
	}

}
