package com.membertag.model;

import java.sql.*;
import java.util.List;

public class TagService {

    private TagDAO tagDAO;
    private TagCache tagCache;

    public TagService() throws SQLException {
        this.tagDAO = new TagDAO();
        this.tagCache = new TagCache();
    }

    // 查詢標籤（優先從 Redis 查詢，若無則從 SQL 查詢）
    public TagVO getTag(int tagId) throws SQLException {
        TagVO tag = null;
        try {
            // 先從 Redis 查詢
            tag = tagCache.getTagFromRedis(tagId);

            if (tag == null) {
                // Redis 中沒有資料，從 SQL 查詢
                tag = tagDAO.getTagById(tagId);

                if (tag != null) {
                    // 查詢到標籤後，更新 Redis
                    tagCache.updateTagInRedis(tag);
                }
            }
        } catch (Exception e) {
            // 日誌記錄 Redis 查詢失敗
            System.err.println("Redis 查詢失敗，改為使用 SQL 查詢：" + e.getMessage());
            // 如果 Redis 出現問題，可以選擇重新從資料庫查詢
            if (tag == null) {
                tag = tagDAO.getTagById(tagId);
            }
        }
        return tag;
    }

 // 查詢會員的所有標籤（優先從 Redis 查詢，若無則從 SQL 查詢）
    public List<TagVO> getTags(int memId) throws SQLException {
        List<TagVO> tags = null;
        try {
            // 先從 Redis 查詢
            tags = tagCache.getTagsFromRedis(memId);

            if (tags == null || tags.isEmpty()) {
                // Redis 中沒有資料，從 SQL 查詢
                tags = tagDAO.getTagsByMemberId(memId);

                if (tags != null && !tags.isEmpty()) {
                    // 查詢到標籤後，更新 Redis
                    tagCache.updateTagsInRedis(memId, tags);
                }
            }
        } catch (Exception e) {
            // 日誌記錄 Redis 查詢失敗
            System.err.println("Redis 查詢失敗，改為使用 SQL 查詢：" + e.getMessage());
            // 如果 Redis 出現問題，可以選擇重新從資料庫查詢
            if (tags == null) {
                tags = tagDAO.getTagsByMemberId(memId);
            }
        }
        return tags;
    }



    // 新增標籤
    public void addTag(TagVO tag) throws SQLException {
        try {
            // 1. 在資料庫中新增標籤
            int tagId = tagDAO.addTag(tag);

            if (tagId != -1) {
                // 2. 在 Redis 中新增標籤
                tag.setTagId(tagId);
                tagCache.updateTagInRedis(tag);
            }
        } catch (SQLException e) {
            // 捕捉並處理 SQL 錯誤
            System.err.println("新增標籤時出錯：" + e.getMessage());
            throw e; // 可以選擇重新拋出或處理
        }
    }

    // 刪除標籤
    public void removeTag(int tagId) throws SQLException {
        try {
            // 1. 刪除資料庫中的標籤
            tagDAO.removeTag(tagId);

            // 2. 刪除 Redis 中的標籤
            tagCache.removeTagFromRedis(tagId);
        } catch (SQLException e) {
            // 捕捉並處理 SQL 錯誤
            System.err.println("刪除標籤時出錯：" + e.getMessage());
            throw e; // 可以選擇重新拋出或處理
        }
    }
}
