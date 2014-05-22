package com.process.model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.data.vo.*;
import com.process.service.SearchService;
import com.web.api.UrlParser;
import com.web.api.WebApiInterface;

public class LabelProcessor implements WebApiInterface,SearchService {
	
	enum CONDITIONTYPE{
		SONG,
		ALBUM,
		SINGER,
		LABEL,
		UNKNOWN
	}
	
	//需要搜索的标签条件个数
	private int condition_counter = 0;
	//存放用户输入的标签条件字符串
	private String conditionstring;
	//经过解析的标签数组
	private String[] words = null;
	//
	//public HashMap<String, String> labelmap;
	//UrlParser
	private UrlParser parser = null;
		
	//DAO
	private SongDAO sd = null;
	private AlbumDAO ad = null;
	private LabelDAO ld = null;
	private SingerDAO sid = null;
	
	public LabelProcessor(String user_input){
		this.conditionstring = user_input;
		this.words = conditionstring.split("\\/");
		condition_counter = words.length;
	}
	
	public LabelProcessor(){
		parser = new UrlParser();
		sd = new SongDAO();
		ad = new AlbumDAO();
		ld = new LabelDAO();
		sid = new SingerDAO();
	}
	
	 /**
	   * function 设置标签条件
	   * @param String 用户输入
	   * @return
	   */
	public final void set_words(String user_input)
	{
		this.conditionstring = user_input;
		this.words = conditionstring.split("\\/");
		condition_counter = words.length;
	}
	
	 /**
	   * function 给出当前标签条件字符串
	   * @return String
	   */
	public final String give_currentlabel(){
		return this.conditionstring;
	}
	
	/**
	   * function 给出当前一个标签条件想匹配的标签列表
	   * @param String  one word
	   * @return List Label 
	   */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public final List find_matchlabel(String word)
	{
		String[] tempresult = word.split("\\/");
		List temp = new ArrayList();
		
		//
		for(int k=0; k<tempresult.length; k++)
		{
			temp.addAll(ld.findByLabelLikedLimited(tempresult[k], 0, 9));			
		}
		return temp;
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
		this.printsong_byname(temp);
		
		
		//
        this.importUrl(temp);
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
		this.printsong_byname(temp);
		
		
		//
        this.importUrl(temp);
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
		this.printsong_byname(temp);
		
		
		//
        this.importUrl(temp);
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
		this.printsong_byname(temp);
		
		
		//
        this.importUrl(temp);
		return temp;
	}
	
	/**
	   * function 根据当前的标签条件搜索歌曲列表
	   * @param int 是否降低匹配精度（1）
	   * @return List Song
	   */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public final List find_songlist_by_words(String input, int minus){

		this.set_words(input);
		
		List<Integer> tempsong_idlist = new ArrayList<Integer>();
		List templabel = new ArrayList();
		List temptag = new ArrayList();
				
		//取数据量的基准数
		int cardinalsongnumber = 150;
		//匹配下限
		int match_degree = 1;
		match_degree = (2+condition_counter)/2;		
		if(match_degree - minus >=1)
			match_degree -= minus; 
		//
		System.out.println("match_degree:"+match_degree+"\n"+"cardinalsongnumber:"+cardinalsongnumber
							+"\n"+"condition_counter:"+condition_counter);
		
		//对于每个搜索词，找到它对应的label，合并到templabel
		for(int i=0; i<condition_counter; i++)
		{
			templabel.addAll(ld.findByProperty("label", words[i]));
		}
		//根据label找到tag
		for(int k=0; k<templabel.size(); k++)
		{
			temptag.addAll(((Label)templabel.get(k)).getTags());
		}
		//根据label在tag表中找到它对应的一首歌曲，加入所有相关歌曲的id
		for(int j=0; j<temptag.size(); j++)
		{
			tempsong_idlist.add(((Tag)temptag.get(j)).getSong().getId());
		}
		for(int k=0; k<tempsong_idlist.size(); k++)
		{
			System.out.println("待处理的id："+tempsong_idlist.get(k));
		}
		//每个song_id的匹配了几次,放到map中
		Map<Integer, Integer> map = new HashMap<Integer, Integer>(); 
        for (int temp : tempsong_idlist) { 
            Integer count = map.get(temp); 
            map.put(temp, (count == null) ? 1 : count + 1); 
        } 
        //this.printMap(map);
        templabel.clear();
        for (int temp : map.keySet()) 
        { 
        	if(map.get(temp) < match_degree)
        		continue;
        	templabel.add(sd.findById(temp));
        }        
        //
        this.printMap(map);
        System.out.println("匹配结果：");
        this.printsong_byname(templabel);    
        
        
        //
        this.importUrl(templabel);
		return templabel;
	}	
	
	/**
	   * function 根据input搜索歌曲列表
	   * @param String input, Filter filter
	   * @return List Song
	   */
	@SuppressWarnings({ "rawtypes" })
	public final List find_songlist_by_input(String input, Filter filter){
		this.set_words(input);
		List result = new ArrayList();
		List<Integer> idlist = new ArrayList<Integer>();
		
		//
		int match_degree = 1;
		match_degree = (1+condition_counter)/2;		
		System.out.println(condition_counter+"match"+match_degree);
		//依次判断是否为songname/album/singer/label
		for(int i=0; i<this.condition_counter; i++)
		{
			//System.out.println(i+"循环");
			CONDITIONTYPE type = checkInputType(words[i]);
			if(type.equals(CONDITIONTYPE.SONG))
			{
				System.out.println("这是songname标签:"+words[i]);
				List<Song> temp = find_songlist_by_songname(words[i]);
				for(int k=0; k<temp.size(); k++)
					idlist.add(temp.get(k).getId());
				continue;
			}
			if(type.equals(CONDITIONTYPE.ALBUM))
			{
				System.out.println("这是albumname标签:"+words[i]);
				List<Song> temp = find_songlist_by_albumname(words[i]);
				for(int k=0; k<temp.size(); k++)
					idlist.add(temp.get(k).getId());
				continue;
			}
			if(type.equals(CONDITIONTYPE.SINGER))
			{
				System.out.println("这是singername标签:"+words[i]);
				List<Song> temp = find_songlist_by_singer(words[i]);
				for(int k=0; k<temp.size(); k++)
					idlist.add(temp.get(k).getId());
				continue;
			}
			if(type.equals(CONDITIONTYPE.LABEL))
			{
				System.out.println("这是labelname标签:"+words[i]);
				List<Song> temp = find_songlist_by_label(words[i]);
				for(int k=0; k<temp.size(); k++)
					idlist.add(temp.get(k).getId());
				continue;
			}
		}
		//
		Map<Integer, Integer> map = new HashMap<Integer, Integer>(); 
      for (int temp : idlist) { 
          Integer count = map.get(temp); 
          map.put(temp, (count == null) ? 1 : count + 1); 
      } 
      //
      idlist.clear();
      for (int temp : map.keySet()) 
      { 
      	if(map.get(temp) < match_degree)
      		continue;
      	idlist.add(temp);
      }   
      //filtering
      idlist = filter.filtering(idlist);
      //
      for (int i=0; i<idlist.size(); i++) 
      {
    	  Song temp = sd.findById(idlist.get(i));
    	  if(temp != null)
    		  result.add(temp);
      }    
    	  
      this.printMap(map);
      System.out.println("匹配结果：");
      this.printsong_byname(result);       
      
      
      
      
      //
      this.importUrl(result);
	  return result;
	}
	
	//使用API导入歌曲的url
	public void importUrl(List result){
		SongDAO sd = new SongDAO();
		Session session = HibernateSessionFactory.getSession();
		Transaction tst = session.beginTransaction();	
		
		for(int i=0; i<result.size(); i++)
		{		
			String currentpath = this.getSongUrlByName(((Song)result.get(i)).getName());
			Song tempsong = new Song();
			tempsong.setId(((Song)result.get(i)).getId());
			tempsong.setPath(currentpath);
			sd.save(tempsong);
		}
		
		tst.commit();
		session.close();
	}
	
	public CONDITIONTYPE checkInputType(String condition){
		if(sd.findByName(condition).size()>0)
			return CONDITIONTYPE.SONG;
		else if(ad.findByName(condition).size()>0)
			return CONDITIONTYPE.ALBUM;
		else if(sid.findByName(condition).size()>0)
			return CONDITIONTYPE.SINGER;
		else if(ld.findByLabel(condition).size()>0)
			return CONDITIONTYPE.LABEL;
		else
			return CONDITIONTYPE.UNKNOWN;
	}
	
	
	/**
	   * function 后台输出歌曲匹配情况
	   * @param Map 歌曲（id，匹配成功次数）
	   * @return
	   */
	public void printMap(Map<Integer, Integer> map) { 
		System.out.println("具体匹配情况:");
		if(map.size() == 0)
			System.out.println("空");
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) { 
            System.out.println("id: " + entry.getKey() + "    匹配成功次数: "
                    + entry.getValue()); 
        } 
    } 
	
	/**
	   * function 后台输出搜索到的歌曲列表
	   * @param List Song
	   * @return
	   */
	public void printsong_byname(List songlist){
		if(songlist.size() == 0)
		{
			System.out.println("未找到歌曲");
			return;
		}
		for (int i = 0; i < songlist.size(); i++) {
			 System.out.println("id: "+((Song)songlist.get(i)).getId()+"    name："
					 +((Song)songlist.get(i)).getName());	
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//临时数据导入
	public void input_song()
	{
		
		File file=new File("D:\\MyEclipse_ide\\.metadata\\.me_tcat\\webapps\\Jing\\Song");
		String test[];
		test=file.list();
		
		
		SingerDAO sd = new SingerDAO();
		SongDAO ss = new SongDAO();
		
		for(int i=0;i<test.length;i++)
		{
			//System.out.println(test[i]);
			String Arecord[] = test[i].split("\\-");
			int sl = Arecord[0].length()-1;
			String Asinger = Arecord[0].substring(0, sl);
			int nl = Arecord[1].length()-4;
			String Asong = Arecord[1].substring(1,nl);
			System.out.println(Asinger+" "+Asong);
			
			
			List temp = sd.findByName(Asinger);
			
			int id = 1;
			if(temp.size()>0)
			{
				id = ((Singer)(temp.get(0))).getSingerId();
				System.out.println(id);
			}
			
			Song tempsong = new Song(sd.findById(id),Asong,"song/"+test[i]);
			
			Session session = HibernateSessionFactory.getSession();
			Transaction tst = session.beginTransaction();	
			ss.save(tempsong);
			tst.commit();
			session.close();
		}	
		
	}

	
	
	@SuppressWarnings("static-access")
	public String getSongUrlByName(String songname) {
		// TODO Auto-generated method stub
		return parser.getSongUrl(songname);
	}
	
	
}
