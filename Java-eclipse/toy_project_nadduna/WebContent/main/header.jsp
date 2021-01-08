<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <style>
       #title {margin-top:40px; padding:10px;}
      .item a:hover{color:white; background:#A2E8ED;}
      .item a{text-decoration:none; color:#4A82C1;padding:5px;} 
      #title2 {margin:auto; margin-top:70px;}
      .item {float:left; }
      .menu {list-style-type:none;}
      .menu a {text-decoration:none;}
      body {background:white;}
      img {float:left;}
      
    </style> 
<div id="title">
	<marquee direction="right">✈&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;✈</marquee>
  	<a href="../main/index.jsp">
  	<img src="../image\dduna.png" width="300" height="200" border="0"></a>
   	<div id="title2">
      <ul class="menu">
         <li class="item" style="float:left; margin:30px;"><a href="../main/info.jsp">소개</a></li>            
         <li class="item" style="float:left; margin:30px;"><a href="../eat/recommend_a.jsp">추천 맛집</a></li>
         <li class="item" style="float:left; margin:30px;"><a href="../tour/main.jsp">관광지</a></li>
         <li class="item" style="float:left; margin:30px;"><a href="../main/mt.jsp">검색</a></li>
         <li class="item" style="float:left; margin:30px;"><a href="/semi/board/list.jsp">자추판</a></li>
      </ul>
   </div>
</div>