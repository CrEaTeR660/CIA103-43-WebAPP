package com.membertag.model;

import java.util.List;

public class MemberTagsVO implements java.io.Serializable{

    private int memId; // 會員編號
    private List<TagVO> tags; // 會員所擁有的所有標籤

    public MemberTagsVO(int memId, List<TagVO> tags) {
        this.memId = memId;
        this.tags = tags;
    }

    public int getMemId() {
        return memId;
    }

    public void setMemId(int memId) {
        this.memId = memId;
    }

    public List<TagVO> getTags() {
        return tags;
    }

    public void setTags(List<TagVO> tags) {
        this.tags = tags;
    }
}
