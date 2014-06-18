package com.process.service;

import java.util.List;
import com.data.vo.Label;
import com.data.vo.Song;
import com.process.model.FilterChain;
import com.process.model.LabelProcessor;

@javax.jws.WebService(targetNamespace = "http://service.process.com/", serviceName = "SearchImplementService", portName = "SearchImplementPort", wsdlLocation = "WEB-INF/wsdl/SearchImplementService.wsdl")
public class SearchImplementDelegate {

	com.process.service.SearchImplement searchImplement = new com.process.service.SearchImplement();

	public List<Label> find_matchlabel(String word) {
		return searchImplement.find_matchlabel(word);
	}

	public List<Song> find_songlist_by_input(int match_degree_minus,
			String input, FilterChain chain, String user_id) {
		return searchImplement.find_songlist_by_input(match_degree_minus,
				input, chain, user_id);
	}

	public Song find_song_by_id(int song_id) {
		return searchImplement.find_song_by_id(song_id);
	}

	public List<Song> find_songlist_by_input_Basic(String input) {
		return searchImplement.find_songlist_by_input_Basic(input);
	}

}