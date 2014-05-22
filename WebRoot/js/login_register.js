

var logJud = false;
var regJud = false;

function showLogFrame()
{
	if(regJud)
	{
		$("#regisDiv").hide();
		regJud = false;
	}
	//$("#loginDiv").show();
	$("#loginDiv").fadeIn("slow");
	logJud = true;
}

function showRegFrame()
{
	if(logJud)
		{
			$("#loginDiv").hide();
			logJud = false;
		}
	$("#regisDiv").fadeIn("3000");
	regJud = true;
}

