$(".post-box-mini").ready(function() {
	$(".post-box-mini").focus(function() {
		$(this).hide();
		$(this).next().show();
		$(this).next().find(".post-content-box").focus();
	})
})

$(".post-box-richer").ready(function() {
	var isBtnClicked = true;
	var $contentBox = $(".post-box-richer").find(".post-content-box");
	$contentBox.blur(function() {
		if (isBtnClicked && $contentBox.val() == "") {
			var postBoxRicher = $(this).parent();
			postBoxRicher.prev().show();
			postBoxRicher.hide();
		}
		else {
			isBtnClicked = true;
		}
	});
	
	$(this).find(".btn-upload").mousedown(function(){
		isBtnClicked = false;
	});

	$(this).find(".btn-send")
	.mousedown(function() {
		isBtnClicked = false;
	})
	.click(function(){
		$(this).parent().find(".post-counter").text("Sending...").css("color", "blue").css("textalign", "right");
	});

	$contentBox.delegate($contentBox, "keyup keydown", function(){
		var charCnt = $(this).val().length;
		var $postBoxCounter = $(this).parent().find(".post-counter");
		var $btnSend = $(this).parent().find(".btn-send");
		var $atList = $(this).parent().find(".at-dropdown");

		$postBoxCounter.html(140 - charCnt);
		if (charCnt == 0) {
			$btnSend.addClass("disabled");
			$btnSend.attr("disabled", true);
		}
		else if (charCnt> 0 && charCnt < 140) {
			$postBoxCounter.css("color", "#999");
			$btnSend.attr("disabled", false);
			$btnSend.removeClass("disabled");
		}
		else {
			$postBoxCounter.css("color", "red");
			$btnSend.addClass("disabled");
			$btnSend.attr("disabled", true);
		}

		// deal with @
		if ($contentBox.val().charAt(charCnt - 1) == '@') {
			$atList.addClass("open");
		}
		$contentBox.blur(function() {
			$atList.removeClass("open");
		})
	});
})

$(".at-dropdown").ready(function() {
	$(".at-dropdown").find("li a").click(function() {
		var userName = "@" + $(this).html();
		var $parent = $(this).closest('.at-dropdown').parent();
		var $contentBox = $parent.find(".post-content-box");
		if ($contentBox.html() == null) $contentBox = $parent.find(".reply-content-box");
		var wholeText = $contentBox.val().substr(0, $contentBox.val().lastIndexOf('@')) + userName;
		$contentBox.val(wholeText);
		$(this).closest('.at-dropdown').removeClass("open");
	})
})

$(".input-upload").ready(function() {
	$(".input-upload").change(function() {
		var fileName = $(this).val().replace(/.*\\/g,"");
		$(".upload-preview").find("p").html(fileName);
		$(".upload-preview").show();
		$(".input-upload").attr("disabled", true);
		$(".btn-upload").addClass("disabled");
	})

	$(".btn-del").click(function() {
		$(".input-upload").val("");
		$(".btn-upload").removeClass("disabled");
		$(".input-upload").attr("disabled", false);
		$(".upload-preview").hide();
	})
})
