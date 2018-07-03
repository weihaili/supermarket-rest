package com.supermarket.rest.jedis;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.JedisCluster;

public class JedisSpringIntegrationClusterTest {
	
	private JedisCluster cluster;
	
	private ApplicationContext context;
	
	@Before
	public void init() {
		context=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		cluster = (JedisCluster) context.getBean("redisClusterClient");
	}
	
	@Test
	public void test() {
		cluster.set("key5", "jedis integrate spirng redis cluster test");
		String string = cluster.get("key5");
		System.out.println(string);
	}
	
	@After
	public void destory() {
		cluster.close();
	}

}
