<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나떠나 자유게시판</title>
<link rel="stylesheet" href="../home1.css">
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
	<link href="http://fonts.googleapis.com/earlyaccess/jejugothic.css" rel="stylesheet">
	
<style>
table{
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
		<!-- 내용입력 -->
		<div id="divTop">
		</div>
		<form name="frm" action="update" method="post" enctype="multipart/form-data">
		<table id="tbl" >
				<tr>
					<td width=50>${vo.bo_list}</td>
					<td colspan=3 style= font-size: larger;>
					${vo.bo_title}
					<!-- form은 name으로 값을 가져온다!!!! -->
					<input type = "hidden" name = "bo_seq" value = "${vo.seq}">
					<input type = "hidden" name = "bo_list" value = "${vo.bo_list}">
					<input type = "hidden" name = "bo_title" value = "${vo.bo_title}">
					</td>
				</tr>
				<tr>
					<td colspan=3 style="height:500px; width:100px;"  >
					<pre style="white-space: pre-wrap;"font-family: 'Jeju Gothic'>${vo.bo_content}</pre></td>
					<input type = "hidden" name = "bo_content" value = "${vo.bo_content}">
					<input type = "hidden" name = "bo_id" value = "${vo.bo_id}">
					<input type = "hidden" name = "bo_pw" value = "${vo.bo_pw}">
				</tr>
					<!-- <td colspan=3>${vo.bo_image}</td> 
					<input type = "hidden" name = "bo_image" value = "${vo.bo_image}">-->
			
				
			</table>
			<div id="pagination">
				<%--가운데로 만들어주고싶은경우  pagination 사용--%>
				<input type="submit" value="보기" class="button"/> 
				<input type="reset" value="목록" onclick="location.href='list.jsp'" class="button"/>
			</div>
			</form>
			</div>
			 <div id="footer"><jsp:include page="../footer.jsp"/></div>	

</body>
</html>