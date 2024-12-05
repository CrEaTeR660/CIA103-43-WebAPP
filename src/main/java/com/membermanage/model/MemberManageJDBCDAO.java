package com.membermanage.model;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberManageJDBCDAO implements MemberManageDAO_interface {

    // 資料庫連線資訊
    private static final String DB_URL = "jdbc:mysql://localhost:3306/voicebus?serverTimezone=Asia/Taipei";
    private static final String DB_USER = "toot";
    private static final String DB_PASSWORD = "tony797977";

    // SQL 語句
    private static final String GET_ALL_SQL = "SELECT * FROM MEMBER";
    private static final String GET_ONE_SQL = "SELECT * FROM MEMBER WHERE MEM_ID = ?";
    private static final String UPDATE_SQL = "UPDATE MEMBER SET MEM_LV_ID = ?, MEM_STATUS = ? WHERE MEM_ID = ?";

    @Override
    public List<MemberManageVO> getAll() {
        List<MemberManageVO> list = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = connection.prepareStatement(GET_ALL_SQL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                MemberManageVO member = new MemberManageVO();
                member.setMemberId(rs.getInt("MEM_ID"));
                member.setMemberLvId(rs.getByte("MEM_LV_ID"));
                member.setMemberName(rs.getString("MEM_NAME"));
                member.setMemberStatus(rs.getByte("MEM_STATUS"));
                list.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public MemberManageVO findByPrimaryKey(Integer memberId) {
        MemberManageVO member = null;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = connection.prepareStatement(GET_ONE_SQL)) {

            ps.setInt(1, memberId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    member = new MemberManageVO();
                    member.setMemberId(rs.getInt("MEM_ID"));
                    member.setMemberLvId(rs.getByte("MEM_LV_ID"));
                    member.setMemberName(rs.getString("MEM_NAME"));
                    member.setMemberStatus(rs.getByte("MEM_STATUS"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return member;
    }

    @Override
    public void updateLevelAndStatus(Integer memberId, Byte memberLvId, Byte memberStatus) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = connection.prepareStatement(UPDATE_SQL)) {

            ps.setByte(1, memberLvId);
            ps.setByte(2, memberStatus);
            ps.setInt(3, memberId);

            int rowCount = ps.executeUpdate();
            System.out.println("[訊息] 更新成功，受影響的行數：" + rowCount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
