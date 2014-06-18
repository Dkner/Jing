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
	private BasisSearch bs = null;
		
	//DAO
	private SongDAO sd = null;
	private AlbumDAO ad = null;
	private LabelDAO ld = null;
	private SingerDAO sid = null;
	
	public LabelProcessor(String user_input){
		this.conditionstring = user_input;
		this.words = conditionstring.split("\\+");
		condition_counter = words.length;
	}
	
	public LabelProcessor(){
		parser = new UrlParser();
		sd = new SongDAO();
		ad = new AlbumDAO();
		ld = new LabelDAO();
		sid = new SingerDAO();
		bs = new BasisSearch();
	}
	
	 /**
	   * function 设置标签条件
	   * @param String 用户输入
	   * @return
	   */
	public final void set_words(String user_input)
	{
		this.conditionstring = user_input;
		this.words = conditionstring.split("\\+");
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
		String[] tempresult = word.split("\\+");
		List temp = new ArrayList();
		
		//
		for(int k=0; k<tempresult.length; k++)
		{
			temp.addAll(ld.findByLabelLikedLimited(tempresult[k], 0, 9));			
		}
		return temp;
	}

	
	
	
	/**
	   * function 根据input搜索歌曲列表
	   * @param String input, Filter filter
	   * @return List Song
	   */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public final List find_songlist_by_input(int match_degree_minus, String input, FilterChain chain, String user_id){
		this.set_words(input);
		List result = new ArrayList();
		List<Integer> idlist = new ArrayList<Integer>();
		
		//
		int match_degree = 1;
		match_degree = condition_counter/2 + 1;		
		if(condition_counter>5)
			match_degree = 3;
		
		match_degree -= match_degree_minus;
		System.out.println(condition_counter+"match"+match_degree);
		//依次判断是否为songname/album/singer/label
		for(int i=0; i<this.condition_counter; i++)
		{
			//System.out.println(i+"循环");
			CONDITIONTYPE type = checkInputType(words[i]);
			if(type.equals(CONDITIONTYPE.SONG))
			{
				System.out.println("这是songname标签:"+words[i]);
				List<Song> temp = bs.find_songlist_by_songname(words[i]);
				for(int k=0; k<temp.size(); k++)
					idlist.add(temp.get(k).getId());
				continue;
			}
			if(type.equals(CONDITIONTYPE.ALBUM))
			{
				System.out.println("这是albumname标签:"+words[i]);
				List<Song> temp = bs.find_songlist_by_albumname(words[i]);
				for(int k=0; k<temp.size(); k++)
					idlist.add(temp.get(k).getId());
				continue;
			}
			if(type.equals(CONDITIONTYPE.SINGER))
			{
				System.out.println("这是singername标签:"+words[i]);
				List<Song> temp = bs.find_songlist_by_singer(words[i]);
				for(int k=0; k<temp.size(); k++)
					idlist.add(temp.get(k).getId());
				continue;
			}
			if(type.equals(CONDITIONTYPE.LABEL))
			{
				System.out.println("这是labelname标签:"+words[i]);
				List<Song> temp = bs.find_songlist_by_label(words[i]);
				for(int k=0; k<temp.size(); k++)
					idlist.add(temp.get(k).getId());
				
				//usertag weight processing
				UserDAO ud = new UserDAO();
				if(ud.findById(user_id) != null)
				{
					UserProfileProcessor userprofile = new UserProfileProcessor();
					userprofile.UserTagProcesing_FOR_Search(user_id, words[i]);
				}
					
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
      
      //System.out.println(idlist.size());
      //过滤
      idlist = chain.doFilter(idlist);
      //
      
      
      for (int i=0; i<idlist.size(); i++) 
      {
    	  Song temp = sd.findById(idlist.get(i));
    	  if(temp != null)
    		  result.add(temp);
      }    
    	  
      if(result.size() == 0 && match_degree>1)
      {
    	  return this.find_songlist_by_input(++match_degree_minus, input, chain, user_id);
      }
      
      
      //this.printMap(map);
      System.out.println("\n匹配结果：");
      Toolkit.printsong_byname(result);       
      
         
      //
      //this.importUrl(result);
	  return result;
	}
	
	//使用API导入歌曲的url
	public void importUrl(List result){
		this.parser.importUrl(result);
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//临时数据导入
	public void input_song()
	{
		
		File file=new File("J:\\Song");
		String test[];
		test=file.list();
		
		
		SingerDAO sd = new SingerDAO();
		SongDAO ss = new SongDAO();
		
		Session session = HibernateSessionFactory.getSession();
		Transaction tst = session.beginTransaction();	
		for(int i=0;i<test.length;i++)
		{
			//System.out.println(test[i]);
			//String Arecord[] = test[i].split("\\-");
			//int sl = Arecord[0].length()-1;
			//String Asinger = Arecord[0].substring(0, sl);
			//int nl = Arecord[1].length()-4;
			//String Asong = Arecord[1].substring(1,nl);
			int len = test[i].length();
			String Asong = test[i].substring(0,len-4);
			System.out.println("song"+i+":"+Asong);
			
			
			List<Song> temp = ss.findByName(Asong);
			
//			int id = 1;
//			if(temp.size()>0)
//			{
//				id = ((Singer)(temp.get(0))).getSingerId();
//				System.out.println(id);
//			}
			
			//Song tempsong = new Song(sd.findById(id),Asong,"song/"+test[i]);
			if(temp.size()>0)
			{
				for(int j=0; j<temp.size(); j++)
				{
					Song tempsong = temp.get(j);
					tempsong.setPath("song/"+test[i]);
					System.out.println("song"+i+":"+test[i]);
					//Song tempsong = new Song((temp.get(0)).getSinger(), Asong, "song/"+Asong);

					session.saveOrUpdate(tempsong);
					//ss.save(tempsong);
				}
			}
		}
		tst.commit();
		session.close();
		
	}

	public void revicePath(){
		Session session = HibernateSessionFactory.getSession();
		Transaction tst = session.beginTransaction();
		List list = sd.findAll();
		for(int i=0; i<list.size(); i++)
		{
			Song tempsong = (Song) list.get(i);
			
			String path = tempsong.getPath();
			System.out.println(path);
			if(path.startsWith("http"))
			{
				System.out.println("song"+i+",修改path:"+path+"变为null");
				tempsong.setPath("null");	
				session.saveOrUpdate(tempsong);
				//ss.save(tempsong);
			}
		}
		
		tst.commit();
		session.close();
	}
	
	
	@SuppressWarnings("static-access")
	public String getSongUrlByName(String songname) {
		// TODO Auto-generated method stub
		return parser.getSongUrl(songname);
	}

	public Song find_song_by_id(int song_id) {
		return bs.find_song_by_id(song_id);
	}

	public List find_songlist_by_input_Basic(String input) {
		FilterChain chain = new FilterChain();
		chain.AddFilter(new Filter(4));
		chain.AddFilter(new Filter(5));
		return this.find_songlist_by_input(0, input, chain, "");
	}
	
	
}
