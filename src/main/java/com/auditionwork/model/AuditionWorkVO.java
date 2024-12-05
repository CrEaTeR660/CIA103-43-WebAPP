package com.auditionwork.model;

import java.sql.Timestamp;

/**
 * AuditionWorkVO - 封裝配音作品表 (AUDITION_WORKS) 的資料
 */
public class AuditionWorkVO {
	private int workId; // 作品編號 (主鍵)
	private int memId; // 會員編號 (外鍵)
	private String title; // 作品標題
	private String description; // 作品描述
	private String filePath; // 試音檔案路徑
	private Timestamp uploadDate; // 上傳日期

	// 空建構子
	public AuditionWorkVO() {
	}

	// 有參數建構子 (用於快速初始化)
	public AuditionWorkVO(int workId, int memId, String title, String description, String filePath,
			Timestamp uploadDate) {
		this.workId = workId;
		this.memId = memId;
		this.title = title;
		this.description = description;
		this.filePath = filePath;
		this.uploadDate = uploadDate;
	}

	// Getters and Setters
	public int getWorkId() {
		return workId;
	}

	public void setWorkId(int workId) {
		this.workId = workId;
	}

	public int getMemId() {
		return memId;
	}

	public void setMemId(int memId) {
		this.memId = memId;
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

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Timestamp getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Timestamp uploadDate) {
		this.uploadDate = uploadDate;
	}

	// toString 方法，toString 方法，用於打印 VO 的詳細內容。

	@Override
	public String toString() {
		return "AuditionWorkVO{" + "workId=" + workId + ", memId=" + memId + ", title='" + title + '\''
				+ ", description='" + description + '\'' + ", filePath='" + filePath + '\'' + ", uploadDate="
				+ uploadDate + '}';
	}

}
