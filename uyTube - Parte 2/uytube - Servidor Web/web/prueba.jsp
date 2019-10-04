<%-- 
    Document   : prueba
    Created on : Oct 2, 2019, 3:15:18 PM
    Author     : antus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<!-- Load JQuery -->
  <head>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <script src="js/jquery.min.js"></script>
         <script src="js/autocomplete.min.js"></script>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Prueba Jsp</title>          
      
 </head>
<body>
<!-- Load JSON file and output it-->

<script>
$(function() {
    myData = [];
   // myData.push({"id":1,"name":'Item1',"ignore":false},{"id":2,"name":'Item2',"ignore":false},{"id":3,"name":'Item3',"ignore":false})

 // User clicks the "getData" button
   $( document ).ready(function() {
          

    // Put artistList element and JSON file location into a variable
    var artistList = $("#artistList");
    var url = "http://localhost:8080/WebApplication/artists.txt";

    // Get the JSON file
    $.getJSON(url, function(data) {

      // Put artist info into a variable
       data.artists.map(function(item) {
           myData.push({"id":1,"name":item.artistname,"ignore":false});
        //return item.artistname + " (" + item.born + ")";
      });
      
      // Remove all child nodes (including text nodes) 
      artistList.empty();

      // Format artists with HTML tags 
      if (artists.length) {
        var content = "<li>" + artists.join("</li><li>") + "</li>";
        var list = $("<ul>").html(content);
        artistList.append(list);
      }
    });
  });

});

</script>
<script>
    

    $( document ).ready(function() {
        $('.demo').autocomplete({nameProperty:'name',valueField:'#hidden-field',dataSource: myData});
    });
</script>
<!-- The output appears here -->
<input type="text" class="demo">
<input type="hidden" id="hidden-field">
<button id="getData">Display Artists</button>
<div id="artistList"></div>
</body>