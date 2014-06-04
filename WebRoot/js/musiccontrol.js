//
var control_XmlHttpRequest="";
var comment_XmlHttpRequest="";
var love_XmlHttpRequest="";
var hate_XmlHttpRequest="";


function get(id){
	return document.getElementById(id);
}

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
	
	function gonext(){
		$('#remindInfo').text("正在拼命搜索。。。");
		control_XmlHttpRequest=getXmlHttpObject();
		if(control_XmlHttpRequest){
			//window.alert("next1");
			var data =  "next=song";
	
			var url="/Jing/jing_servlet";
			
		
			control_XmlHttpRequest.open("post",url,true);
			control_XmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			control_XmlHttpRequest.onreadystatechange=gonext_callback;
			control_XmlHttpRequest.send(data); 
		}
	}

	//回调函数
	function gonext_callback(){		
		//window.alert("处理函数被调回"+myXmlHttpRequest.readyState);
		//我要取出从registerPro.php页面返回的数据
		if(control_XmlHttpRequest.readyState==4){
			
			if(control_XmlHttpRequest.status == 200)
			{
					
				var mes=control_XmlHttpRequest.responseText;
				var mes_obj=eval("("+mes+")");
				$('#player').attr("src", "/songlist/"+mes_obj[0].path);
				$('#songName').text( mes_obj[0].songname);
				$('#singerName').text(mes_obj[0].singername);
								
				
				if(mes_obj[0].path != "")
					$('#remindInfo').text("下一首");
				if($.trim(mes_obj[0].path) == "")
					$('#remindInfo').text("不好意思，歌放完了，搜搜其他的歌呗");
			}
		}
	}
	
	//
	function loop(){
		if(get('player').isLooped == true)
		{
			get('player').loop = "";
			get('player').isLooped = false;
			$('#remindInfo').text("已取消单曲循环");
			//alert(1);
		}
		else
		{
			get('player').loop = "loop";
			get('player').isLooped = true;
			$('#remindInfo').text("单曲循环中。。。");
			//alert(2);
		}
	}
	
	function start(){
		if(get('player').src != "")
			get('player').play();
	}
	
	function pause(){
		if(get('player').src != "")
			get('player').pause();
	}
	
	function stop(){
		//if(get('player').src != "")
		//	get('player').stop();
	}

	
	
	//comment
	function postcomment(){
		comment_XmlHttpRequest=getXmlHttpObject();
		if(comment_XmlHttpRequest){
			var data =  "level="+get("level").value+"&comment="+get("comment").value;
			var url="/Jing/jing_servlet";
			comment_XmlHttpRequest.open("post",url,true);
			comment_XmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			comment_XmlHttpRequest.onreadystatechange=chuli4;
			comment_XmlHttpRequest.send(data); 
		}
	}

	function love(){
		love_XmlHttpRequest=getXmlHttpObject();
		if(love_XmlHttpRequest){
			var data =  "love="+"song";
			var url="/Jing/jing_servlet";
			love_XmlHttpRequest.open("post",url,true);
			love_XmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			love_XmlHttpRequest.onreadystatechange=chuli4;
			love_XmlHttpRequest.send(data); 
		}
	}
	
	function chuli4(){		
		$('#remindInfo').text("已为您标记为喜欢咯");	
	}
	
	
	function hate(){
		//window.alert("hate1");
		hate_XmlHttpRequest=getXmlHttpObject();
		if(hate_XmlHttpRequest){
			var data =  "hate="+"song";
			var url="/Jing/jing_servlet";
			hate_XmlHttpRequest.open("post",url,true);
			hate_XmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			hate_XmlHttpRequest.onreadystatechange=hate_callback;
			hate_XmlHttpRequest.send(data); 
		}
	}

	
	//回调函数
	function hate_callback(){		
		if(hate_XmlHttpRequest.readyState==4){
			
			//window.alert("hate2");
			if(hate_XmlHttpRequest.status == 200)
			{
				gonext();
			}
		}
	}
	
	
	
	//安全退出
	function exit(){
		//window.alert("exit");
		window.location.href = "/Jing/jing_servlet?exit=true";			
	}
	
	
	//
	function musiccontroltest()
	{
		window.alert("musiccontroltest");
	}
	
	
	
	
	
	
	
	
	