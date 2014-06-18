var updateXmlHttpRequest = "";
var deleteXmlHttpRequest = "";

function update_info()
{
	//alert("info");
	infoXmlHttpRequest=getXmlHttpObject();
	var sex = "male";
	if(get('optionsRadios2').checked = true)
		sex = "female";
		
	if(infoXmlHttpRequest){
		var data =  "userinfo=user&username="+get('inputNickName').value+"&sex="+sex+"&signature="+get('inputSentence').value;
		var url="/Jing/jing_servlet";
		infoXmlHttpRequest.open("post",url,true);
		infoXmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		infoXmlHttpRequest.onreadystatechange=updateinfo_callback;
		infoXmlHttpRequest.send(data); 
	}
}
		
function updateinfo_callback()
{
	if(infoXmlHttpRequest.readyState==4){
		if(infoXmlHttpRequest.status == 200)
		{
			var result = infoXmlHttpRequest.responseText;			
			get('remindInfo').text = result;
		}
	}
}
		
function update_key()
{
	keyXmlHttpRequest=getXmlHttpObject();
	if(keyXmlHttpRequest){
		var data =  "oldkeyword="+get('oldPwd').value+"&newkeyword="+get('newPwd').value;
		var url="/Jing/jing_servlet";
		keyXmlHttpRequest.open("post",url,true);
		keyXmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		keyXmlHttpRequest.onreadystatechange=updatekey_callback;
		keyXmlHttpRequest.send(data); 
	}
}

function updatekey_callback()
{
	if(keyXmlHttpRequest.readyState==4){
		if(keyXmlHttpRequest.status == 200)
		{
			var result = keyXmlHttpRequest.responseText;			
			get('remindInfo').text = result;
		}
	}
}


function update_tag()
{
	//window.alert("usertag");
	updateXmlHttpRequest=getXmlHttpObject();
	if(updateXmlHttpRequest){
		var data =  "newusertag="+get('inputTag').value;
		var url="/Jing/jing_servlet";
		updateXmlHttpRequest.open("post",url,true);
		updateXmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		updateXmlHttpRequest.onreadystatechange=updatetag_callback;
		updateXmlHttpRequest.send(data); 
	}
}

function updatetag_callback()
{
	if(updateXmlHttpRequest.readyState==4){
		//window.alert("insise1");
		if(updateXmlHttpRequest.status == 200)
		{
			//window.alert("服务器返回"+updateXmlHttpRequest.responseText);
			var result = updateXmlHttpRequest.responseText;			
			get('remindInfo').text = result;
			if(result == "标签订制成功")
			{				
				//var newusertag = new usertag(get('inputTag').value);
			}
			//window.alert("usertagdisplay");
			get('inputTag').value = "";
		}
	}
}


function delete_tag(obj)
{
	//var Obj = obj;
	//alert("1");
	deleteXmlHttpRequest=getXmlHttpObject();
	if(deleteXmlHttpRequest){
		//alert($(obj).prev().text());
		var data =  "deleteusertag="+$(obj).prev().text();
		var url="/Jing/jing_servlet";
		deleteXmlHttpRequest.open("post",url,true);
		deleteXmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		deleteXmlHttpRequest.onreadystatechange=deletetag_callback;
		deleteXmlHttpRequest.send(data); 
	}
	//alert("end");
	removeTagAnimate(obj);
}

function deletetag_callback()
{
	//alert("3");
	if(deleteXmlHttpRequest.readyState==4){
		if(deleteXmlHttpRequest.status == 200)
		{
			var result = deleteXmlHttpRequest.responseText;			
			get('remindInfo').text = result;
		}
	}
}
