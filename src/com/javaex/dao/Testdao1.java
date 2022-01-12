package com.javaex.dao;

import java.util.List;

import com.javaex.vo.GuestbookVo;
public class Testdao1 {

	public static void main(String[] args) {
		GuestbookDao dao = new GuestbookDao();
			
			//리스트 선언
		List<GuestbookVo> getList = dao.getList();
			
			if(getList.isEmpty()) {
				
				System.out.println("List is empty");
			} 
			else {
				
				System.out.println(getList);
				System.out.println("List is not empty");
			}
		}
	}

