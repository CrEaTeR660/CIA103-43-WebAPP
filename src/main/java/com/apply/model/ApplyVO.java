package com.apply.model;

import java.sql.Timestamp;

/**
 * ApplyVO - 封裝應徵資料表 (APPLY) 的資料
 */
public class ApplyVO {
	private int caseId; // 案件編號 (主鍵)
	private int memId; // 應徵會員編號 (外鍵)
	private int clientId; // 客戶會員編號 (外鍵)
	private String status; // 應徵狀態
	private String remarks; // 備註
	private Timestamp createdAt; // 建立時間
	private Timestamp updatedAt; // 更新時間
	private byte[] voiceFile; // 試音檔案 (BLOB)

	// 空建構子
	public ApplyVO() {
	}

	// 有參數建構子
	public ApplyVO(int caseId, int memId, int clientId, String status, String remarks, Timestamp createdAt,
			Timestamp updatedAt, byte[] voiceFile) {
		this.caseId = caseId;
		this.memId = memId;
		this.clientId = clientId;
		this.status = status;
		this.remarks = remarks;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.voiceFile = voiceFile;
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

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public byte[] getVoiceFile() {
		return voiceFile;
	}

	public void setVoiceFile(byte[] voiceFile) {
		this.voiceFile = voiceFile;
	}

	// toString 方法
	@Override
	public String toString() {
		return "ApplyVO{" + "caseId=" + caseId + ", memId=" + memId + ", clientId=" + clientId + ", status='" + status
				+ '\'' + ", remarks='" + remarks + '\'' + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", voiceFile=" + (voiceFile != null ? "BLOB Data" : "null") + '}';
	}
}
