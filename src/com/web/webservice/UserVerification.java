package com.web.webservice;

import com.data.vo.User;
import com.data.vo.UserDAO;

public class UserVerification {
	
	public boolean verify(String id, String password)
	{
		UserDAO ud = new UserDAO();
		User user = ud.findById(id);
		if(user == null)
			return false;
		else if(!user.getKeyword().equals(password))
			return false;
		else
			return true;
	}
	
}
