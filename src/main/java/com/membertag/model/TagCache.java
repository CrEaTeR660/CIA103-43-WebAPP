package com.membertag.model;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TagCache {

	private JedisPool jedisPool;

	public TagCache() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(128); // 設置最大連接數
		poolConfig.setMaxIdle(128); // 設置最大閒置連接數
		poolConfig.setMinIdle(16); // 設置最小閒置連接數
		poolConfig.setTestOnBorrow(true); // 測試連接可用性

		this.jedisPool = new JedisPool(poolConfig, "localhost", 6379);
	}

	// 從 Redis 中獲取單個標籤資料
	public TagVO getTagFromRedis(int tagId) {
		try (Jedis jedis = jedisPool.getResource()) {
			String tagKey = "TAG:" + tagId;
			Map<String, String> tagData = jedis.hgetAll(tagKey);

			if (tagData != null && !tagData.isEmpty()) {
				// 使用 TagVO 來封裝 Redis 中的標籤資料
				return new TagVO(tagId, Integer.parseInt(tagData.get("TAG_TYPE_NO")), tagData.get("TAG_NAME"));
			}
		} catch (Exception e) {
			System.err.println("Redis 查詢失敗：" + e.getMessage());
		}
		return null;
	}

	// 從 Redis 中獲取會員的所有標籤資料
	public List<TagVO> getTagsFromRedis(int memId) {
		List<TagVO> tags = new ArrayList<>();
		try (Jedis jedis = jedisPool.getResource()) {
			String tagsKey = "TAGS:MEMBER:" + memId; // 假設 Redis 中存儲會員標籤的鍵是這個

			// 查詢所有標籤的 ID
			List<String> tagIds = jedis.lrange(tagsKey, 0, -1);

			for (String tagIdStr : tagIds) {
				int tagId = Integer.parseInt(tagIdStr);
				TagVO tag = getTagFromRedis(tagId); // 使用 getTagFromRedis 來獲取單個標籤
				if (tag != null) {
					tags.add(tag); // 如果標籤存在，則加入列表
				}
			}
		} catch (Exception e) {
			System.err.println("Redis 查詢會員標籤失敗：" + e.getMessage());
		}
		return tags; // 如果沒有標籤，將返回空的 List
	}

	// 更新 Redis 中的標籤資料
	public void updateTagInRedis(TagVO tag) {
		try (Jedis jedis = jedisPool.getResource()) {
			String tagKey = "TAG:" + tag.getTagId();
			jedis.hset(tagKey, "TAG_NAME", tag.getTagName());
			jedis.hset(tagKey, "TAG_TYPE_NO", String.valueOf(tag.getTagTypeNo()));
		} catch (Exception e) {
			System.err.println("更新 Redis 標籤資料時發生錯誤：" + e.getMessage());
		}
	}

	// 更新 Redis 中會員的所有標籤資料
	public void updateTagsInRedis(int memId, List<TagVO> tags) {
		try (Jedis jedis = jedisPool.getResource()) {
			String tagsKey = "TAGS:MEMBER:" + memId; // 假設這個鍵是用來存儲會員的所有標籤的

			// 清除原來的標籤 ID 列表
			jedis.del(tagsKey);

			// 為會員存儲所有標籤 ID
			for (TagVO tag : tags) {
				jedis.rpush(tagsKey, String.valueOf(tag.getTagId())); // 將標籤 ID 加入列表
				updateTagInRedis(tag); // 更新每個標籤的資料
			}
		} catch (Exception e) {
			System.err.println("更新 Redis 會員標籤資料時發生錯誤：" + e.getMessage());
		}
	}

	// 刪除 Redis 中的標籤資料
	public void removeTagFromRedis(int tagId) {
		try (Jedis jedis = jedisPool.getResource()) {
			String tagKey = "TAG:" + tagId;
			jedis.del(tagKey);
		} catch (Exception e) {
			System.err.println("刪除 Redis 標籤資料時發生錯誤：" + e.getMessage());
		}
	}
}
