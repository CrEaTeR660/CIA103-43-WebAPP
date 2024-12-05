package com.member.model;

import java.sql.Date;
import java.util.List;

public class MemberService {

	private MemberDAO_interface dao;

	public MemberService() {
		dao = new MemberJDBCDAO();
	}

	public MemberVO addMember(Integer memberId, Byte memberLvId, String memberName, String memberUid,
			Date memberBth, Byte memberGender, String memberEmail, String memberTel, String memberAdd,
			String memberAcc, String memberPw, Byte memberStatus) {

		MemberVO MemberVO = new MemberVO();

		MemberVO.setMemberId(memberId);
		MemberVO.setMemberLvId(memberLvId);
		MemberVO.setMemberName(memberName);
		MemberVO.setMemberUid(memberUid);
		MemberVO.setMemberBth(memberBth);
		MemberVO.setMemberGender(memberGender);
		MemberVO.setMemberEmail(memberEmail);
		MemberVO.setMemberTel(memberTel);
		MemberVO.setMemberAdd(memberAdd);
		MemberVO.setMemberAcc(memberAcc);
		MemberVO.setMemberPw(memberPw);
		MemberVO.setMemberStatus(memberStatus);
		
		dao.insert(MemberVO);

		return MemberVO;
	}

	public MemberVO updateMember(Integer memberId, Byte memberLvId, String memberName, String memberUid,
			Date memberBth, Byte memberGender, String memberEmail, String memberTel, String memberAdd,
			String memberAcc, String memberPw, Byte memberStatus) {

		MemberVO MemberVO = new MemberVO();

		MemberVO.setMemberId(memberId);
		MemberVO.setMemberLvId(memberLvId);
		MemberVO.setMemberName(memberName);
		MemberVO.setMemberUid(memberUid);
		MemberVO.setMemberBth(memberBth);
		MemberVO.setMemberGender(memberGender);
		MemberVO.setMemberEmail(memberEmail);
		MemberVO.setMemberTel(memberTel);
		MemberVO.setMemberAdd(memberAdd);
		MemberVO.setMemberAcc(memberAcc);
		MemberVO.setMemberPw(memberPw);
		MemberVO.setMemberStatus(memberStatus);
		dao.update(MemberVO);

		return MemberVO;
	}

	public void deleteMember(Integer memberId) {
		dao.delete(memberId);
	}

	public MemberVO getOneMember(Integer memberId) {
		return dao.findByPrimaryKey(memberId);
	}

	public List<MemberVO> getAll() {
		return dao.getAll();
	}
}
