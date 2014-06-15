package com.process.service;

import java.util.List;

import com.data.vo.Song;
import com.process.model.FilterChain;
import com.process.model.LabelProcessor;

public class SearchImplement implements SearchService{

	LabelProcessor lp = new LabelProcessor();
	
	public List find_matchlabel(String word) {
		// TODO Auto-generated method stub
		return lp.find_matchlabel(word);
	}

	public List find_songlist_by_input(int match_degree_minus, String input,
			FilterChain chain, String user_id) {
		// TODO Auto-generated method stub
		return lp.find_songlist_by_input(match_degree_minus, input, chain, user_id);
	}

	public Song find_song_by_id(int song_id) {
		// TODO Auto-generated method stub
		return lp.find_song_by_id(song_id);
	}

}
