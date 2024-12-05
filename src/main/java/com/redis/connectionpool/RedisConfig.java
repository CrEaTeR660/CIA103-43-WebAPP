package com.redis.connectionpool;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisConfig {
    private static JedisPool jedisPool;

    // 初始化 JedisPool
    static {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(128); // 最大连接数
        poolConfig.setMaxIdle(64);   // 最大空闲连接数
        poolConfig.setMinIdle(16);   // 最小空闲连接数
        poolConfig.setTestOnBorrow(true); // 在借用连接时检查连接有效性

        // Redis 配置
        jedisPool = new JedisPool(poolConfig, "localhost", 6379); // Redis 地址和端口
    }

    // 获取 Jedis 连接池
    public static JedisPool getJedisPool() {
        return jedisPool;
    }
}
