package com.francis.zhengwusystem.model;

import cn.bmob.v3.BmobObject;

/**
 * @author taoc @ Zhihu Inc.
 * @since 10-12-2016
 */

public class Suggestion extends BmobObject{

	private int id;
	private String peopleName;
	private String phone;
	private String content;
	private String reply;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPeopleName() {
		return peopleName;
	}

	public void setPeopleName(String peopleName) {
		this.peopleName = peopleName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}
}
