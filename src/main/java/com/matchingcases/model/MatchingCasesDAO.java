package com.matchingcases.model;

import java.sql.*;
import java.util.*;
import com.utils.datasource.HikariDataSourceUtil;

import javax.sql.DataSource;

public class MatchingCasesDAO implements MatchingCasesDAO_interface {

	private static final DataSource ds = HikariDataSourceUtil.getDataSource();

	private static final String INSERT_STMT = "INSERT INTO matching_cases (MEM_ID, RECEIVER_ID, TITLE, DESCRIPTION, BUDGET, START_DATE, END_DATE, STATUS, CREATED_AT, CASE_TOT) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, NOW(), ?)";
	private static final String UPDATE_STMT = "UPDATE matching_cases SET RECEIVER_ID=?, TITLE=?, DESCRIPTION=?, BUDGET=?, START_DATE=?, END_DATE=?, STATUS=?, CASE_TOT=? WHERE CASE_ID=?";
	private static final String GET_ONE_STMT = "SELECT CASE_ID, MEM_ID, RECEIVER_ID, TITLE, DESCRIPTION, BUDGET, START_DATE, END_DATE, STATUS, CREATED_AT, CASE_TOT FROM matching_cases WHERE CASE_ID=?";
	private static final String GET_ALL_STMT = "SELECT CASE_ID, MEM_ID, RECEIVER_ID, TITLE, DESCRIPTION, BUDGET, START_DATE, END_DATE, STATUS, CREATED_AT, CASE_TOT FROM matching_cases";

	@Override
	public void insert(MatchingCasesVO matchingCasesVO) {
		try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(INSERT_STMT)) {

			stmt.setInt(1, matchingCasesVO.getMemId());
			stmt.setInt(2, matchingCasesVO.getReceiverId());
			stmt.setString(3, matchingCasesVO.getTitle());
			stmt.setString(4, matchingCasesVO.getDescription());
			stmt.setBigDecimal(5, matchingCasesVO.getBudget());
			stmt.setTimestamp(6, matchingCasesVO.getStartDate());
			stmt.setTimestamp(7, matchingCasesVO.getEndDate());
			stmt.setInt(8, matchingCasesVO.getStatus());
			stmt.setInt(9, matchingCasesVO.getCaseTot());
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(MatchingCasesVO matchingCasesVO) {
		try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(UPDATE_STMT)) {

			stmt.setInt(1, matchingCasesVO.getReceiverId());
			stmt.setString(2, matchingCasesVO.getTitle());
			stmt.setString(3, matchingCasesVO.getDescription());
			stmt.setBigDecimal(4, matchingCasesVO.getBudget());
			stmt.setTimestamp(5, matchingCasesVO.getStartDate());
			stmt.setTimestamp(6, matchingCasesVO.getEndDate());
			stmt.setInt(7, matchingCasesVO.getStatus());
			stmt.setInt(8, matchingCasesVO.getCaseTot());
			stmt.setInt(9, matchingCasesVO.getCaseId());
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public MatchingCasesVO getOne(int caseId) {
		MatchingCasesVO matchingCasesVO = null;
		try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(GET_ONE_STMT)) {

			stmt.setInt(1, caseId);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					matchingCasesVO = new MatchingCasesVO();
					matchingCasesVO.setCaseId(rs.getInt("CASE_ID"));
					matchingCasesVO.setMemId(rs.getInt("MEM_ID"));
					matchingCasesVO.setReceiverId(rs.getInt("RECEIVER_ID"));
					matchingCasesVO.setTitle(rs.getString("TITLE"));
					matchingCasesVO.setDescription(rs.getString("DESCRIPTION"));
					matchingCasesVO.setBudget(rs.getBigDecimal("BUDGET"));
					matchingCasesVO.setStartDate(rs.getTimestamp("START_DATE"));
					matchingCasesVO.setEndDate(rs.getTimestamp("END_DATE"));
					matchingCasesVO.setStatus(rs.getInt("STATUS"));
					matchingCasesVO.setCreatedAt(rs.getTimestamp("CREATED_AT"));
					matchingCasesVO.setCaseTot(rs.getInt("CASE_TOT"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return matchingCasesVO;
	}

	@Override
	public List<MatchingCasesVO> getAll() {
		List<MatchingCasesVO> list = new ArrayList<>();
		try (Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(GET_ALL_STMT);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				MatchingCasesVO matchingCasesVO = new MatchingCasesVO();
				matchingCasesVO.setCaseId(rs.getInt("CASE_ID"));
				matchingCasesVO.setMemId(rs.getInt("MEM_ID"));
				matchingCasesVO.setReceiverId(rs.getInt("RECEIVER_ID"));
				matchingCasesVO.setTitle(rs.getString("TITLE"));
				matchingCasesVO.setDescription(rs.getString("DESCRIPTION"));
				matchingCasesVO.setBudget(rs.getBigDecimal("BUDGET"));
				matchingCasesVO.setStartDate(rs.getTimestamp("START_DATE"));
				matchingCasesVO.setEndDate(rs.getTimestamp("END_DATE"));
				matchingCasesVO.setStatus(rs.getInt("STATUS"));
				matchingCasesVO.setCreatedAt(rs.getTimestamp("CREATED_AT"));
				matchingCasesVO.setCaseTot(rs.getInt("CASE_TOT"));
				list.add(matchingCasesVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
