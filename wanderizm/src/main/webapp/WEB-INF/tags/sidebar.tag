<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytag"%>
<div id="sidebar">
	<div class="inner">
		
		<!-- Menu -->
		<nav id="menu">
			<header class="major" style="padding-top: 20px;">
				<h2>Menu</h2>
			</header>
			<ul>		
				<li><a href="getRecommandList.do">FOR WANDERIER</a></li>
				<li><span class="opener">여행 정보 게시판</span>
					<ul>
						<c:forEach var="v" items="${sidebarData.cateData}">
						<li><a href="getBoardList.do?b_type=info&cate_id=${v.cate_id}">${v.category}</a></li>
						</c:forEach>
					</ul>
				</li>
				<li><a href="getBoardList.do?b_type=ask">자유 질문 게시판</a></li>
				<li><span class="opener">여행 후기 게시판</span>
					<ul>
						<c:forEach var="v" items="${sidebarData.nationData}">
						<li><a href="getBoardList.do?b_type=review&n_id=${v.n_id}">${v.nation}</a></li>
						</c:forEach>
					</ul>
				</li>
				<c:if test="${sessionID eq 'seong'}">
				<li><a href="generic.jsp">generic</a></li>
				<li><a href="elements.jsp">element</a></li>				
				</c:if>
			</ul>
		</nav>

		<!-- Section -->
		<section>
			<header class="major">
				<h2>Get in touch</h2>
			</header>
			<p>Sed varius enim lorem ullamcorper dolore aliquam aenean ornare
				velit lacus, ac varius enim lorem ullamcorper dolore. Proin sed
				aliquam facilisis ante interdum. Sed nulla amet lorem feugiat tempus
				aliquam.</p>
			<ul class="contact">
				<li class="icon solid fa-envelope"><a href="https://mail.google.com/mail">sngynhy@gmail.com</a></li>
				<li class="icon solid fa-home"><a href="https://sngynhy.tistory.com">https://sngynhy.tistory.com/</a></li>
				<li class="icon brands fa-github"><a href="https://github.com/sngynhy"><span class="label">https://github.com/sngynhy</span></a></li>
				<!-- <li class="icon solid fa-phone">010-9259-3937</li> -->
			</ul>
		</section>

		<!-- Footer -->
		<footer id="footer">
			<p class="copyright">
				&copy; Untitled. All rights reserved. Demo Images: <a
					href="https://unsplash.com">Unsplash</a>. Design: <a
					href="https://html5up.net">HTML5 UP</a>.
			</p>
		</footer>

	</div>
</div>