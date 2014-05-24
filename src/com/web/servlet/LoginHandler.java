package com.web.servlet;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.data.vo.User;
import com.data.vo.HibernateSessionFactory;
import com.data.vo.UserDAO;
import com.process.logic.DJ;
import com.process.model.CurrentList;

public class LoginHandler {

	public void login(HttpServletRequest request, HttpServletResponse response, PrintWriter out, DJ factory, CurrentList list){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username + " " + password);
		UserDAO identify = new UserDAO();
		// 用邮箱登录，验证
		List temp = identify.findByMail(username);

		if (temp.size() > 0
				&& ((User) temp.get(0)).getKeyword().equals(password)) {
			
			System.out.println("验证成功");
			//初始化session和currentlist
			String user_id = ((User) temp.get(0)).getUserId();
			list.set_userid(user_id);

			List usertaglist = factory.get_usertagProcess(user_id);
			//List lovesonglist = factory.GetLoveSongListProcess(user_id);
			
			//cookie
			Cookie namecookie = new Cookie("username", username);
			namecookie.setMaxAge(10000);
			response.addCookie(namecookie);
			
			// session
			System.out.println("写入session");
			HttpSession hs = request.getSession(true);
			hs.setAttribute("username", username);
			hs.setAttribute("usertag", usertaglist);
			//hs.setAttribute("lovesonglist", lovesonglist);
			//response.sendRedirect("/Jing/MainView.jsp");
			out.print("correct");
			out.flush();
			out.close();
		} else {
			System.out.println("验证错误");
			//response.sendRedirect("/Jing/Login.jsp");
			out.print("wrong");
			out.flush();
			out.close();
		}
	}
	
	public void register(HttpServletRequest request, HttpServletResponse response, PrintWriter out, CurrentList list){
		String email = request.getParameter("RegisEmail");
		String password = request.getParameter("RegisPassword");
		System.out.println("注册邮箱 ；"+email+" 注册密码 ："+password);
		UserDAO identify = new UserDAO();
		//用邮箱登录，验证
		List temp = identify.findByMail(email);
		if(temp.size()>0)
		{
			System.out.println("邮箱被占用");
			out.print("email occupied");
			out.flush();
			out.close();
		}
		else
		{
			System.out.println("注册成功");
			int new_id = identify.findAll().size()+1;
			String newuser_id = "u"+new_id;
			//创建一个新用户
			Session session = HibernateSessionFactory.getSession();
			Transaction tst = session.beginTransaction();
			User newuser = new User(newuser_id, password, email);
			identify.save(newuser);
			tst.commit();
			session.close();
			//初始化list的id
			list.set_userid(newuser_id);
			
			//cookie
			Cookie namecookie = new Cookie("username", email);
			namecookie.setMaxAge(10000);
			response.addCookie(namecookie);
			
			//session相关操作
			System.out.println("写入session");
			HttpSession hs = request.getSession(true);
    		hs.setAttribute("username",email);
			out.print("success");
			out.flush();
			out.close();
		}
	}
}
