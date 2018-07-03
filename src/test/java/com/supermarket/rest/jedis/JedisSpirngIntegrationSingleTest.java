package com.supermarket.rest.jedis;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisSpirngIntegrationSingleTest {
	
	private ApplicationContext context;
	
	private JedisPool pool;
	
	private Jedis jedis;
	
	@Before
	public void init() {
		context=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
	}
	
	@Test
	public void test() {
		pool=(JedisPool) context.getBean("redisSingleClient");
		jedis=pool.getResource();
		jedis.set("key4", "jedis single integration spring test");
		String string = jedis.get("key4");
		System.out.println(string);
	}
	
	@After
	public void destory() {
		jedis.close();
		pool.close();
	}

}
