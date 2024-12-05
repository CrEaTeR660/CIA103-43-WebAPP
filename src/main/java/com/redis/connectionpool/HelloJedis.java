package com.redis.connectionpool;

import redis.clients.jedis.Jedis;

public class HelloJedis {

	public static void main(String[] args) {
		Jedis jedis = null;
		try {
			jedis = new Jedis("localhost", 6379);
			System.out.println(jedis.ping());
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}
}