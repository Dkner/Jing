package com.data.vo;

import java.util.HashSet;
import java.util.Set;

/**
 * Singer entity. @author MyEclipse Persistence Tools
 */

public class Singer implements java.io.Serializable {

	// Fields

	private Integer singerId;
	private String name;
	private String birth;
	private String nationality;
	private String briefing;
	private Set favors = new HashSet(0);
	private Set albums = new HashSet(0);
	private Set songs = new HashSet(0);
	private Set singerlabels = new HashSet(0);

	// Constructors

	/** default constructor */
	public Singer() {
	}

	/** minimal constructor */
	public Singer(String name) {
		this.name = name;
	}

	/** full constructor */
	public Singer(String name, String birth, String nationality,
			String briefing, Set favors, Set albums, Set songs, Set singerlabels) {
		this.name = name;
		this.birth = birth;
		this.nationality = nationality;
		this.briefing = briefing;
		this.favors = favors;
		this.albums = albums;
		this.songs = songs;
		this.singerlabels = singerlabels;
	}

	// Property accessors

	public Integer getSingerId() {
		return this.singerId;
	}

	public void setSingerId(Integer singerId) {
		this.singerId = singerId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirth() {
		return this.birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getNationality() {
		return this.nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getBriefing() {
		return this.briefing;
	}

	public void setBriefing(String briefing) {
		this.briefing = briefing;
	}

	public Set getFavors() {
		return this.favors;
	}

	public void setFavors(Set favors) {
		this.favors = favors;
	}

	public Set getAlbums() {
		return this.albums;
	}

	public void setAlbums(Set albums) {
		this.albums = albums;
	}

	public Set getSongs() {
		return this.songs;
	}

	public void setSongs(Set songs) {
		this.songs = songs;
	}

	public Set getSingerlabels() {
		return this.singerlabels;
	}

	public void setSingerlabels(Set singerlabels) {
		this.singerlabels = singerlabels;
	}

}