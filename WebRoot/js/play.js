

function test()
{
 	$("#topLeftFrame").hide();
	$("#searchTagFrame").show("slow");
}

function backToListen()
{
	if($("#searchTagFrame").is(":visible"))
		{
			$("#searchTagFrame").hide();
			$("#topLeftFrame").show("slow");
			//alert("test");
		}
}

function removeTagAnimate(obj)
{
	$(obj).parent().parent().remove();
}

var judgeLove = false;
function loveMusic()
{
	if(!judgeLove)
	{
		$("#musicLove").css("background-image","url(./img/playerIconsLove1.png)");
		judgeLove = true;
	}
	else
	{
		$("#musicLove").css("background-image","url(./img/playerIconsLove0.png)");
		judgeLove = false;
	}
	
	//
	love();
}

function hateMusic()
{
	$("#musicHate").hide("50").show("50");
	//$("#musicHate").animate({background-image:url(./img/playerIconsLove0.png)});
	
	hate();
}
function changeMusic()
{
	$("#musicChange").hide("50").show("50");
	
	gonext();
}

var judgeCirPlay = false;
function cirPlayMusic()
{
	if(!judgeCirPlay)
	{
		$("#musicCirPlay").css("background-image","url(./img/playerIconsLoop.png)");
		judgeCirPlay = true;
	}
	else
	{
		$("#musicCirPlay").css("background-image","url(./img/playerIconsLoop0.png)");
		judgeCirPlay = false;
	}
	
	loop();
}
function showComment()
{
	$("#commentFrame").show().animate({"top":"-620px"},"500");
	$("#closeCommentBtn").show();
}
function hideComment()
{
	//alert("1");
	$("#commentFrame").hide("slow").css("top","50px");
	$("#closeCommentBtn").hide();
	//alert("1");
	}

function randomPlay()
{
	//window.alert(3);
	$("#flyImg").show()
		.animate({
		left:'600px'
		},"500")
		.animate({top:'50px'},"500")
		.animate({top:'-50px'},"400")
		.hide("200")
		.animate({left:'30px',top:'0px'});
	
}


function setInfo()
{
	$("#rightFrame").toggle(500);
}

var judgeMusicState = true;
function playOrStop()
{
	if(judgeMusicState)
	{
		$("#musicConBtn").css("background-image","url(./img/playCtlPause.png)");
		$("#MusicPlayer").css("animation-play-state","paused");
		
		judgeMusicState = false;
		pause();
	}
	else
	{
		$("#musicConBtn").css("background-image","url(./img/playCtlPlay.png)");
		$("#MusicPlayer").css("animation-play-state","running");
		//alert($("#audioCtl").played);
		//$("#audioCtl").play();
		judgeMusicState = true;
		start();
	}
	
}

//显示标签浮窗
function showSearchTagFrame()
{
	//
	$("#MerryChristmasFrame").hide();
	$("#topLeftFrame").hide();
	$("#searchTagFrame").show("slow");
}
//隐藏标签浮窗
function hideSearchTagFrame()
{
	//
	$("#MerryChristmasFrame").hide();
	$("#searchTagFrame").hide();
	$("#topLeftFrame").show("slow");
}
//显示圣诞节浮窗
function showMerryChristmasFrame()
{
	$("#topLeftFrame").hide();
	$("#MerryChristmasFrame").show("slow");
}
//隐藏圣诞节
function hideMerryChristmasFrame()
{
	$("#MerryChristmasFrame").hide();
	$("#topLeftFrame").show("slow");
}

function showMenu()
{
	$(this).next().toggle();
	//alert($(this).attr('id'));
	var name=$(this).attr('id');
	//alert(name=="checkLove");				i
	if(name=="checkLove")
		if($("#hateGroup").is(":visible"))
			$("#hateGroup").hide();
	if(name=="checkHate")
		if($("#loveGroup").is(":visible"))
			$("#loveGroup").hide();
					   
}