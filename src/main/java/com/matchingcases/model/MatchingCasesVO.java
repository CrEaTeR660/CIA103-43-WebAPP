package com.matchingcases.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * MatchingCaseVO - 封裝媒合案件表 (MATCHING_CASES) 的資料
 */
public class MatchingCasesVO implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int caseId; // 案件編號 (主鍵)
	private int memId; // 發案會員編號 (外鍵)
	private int receiverId; // 接案會員編號
	private String title; // 案件標題
	private String description; // 案件描述
	private BigDecimal budget; // 預算
	private Timestamp startDate; // 開始日期
	private Timestamp endDate; // 結束日期
	private int status; // 案件狀態 (0: 媒合中, 1: 已結案)
	private Timestamp createdAt; // 建立時間
	private int caseTot; // 案件金額

	public MatchingCasesVO() {
		super();
	}

	public MatchingCasesVO(int caseId, int memId, int receiverId, String title, String description, BigDecimal budget,
			Timestamp startDate, Timestamp endDate, int status, Timestamp createdAt, int caseTot) {
		super();
		this.caseId = caseId;
		this.memId = memId;
		this.receiverId = receiverId;
		this.title = title;
		this.description = description;
		this.budget = budget;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.createdAt = createdAt;
		this.caseTot = caseTot;
	}

	// Getters and Setters
	public int getCaseId() {
		return caseId;
	}

	public void setCaseId(int caseId) {
		this.caseId = caseId;
	}

	public int getMemId() {
		return memId;
	}

	public void setMemId(int memId) {
		this.memId = memId;
	}

	public int getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getBudget() {
		return budget;
	}

	public void setBudget(BigDecimal budget) {
		this.budget = budget;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public int getCaseTot() {
		return caseTot;
	}

	public void setCaseTot(int caseTot) {
		this.caseTot = caseTot;
	}

	public String toString() {
		return "MatchingCasesVO {" + "caseId=" + caseId + ", memId=" + memId + ", receiverId=" + receiverId + ", title"
				+ title + ",description" + description + ", budget" + budget + ", startDate" + startDate + ", endDate"
				+ endDate + ", status" + status + ", createdAt" + createdAt + ", caseTot" + caseTot + '}';
	}
}
