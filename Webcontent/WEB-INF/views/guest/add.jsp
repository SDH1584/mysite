<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.javaex.vo.GuestbookVo" %>
<%@ page import="com.javaex.dao.GuestbookDao" %>
<%@ page import="java.util.List" %>
<%
	String name = request.getParameter("name");
	String content = request.getParameter("content");
	String password = request.getParameter("password");
	
	GuestbookVo guestbookVo = new GuestbookVo(name, content,password);
	GuestbookDao guestbookDao = new GuestbookDao();
	guestbookDao.insert(guestbookVo);
	List<GuestbookVo> guestbookList = guestbookDao.getList();
	
	response.sendRedirect("./addList.jsp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>