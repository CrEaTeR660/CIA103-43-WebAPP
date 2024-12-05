package com.membermanage.model;

import java.sql.Date;

public class MemberManageVO implements java.io.Serializable {

	private Integer memberId;
	private Byte memberLvId;
	private String memberName;
	private String memberUid;
	private Date memberBth;
	private Byte memberGender;
	private String memberEmail;
	private String memberTel;
	private String memberAdd;
	private String memberAcc;
	private String memberPw;
	private Byte memberStatus;

	public MemberManageVO() {
		
	}

	public MemberManageVO(Integer memberId, Byte memberLvId, String memberName, String memberUid, Date memberBth,
			Byte memberGender, String memberEmail, String memberTel, String memberAdd, String memberAcc,
			String memberPw, Byte memberStatus) {

		this.memberId = memberId;
		this.memberLvId = memberLvId;
		this.memberName = memberName;
		this.memberUid = memberUid;
		this.memberBth = memberBth;
		this.memberGender = memberGender;
		this.memberEmail = memberEmail;
		this.memberTel = memberTel;
		this.memberAdd = memberAdd;
		this.memberAcc = memberAcc;
		this.memberPw = memberPw;
		this.memberStatus = memberStatus;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Byte getMemberLvId() {
		return memberLvId;
	}

	public void setMemberLvId(Byte memberLvId) {
		this.memberLvId = memberLvId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberUid() {
		return memberUid;
	}

	public void setMemberUid(String memberUid) {
		this.memberUid = memberUid;
	}

	public Date getMemberBth() {
		return memberBth;
	}

	public void setMemberBth(Date memberBth) {
		this.memberBth = memberBth;
	}

	public Byte getMemberGender() {
		return memberGender;
	}

	public void setMemberGender(Byte memberGender) {
		this.memberGender = memberGender;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberTel() {
		return memberTel;
	}

	public void setMemberTel(String memberTel) {
		this.memberTel = memberTel;
	}

	public String getMemberAdd() {
		return memberAdd;
	}

	public void setMemberAdd(String memberAdd) {
		this.memberAdd = memberAdd;
	}

	public String getMemberAcc() {
		return memberAcc;
	}

	public void setMemberAcc(String memberAcc) {
		this.memberAcc = memberAcc;
	}

	public String getMemberPw() {
		return memberPw;
	}

	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}

	public Byte getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(Byte memberStatus) {
		this.memberStatus = memberStatus;
	}
	
	@Override
	public String toString() {
	    return "MemberManageVO [memberId=" + memberId + ", memberLvId=" + memberLvId + 
	           ", memberName=" + memberName + ", memberUid=" + memberUid + ", memberBth=" + memberBth + 
	           ", memberGender=" + memberGender + ", memberEmail=" + memberEmail + 
	           ", memberTel=" + memberTel + ", memberAdd=" + memberAdd + ", memberAcc=" + memberAcc + 
	           ", memberStatus=" + memberStatus + "]";
	}

}
