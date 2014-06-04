package com.web.webservice;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces; 

import com.process.model.Filter;
import com.process.model.FilterChain;
import com.process.model.LabelProcessor;
import com.process.service.SearchService;
import com.sun.jersey.spi.resource.Singleton;

@Produces("text/plain")
@Path("search")
@Singleton
public class test {
	
	SearchService searchservice = new LabelProcessor();
			

@GET
public String testoutput() {
    return "all";
}

@GET
@Path("{input}")
public String getInput(@PathParam("input") String input) {
	System.out.println("webservice 输入："+input);
	FilterChain chain = new FilterChain();
	chain.AddFilter(new Filter(4));
	chain.AddFilter(new Filter(5));
	List result = searchservice.find_songlist_by_input(0, input, chain, "");
    return result.toString();
}

@POST
@Path("{add}")
@Produces("text/plain")
public String addCustomer(@PathParam("add") String ucontent) {
    //todo  
    return "add success";
} 
}