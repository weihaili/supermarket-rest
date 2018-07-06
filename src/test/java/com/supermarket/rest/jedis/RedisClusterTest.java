package com.supermarket.rest.jedis;

import java.util.HashSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

/**
 * @author Admin
 *redis-cluster test
 */
public class RedisClusterTest {
	
	private JedisCluster cluster;
	private String redisIp="192.168.25.133";
	
	@Before
	public void init() {
		HashSet<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort(redisIp, 7001));
		nodes.add(new HostAndPort(redisIp, 7002));
		nodes.add(new HostAndPort(redisIp, 7003));
		nodes.add(new HostAndPort(redisIp, 7004));
		nodes.add(new HostAndPort(redisIp, 7005));
		nodes.add(new HostAndPort(redisIp, 7006));
		nodes.add(new HostAndPort(redisIp, 7007));
		nodes.add(new HostAndPort(redisIp, 7008));
		cluster = new JedisCluster(nodes);
	}
	
	@Test
	public void test() {
		cluster.set("key3", "reids cluster test");
		String str = cluster.get("key3");
		System.out.println(str);
	}
	
	@After
	public void destory() {
		cluster.close();
	}

}
