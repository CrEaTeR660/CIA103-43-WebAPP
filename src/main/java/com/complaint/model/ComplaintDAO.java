package com.complaint.model;

import javax.sql.DataSource;

import com.utils.datasource.HikariDataSourceUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComplaintDAO implements ComplaintDAO_interface {

	private static final DataSource ds = HikariDataSourceUtil.getDataSource();

	private static final String INSERT_COM = "INSERT INTO complaint (com_id, mem_id, case_id, com_con, com_time, com_st, com_rs) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_COM = "SELECT * FROM complaint ORDER BY com_id";
	private static final String GET_ONE_COM = "SELECT com_id, mem_id, case_id, com_con, com_time, com_st, com_rs FROM complaint WHERE com_id = ?";
	private static final String UPDATE_COM = "UPDATE complaint SET mem_id = ?, case_id = ?, com_con = ?, com_time = ?, com_st = ?, com_rs = ? WHERE com_id = ?";

	@Override
	public void insert(ComplaintVO complaintVO) {
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(INSERT_COM)) {

			pstmt.setInt(1, complaintVO.getComplaintId());
			pstmt.setInt(2, complaintVO.getMemberId());
			pstmt.setInt(3, complaintVO.getCaseId());
			pstmt.setString(4, complaintVO.getComplaintCon());
			pstmt.setTimestamp(5, complaintVO.getComplaintTime());
			pstmt.setByte(6, complaintVO.getComplaintStatus());
			pstmt.setString(7, complaintVO.getComplaintResult());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("新增資料時發生錯誤：" + e.getMessage(), e);
		}
	}

	@Override
	public void update(ComplaintVO complaintVO) {
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(UPDATE_COM)) {

			pstmt.setInt(1, complaintVO.getMemberId());
			pstmt.setInt(2, complaintVO.getCaseId());
			pstmt.setString(3, complaintVO.getComplaintCon());
			pstmt.setTimestamp(4, complaintVO.getComplaintTime());
			pstmt.setByte(5, complaintVO.getComplaintStatus());
			pstmt.setString(6, complaintVO.getComplaintResult());
			pstmt.setInt(7, complaintVO.getComplaintId());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("更新資料時發生錯誤：" + e.getMessage(), e);
		}
	}

	@Override
	public ComplaintVO findByPrimaryKey(Integer complaintId) {
		ComplaintVO complaintVO = null;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(GET_ONE_COM)) {

			pstmt.setInt(1, complaintId);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					complaintVO = new ComplaintVO();
					complaintVO.setComplaintId(rs.getInt("com_id"));
					complaintVO.setMemberId(rs.getInt("mem_id"));
					complaintVO.setCaseId(rs.getInt("case_id"));
					complaintVO.setComplaintCon(rs.getString("com_con"));
					complaintVO.setComplaintTime(rs.getTimestamp("com_time"));
					complaintVO.setComplaintStatus(rs.getByte("com_st"));
					complaintVO.setComplaintResult(rs.getString("com_rs"));
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException("查詢單筆資料時發生錯誤：" + e.getMessage(), e);
		}
		return complaintVO;
	}

	@Override
	public List<ComplaintVO> getAll() {
		List<ComplaintVO> list = new ArrayList<>();

		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(GET_ALL_COM);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				ComplaintVO complaintVO = new ComplaintVO();
				complaintVO.setComplaintId(rs.getInt("com_id"));
				complaintVO.setMemberId(rs.getInt("mem_id"));
				complaintVO.setCaseId(rs.getInt("case_id"));
				complaintVO.setComplaintCon(rs.getString("com_con"));
				complaintVO.setComplaintTime(rs.getTimestamp("com_time"));
				complaintVO.setComplaintStatus(rs.getByte("com_st"));
				complaintVO.setComplaintResult(rs.getString("com_rs"));
				list.add(complaintVO);
			}

		} catch (SQLException e) {
			throw new RuntimeException("查詢所有資料時發生錯誤：" + e.getMessage(), e);
		}
		return list;
	}
}

//	public static void main(String[] args) {
//
//		ComplaintDAO dao = new ComplaintDAO();
//
//		// 測試新增資料
//		System.out.println("=== 測試新增資料 ===");
//		ComplaintVO complaintVO1 = new ComplaintVO();
//		complaintVO1.setComplaintId(1); // 假設 com_id 是自訂主鍵
//		complaintVO1.setMemberId(1);
//		complaintVO1.setCaseId(1);
//		complaintVO1.setComplaintCon("測試新增的投訴內容");
//		complaintVO1.setComplaintTime(Timestamp.valueOf("2024-12-03 10:00:00"));
//		complaintVO1.setComplaintStatus((byte) 0); // 投訴狀態：未處理
//		complaintVO1.setComplaintResult("待處理");
//		dao.insert(complaintVO1);
//		System.out.println("新增完成！");
//
//		// 測試修改資料
//		System.out.println("\n=== 測試修改資料 ===");
//		ComplaintVO complaintVO2 = new ComplaintVO();
//		complaintVO2.setComplaintId(1); // 更新指定的投訴 ID
//		complaintVO2.setMemberId(2);
//		complaintVO2.setCaseId(2);
//		complaintVO2.setComplaintCon("更新後的投訴內容");
//		complaintVO2.setComplaintTime(Timestamp.valueOf("2024-12-03 11:00:00"));
//		complaintVO2.setComplaintStatus((byte) 1); // 投訴狀態：已解決
//		complaintVO2.setComplaintResult("已處理，結果滿意");
//		dao.update(complaintVO2);
//		System.out.println("修改完成！");
//
//		// 測試查詢單筆資料
//		System.out.println("\n=== 測試查詢單筆資料 ===");
//		ComplaintVO complaintVO3 = dao.findByPrimaryKey(1);
//		if (complaintVO3 != null) {
//			System.out.println("查詢結果：");
//			System.out.println("投訴ID：" + complaintVO3.getComplaintId());
//			System.out.println("會員ID：" + complaintVO3.getMemberId());
//			System.out.println("案件ID：" + complaintVO3.getCaseId());
//			System.out.println("投訴內容：" + complaintVO3.getComplaintCon());
//			System.out.println("投訴時間：" + complaintVO3.getComplaintTime());
//			System.out.println("投訴狀態：" + complaintVO3.getComplaintStatus());
//			System.out.println("處理結果：" + complaintVO3.getComplaintResult());
//		} else {
//			System.out.println("找不到指定的投訴記錄！");
//		}
//
//		// 測試查詢所有資料
//		System.out.println("\n=== 測試查詢所有資料 ===");
//		List<ComplaintVO> list = dao.getAll();
//		for (ComplaintVO aComplaint : list) {
//			System.out.println("投訴ID：" + aComplaint.getComplaintId());
//			System.out.println("會員ID：" + aComplaint.getMemberId());
//			System.out.println("案件ID：" + aComplaint.getCaseId());
//			System.out.println("投訴內容：" + aComplaint.getComplaintCon());
//			System.out.println("投訴時間：" + aComplaint.getComplaintTime());
//			System.out.println("投訴狀態：" + aComplaint.getComplaintStatus());
//			System.out.println("處理結果：" + aComplaint.getComplaintResult());
//			System.out.println("---------------------");
//		}
//
//
//		List<ComplaintVO> newList = dao.getAll();
//		if (newList.isEmpty()) {
//			System.out.println("目前沒有投訴記錄！");
//		} else {
//			for (ComplaintVO aComplaint : newList) {
//				System.out.println("投訴ID：" + aComplaint.getComplaintId());
//				System.out.println("會員ID：" + aComplaint.getMemberId());
//				System.out.println("案件ID：" + aComplaint.getCaseId());
//				System.out.println("投訴內容：" + aComplaint.getComplaintCon());
//				System.out.println("投訴時間：" + aComplaint.getComplaintTime());
//				System.out.println("投訴狀態：" + aComplaint.getComplaintStatus());
//				System.out.println("處理結果：" + aComplaint.getComplaintResult());
//				System.out.println("---------------------");
//			}
//		}
//	}
