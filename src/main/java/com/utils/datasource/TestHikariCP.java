package com.utils.datasource;


		import javax.sql.DataSource;
		import java.sql.Connection;
		import java.sql.PreparedStatement;
		import java.sql.ResultSet;

		public class TestHikariCP {

		    public static void main(String[] args) {
		        // 獲取數據源
		        DataSource dataSource = HikariDataSourceUtil.getDataSource();

		        // SQL 查詢語句
		        String sql = "SELECT mem_id, mem_name, mem_email FROM member";

		        try (Connection connection = dataSource.getConnection();
		             PreparedStatement pstmt = connection.prepareStatement(sql);
		             ResultSet rs = pstmt.executeQuery()) {

		            // 打印查詢結果
		            while (rs.next()) {
		                int memId = rs.getInt("mem_id");
		                String memName = rs.getString("mem_name");
		                String memEmail = rs.getString("mem_email");
		                System.out.println("會員 ID: " + memId + ", 名稱: " + memName + ", Email: " + memEmail);
		            }
		        } catch (Exception e) {
		            System.err.println("資料查詢失敗：" + e.getMessage());
		            e.printStackTrace();
		        }
		    }
		}
