
var onwhitchstar=0;
var starstate=new Array("0","0","0","0","0");
var score=0;
var clicked=false;
comment_score(4);

function mOut()
{	if(clicked==false)
	{
		for(var i=0; i<5; i++)
		{
			if(starstate[i]==1)
			{
				starstate[i]=0;
				//document.getElementById("star"+(i+1)).src="img/nst.gif";
			}
		}
	}
	else
	{
		for(var i=0; i<score; i++)
		{
			if(starstate[i]==0)
			{
				starstate[i]=1;
				//document.getElementById("star"+(i+1)).src="img/sth.gif";
			}
		}
		for(var i=score; i<5; i++)
		{
			if(starstate[i]==1)
			{
				starstate[i]=0;
				//document.getElementById("star"+(i+1)).src="img/nst.gif";
			}
		}
	}
}

function mOver(x)
{
	onwhitchstar=x;
	if(clicked==false || (clicked==true && onwhitchstar>score))
	{
		for(var i=0; i<onwhitchstar; i++)
		{
			if(starstate[i]==0)
			{
				starstate[i]=1;
				//document.getElementById("star"+(i+1)).src="img/sth.gif";
			}
		}
		
		for(var i=onwhitchstar; i<5; i++)
		{
			if(starstate[i]==1)
			{
				starstate[i]=0;
				//document.getElementById("star"+(i+1)).src="img/nst.gif";
			}
		}
	}
}

function on_click(x)
{
	score=x;
	for(var i=0; i<onwhitchstar; i++)
		{
			if(starstate[i]==0)
			{
				starstate[i]=1;
				//document.getElementById("star"+(i+1)).src="img/sth.gif";
			}
		}
		
		for(var i=onwhitchstar; i<5; i++)
		{
			if(starstate[i]==1)
			{
				starstate[i]=0;
				//document.getElementById("star"+(i+1)).src="img/nst.gif";
			}
		}
	clicked=true;
	document.getElementById("n_rating").value=score;
}

function comment_score(x)
{
		for(var i=0; i<x; i++)
		{
				//document.getElementById("star_"+(i+1)).src="img/sth.gif";
		}
		
		for(var i=x; i<5; i++)
		{
				//document.getElementById("star_"+(i+1)).src="img/nst.gif";
		}
}

