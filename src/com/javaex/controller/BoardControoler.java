package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.BoardDao;
import com.javaex.utill.Webutill;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@WebServlet("/board")
public class BoardControoler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.println("/board");
	String action = request.getParameter("action");

	if ("list".equals(action)) {
		System.out.println("action > list");
		List<BoardVo> boardList = new BoardDao().boardList();

		request.setAttribute("boardList", boardList);

		 
		System.out.println(boardList);

		Webutill.forward(request, response, "/WEB-INF/views/board/list.jsp");
	} else if ("writeForm".equals(action)) {
		System.out.println("action > writeForm");

		Webutill.forward(request, response, "/WEB-INF/views/board/writeForm.jsp");
	} else if ("write".equals(action)) {
		System.out.println("action > write");

		HttpSession session = request.getSession();

		UserVo authUser = (UserVo) session.getAttribute("authUser");

		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int userNo = authUser.getNo();

		BoardVo boardVo = new BoardVo(title, content, userNo);
		new BoardDao().insert(boardVo);

		Webutill.redirect(request, response, "/mysite/board?action=list");
	} else if ("read".equals(action)) {

		int no = Integer.parseInt(request.getParameter("no"));
		
		BoardVo boardvo = new BoardDao().read(no);
		BoardDao boardDao = new BoardDao();
		
		request.setAttribute("boardVo", boardvo);
		boardDao.hit(no);
		
		Webutill.forward(request, response, "/WEB-INF/views/board/read.jsp");
	
	
	} else if ("delete".equals(action)) {// 게시글 삭제
		System.out.println("action > delete");

		int num = Integer.parseInt(request.getParameter("no"));

		new BoardDao().delete(num);

		Webutill.redirect(request, response, "/mysite/board?action=list");
	} else if ("modifyForm".equals(action)) {// 게시글 수정 폼
		System.out.println("action > modifyForm");

		int no = Integer.parseInt(request.getParameter("no"));

		BoardVo boardVo = new BoardDao().read(no);
		request.setAttribute("boardVo", boardVo);

		Webutill.forward(request, response, "/WEB-INF/views/board/modifyForm.jsp");
	} else if ("modify".equals(action)) {
		System.out.println("action > modify");

		int no = Integer.parseInt(request.getParameter("no"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		BoardVo boardVo = new BoardVo(no, title, content);
		new BoardDao().update(boardVo);

		Webutill.redirect(request, response, "/mysite/board?action=list");
	} else {
		System.out.println("파라미터 없음");
	}
}

protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	doGet(request, response);
}

}
