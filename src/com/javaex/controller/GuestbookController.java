package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestbookDao;
import com.javaex.utill.Webutill;
import com.javaex.vo.GuestbookVo;

@WebServlet("/guest")
public class GuestbookController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("/guest");

		String action = request.getParameter("action");

		if ("add".equals(action)) {
			System.out.println("action=add");

			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
				
			GuestbookVo vo = new GuestbookVo(name, password,content);
			GuestbookDao dao = new GuestbookDao();
			System.out.println(vo);
			dao.insert(vo);

			Webutill.redirect(request, response, "/mysite/guest?action=addList");

		} else if ("deleteForm".equals(action)) {
			System.out.println("action=deleteform");

			Webutill.forward(request, response, "/WEB-INF/views/guest/addList.jsp");
	
		} else if ("delete".equals(action)) {
			System.out.println("action=delete");

			int no = Integer.parseInt(request.getParameter("no"));
			String password = request.getParameter("password");

			GuestbookDao dao = new GuestbookDao();
			dao.delete(no,password);
			
			Webutill.redirect(request, response, "/mysite/guest");

		} else if("addList".equals(action)){
			System.out.println("addList");
			GuestbookDao dao = new GuestbookDao();
			
			List<GuestbookVo> getList = dao.getList();
			System.out.println(getList);
			request.setAttribute("getList", getList);
			
			Webutill.forward(request, response, "/WEB-INF/views/guest/addList.jsp");
		}else{
			System.out.println("error");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}