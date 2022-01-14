package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.BoardDao;
import com.javaex.dao.GuestbookDao;
import com.javaex.utill.Webutill;
import com.javaex.vo.BoardVo;
import com.javaex.vo.GuestbookVo;

@WebServlet("/board")
public class BoardControoler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.println("/board");
	String action = request.getParameter("action");

	if ("writeForm".equals(action)) {
		System.out.println("writeForm=add");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		
		int userNo=Integer.parseInt(request.getParameter("no"));
	
		BoardVo vo=new BoardVo(title,content,userNo);
		BoardDao dao=new BoardDao();
		System.out.println(vo);
		dao.insert(vo);
		
	Webutill.redirect(request, response, "/mysite/board?action=list");	
	
	}else if("list".equals(action)){
		System.out.println("list");
		BoardDao dao=new BoardDao();
		List<BoardVo> boardList = dao.boardList();
		System.out.println(boardList);
		request.setAttribute("boardList", boardList);
		
		Webutill.forward(request, response, "/WEB-INF/views/board/list.jsp");
	}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
