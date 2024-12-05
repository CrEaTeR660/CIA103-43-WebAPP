package com.membertag.model;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class MemberTagService {

    private JedisPool jedisPool;
    private Connection connection;

    public MemberTagService() throws Exception {
        // 使用 Redis 連接池來管理 Redis 連接
        this.jedisPool = new JedisPool("localhost", 6379);
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/voicebus?serverTimezone=Asia/Taipei", "root", "tony797977");
    }

    public void addTagToMember(int memId, int tagId) throws Exception {
        try (Jedis jedis = jedisPool.getResource()) {
            // 使用管道 (Pipeline) 來批量處理 Redis 操作，提高性能
            Pipeline pipeline = jedis.pipelined();

            // 1. 更新 SQL
            String sql = "INSERT INTO MEMBER_TAG (MEM_ID, TAG_ID) VALUES (?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, memId);
                stmt.setInt(2, tagId);
                stmt.executeUpdate();
            }

            // 2. 更新 Redis
            pipeline.sadd("MEMBER:" + memId + ":TAGS", String.valueOf(tagId));
            pipeline.sadd("TAG:" + tagId + ":MEMBERS", String.valueOf(memId));

            // 批量執行 Redis 操作
            pipeline.sync();

            System.out.println("同步新增成功：MEM_ID=" + memId + ", TAG_ID=" + tagId);
        } catch (Exception e) {
            e.printStackTrace();
            // 這裡可以加上事務回滾機制
        }
    }

    public void removeTagFromMember(int memId, int tagId) throws Exception {
        try (Jedis jedis = jedisPool.getResource()) {
            Pipeline pipeline = jedis.pipelined();

            // 1. 更新 SQL
            String sql = "DELETE FROM MEMBER_TAG WHERE MEM_ID = ? AND TAG_ID = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, memId);
                stmt.setInt(2, tagId);
                stmt.executeUpdate();
            }

            // 2. 更新 Redis
            pipeline.srem("MEMBER:" + memId + ":TAGS", String.valueOf(tagId));
            pipeline.srem("TAG:" + tagId + ":MEMBERS", String.valueOf(memId));

            // 批量執行 Redis 操作
            pipeline.sync();

            System.out.println("同步刪除成功：MEM_ID=" + memId + ", TAG_ID=" + tagId);
        } catch (Exception e) {
            e.printStackTrace();
            // 可以加上錯誤處理
        }
    }
}
