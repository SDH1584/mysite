package com.javaex.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.utill.Webutill;
@WebServlet("/main")
public class MainController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.println("/main");

	request.setCharacterEncoding("UTF-8");
	String action = request.getParameter("action");

	if ("add".equals(action)) {
		System.out.println("/join");

		Webutill.redirect(request, response, "/mysite/join.jsp");
		}
	Webutill.forward(request, response, "WEB-INF/views/main/index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
