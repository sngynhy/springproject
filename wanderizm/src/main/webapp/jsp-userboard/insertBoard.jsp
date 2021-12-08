<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytag"%>
<!DOCTYPE HTML>
<!--
	Editorial by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title>WANDERIZM</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="/assets/css/main.css" />
		<link rel="icon" href="/images/road-trip.png">
		<script src="https://cdn.ckeditor.com/ckeditor5/30.0.0/classic/ckeditor.js"></script>
		<script src="https://ckeditor.com/apps/ckfinder/3.5.0/ckfinder.js"></script>
		<style>
			.ck-editor__editable_inline {
			    min-height: 600px;
			}
		</style>
	</head>
	<body class="is-preload">

		<!-- Wrapper -->
			<div id="wrapper">

				<!-- Main -->
					<div id="main">
						<div class="inner">

						<!-- Header -->
							<header id="header">
								<a href="main.jsp" class="logo"><strong>WANDERIZM</strong></a>
								<mytag:login />
							</header>

						<!-- Section -->
							<section style="padding-top: 35px;">
								<header class="major">
									<c:choose>
										<c:when test="${data.b_type eq 'info'}">
											<h3>정보 공유 게시판</h3>
											<c:forEach var="v" items="${sidebarData.cateData}">
												<c:if test="${data.cate_id eq v.cate_id}">
													<h4 style="color: gray;">${v.category}</h4>
												</c:if>
											</c:forEach>
										</c:when>
										<c:when test="${data.b_type eq 'ask'}">
											<h3>자유 질문 게시판</h3>
										</c:when>
										<c:when test="${data.b_type eq 'review'}">
											<h3>여행 후기 게시판</h3>
											<c:forEach var="v" items="${sidebarData.nationData}">
												<c:if test="${data.n_id eq v.n_id}">
													<h4 style="color: gray;">${v.nation}</h4>
												</c:if>
											</c:forEach>
										</c:when>
									</c:choose>
								</header>
								
								<!-- 게시글 입력 폼 -->
								<form method="post" action="/insertBoard.do" name="form1">
									<input type="hidden" name="id" value="${sessionID}">
									<input type="hidden" name="b_type" value="${data.b_type}">
									<input type="hidden" name="cate_id" value="${data.cate_id}">
									<input type="hidden" name="a_id" value="${data.a_id}">
									<input type="hidden" name="n_id" value="${data.n_id}">
									<h2><span style="float: left; width: 10%">제목 : </span><input type="text" name="title" id="title" required="required" style="width: 90%;"></h2>
									<!-- CKEditor -->
									<textarea name="content" id="editor"></textarea>
									<input type="button" value="등록" onclick="send()" class="button" style="float: right; margin-top:10px;">
								</form>
							</section>
						</div>
					</div>

				<!-- Sidebar -->
					<mytag:sidebar />
			</div>

		<!-- Scripts -->
			<script src="/assets/js/jquery.min.js"></script>
			<script src="/assets/js/browser.min.js"></script>
			<script src="/assets/js/breakpoints.min.js"></script>
			<script src="/assets/js/util.js"></script>
			<script src="/assets/js/main.js"></script>
	</body>
	<script>
	function send() {
		if ($('#title').val() == '') {
			alert('제목을 입력해주세요.');
			$('#title').focus();
			return;
		}
		if ($('.ck-content').text() == '') {
			alert('내용을 입력해주세요.');
			$('.ck-content').focus();
			return;
		}
		document.form1.submit();
	}
	function changeCateId(obj) {
		var selectValue = obj.value;
		/* console.log(selectValue); */
		window.location.href="/getBoardList.do?b_type=info&cate_id=" + selectValue;
	}
	function changeN_Id(obj) {
		var selectValue = obj.value;
		/* console.log(selectValue); */
		window.location.href="/getBoardList.do?b_type=review&n_id=" + selectValue;
	}
    // 3. CKEditor5를 생성할 textarea 지정
    ClassicEditor
	.create( document.querySelector('#editor'),{
		language: "ko",
		ckfinder: {
	        uploadUrl: '/fileupload.do'
		},
		alignment: {
            options: [ 'left', 'center', 'right' ]
        },
        image: {
        	toolbar: ['toggleImageCaption','resizeImage']
        }
	})
	.then( editor => {
        console.log( 'Editor was initialized', editor );
    } )
	.catch( error => {
		console.error( error );
	} );
    </script>
</html>