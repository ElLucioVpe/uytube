
<%

	String aux = (String) session.getAttribute("embedded");
	String embedded = aux + "?autoplay=1&enablejsapi=1&rel=0&showinfo=0&controls=0";
	if(aux == null || aux.equals("null")) {
		embedded = "";
	}
%>
<%if(!embedded.isEmpty()) {%>
<link rel="stylesheet" type="text/css" href="../css/mini-video.css">
<footer>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script src="https://www.youtube.com/iframe_api"></script>

	<div class="fixed-bottom">
		<div class="content-media--video">
			<h5><i id="close-mini-video" class="fas fa-times" onclick="noembedded()"></i></h5>
			<iframe class="content-media_object" id="mini-video" width="200" height="120" src="<%=embedded%>" frameborder="0"></iframe>
		    <script type="text/javascript">
		    	function noembedded() {
		    		$.ajax({
                        url: "<%=request.getContextPath()%>/api/embeddedVideo.jsp?emb=null",
                        success: function(){ 
                            location.reload();
                        },
                        error: function() { alert("Error inesperado al cerrar el reproductor");}
                    });
		    	}
		    </script>
		</div>
	</div>
</footer>
<%}%>