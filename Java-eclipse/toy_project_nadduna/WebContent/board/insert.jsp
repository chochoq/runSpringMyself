<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
	
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
		<div id="divTop">
		</div>
		<form name=frm action="insert" method="post" enctype="multipart/form-data">
			
			<table id="tbl">

				<tr>
					<td colspan=2><select name="bo_list">
							<option value="관광">관광</option>
							<option value="숙소">숙소</option>
							<option value="음식점">음식점</option>
					</select></td>
					<td colspan=3><input type="text" name="bo_title" size=80
						placeholder="제목"></td>
				</tr>

				<tr>
					<td colspan=3><textarea rows="30" cols="110" name="bo_content"
							placeholder="내용을 입력해주세요"></textarea></td>
				</tr>

				<tr>
					<td colspan=2>이미지</td>
					<td colspan=2><img src="http://placehold.it/150x120"
						id="image" width=150 /> <input type="file" name="image"
						accept="image/*" style="visibility: hidden;"></td>
				</tr>

				<tr>

					<td colspan=2><input type="text" name="bo_id" size=5
						placeholder="Id"></td>
					<td colspan=2><input type="password" name="bo_pw" size=5
						placeholder="passward" max=10></td>
				</tr>

			</table>

			<div id="pagination">
				<%--가운데로 만들어주고싶은경우  pagination 사용--%>
				<input type="submit" value="저장"> 
				<input type="reset" value="취소">
			</div>
		</form>
		<!-- 여기에 내용 등록 종료--------------------------------------------->
	</div>
	 <div id="footer"><jsp:include page="../footer.jsp"/></div>	
</body>
<script>

	
	//이미지 미리보기
	$("#image").on("click", function() {
		$(frm.image).click();
	});
	
	//선택한 이미지 미리보기
	   $(frm.image).on("change", function(){
	      var reader=new FileReader();
	      reader.onload=function(e){
	         $("#image").attr("src", e.target.result);
	      }
	      reader.readAsDataURL(this.files[0]);
	   });
	
	//저장버튼 클릭시
	
	
	   $(frm).submit(function(e){
			e.preventDefault(); //이벤트실행금지<저장버튼실행금지
			var bo_id=$(frm.bo_id).val();
			var bo_pw=$(frm.bo_pw).val();
			var bo_title=$(frm.bo_title).val();
			
			//날짜를 받기위한것
		    var birthday=$(frm.yy).val() + "-" + $(frm.mm).val() + "-" + $(frm.dd).val(); 
			
			
			if(bo_id.length ==0){
				alert("작성자 ID를 입력해주세요");
				$(frm.bo_id).focus();
			}else if(bo_pw.length==0){ //db에서 15자리로줬으니까 15자리까지 가능하게만들기
				alert("글 수정,삭제를 위한 PW를 입력해주세요");
				$(frm.bo_pw).focus();
			}else if(bo_title.length==0){ //db에서 15자리로줬으니까 15자리까지 가능하게만들기
				alert("글 제목을 입력해주세요");
				$(frm.bo_title).focus();
			}else{
				if(!confirm("글을 등록하시겟습니까?")) return;
				frm.submit();
			}
		});
	
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