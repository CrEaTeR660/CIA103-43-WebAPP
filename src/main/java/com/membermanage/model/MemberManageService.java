package com.membermanage.model;

import java.util.List;

public class MemberManageService {

    private MemberManageDAO dao;

    public MemberManageService() {
        dao = new MemberManageDAO();
    }

    // 取得全部會員資料
    public List<MemberManageVO> getAllMembers() {
        return dao.getAll();
    }

    // 查詢單一會員資料
    public MemberManageVO getOneMember(Integer memberId) {
        return dao.getOne(memberId);
    }

    // 更新會員等級與狀態
    public void updateMemberLevelAndStatus(Integer memberId, Byte memberLvId, Byte memberStatus) {
        MemberManageVO member = new MemberManageVO();
        member.setMemberId(memberId);
        member.setMemberLvId(memberLvId);
        member.setMemberStatus(memberStatus);

        dao.updateLevelAndStatus(member);
    }
}
