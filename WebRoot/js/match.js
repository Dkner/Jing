	var match_XmlHttpRequest="";
	var firstmatch_XmlHttpRequest="";
	
	function firstmatch()
	{
		showSearchTagFrame();
		
		firstmatch_XmlHttpRequest=getXmlHttpObject();
		if(firstmatch_XmlHttpRequest){
			var data =  "firstmatch=song";
			var url="/Jing/jing_servlet";
			firstmatch_XmlHttpRequest.open("post",url,true);
			firstmatch_XmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			firstmatch_XmlHttpRequest.onreadystatechange=firstmatch_callback;
			firstmatch_XmlHttpRequest.send(data); 
		}
	}

	function firstmatch_callback(){		
		if(firstmatch_XmlHttpRequest.readyState==4){
			
			var res = "";
			if(firstmatch_XmlHttpRequest.status == 200)
			{
				res = firstmatch_XmlHttpRequest.responseText;
    			if(res == "yes")
    			{
    				//alert("firstmatch yes");
    				$('#remindInfo').text("都是精选的标签，走一走，看一看啦");
    				var myiframe = get("searchTagFrame");
					myiframe.contentWindow.location.reload();
    			}
			}
		}
		
		
	}

	function match(){
		var value = $.trim(get("tagSearchInput").value);
//		var value = get("tagSearchInput").value;
//		var parten = /^\s*get/ ; 
//		if(!parten.test(value))

		if(value != "")
		{
			match_XmlHttpRequest=getXmlHttpObject();
			if(match_XmlHttpRequest){
				var data =  "match="+get("tagSearchInput").value;
				var url="/Jing/jing_servlet";
				match_XmlHttpRequest.open("post",url,true);
				match_XmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
				match_XmlHttpRequest.onreadystatechange=match_callback;
				match_XmlHttpRequest.send(data); 
			}
		}
	}

	function match_callback(){		
		if(match_XmlHttpRequest.readyState==4){
			
			var res = "";
			if(match_XmlHttpRequest.status == 200)
			{
				res = match_XmlHttpRequest.responseText;
				
				$('#remindInfo').text("正在为您匹配相关标签...");
    				var myiframe = get("searchTagFrame");
					myiframe.contentWindow.location.reload();
    	
			}
		}
		
	}
