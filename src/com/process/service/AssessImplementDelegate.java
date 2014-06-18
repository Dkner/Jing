package com.process.service;

import java.util.List;
import com.data.vo.User;
import com.process.model.AssessProcessor;
import com.process.model.Page;

@javax.jws.WebService(targetNamespace = "http://service.process.com/", serviceName = "AssessImplementService", portName = "AssessImplementPort", wsdlLocation = "WEB-INF/wsdl/AssessImplementService.wsdl")
public class AssessImplementDelegate {

	com.process.service.AssessImplement assessImplement = new com.process.service.AssessImplement();

	public List get_UserTag(String user_id) {
		return assessImplement.get_UserTag(user_id);
	}

	public void give_loveassess(String user_id, int song_id) {
		assessImplement.give_loveassess(user_id, song_id);
	}

	public void give_hateveassess(String user_id, int song_id) {
		assessImplement.give_hateveassess(user_id, song_id);
	}

	public void give_commentassess(String comment, String user_id, int song_id) {
		assessImplement.give_commentassess(comment, user_id, song_id);
	}

	public void give_levelassess(String level, String user_id, int song_id) {
		assessImplement.give_levelassess(level, user_id, song_id);
	}

	public boolean customize_usertag(String user_id, String usertag) {
		return assessImplement.customize_usertag(user_id, usertag);
	}

	public boolean undo_usertag(String user_id, String usertag) {
		return assessImplement.undo_usertag(user_id, usertag);
	}

	public boolean collect_singer(String user_id, String singername) {
		return assessImplement.collect_singer(user_id, singername);
	}

	public List get_RecordsByPage(String user_id, Page page) {
		return assessImplement.get_RecordsByPage(user_id, page);
	}

	public User get_User(String user_id) {
		return assessImplement.get_User(user_id);
	}

	public List get_Assess(int song_id, Page page) {
		return assessImplement.get_Assess(song_id, page);
	}

	public List get_FavorSingerByPage(String usr_id, Page page) {
		return assessImplement.get_FavorSingerByPage(usr_id, page);
	}

	public void collect_song(String user_id, String songname) {
		assessImplement.collect_song(user_id, songname);
	}

	public List get_Friend(String user_id) {
		return assessImplement.get_Friend(user_id);
	}

	public List get_Notice(String user_id) {
		return assessImplement.get_Notice(user_id);
	}

}