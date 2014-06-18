package com.data.vo;

/**
 * Friendship entity. @author MyEclipse Persistence Tools
 */

public class Friendship implements java.io.Serializable {

	// Fields

	private FriendshipId id;
	private User userByU2;
	private User userByU1;

	// Constructors

	/** default constructor */
	public Friendship() {
	}

	/** full constructor */
	public Friendship(FriendshipId id, User userByU2, User userByU1) {
		this.id = id;
		this.userByU2 = userByU2;
		this.userByU1 = userByU1;
	}

	// Property accessors

	public FriendshipId getId() {
		return this.id;
	}

	public void setId(FriendshipId id) {
		this.id = id;
	}

	public User getUserByU2() {
		return this.userByU2;
	}

	public void setUserByU2(User userByU2) {
		this.userByU2 = userByU2;
	}

	public User getUserByU1() {
		return this.userByU1;
	}

	public void setUserByU1(User userByU1) {
		this.userByU1 = userByU1;
	}

}