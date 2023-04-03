package com.opencloudplatform.edm.domain;

import java.util.Date;
import java.util.Objects;

public class NoticeBoard {

    private Integer idx;
    private String title;
    private String content;
    private String writer;
    private String password;
    private Date register_date;
    private Date update_date;
    private int view_cnt;


    @Override
    public String toString() {
        return "NoticeBoard{" +
                "idx=" + idx +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", password='" + password + '\'' +
                ", register_date=" + register_date +
                ", update_date=" + update_date +
                ", view_cnt=" + view_cnt +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoticeBoard that = (NoticeBoard) o;
        return view_cnt == that.view_cnt && Objects.equals(idx, that.idx) && Objects.equals(title, that.title) && Objects.equals(content, that.content) && Objects.equals(writer, that.writer) && Objects.equals(password, that.password) && Objects.equals(register_date, that.register_date) && Objects.equals(update_date, that.update_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idx, title, content, writer, password, register_date, update_date, view_cnt);
    }

    public int getView_cnt() {
        return view_cnt;
    }

    public void setView_cnt(int view_cnt) {
        this.view_cnt = view_cnt;
    }

    public NoticeBoard() {}

    public NoticeBoard(String title, String content, String writer, String password) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.password = password;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegister_date() {
        return register_date;
    }

    public void setRegister_date(Date register_date) {
        this.register_date = register_date;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }
}
