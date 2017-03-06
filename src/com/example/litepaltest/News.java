package com.example.litepaltest;

import java.util.Date;

import org.litepal.crud.DataSupport;

public class News extends DataSupport {
	//主键
	/**
	 * id这个字段可写可不写，即使不写这个字段，LitePal也会在表中自动
	 * 生成一个id列
	 */
	private int id;
	private String title;
	private String content;
	private Date publishDate;
	private int commentCount;
	
	//添加字段
	private String publisher;
	
	public int getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
