package com.data.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private String userId;
	private String name;
	private String keyword;
	private String sex;
	private String signature;
	private String mail;
	private Date birth;
	private Set tagrecords = new HashSet(0);
	private Set favors = new HashSet(0);
	private Set usertags = new HashSet(0);
	private Set friendshipsForU2 = new HashSet(0);
	private Set calls = new HashSet(0);
	private Set friendshipsForU1 = new HashSet(0);
	private Set assesses = new HashSet(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String userId, String keyword, String mail) {
		this.userId = userId;
		this.keyword = keyword;
		this.mail = mail;
	}

	/** full constructor */
	public User(String userId, String name, String keyword, String sex,
			String signature, String mail, Date birth, Set tagrecords,
			Set favors, Set usertags, Set friendshipsForU2, Set calls,
			Set friendshipsForU1, Set assesses) {
		this.userId = userId;
		this.name = name;
		this.keyword = keyword;
		this.sex = sex;
		this.signature = signature;
		this.mail = mail;
		this.birth = birth;
		this.tagrecords = tagrecords;
		this.favors = favors;
		this.usertags = usertags;
		this.friendshipsForU2 = friendshipsForU2;
		this.calls = calls;
		this.friendshipsForU1 = friendshipsForU1;
		this.assesses = assesses;
	}

	// Property accessors

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSignature() {
		return this.signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Date getBirth() {
		return this.birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public Set getTagrecords() {
		return this.tagrecords;
	}

	public void setTagrecords(Set tagrecords) {
		this.tagrecords = tagrecords;
	}

	public Set getFavors() {
		return this.favors;
	}

	public void setFavors(Set favors) {
		this.favors = favors;
	}

	public Set getUsertags() {
		return this.usertags;
	}

	public void setUsertags(Set usertags) {
		this.usertags = usertags;
	}

	public Set getFriendshipsForU2() {
		return this.friendshipsForU2;
	}

	public void setFriendshipsForU2(Set friendshipsForU2) {
		this.friendshipsForU2 = friendshipsForU2;
	}

	public Set getCalls() {
		return this.calls;
	}

	public void setCalls(Set calls) {
		this.calls = calls;
	}

	public Set getFriendshipsForU1() {
		return this.friendshipsForU1;
	}

	public void setFriendshipsForU1(Set friendshipsForU1) {
		this.friendshipsForU1 = friendshipsForU1;
	}

	public Set getAssesses() {
		return this.assesses;
	}

	public void setAssesses(Set assesses) {
		this.assesses = assesses;
	}

}