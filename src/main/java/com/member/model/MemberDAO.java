package com.member.model;

import java.sql.*;
import java.util.*;

import javax.sql.DataSource;

import com.utils.datasource.HikariDataSourceUtil;

public class MemberDAO {
	private static final DataSource ds = HikariDataSourceUtil.getDataSource();

	private static final String INSERT_STMT = "INSERT INTO member (mem_id, mem_lv_id, mem_name, mem_uid, mem_bth, mem_gender, mem_email, mem_tel, mem_add, mem_acc, mem_pw, mem_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
	private static final String GET_ALL_STMT = "SELECT mem_id, mem_lv_id, mem_name, mem_uid, mem_bth, mem_gender, mem_email, mem_tel, mem_add, mem_acc, mem_pw, mem_status FROM member order by mem_id";
	private static final String GET_ONE_STMT = "SELECT member_id, mem_lv_id, mem_name,mem_uid, mem_bth, mem_gender, mem_email, mem_tel, mem_add, mem_acc, mem_pw, mem_status  FROM member where mem_id= ?";
	private static final String DELETE = "DELETE FROM member where mem_id = ?";
	private static final String UPDATE = "UPDATE member set mem_id=?, mem_lv_id=?, mem_name=?, mem_uid=?, mem_bth=?, mem_gender=?, mem_email=?, mem_tel=?, mem_add=?, mem_acc=? mem_pw=? mem_status=? where mem_id = ?";

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
			pstmt.setByte(6, memberVO.getMemberGender());
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
			pstmt.setByte(6, memberVO.getMemberGender());
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
				// MemberVO 也稱為 Domain objects
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
				// MemberVO �]�٬� Domain objects
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
}