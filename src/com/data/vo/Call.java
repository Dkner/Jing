package com.data.vo;

/**
 * Call entity. @author MyEclipse Persistence Tools
 */

public class Call implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private Notice notice;

	// Constructors

	/** default constructor */
	public Call() {
	}

	/** full constructor */
	public Call(User user, Notice notice) {
		this.user = user;
		this.notice = notice;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Notice getNotice() {
		return this.notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

}