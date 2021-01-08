<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세정보</title>
<script
   src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9909440d372367b5fb31de01663a9cfc"></script>
<style>
   #divMenu {margin-top:10px; float:left;}
   #divCondition {width:1000px; float:left; padding: 20px 20px 20px 20px; margin:0px auto; text-align:center; margin-left:120px;}
   #divpage {overflow:hidden;}

   #pagination{width:200px; margin-left:450px; text-align:center; padding:10px 0px 10px 0px;}
  .box img {float:right;}
   .tour_code{clear:both; text-align:center; margin-left:200px; margin-top:100px;}
   .tour_name{text-align:center; margin-left:200px; margin-top:100px;}
   .tour_dist{text-align:center; margin-left:200px; margin-top:100px;}
   .image{padding:20px; margin:auto; border:5px;}
   .box{width:370px; height:400px; float:left;}
   #sibal{margin-left:80px;}
   #tour_image{width:800px; height:400px;}
	.title {}
	#tot {background:#E7E7E7; width:800px; height:500px; margin-left:130px; margin-top:-400px;}
	
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
   
   <div id = "divpage" >
   <div id = "divMenu"><jsp:include page ="../menu2.jsp"/></div>
      <!-- 내용시작 -->
      <div id = "divCondition">
         <h1 style="margin-left:80px;">상세정보</h1>
         <div id="tot">
         <table id="tb" style = "margin-top:460px; margin-left:80px;">
      
         	
            	
           		 <c:if test="${vo.tour_image!=null}">
                  <img src="/semi/img/${vo.tour_image}" id="tour_image" width=150 />
                  </c:if>
         <table class="title">
	         <tr>
	            	<td width=100>관광지 이름</td>
	            	<td >${vo.tour_name}</td>
	         </tr>                 
	         <tr>
	         	<td width=100>주소</td>
	            <td>${vo.tour_address}</td>
	         </tr>
	         <tr>
	            <td  width=100>정보</td>
	            <td>"${vo.tour_info}"</td>
	         </tr>
         </table>
      </table>
      </div>
   </div>
   <table id = "tbl"></table>
      <script id = "temp" type="text/x-handlebars-template">
         <tr class = "title">
            <td>지도</td>
            <td>근처맛집</td>
         </tr>
         {{#each documents}}
         <tr class = "row">
            <td><button class = "map" x={{x}} y={{y}} place = "{{tour_name}}">지도</button></td>
            <td><button>근처맛집</button></td>
         </tr>
         {{/each}}
         </script>
         <div id="map" style="width:500px;height:400px; margin:auto;"></div>
</div>
      <!-- 내용종료 -->
      <div id="footer"><jsp:include page="../footer.jsp"/></div>  
</body>
<script>
   var url="https://dapi.kakao.com/v2/local/search/keyword.json";
         
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
<script src = "home.js"></script>
</html>