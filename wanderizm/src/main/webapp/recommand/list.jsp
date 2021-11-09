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
		<link rel="stylesheet" href="assets/css/main.css" />
		<link rel="stylesheet" href="assets/css/recommand.css" />
		<link rel="icon" href="images/road-trip.png">
	</head>
	<body class="is-preload">

		<!-- Wrapper -->
			<div id="wrapper">

				<!-- Main -->
					<div id="main">
						<div class="inner">

						<!-- Header -->
							<header id="header">
								<a href="main.do" class="logo"><strong>WANDERIZM</strong></a>
								<mytag:login />
							</header>
						
						<!-- Section -->
							<section>
								<div class="recommand-header">
									<h2 class="recommand-title">Recommand</h2>
									<c:if test="${sessionID == 'seong'}">
									<div class="recommand-btn-area">
										<a href="insertRecoomandView.do" class="button primary samll">작성하기</a>
									</div>
									</c:if>
								</div>
							
							<c:forEach var="v" items="${datas}">
								<div class="posts">
									<!-- list for start -->
									<article>
										<a href="getRecommand.do?b_id=${v.b_id}" class="image"><img src="${v.img_path}" alt="" /></a>
										<h2><a href="getRecommand.do?b_id=${v.b_id}">${v.title}</a></h2>
									</article>
									<!-- list for end -->
								</div>
							</c:forEach>
							</section>
							
						</div>
					</div>

				<!-- Sidebar -->
					<mytag:sidebar />
			</div>

		<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/browser.min.js"></script>
			<script src="assets/js/breakpoints.min.js"></script>
			<script src="assets/js/util.js"></script>
			<script src="assets/js/main.js"></script>

	</body>
	
</html>