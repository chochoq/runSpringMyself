<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>추천 맛집! 베스트</title>
<style>
body {overflow:auto;}
#divMenu {margin-top:10px; float:left;}
#divCondition {width:1500px; padding: 0px 20px 30px 40px; margin-top:30px;float:left; margin:0 auto;}
#footer {
   color: #808080;
    background-color: white;
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

#best {margin:0px auto; width:1230px; margin-left:180px;}
ul {list-style-type:none;}
h2 {font-size: 35px;color: #5CD1E5; text-align:center;}
h3 {font-size: 25px;color: #6799FF;}
.image img {border: 6px solid #B2EBF4; border-radius: 10px 10px 10px 10px;}
#best li {padding-right:30px; width:525px; float:left; background:#F9F9F9; border-radius: 10px 10px 10px 10px; margin-bottom:50px;}
.box {width: 300px;float: left;margin: auto;text-align: center;margin-top: 20px;}
.box:hover {color: #BDBDBD;   box-shadow: 10px 10px 10px black;}
.box-image { position: relative; width: 270px;min-height: 350px; margin-bottom: 10px; float:left}
.rank {display: block; width:250px; background: white; height: 28px;margin-bottom: 4px; border: 6px solid #B2EBF4;color: #B2EBF4;font-size: 19px;text-align: center; line-height: 28px;  border-radius: 5px 5px 5px 5px;}
.eat_name {font-size: 25px; white-space: nowrap;}
.eat_tel, .eat_dist {font-size: 15px;white-space: normal;}
.eat_dist {width:255px; white-space: normal;}
</style>
</head>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script
   src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<body>
   <div id="divPage">
      <div id="divHeader"><jsp:include page="../main/header.jsp"></jsp:include></div>
      <hr style="border:solid 1px #4A82C1; margin-top : 130px; ">
      <div id="divTitle">
         <div id="divMenu"><jsp:include page="../menu1.jsp"></jsp:include></div>
         <!-- 내용 입력 시작 -->
         <div id="divCondition">         
            <h2>'나떠나'의 추천 맛집 속으로</h2>   
            <h3>BEST 4</h3>
         <form name="frm" method="post" enctype="multipart/form-data">
            <div id="best">
               <div style="margin-bottom: 50px; padding-bottom:420px; border-bottom: 3px solid #241d1e;">
                  <ol style="list-style: none;">
                     <li>
                        <div class="box-image">
                           <div class="rank">Rank.1</div>
                           <div class="image" name="image">
                              <img src="../image/img_027.png" width="250" height="300" onClick = "location.href='http://localhost:8088/semi/eat/read?eat_code=P072/'">
                           </div>
                        </div>
                        <div style="width:270; height: 356px; float:left;">
                           <p class="eat_name">더 레드락</p>
                           <p class="eat_dist">인천 부평구 - 닭 요리</p>
                           <p class="eat_time">16:00 ~ 01:30</p>
                           <p class="eat_dist">중독성이 강한 부평의 치킨 맛집으로 수제 치킨집, 오로지 순살 조각 치킨을 사용.</p>
                        </div>
                     </li>
                     <li>
                        <div class="box-image">
                           <div class="rank">Rank.2</div>
                           <div class="image" name="image">
                              <img src="../image/img17.jpg" width="250" height="300">
                           </div>
                        </div>
                        <div style="width:280; height: 93px; float:left">
                           <p class="eat_name">디벨로핑룸</p>
                           <p class="eat_dist">인천 연수구 - 카페</p>
                           <p class="eat_time">11:00 ~ 22:00</p>
                           <p class="eat_dist">연수구 카페 중 탑급이라고 자신할 수 있는 카페</p>
                        </div>
                     </li>
                  </ol>
               </div>
               <ol style="list-style: none; margin-top:30px;">
                  <li>
                     <div class="box-image">
                        <div class="rank">Rank.3</div>
                        <div class="image" name="image">
                           <img src="../image/img10.jpg" width="250" height="300">
                        </div>
                     </div>
                     <div style="width:280; height: 93px; float:left">
                        <p class="eat_name">Territory</p>
                        <p class="eat_dist">인천 미추홀구 - 카페</p>
                        <p class="eat_time">12:00 ~ 22:00</p>
                        <p class="eat_dist">한번 오면 다시 생각날 수밖에 없는 미추홀구의 소문난 카페</p>
                     </div>
                  </li>
                  <li>
                     <div class="box-image">
                        <div class="rank">Rank.4</div>
                        <div class="image" name="image">
                           <img src="../image/img02.jpg" width="250" height="300">
                        </div>
                     </div>
                     <div style="width:280; height: 93px; float:left">
                        <p class="eat_name">신승반점</p>
                        <p class="eat_dist">인천 중구 - 중국 요리</p>
                        <p class="eat_time">11:00 ~ 21:00</p>
                        <p class="eat_dist">위생적 수저관리, 종사자 마스크 착용을 준수하는 곳으로 소재지 지자체의 인증을 받은 음식점</p>
                     </div>
                  </li>
               </ol>
            </div>
         </form>
         </div>
         <div><a href="#divMainMenu" id="toTop" style="display:inline;"><i class="xi-angle-up">::before</i></a></div>
         <div id="footer"><jsp:include page="../footer.jsp"/></div>   
      </div>
   </div>
</body>
<script>
   var key, word, page = 1, perPage = 2;
   getList();
   
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
   
   function getList() {
      key = $("#selKey").val();
      word = $(".txtWord").val();

      $.ajax({
         type : "get",
         url : "/semi/eat/list.json",
         data : {
            "key" : key,
            "word" : word,
            "page" : page,
            "perPage" : perPage
         },
         dataType : "json",
         success : function(data) {
            var template = Handlebars.compile($("#temp").html());
            $("#tbl").html(template(data));

         }
      });
   }
</script>
</html>