ranking_XmlHttpRequest = "";
outer_XmlHttpRequest = "";

//创建ajax引擎
function getXmlHttpObject(){
	
	var xmlHttpRequest;
	//不同的浏览器获取对象xmlhttprequest 对象方法不一样
	if(window.ActiveXObject){
		
		xmlHttpRequest=new ActiveXObject("Microsoft.XMLHTTP");
		
	}else{

		xmlHttpRequest=new XMLHttpRequest();
	}

	return xmlHttpRequest;

}

function playMusicRankingInner(obj)
{
	var SongInfo = $(obj).parent().parent().parent().parent();
	var input = $(SongInfo).find("a");
	var songName = $(input[0]).text();
	//alert(songName);
	
	ranking_XmlHttpRequest=getXmlHttpObject();
	if(ranking_XmlHttpRequest){
		var data =  "input="+songName;
		var url="/Jing/jing_servlet";
		ranking_XmlHttpRequest.open("post",url,true);
		ranking_XmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		ranking_XmlHttpRequest.onreadystatechange=playMusicRankingInner_callback;
		ranking_XmlHttpRequest.send(data); 
	}
}

function playMusicRankingInner_callback()
{
	//获取父窗口的句柄
	var par = window.opener;
	//alert(par.document.getElementById("remindInfo").innerText);
	
	if(ranking_XmlHttpRequest.readyState==4){			

		if(ranking_XmlHttpRequest.status == 200)
		{
			var mes=ranking_XmlHttpRequest.responseText;
			var mes_obj=eval("("+mes+")");
			par.document.getElementById('player').src = "/songlist/"+mes_obj[0].path;
			par.document.getElementById('songName').innerText = mes_obj[0].songname;
			par.document.getElementById('singerName').innerText = mes_obj[0].singername;
			//$('#player').attr("src", "/songlist/"+mes_obj[0].path);
			//$('#songName').text( mes_obj[0].songname);
			//$('#singerName').text(mes_obj[0].singername);
			if(mes_obj[0].path != "")
				par.document.getElementById("remindInfo").innerText = "一键收听中。。。";
			if($.trim(mes_obj[0].path) == "")
				par.document.getElementById("remindInfo").innerText = "不好意思,歌曲放完了。。。";
			
		}
	}

}

function playMusicInner(obj)
{
	//图片内按钮直接播放，捕获歌名
	//alert($(obj).parent().html());
	var albumOrSongInfo = $(obj).parent();
	//alert($(albumOrSongInfo).html());
	var infors = $(albumOrSongInfo).find("a");
	var albumName = $(infors[0]).text();
	var songName = $(infors[1]).text();
	//alert("albumName:"+albumName+" \n"+"songName:"+songName);
	//window.location.href="play.html";
	
	
	outer_XmlHttpRequest=getXmlHttpObject();
	if(outer_XmlHttpRequest){
		var data =  "input=";
		if(songName!="")
			data +=  songName + "/";
		if(albumName!="")
			data += albumName;
		var url="/Jing/jing_servlet";
		outer_XmlHttpRequest.open("post",url,true);
		outer_XmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		outer_XmlHttpRequest.onreadystatechange=playMusicOuter_callback;
		outer_XmlHttpRequest.send(data); 
	}
} 

function playMusicOuter(type)
{
	outer_XmlHttpRequest=getXmlHttpObject();
	
	if(type == 1)//猜你喜欢
	{
		if(outer_XmlHttpRequest){
			var data =  "guesssong=song";
			var url="/Jing/jing_servlet";
			outer_XmlHttpRequest.open("post",url,true);
			outer_XmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			outer_XmlHttpRequest.onreadystatechange=playMusicOuter_callback;
			outer_XmlHttpRequest.send(data); 
		}
	}
	
	else if(type == 2)//歌手推荐
	{
		if(outer_XmlHttpRequest){
			var data =  "recommendsinger=song";
			var url="/Jing/jing_servlet";
			outer_XmlHttpRequest.open("post",url,true);
			outer_XmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			outer_XmlHttpRequest.onreadystatechange=playMusicOuter_callback;
			outer_XmlHttpRequest.send(data); 
		}
	}

	else if(type == 3)//排行榜
	{
		if(outer_XmlHttpRequest){
			var data =  "ranking=song";
			var url="/Jing/jing_servlet";
			outer_XmlHttpRequest.open("post",url,true);
			outer_XmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			outer_XmlHttpRequest.onreadystatechange=playMusicOuter_callback;
			outer_XmlHttpRequest.send(data); 
		}
	}
	
	
}

function playMusicOuter_callback(){
	
	//获取父窗口的句柄
	var par = window.opener;
	//alert(par.document.getElementById("remindInfo").innerText);
	
	if(outer_XmlHttpRequest.readyState==4){			

		if(outer_XmlHttpRequest.status == 200)
		{
			var mes=outer_XmlHttpRequest.responseText;
			var mes_obj=eval("("+mes+")");
			par.document.getElementById('player').src = "/songlist/"+mes_obj[0].path;
			par.document.getElementById('songName').innerText = mes_obj[0].songname;
			par.document.getElementById('singerName').innerText = mes_obj[0].singername;
			//$('#player').attr("src", "/songlist/"+mes_obj[0].path);
			//$('#songName').text( mes_obj[0].songname);
			//$('#singerName').text(mes_obj[0].singername);
			if(mes_obj[0].path != "")
				par.document.getElementById("remindInfo").innerText = "一键收听中。。。";
			if($.trim(mes_obj[0].path) == "")
				par.document.getElementById("remindInfo").innerText = "不好意思,歌曲放完了。。。";
			
		}
	}

}

function preAlbums(obj)
{
	getCurretAlbums(obj);
	//
}

function nextAlbums(obj)
{
	//getCurretAlbums(obj);
	//
}

function getCurretAlbums(obj)
{
	//alert("hello");
	var albums = $(obj).parent().find(".album");//五张专辑
	//alert($(albums[0]).find("a.image").html());换第一张专辑
	$(albums[0]).find("a.image").children().attr("src","123");
	var aInfors = $(albums[0]).find(".info").find("a");
	$(aInfors[0]).text("change");
	$(aInfors[1]).text("change");

}


function rangListSongCoverVis(obj)
{
	var imageCover = $(obj).find(".imageano");
	if(!$(imageCover).is(":visible")) 
		$(imageCover).show();
		
}

function rangListSongCoverHid(obj)
{
	var imageCover = $(obj).find(".imageano");
	if($(imageCover).is(":visible")) 
		$(imageCover).hide();
}

function playMusicInnerRankingList(obj)
{
	
}





