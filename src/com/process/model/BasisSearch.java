package com.process.model;

import java.util.ArrayList;
import java.util.List;

import com.data.vo.Album;
import com.data.vo.AlbumDAO;
import com.data.vo.Label;
import com.data.vo.LabelDAO;
import com.data.vo.Singer;
import com.data.vo.SingerDAO;
import com.data.vo.Song;
import com.data.vo.SongDAO;
import com.data.vo.Tag;

public class BasisSearch {

	
	//DAO
	private SongDAO sd = null;
	private AlbumDAO ad = null;
	private LabelDAO ld = null;
	private SingerDAO sid = null;
	
	public BasisSearch()
	{
		sd = new SongDAO();
		ad = new AlbumDAO();
		ld = new LabelDAO();
		sid = new SingerDAO();
	}
	
	
	
	
	public Song find_song_by_id(int song_id) {
		// TODO Auto-generated method stub
		return sd.findById(song_id);
	}
	
	/**
	   * function 根据歌名找到歌曲列表
	   * @param String 歌名
	   * @return List Song
	   */
	@SuppressWarnings("rawtypes")
	public final List find_songlist_by_songname(String songname)
	{
		List temp = new ArrayList();
		temp = sd.findByName(songname);
		
		return temp;
	}
	
	/**
	   * function 根据专辑名找到歌曲列表
	   * @param String 专辑名
	   * @return List Song
	   */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public final List find_songlist_by_albumname(String albumname)
	{
		List tempalbum = new ArrayList();
		List temp = new ArrayList();
		tempalbum = ad.findByName(albumname);
		for (int i = 0; i < tempalbum.size(); i++) {
			temp.addAll(((Album)tempalbum.get(i)).getSongs());
		}
		
		return temp;
	}
	
	/**
	   * function 根据单个标签找到歌曲列表
	   * @param String 专辑名
	   * @return List Song
	   */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public final List find_songlist_by_label(String label)
	{
		List templabel = new ArrayList();
		List temptag = new ArrayList();
		List temp = new ArrayList();
		templabel = ld.findByLabel(label);
		for (int i = 0; i < templabel.size(); i++) {
			temptag.addAll(((Label)templabel.get(i)).getTags());
		}
		for(int i=0; i<temptag.size(); i++){
			temp.add(((Tag)temptag.get(i)).getSong());
		}
		
		return temp;
	}
	
	/**
	   * function 根据歌手找到歌曲列表
	   * @param String 专辑名
	   * @return List Song
	   */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public final List find_songlist_by_singer(String singer)
	{
		List tempsinger = new ArrayList();
		List temp = new ArrayList();
		tempsinger = sid.findByName(singer);
		for (int i = 0; i < tempsinger.size(); i++) {
			temp.addAll(((Singer)tempsinger.get(i)).getSongs());
		}
		
		return temp;
	}
	
}
