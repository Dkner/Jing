package com.process.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
			Assess newassess = new Assess(tempuser,tempsong,null,null,level,date);
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
		Assess newassess = new Assess(tempuser,tempsong,null,comment,null,date);
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
				assess.setLoveorhate("1");
				tst.commit();
				session.close();
				return;
			}
		} 
		System.out.println("喜欢");
		User tempuser = ud.findById(user_id);
		Song tempsong = sd.findById(song_id);
		Date date = new Date(System.currentTimeMillis()); 
		//
		Session session = HibernateSessionFactory.getSession();
		Transaction tst = session.beginTransaction();
		Assess newassess = new Assess(tempuser,tempsong,"1",null,null,date);
		ad.save(newassess);
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
				assess.setLoveorhate("-1");
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
		Assess newassess = new Assess(tempuser,tempsong,"-1",null,null,date);
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
		ud = new UserDAO();
		utd = new UsertagDAO();
		
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
	}
	
	/**
	   * function 用户添加喜好的已知标签
	   * @param String 用户id，已知标签名
	   * @return boolean 添加成功与否
	   */
	public final boolean customize_usertag(String user_id, String usertag)
	{
		//
		ld = new LabelDAO();
		ud = new UserDAO();
		utd = new UsertagDAO();
		List temp = new ArrayList();
		temp = ld.findByLabel(usertag);
		if(temp.size() == 0)
		{
			System.out.println("没有匹配的标签");
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
			Usertag newusertag = new Usertag((Label)(temp.get(0)), ud.findById(user_id));
			utd.save(newusertag);
			tst.commit();
			session.close();
			return true;
		}
	}
	
}