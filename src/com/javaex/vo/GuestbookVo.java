package com.javaex.vo;

public class GuestbookVo {
	
	//�ʵ�
	private int no;
	private String name;
	private String password;
	private String content;
	private String regDate;
	//������
	public GuestbookVo() {}
	public GuestbookVo(int no, String name,  String password,String content, String regDate) {
		this.no = no;
		this.name = name;
		this.password = password;
		this.content = content;
		this.regDate = regDate;
	}
	
	public GuestbookVo( String name, String password,String content) {
		this.name = name;
		this.password = password;
		this.content = content;

	}
	
	public GuestbookVo( String name, String password,String content, String regDate) {
		this.name = name;
		this.password = password;
		this.content = content;
		this.regDate = regDate;
	}
	//�޼ҵ� g/s
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	
	
	
	//�޼ҵ� �Ϲ�	
	@Override
	public String toString() {
		return "GusetbookVo [no=" + no + ", name=" + name + ", content=" + content + ", password=" + password
				+ ", regDate=" + regDate + "]";
	}
	public GuestbookVo(int no, String name) {
		super();
		this.no = no;
		this.name = name;
	}
	
}