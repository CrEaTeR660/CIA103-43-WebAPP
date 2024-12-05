package com.data.sqltoredis;

import redis.clients.jedis.Jedis;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SQLToRedis {
	public static void main(String[] args) {
		// MySQL 連接資訊
		String jdbcURL = "jdbc:mysql://localhost:3306/voicebus?serverTimezone=Asia/Taipei";
		String dbUser = "root";
		String dbPassword = "tony797977";

		// Redis 連接資訊
		Jedis jedis = new Jedis("localhost", 6379);
		// 切換到 Redis 的 db1
		jedis.select(1);

		try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword)) {

			// 1. 同步 TAG_TYPE 資料到 Redis
			String tagTypesQuery = "SELECT TAG_TYPE_NO, TAG_TYPE_NAME FROM tag_type";
			PreparedStatement tagTypesStmt = connection.prepareStatement(tagTypesQuery);
			ResultSet tagTypesResult = tagTypesStmt.executeQuery();

			while (tagTypesResult.next()) {
				String tagTypeNo = tagTypesResult.getString("TAG_TYPE_NO");
				String tagTypeName = tagTypesResult.getString("TAG_TYPE_NAME");

				// 存入 Redis
				jedis.hset("TAG_TYPE:" + tagTypeNo, "TAG_TYPE_NAME", tagTypeName);
			}

			// 2. 同步 TAG 資料到 Redis
			String tagsQuery = "SELECT TAG_ID, TAG_TYPE_NO, TAG_NAME FROM tag";
			PreparedStatement tagsStmt = connection.prepareStatement(tagsQuery);
			ResultSet tagsResult = tagsStmt.executeQuery();

			while (tagsResult.next()) {
				String tagId = tagsResult.getString("TAG_ID");
				String tagTypeNo = tagsResult.getString("TAG_TYPE_NO");
				String tagName = tagsResult.getString("TAG_NAME");

				// 存入 Redis
				jedis.hset("TAG:" + tagId, "TAG_TYPE_NO", tagTypeNo);
				jedis.hset("TAG:" + tagId, "TAG_NAME", tagName);

				// 將 TAG_ID 加入到 TAG_TYPE 對應的集合
				jedis.sadd("TAG_TYPE:" + tagTypeNo + ":TAGS", tagId);
			}

			// 3. 同步 MEMBER_TAG 資料到 Redis
			String memberTagQuery = "SELECT MEM_ID, TAG_ID FROM member_tag";
			PreparedStatement memberTagStmt = connection.prepareStatement(memberTagQuery);
			ResultSet memberTagResult = memberTagStmt.executeQuery();

			while (memberTagResult.next()) {
				String memId = memberTagResult.getString("MEM_ID");
				String tagId = memberTagResult.getString("TAG_ID");

				// 存入 Redis：將 MEM_ID 加入 TAG 的會員集合
				jedis.sadd("TAG:" + tagId + ":MEMBERS", memId);

				// 存入 Redis：將 TAG_ID 加入 MEMBER 的標籤集合
				jedis.sadd("MEMBER:" + memId + ":TAGS", tagId);
			}

			System.out.println("資料已成功從 SQL 導入 Redis");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
	}
}
