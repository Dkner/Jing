package com.process.service;

import java.util.List;

import com.data.vo.User;
import com.process.model.AssessProcessor;
import com.process.model.Page;

public class AssessImplement implements AssessService{

	AssessProcessor ap = new AssessProcessor();
	
	public List get_UserTag(String user_id) {
		// TODO Auto-generated method stub
		return ap.get_UserTag(user_id);
	}

	public void give_loveassess(String user_id, int song_id) {
		// TODO Auto-generated method stub
		ap.give_loveassess(user_id, song_id);
	}

	public void give_hateveassess(String user_id, int song_id) {
		// TODO Auto-generated method stub
		ap.give_hateveassess(user_id, song_id);
	}

	public void give_commentassess(String comment, String user_id, int song_id) {
		// TODO Auto-generated method stub
		ap.give_commentassess(comment, user_id, song_id);
	}

	public void give_levelassess(String level, String user_id, int song_id) {
		// TODO Auto-generated method stub
		ap.give_levelassess(level, user_id, song_id);
	}

	public boolean customize_usertag(String user_id, String usertag) {
		// TODO Auto-generated method stub
		return ap.customize_usertag(user_id, usertag);
	}

	public boolean undo_usertag(String user_id, String usertag) {
		// TODO Auto-generated method stub
		return ap.undo_usertag(user_id, usertag);
	}

	public boolean collect_singer(String user_id, String singername) {
		// TODO Auto-generated method stub
		return ap.collect_singer(user_id, singername);
	}

	public List get_RecordsByPage(String user_id, Page page) {
		// TODO Auto-generated method stub
		return ap.get_RecordsByPage(user_id, page);
	}

	public User get_User(String user_id) {
		// TODO Auto-generated method stub
		return ap.get_User(user_id);
	}

	public List get_Assess(int song_id, Page page) {
		// TODO Auto-generated method stub
		return ap.get_Assess(song_id, page);
	}

	public List get_FavorSingerByPage(String usr_id, Page page) {
		// TODO Auto-generated method stub
		return ap.get_FavorSingerByPage(usr_id, page);
	}

}
