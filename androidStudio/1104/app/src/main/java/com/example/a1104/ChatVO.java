package com.example.a1104;

public class ChatVO {
    private String key; //삭제를위한 키
    private String content;
    private String email;
    private String date;

    @Override
    public String toString() {
        return "ChatVO{" +
                "key='" + key + '\'' +
                ", content='" + content + '\'' +
                ", email='" + email + '\'' +
                ", date='" + date + '\'' +
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
