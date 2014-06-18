package com.data.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Notice entity. @author MyEclipse Persistence Tools
 */

public class Notice implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private String content;
	private Date time;
	private String type;
	private Set comments = new HashSet(0);
	private Set calls = new HashSet(0);

	// Constructors

	/** default constructor */
	public Notice() {
	}

	/** minimal constructor */
	public Notice(User user, String content, String type) {
		this.user = user;
		this.content = content;
		this.type = type;
	}

	/** full constructor */
	public Notice(User user, String content, Date time, String type,
			Set comments, Set calls) {
		this.user = user;
		this.content = content;
		this.time = time;
		this.type = type;
		this.comments = comments;
		this.calls = calls;
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

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set getComments() {
		return this.comments;
	}

	public void setComments(Set comments) {
		this.comments = comments;
	}

	public Set getCalls() {
		return this.calls;
	}

	public void setCalls(Set calls) {
		this.calls = calls;
	}

}