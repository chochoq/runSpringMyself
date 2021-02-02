<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>소개</title>
<style> 
*{margin:0; padding:0;} 
li{list-style: none;} 
body{margin:20px; font:20px "Arial", Gulim;} 
p{width:50px; height:50px; padding:10px; background:#c00; color:#fff; font-size:20px; 
animation:sunday 1.5s; 
-webkit-animation:sunday 2s; 
-moz-animation:sunday 1s; 
-o-animation:sunday 3s; 
animation-fill-mode:both;} 
#video1 {margin-right:600px;}
@keyframes sunday{ 
25%{background:#c0c; width:200px; height:250px;}  

100%{background:#93CC8D; width:550px; height:400px;} 
} 
body {overflow:auto;}
#footer {
    bottom:0;
    left: 0;
    width:100%;  
   color: #808080;
    font-size: 0.916em;
    padding: 10px 0;
    border-top: 1px solid #000;
    box-shadow: 0 4px 4px rgba(0,0,0,0.2) inset;
    clear:both;
    margin-top:100px}
   
   #toTop {background-color: #999;}
   #toTop{
   background: url(../image/add.png) no-repeat center center;
   background-size: 50px;
    width: 40px;
    height: 40px;
    overflow: hidden;
    bottom: 57px;
    right: 60px;
    text-indent: -10000em;
    position: fixed;
    border-radius: 3px;
    }
    .xi-angle-up:before {content:\e944;}
    .xi-angle-up {font-family: xeicon!important;
    content:\e944;
    display: inline-block;
    speak: none;
    font-style: normal;
    font-weight: 400;
    font-variant: normal;
    text-transform: none;
    line-height: 1;
    -webkit-font-smoothing: antialiased;}

</style>
	<link rel="stylesheet" href="../home.css">
</head>
<body>
	<div id="divHeader"><jsp:include page="../main/header.jsp"/></div>
	
	<video id="video1" controls="controls" width="900" height="700" class="video-js vjs-default-skin" data-setup="{}">
                      <source id="video" src="Incheon.mp4" type="video/mp4" />
                  </video>
	<img src="so.jpg" width="1200px" height="1200px" border="0" style="margin-left:200px; padding:50px;">
	<div id="footer"><jsp:include page="../footer.jsp"/></div>   
</body>
<script>
$(window).scroll(function(){
	   var scrollTop = $(document).scrollTop();
	   if (scrollTop < 180) {
	    scrollTop = 180;
	   }
	   $("#divMenu").stop();
	   $("#divMenu").animate( { "top" : scrollTop });
	   });
	   
	   $("#key").change(function(){
	      getList();
	   })
</script>
</html>