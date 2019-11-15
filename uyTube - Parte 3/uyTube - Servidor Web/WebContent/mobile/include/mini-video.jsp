
<%
	String embedded = "";
	float time = 0;
	
	if(session.getAttribute("embedded") != null && session.getAttribute("embedded") != "null") {
		embedded = (String) session.getAttribute("embedded");
	}
	
	if(session.getAttribute("embedded-time") != null) {
		time = (float)session.getAttribute("embedded-time");
	}
%>
<%if(!embedded.isEmpty()) {%>
<link rel="stylesheet" type="text/css" href="../css/mini-video.css">
<script src="../js/jquery.min.js"></script>
<script src="https://www.youtube.com/iframe_api"></script>

<footer>
	
	<script type="text/javascript">
		
		var embedded = '<%=embedded%>';
		var player;
		function onYouTubeIframeAPIReady() {
		  //player = document.getElementById("mini-video");
		  player = new YT.Player('mini-video', {
		    events: {
		      'onReady': onPlayerReady,
		      'onStateChange': onPlayerStateChange
		    }
		  });
		}

		function onPlayerReady() {
		  var newTime = <%=time%>;
		  player.seekTo(newTime, true);
		  //console.log(newTime+"--"+player.getCurrentTime());
		}

		function onPlayerStateChange() {
		  //console.log("cambio el estado");
		}
		
		function datosEmbedded(emb, time) {
			$.ajax({
	            url: "<%=request.getContextPath()%>/api/embeddedVideo.jsp?emb="+emb+"&time="+time,
	            success: function(){ 
	            	embedded = emb;
	            },
	            error: function() { alert("Error inesperado al modificar el reproductor");}
	        });
		}
		
		function cierroVideo() {
			datosEmbedded("","");
			embedded = "";
			location.reload();
		}
		
		$(window).on('beforeunload', function() {
			if(embedded !== "") {
				var time = player.getCurrentTime();
			    datosEmbedded(embedded, time); 
			}
		});
	</script>

	<div class="fixed-bottom">
		<div class="content-media--video">
			<h5><i id="close-mini-video" class="fas fa-times" onclick='cierroVideo();'></i></h5>
			<iframe class="content-media_object" id="mini-video" width="200" height="120" src="<%=embedded%>?enablejsapi=1&autoplay=1&rel=0&showinfo=0&controls=0" allow="autoplay" frameborder="0"></iframe>
		</div>
	</div>
</footer>
<%}%>