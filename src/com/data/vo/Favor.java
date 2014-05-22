package com.data.vo;

/**
 * Favor entity. @author MyEclipse Persistence Tools
 */

public class Favor implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private Singer singer;

	// Constructors

	/** default constructor */
	public Favor() {
	}

	/** full constructor */
	public Favor(User user, Singer singer) {
		this.user = user;
		this.singer = singer;
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

	public Singer getSinger() {
		return this.singer;
	}

	public void setSinger(Singer singer) {
		this.singer = singer;
	}

}