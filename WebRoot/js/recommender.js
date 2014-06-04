//
var rec_XmlHttpRequest="";
var rec_XmlHttpRequest1="";
var rec_XmlHttpRequest2="";
var rec_XmlHttpRequest3="";
var rec_XmlHttpRequest4="";
var rec_XmlHttpRequest5="";
var rec_XmlHttpRequest6="";


//
function collectsinger(){
	//alert("1");
	$('#remindInfo').text("正在拼命搜索。。。");
	rec_XmlHttpRequest6=getXmlHttpObject();
	if(rec_XmlHttpRequest6){
		var data =  "collectsinger=song";
		var url="/Jing/jing_servlet";
		rec_XmlHttpRequest6.open("post",url,true);
		rec_XmlHttpRequest6.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		rec_XmlHttpRequest6.onreadystatechange=collectsinger_callback;
		rec_XmlHttpRequest6.send(data); 
		//alert("2");
	}
}

function collectsinger_callback(){		
	
	//alert("3");
	if(rec_XmlHttpRequest6.readyState==4){			

		if(rec_XmlHttpRequest6.status == 200)
		{
			var result = rec_XmlHttpRequest6.responseText;
			if(result == "success")
				$('#remindInfo').text("收藏艺人成功");
			else
				$('#remindInfo').text("您已经是粉丝了啦");
		}
	}
}

function searchsimilarsong(){
	//alert("1");
	$('#remindInfo').text("正在拼命搜索。。。");
	rec_XmlHttpRequest5=getXmlHttpObject();
	if(rec_XmlHttpRequest5){
		var data =  "searchsimilarsong=song";
		var url="/Jing/jing_servlet";
		rec_XmlHttpRequest5.open("post",url,true);
		rec_XmlHttpRequest5.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		rec_XmlHttpRequest5.onreadystatechange=searchsimilarsong_callback;
		rec_XmlHttpRequest5.send(data); 
		//alert("2");
	}
}

function searchsimilarsong_callback(){		
	
	//alert("3");
	if(rec_XmlHttpRequest5.readyState==4){			

		if(rec_XmlHttpRequest5.status == 200)
		{
			var mes=rec_XmlHttpRequest5.responseText;
			var mes_obj=eval("("+mes+")");
			$('#player').attr("src", "/songlist/"+mes_obj[0].path);
			$('#songName').text( mes_obj[0].songname);
			$('#singerName').text(mes_obj[0].singername);
			if(mes_obj[0].path != "")
				$('#remindInfo').text("正在播放风格相似的歌曲");
			if($.trim(mes_obj[0].path) == "")
				$('#remindInfo').text("sorry,没有找到类似的歌曲。。。");
			
			//alert("4");
		}
	}
}


function searchsimilarsinger(){
	$('#remindInfo').text("正在拼命搜索。。。");
	rec_XmlHttpRequest4=getXmlHttpObject();
	if(rec_XmlHttpRequest4){
		var data =  "searchsimilarsinger=singer";
		var url="/Jing/jing_servlet";
		rec_XmlHttpRequest4.open("post",url,true);
		rec_XmlHttpRequest4.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		rec_XmlHttpRequest4.onreadystatechange=searchsimilarsinger_callback;
		rec_XmlHttpRequest4.send(data); 
	}
}

function searchsimilarsinger_callback(){		
	
	if(rec_XmlHttpRequest4.readyState==4){			

		if(rec_XmlHttpRequest4.status == 200)
		{
			var mes=rec_XmlHttpRequest4.responseText;
			var mes_obj=eval("("+mes+")");
			$('#player').attr("src", "/songlist/"+mes_obj[0].path);
			$('#songName').text( mes_obj[0].songname);
			$('#singerName').text(mes_obj[0].singername);
			if(mes_obj[0].path != "")
				$('#remindInfo').text("正在播放相似歌手的歌曲哦");
			if($.trim(mes_obj[0].path) == "")
				$('#remindInfo').text("sorry,没有找到类似的歌手...");
			
		}
	}
}


function hongxindiantai(){
	$('#remindInfo').text("正在拼命搜索。。。");
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
			$('#player').attr("src", "/songlist/"+mes_obj[0].path);
			$('#songName').text( mes_obj[0].songname);
			$('#singerName').text(mes_obj[0].singername);
			if(mes_obj[0].path != "")
				$('#remindInfo').text("正在播放您喜欢的歌曲哦...");
			if($.trim(mes_obj[0].path) == "")
				$('#remindInfo').text("抱歉，没歌了");
			
			//result = rec_XmlHttpRequest1.responseText;
			//$('#remindInfo').text( result;
			//$('#player').src = result;
		}
	}
}


function zhinengtuijian(){
	randomPlay();
	$('#remindInfo').text("正在拼命搜索。。。");

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

		if(rec_XmlHttpRequest2.status == 200)
		{
			var mes=rec_XmlHttpRequest2.responseText;
			var mes_obj=eval("("+mes+")");
			$('#player').attr("src", "/songlist/"+mes_obj[0].path);
			$('#songName').text(mes_obj[0].songname);
			$('#singerName').text(mes_obj[0].singername);
			if(mes_obj[0].path != "")
			{
				$('#remindInfo').text("为您智能推荐标签组合："+mes_obj[0].label);
				$('#tagSearchInput').attr("value", mes_obj[0].label);	
			}
			if($.trim(mes_obj[0].path) == "")
				$('#remindInfo').text("抱歉，没歌了");
			
		}
	}
}

function lookandlisten(){
	//
	randomPlay();
	$('#remindInfo').text("正在拼命搜索。。。");
		rec_XmlHttpRequest=getXmlHttpObject();
		if(rec_XmlHttpRequest){
			var data =  "lookandlisten=song";
			var url="/Jing/jing_servlet";
			rec_XmlHttpRequest.open("post",url,true);
			rec_XmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			rec_XmlHttpRequest.onreadystatechange=lookandlisten_callback;
			rec_XmlHttpRequest.send(data); 
		}
	}


	function lookandlisten_callback(){		
		if(rec_XmlHttpRequest.readyState==4){			

			if(rec_XmlHttpRequest.status == 200)
			{
				var mes=rec_XmlHttpRequest.responseText;
				var mes_obj=eval("("+mes+")");
				$('#player').attr("src", "/songlist/"+mes_obj[0].path);
				$('#songName').text( mes_obj[0].songname);
				$('#singerName').text(mes_obj[0].singername);
				if(mes_obj[0].path != "")
				{
					$('#remindInfo').text( "为您随机推荐标签组合："+mes_obj[0].label);
					$('#tagSearchInput').attr("value", mes_obj[0].label);	
				}
				if($.trim(mes_obj[0].path) == "")
					$('#remindInfo').text( "抱歉，暂未搜到标签");

			}
		}
	}
	