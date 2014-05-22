//音乐播放器
function musicplayer(src){
		//
		this.UI = document.createElement("audio");		
		this.UI.id="player";
		this.UI.src = src;
		this.UI.style.width = 200+"px";
		this.UI.style.height = 27+"px";
		//this.UI.hidden = true;
		this.UI.controls = "controls";
		//this.UI.loop = "loop";
		this.UI.autoplay = "autoplay";
		this.isLooped = "false";
		//
		this.parentdiv=document.getElementById("control");
		this.parentdiv.appendChild(this.UI);
		//
		this.UI.addEventListener("ended",function(){
				//alert("end");
				gonext();
		});
		
	}


function videoplayer(src){
	//
	this.UI = document.createElement("video");		
	this.UI.id="videoplayer";
	this.UI.src = src;
	this.UI.style.width = 400+"px";
	this.UI.style.height = 300+"px";
	//this.UI.hidden = true;
	this.UI.controls = "controls";
	this.isLooped = "false";
	//
	this.parentdiv=document.getElementById("videodiv");
	this.parentdiv.appendChild(this.UI);
	//
	//this.UI.addEventListener("ended",function(){
	//		gonext();
	//});
	
}