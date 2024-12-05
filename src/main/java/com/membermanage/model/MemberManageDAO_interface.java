package com.membermanage.model;

import java.util.List;

public  interface MemberManageDAO_interface  {
	public List<MemberManageVO> getAll();
	public MemberManageVO findByPrimaryKey(Integer memberId);
	public void updateLevelAndStatus(Integer memberId, Byte memberLvId, Byte memberStatus);
}
