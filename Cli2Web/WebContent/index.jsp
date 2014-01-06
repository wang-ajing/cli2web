<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.10.3.custom.min.js"></script>
<link href="css/sunny/jquery-ui-1.10.3.custom.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HelloWorld</title>
<script>
	var onQueryCurrentStatusSuccess = function(data) {
		if (data.totalProgress) {
			$("#progressbar").progressbar("option", {
				value : data.currentProgress,
				max : data.totalProgress
			});
		}
		if (data.expectedData != null) {
			//create form
			if (data.expectedData.formatType == "TEXT") {
				$("#form")
						.html(
								+"<label for='input-data'>"
										+ data.expectedData.desc
										+ "</label>"
										+ "<input type='text' id='input-data'/><br/>"
										+ "<button class='button' onclick='submitData()'>submit</button>");
			}
		} else {
			queryCurrentStatus();
		}

	}
	function queryCurrentStatus() {
		$.get("cli/current", onQueryCurrentStatusSuccess, "json");
	};
	function submitData() {
		$.post("cli/current", {
			data : $("#input-data").val()
		}, onQueryCurrentStatusSuccess);
		$("#form").html("");
	};
	$(function() {
		$(".button").button();
		$("#progressbar").progressbar({
			value : 0
		});
		$("#start").click(function() {
			$.get("cli/start", function(data) {
				queryCurrentStatus();
			});
		});
	});
</script>
</head>
<body>
	<button class="button" id="start">Start!</button>
	<div id="progressbar"></div>
	<div id="information"></div>
	<div id="form"></div>

</body>
</html>