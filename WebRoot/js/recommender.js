//
var rec_XmlHttpRequest="";
var rec_XmlHttpRequest1="";
var rec_XmlHttpRequest2="";


function hongxindiantai(){
	
	//window.alert("look");
	rec_XmlHttpRequest1=getXmlHttpObject();
	if(rec_XmlHttpRequest1){
		var data =  "hongxindiantai=song";
		var url="/Jing/jing_servlet";
		rec_XmlHttpRequest1.open("post",url,true);
		rec_XmlHttpRequest1.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		rec_XmlHttpRequest1.onreadystatechange=hongxindiantai_callback;
		rec_XmlHttpRequest1.send(data); 
	}
}


function hongxindiantai_callback(){		
	
	if(rec_XmlHttpRequest1.readyState==4){			
		//window.alert("listen");
		var result = "";
		if(rec_XmlHttpRequest1.status == 200)
		{
			var mes=rec_XmlHttpRequest1.responseText;
			var mes_obj=eval("("+mes+")");
			get('player').src = mes_obj[0].path;
			get('songName').text = mes_obj[0].songname;
			if(mes_obj[0].path != "")
				get('remindInfo').text = "正在播放您喜欢的歌曲哦...";
			if($.trim(mes_obj[0].path) == "")
				get('remindInfo').text = "抱歉，没歌了";
			
			//result = rec_XmlHttpRequest1.responseText;
			//get('remindInfo').text = result;
			//get('player').src = result;
		}
	}
}


function zhinengtuijian(){
	//
	randomPlay();
	//
	//window.alert("look");
	rec_XmlHttpRequest2=getXmlHttpObject();
	if(rec_XmlHttpRequest2){
		var data =  "zhinengtuijian=song";
		var url="/Jing/jing_servlet";
		rec_XmlHttpRequest2.open("post",url,true);
		rec_XmlHttpRequest2.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		rec_XmlHttpRequest2.onreadystatechange=zhinengtuijian_callback;
		rec_XmlHttpRequest2.send(data); 
	}
}


function zhinengtuijian_callback(){		
	if(rec_XmlHttpRequest2.readyState==4){			
		//window.alert("listen");
		var result = "";
		if(rec_XmlHttpRequest2.status == 200)
		{
			var mes=rec_XmlHttpRequest2.responseText;
			var mes_obj=eval("("+mes+")");
			get('player').src = mes_obj[0].path;
			get('songName').text = mes_obj[0].songname;
			if(mes_obj[0].path != "")
			{
				get('remindInfo').text = "为您智能推荐标签组合："+mes_obj[0].label;
				get('tagSearchInput').value = mes_obj[0].label;	
			}
			if($.trim(mes_obj[0].path) == "")
				get('remindInfo').text = "抱歉，没歌了";
			
			
//			result = rec_XmlHttpRequest2.responseText;
//			get('remindInfo').text = result;
//			get('player').src = result;
		}
	}
}

function look(){
	//
	randomPlay();
	//
		rec_XmlHttpRequest=getXmlHttpObject();
		if(rec_XmlHttpRequest){
			var data =  "lookandlisten=song";
			var url="/Jing/jing_servlet";
			rec_XmlHttpRequest.open("post",url,true);
			rec_XmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			rec_XmlHttpRequest.onreadystatechange=look_callback;
			rec_XmlHttpRequest.send(data); 
		}
	}


	function look_callback(){		
		if(rec_XmlHttpRequest.readyState==4){			
			var result = "";
			if(rec_XmlHttpRequest.status == 200)
			{
				var mes=rec_XmlHttpRequest.responseText;
				var mes_obj=eval("("+mes+")");
				//alert(mes_obj);
				get('player').src = mes_obj[0].path;
				get('songName').text = mes_obj[0].songname;
				if(mes_obj[0].path != "")
				{
					get('remindInfo').text = "为您随机推荐标签组合："+mes_obj[0].label;
					get('tagSearchInput').value = mes_obj[0].label;	
				}
				if($.trim(mes_obj[0].path) == "")
					get('remindInfo').text = "抱歉，暂未搜到标签";
				
				
//				result = rec_XmlHttpRequest.responseText;
//				get('remindInfo').text = result;
//				get('player').src = result;
//				if(get('player').src != "")				
//				{
//					get('player').pause();
//					get('player').src = result;
//				}
//				get('MusicPlayer').removeChild(get('player'));
//				var newplayer = new musicplayer(result);
			}
		}
	}
	