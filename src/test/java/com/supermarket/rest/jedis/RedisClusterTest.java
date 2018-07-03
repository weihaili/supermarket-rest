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
	
	@Before
	public void init() {
		HashSet<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.220.128", 7001));
		nodes.add(new HostAndPort("192.168.220.128", 7002));
		nodes.add(new HostAndPort("192.168.220.128", 7003));
		nodes.add(new HostAndPort("192.168.220.128", 7004));
		nodes.add(new HostAndPort("192.168.220.128", 7005));
		nodes.add(new HostAndPort("192.168.220.128", 7006));
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
