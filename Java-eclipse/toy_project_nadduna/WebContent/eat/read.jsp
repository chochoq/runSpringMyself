<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 등록</title>
<link rel="stylesheet" href="../home.css">
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<!-- 실제 지도를 그리는 Javascript API를 불러오기 -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9909440d372367b5fb31de01663a9cfc"></script>

<style>
   body {overflow:auto;}
   #divTitle {overflow:hidden;}
   #divMenu {margin-top:20px; float:left; position:absolute; top:280px; left:1%;}
   #divCondition { padding: 0px 20px 30px 40px; float:left; margin-left:200px;}
   
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
    margin-top:150px}
   
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
   
   h1 {text-align:center;}
   td{border-color:none;}
   #eat_image {vertical-align: middle; text-align: center; margin-top:50px;}
   #text {text-align:center;}
   #tbl {float:left;}
   #tbl1 {float:left; margin-top:100px;}
   .title {width:300px; margin-left:30px; padding:30px 30px 10px 30px;}
   .title1 {width:430px; margin-left:30px; padding:30px 30px 10px 30px;}
</style>
</head>
<body>
   <div id="divPage" style="overflow: auto;">
      <div id="divMainMenu"><jsp:include page="../main/header.jsp"></jsp:include></div>
      <div><hr style="border:solid 1px #4A82C1; margin-top : 130px;"></div>
      <div id = "divPage" >
      <div id="divMenu"><jsp:include page="../menu1.jsp"></jsp:include></div>
      <!-- 내용 입력 시작 -->
      <h1>추천 맛집!</h1>
      <div id="divCondition">
            
         <table id="tbl">
            <td style="display:none"><input type="text" name="eat_code" value="${vo.eat_code}" style="display:none" size=10 readonly></td>
            <tr>
               <c:if test="${vo.eat_image!=null}">
                  <img src="/semi/img/${vo.eat_image}" id="eat_image" width=1000px height="600px"/>
               </c:if>
            </tr>
         </table>
         <table id="tbl1">   
               <tr>
                  
                  <td style="padding:30px 30px 30px 50px; "><font id="text" size="50">${vo.eat_name}</font></td>
               </tr>
               <tr>
                  <td class="title1" name="eat_address">주소 : ${vo.eat_address}</td>
               </tr>
               <tr>
                  <td class="title" name="eat_tel">전화번호 : ${vo.eat_tel}</td>
               </tr>
   
               <tr>
                  <td class="title" name="eat_info">${vo.eat_info}</td>
               </tr>
               <tr>
                  <td class="title" name="eat_time" >운영 시간 : ${vo.eat_time}</td>
               </tr>
         </table>
         
      </div>   
      </div>
      <div><a href="#divMainMenu" id="toTop" style="display:inline;"><i class="xi-angle-up">::before</i></a></div>
   <div id="footer"><jsp:include page="../footer.jsp"/></div>
   </div>
</body>
<script>
   
   $(window).scroll(function(){
      var scrollTop = $(document).scrollTop();
      if (scrollTop < 300) {
       scrollTop = 300;
      }
      $("#divMenu").stop();
      $("#divMenu").animate( { "top" : scrollTop });
      });
      
      $("#key").change(function(){
         getList();
      })
   
      var url="https://dapi.kakao.com/v2/local/search/keyword.json";
      
   $("#tbl").on("click", ".row .map", function(){
      var x=$(this).attr("x");
      var y=$(this).attr("y");
      var place=$(this).attr("place"); 
       var phone=$(this).attr("phone");
      
      var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
      var options = { //지도를 생성할 때 필요한 기본 옵션
         center: new kakao.maps.LatLng(y, x), //지도의 중심좌표.
         level: 3 //지도의 레벨(확대, 축소 정도)
      };
      
        var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴 
        
        var position =  new kakao.maps.LatLng(y, x);// 마커를 표시할 위치입니다 

        var marker = new kakao.maps.Marker({ // 마커를 생성합니다
          position: position
        });
    
        marker.setMap(map);// 마커를 지도에 표시합니다.
      
        // 마커에 커서가 오버됐을 때 마커 위에 표시할 인포윈도우를 생성합니다
        // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        var str ="<div style='padding:5px;font-size:12px;'>";
            str += place + "<br>" + phone;
            str +="</div>";
         
        var info=new kakao.maps.InfoWindow({ content:str }); // 인포윈도우를 생성합니다
           

        kakao.maps.event.addListener(marker, "mouseover", function(){
           // 마커에 마우스오버(마크에 마우스를 올릴 때) 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
           info.open(map, marker);
         });
          // 마커에 마우스아웃 이벤트를 등록합니다
        kakao.maps.event.addListener(marker, "mouseout", function(){
             // 마커에 마우스아웃(마크에서 마우스를 내릴 때) 이벤트가 발생하면 인포윈도우를 제거합니다
           info.close(map, marker); 
        });      
   });
   
</script>
</html>