package com.lzp.home.Pool;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;

public class Pool {
	static Logger logger = Logger.getLogger(Pool.class);
	public Jedis getResource() {
		logger.error("调用了Pool的getresource");
		System.out.println("调用了真实的getResource");
		return null;
		
	}

}
