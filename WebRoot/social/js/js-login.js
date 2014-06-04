$("a#to-reg-card").ready(function() {
	$("a#to-reg-card").click(function() {
		$("div#register-card").fadeIn(500);
	})
})

$("textarea#post-content-box").ready(function() {
		$("textarea#post-content-box").blur(function() {
		$("#post-box-mini").show();
		$("#post-box-richer").hide();
	})
})

