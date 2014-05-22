package tool;

import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

public class JSON2JavaBean {
	
	public static void j2b()
	{
		//JSONArray jsonArray = JSONArray.fromObject(request.getParameter("json"));
		JsonConfig jsonconfig = new JsonConfig();
		jsonconfig.setArrayMode( JsonConfig.MODE_OBJECT_ARRAY );
		//jsonconfig.setRootClass(User.class);
	 
		//User[] users= (User[]) JSONSerializer.toJava( jsonArray, jsonconfig );
	}
}
