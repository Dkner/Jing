package com.web.xmlfactory;

import java.util.List;

import com.web.xmlbean.Xml;

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
		if(XMLBEAN.equals("Label"))
		{
			return tool.Change_labellist(source);
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
		if(XMLBEAN.equals("Label"))
		{
			sourcedata.setLabellist(xmldatalist);
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
		if(xmldatalist == null || xmldatalist.size() == 0)
			return ProductXmlString_NoResult();
		this.XmlDataLoading(XMLBEAN, xmldatalist, sourcedata);
		return this.TransformProcessing(sourcedata);
	}
	
	public String ProductXmlString_NoResult()
	{
		return "<xml>No Result</xml>";
	}
	
	public String ProductXmlString_NoUser()
	{
		return "<xml>No Exisiting User</xml>";
	}
	
	public String ProductXmlString_Success()
	{
		return "<xml>Operation Success</xml>";
	}
	
	public String ProductXmlString_Fail()
	{
		return "<xml>Operation Fail</xml>";
	}
	
}
