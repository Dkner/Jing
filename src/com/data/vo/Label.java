package com.data.vo;

import java.util.HashSet;
import java.util.Set;

/**
 * Label entity. @author MyEclipse Persistence Tools
 */

public class Label implements java.io.Serializable {

	// Fields

	private Integer labelId;
	private String type;
	private String label;
	private String picture;
	private Set usertags = new HashSet(0);
	private Set singerlabels = new HashSet(0);
	private Set tags = new HashSet(0);

	// Constructors

	/** default constructor */
	public Label() {
	}

	/** minimal constructor */
	public Label(String type, String label) {
		this.type = type;
		this.label = label;
	}

	/** full constructor */
	public Label(String type, String label, String picture, Set usertags,
			Set singerlabels, Set tags) {
		this.type = type;
		this.label = label;
		this.picture = picture;
		this.usertags = usertags;
		this.singerlabels = singerlabels;
		this.tags = tags;
	}

	// Property accessors

	public Integer getLabelId() {
		return this.labelId;
	}

	public void setLabelId(Integer labelId) {
		this.labelId = labelId;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Set getUsertags() {
		return this.usertags;
	}

	public void setUsertags(Set usertags) {
		this.usertags = usertags;
	}

	public Set getSingerlabels() {
		return this.singerlabels;
	}

	public void setSingerlabels(Set singerlabels) {
		this.singerlabels = singerlabels;
	}

	public Set getTags() {
		return this.tags;
	}

	public void setTags(Set tags) {
		this.tags = tags;
	}

}