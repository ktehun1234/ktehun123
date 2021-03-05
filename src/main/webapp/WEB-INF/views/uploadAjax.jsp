<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <script src="/resources/plugins/jquery/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<script type="text/javascript">
	$(function() {
		$(".fDrop").on("dragenter dragover",function(evt){
			evt.preventDefault(); // 이벤트 전파를 막음. (파일이 웹브라우저에서 열리는걸 막음)
		});
		
		$(".fDrop").on("drop",function(evt){
			evt.preventDefault(); 
			
			let files = evt.originalEvent.dataTransfer.files;
			let file = files[0];
			console.log(file);
			
			let formData = new FormData();
			formData.append("file",file);
			
			$ajax({
			url : '/uploadAjax',
			data : formData,
			dataType : "text",
			type : "post",
			processData : false, // 전송하는 데이터를 쿼리 스트링 형태로 변환하는지를 결정
			contentType : false, // 기본값 : application/x-www-form-urlencoded ( from 태그의 인코딩 기본값 )
			success : function(result) {
				let output = "";
				if(checkImgType(result)){
					//이미지 파일이면...
					output = "<div>"+
					'<img src = '"displayFile?fileName="+result+""'>'
				}
			},fail :  function(result) {
				alert(result);
			}
			});
		})
	});
	
	// 파일 이름을 넘겨받아 확장자가 패턴에 있느지 없는지 참/가짓 반환
	function checkImgType(fileName) {
		let imgPattern = /jpg$|gif$|png$|jpeg$/i
		return fileName.match(imgPattern);
	}
	</script>

<style>
	.fDrop{
		width: 100%;
		height: 50px
	}
	
</style>
<body>
	<div class="fDrop"></div>
	<div class="fDropList"></div>
</body>
</html>