package com.web.webservice;

import java.util.List;

public class XmlDataFactory {
	
	bean2xml tool = new bean2xml();
	MarshallerProcessor marp = new MarshallerProcessor();	
	
	public Xml Create_NewXmlSourceData(){
		return new Xml();
	}
	
	public List XmlDataChangeProcessing(String XMLBEAN, List source)
	{
		if(XMLBEAN.equals("Song"))
		{
			return tool.Change_songlist(source);
		}
		if(XMLBEAN.equals("Singer"))
		{
			return tool.Change_singerlist(source);
		}
		
		return null;
	}
	
	public void XmlDataLoading(String XMLBEAN, List xmldatalist, Xml sourcedata){
		if(XMLBEAN.equals("Song"))
		{
			sourcedata.setSonglist(xmldatalist);
			return;
		}
		if(XMLBEAN.equals("Singer"))
		{
			sourcedata.setSingerlist(xmldatalist);
			return;
		}
	}
	
	public String TransformProcessing(Xml sourcedata)
	{
		return marp.Transform2XMLString(sourcedata);
	}
	
	public String ProductXmlString_FromSourceData(String XMLBEAN, List source)
	{
		Xml sourcedata = this.Create_NewXmlSourceData();
		List xmldatalist = this.XmlDataChangeProcessing(XMLBEAN, source);
		if(xmldatalist == null)
			return "<xml>No Result</xml>";
		this.XmlDataLoading(XMLBEAN, xmldatalist, sourcedata);
		return this.TransformProcessing(sourcedata);
	}
	
}
