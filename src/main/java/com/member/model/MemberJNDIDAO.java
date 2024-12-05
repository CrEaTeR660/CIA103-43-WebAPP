package com.member.model;

import java.util.*;
import java.sql.*;
import java.sql.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberJNDIDAO implements MemberDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO member (mem_id, mem_lv_id, mem_name, mem_uid, mem_bth, mem_gender, mem_email, mem_tel, mem_add, mem_acc, mem_pw, mem_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT mem_id, mem_lv_id, mem_name, mem_uid, mem_bth, mem_gender, mem_email, mem_tel, mem_add, mem_acc, mem_pw, mem_status FROM member order by mem_id";
	private static final String GET_ONE_STMT = "SELECT mem_id, mem_lv_id, mem_name, mem_uid, mem_bth, mem_gender, mem_email, mem_tel, mem_add, mem_acc, mem_pw, mem_status FROM member where mem_id = ?";
	private static final String DELETE = "DELETE FROM member where mem_id = ?";
	private static final String UPDATE = "UPDATE member set mem_id=?, mem_lv_id=?, mem_name=?, mem_uid=?, mem_bth=?, mem_gender=?, mem_email=?, mem_tel=?, mem_add=?, mem_acc=? mem_pw=? mem_status=? where mem_id = ?";

	@Override
	public void insert(MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, memberVO.getMemberId());
			pstmt.setByte(2, memberVO.getMemberLvId());
			pstmt.setString(3, memberVO.getMemberName());
			pstmt.setString(4, memberVO.getMemberUid());
			pstmt.setDate(5, memberVO.getMemberBth());
			pstmt.setInt(6, memberVO.getMemberGender());
			pstmt.setString(7, memberVO.getMemberEmail());
			pstmt.setString(8, memberVO.getMemberTel());
			pstmt.setString(9, memberVO.getMemberAdd());
			pstmt.setString(10, memberVO.getMemberAcc());
			pstmt.setString(11, memberVO.getMemberPw());
			pstmt.setByte(12, memberVO.getMemberStatus());

			pstmt.executeUpdate();

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, memberVO.getMemberId());
			pstmt.setByte(2, memberVO.getMemberLvId());
			pstmt.setString(3, memberVO.getMemberName());
			pstmt.setString(4, memberVO.getMemberUid());
			pstmt.setDate(5, memberVO.getMemberBth());
			pstmt.setInt(6, memberVO.getMemberGender());
			pstmt.setString(7, memberVO.getMemberEmail());
			pstmt.setString(8, memberVO.getMemberTel());
			pstmt.setString(9, memberVO.getMemberAdd());
			pstmt.setString(10, memberVO.getMemberAcc());
			pstmt.setString(11, memberVO.getMemberPw());
			pstmt.setByte(12, memberVO.getMemberStatus());
			pstmt.setInt(13, memberVO.getMemberId());

			pstmt.executeUpdate();

			// Handle any driver errors
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, memberId);

			pstmt.executeUpdate();

			// Handle any driver errors
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, memberId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
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
		MemberJNDIDAO dao = new MemberJNDIDAO();

		// 測試新增資料
		System.out.println("=== 測試新增資料 ===");
		MemberVO newMember = new MemberVO();
		newMember.setMemberId(6); // 假設主鍵由資料庫自動生成，這行可以省略
		newMember.setMemberLvId((byte) 2);
		newMember.setMemberName("新增的會員");
		newMember.setMemberUid("A123456789");
		newMember.setMemberBth(Date.valueOf("1990-01-01"));
		newMember.setMemberGender((byte) 1);
		newMember.setMemberEmail("new@example.com");
		newMember.setMemberTel("0912345678");
		newMember.setMemberAdd("台北市信義區松壽路1號");
		newMember.setMemberAcc("new_account");
		newMember.setMemberPw("new_password");
		newMember.setMemberStatus((byte) 1);
		dao.insert(newMember);
		System.out.println("新增完成！");

		// 測試更新資料
		System.out.println("\n=== 測試更新資料 ===");
		MemberVO updateMember = new MemberVO();
		updateMember.setMemberId(6); // 必須設定要更新的會員ID
		updateMember.setMemberLvId((byte) 3);
		updateMember.setMemberName("更新的會員");
		updateMember.setMemberUid("B987654321");
		updateMember.setMemberBth(Date.valueOf("1985-12-31"));
		updateMember.setMemberGender((byte) 0);
		updateMember.setMemberEmail("updated@example.com");
		updateMember.setMemberTel("0987654321");
		updateMember.setMemberAdd("高雄市鼓山區鼓山一路1號");
		updateMember.setMemberAcc("updated_account");
		updateMember.setMemberPw("updated_password");
		updateMember.setMemberStatus((byte) 0);
		dao.update(updateMember);
		System.out.println("更新完成！");

		// 測試刪除資料
		System.out.println("\n=== 測試刪除資料 ===");
		dao.delete(6); // 刪除指定ID的會員
		System.out.println("刪除完成！");

		// 測試查詢單筆資料
		System.out.println("\n=== 測試查詢單筆資料 ===");
		MemberVO member = dao.findByPrimaryKey(1); // 假設ID為1的會員存在
		if (member != null) {
			System.out.println("會員ID：" + member.getMemberId());
			System.out.println("會員等級ID：" + member.getMemberLvId());
			System.out.println("會員姓名：" + member.getMemberName());
			System.out.println("會員身分證字號：" + member.getMemberUid());
			System.out.println("會員生日：" + member.getMemberBth());
			System.out.println("會員性別：" + member.getMemberGender());
			System.out.println("會員Email：" + member.getMemberEmail());
			System.out.println("會員電話：" + member.getMemberTel());
			System.out.println("會員地址：" + member.getMemberAdd());
			System.out.println("會員帳號：" + member.getMemberAcc());
			System.out.println("會員密碼：" + member.getMemberPw());
			System.out.println("會員狀態：" + member.getMemberStatus());
		} else {
			System.out.println("查無資料！");
		}

		// 測試查詢所有資料
		System.out.println("\n=== 測試查詢所有資料 ===");
		List<MemberVO> list = dao.getAll();
		for (MemberVO aMember : list) {
			System.out.println("會員ID：" + aMember.getMemberId());
			System.out.println("會員等級ID：" + aMember.getMemberLvId());
			System.out.println("會員姓名：" + aMember.getMemberName());
			System.out.println("會員身分證字號：" + aMember.getMemberUid());
			System.out.println("會員生日：" + aMember.getMemberBth());
			System.out.println("會員性別：" + aMember.getMemberGender());
			System.out.println("會員Email：" + aMember.getMemberEmail());
			System.out.println("會員電話：" + aMember.getMemberTel());
			System.out.println("會員地址：" + aMember.getMemberAdd());
			System.out.println("會員帳號：" + aMember.getMemberAcc());
			System.out.println("會員密碼：" + aMember.getMemberPw());
			System.out.println("會員狀態：" + aMember.getMemberStatus());
			System.out.println("---------------------");
		}
	}

}