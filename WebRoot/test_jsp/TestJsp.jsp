<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'TestJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">

  </head>
  
  <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
  
  <script src="js/mediaplayer.js"></script>  
  
  
  <script type="text/javascript">

	var myXmlHttpRequest="";
	var myXmlHttpRequest2="";
	var myXmlHttpRequest3="";
	var myXmlHttpRequest4="";
	var myXmlHttpRequest5="";
	var myXmlHttpRequest6="";
	var myXmlHttpRequest7="";
	
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
	
	function firstmatch()
	{
		myXmlHttpRequest7=getXmlHttpObject();
		if(myXmlHttpRequest7){
			var data =  "firstmatch=song";
			var url="/Jing/jing_servlet";
			myXmlHttpRequest7.open("post",url,true);
			myXmlHttpRequest7.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			myXmlHttpRequest7.onreadystatechange=firstmatch_callback;
			myXmlHttpRequest7.send(data); 
		}
	}

	function firstmatch_callback(){		
		if(myXmlHttpRequest7.readyState==4){
			
			var res = "";
			if(myXmlHttpRequest7.status == 200)
			{
				res = myXmlHttpRequest7.responseText;
    			$('matchtag').value = res;
    			if(res == "yes")
    			{
    				var myiframe = $("label");
					myiframe.src = myiframe.src;
    			}
			}
		}
		
		
	}

	function match(){
		//window.alert("match");
		var parten = /^\s*$/ ; 
		var value = $("tag").value;
		//value = value.replace('\s+', '');
		if(!parten.test(value))
		{
			myXmlHttpRequest6=getXmlHttpObject();
			if(myXmlHttpRequest6){
				var data =  "match="+$("tag").value;
				var url="/Jing/jing_servlet";
				myXmlHttpRequest6.open("post",url,true);
				myXmlHttpRequest6.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
				myXmlHttpRequest6.onreadystatechange=match_callback;
				myXmlHttpRequest6.send(data); 
			}
		}
	}

	function match_callback(){		
		if(myXmlHttpRequest6.readyState==4){
			
			var res = "";
			if(myXmlHttpRequest6.status == 200)
			{
				//$('matchtag').value = myXmlHttpRequest6.responseText;
				//var res_obj = eval("("+res+")");
				//var res=eval("("+xmlHttpRequest6.responseText+")");
				res = myXmlHttpRequest6.responseText;
    			$('matchtag').value = res;
    			if(res == "yes")
    			{
    				var myiframe = $("label");
					myiframe.src = myiframe.src;
    			}
			}
		}
		
		
	}

	function lookandlisten(){
		myXmlHttpRequest5=getXmlHttpObject();
		if(myXmlHttpRequest5){
			var data =  "lookandlisten=song";
			var url="/Jing/jing_servlet";
			myXmlHttpRequest5.open("post",url,true);
			myXmlHttpRequest5.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			myXmlHttpRequest5.onreadystatechange=lookandlisten_callback;
			myXmlHttpRequest5.send(data); 
		}
	}

	function postcomment(){
		myXmlHttpRequest4=getXmlHttpObject();
		if(myXmlHttpRequest4){
			var data =  "userid=u1&"+"level="+$("level").value+"&comment="+$("comment").value;
			var url="/Jing/jing_servlet";
			myXmlHttpRequest4.open("post",url,true);
			myXmlHttpRequest4.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			myXmlHttpRequest4.onreadystatechange=chuli4;
			myXmlHttpRequest4.send(data); 
		}
	}

	function love(){
		myXmlHttpRequest3=getXmlHttpObject();
		if(myXmlHttpRequest3){
			var data =  "userid=u1&love="+"song";
			var url="/Jing/jing_servlet";
			myXmlHttpRequest3.open("post",url,true);
			myXmlHttpRequest3.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			myXmlHttpRequest3.onreadystatechange=chuli4;
			myXmlHttpRequest3.send(data); 
		}
	}
	
	function hate(){
		myXmlHttpRequest3=getXmlHttpObject();
		if(myXmlHttpRequest3){
			var data =  "userid=u1&hate="+"song";
			var url="/Jing/jing_servlet";
			myXmlHttpRequest3.open("post",url,true);
			myXmlHttpRequest3.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			myXmlHttpRequest3.onreadystatechange=hate_callback;
			myXmlHttpRequest3.send(data); 
		}
	}

	function lookandlisten_callback(){		
		if(myXmlHttpRequest5.readyState==4){
			
			var result = "";
			if(myXmlHttpRequest5.status == 200)
			{
				result = myXmlHttpRequest5.responseText;
				$('myres').value = result;
				//$('music').src = result;
				if($('player').src != "")
					$('player').stop();
				$('MusicPlayer').removeChild($('player'));
				var newplayer = new musicplayer(result);
			}
		}
	}

	function chuli4(){		

	}
	
	//回调函数
	function hate_callback(){		
		if(myXmlHttpRequest3.readyState==4){
			
			if(myXmlHttpRequest3.status == 200)
			{
				gonext();
			}
		}
	}
	
	function gonext(){
		myXmlHttpRequest2=getXmlHttpObject();
		if(myXmlHttpRequest2){
			//window.alert("next");
			var data =  "next=song";
			var url="/Jing/jing_servlet";
			myXmlHttpRequest2.open("post",url,true);
			myXmlHttpRequest2.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			myXmlHttpRequest2.onreadystatechange=gonext_callback;
			myXmlHttpRequest2.send(data); 
		}
	}

	//回调函数
	function gonext_callback(){		
		//window.alert("处理函数被调回"+myXmlHttpRequest.readyState);
		//我要取出从registerPro.php页面返回的数据
		if(myXmlHttpRequest2.readyState==4){
			
			if(myXmlHttpRequest2.status == 200)
			{
			//window.alert("服务器返回"+myXmlHttpRequest.responseText);
				var result = myXmlHttpRequest2.responseText;
				$('myres').value = result;
				if($('player').src != "")
					$('player').stop();
				$('MusicPlayer').removeChild($('player'));
				var newplayer = new musicplayer(result);
			}
		}
	}

	//验证用户名是否存在
	function search(search_type){
		
		myXmlHttpRequest=getXmlHttpObject();

		//window.alert(search_type+"in");
		//怎么判断创建ok
		if(myXmlHttpRequest){
			//通过myXmlHttpRequest对象发送请求到服务器的某个页面
			//第一个参数表示请求的方式, "get" / "post"
			//第二个参数指定url,对哪个页面发出ajax请求(本质仍然是http请求)
			//第三个参数表示 true表示使用异步机制,如果false表示不使用异步
			var data =  search_type+"="+$(search_type).value;
			var url="/Jing/jing_servlet";
			//打开请求.
			myXmlHttpRequest.open("post",url,true);
			myXmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			//指定回调函数.chuli是函数名
			myXmlHttpRequest.onreadystatechange=search_callback;
			//真的发送请求,如果是get请求则填入 null即可
			//如果是post请求，则填入实际的数据
			myXmlHttpRequest.send(data); 
			//window.alert("浏览器发送:"+data);
		}
	}

	//回调函数
	function search_callback(){		
		//window.alert("处理函数被调回"+myXmlHttpRequest.readyState);
		//我要取出从registerPro.php页面返回的数据
		if(myXmlHttpRequest.readyState==4){
			
			if(myXmlHttpRequest.status == 200)
			{
				var result = myXmlHttpRequest.responseText;
				$('myres').value = result;
				if($('player').src != "")
					$('player').stop();
				$('MusicPlayer').removeChild($('player'));
				var newplayer = new musicplayer(result);
			}
		}
	}
	
	function exit(){
		//window.alert("exit");
		window.location.href = "/Jing/jing_servlet?exit=true";
			
	}
	
	
	

	//这里我们写一个函数
	function $(id){
		return document.getElementById(id);
	}
	
</script>
  
  
  
  
  <body>
  <% 
	request.setCharacterEncoding("UTF-8");
  %>
  
 	<%
  		HttpSession hs = request.getSession(true);
  		if(hs.getAttribute("username") == null)
    	{   		
    		response.sendRedirect("/Jing/test_jsp/Login.jsp");
    		return;
    	}
    	
    %>
   <div>
   		<iframe name="label" src="/Jing/test_jsp/LabelJsp.jsp" ></iframe>
   </div>
   
  <div id = "MusicPlayer">
  		<embed id = "player" src= "song/周杰伦 - 以父之名.mp3" hidden="true"></embed> 
  </div>
   
  	<form action="/Jing/jing_servlet?userid=u1" method="post">
    歌名搜索:<input type="text" name="songname" id="name"><input type="button" onclick="search('name');"  value="search">
    <input style="border-width: 0;color: red" type="text" id="myres">
    <br/>
    专辑搜索:<input type="text" name="albumname" id="album"><input type="button"  onclick="search('album');" value="search">
    <br/>
    标签搜索:<input type="text" onfocus="firstmatch();" onkeyup="match();" name="tagname" id="tag"><input type="button" onclick="search('tag');" value="search">
    <input style="border-width: 0;color: red" type="text" id="matchtag">
    <br/> 
    <input type="button" onclick="gonext();"  value="next">
    <input type="button" onclick="love();"  value="love">
    <input type="button" onclick="hate();"  value="hate">
    <input type="button" onclick="lookandlisten();"  value="listen">
    <br/>
    </form>


	<form action = "#" method = "post">
	评分：<input type="text" name = "levelcontent" id ="level">
	<br/>
	评论：<input type="text" name = "commentcontent" id = "comment">
	<br/>
    <input type="button" onclick="postcomment();" value="提交评论">
    <br/>
    <input type="button" onclick="exit();" value="安全退出">
	</form>
   	
   	
   	
  </body>
</html>
