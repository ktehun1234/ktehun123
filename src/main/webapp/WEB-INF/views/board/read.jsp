<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>AdminLTE 3 | Dashboard</title>

<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="../resources/plugins/fontawesome-free/css/all.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<!-- Tempusdominus Bootstrap 4 -->
<link rel="stylesheet"
	href="../resources/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
<!-- iCheck -->
<link rel="stylesheet"
	href="../resources/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
<!-- JQVMap -->
<link rel="stylesheet" href="../resources/plugins/jqvmap/jqvmap.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="../resources/dist/css/adminlte.min.css">
<!-- overlayScrollbars -->
<link rel="stylesheet"
	href="../resources/plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
<!-- Daterange picker -->
<link rel="stylesheet"
	href="../resources/plugins/daterangepicker/daterangepicker.css">
<!-- summernote -->
<link rel="stylesheet"
	href="../resources/plugins/summernote/summernote-bs4.min.css">

<script src="../resources/plugins/jquery/jquery.min.js"></script>

<script>
	function goModify(no) {
		
		$("#replyno").val(no);
		$("#modifyBox").show();
	}
	function godelete(no) {
		
		$("#deleteBox #replyno").val(no);
		
		$("#deleteBox").show();
	}
	function deleteBox() {
		let no = $("#deleteBox #replyno").val();
		$("#deleteBox").hide();
		$.ajax({
			method : "delete",
			url : "/replies/" + no,
			headers : { // 요청 하는 데이터의 헤더에 전송
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "DELETE"
			},
			dataType : "text", // 응답 받는 데이터 타입
			data : JSON.stringify({ // 요청하는 데이터
				no : no
			}),
			success : function(result) {
				if (result == 'Success') {
					//alert('댓글 등록 완료!');
					
					callReplyList(); // 댓글 다시 호출
					
				}
				
			
			}
		});
		
	}
	function callReplyList() {

		let bno = ${board.no};
		$("#replyBox").empty();
		$.getJSON("/replies/all/" + bno, function(data) {
			let output = "<ul class='list-group'>";
			console.log(data);
			$(data).each(
					function(index, item) {
						output += "<li class='list-group-item'><span>댓글번호 : "
								+ item.no + "</span>"
						output += "<div>" + item.replytext + "</div>"
						output += "<div>작성자 : " + item.replayer + "</div>"
						output += "<div>작성날짜 : "
								+ new Date(item.upadtedate).toLocaleString()
								+ "</div>"
						output += "</li>"
						output += '<li class="list-group-item"><span id="' + item.no 
						+ '" onclick="goModify(' + item.no + ')"><img src="../resources/img/modify.png" width="15px" /></span>';
						output += "<span  onclick='godelete(" + item.no + ");'><img src='../resources/img/delete.png' width='15px' /></span></li>";
						
					})
			output += "</ul>";

			$("#replyBox").html(output);
		});

	}

	function showReplyBox() {
		$("#inputReplyBox").show();
	}
	
	function addReply() {
		let replyer = $("#newReplyWriter").val();
		let replytext = $("#newReplyText").val();
		let bno = ${param.no};
		$.ajax({
			method : "post",
			url : "/replies",
			headers : { // 요청 하는 데이터의 헤더에 전송
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "POST"
			},
			dataType : "text", // 응답 받는 데이터 타입
			data : JSON.stringify({ // 요청하는 데이터
				bno : bno,
				replayer : replyer,
				replytext : replytext
			}),
			success : function(result) {
				if (result == 'Success') {
					//alert('댓글 등록 완료!');
					callReplyList(); // 댓글 다시 호출
				}
				
			
			}
		});
		
	}

	$(function() {
		callReplyList();
	});
</script>
<style type="text/css">
	#modifyBox{
		width: 400px;
		height: 150px;
		background-color: lightgray;
		position: fixed;
		top: 50%;
		left: 50%;
		margin-top: -50px;
		margin-left: -150px;
		padding : 15px;
		display: none;
		z-index: 999;
	}
	#deleteBox{
		width: 400px;
		height: 150px;
		background-color: lightgray;
		position: fixed;
		top: 50%;
		left: 50%;
		margin-top: -50px;
		margin-left: -150px;
		padding : 15px;
		display: none;
		z-index: 999;
		}
</style>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
	<%@include file="../templateHeader.jsp"%>
	<%@include file="../templateAside.jsp"%>


	<c:choose>
		<c:when test='${board.isdelete == "N"}'>
			<div class="container">
				<h1>게시판 글 등록</h1>
				<div class="form-group">
					<label for="no">글 번호:</label><br /> ${board.no }
				</div>
				<div class="form-group">
					<label for="no">작성 날짜: </label><br />
					<fmt:formatDate value="${board.regdate }" type="both"
						pattern="yyyy-MM-dd HH:mm:ss" />
				</div>
				<div class="form-group">
					<label for="no">조회수:</label><br />${board.viewnt }
				</div>
				<div class="form-group">
					<label for="writer">작성자:</label><br />${board.writer }
				</div>
				<div class="form-group">
					<label for="title">제목:</label><br />${board.title }
				</div>
				<div class="form-group">
					<label for="content">내용:</label><br />${board.content }
				</div>
				
				<div id="modifyBox">
					<div>댓글 수정</div>
					<input type="hidden" id="replyno" />
					<div>
						<input type="text" id="replytext" />
						<button type="button" id="replyModBtn" onclick="modiProc();">수정</button>
						<button type="button" id="replyModClose" onclick="modiBoxClose();">닫기</button>
					</div>
		
				</div>
				<div id="deleteBox">
					<input type="hidden" id="replyno" />
					<div>정말로 삭제하시겠습니까?</div>
					<div>
					<button type="button" id="replyModBtn" onclick="deleteBox();">삭제</button>
					<button type="button" id="replyModClose" onclick="deleteClose();">닫기</button>
					</div>
				</div>
				<script>
					function modiProc() {
						$("#modifyBox").hide();
						let no = $("#replyno").val();
						let replytext = $('#replytext').val();
						
						$.ajax({
							method : "put",
							url : "/replies/" + no,
							headers : { // 요청 하는 데이터의 헤더에 전송
								"Content-Type" : "application/json",
								"X-HTTP-Method-Override" : "PUT"
							},
							dataType : "text", // 응답 받는 데이터 타입
							data : JSON.stringify({ // 요청하는 데이터
								no : no,
								replytext : replytext
							}),
							success : function(result) {
								if (result == 'Success') {
									//alert('댓글 등록 완료!');
									callReplyList(); // 댓글 다시 호출
								}
								
							
							}
						});
					}
					
					function modiBoxClose() {
						$("#modifyBox").hide();
					}
				function deleteClose() {
					$("#deleteBox").hide();
				}
				</script>
				
				<div>
				<c:if test="${loginMember.uid == board.writer }">
					<button type="button" class="btn btn-success" id="rewriteBoard"
						onclick="location.href='/board/modi?no=${board.no}'">수정하기</button>
					<button type="button" class="btn btn-info" id="deleteBoard"
						onclick="location.href='/board/remove?no=${board.no}'">삭제하기</button>
				</c:if>
				
					<button type="button" class="btn btn-primary"
						onclick="location.href='/board/listCri?page=${param.page}'">리스트페이지로</button>
	<c:if test="${loginMember != null }">
					<button type="button" class="btn btn-primary"
						onclick="showReplyBox();">댓글달기</button></c:if>
				</div>
				<div id="inputReplyBox"
					style="padding: 15px; border: 1px dotted gray; display: none;">
					
						작성자 : <input type="text" name="replyer" id="newReplyWriter" "/> 
						댓글 입력 : <input type="text" name="replytext" id="newReplyText" />

						<button id="replyAddBtn" onclick="addReply();">ADD Reply</button>
					
				</div>
				<div>
					<div id="replyBox"
						style="padding: 10px; border-bottom: 1px solid gray;">
						
					</div>
					
				</div>
			</div>

		</c:when>

		<c:otherwise>
			<div class="container">
				<h2>존재하지 않는 글입니다</h2>
				<button type="button" class="btn btn-primary"
					onclick="history.back();">돌아가기</button>
			</div>
		</c:otherwise>
	</c:choose>


	


	<%@include file="../templatefooter.jsp"%>
</body>
</html>