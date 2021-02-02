<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>추천 맛집 화면</title>
<style>
   body {overflow:auto;}
   #divTitle {overflow:hidden;}
   #divMenu {margin-top:100px; float:left; position:absolute; top:280px; left:1%;}
   #divCondition {width:1500px; padding: 0px 20px 30px 40px; margin-top:30px; float:left; margin-left:180px;}
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
   #divsmall {text-align:center; color: #5CD1E5;}
   #tblAll {margin-left:100px; margin-bottom:70px;}
   .box {width:227px; float:left; margin:auto; text-align:center; margin-top:20px; background:#E7E7E7; margin-right:30px; border-radius: 5px 5px 5px 5px;}
   .image img{width:227px; height:170px; margin:auto; margin-bottom:5px; cursor:pointer;} 
   .eat_info {overflow:hidden; text-overflow:ellipsis;}
   .eat_name {clear:both; font-size: 18px; font-weight:bold}
   .eat_info {font-weight:bold; color:#4C4C4C}
   .eat_address, .eat_info {clear:both; font-size: 14px; margin-top:5px;}
   .eat_address {padding-bottom:10px;}
</style>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
</head>
<body>
   <div id = "divMainMenu"><jsp:include page="../main/header.jsp" /></div>
   <div><hr style="border:solid 1px #4A82C1; margin-top : 130px;"></div>
   
   <div id = "divPage" >
   <div id = "divMenu"><jsp:include page="../menu1.jsp" /></div>
   
      <!-- 내용시작 -->
      <div id = "divCondition">
      <h1  id="divsmall">'나떠나'가  추천하는 인천 맛집!</h1>
         <div>
            <select id="key" style="display:none">
            <option>강화군</option>
            </select>
            <select id="key1" style="display:none">   
            <option>계양구</option>
            </select>
            <select id="key2" style="display:none">   
            <option>남동구</option>
            </select>
            <select id="key3" style="display:none">
            <option>동구</option>
            </select>
            <select id="key4" style="display:none">
            <option>미추홀</option>
            </select>
            <select id="key5" style="display:none">
            <option>서구</option>
            </select>
            <select id="key6" style="display:none">
            <option>부평구</option>
            </select>
            <select id="key7" style="display:none">
            <option>연수구</option>
            </select>
            <select id="key8" style="display:none">
            <option>웅진군</option>
            </select>
            <select id="key9" style="display:none">
            <option>중구</option>
            </select>
         </div>
         <div id="tblAll">
           <h2 id="gye">계양구</h2>
           <table id="tbl1"></table>
           <h2 id="nam">남동구</h2>
           <table id="tbl2"></table>
           <h2 id="dong">동구</h2>
           <table id="tbl3"></table>
           <h2 id="mi">미추홀구</h2>
           <table id="tbl4"></table>
           <h2 id="su">서구</h2>
           <table id="tbl5"></table>
           <h2 id="bu">부평구</h2>
           <table id="tbl6"></table>
           <h2 id="union">연수구</h2>
           <table id="tbl7"></table>
           <h2 id="jung">중구</h2>
           <table id="tbl9"></table>
           <h2 id="ung">웅진군</h2>
           <table id="tbl8"></table>
           <h2 id="gang">강화군</h2>
           <table id="tbl"></table>
        </div>
      <script id="temp" type="text/x-handlebars-template">         
         {{#each list}}
         <div class="box" name="box">
            <div class="image" name="image">
               <img src="{{printimg eat_image}}" onClick = "location.href='read?eat_code={{eat_code}}'">
            </div>
            <div class="eat_code" style="display:none">{{eat_code}}</div>
            <div class="eat_name">{{eat_name}}</div>
            <div class="eat_info">{{eat_info}}</div>
            <div>~</div>
            <div class="eat_address">주소 : {{eat_address}}</div>
         </div>
         {{/each}}
      </script>
        <script>
        Handlebars.registerHelper("printimg", function(image){
            if(image){
               return "/semi/img/" + image;
            }else {
               return "http://placehold.it/150x120";
            }
         });
        </script> 
      </div>
      <!-- 내용종료 -->
   </div>
   <div><a href="#divMainMenu" id="toTop" style="display:inline;"><i class="xi-angle-up">::before</i></a></div>
   <div id="footer"><jsp:include page="../footer.jsp"/></div>   
</body>
<script>
   var key, word, page=1, perPage=5;
   var key="eat_dist";
   getList();
   getList1();
   getList2();
   getList3();
   getList4();
   getList5();
   getList6();
   getList7();
   getList8();
   getList9();
   
   
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
   
   
   
   function getList(){
      word=$("#key").val();
   
      $.ajax({
         type:"get",
         url:"/semi/eat/list.json",
         data:{"key":key, "word":word, "page":page, "perPage":perPage},
         dataType:"json",
         success:function(data) {
            
            var template = Handlebars.compile($("#temp").html());
            $("#tbl").html(template(data));
            
         }
      });
   }
   
   function getList1(){
      word=$("#key1").val();
   
      $.ajax({
         type:"get",
         url:"/semi/eat/list.json",
         data:{"key":key, "word":word, "page":page, "perPage":perPage},
         dataType:"json",
         success:function(data) {
            
            var template = Handlebars.compile($("#temp").html());
            $("#tbl1").html(template(data));
            
         }
      });
   }
   function getList2(){
      word=$("#key2").val();
   
      $.ajax({
         type:"get",
         url:"/semi/eat/list.json",
         data:{"key":key, "word":word, "page":page, "perPage":perPage},
         dataType:"json",
         success:function(data) {
            
            var template = Handlebars.compile($("#temp").html());
            $("#tbl2").html(template(data));
            
         }
      });
   }
   function getList3(){
      word=$("#key3").val();
   
      $.ajax({
         type:"get",
         url:"/semi/eat/list.json",
         data:{"key":key, "word":word, "page":page, "perPage":perPage},
         dataType:"json",
         success:function(data) {
            
            var template = Handlebars.compile($("#temp").html());
            $("#tbl3").html(template(data));
            
         }
      });
   }
   function getList4(){
      word=$("#key4").val();
   
      $.ajax({
         type:"get",
         url:"/semi/eat/list.json",
         data:{"key":key, "word":word, "page":page, "perPage":perPage},
         dataType:"json",
         success:function(data) {
            
            var template = Handlebars.compile($("#temp").html());
            $("#tbl4").html(template(data));
            
         }
      });
   }
   function getList5(){
      word=$("#key5").val();
   
      $.ajax({
         type:"get",
         url:"/semi/eat/list.json",
         data:{"key":key, "word":word, "page":page, "perPage":perPage},
         dataType:"json",
         success:function(data) {
            
            var template = Handlebars.compile($("#temp").html());
            $("#tbl5").html(template(data));
            
         }
      });
   }
   function getList6(){
      word=$("#key6").val();
   
      $.ajax({
         type:"get",
         url:"/semi/eat/list.json",
         data:{"key":key, "word":word, "page":page, "perPage":perPage},
         dataType:"json",
         success:function(data) {
            
            var template = Handlebars.compile($("#temp").html());
            $("#tbl6").html(template(data));
            
         }
      });
   }
   function getList7(){
      word=$("#key7").val();
   
      $.ajax({
         type:"get",
         url:"/semi/eat/list.json",
         data:{"key":key, "word":word, "page":page, "perPage":perPage},
         dataType:"json",
         success:function(data) {
            
            var template = Handlebars.compile($("#temp").html());
            $("#tbl7").html(template(data));
            
         }
      });
   }
   function getList8(){
      word=$("#key8").val();
   
      $.ajax({
         type:"get",
         url:"/semi/eat/list.json",
         data:{"key":key, "word":word, "page":page, "perPage":perPage},
         dataType:"json",
         success:function(data) {
            
            var template = Handlebars.compile($("#temp").html());
            $("#tbl8").html(template(data));
            
         }
      });
   }
   function getList9(){
      word=$("#key9").val();
   
      $.ajax({
         type:"get",
         url:"/semi/eat/list.json",
         data:{"key":key, "word":word, "page":page, "perPage":perPage},
         dataType:"json",
         success:function(data) {
            
            var template = Handlebars.compile($("#temp").html());
            $("#tbl9").html(template(data));
            
         }
      });
   }
</script>
</html>