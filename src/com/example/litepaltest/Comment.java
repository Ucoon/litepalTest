package com.example.litepaltest;

import java.sql.Date;

import org.litepal.crud.DataSupport;

public class Comment extends DataSupport {
	private int id;
	private String content;
	private Date publishDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	
}
