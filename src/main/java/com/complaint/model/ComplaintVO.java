package com.complaint.model;

import java.sql.Timestamp;

public class ComplaintVO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer complaintId;
	private Integer memberId;
	private Integer caseId;
	private String complaintCon;
	private Timestamp complaintTime;
	private Byte complaintStatus;
	private String complaintResult;
	
	public ComplaintVO() {
		super();
	}

	public ComplaintVO(Integer complaintId,Integer memberId,Integer caseId, String complaintCon, Timestamp complaintTime, Byte complaintStatus,
			String complaintResult) {
		super();
		this.complaintId = complaintId;
		this.memberId = memberId;
		this.caseId = caseId;
		this.complaintCon = complaintCon;
		this.complaintTime = complaintTime;
		this.complaintStatus = complaintStatus;
		this.complaintResult = complaintResult;
	}

	public Integer getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(Integer complaintId) {
		this.complaintId = complaintId;
	}

	public String getComplaintCon() {
		return complaintCon;
	}

	public void setComplaintCon(String complaintCon) {
		this.complaintCon = complaintCon;
	}

	public Timestamp getComplaintTime() {
		return complaintTime;
	}

	public void setComplaintTime(Timestamp complaintTime) {
		this.complaintTime = complaintTime;
	}

	public Byte getComplaintStatus() {
		return complaintStatus;
	}

	public void setComplaintStatus(Byte complaintStatus) {
		this.complaintStatus = complaintStatus;
	}

	public String getComplaintResult() {
		return complaintResult;
	}

	public void setComplaintResult(String complaintResult) {
		this.complaintResult = complaintResult;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getCaseId() {
		return caseId;
	}

	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}



}
