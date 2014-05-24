package com.process.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.data.vo.*;
import com.process.service.RecommendService;
import com.web.api.UrlParser;
import com.web.api.WebApiInterface;

public class AI_Recommender implements WebApiInterface,RecommendService {

	//size为数据库标签总数，用来随机
	private int size = 0;
	//随便听听函数的随机标签个数
	private final int condition_counter = 3;
	//随机标签数目
	private final int label_counter = 10;
	//当前的标签内容字符串
	private String currentlabel = "";
	//DAO
	private LabelDAO ld = null; 
	private SingerDAO sid = null;
	private LabelProcessor lp = null;
	private UserDAO ud = null;
	
	//UrlParser
	private UrlParser parser = null;
	
	
	
	
	
	//
	public AI_Recommender()
	{
		sid = new SingerDAO();
		ud = new UserDAO();
		ld = new LabelDAO();
		lp = new LabelProcessor();
		size = ld.findAll().size();
		
		parser = new UrlParser();
	}
	
	/**
	   * function 找到用户标记过喜欢的歌曲列表
	   * @param String 用户id
	   * @return List Song
	   */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public final List hongxindiantai(String user_id)
	{
		String result = "";
		List templist = new ArrayList();
		List lovesonglist = new ArrayList();
		User user = ud.findById(user_id);
		templist.addAll(user.getAssesses());
	
		for (Iterator i = templist.iterator(); i.hasNext();){  
			Assess assess = (Assess)i.next();	
			result = assess.getLoveorhate();
			//System.out.println(result);
			if(result != null && result.equals("love"))
			{
				lovesonglist.add(assess.getSong());
			}
		} 
		
		this.printsong_byname(lovesonglist);
		this.importUrl(lovesonglist);
		
		return lovesonglist;
	}
	
	/**
	   * function 随机标签搜索歌曲列表
	   * @param 
	   * @return List Song
	   */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public final List suibiantingting()
	{
		List number = new ArrayList();
		String labelstring = "";		
		//
		number = this.create_randomnumber(size, condition_counter);
		for(int i=0;i<number.size();i++)
		{
			Label temp = ld.findById((Integer)(number.get(i)));
			if(temp == null)
				continue;
			if(i == condition_counter-1)
				labelstring += temp.getLabel();
			else
				labelstring += temp.getLabel()+"/";
		}
		
		this.currentlabel = labelstring;
		System.out.println(labelstring);
		//
		//lp.set_words(labelstring);
		//return lp.find_songlist_by_words(labelstring, 1);
		return lp.find_songlist_by_input(labelstring, new Filter(4));
	}
	
	/**
	   * function 得到用户已有的喜欢标签
	   * @param String 用户id
	   * @return List UserTag
	   */
	public final List get_UserTag(String user_id)
	{
		List templist = new ArrayList();
		List list = new ArrayList();
		User user = ud.findById(user_id);
		templist.addAll(user.getUsertags());
		if(templist.size() == 0)
		{
			System.out.println("该用户未设置标签");
			return templist;
		}
		for (int i = 0; i<templist.size(); i++){ 
			Label temp = ((Usertag)templist.get(i)).getLabel();
			list.add(temp);
		}
		return list;
	}
	
	/**
	   * function 根据用户的喜好标签找到相关歌曲列表
	   * @param String 用户id
	   * @return List Song
	   */
	public final List zhinengtuijian(String user_id)
	{
		//
		String labelstring = "";
		int counter = 0;
		List templist = new ArrayList();
		User user = ud.findById(user_id);
		templist.addAll(user.getUsertags());
		if(templist.size() == 0)
		{
			System.out.println("该用户未设置标签");
			return null;
		}
		for (Iterator i = templist.iterator(); i.hasNext();){ 
			counter++;
			Label temp = ((Usertag)i.next()).getLabel();
			if(counter == templist.size())
				labelstring += temp.getLabel();
			else
				labelstring += temp.getLabel()+"/";
		} 	
		
		this.currentlabel = labelstring;
		System.out.println("随机的标签： "+labelstring);
		//
		//lp.set_words(labelstring);
		return lp.find_songlist_by_input(labelstring, new Filter(4));
	}
	
	/**
	   * function 根据一首歌曲推荐类似的歌曲
	   * @param Song Integer song,tracksnumber
	   * @return List Song
	   */
	public final List RecommendSong_BySong(Song song){
		List<Tag> temp = new ArrayList();
		temp.addAll(song.getTags());
		String labelstring = "";
		for(int i=0; i<temp.size(); i++)
		{
			if(i!=0)
				labelstring += "/";
			labelstring += temp.get(i).getLabel().getLabel();
		}
		
		//
		Filter filter = new Filter(1);
		List filterlist = new ArrayList();
		filterlist.add(song.getId());
		filter.setFilterCondition(filterlist);
		//
		return lp.find_songlist_by_input(labelstring, filter);
	}
	
	/**
	   * function 根据一个歌手推荐类似的歌手
	   * @param Singer Integer singer,tracksnumber
	   * @return List Song
	   */
	public final List RecommendSinger_BySinger(Singer singer){
		List<Integer> tempsinger_idlist = new ArrayList<Integer>();
		List templabel = new ArrayList();
		List<Singerlabel> singerlabel = new ArrayList<Singerlabel>();	
		singerlabel.addAll(singer.getSingerlabels());
		
		//匹配下限
		int match_degree = 1;
		match_degree = (2+singerlabel.size())/2;		
		
		//根据singerlabel找到label
		for(int k=0; k<singerlabel.size(); k++)
		{
			Label temp = singerlabel.get(k).getLabel();
			templabel.addAll(temp.getSingerlabels());
		}
		//再根据singer在singerlabel表中的记录反推找到它对应的一个歌手，加入所有相关歌手的id
		for(int j=0; j<templabel.size(); j++)
		{
			tempsinger_idlist.add(((Singerlabel)templabel.get(j)).getSinger().getSingerId());
		}
		//每个singer_id匹配了几次,放到map中
		Map<Integer, Integer> map = new HashMap<Integer, Integer>(); 
		for (int temp : tempsinger_idlist) { 
			Integer count = map.get(temp); 
			map.put(temp, (count == null) ? 1 : count + 1); 
		} 
		tempsinger_idlist.clear();
		for (int temp : map.keySet()) 
		{ 
			int id = map.get(temp);
			if(id < match_degree || id == singer.getSingerId())
				continue;
			tempsinger_idlist.add(temp);
		}      
		int index = (Integer) this.create_randomnumber(tempsinger_idlist.size()-1, 1).get(0);
		int singerid_chosen = 0;
		if(index>=0)
			singerid_chosen = tempsinger_idlist.get(index);
		//
		System.out.println("相似歌手："+sid.findById(singerid_chosen).getName());
		//return lp.find_songlist_by_singer(sid.findById(singerid_chosen).getName());
		return lp.find_songlist_by_input(sid.findById(singerid_chosen).getName(), new Filter(4));
	}
	
	/**
	   * function 根据一首歌推荐它的演唱歌手
	   * @param Song Integer song,tracksnumber
	   * @return List Song
	   */
	public final List RecommendSinger_BySong(Song song){
		
		Singer singer = song.getSinger();
		return lp.find_songlist_by_input(song.getSinger().getName(), new Filter(4));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	   * function 辅助函数，生成随机标签
	   * @param 
	   * @return List Label
	   */
	public final List create_randomlabel()
	{
		List number = new ArrayList();
		List labellist = new ArrayList();	
		//
		number = this.create_randomnumber(size, label_counter);
		for(int i=0;i<number.size();i++)
		{
			Label temp = ld.findById((Integer)(number.get(i)));
			if(temp == null)
				continue;
			labellist.add(temp);
		}
		//
		return labellist;
		
	}
	
	/**
	   * function 生成随机数字，用来随机标签
	   * @param int 上限（包含边界），随机数字个数
	   * @return List integers
	   */
	public final List create_randomnumber(int uplimit, int amount)
	{
		int counter = 0;
		List number = new ArrayList();
		Random god = new Random();
		//
		while(counter<amount)
		{
			int label_id_choosed = god.nextInt(uplimit)+1;
			if(!number.contains(label_id_choosed))
			{
				counter++;
				number.add(label_id_choosed);
			}
		}
		
		
		return number;
	}
	
	
	
	
	
	//临时数据标签导入
	public void input_tag()
	{
		List<Integer> number = new ArrayList<Integer>();
		TagDAO td = new TagDAO();
		LabelDAO ld = new LabelDAO();
		SongDAO sd = new SongDAO();
		
		Session session = HibernateSessionFactory.getSession();
		Transaction tst = session.beginTransaction();
		
		for(int i=1;i<83;i++)
		{	
			Song tempsong = sd.findById(i);
			number = this.create_randomnumber(size, 4);
			
			for(int j=0; j<4; j++)
			{
				Label templabel = ld.findById(number.get(j));		
				Tag temptag = new Tag(templabel, tempsong);
				System.out.println("歌曲名："+tempsong.getName()+"  添加标签："+templabel.getLabel());
				td.save(temptag);
			}
			
		}	
		
		
		tst.commit();
		session.close();
	}
	
	
	
	//打印songlist
	public void printsong_byname(List songlist) {
		for (int i = 0; i < songlist.size(); i++) {
			System.out.println("id: " + ((Song) songlist.get(i)).getId()
					+ "name：" + ((Song) songlist.get(i)).getName());
		}
	}

	public String getSongUrlByName(String songname) {
		// TODO Auto-generated method stub
		return this.parser.getSongUrl(songname);
	}

	public void importUrl(List result) {
		// TODO Auto-generated method stub
		this.parser.importUrl(result);
	}
}
 