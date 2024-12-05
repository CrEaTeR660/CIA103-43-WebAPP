package com.membertag.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.utils.datasource.HikariDataSourceUtil;

public class TagDAO {

	private static final DataSource ds = HikariDataSourceUtil.getDataSource();

	// 查詢標籤
	public TagVO getTagById(int tagId) throws SQLException {
		String query = "SELECT TAG_NAME, TAG_TYPE_NO FROM tag WHERE TAG_ID = ?";
		try (Connection connection = ds.getConnection(); // 從 DataSource 獲取 Connection
				PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, tagId);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					// 將查詢結果轉換為 TagVO 物件
					return new TagVO(tagId, rs.getInt("TAG_TYPE_NO"), rs.getString("TAG_NAME"));
				}
			}
		}
		return null; // 如果查無資料，返回 null
	}

	// 查詢會員的所有標籤
	public List<TagVO> getTagsByMemberId(int memId) throws SQLException {
		List<TagVO> tags = new ArrayList<>();
		String query = "SELECT t.TAG_ID, t.TAG_NAME, t.TAG_TYPE_NO " + "FROM tag t "
				+ "JOIN member_tags mt ON t.TAG_ID = mt.TAG_ID " + "WHERE mt.MEMBER_ID = ?";
		try (Connection connection = ds.getConnection(); // 確保每次使用前獲取連線
				PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, memId);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					// 將查詢結果轉換為 TagVO 物件並加入列表
					int tagId = rs.getInt("TAG_ID");
					String tagName = rs.getString("TAG_NAME");
					int tagTypeNo = rs.getInt("TAG_TYPE_NO");
					tags.add(new TagVO(tagId, tagTypeNo, tagName));
				}
			}
		}
		return tags; // 返回會員的所有標籤
	}

	// 新增標籤
	public int addTag(TagVO tag) throws SQLException {
		String query = "INSERT INTO tag (TAG_NAME, TAG_TYPE_NO) VALUES (?, ?)";
		try (Connection connection = ds.getConnection();
				PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, tag.getTagName());
			stmt.setInt(2, tag.getTagTypeNo());
			stmt.executeUpdate();

			try (ResultSet rs = stmt.getGeneratedKeys()) {
				if (rs.next()) {
					return rs.getInt(1); // 返回自增的 TAG_ID
				}
			}
		}
		return -1; // 如果沒有成功插入則返回 -1
	}

	// 刪除標籤
	public void removeTag(int tagId) throws SQLException {
		String query = "DELETE FROM tag WHERE TAG_ID = ?";
		try (Connection connection = ds.getConnection(); // 確保獲取連線
				PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, tagId);
			stmt.executeUpdate();
		}
	}

	public static void main(String[] args) {
		TagDAO tagDAO = new TagDAO();

//            try {
//                // 測試新增標籤
//                System.out.println("=== 新增標籤 ===");
//                memberTagVO newTag = new memberTagVO();
//                newTag.setTagName("Test Tag");
//                newTag.setTagTypeNo(1); // 假設 1 是有效的類型編號
//                int generatedTagId = tagDAO.addTag(newTag);
//                System.out.println("新增的標籤 ID: " + generatedTagId);
//
//                // 測試查詢單一標籤
//                System.out.println("\n=== 查詢單一標籤 ===");
//                TagVO tag = tagDAO.getTagById(generatedTagId);
//                if (tag != null) {
//                    System.out.println("標籤 ID: " + tag.getTagId());
//                    System.out.println("標籤名稱: " + tag.getTagName());
//                    System.out.println("標籤類型編號: " + tag.getTagTypeNo());
//                } else {
//                    System.out.println("找不到該標籤");
//                }
//
//                // 測試查詢會員的所有標籤
//                System.out.println("\n=== 查詢會員的所有標籤 ===");
//                int memberId = 1; // 假設 1 是有效的會員 ID
//                List<TagVO> memberTags = tagDAO.getTagsByMemberId(memberId);
//                for (TagVO memberTag : memberTags) {
//                    System.out.println("標籤 ID: " + memberTag.getTagId());
//                    System.out.println("標籤名稱: " + memberTag.getTagName());
//                    System.out.println("標籤類型編號: " + memberTag.getTagTypeNo());
//                }
//
//                // 測試刪除標籤
//                System.out.println("\n=== 刪除標籤 ===");
//                tagDAO.removeTag(generatedTagId);
//                System.out.println("標籤已刪除: " + generatedTagId);
//
//            } catch (SQLException e) {
//                e.printStackTrace();
//                System.out.println("操作失敗: " + e.getMessage());
//            }
//        }
	}
}
