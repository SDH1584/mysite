<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/mysite/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="/mysite/assets/css/board.css" rel="stylesheet" type="text/css">
</head>

<body>
	<div id="wrap">
		<!-- header -->
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->
		<div id="container" class="clearfix">
			<!--  aside  -->
			<c:import url="/WEB-INF/views/include/aside.jsp"></c:import>
			<!-- //content-head -->
			<div id="content">
				<div id="content-head">
					<h3>게시판</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>게시판</li>
							<li class="last">일반게시판</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
			</div>
			<div id="board">
				<div id="writeForm">
					<form action="/mysite/board" method="get">
						<!-- 제목 -->
						<div class="form-group">
							<label class="form-text" for="txt-title">제목</label> <input type="text" id="txt-title" name="title" value="" placeholder="제목을 입력해 주세요">
						</div>
						<!-- 내용 -->
						<div class="form-group">
							<textarea name="content" id="txt-content"></textarea>
						</div>
						<a id="btn_cancel" href="">취소</a>
						<button id="btn_add" type="submit">등록</button>
						<input type="hidden" name="action" value="write"> <input type="hidden" name="no" value="${sessionScope.authUser.no}"> <input type="hidden" name="name" value="${sessionScope.authUser.name}">
					</form>
					<!-- //form -->
				</div>
				<!-- //writeForm -->
			</div>
			<!-- //board -->
		</div>
		<!-- //content  -->
	<div>
	<!-- //container  -->
	<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
	<!-- //footer -->
	</div>
	<!-- //wrap -->

</body>

</html>