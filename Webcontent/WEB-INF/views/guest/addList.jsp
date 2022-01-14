<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="/mysite/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="/mysite/assets/css/guestbook.css" rel="stylesheet" type="text/css">

</head>

<body>
	<div id="wrap">
		<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	
	
		<div id="container" class="clearfix">
			<div id="aside">
				<h2>방명록</h2>
				<ul>
					<li>일반방명록</li>
					<li>방명록</li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">
				
				<div id="content-head" class="clearfix">
					<h3>일반방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">일반방명록</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->

				<div id="guestbook">
					<form action="/mysite/guest" method="">
						<table id="guestAdd">
							<colgroup>
								<col style="width: 70px;">
								<col>
								<col style="width: 70px;">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th><label class="form-text" for="input-uname">이름</label></td>
									<td><input id="input-uname" type="text" name="name"></td>
									<th><label class="form-text" for="input-password">패스워드</label></td>
									<td><input id="input-password"type="password" name="password"></td>
								</tr>
								<tr>
									<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
								</tr>
								<tr class="button-area">
									<td colspan="4" class="text-center"><button type="submit">등록</button></td>
									<input type="hidden" name="action" value="add">
								</tr>
							</tbody>
							
						</table>
						<!-- //guestWrite -->
						<input type="hidden" name="action" value="add">
						
					</form>	
				
				<c:forEach items="${getList}" var="guestVo">
						<table class="guestRead">
							<colgroup>
								<col style="width: 10%;">
								<col style="width: 40%;">
								<col style="width: 40%;">
								<col style="width: 10%;">
							</colgroup>
							<tr>
								<td>${guestVo.no }</td>
								<td>${guestVo.name }</td>
								<td>${guestVo.regDate }</td>
								<td><a href="/mysite/guest?action=deleteForm&no=${guestVo.no }">[삭제]</a></td>
							</tr>
							<tr>
								<td colspan=4 class="text-left">${guestVo.content }</td>
							</tr>
						</table>
					</c:forEach>
					
				</div>
				<!-- //guestbook -->
			
			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->

	<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
	</div>
	<!-- //wrap -->

</body>

</html>