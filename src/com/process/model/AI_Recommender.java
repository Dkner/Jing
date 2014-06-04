package com.process.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

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
		SongDAO sd = new SongDAO();
		String result = "";
		List<Integer> idlist = new ArrayList();
		List templist = new ArrayList();
		List<Song> lovesonglist = new ArrayList();
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
		
		for(int k=0; k<lovesonglist.size(); k++)
			idlist.add(lovesonglist.get(k).getId());
		
		this.printsong_byname(lovesonglist);
		//this.importUrl(lovesonglist);
		
		FilterChain chain = new FilterChain();
		chain.AddFilter(new Filter(4));
		chain.AddFilter(new Filter(5));
		idlist = chain.doFilter(idlist);
		lovesonglist.clear();
		
		for (int i=0; i<idlist.size(); i++) 
	      {
	    	  Song temp = sd.findById(idlist.get(i));
	    	  if(temp != null)
	    		  lovesonglist.add(temp);
	      }   
		this.printsong_byname(lovesonglist);
		return lovesonglist;
	}
	
	/**
	   * function 随机标签搜索歌曲列表
	   * @param 
	   * @return List Song
	   */
	@SuppressWarnings({ "rawtypes" })
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
				labelstring += temp.getLabel()+"+";
		}
		
		this.currentlabel = labelstring;
		System.out.println(labelstring);
		//
		//lp.set_words(labelstring);
		//return lp.find_songlist_by_words(labelstring, 1);
		FilterChain chain = new FilterChain();
		chain.AddFilter(new Filter(4));
		chain.AddFilter(new Filter(5));
		return lp.find_songlist_by_input(0, labelstring, chain, "");
	} 
	
	/**
	   * function 排行榜
	   * @param 
	   * @return List Song
	   */
	public final List Recommend_ByRanking(Page page)
	{
		SongDAO sd = new SongDAO();
		page.set_allcount(sd.getTotalRows());
		page.set_pagecount();
		List<Song> list = sd.findByPage(page);
		
		List<Integer> idlist = new ArrayList<Integer>();
		for(int k=0; k<list.size(); k++)
			idlist.add(list.get(k).getId());
		
		FilterChain chain = new FilterChain();
		chain.AddFilter(new Filter(4));
		chain.AddFilter(new Filter(5));
		idlist = chain.doFilter(idlist);
		list.clear();
		
		for (int i=0; i<idlist.size(); i++) 
	      {
	    	  Song temp = sd.findById(idlist.get(i));
	    	  if(temp != null)
	    		  list.add(temp);
	      }   
		this.printsong_byname(list);
		return list;
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
		int counter = 5;
		List templist = new ArrayList();
		User user = ud.findById(user_id);
		UsertagDAO utd = new UsertagDAO();
		
		templist = utd.findTopFeature(user_id, counter);
		if(templist.size() == 0)
		{
			System.out.println("该用户未设置标签");
			return null;
		}
		//得到用户排行前五的标签
		List<Label> labellist = new ArrayList<Label>();
		for(int i=0; i<templist.size(); i++)
		{
			Usertag tag = (Usertag) templist.get(i);
			labellist.add(tag.getLabel());
		}
		for (Iterator<Label> it = labellist.iterator(); it.hasNext();){ 
			//Label temp = ((Usertag)i.next()).getLabel();
			counter--;
			if(counter == 0)
				labelstring += it.next().getLabel();
			else
				labelstring += it.next().getLabel()+"+";
			
		} 	
		
		this.currentlabel = labelstring;
		//
		FilterChain chain = new FilterChain();
		chain.AddFilter(new Filter(4));
		chain.AddFilter(new Filter(5));
		return lp.find_songlist_by_input(0, labelstring, chain, user_id);
	}
	
	/**
	   * function 根据一首歌曲推荐类似的歌曲
	   * @param Song Integer song,tracksnumber
	   * @return List Song
	   */
	@SuppressWarnings("unchecked")
	public final List RecommendSong_BySong(int songId){
		Song song = new SongDAO().findById(songId);
		if(song == null)
			return null;
		List<Tag> temp = new ArrayList();
		temp.addAll(song.getTags());
		String labelstring = "";
		for(int i=0; i<temp.size(); i++)
		{
			if(i!=0)
				labelstring += "+";
			labelstring += temp.get(i).getLabel().getLabel();
		}
		
		System.out.println(labelstring);
		//
		FilterChain chain = new FilterChain();
		Filter filter = new Filter(1);
		List filterlist = new ArrayList();
		filterlist.add(song.getId());
		filter.setFilterCondition(filterlist);
		chain.AddFilter(filter);
		chain.AddFilter(new Filter(4));
		chain.AddFilter(new Filter(5));
		//
		return lp.find_songlist_by_input(0, labelstring, chain, "");
	}
	
	/**
	   * function 根据一个歌手推荐类似的歌手
	   * @param Singer Integer singer,tracksnumber
	   * @return List Song
	   */
	public final List RecommendSinger_BySinger(String singername){
		List<Integer> tempsinger_idlist = new ArrayList<Integer>();
		tempsinger_idlist = this.RecommendSinger(singername);
		int index = (Integer) this.create_randomnumber(tempsinger_idlist.size(), 1).get(0);
		int singerid_chosen = 0;
		if(index>=0)
			singerid_chosen = tempsinger_idlist.get(index);
		//
		System.out.println("数字:"+singerid_chosen+"\n最后选中的歌手id:"+sid.findById(singerid_chosen).getSingerId());
		FilterChain chain = new FilterChain();
		chain.AddFilter(new Filter(4));
		chain.AddFilter(new Filter(5));
		return lp.find_songlist_by_input(0, sid.findById(singerid_chosen).getName(), chain, "");
	}	
	
	/**
	   * function 推荐歌手
	   * @param Singer user_id
	   * @return List Song
	   */
	public final List RecommendSinger_ByPage(String user_id, Page page){
		
		SingerDAO sd = new SingerDAO();
		page.set_allcount(sd.findAll().size());
		page.set_pagecount();
		
		UserDAO ud = new UserDAO();
		User user = ud.findById(user_id);
		List<Integer> tempsinger_idlist = new ArrayList<Integer>();
		List singers = new ArrayList();
		List favors = new ArrayList();
		Set userfavor = user.getFavors();
		if(user.getFavors() != null && userfavor.size()>0)
		{
			favors.addAll(userfavor);
			if(favors.size()>0)
			{
				for(int i=0; i<favors.size(); i++)
				{
					Singer singer = ((Favor) favors.get(i)).getSinger();
					tempsinger_idlist.addAll(this.RecommendSinger(singer.getName()));
				}
			}
		}
		
		//随机艺人
		List<Integer> random_idlist = this.create_randomnumber(sd.findAll().size(), 5);
		for(int i=0; i<random_idlist.size(); i++)
		{
			if(!tempsinger_idlist.contains(random_idlist.get(i)))
				tempsinger_idlist.add(random_idlist.get(i));
		}
		
		int counter = 0;
		int j=(page.get_pagenow()-1)*page.get_pagesize(); 
		//page.print_page();
		//System.out.println("j:"+j+"\nsize:"+tempsinger_idlist.size());
		while(j<tempsinger_idlist.size())
		{
			int id = tempsinger_idlist.get(j);
			singers.add(sd.findById(id));
			counter++;
			j++;
			if(counter == page.get_pagesize())
				break;
		}
		
		return singers;
	}	
	
	public final List RecommendSong_BySingers(String user_id, Page page){
		
		SongDAO sd = new SongDAO();
		List<Integer> idlist = new ArrayList<Integer>();
		List<Singer> singers = (List<Singer>)this.RecommendSinger_ByPage(user_id, page);
		
		List<Song> songs = new ArrayList();
		for(int i=0; i<singers.size(); i++)
		{
			Set temp = singers.get(i).getSongs();
			if(temp != null)
				songs.addAll(temp);
		}
		
		for(int k=0; k<songs.size(); k++)
			idlist.add(songs.get(k).getId());
		
		FilterChain chain = new FilterChain();
		chain.AddFilter(new Filter(4));
		chain.AddFilter(new Filter(5));
		
		idlist = chain.doFilter(idlist);
		songs.clear();
		
		for (int i=0; i<idlist.size(); i++) 
	      {
	    	  Song temp = sd.findById(idlist.get(i));
	    	  if(temp != null)
	    		  songs.add(temp);
	      }   
		
		return songs;
	}	
	
	//根据一个歌手推荐类似的歌手们
	public final List RecommendSinger(String singername)
	{
		SingerDAO sd = new SingerDAO();
		List<Singer> singerlist = sd.findByName(singername);
		Singer singer = null;
		if(singerlist.size()>0)
			singer = singerlist.get(0);
		else
			return null;
		
		List<Integer> tempsinger_idlist = new ArrayList<Integer>();
		List templabel = new ArrayList();
		List<Singerlabel> singerlabel = new ArrayList<Singerlabel>();	
		singerlabel.addAll(singer.getSingerlabels());

		//匹配下限
		int match_degree = 1;
		match_degree = singerlabel.size()/2 + 1;		
		//System.out.println(singerlabel.size()+"个标签需要命中"+match_degree+"个");
		
		//根据singerlabel顺推找到label
		for(int k=0; k<singerlabel.size(); k++)
		{
			Label temp = singerlabel.get(k).getLabel();
			templabel.addAll(temp.getSingerlabels());
		}
		//再根据singerlabel反推找到它对应的一个歌手，加入所有相关歌手的id
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
		//this.printMap(map);
		//情况临时的所有歌手id在根据map导入
		tempsinger_idlist.clear();
		for (int temp : map.keySet()) 
		{ 
			int id = map.get(temp);
			if(id < match_degree || temp == singer.getSingerId())
				continue;
			tempsinger_idlist.add(temp);
		}      
		
		//for(int h=0;h<tempsinger_idlist.size();h++)
		//	System.out.println("待选歌手id:"+tempsinger_idlist.get(h));
		
		return tempsinger_idlist;
	}
	
	public final String get_currentlabel(){
		return this.currentlabel;
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
	   * @param int 上限（不包含边界），随机数字个数
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
			int label_id_choosed = god.nextInt(uplimit);
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
	
	
	//打印算法中的匹配命中情况
	public void printMap(Map<Integer, Integer> map) { 
		System.out.println("具体匹配情况:");
		if(map.size() == 0)
			System.out.println("空");
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) { 
            System.out.println("id: " + entry.getKey() + "    匹配成功次数: "
                    + entry.getValue()); 
        } 
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
 