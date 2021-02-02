<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>숙소 검색</title>
	<link rel="stylesheet" href="../home1.css">
	<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
	
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9909440d372367b5fb31de01663a9cfc"></script>
	
	<style>
	h2{margin:0px auto; width:400px; height:50px; text-align:center;}
	#divPage{padding:15px;}
	#divCondition{width:880px;}
	
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
	<div id="divHeader"><jsp:include page="../main/header.jsp"/></div>
	<hr style="border:solid 1px #4A82C1; margin-top : 120px; ">
	<div id="divPage">
		<div id="divHeader3"><h2>검 색</h2></div>
		<div id="divCondition">
			<input type="text" id="query" value="인천 숙소">
			<select id="size">
				<option value=5>5개씩 보기</option>
				<option value=10>10개씩 보기</option>
				<option value=15>15개씩 보기</option>
			</select>
			<input type="button" value="검 색" id="btnSearch">
			검색수 : <span id="total"></span>
		</div>
		<table id="tbl"></table>
		<script id="temp" type="text/x-handlebars-template">
			<tr class="title">
				<td width=200>장소 이름</td>
				<td width=300>주소</td>
				<td width=200>전화번호</td>
				<td width=50>지도</td>
				<td width=200>예약</td>
			</tr>
			{{#each documents}}
			<tr class="row">
				<td>{{place_name}}</td>
				<td>{{address_name}}</td>
				<td>{{phone}}</td>
				<td><button class="map" x={{x}} y={{y}} place="{{place_name}}" phone="{{phone}}">지도</button></td>
				<td><button type="button" onclick="window.open('https://www.skyscanner.co.kr/hotels') ">예약사이트 바로이동</button></td>	
			</tr>
			{{/each}}
		</script>
		<div id="pagination">
			<button id="btnPre">이전</button>
			<span id="curPage"></span> / <span id="lastPage"></span>
			<button id="btnNext">다음</button>
		</div>
		<div id="map" style="width:900px;height:500px; margin:auto; margin-bottom:100px;"></div>
			
	</div>
<div id="footer"><jsp:include page="../footer.jsp"/></div>
</body>
<script>
	var url="https://dapi.kakao.com/v2/local/search/keyword.json";

</script>
<script src="../home.js"></script>
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