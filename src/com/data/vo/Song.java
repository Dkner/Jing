package com.data.vo;

import java.util.HashSet;
import java.util.Set;

/**
 * Song entity. @author MyEclipse Persistence Tools
 */

public class Song implements java.io.Serializable {

	// Fields

	private Integer id;
	private Singer singer;
	private Album album;
	private String name;
	private String path;
	private Set tags = new HashSet(0);
	private Set assesses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Song() {
	}

	/** minimal constructor */
	public Song(Singer singer, String name, String path) {
		this.singer = singer;
		this.name = name;
		this.path = path;
	}

	/** full constructor */
	public Song(Singer singer, Album album, String name, String path, Set tags,
			Set assesses) {
		this.singer = singer;
		this.album = album;
		this.name = name;
		this.path = path;
		this.tags = tags;
		this.assesses = assesses;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Singer getSinger() {
		return this.singer;
	}

	public void setSinger(Singer singer) {
		this.singer = singer;
	}

	public Album getAlbum() {
		return this.album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Set getTags() {
		return this.tags;
	}

	public void setTags(Set tags) {
		this.tags = tags;
	}

	public Set getAssesses() {
		return this.assesses;
	}

	public void setAssesses(Set assesses) {
		this.assesses = assesses;
	}

}