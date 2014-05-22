package com.data.vo;

/**
 * Friendship entity. @author MyEclipse Persistence Tools
 */

public class Friendship implements java.io.Serializable {

	// Fields

	private FriendshipId id;
	private String relation;

	// Constructors

	/** default constructor */
	public Friendship() {
	}

	/** full constructor */
	public Friendship(FriendshipId id, String relation) {
		this.id = id;
		this.relation = relation;
	}

	// Property accessors

	public FriendshipId getId() {
		return this.id;
	}

	public void setId(FriendshipId id) {
		this.id = id;
	}

	public String getRelation() {
		return this.relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

}