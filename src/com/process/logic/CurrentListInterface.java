package com.process.logic;

public interface CurrentListInterface {

	public void set_userid(String user_id);
	
	public String get_userid();
	
	public String get_currentlabel();
	
	public String give_currentsongname();

	public String give_currentpath();
	
	public String give_currentsingername();
	
	public int get_songamount();
	
	public int get_currentSongId();
	
	public void play_nextsong();
	
}
