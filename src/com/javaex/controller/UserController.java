package com.javaex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.javaex.utill.Webutill;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;


@WebServlet("/user")
public class UserController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/user");
		
		String action = request.getParameter("action");
	
		if("joinForm".equals(action)) {
			System.out.println("user > joinForm");
					
			Webutill.forward(request, response, "/WEB-INF/views/user/joinForm.jsp");
			
		}else if("join".equals(action)) {
			System.out.println("user > join");
			
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			
			UserVo userVo = new UserVo(id, password, name, gender);
			System.out.println(userVo);
			
			UserDao userDao = new UserDao();
			userDao.insert(userVo);
			
			Webutill.forward(request, response, "/WEB-INF/views/user/joinOk.jsp");
				
		}else if("loginForm".equals(action)) {
			System.out.println("user > loginForm");
			
			Webutill.forward(request, response, "/WEB-INF/views/user/loginForm.jsp");
			
		}else if("login".equals(action)) {
			System.out.println("user > login");
			
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			
			UserDao userDao = new UserDao();
			UserVo authVo = userDao.getUser(id, password);
			
			if(authVo == null) { 
				System.out.println("로그인실패");
				
				Webutill.redirect(request, response, "/mysite/user?action=loginForm&result=fail");
			}else { 
				System.out.println("로그인성공");
				
				HttpSession session = request.getSession();
				session.setAttribute("authUser", authVo);
				
				Webutill.redirect(request, response, "/mysite/main");
			}
			
						
		}else if("logout".equals(action)) {
			System.out.println("user > logout");
			
			HttpSession session = request.getSession();
			session.removeAttribute("authUser");
			session.invalidate();
			
			Webutill.redirect(request, response, "/mysite/main");
			
		}else if("modifyForm".equals(action)) {
			System.out.println("user > modifyForm");
			
			HttpSession session = request.getSession();
			int no = ((UserVo)session.getAttribute("authUser")).getNo();
			
			
			UserDao userDao = new UserDao();
			UserVo userVo = userDao.getUser(no);	
			
			System.out.println(userVo);
			request.setAttribute("userVo", userVo);
			Webutill.forward(request, response, "/WEB-INF/views/user/modifyForm.jsp");
			
		}else if("modify".equals(action)) {
			System.out.println("user > modify");
			
			HttpSession session = request.getSession();
			int no = ((UserVo)session.getAttribute("authUser")).getNo();
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String gender = request.getParameter("gender");
			
			UserVo vo = new UserVo(no, "", password, name, gender);
			
			
			UserDao userDao = new UserDao();
			userDao.update(vo);
			
			
			UserVo sVo = (UserVo)session.getAttribute("authUser");
			sVo.setName(name);
			
			Webutill.redirect(request, response, "/mysite/main");
		}else {
			System.out.println("error");
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}