 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>AdminLTE 3 | Dashboard</title>

  <!-- Google Font: Source Sans Pro -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="../resources/plugins/fontawesome-free/css/all.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
  <!-- Tempusdominus Bootstrap 4 -->
  <link rel="stylesheet" href="../resources/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
  <!-- iCheck -->
  <link rel="stylesheet" href="../resources/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
  <!-- JQVMap -->
  <link rel="stylesheet" href="../resources/plugins/jqvmap/jqvmap.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="../resources/dist/css/adminlte.min.css">
  <!-- overlayScrollbars -->
  <link rel="stylesheet" href="../resources/plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
  <!-- Daterange picker -->
  <link rel="stylesheet" href="../resources/plugins/daterangepicker/daterangepicker.css">
  <!-- summernote -->
  <link rel="stylesheet" href="../resources/plugins/summernote/summernote-bs4.min.css">
  
  <script src="../resources/plugins/jquery/jquery.min.js"></script>
  <script>

  </script>
  
  
</head>
<body class="hold-transition sidebar-mini layout-fixed">

   <%@include file="../templateHeader.jsp" %>
   <%@include file="../templateAside.jsp" %>
   
   <div class="content-wrapper">   <!--  여기가 본문 시작 -->
      <h1>글 수정페이지 </h1>
   <div class="container">
      <h1>${board.no }번째 글</h1><hr />

         <form action="/board/modi" method="post">
         <input type="hidden" name="no" value="${board.no }">
            <div class="form-group">
               <label class="control-label col-sm-2" for="userid">작성자 :</label>
               <div class="col-sm-10">
                  <input type="text" class="form-control" id="writer" name="writer" value="${board.writer }" readonly="readonly">
               </div>
            </div>
            <div class="form-group">
               <label class="control-label col-sm-2" for="title">제 목 :</label>
               <div class="col-sm-10">
                  <input type="text" class="form-control" id="title" name="title" value="${board.title} ">
               </div>
            </div>
            <div class="form-group">
               <label class="control-label col-sm-2" for="content">내 용 :</label>
               <div class="col-sm-10">
                  <textarea rows="10" cols="30" class="form-control" id="content"  name="content" >${board.content}</textarea>
               </div>
            </div>
            <div class="form-group">
               <div class="col-sm-offset-2 col-sm-10">
                  <button type="submit" class="btn btn-success">저장</button>
                  <button type="button" class="btn btn-danger" onclick="location.href='/board/listAll';">취소</button>
               </div>
            </div>
         </form>
      </div>
   </div>
   
   
   
   <%@include file="../templatefooter.jsp" %>

</body>

<!-- jQuery -->
<script src="./resources/plugins/jquery/jquery.min.js"></script>
<!-- jQuery UI 1.11.4 -->
<script src="./resources/plugins/jquery-ui/jquery-ui.min.js"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
  $.widget.bridge('uibutton', $.ui.button)
</script>
<!-- Bootstrap 4 -->
<script src="./resources/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- ChartJS -->
<script src="./resources/plugins/chart.js/Chart.min.js"></script>
<!-- Sparkline -->
<script src="./resources/plugins/sparklines/sparkline.js"></script>
<!-- JQVMap -->
<script src="./resources/plugins/jqvmap/jquery.vmap.min.js"></script>
<script src="./resources/plugins/jqvmap/maps/jquery.vmap.usa.js"></script>
<!-- jQuery Knob Chart -->
<script src="./resources/plugins/jquery-knob/jquery.knob.min.js"></script>
<!-- daterangepicker -->
<script src="./resources/plugins/moment/moment.min.js"></script>
<script src="./resources/plugins/daterangepicker/daterangepicker.js"></script>
<!-- Tempusdominus Bootstrap 4 -->
<script src="./resources/plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
<!-- Summernote -->
<script src="./resources/plugins/summernote/summernote-bs4.min.js"></script>
<!-- overlayScrollbars -->
<script src="./resources/plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
<!-- AdminLTE App -->
<script src="./resources/dist/js/adminlte.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="./resources/dist/js/demo.js"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<script src="./resources/dist/js/pages/dashboard.js"></script>
</body>
</html>