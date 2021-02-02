<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나떠나 자유게시판</title>
<link rel="stylesheet" href="../home1.css">
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<link href="http://fonts.googleapis.com/earlyaccess/jejugothic.css"
	rel="stylesheet">
<style>
table {
	font-family: 'Jeju Gothic', sans-serif;
	font-size: medium
}
.button {
    border: none;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    cursor: pointer;
}
h2{margin:0px auto; width:400px; height:50px; text-align:center; marigin}

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
	<div id="divHearder"><jsp:include page="../main/header.jsp" /></div>
	<hr style="border:solid 1px #4A82C1; margin-top : 120px; ">
	<div id="divPage">
		<div id="divHeader3"><h2>게 시 판</h2></div>
		<!-- 내용입력 -->
		<div id="divTop"></div>
		<!-- 테이블 -->
		<table id="tbl"></table>
		<script id="temp" type="text/x-handlebars-template">
			<tr class="title">
				<td width=10>말머리</td>
				<td width=200>제목</td> 
				<td width=30>작성자</td>
				<td width=30>작성일</td>
			</tr>
			{{#each list}}
			<tr class="row">
				<td width=10 >{{bo_list}}</td>
				<td width=200 onClick="location.href='read?seq={{seq}}'"> {{bo_title}}</td>
				<td width=30>{{bo_id}}</td>
				<td width=30>{{bo_date}}</td>
			</tr>
			{{/each}}
		</script>

		<!--페이지넘기기 -->
		<div id="pagination">
			<!-- 검색수 : <span id="tot"></span> -->
			<button id="btnPre" class="button">이전</button>
			<span id="curPage"></span>
			<button id="btnNext" class="button">다음</button>
		</div>


	<div id="divSearch"style="float:left; margin-left:40px;">
      <select id="selKey">
         <option value="bo_list">말머리</option>
         <option value="bo_id">작성자</option>
         <option value="bo_title">제목</option>
      </select> 
      <input type="text" name="word" value="${word}" placeholder="검색"> 
      <input type="button" class="button" value="검색" id="btnSearch" > 
   </div>
   <input type="button" class="button" value="글쓰기" onclick="location.href='insert.jsp'" style="float:right; margin-right:40px;">
   </div>
    <div id="footer"><jsp:include page="../footer.jsp"/></div>	
	<!-- 내용입력종료 -->
</body>

<script>
	var page = 1;
	var key = "bo_id";
	var word = "";
	var perpage = 5;
	getList();
	function getList() {
		$.ajax({
			type : "get",
			url : "list.json",
			data : {
				"key" : key,
				"word" : word,
				"page" : page,
				"perPage" : perpage
			},
			dataType : "json",
			success : function(data) {
				var temp = Handlebars.compile($("#temp").html());
				$("#tbl").html(temp(data));
				$("#curPage").html(page + "/" + data.lastPage);
				$("#tot").html(data.cnt);
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


<script>
	//첫페이지 안눌리게
	if (page == 1)
		$("#btnPre").attr("disabled", true)
	else
		$("#btnPre").attr("disabled", false);
	//마지막 페이지 안눌리게
	if (data.meta.is_end)
		$("#btnNext").attr("disabled", true)
	else
		$("#btnNext").attr("disabled", false);
</script>
</html>