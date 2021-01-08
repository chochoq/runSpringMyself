<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관광지 메인 화면</title>
<style>
	#divMenu {margin-top:10px;}
	#divCondition {width:1500px; float:left; padding: 0px 20px 30px 40px; margin-top:30px;}
	#divPage {overflow:hidden;}
	#divMenu {float:left;}
	#divCondition {float:left; text-align:center;}
	.main1 {float:left; padding-right:30px}
	
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
</head>
<body>
	<div id = "divMainMenu"><jsp:include page ="../main/header.jsp"/></div>
	<hr style="border:solid 1px #4A82C1; margin-top : 120px; ">
	
	<div id = "divPage" >
	<div id = "divMenu"><jsp:include page ="../menu2.jsp"/></div>
		<!-- 내용시작 -->
		<div id = "divCondition">
			<h1>인천의 관광지</h1>
			<div class = "main1">
				<img src="/semi/tour/imgp026.jpg" width="270" height="270" border="0">
				<h2>경인아라뱃길 수향원</h2>
				<p>계양구</p>
			</div>
			<div class = "main1">	
				<img src = "/semi/tour/imgp031.jpg" width = "270" height="270" border="0">
				<h2>인천대공원</h2>
				<p>남동구</p>
			</div>
			<div class = "main1">	
				<img src = "/semi/tour/cunell.jpg" width = "270" height="270" border="0">
				<h2>커넬워크</h2>
				<p>연수구</p>
			</div>	
			<div class = "main1">
				<img src = "/semi/tour/gungwon.jpg" width = "270" height="270" border="0">
				<h2>경원재</h2>
				<p>연수구</p>
			</div>
			<div class = "main1">
				<img src = "/semi/tour/imgp030.jpg" width = "270" height="270" border="0">
				<h2>소래생태습지공원</h2>
				<p>남동구</p>
			</div>
			
		</div>
		
		<!-- 내용종료 -->
	</div>
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