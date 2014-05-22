package com.data.vo;

/**
 * Usertag entity. @author MyEclipse Persistence Tools
 */

public class Usertag implements java.io.Serializable {

	// Fields

	private Integer usertagId;
	private Label label;
	private User user;
	private Integer weight;
	private Integer shit;

	// Constructors

	/** default constructor */
	public Usertag() {
	}

	/** minimal constructor */
	public Usertag(Label label, User user) {
		this.label = label;
		this.user = user;
	}

	/** full constructor */
	public Usertag(Label label, User user, Integer weight, Integer shit) {
		this.label = label;
		this.user = user;
		this.weight = weight;
		this.shit = shit;
	}

	// Property accessors

	public Integer getUsertagId() {
		return this.usertagId;
	}

	public void setUsertagId(Integer usertagId) {
		this.usertagId = usertagId;
	}

	public Label getLabel() {
		return this.label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getWeight() {
		return this.weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getShit() {
		return this.shit;
	}

	public void setShit(Integer shit) {
		this.shit = shit;
	}

}