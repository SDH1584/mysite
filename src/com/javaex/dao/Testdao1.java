package com.javaex.dao;

import com.javaex.vo.UserVo;

public class Testdao1 {

	public static void main(String[] args) {
		UserVo userVo= new UserVo("cc2c","12314","°­1È£µ¿","male");
	
		
		UserDao userDao=new UserDao();
		userDao.insert(userVo);
	}

}
