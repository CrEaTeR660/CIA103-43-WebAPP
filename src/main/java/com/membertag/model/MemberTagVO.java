package com.membertag.model;

public class MemberTagVO implements java.io.Serializable {

	private int memId; // 會員編號
	private int tagId; // 標籤編號

	// Constructor
	public MemberTagVO(int memId, int tagId) {
		this.memId = memId;
		this.tagId = tagId;
	}

	// Getters and Setters
	public int getMemId() {
		return memId;
	}

	public void setMemId(int memId) {
		this.memId = memId;
	}

	public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}
}
