package com.membertag.model;

public class TagTypeVO {

    private int tagTypeNo; // 標籤種類編號
    private String tagTypeName; // 標籤種類名稱

    public TagTypeVO(int tagTypeNo, String tagTypeName) {
        this.tagTypeNo = tagTypeNo;
        this.tagTypeName = tagTypeName;
    }

    public int getTagTypeNo() {
        return tagTypeNo;
    }

    public void setTagTypeNo(int tagTypeNo) {
        this.tagTypeNo = tagTypeNo;
    }

    public String getTagTypeName() {
        return tagTypeName;
    }

    public void setTagTypeName(String tagTypeName) {
        this.tagTypeName = tagTypeName;
    }
}
