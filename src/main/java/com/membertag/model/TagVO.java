package com.membertag.model;

public class TagVO {

    private int tagId; // 標籤編號
    private int tagTypeNo; // 標籤種類編號
    private String tagName; // 標籤名稱

    public TagVO(int tagId, int tagTypeNo, String tagName) {
        this.tagId = tagId;
        this.tagTypeNo = tagTypeNo;
        this.tagName = tagName;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public int getTagTypeNo() {
        return tagTypeNo;
    }

    public void setTagTypeNo(int tagTypeNo) {
        this.tagTypeNo = tagTypeNo;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
