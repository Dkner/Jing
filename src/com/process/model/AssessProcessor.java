package com.process.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.data.vo.*;
import com.process.service.AssessService;

public class AssessProcessor implements AssessService {
	//DAO
	public AssessDAO ad = null;
	public UserDAO ud = null;
	public User user = null;
	public SongDAO sd = null;
	private UsertagDAO utd = null;
	private LabelDAO ld = null;
	//
	
	public AssessProcessor()
	{
		ad = new AssessDAO();
		ud = new UserDAO();
		sd = new SongDAO();
		utd = new UsertagDAO();
		ld = new LabelDAO();
	}
	
	/**
	   * function 给予歌曲评分
	   * @param String 分数，user_id,song_id
	   * @return
	   */
	public final void give_levelassess(String level, String user_id, int song_id)
	{		
	
		user = ud.findById(user_id);
		List temp = new ArrayList();
		temp.addAll(user.getAssesses());
		for (Iterator i = temp.iterator(); i.hasNext();){  
			Assess assess = (Assess)i.next();	
			if(((Song)assess.getSong()).getId().equals(song_id))
			{
				System.out.println("已有评分");
				Session session = HibernateSessionFactory.getSession();
				Transaction tst = session.beginTransaction();
				assess.setLevel(level);
				tst.commit();
				session.close();
				return;
			}
		} 

	
			System.out.println("新建评分");
			User tempuser = ud.findById(user_id);
			Song tempsong = sd.findById(song_id);
			Date date = new Date(System.currentTimeMillis()); 
			//
			Session session = HibernateSessionFactory.getSession();
			Transaction tst = session.beginTransaction();
			Assess newassess = new Assess(tempuser,tempsong,null,null,level,date.toLocaleString());
			ad.save(newassess);
			tst.commit();
			session.close();

	}
	
	/**
	   * function 给予歌曲评论
	   * @param String 评论，user_id,song_id
	   * @return
	   */
	public final void give_commentassess(String comment, String user_id, int song_id)
	{
		user = ud.findById(user_id);
		List temp = new ArrayList();
		temp.addAll(user.getAssesses());
		for (Iterator i = temp.iterator(); i.hasNext();){  
			Assess assess = (Assess)i.next();	
			if(((Song)assess.getSong()).getId().equals(song_id))
			{
				System.out.println("已有评论");
				Session session = HibernateSessionFactory.getSession();
				Transaction tst = session.beginTransaction();
				assess.setComment(comment);
				tst.commit();
				session.close();
				return;
			}
		} 
		System.out.println("新建评论");
		User tempuser = ud.findById(user_id);
		Song tempsong = sd.findById(song_id);
		Date date = new Date(System.currentTimeMillis()); 
		//
		Session session = HibernateSessionFactory.getSession();
		Transaction tst = session.beginTransaction();
		Assess newassess = new Assess(tempuser,tempsong,null,comment,null,date.toLocaleString());
		ad.save(newassess);
		tst.commit();
		session.close();
	}
	
	/**
	   * function 当前歌曲的评论翻到上一页
	   * @param String user_id,song_id
	   * @return
	   */
	public final void give_loveassess(String user_id, int song_id)
	{
		UserProfileProcessor userprofile = new UserProfileProcessor();
		userprofile.UserTagProcesing_FOR_CollectSong(user_id, song_id);
		
		user = ud.findById(user_id);
		List temp = new ArrayList();
		temp.addAll(user.getAssesses());
		for (Iterator i = temp.iterator(); i.hasNext();){  
			Assess assess = (Assess)i.next();	
			if(((Song)assess.getSong()).getId().equals(song_id))
			{
				System.out.println("已标记");
				Session session = HibernateSessionFactory.getSession();
				Transaction tst = session.beginTransaction();
				assess.setLoveorhate("love");
				assess.setLevel("9");
				session.saveOrUpdate(assess);
				tst.commit();
				session.close();
				return;
			}
		} 
		System.out.println("新标记喜欢");
		User tempuser = ud.findById(user_id);
		Song tempsong = sd.findById(song_id);
		Date date = new Date(System.currentTimeMillis()); 
		//
		Session session = HibernateSessionFactory.getSession();
		Transaction tst = session.beginTransaction();
		Assess newassess = new Assess(tempuser,tempsong,"love",null,"9",date.toLocaleString());
		session.saveOrUpdate(newassess);
		tst.commit();
		session.close();
	}
	
	/**
	   * function 讨厌一首歌曲
	   * @param String user_id,song_id
	   * @return
	   */
	public final void give_hateveassess(String user_id, int song_id)
	{
		UserProfileProcessor userprofile = new UserProfileProcessor();
		userprofile.UserTagProcesing_FOR_HateSong(user_id, song_id);
		
		user = ud.findById(user_id);
		List temp = new ArrayList();
		temp.addAll(user.getAssesses());
		for (Iterator i = temp.iterator(); i.hasNext();){  
			Assess assess = (Assess)i.next();	
			if(((Song)assess.getSong()).getId().equals(song_id))
			{
				System.out.println("以标记");
				Session session = HibernateSessionFactory.getSession();
				Transaction tst = session.beginTransaction();
				assess.setLoveorhate("hate");
				assess.setLevel("1");
				session.saveOrUpdate(assess);
				tst.commit();
				session.close();
				return;
			}
		} 
		System.out.println("讨厌");
		User tempuser = ud.findById(user_id);
		Song tempsong = sd.findById(song_id);
		Date date = new Date(System.currentTimeMillis()); 
		//
		Session session = HibernateSessionFactory.getSession();
		Transaction tst = session.beginTransaction();
		Assess newassess = new Assess(tempuser,tempsong,"hate",null,"1",date.toLocaleString());
		ad.save(newassess);
		tst.commit();
		session.close();
	}
	
	/**
	   * function 更新用户基本信息
	   * @param String 基本信息
	   * @return
	   */
	public final void update_userinfo(String user_id, String name, String sex, String signature)
	{
		user = ud.findById(user_id);
		//
		Session session = HibernateSessionFactory.getSession();
		Transaction tst = session.beginTransaction();
		user.setName(name);
		user.setSex(sex);
		user.setSignature(signature);
		tst.commit();
		session.close();
	}
	
	/**
	   * function 更改密码
	   * @param String user_id,旧密码，新密码
	   * @return
	   */
	public final boolean update_keyword(String user_id, String oldkeyword, String newkeyword)
	{
		user = ud.findById(user_id);
		//
		if(user.getKeyword().equals(oldkeyword))
		{
			Session session = HibernateSessionFactory.getSession();
			Transaction tst = session.beginTransaction();
			user.setKeyword(newkeyword);
			tst.commit();
			session.close();
			return true;
		}
		else
			return false;
	}
	
	
	/**
	   * function 删除用户已有标签
	   * @param String 用户id，用户已有标签名
	   * @return
	   */
	public final void undo_usertag(String user_id, String usertag)
	{
		User tempuser = ud.findById(user_id);
		List temptag = new ArrayList();
		temptag.addAll(tempuser.getUsertags());
		for (Iterator i = temptag.iterator(); i.hasNext();){  
			Usertag tempusertag = (Usertag)i.next();	
			if(tempusertag.getLabel().getLabel().equals(usertag))
			{
				System.out.println("删除usertag");
				Session session = HibernateSessionFactory.getSession();
				Transaction tst = session.beginTransaction();
				utd.delete(tempusertag);
				tst.commit();
				session.close();
				return;
			}		
		} 
		System.out.println("没有匹配的标签需要删除");
	}
	
	/**
	   * function 用户添加喜好的已知标签
	   * @param String 用户id，已知标签名
	   * @return boolean 添加成功与否
	   */
	public final boolean customize_usertag(String user_id, String usertag)
	{
		List temp = new ArrayList();
		temp = ld.findByLabel(usertag);
		if(temp.size() == 0)
		{
			System.out.println("没有匹配的标签可以添加");
			return false;
		}
		else
		{
			//
			User tempuser = ud.findById(user_id);
			List temptag = new ArrayList();
			temptag.addAll(tempuser.getUsertags());
			for (Iterator i = temptag.iterator(); i.hasNext();){  
				Usertag tempusertag = (Usertag)i.next();	
				if(tempusertag.getLabel().getLabel().equals(usertag))
				{
					System.out.println("已有usertag");
					return false;
				}
			} 
			//
			System.out.println("新建usertag");
			Session session = HibernateSessionFactory.getSession();
			Transaction tst = session.beginTransaction();
			Usertag newusertag = new Usertag((Label)(temp.get(0)), ud.findById(user_id), 30, 0);
			utd.save(newusertag);
			tst.commit();
			session.close();
			return true;
		}
	}
	
	/**
	   * function 收藏艺人
	   * @param String 基本信息
	   * @return
	   */
	public final boolean collect_singer(String user_id, String singername)
	{
		UserProfileProcessor userprofile = new UserProfileProcessor();
		userprofile.TagRecordProcessing(user_id, singername);
		
		FavorDAO fd = new FavorDAO();
		SingerDAO sid = new SingerDAO();
		Singer singer = null;
		User user = null;
		if(sid.findByName(singername).size()>0)
			singer = (Singer) sid.findByName(singername).get(0);
		else
		{
			System.out.println("该歌手不存在");
			return false;
		}
		
		user = ud.findById(user_id);
		if(user == null)
		{
			System.out.println("该用户不存在");
			return false;
		}
			
		Map<String,Object> conditions = new HashMap<String,Object>(); 
	    conditions.put("singer_id", singer.getSingerId());
	    conditions.put("fans_id", user_id);
		if(fd.findByMultiProperty(conditions).size()>0)
		{
			System.out.println("已经是粉丝了");
			return false;
		}
		
		System.out.println("新加入粉丝");
		Session session = HibernateSessionFactory.getSession();
		Transaction tst = session.beginTransaction();
		Favor favor = new Favor(user, singer);
		fd.save(favor);
		tst.commit();
		session.close();
		
		return true;
	}

	public List get_RecordsByPage(String user_id, Page page) {
		// TODO Auto-generated method stub
		TagrecordDAO td = new TagrecordDAO();
		page.set_allcount(td.getUserTotalRows(user_id));
		page.set_pagecount();
		return td.findPropertyByPage(user_id, page);
	}
	
	public User get_User(String user_id) {
		// TODO Auto-generated method stub
		UserDAO ud = new UserDAO();
		return ud.findById(user_id);
	}

	public List get_FavorSingerByPage(String usr_id, Page page) {
		Set temp = ud.findById(usr_id).getFavors();
		page.set_allcount(temp.size());
		page.set_pagecount();
		
		UserDAO ud = new UserDAO();
		List<Favor> favors = new ArrayList<Favor>();
		List singers = new ArrayList();
		if(temp == null)
			return singers;
		favors.addAll(temp);
		
		for(int i=(page.get_pagenow()-1)*page.get_pagesize(); i<page.get_pagesize(); i++)
		{
			if(i >= favors.size())
				break;
			singers.add(favors.get(i).getSinger());
		}
		return singers;
	}

	public List get_Assess(int song_id, Page page) {
		// TODO Auto-generated method stub
		AssessDAO ad = new AssessDAO();
		page.set_allcount(ad.getTotalRows(song_id));
		page.set_pagecount();
		return ad.findByPage(song_id, page);
	}
	
	
}
