package com.complaint.model;

import java.util.*;
import java.sql.*;

public class ComplaintJDBCDAO implements ComplaintDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/voicebus?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "tony797977";

	// mem_Id,case_id(FK)
	private static final String INSERT_COM = "INSERT INTO complaint (com_id,com_con,com_time,com_st,com_rs) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_COM = "SELECT * FROM complaint order by com_id";
	private static final String GET_ONE_COM = "SELECT com_id,com_con,com_time,com_st,com_rs FROM complaint where com_id = ?";
	private static final String DELETE_COM = "DELETE FROM complaint  where com_id= ?";
	private static final String UPDATE_COM = "UPDATE complaint set com_id=?, com_con=?, com_time=?, com_st=?, com_rs=? where com_id= 1";

	public void insert(ComplaintVO complaintVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_COM);

			pstmt.setInt(1, complaintVO.getComplaintId());
//			pstmt.setInt(2, complaintVO.getMemberid());
//			pstmt.setInt(3, complaintVO.getCaseid());
			pstmt.setString(2, complaintVO.getComplaintCon());
			pstmt.setTimestamp(3, complaintVO.getComplaintTime());
			pstmt.setByte(4, complaintVO.getComplaintStatus());
			pstmt.setString(5, complaintVO.getComplaintResult());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	public void update(ComplaintVO complaintVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_COM);

			pstmt.setInt(1, complaintVO.getComplaintId());
//			pstmt.setInt(2, complaintVO.getMemberid());
//			pstmt.setInt(3, complaintVO.getCaseid());
			pstmt.setString(2, complaintVO.getComplaintCon());
			pstmt.setTimestamp(3, complaintVO.getComplaintTime());
			pstmt.setByte(4, complaintVO.getComplaintStatus());
			pstmt.setString(5, complaintVO.getComplaintResult());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(Integer complaintid) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_COM);

			pstmt.setInt(1, complaintid);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public ComplaintVO findByPrimaryKey(Integer complaintid) {

		ComplaintVO complaintVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_COM);

			pstmt.setInt(1, complaintid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
//				 empVo 也稱為 Domain objects
				complaintVO = new ComplaintVO();
				complaintVO.setComplaintId(rs.getInt("com_id"));
//				complaintVO.setMemberid(rs.getInt("mem_id"));
//				complaintVO.setCaseid(rs.getInt("case_id"));
				complaintVO.setComplaintCon(rs.getString("com_con"));
				complaintVO.setComplaintTime(rs.getTimestamp("com_time"));
				complaintVO.setComplaintStatus(rs.getByte("com_st"));
				complaintVO.setComplaintResult(rs.getString("com_rs"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return complaintVO;
	}

	@Override
	public List<ComplaintVO> getAll() {
		List<ComplaintVO> list = new ArrayList<ComplaintVO>();
		ComplaintVO complaintVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_COM);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				complaintVO = new ComplaintVO();
				complaintVO.setComplaintId(rs.getInt("com_id"));
//				complaintVO.setMemberid(rs.getInt("memberid"));
//				complaintVO.setCaseid(rs.getInt("caseid"));
				complaintVO.setComplaintCon(rs.getString("com_con"));
				complaintVO.setComplaintTime(rs.getTimestamp("com_time"));
				complaintVO.setComplaintStatus(rs.getByte("com_st"));
				complaintVO.setComplaintResult(rs.getString("com_rs"));
				list.add(complaintVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {

		ComplaintJDBCDAO dao = new ComplaintJDBCDAO();

//		 新增
//		ComplaintVO complaintVO1 = new ComplaintVO();
//		complaintVO1.setComplaintId(5);
////		complaintVO1.setMemberid(1);
////		complaintVO1.setCaseid(1);
//		complaintVO1.setComplaintCon("試音員不配合");
//		complaintVO1.setComplaintTime(Timestamp.valueOf("2024-10-31 00:00:00"));
//		complaintVO1.setComplaintStatus((byte) 0);
//		complaintVO1.setComplaintresult("媒合另外的試音員");
//		dao.insert(complaintVO1);

//		// 修改
//		ComplaintVO complaintVO2 = new ComplaintVO();
//		complaintVO2.setComplaintId(1);
////		complaintVO2.setMemberid(1);
////		complaintVO2.setCaseid(1);
//		complaintVO2.setComplaintCon("廠商提供價位過高");
//		complaintVO2.setComplaintTime(Timestamp.valueOf("2024-11-01 00:00:00"));
//		complaintVO2.setComplaintStatus((byte) 0);
//		complaintVO2.setComplaintresult("內容不屬實予以駁回");
//		dao.update(complaintVO2);
//
//		// 刪除
//		dao.delete(1);
//
//	    // 查詢
//		ComplaintVO complaintVO3 = dao.findByPrimaryKey(2);
//		System.out.print(complaintVO3.getComplaintId() + ",");
////		System.out.print(complaintVO3.getMemberid() + ",");
////		System.out.print(complaintVO3.getCaseid() + ",");
//		System.out.print(complaintVO3.getComplaintCon() + ",");
//		System.out.print(complaintVO3.getComplaintTime() + ",");
//		System.out.print(complaintVO3.getComplaintStatus() + ",");
//		System.out.println(complaintVO3.getComplaintresult());
//		System.out.println("---------------------");
//
//		// 查詢
		List<ComplaintVO> list = dao.getAll();
		for (ComplaintVO aComplaint : list) {
			System.out.print(aComplaint.getComplaintId() + ",");
//			System.out.print(aComplaint.getMemberid() + ",");
//			System.out.print(aComplaint.getCaseid() + ",");
			System.out.print(aComplaint.getComplaintCon() + " ,");
			System.out.print(aComplaint.getComplaintTime() + ",");
			System.out.print(aComplaint.getComplaintStatus() + ",");
			System.out.print(aComplaint.getComplaintResult());
			System.out.println();
		}
	}

}