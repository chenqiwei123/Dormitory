package com.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;


public class Quit extends ActionSupport {


	//处理用户请求的execute方法
	public String execute() throws Exception {
		
		//清除session
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.removeAttribute("id");
		
		return SUCCESS;
		
	}
	
	//测试
	public static void main(String[] args) {
		System.out.println();
	}
	
}
