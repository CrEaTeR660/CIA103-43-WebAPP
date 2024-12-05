package com.complaintphotos.model;

import javax.sql.DataSource;
import com.utils.datasource.HikariDataSourceUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComplaintPhotosDAO implements ComplaintPhotosDAO_interface {

	private static final DataSource ds = HikariDataSourceUtil.getDataSource();

	private static final String INSERT_STMT = "INSERT INTO complaint_photos (COM_ID, COM_PIC, FILE_NAME, MIME_TYPE, UPLOAD_TIME) VALUES (?, ?, ?, ?, NOW())";
	private static final String UPDATE_STMT = "UPDATE complaint_photos SET COM_ID = ?, COM_PIC = ?, FILE_NAME = ?, MIME_TYPE = ?, UPLOAD_TIME = NOW() WHERE COM_PIC_ID = ?";
	private static final String FIND_BY_ID_STMT = "SELECT COM_PIC_ID, COM_ID, COM_PIC, FILE_NAME, MIME_TYPE, UPLOAD_TIME FROM complaint_photos WHERE COM_PIC_ID = ?";
	private static final String FIND_ALL_STMT = "SELECT COM_PIC_ID, COM_ID, COM_PIC, FILE_NAME, MIME_TYPE, UPLOAD_TIME FROM complaint_photos";

	// 新增
	@Override
	public void insert(ComplaintPhotosVO vo) {
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(INSERT_STMT)) {

			pstmt.setInt(1, vo.getComId());
			pstmt.setBytes(2, vo.getComPic());
			pstmt.setString(3, vo.getFileName());
			pstmt.setString(4, vo.getMimeType());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("新增失敗：" + se.getMessage(), se);
		}
	}

//修改
	@Override
	public void update(ComplaintPhotosVO vo) {
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(UPDATE_STMT)) {

			pstmt.setInt(1, vo.getComId());
			pstmt.setBytes(2, vo.getComPic());
			pstmt.setString(3, vo.getFileName());
			pstmt.setString(4, vo.getMimeType());
			pstmt.setInt(5, vo.getComPicId());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("更新失敗：" + se.getMessage(), se);
		}
	}

	//查看單一
	@Override
	public ComplaintPhotosVO findById(int comPicId) {
		ComplaintPhotosVO vo = null;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(FIND_BY_ID_STMT)) {

			pstmt.setInt(1, comPicId);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					vo = new ComplaintPhotosVO();
					vo.setComPicId(rs.getInt("COM_PIC_ID"));
					vo.setComId(rs.getInt("COM_ID"));
					vo.setComPic(rs.getBytes("COM_PIC"));
					vo.setFileName(rs.getString("FILE_NAME"));
					vo.setMimeType(rs.getString("MIME_TYPE"));
					vo.setUploadTime(rs.getDate("UPLOAD_TIME"));
				}
			}

		} catch (SQLException se) {
			throw new RuntimeException("查詢單筆資料失敗：" + se.getMessage(), se);
		}

		return vo;
	}

	//查看所有圖片
	@Override
	public List<ComplaintPhotosVO> findAll() {
		List<ComplaintPhotosVO> list = new ArrayList<>();

		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(FIND_ALL_STMT);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				ComplaintPhotosVO vo = new ComplaintPhotosVO();
				vo.setComPicId(rs.getInt("COM_PIC_ID"));
				vo.setComId(rs.getInt("COM_ID"));
				vo.setComPic(rs.getBytes("COM_PIC"));
				vo.setFileName(rs.getString("FILE_NAME"));
				vo.setMimeType(rs.getString("MIME_TYPE"));
				vo.setUploadTime(rs.getDate("UPLOAD_TIME"));
				list.add(vo);
			}

		} catch (SQLException se) {
			throw new RuntimeException("查詢全部資料失敗：" + se.getMessage(), se);
		}

		return list;
	}
}

//	public static void main(String[] args) {
//		ComplaintPhotosDAO dao = new ComplaintPhotosDAO();
//
////		// 測試新增資料
////		System.out.println("=== 新增資料 ===");
////		ComplaintPhotosVO newPhoto = new ComplaintPhotosVO();
////		newPhoto.setComId(10); // 假設有有效的 COM_ID
////		newPhoto.setComPic("Test Image Data".getBytes()); // 模擬圖片二進制數據
////		newPhoto.setFileName("test_image.jpg");
////		newPhoto.setMimeType("image/jpeg"); // MIME_TYPE
////		dao.insert(newPhoto);
////		System.out.println("新增完成！");
//
//		// 測試查詢單筆資料
//		System.out.println("\n=== 查詢單筆資料 ===");
//		int testId = 3; // 替換為存在於資料庫的 COM_PIC_ID
//		ComplaintPhotosVO singlePhoto = dao.findById(testId);
//		if (singlePhoto != null) {
//			System.out.println("查詢結果：");
//			System.out.println("照片ID：" + singlePhoto.getComPicId());
//			System.out.println("申訴ID：" + singlePhoto.getComId());
//			System.out.println("檔案名稱：" + singlePhoto.getFileName());
//			System.out.println("MIME 類型：" + singlePhoto.getMimeType());
//			System.out.println("上傳時間：" + singlePhoto.getUploadTime());
//		} else {
//			System.out.println("找不到該資料！");
//		}
//
//		// 測試更新資料
//		System.out.println("\n=== 更新資料 ===");
//		if (singlePhoto != null) {
//			singlePhoto.setFileName("updated_image.jpg");
//			singlePhoto.setComPic("Updated Image Data".getBytes()); // 模擬新的圖片二進制數據
//			singlePhoto.setMimeType("image/png"); // 更新 MIME_TYPE
//			dao.update(singlePhoto);
//			System.out.println("更新完成！");
//		} else {
//			System.out.println("無法更新，因為找不到該資料！");
//		}
//
//		// 測試查詢全部資料
//		System.out.println("\n=== 查詢全部資料 ===");
//		List<ComplaintPhotosVO> allPhotos = dao.findAll();
//		for (ComplaintPhotosVO photo : allPhotos) {
//			System.out.println("照片ID：" + photo.getComPicId() + ", 申訴ID：" + photo.getComId() + ", 檔案名稱："
//					+ photo.getFileName() + ", MIME 類型：" + photo.getMimeType() + ", 上傳時間：" + photo.getUploadTime());
//		}
//	}
//}
