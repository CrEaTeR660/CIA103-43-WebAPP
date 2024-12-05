package com.complaintphotos.model;

import java.sql.Date;
import java.util.Arrays;

public class ComplaintPhotosVO implements java.io.Serializable {

	//序列化
	private static final long serialVersionUID = 1L;
	private int comPicId; // 申訴照片編號
	private int comId; // 申訴編號
	private byte[] comPic; // 申訴照片（BLOB）
	private String fileName; // 圖片檔案名稱
	private String mimeType;// 图片 MIME 类型
	private Date uploadTime; // 上傳時間


	public ComplaintPhotosVO() {
		super();
	}
	
	public ComplaintPhotosVO(int comPicId, int comId, byte[] comPic, String fileName, String mimeType,
			Date uploadTime) {
		super();
		this.comPicId = comPicId;
		this.comId = comId;
		this.comPic = comPic;
		this.fileName = fileName;
		this.mimeType = mimeType;
		this.uploadTime = uploadTime;
	}

	public int getComPicId() {
		return comPicId;
	}

	public void setComPicId(int comPicId) {
		this.comPicId = comPicId;
	}

	public int getComId() {
		return comId;
	}

	public void setComId(int comId) {
		this.comId = comId;
	}

	public byte[] getComPic() {
		return comPic;
	}

	public void setComPic(byte[] comPic) {
		this.comPic = comPic;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	@Override
	public String toString() {
		return "ComplaintPhotosVO [comPicId=" + comPicId + ", comId=" + comId + ", comPic=" + Arrays.toString(comPic)
				+ ", fileName=" + fileName + ", mimeType=" + mimeType + ", uploadTime=" + uploadTime + "]";
	}
}
