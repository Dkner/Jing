package com.web.xmlfactory;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.web.xmlbean.Xml;

public class MarshallerProcessor {

	public String Transform2XMLString(Xml data){
		String xmldata = "";
		
		try {
			//ByteArrayOutputStream out = new ByteArrayOutputStream();
			final StringWriter stringWriter = new StringWriter(); 
			
			JAXBContext jaxbContext = JAXBContext.newInstance(Xml.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	 
			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);		
			jaxbMarshaller.marshal(data, stringWriter);
	 
			//xmldata = out.toString();
			xmldata = stringWriter.toString();
	      } catch (JAXBException e) {
	    	  e.printStackTrace();
	    	  xmldata = "<xml>No Result</xml>";
	      }
		
	    return xmldata;
	}
	
}
