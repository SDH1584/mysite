package com.javaex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.UserDao;
import com.javaex.utill.Webutill;
import com.javaex.vo.UserVo;


@WebServlet("/user")
public class UserController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/user");
		
		String action = request.getParameter("action");
	
		if("joinForm".equals(action)) {
			System.out.println("user > joinForm");
					
			//포워드
			Webutill.forward(request, response, "/WEB-INF/views/user/joinForm.jsp");
			
		}else if("join".equals(action)) {
			System.out.println("user > join");
			
			//파라미터값 가져오기
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			
			//파라미터-->vo로 만들기
			UserVo userVo = new UserVo(id, password, name, gender);
			//System.out.println(userVo);
			
			//UserDao 의 insert()로 저장하기(회원가입하기)
			UserDao userDao = new UserDao();
			userDao.insert(userVo);
			
			//포워드
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
			//System.out.println(uservo);
			
			if(authVo == null) { //로그인실패
				System.out.println("로그인실패");
				
				Webutill.redirect(request, response, "/mysite/user?action=loginForm&result=fail");
			}else { //로그인성공
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
			
			HttpSession session = request.getSession();
			UserVo authUser = (UserVo)session.getAttribute("authUser");
			
			String id=authUser.getId();
			String password=authUser.getPassword();
			UserDao userDao = new UserDao();
			UserVo userVo = userDao.getUser(id,password);
			
			request.setAttribute("userVo", userVo);
			
			//포워드
			Webutill.forward(request, response, "/WEB-INF/views/user/modifyForm.jsp");
	
		}else {
			System.out.println("error");
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}