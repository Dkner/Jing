package com.data.vo;

/**
 * Tagrecord entity. @author MyEclipse Persistence Tools
 */

public class Tagrecord implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private String record;
	private String time;

	// Constructors

	/** default constructor */
	public Tagrecord() {
	}

	/** minimal constructor */
	public Tagrecord(User user, String record) {
		this.user = user;
		this.record = record;
	}

	/** full constructor */
	public Tagrecord(User user, String record, String time) {
		this.user = user;
		this.record = record;
		this.time = time;
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

	public String getRecord() {
		return this.record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}