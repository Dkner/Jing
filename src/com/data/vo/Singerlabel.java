package com.data.vo;

/**
 * Singerlabel entity. @author MyEclipse Persistence Tools
 */

public class Singerlabel implements java.io.Serializable {

	// Fields

	private Integer id;
	private Label label;
	private Singer singer;

	// Constructors

	/** default constructor */
	public Singerlabel() {
	}

	/** full constructor */
	public Singerlabel(Label label, Singer singer) {
		this.label = label;
		this.singer = singer;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Label getLabel() {
		return this.label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public Singer getSinger() {
		return this.singer;
	}

	public void setSinger(Singer singer) {
		this.singer = singer;
	}

}