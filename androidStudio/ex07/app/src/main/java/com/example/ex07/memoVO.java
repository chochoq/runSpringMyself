package com.example.ex07;

public class memoVO {
    private String key; //프라이머리키
    private String content;
    private String createDate;
    private String updateData;

    @Override
    public String toString() {
        return "memoVO{" +
                "key='" + key + '\'' +
                ", content='" + content + '\'' +
                ", createDate='" + createDate + '\'' +
                ", updateData='" + updateData + '\'' +
                '}';
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateData() {
        return updateData;
    }

    public void setUpdateData(String updateData) {
        this.updateData = updateData;
    }
}
