package com.webservice.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces; 
import com.sun.jersey.spi.resource.Singleton;

@Produces("text/plain")
@Path("search")
@Singleton
public class test {

@GET
public String getCustomers() {
    return "all";
}

@GET
@Path("{id}")
public String getCustomer(@PathParam("id") String uid) {
    return "yourid is "+ uid;
}

@POST
@Path("{add}")
@Produces("text/plain")
public String addCustomer(@PathParam("add") String ucontent) {
    //todo  
    return "add success";
} 
}

