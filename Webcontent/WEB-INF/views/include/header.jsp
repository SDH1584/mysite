<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.javaex.vo.UserVo" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	UserVo authUser = (UserVo)session.getAttribute("authUser");
%>

	<div id="header" class="clearfix">
		<h1>
			<a href="/mysite/main">MySite</a>
		</h1>
	<c:choose>
		<c:when test="${empty sessionScope.authUser }">
		<!-- 로구인실패 로그인전 -->
		<ul>
			<li><a href="/mysite/user?action=loginForm" class="btn_s">로그인</a></li>
			<li><a href="/mysite/user?action=joinForm" class="btn_s">회원가입</a></li>
		</ul>
		</c:when>
		<c:otherwise>
		<ul>
			<li>${sessionScope.authUser.name}님 안녕!!</li>
			<li><a href="/mysite/user?action=logout" class="btn_s">로그아웃</a></li>
			<li><a href="/mysite/user?action=modifyForm" class="btn_s">회원정보수정</a></li>
		</ul>
		
		</c:otherwise>

		
	</c:choose>



	</div>
	<!-- //header -->
	<div id="nav">
		<ul class="clearfix">
			<li><a href="">입사지원서</a></li>
			<li><a href="/mysite/board?action=list">게시판</a></li>
			<li><a href="">갤러리</a></li>
			<li><a href="/mysite/guest?action=addList">방명록</a></li>
		</ul>
	</div>
	<!-- //nav -->
