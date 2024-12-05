package com.member.model;

import java.util.*;
import java.sql.*;
import java.sql.Date;

public class MemberJDBCDAO implements MemberDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/voicebus?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "tony797977";

	private static final String INSERT_STMT = "INSERT INTO member (mem_id, mem_lv_id, mem_name, mem_uid, mem_bth, mem_gender, mem_email, mem_tel, mem_add, mem_acc, mem_pw, mem_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
	private static final String GET_ALL_STMT = "SELECT mem_id, mem_lv_id, mem_name, mem_uid, mem_bth, mem_gender, mem_email, mem_tel, mem_add, mem_acc, mem_pw, mem_status FROM member order by mem_id";
	private static final String GET_ONE_STMT = "SELECT mem_id, mem_lv_id, mem_name, mem_uid, mem_bth, mem_gender,mem_email, mem_tel, mem_add, mem_acc, mem_pw, mem_status FROM member where mem_id = ?";
	private static final String DELETE = "DELETE FROM member where mem_id = ?";
	private static final String UPDATE = "UPDATE member set mem_lv_id=?, mem_name=?, mem_uid=?, mem_bth=?, mem_gender=?, mem_email=?, mem_tel=?, mem_add=?, mem_acc=?, mem_pw=?, mem_status=? where mem_id=?";

	@Override
	public void insert(MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, memberVO.getMemberId());
			pstmt.setByte(2, memberVO.getMemberLvId());
			pstmt.setString(3, memberVO.getMemberName());
			pstmt.setString(4, memberVO.getMemberUid());
			pstmt.setDate(5, memberVO.getMemberBth());
			pstmt.setByte(6, memberVO.getMemberGender());
			pstmt.setString(7, memberVO.getMemberEmail());
			pstmt.setString(8, memberVO.getMemberTel());
			pstmt.setString(9, memberVO.getMemberAdd());
			pstmt.setString(10, memberVO.getMemberAcc());
			pstmt.setString(11, memberVO.getMemberPw());
			pstmt.setByte(12, memberVO.getMemberStatus());

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
	public void update(MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			//因為主鍵所以不用更新
			pstmt.setByte(1, memberVO.getMemberLvId());
			pstmt.setString(2, memberVO.getMemberName());
			pstmt.setString(3, memberVO.getMemberUid());
			pstmt.setDate(4, memberVO.getMemberBth());
			pstmt.setByte(5, memberVO.getMemberGender());
			pstmt.setString(6, memberVO.getMemberEmail());
			pstmt.setString(7, memberVO.getMemberTel());
			pstmt.setString(8, memberVO.getMemberAdd());
			pstmt.setString(9, memberVO.getMemberAcc());
			pstmt.setString(10, memberVO.getMemberPw());
			pstmt.setByte(11, memberVO.getMemberStatus());
			pstmt.setInt(12, memberVO.getMemberId());


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
	public void delete(Integer memberId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, memberId);

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
	public MemberVO findByPrimaryKey(Integer memberId) {

		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, memberId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				memberVO = new MemberVO();
				memberVO.setMemberId(rs.getInt("mem_id"));
				memberVO.setMemberLvId(rs.getByte("mem_lv_id"));
				memberVO.setMemberName(rs.getString("mem_name"));
				memberVO.setMemberUid(rs.getString("mem_uid"));
				memberVO.setMemberBth(rs.getDate("mem_bth"));
				memberVO.setMemberGender(rs.getByte("mem_gender"));
				memberVO.setMemberEmail(rs.getString("mem_email"));
				memberVO.setMemberTel(rs.getString("mem_tel"));
				memberVO.setMemberAdd(rs.getString("mem_add"));
				memberVO.setMemberAcc(rs.getString("mem_acc"));
				memberVO.setMemberPw(rs.getString("mem_pw"));
				memberVO.setMemberStatus(rs.getByte("mem_status"));
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
		return memberVO;
	}

	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				memberVO = new MemberVO();
				memberVO.setMemberId(rs.getInt("mem_id"));
				memberVO.setMemberLvId(rs.getByte("mem_lv_id"));
				memberVO.setMemberName(rs.getString("mem_name"));
				memberVO.setMemberUid(rs.getString("mem_uid"));
				memberVO.setMemberBth(rs.getDate("mem_bth"));
				memberVO.setMemberGender(rs.getByte("mem_gender"));
				memberVO.setMemberEmail(rs.getString("mem_email"));
				memberVO.setMemberTel(rs.getString("mem_tel"));
				memberVO.setMemberAdd(rs.getString("mem_add"));
				memberVO.setMemberAcc(rs.getString("mem_acc"));
				memberVO.setMemberPw(rs.getString("mem_pw"));
				memberVO.setMemberStatus(rs.getByte("mem_status"));
				list.add(memberVO); // Store the row in the list
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

		MemberJDBCDAO dao = new MemberJDBCDAO();

		// 新增
		MemberVO memberVO1 = new MemberVO();
		memberVO1.setMemberId(6);
		memberVO1.setMemberLvId((byte) 1);
		memberVO1.setMemberName("湯姆布魯斯");
		memberVO1.setMemberUid("A111111111");
		memberVO1.setMemberBth(Date.valueOf("1999-12-31"));
		memberVO1.setMemberGender((byte)1);
		memberVO1.setMemberEmail("s77777@gamil.com");
		memberVO1.setMemberTel("050000000");
		memberVO1.setMemberAdd("中壢區復興路1號");
		memberVO1.setMemberAcc("11111111");
		memberVO1.setMemberPw("111111");
		memberVO1.setMemberStatus((byte)1);
		dao.insert(memberVO1);

		// 修改
		MemberVO memberVO2 = new MemberVO();
		memberVO2.setMemberId(2);
		memberVO2.setMemberLvId((byte)0);
		memberVO2.setMemberName("萊恩布萊諾");
		memberVO2.setMemberUid("B111111111");
		memberVO2.setMemberBth(Date.valueOf("2000-12-31"));
		memberVO2.setMemberGender((byte)0);
		memberVO2.setMemberEmail("t11111111111@yahoo.com.tw");
		memberVO2.setMemberTel("050000000");
		memberVO2.setMemberAdd("中壢區復興路5號");
		memberVO2.setMemberAcc("222222222");
		memberVO2.setMemberPw("222222");
		memberVO2.setMemberStatus((byte)0);
		dao.update(memberVO2);

		// 刪除
		dao.delete(6);

		// 單一查詢
		MemberVO memberVO3 = dao.findByPrimaryKey(1);
		System.out.print(memberVO3.getMemberId() + ",");
		System.out.print(memberVO3.getMemberLvId() + ",");
		System.out.print(memberVO3.getMemberName() + ",");
		System.out.print(memberVO3.getMemberUid() + ",");
		System.out.print(memberVO3.getMemberBth() + ",");
		System.out.print(memberVO3.getMemberGender() + ",");
		System.out.print(memberVO3.getMemberEmail() + ",");
		System.out.print(memberVO3.getMemberTel() + ",");
		System.out.print(memberVO3.getMemberAdd() + ",");
		System.out.print(memberVO3.getMemberAcc() + ",");
		System.out.print(memberVO3.getMemberPw() + ",");
		System.out.print(memberVO3.getMemberStatus());
		System.out.println("---------------------");

		// 查詢
		List<MemberVO> list = dao.getAll();
		for (MemberVO aMember : list) {
			System.out.print(aMember.getMemberId() + ",");
			System.out.print(aMember.getMemberLvId() + ",");
			System.out.print(aMember.getMemberName() + ",");
			System.out.print(aMember.getMemberUid() + ",");
			System.out.print(aMember.getMemberBth() + ",");
			System.out.print(aMember.getMemberGender() + ",");
			System.out.print(aMember.getMemberEmail() + ",");
			System.out.print(aMember.getMemberTel() + ",");
			System.out.print(aMember.getMemberAdd() + ",");
			System.out.print(aMember.getMemberAcc() + ",");
			System.out.print(aMember.getMemberPw() + ",");
			System.out.print(aMember.getMemberStatus());
			System.out.println();
		}
	}




}