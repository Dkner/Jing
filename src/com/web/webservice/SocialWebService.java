package com.web.webservice;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces; 

import com.data.vo.Label;
import com.process.service.AssessImplement;
import com.sun.jersey.spi.resource.Singleton;

@Produces({"application/xml"})
@Path("social")
@Singleton
public class SocialWebService {
	
	UserVerification verifier = new UserVerification();
	AssessImplement as = new AssessImplement();
	XmlDataFactory factory = new XmlDataFactory();

@GET
public String testoutput() {
    return "<xml>SEARCH</xml>";
}

@GET
@Path("{userid}/{password}/gettag")
public String GetUsertag(@PathParam("userid") String id, @PathParam("password") String password, @PathParam("usertag") String usertag) {
	System.out.println("webservice用户获得定制标签");
	
	if(verifier.verify(id, password))
	{
		List result = as.get_UserTag(id);
		return factory.ProductXmlString_FromSourceData(Label.class.getSimpleName(), result);
	}
	else
		return factory.ProductXmlString_NoUser();
}

@GET
@Path("{userid}/{password}/addtag/{usertag}")
public String AddUsertag(@PathParam("userid") String id, @PathParam("password") String password, @PathParam("usertag") String usertag) {
	System.out.println("webservice用户添加定制标签");
	
	if(verifier.verify(id, password))
	{
		if(as.customize_usertag(id, usertag))
		{
			return factory.ProductXmlString_Success();
		}
		else
		{
			return factory.ProductXmlString_Fail();
		}
	}
	else
		return factory.ProductXmlString_NoUser();
}

@GET
@Path("{userid}/{password}/deletetag/{usertag}")
public String DeleteUsertag(@PathParam("userid") String id, @PathParam("password") String password, @PathParam("usertag") String usertag) {
	System.out.println("webservice用户删除定制标签");
	
	if(verifier.verify(id, password))
	{
		if(as.undo_usertag(id, usertag))
		{
			return factory.ProductXmlString_Success();
		}
		else
		{
			return factory.ProductXmlString_Fail();
		}
	}
	else
		return factory.ProductXmlString_NoUser();
}

@POST
@Path("{add}")
@Produces("text/plain")
public String addCustomer(@PathParam("add") String ucontent) {
    //todo  
    return "add success";
} 
}