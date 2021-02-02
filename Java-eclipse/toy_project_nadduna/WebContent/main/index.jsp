<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<style>
#weather {float:right;}
#divHeader1{width:1900px; margin-top:50px; float:left;}
#box {margin-top:50px; text-align:center; padding:50px;}
.rank{text-align:center;}
.image{text-align:center;}
#divHeader2{width:1900px; margin-top:50px; float:left; }
#btnSearch {float:right; margin-right:80px;}

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
<title>나떠나</title>
<link rel="stylesheet" href="../home.css">
<script>
	window.onload = function() {
		var count = 0;
		var image = document.getElementById('image');
		var frames = [ '../image/incheon.jpg', '../image/incheon1.png',
				'../image/incheon7.jpg', '../image/incheon12.jpg',
				'../image/incheon13.jpg'
		
		];
		setInterval(function() {
			image.src = frames[count % frames.length];
			count = count + 1;
		}, 25000 / 8);
	};
</script>
</head>
<body>
	<div id="divHeader"><jsp:include page="../main/header.jsp"/></div>
	
	<img id="image" />
	<div id="divPage">
		<div id="divHeader1">
			<span style="font-size: 25px; margin-top:80px; padding:40px; text-align:center;">[나떠나의 맛집추천]</span>
			<div id="btnSearch">
			<input type="button" value="더 보기" style="width:75; font-family:굴림; background-color:white; border:0; outline:0;"onClick="location.href='/semi/eat/recommend_a.jsp'">
			
			</div>
			<div id="best">
               <ol style="list-style:none;">
                  <li style="margin-left: 64px; padding-bottom: 30px; width:300px; float:left">
                     <div class="box-image">
                           <img src="../image/img01.jpg" width="250" height="300" border="5">
                         	
                        <div class="image" name="image">
                        </div>
                     </div>
                     <div>
                        <h2 style="clear:both; text-align:center; border:5; margin-right:20px;">바다 앞 테라스</h2>
                     </div>
                  </li>
                  <li style="margin-left: 64px; padding-bottom: 30px; width:300px; float:left">
                     <div class="box-image">
                           <img src="../image/img12.jpg" width="250" height="300" border="5">
                       	
                        <div class="image" name="image">
                        </div>
                     </div>
                     <div style="height: 93px;">
                        <h2 style="clear:both; text-align:center; border:5; margin-right:20px;">이대조 감자탕</h2>
                     </div>
                  </li>
               </ol>   
               <ol style="list-style: none;">
                  <li style="margin-left: 64px; padding-bottom: 30px; width:300px; float:left" >
                     <div class="box-image">
                           <img src="../image/img18.jpg" width="250" height="300" border="5">
                            
                        	<div class="image" name="image">
                        </div>
                     </div>
                     <div style="height: 93px;">
                        <h2 style="clear:both; text-align:center; border:5; margin-right:20px;">웨스트34</h2>
						
                     </div>
                  </li>
                  <li style="margin-left: 64px; padding-bottom: 30px; width:300px; float:left">
                     <div class="box-image">
                           <img src="../image/img23.jpg" width="250" height="300" border="5">
                           
                        	<div class="image" name="image">
                        </div>
                     </div>
                     <div style="height: 93px;">
                        <h2 style="clear:both; text-align:center; border:5; margin-right:20px;">케이크오후</h2>
                     </div>
                  </li>
                  <li style="margin-left: 64px; padding-bottom: 30px; width:300px; float:left">
                     <div class="box-image">
                           
                           <img src="../image/img19.jpg" width="250" height="300" border="5">
                           
                        	<div class="image" name="image">
                        </div>
                     </div>
                     <div style="height: 93px;">
                        <h2 style="clear:both; text-align:center; border:5; margin-right:20px;">이학갈비</h2>
                     </div>
                  </li>
               </ol>
            </div>
		</div>
		<marquee>~나떠나가 추천하는 최강 맛집, 꼭 가야하는 관광지~</marquee>
		<div id="divHeader2">
			<span style="font-size: 25px; margin-top:80px; padding:40px;">[나떠나의 관광지추천]</span>
			<div id="btnSearch"><input type="button"  value="더 보기" style="width:75; font-family:굴림; background-color:white; border:0; outline:0; "onClick="location.href='/semi/tour/main.jsp'"></div>
						<div id="best1">
               <ol style="list-style:none;">
                  <li style="margin-left: 64px; padding-bottom: 30px; width:300px; float:left">
                     <div class="box-image">
                           <img src="../image/img39.jpg" width="250" height="300" border="5">
                         
                        <div class="image" name="image">
                        </div>
                     </div>
                     <div>
                        <h2 style="clear:both; text-align:center; border:5; margin-right:20px;">송도 커넬워크</h2>
                     </div>
                  </li>
                  <li style="margin-left: 64px; padding-bottom: 30px; width:300px; float:left">
                     <div class="box-image">
                           <img src="../image/img40.jpg" width="250" height="300" border="5">
                       	
                        <div class="image" name="image">
                        </div>
                     </div>
                     <div style="height: 93px;">
                        <h2 style="clear:both; text-align:center; border:5; margin-right:20px;">연수구 경원재</h2>
                     </div>
                  </li>
               </ol>   
               <ol style="list-style: none;">
                  <li style="margin-left: 64px; padding-bottom: 30px; width:300px; float:left" >
                     <div class="box-image">
                           <img src="../image/img41.jpg" width="250" height="300" border="5">
                            
                        	<div class="image" name="image">
                        </div>
                     </div>
                     <div style="height: 93px;">
                        <h2 style="clear:both; text-align:center; border:5; margin-right:20px;">아라마루전망대</h2>
						
                     </div>
                  </li>
                  <li style="margin-left: 64px; padding-bottom: 30px; width:300px; float:left">
                     <div class="box-image">
                           <img src="../image/img42.jpg" width="250" height="300" border="5">
                           
                        	<div class="image" name="image">
                        </div>
                     </div>
                     <div style="height: 93px;">
                        <h2 style="clear:both; text-align:center; border:5; margin-right:20px;">경인아라뱃길 수향원</h2>
                     </div>
                  </li>
                  <li style="margin-left: 64px; padding-bottom: 30px; width:300px; float:left">
                     <div class="box-image">
                           <img src="../image/incheon8.jpg" width="250" height="300" border="5">
                           
                        	<div class="image" name="image">
                        </div>
                     </div>
                     <div style="height: 93px;">
                        <h2 style="clear:both; text-align:center; border:5; margin-right:20px;">월미도 테마파크</h2>
                     </div>
                  </li>
               </ol>
            </div>
            <div id="footer"><jsp:include page="../footer.jsp"/></div>	
		</div>
	</div>
		<table id="tbl"></table>
		<script id="temp" type="text/x-handlebars-template">
			
			{{#each documents}}
			<tr class="row">
				<td>{{place_name}}</td>
				<td>{{address_name}}</td>
				<td>{{phone}}</td>			
			</tr>
			{{/each}}
</script>
</body>
<script>
	var weather = [];

	getWeather();
	function getWeather() {
		$.ajax({
			type : "get",
			url : "weather.json",
			dataType : "json",
			success : function(data) {
				
				$(data).each(function() {
					weather.push(this.part + ":" + this.temper + this.wa);
				});
				var i = 0;
				var interval = setInterval(function() {
					$("#weather").html(weather[i]);
					if (i < weather.length - 1) {
						i++;
					} else {
						i = 0;
					}

				}, 1000);

			}
		});
	}

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