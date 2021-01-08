getList();
	
	var page=1;
	
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
	
	$("#btnSearch").on("click", function(){
		page=1;
		getList();
	});
	$("#query").keydown(function(e){
		if(e.keyCode==13)
			page=1;
		getList();
	});
	$("#size").change(function(){
		page=1;
		getList();
	});
	
	$("#btnNext").on("click", function(){
		page++;
		getList();		
	});
	
	$("#btnPre").on("click", function(){
		page--;
		getList();		
	});
	
	function getList(){
		
		var query=$("#query").val();
		var size=$("#size").val();
		
		$.ajax({
			type:"get",
			url:url,
			headers:{"Authorization":"KakaoAK 9909440d372367b5fb31de01663a9cfc"},
			dataType:"json",
			data:{"query":query, "size":size, "page":page},
			success:function(data){
				
				var temp = Handlebars.compile($("#temp").html());
				$("#tbl").html(temp(data));
				
				var total=data.meta.pageable_count //검색수
				$("#total").html(total);
				
				if(page==1) $("#btnPre").attr("disabled", true)
				else $("#btnPre").attr("disabled", false);
				
				if(data.meta.is_end) $("#btnNext").attr("disabled", true)
				else $("#btnNext").attr("disabled", false);
				
				$("#curPage").html(page);
				if(total%size==0) lastPage=total/size
				else lastPage = parseInt(total/size) +1;
				$("#lastPage").html(lastPage);
				
			}
		});	
	}	