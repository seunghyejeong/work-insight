package com.opencloudplatform.edm.domain;

import java.util.Date;
import java.util.Objects;

public class QnABoard {
	private Integer idx;
	private String title;
	private String content;
	private String writer;
	private String password;
	private String reply;

	private Date register_date;
	private Date update_date;
	private int publicOrNot;
	private int replyOrNot;

	public QnABoard() {}

	public QnABoard(String title, String content, String writer, String password, int publicOrNot, int replyOrNot) {
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.password = password;
		this.publicOrNot = publicOrNot;
		this.replyOrNot = replyOrNot;
	}

	@Override
	public String toString() {
		return "QnABoard{" +
				"idx=" + idx +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				", writer='" + writer + '\'' +
				", password='" + password + '\'' +
				", reply='" + reply + '\'' +
				", register_date=" + register_date +
				", update_date=" + update_date +
				", publicOrNot=" + publicOrNot +
				", replyOrNot=" + replyOrNot +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		QnABoard qnABoard = (QnABoard) o;
		return publicOrNot == qnABoard.publicOrNot && replyOrNot == qnABoard.replyOrNot && Objects.equals(idx, qnABoard.idx) && Objects.equals(title, qnABoard.title) && Objects.equals(content, qnABoard.content) && Objects.equals(writer, qnABoard.writer) && Objects.equals(password, qnABoard.password) && Objects.equals(reply, qnABoard.reply) && Objects.equals(register_date, qnABoard.register_date) && Objects.equals(update_date, qnABoard.update_date);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idx, title, content, writer, password, reply, register_date, update_date, publicOrNot, replyOrNot);
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

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
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

	public int getPublicOrNot() {
		return publicOrNot;
	}

	public void setPublicOrNot(int publicOrNot) {
		this.publicOrNot = publicOrNot;
	}

	public int getReplyOrNot() {
		return replyOrNot;
	}

	public void setReplyOrNot(int replyOrNot) {
		this.replyOrNot = replyOrNot;
	}
}