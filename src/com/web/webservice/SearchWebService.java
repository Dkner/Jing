package com.web.webservice;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces; 

import com.process.service.SearchImplement;
import com.sun.jersey.spi.resource.Singleton;
import com.web.xmlfactory.XmlDataFactory;

@Produces({"application/xml"})
@Path("search")
@Singleton
public class SearchWebService {
	
	SearchImplement ss = new SearchImplement();
	XmlDataFactory factory = new XmlDataFactory();

@GET
public String testoutput() {
    return "<xml>SEARCH</xml>";
}

@GET
@Path("{input}")
public String SearchInput(@PathParam("input") String input) {
	System.out.println("webservice 输入："+input);
	
	List result = ss.find_songlist_by_input_Basic(input);
	
	return factory.ProductXmlString_FromSourceData("Song", result);
}

@POST
@Path("{add}")
@Produces("text/plain")
public String addCustomer(@PathParam("add") String ucontent) {
    //todo  
    return "add success";
} 
}