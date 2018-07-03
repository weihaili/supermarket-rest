package com.supermarket.rest.jedis;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisPoolTest {
	
	private String host="192.168.220.128";
	private int port=6379;
	
	private Jedis jedis;
	private JedisPool pool;

	@Before
	public void init() {
		//1. create jedis pool object
		pool=new JedisPool(host,port);
		//get jedis object form jedis pool
		jedis = pool.getResource();
		
	}
	
	@Test
	public void test() {
		jedis.set("key2", "jedis pool test");
		String str = jedis.get("key2");
		System.out.println(str);
	}
	
	/**
	 *close jedis 
	 *let jedis pool recycling 
	 */
	@After
	public void destory() {
		jedis.close();
		pool.close();
	}

}
