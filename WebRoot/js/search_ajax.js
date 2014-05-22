//三种搜索的ajax对象
var search_XmlHttpRequest="";
	
function search(search_type){
	
		if(get('tagSearchInput').value == "刘青" || get('tagSearchInput').value == "liuqing" || get('tagSearchInput').value == "吴珂" || get('tagSearchInput').value == "wuke"
			|| get('tagSearchInput').value == "陈安然" || get('tagSearchInput').value == "chenanran" )
		{
			surprise();
			return;
		}
	
		search_XmlHttpRequest=getXmlHttpObject();

		//window.alert(search_type+"  in");
		//怎么判断创建ok
		if(search_XmlHttpRequest){
			//通过search_XmlHttpRequest对象发送请求到服务器的某个页面
			//第一个参数表示请求的方式, "get" / "post"
			//第二个参数指定url,对哪个页面发出ajax请求(本质仍然是http请求)
			//第三个参数表示 true表示使用异步机制,如果false表示不使用异步
			
			
			
			
			
			
			
			var data =  search_type+"="+get('tagSearchInput').value;					
			var url="/Jing/jing_servlet";
			//var data =  "fct=tag&"+search_type+"="+get('tagSearchInput').value;		
			//var url="/Jing/MusicServiceServlet";
			
			
			
			
			
			
			
			
			
			
			
			//打开请求.
			search_XmlHttpRequest.open("post",url,true);
			search_XmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			//指定回调函数.chuli是函数名
			search_XmlHttpRequest.onreadystatechange=search_callback;
			//真的发送请求,如果是get请求则填入 null即可
			//如果是post请求，则填入实际的数据
			search_XmlHttpRequest.send(data); 
			//window.alert("浏览器发送:"+data);
		}
	}

	//回调函数
	function search_callback(){		
		//window.alert("处理函数被调回"+search_XmlHttpRequest.readyState);
		//我要取出从registerPro.php页面返回的数据
		if(search_XmlHttpRequest.readyState==4){
			
			if(search_XmlHttpRequest.status == 200)
			{
				//alert("shit");
				//var result = search_XmlHttpRequest.responseText;
				//get('remindInfo').text = result;

				var mes=search_XmlHttpRequest.responseText;
				//使用 eval函数讲mes字串，转成对应的对象
				var mes_obj=eval("("+mes+")");
				get('player').src = mes_obj[0].path;
				get('songName').text = mes_obj[0].songname;
				hideSearchTagFrame();
				
				
				
				alert(get('player').src);
				
				//test
				//get('MusicPlayer').style.backgroundImage.src = "url("+mes_obj[0].coverimg+")";
				
				
				
				
				
				
				
				if(mes_obj[0].path != "")
					get('remindInfo').text = "找到咯";
				if($.trim(mes_obj[0].path) == "")
					get('remindInfo').text = "抱歉，没歌了";
			}
		}
	}
	
	
	function surprise(){	
		//window.alert("surprise");
		//var player = new videoplayer("song/周杰伦 - 以父之名.ogg");
		hideSearchTagFrame();
		showMerryChristmasFrame();
		get("MerryChristmasFrame").contentWindow.location.reload();
	}
	