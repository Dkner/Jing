package tool;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.dom4j.*;
import org.dom4j.io.SAXReader;


public class Dom4j {
    /**
     * 获取指定xml文档的Document对象,xml文件必须在classpath中可以找到
     *
     * @param xmlFilePath xml文件路径
     * @return Document对象
     */
    public static Document parse2DocumentByXML(String xmlFilePath) {
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            InputStream in = Dom4j.class.getResourceAsStream(xmlFilePath);
            document = reader.read(in);
        } catch (DocumentException e) {
            System.out.println(e.getMessage());
            System.out.println("读取classpath下xmlFileName文件发生异常，请检查CLASSPATH和文件名是否存在！");
            e.printStackTrace();
        }
        return document;
    }
    
    
    public static Document parse2DocumentByString(String source){
    	Document doc = null;
    	try {
    	     doc = DocumentHelper.parseText(source);
    	} catch (DocumentException e) {
    	    e.printStackTrace();
    	}
    	return doc;
    }
    

    
    public static List ParseXMLData_FromMusicCovery(String xmlFilePath) {
    	List result = new ArrayList(); 
    	
        //产生一个解析器对象
        SAXReader reader = new SAXReader();
        //将xml文档转换为Document的对象
        //Document document = parse2DocumentByXML(xmlFilePath);
        Document document = parse2DocumentByString(xmlFilePath);
        //获取文档的根元素
        Element root = document.getRootElement();

        //遍历当前元素(在此是根元素)的子元素
        for (Iterator i_pe = root.elementIterator(); i_pe.hasNext();) {
            Element tracks = (Element) i_pe.next();
            String name = tracks.getName();
            if(!name.equals("tracks"))
            	continue;
            
            //System.out.println(name);
            //Element tracks = e_pe.element("tracks");

            //遍历当前元素e_adds(在此是adds元素)的子元素
            for (Iterator i_tracks = tracks.elementIterator(); i_tracks.hasNext();) {
                Element track = (Element) i_tracks.next();
                String title = track.element("title").getText();
                String artist = track.element("artist").element("name").getText();
                String genre = track.element("genre").getText();
                String url = track.element("sampleurl").getText();
                String coverimg = track.element("coverimg").getText();
                
                //WebSong song = new WebSong(title,artist,genre,url,coverimg);
                //result.add(song);
            }
         
        }
        
        return result;
         
    } 
    
    public static List ParseJSONData_FromDouBan(String data){
    	List result = new ArrayList();
    	
    	return result;
    }
    
    public static String ParseXMLData_FromBaiDu(String data){
    	//List result = new ArrayList();
    	
    	//产生一个解析器对象
        SAXReader reader = new SAXReader();
        //将xml文档转换为Document的对象
        //Document document = parse2DocumentByXML(xmlFilePath);
        Document document = parse2DocumentByString(data);
        //获取文档的根元素
        Element root = document.getRootElement();

        String encode = "";
        String decode = "";
        //遍历当前元素(在此是根元素)的子元素
        for (Iterator i_pe = root.elementIterator(); i_pe.hasNext();) {
            Element url = (Element) i_pe.next();
            String name = url.getName();
            if(!name.equals("url"))
            	continue;
            
            //System.out.println(name);
            //Element tracks = e_pe.element("tracks");

            //遍历当前元素e_adds(在此是adds元素)的子元素
            for (Iterator i_tracks = url.elementIterator(); i_tracks.hasNext();) {
                Element code = (Element) i_tracks.next();
                                           
                String shit = code.getName();
                if(shit.equals("encode"))
                {
                	encode = code.getText();
                }
                if(shit.equals("decode"))
                {
                	decode = code.getText();
                }              
                
            
            }
            
            break;
        }
        
        System.out.println("正："+encode);
        System.out.println("反："+decode);
        
        String temp[] = encode.split("\\/");
        temp[temp.length-1] = decode;
        String tempresult = "";
        for(int i=0; i<temp.length; i++)
        {
        	if(i!=0)
        		tempresult += "/";
        	tempresult+=temp[i];
        }
     
        System.out.println(tempresult);
        
    	
        return tempresult;
    }
    
    
    
}
