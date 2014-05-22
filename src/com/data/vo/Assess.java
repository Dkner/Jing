package com.data.vo;

import java.util.Date;

/**
 * Assess entity. @author MyEclipse Persistence Tools
 */

public class Assess implements java.io.Serializable {

	// Fields

	private Integer assessId;
	private User user;
	private Song song;
	private String loveorhate;
	private String comment;
	private String level;
	private Date time;

	// Constructors

	/** default constructor */
	public Assess() {
	}

	/** minimal constructor */
	public Assess(User user, Song song) {
		this.user = user;
		this.song = song;
	}

	/** full constructor */
	public Assess(User user, Song song, String loveorhate, String comment,
			String level, Date time) {
		this.user = user;
		this.song = song;
		this.loveorhate = loveorhate;
		this.comment = comment;
		this.level = level;
		this.time = time;
	}

	// Property accessors

	public Integer getAssessId() {
		return this.assessId;
	}

	public void setAssessId(Integer assessId) {
		this.assessId = assessId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Song getSong() {
		return this.song;
	}

	public void setSong(Song song) {
		this.song = song;
	}

	public String getLoveorhate() {
		return this.loveorhate;
	}

	public void setLoveorhate(String loveorhate) {
		this.loveorhate = loveorhate;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}