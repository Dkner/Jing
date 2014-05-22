package com.process.model;

public class WebSong {
	private String url;
	private String artist;
	private String genre;
	private String title;
	private String coverimg;
	
	public WebSong(String title, String artist, String genre, String url, String coverimg){
		this.title = title;
		this.artist = artist;
		this.genre = genre;
		this.url = url;
		this.coverimg = coverimg;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCoverimg() {
		return coverimg;
	}

	public void setCoverimg(String coverimg) {
		this.coverimg = coverimg;
	}

	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String ToString(){
		String song = this.title+"\n"+this.artist+"\n"+this.genre+"\n"+this.url;
		return song;
	}
	
	public void print(){
		System.out.println(this.ToString());
	}
}
