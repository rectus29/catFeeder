<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>CatFeeder</title>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.1/moment-with-locales.min.js"></script>
	<script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
	<link rel="apple-touch-icon" sizes="128x128" href="cat-hungry-icon.png">
	<link rel="icon" sizes="192x192" href="cat-hungry-icon.png">
	<style>
		body {
			background-color: #f8f9fa !important;
		}

		#frequencyEditor div.row {
			padding: 3px 0px;
		}

		.dayLabel:first-letter {
			text-transform: uppercase;
		}
	</style>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
</head>
<body>
<br>
<div class="container">
	<form id="frequencyEditor" method="post">
		<input type="hidden" value="" name="jsonData" id="jsonHidden">
		<h3 class="bold center">
			<i class="far fa-calendar-alt"></i>&nbsp;Programmation
		</h3>
		<hr/>
		<div class="container">
			<div class="form-group row">
				<select name="daySelector" id="daySelector" class="form-control">
					<optgroup label="">
						<option value="0">
							Tous les jours
						</option>
					</optgroup>
					<optgroup label="Semaine">
						<option value="1">Lundi</option>
						<option value="2">Mardi</option>
						<option value="3">Mercredi</option>
						<option value="4">Jeudi</option>
						<option value="5">Vendredi</option>
						<option value="6">Samedi</option>
						<option value="7">Dimanche</option>
					</optgroup>
				</select>
			</div>
			<div class="form-group row">
				<input type="time" id="startTime" class="form-control" placeholder="Horaire"/>
			</div>
			<div class="row">
				<a href="#" id="addSchedule" class="btn btn-warning">Ajouter</a>
			</div>
		</div>
		<hr style="clear: both;"/>
		<div class="container">
			<ul class="list-group" id="scheduleList">

			</ul>
		</div>
		<hr/>
		<div id="errors" class="error" style="display: none">
			<div class="error error-wrapper clearfix ">
				<div class="padding10 pull-left">
					<i class="fa fa-exclamation-triangle fa-2x text-danger"></i>
				</div>
				<div class="padding10 pull-left" id="errorsDisplay">

				</div>
			</div>
		</div>
		<div class="text-right">
			<button id="submit" type="submit" class="btn btn-primary">
				Sauvegarder
			</button>
			<button type="button" class="btn btn-outline-secondary" onclick="location.reload();">
				Annuler
			</button>
		</div>
		<script>
			var jsonObject = JSON.parse('<%= request.getAttribute("jsonData")%>');
			var scheduleObjectList = jsonObject.scheduledTask;

			$(function () {
				moment.locale('fr');
				refreshScheduleList();
			});

			function refreshScheduleList() {
				$('#scheduleList').empty();
				scheduleObjectList.forEach(function (element) {
					printScheduleObject(element);
				});
				//refresh the hidden
				$('#jsonHidden').val(JSON.stringify(jsonObject));
			}

			function printScheduleObject(scheduledObject) {
				var dayLabel = (scheduledObject.day == 0) ? 'Tous les jours' : moment().isoWeekday(parseInt(scheduledObject.day)).format('dddd');
				$('#scheduleList').append('<li class="list-group-item"><span class="dayLabel">' + dayLabel + '</span>&nbsp;-&nbsp;<small class="text-muted">' + scheduledObject.hour + '</small><a href="#" class="scheduleRemove" data-uid="' + scheduledObject.uid + '"><i class="far fa-times-circle float-right"></i></a></li>');
			}

			$(document).on('click', '#addSchedule', function () {
				var scheduledObject = {};
				scheduledObject.day = $('#daySelector').val();
				scheduledObject.hour = $('#startTime').val();
				scheduledObject.uid = new Date().getTime();
				if (scheduledObject.day && scheduledObject.hour) {
					scheduleObjectList.push(scheduledObject);
					$('#daySelector').val(0);
					$('#startTime').val("")
				}
				refreshScheduleList()
			});

			$(document).on('click', '.scheduleRemove', function (e) {
				e.preventDefault();
				if (confirm("Etes vous sûre ? ")) {
					var uid = $(this).data('uid');
					for (var i = 0; scheduleObjectList.length; i++) {
						if (scheduleObjectList[i].uid == uid) {
							scheduleObjectList.splice(i, 1);
							break;
						}
					}
					refreshScheduleList();
				}
			});
		</script>
	</form>
</div>


</body>
</html>
