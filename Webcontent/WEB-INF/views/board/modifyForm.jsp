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
			<!--  //aside  -->

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
				<div id="board">
					<div id="modifyForm">
						<form action="/mysite/board" method="get">
							<!-- 작성자 -->
							<div class="form-group">
								<span class="form-text">작성자</span> <span class="form-value">${requestScope.boardVo.name}</span>
							</div>

							<!-- 조회수 -->
							<div class="form-group">
								<span class="form-text">조회수</span> <span class="form-value">${requestScope.boardVo.hit}</span>
							</div>

							<!-- 작성일 -->
							<div class="form-group">
								<span class="form-text">작성일</span> <span class="form-value">${requestScope.boardVo.regDate}</span>
							</div>

							<!-- 제목 -->
							<div class="form-group">
								<label class="form-text" for="txt-title">글제목</label> <input type="text" id="txt-title" name="title" value="${requestScope.boardVo.title}">
							</div>

							<!-- 내용 -->
							<div class="form-group">
								<textarea id="txt-content" name="content">${requestScope.boardVo.content}</textarea>
							</div>

							<a id="btn_cancel" href="/mysite/board?action=read&no=${requestScope.boardVo.no}">취소</a>
							<button id="btn_modify" type="submit">수정</button>
							<input type="hidden" name="action" value="modify"> <input type="hidden" name="no" value="${requestScope.boardVo.no}">
						</form>
						<!-- //form -->
					</div>
					<!-- //modifyForm -->
				</div>
				<!-- //board -->
			</div>
			<!-- //content  -->

		</div>
		<!-- //container  -->
		<div>
			<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
			<!--  //footer -->
		</div>
		<!-- //wrap -->
</body>

</html>