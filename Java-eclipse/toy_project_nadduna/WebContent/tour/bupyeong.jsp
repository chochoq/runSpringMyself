<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부평구</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<style>
   #divMenu {margin-top:10px; float:left;}
   #divCondition {width:1000px; float:left; padding: 20px 20px 20px 20px; margin:0px auto; text-align:center;}
   #divpage {overflow:hidden;}

   #pagination{ margin-left:100px; text-align:center; padding:10px 0px 10px 0px;  margin-top:150px;}
   .box img {width:300px; height:200px; border:0;}
   .tour_code{clear:both; text-align:center;}
   .tour_name{text-align:center;}
   .tour_dist{text-align:center;}
   .tour_code, .tour_name, .tour_dist, .tour_info{margin-top:5px;}
   .image{padding:20px; margin:auto; border:5px;}
   .box{width:300px; height:300px; float:left; margin-top:50px; margin-left:50px;}
   #sibal{margin-left:80px;}
   
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
         <h1 style="margin-left:80px;">부평구 관광지</h1>   
         <div id=sibal>
         <select id = "selKey"style="display:none">
            <option value = "tour_name">관광지이름</option>
            <option value = "tour_dist" selected>관광지주소</option>
         </select>
         <input type = "text" id="txtWord">
         <select id = "selPerPage"style="display:none">
            <option value = "3">3행씩 출력</option>
            <option value = "6">6행씩 출력</option>
         </select>
         <input type = "button" value = "검색" id = "btSearch" style="display:none">
         관광지 수 : <span id="count"></span>
      </div>
      <table id = "tbl"  style = "width:1300px; margin:0px auto; overflow:hidden; float:left;margin-bottom:10px;"></table>
      <script id = "temp" type = "text/x-handlebars-template">
         {{#each galist}}
         <div class = "box" name = "box">
            <div class = "image" name="image">
               <img src= "{{printImg tour_image}}">
            </div>
            <div class = "tour_code" style="display:none">{{tour_code}}</div>
            <div class = "tour_name">{{tour_name}}</div>
            <div class = "tour_dist">{{tour_dist}}</div>
            <div class = "tour_info"><button onClick="location.href='read?tour_code={{tour_code}}'">상세정보</button></div>
         </div>
         {{/each}}
      </script>
      <script>
         Handlebars.registerHelper("printImg", function(image){
            if(image){
               return "/semi/img/" + image ;
            }else{
               return "http://placehold.it/150x120";
            }
         });
         
      </script>
      <div id = "pagination">
         <button id = "btPre"> < </button>
         <span id ="curPage"></span>/<span id = "lastPage"></span>
         <button id = "btNext"> > </button>
      </div>
   </div>
</div>
      <!-- 내용종료 -->
      <div id="footer" width="200" height="100"><jsp:include page="../footer.jsp"/></div>  
</body>
<script>
var key, word, page=1, perPage=2;

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

$("#btPre").on("click", function(){
   page--;
   getList();
});

$("#btNext").on("click", function(){
   page++;
   getList();
});
//검색버튼 클릭시
$("#btSearch").on("click", function(){
   page = 1;
   getList();
});

//검색어 입력후 엔터키
$("#txtWord").keydown(function(key){
   if(key.keyCode==13){
      $("#btSearch").click();
   }
});

//출력행의 갯수를 변경했을때
$("#selPerPage").change(function(){
   $("#btSearch").click();
});

function getList(){
   key = $("#selKey").val();
   perPage = $("#selPerPage").val();
   
   $.ajax({
      type:"get",
      url:"/semi/tour/bulist.json",
      data:{"key":key, "word":word, "page":page, "perPage":perPage},
      dataType:"json",
      success:function(data){
         var template = Handlebars.compile($("#temp").html());
         $("#tbl").html(template(data));
         
         $("#curPage").html(page);
         $("#lastPage").html(data.lastPage);
         $("#count").html(data.count);
         
         if(page==1){
            $("#btPre").attr("disabled", true);
         }else{
            $("#btPre").attr("disabled", false);
         }
         
         if(page==data.lastPage){
            $("#btNext").attr("disabled", true);
         }else{
            $("#btNext").attr("disabled", false);
         }
      }
   });
}


</script>
</html>