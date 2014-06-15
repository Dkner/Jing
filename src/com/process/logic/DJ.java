package com.process.logic;

import java.util.List;

import com.data.vo.Song;
import com.data.vo.User;
import com.process.model.AI_Recommender;
import com.process.model.AssessProcessor;
import com.process.model.Filter;
import com.process.model.FilterChain;
import com.process.model.LabelProcessor;
import com.process.model.Page;
import com.process.model.UserProfileProcessor;
import com.process.service.AssessService;
import com.process.service.RecommendService;
import com.process.service.SearchService;

public class DJ implements CurrentListInterface{

	private String username = "";
	private CurrentList list;
	
	private AssessService assessservice;
	private SearchService searchservice;
	private RecommendService recommendservice;
	
	private UserProfileProcessor userprofile;
	
	
	public DJ(){
		list = new CurrentList();
		
		searchservice = new LabelProcessor();
		recommendservice = new AI_Recommender();
		assessservice = new AssessProcessor();
		
		userprofile = new UserProfileProcessor();
	}
	
	
	
	
	
	
	
	
	
	
	//播放模块CurrentList的接口实现
	public void set_username(String username)
	{
		this.username = username;
	}
	
	public String get_username()
	{
		return this.username;
	}
	
	public void set_userid(String user_id)
	{
		list.set_userid(user_id);
	}
	
	public String get_userid(){
		return list.get_userid();
	}
	
	public String get_currentlabel(){
		return list.get_currentlabel();
	}
	
	public String give_currentsongname()
	{
		return list.give_currentsongname();
	}

	public String give_currentpath()
	{
		return list.give_currentpath();
	}
	
	public String give_currentsingername()
	{
		return list.give_currentsingername();
	}
	
	public int get_songamount(){
		return list.songamount;
	}
	
	public int get_currentSongId(){
		return list.get_currentSongId();
	}
	
	public void play_nextsong()
	{
		list.play_nextsong();
	}
	
	public final void set_list(List list)
	{
		this.list.set_list(list);		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//SearchService的接口实现
	/**
	   * function 匹配标签
	   * @param String word
	   * @return List Label
	   */
	public final List find_MatchLabelProcess(String word){
		return searchservice.find_matchlabel(word);
	}
	
	public final Song get_CurrentSong()
	{
		return searchservice.find_song_by_id(list.get_currentSongId());
	}
	
	/**
	   * function 根据歌曲名搜索到歌曲列表并填充到用户的当前歌曲列表
	   * @param String CurrentList song.name,list
	   * @return
	   */
	public final void SongnameSearchingProcess(String songname){
		List result = searchservice.find_songlist_by_songname(songname);
		if(result.size() == 0)
			return;
			
		list.set_currentlabel(songname);
		list.set_list(result);
		
	}

	/**
	   * function 根据专辑名搜索到歌曲列表并填充到用户的当前歌曲列表
	   * @param String CurrentList album.name,list
	   * @return
	   */
	public final void AlbumSearchingProcess(String albumname){
		List result = searchservice.find_songlist_by_albumname(albumname);
		if(result.size() == 0)
			return;		
		
		list.set_currentlabel(albumname);
		list.set_list(result);
		
	}
	
	/**
	   * function 根据标签条件字符串搜索到歌曲列表并填充到用户的当前歌曲列表
	   * @param String Integer CurrentList words,minus,list
	   * @return
	   */
	public final void KeywordSearchingProcess(String words, int minus){
		List result = searchservice.find_songlist_by_words(words, minus);
		if(result.size() == 0)
			return;
		
		list.set_currentlabel(words);
		list.set_list(result);
		
	}
	
	/**
	   * function 根据用户输入的字符串搜索到歌曲列表并填充到用户的当前歌曲列表
	   * @param String Integer CurrentList words,minus,list
	   * @return boolean 找到与否
	   */
	public final boolean InputSearchingProcess(String input){
		
		//先做记录
		userprofile.TagRecordProcessing(list.get_userid(), input);
		
		FilterChain chain = new FilterChain();
		chain.AddFilter(new Filter(4));
		chain.AddFilter(new Filter(5));
		List result = searchservice.find_songlist_by_input(0, input, chain, list.get_userid());
		if(result.size() == 0)
			return false;
		
		list.set_currentlabel(input);
		list.set_list(result);
		
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//RecommendService的接口实现	
	/**
	   * function 获得随机标签
	   * @param 
	   * @return List Label
	   */
	public final List create_randomlabelProcess()
	{
		return recommendservice.create_randomlabel();
	}
	
	public List RecommendSinger_ByPage(Page page){

		List result = recommendservice.RecommendSinger_ByPage(list.get_userid(), page);
		
		return result;
	}
	
	public List GuessSong_ByPage(Page page){

		List result = recommendservice.GuessSong_ByPage(list.get_userid(), page);
		
		return result;
	}
	
	public List RecommendSong_BySingers(Page page){

		List result = recommendservice.RecommendSong_BySingers(list.get_userid(), page);
		
		return result;
	}
	
	public List Recommend_ByRanking(Page page){

		List result = recommendservice.Recommend_ByRanking(page);
		
		return result;
	}
	
	/**
	   * function 红心电台填充当前歌曲列表
	   * @param String CurrentList user_id,list
	   * @return
	   */
	public final void LoveRecommendProcess(){
		list.set_currentlabel("红心歌曲");
		List result = recommendservice.hongxindiantai(list.get_userid());
		if(result.size() == 0)
			return;
				
		list.set_list(result);
		
	}
	
	/**
	   * function 根据歌手歌曲推荐相似歌曲
	   * @param String CurrentList user_id,list
	   * @return
	   */
	public final void SearchSimilarSongProcess(){	
		int id = list.get_currentSongId();
		if(id == -1)
			return;
		System.out.println("当前歌曲的id:"+id+",搜索类似歌曲");
		List result = recommendservice.RecommendSong_BySong(list.give_currentsongname());
		if(result.size() == 0)
			return;
		
		list.set_list(result);
		
	}
	
	/**
	   * function 根据歌手推荐相似歌手
	   * @param String CurrentList user_id,list
	   * @return
	   */
	public final void SearchSimilarSingerProcess(){	
		String singername = list.give_currentsingername();
		if(singername == null || singername.equals(""))
			return;
		List result = recommendservice.RecommendSinger_BySinger(singername);
		if(result.size() == 0)
			return;
				
		list.set_list(result);
		
	}
	
	/**
	   * function 得到用户喜欢的歌曲list
	   * @param String CurrentList user_id,list
	   * @return
	   */
	public final List GetLoveSongListProcess(){
		List result = recommendservice.hongxindiantai(list.get_userid());
		return result;
	}
	
	/**
	   * function 随便听听填充当前歌曲列表
	   * @param CurrentList list
	   * @return
	   */
	public final void RandomRecommendProcess(){
		List result = recommendservice.suibiantingting();
		if(result.size() == 0)
			return;
		
		list.set_list(result);
		list.set_currentlabel(recommendservice.get_currentlabel());
		
	}
	
	/**
	   * function 智能推荐填充当前歌曲列表
	   * @param String CurrentList user_id,list
	   * @return
	   */
	public final void SmartRecommendProcess(){
		List result = recommendservice.zhinengtuijian(list.get_userid());
		if(result.size() == 0)
			return;
		
		list.set_list(result);
		list.set_currentlabel(recommendservice.get_currentlabel());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//AssessService的接口实现
	/**
	   * function 获得用户的喜好标签
	   * @param String user_id
	   * @return List UserTag
	   */
	public final List get_usertagProcess()
	{
		return assessservice.get_UserTag(list.get_userid());
	}
	
	public final List GetFavorSingerListProcess(Page page){
		List result = assessservice.get_FavorSingerByPage(list.get_userid(), page);
		return result;
	}
	
	/**
	   * function 收藏艺人
	   * @param CurrentList list
	   * @return
	   */
	public final boolean CollectSingerProcess(){
		return assessservice.collect_singer(list.get_userid(), list.give_currentsingername());
	}
	
	public final List get_Tagrecord(Page page)
	{
		String user_id = this.list.get_userid();
		return assessservice.get_RecordsByPage(user_id, page);
	}
	
	public final User get_User()
	{
		String user_id = this.list.get_userid();
		return assessservice.get_User(user_id);
	}
	
	public final List get_Assess(Page page)
	{
		return assessservice.get_Assess(list.get_currentSongId(), page);
	}
	
	/**
	   * function 给予歌曲评分
	   * @param String level,user_id,song_id
	   * @return
	   */
	public final void GradeMarkingProcess(String level){
		assessservice.give_levelassess(level, list.get_userid(), list.get_currentSongId());
	}
	
	/**
	   * function 给予歌曲评论
	   * @param String comment,user_id,song_id
	   * @return
	   */
	public final void CommentProcess(String comment){
		assessservice.give_commentassess(comment, list.get_userid(), list.get_currentSongId());
	}
	
	/**
	   * function 标记歌曲为讨厌的歌曲
	   * @param String user_id,song_id
	   * @return
	   */
	public final void HateProcess(){
		assessservice.give_hateveassess(list.get_userid(), list.get_currentSongId());
	}
	
	/**
	   * function 标记歌曲为喜欢的歌曲
	   * @param String user_id,song_id
	   * @return
	   */
	public final void LoveProcess(){
		assessservice.give_loveassess(list.get_userid(), list.get_currentSongId());
	}
	
	/**
	   * function 添加用户喜好标签
	   * @param String user_id,usertag
	   * @return boolean 操作是否成功
	   */
	public final boolean CustomizeTagProcess(String usertag){
		return assessservice.customize_usertag(list.get_userid(), usertag);
	}
	
	/**
	   * function 删除用户标签
	   * @param String user_id,usertag
	   * @return boolean 操作是否成功
	   */
	public final boolean UndoTagProcess(String usertag){
		return assessservice.undo_usertag(list.get_userid(), usertag);
	}
	
}
