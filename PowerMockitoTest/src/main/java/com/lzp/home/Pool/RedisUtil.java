package com.lzp.home.Pool;

import java.util.HashMap;
import java.util.Map;

import redis.clients.jedis.Jedis;

public class RedisUtil {

	private static Map<String, Pool> jedisPool = new HashMap();

	public static void init(String poolName) {
		Pool pool = new Pool();
		jedisPool.put(poolName, pool);
	}

	public static Map<String, String> hgetall(String poolName, String redisKey) {
		Jedis jedis = null;
		Pool jedispool = jedisPool.get(poolName);
		if (jedisPool != null) {
			jedis = jedispool.getResource();
		}
		return jedis.hgetAll(redisKey);
	}

	public static String getDefaultJedisId() {
		return "";
	}

}
