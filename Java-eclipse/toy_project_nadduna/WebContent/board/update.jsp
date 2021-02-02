<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나떠나 자유게시판</title>
<link rel="stylesheet" href="../home1.css">
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
	
<style>
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
		<div id="divTop"></div>
		<!-- 테이블 -->
		<form name="frm" action="update" method="post"
			enctype="multipart/form-data">
			<!-- 파일을올릴때 사용하는 enctype -->
			<table id="tbl">

				<tr>
					<input type="hidden" name="bo_seq" value="${vo.seq}">
					<td colspan=2><select name="bo_list">
							<option value="관광">관광</option>
							<option value="숙소">숙소</option>
							<option value="음식점">음식점</option>
					</select></td>
					<td colspan=3><input type="text" name="bo_title" size=80
						value="${vo.bo_title}"></td>
				</tr>

				<tr>
					<td colspan=3><textarea rows="30" cols="110" name="bo_content">${vo.bo_content}</textarea></td>

				</tr>

				<%-- <tr>
					<td colspan=2>이미지</td>
					<td colspan=2><c:if test="${vo.bo_image!=null}">
							<img src="/shop/img/${vo.bo_image}" id="image" width=150 />
						</c:if> <input type="file" name="image" accept="image/*"
						style="visibility: hidden;"></td>
					<c:if test="${vo.bo_image==null}">
						<img src="http://placehold.it/150x120">
					</c:if>
				</tr> --%>


			</table>

			<div id="pagination">
				<input type="button" value="목록" onclick="location.href='list.jsp'" />
			</div>

		 <div id="footer"><jsp:include page="../footer.jsp"/></div>	

		</form>
	</div>

	<!-- 내용입력종료 -->
</body>

<script>
	var bo_id = "${bo_id}";
	var bo_pw = "${bo_pw}";

	//삭제
	$("#btnDelete").on("click", function() {
		if (!confirm("삭제하겟습니까"))
			return;
		$.ajax({
			type : "get",
			url : "delete",
			data : {
				"bo_id" : bo_id,
				"bo_pw" : bo_pw
			},
			success : function(data) {

				if (data == 0) {
					alert("아이디가 틀렸습니다");
				} else if (data == 1) {
					alert("비밀번호가 틀립니다");
				} else {
					alert("로그인 성공 했습니다");
					location.href = "index.jsp";
				}
			}
		});
	});

	//업데이트확인 
	$(frm).submit(function(e) {
		e.preventDefault();
		if (!confirm("수정?"))
			return;
		$.ajax({
			type : "get",
			url : "update",
			data : {
				"bo_id" : bo_id,
				"bo_pw" : bo_pw
			},
			success : function(data) {

				if (data == 0) {
					alert("아이디가 없습니다");
				} else if (data == 1) {
					alert("비밀번호가 틀립니다");
				} else {
					alert("로그인 성공 했습니다");
					location.href = "index.jsp";
				}
			}
		});
		frm.submit();
	});

	//이미지를 눌렀을때 파일첨부할수있게 만듦
	$("#image").on("click", function() {
		$(frm.image).click();
	});

	//선택한 이미지 미리보기
	$(frm.image).on("change", function() {
		var reader = new FileReader();
		reader.onload = function(e) {
			$("#image").attr("src", e.target.result);
		}
		reader.readAsDataURL(this.files[0]);
	});
</script>


</html>