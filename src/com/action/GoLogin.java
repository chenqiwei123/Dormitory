package com.action;

import com.dao.AdminDao;
import com.dao.StudentDao;
import com.dao.TeacherDao;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;

public class GoLogin extends ActionSupport {

	//??????Action????????????????????????
	private String Type;
	private String Username;
	private String Password;
	private String Msg;
	private String check;

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getMsg() {
		return Msg;
	}
	public void setMsg(String msg) {
		Msg = msg;
	}
	/**
	 * 以上就是获取输入登入信息的	 * */
	public String execute() throws Exception {




		if(Type.equals("admin"))
		{
			if (null == new AdminDao().CheckLogin(Username, Password)) {
				Msg = "message check is empty!";
				return INPUT;
			}
			else
			{
				//???ID
				System.out.println("admin have thing！");
				String Admin_ID=new AdminDao().CheckLogin(Username, Password);
				//????session
				HttpSession session = ServletActionContext.getRequest().getSession();
				session.setAttribute("id", Admin_ID);
				session.setAttribute("type", "1");
				return SUCCESS;
			}
		}
		else if(Type.equals("floor"))
		{
			if (null == new TeacherDao().CheckLogin(Username, Password)) {
				Msg = " floor message mistake!";
				return INPUT;
			}
			else
			{
				//???ID
				String Teacher_ID=new TeacherDao().CheckLogin(Username, Password);
				//????session
				HttpSession session = ServletActionContext.getRequest().getSession();
				session.setAttribute("id", Teacher_ID);
				session.setAttribute("type", "2");

				return SUCCESS;
			}
		}
		else if(Type.equals("student"))
		{
			if (null == new StudentDao().CheckLogin(Username, Password)) {
				Msg = "student message mistake!";
				return INPUT;
			}
			else
			{
				//???ID
				String Student_ID=new StudentDao().CheckLogin(Username, Password);
				//????session
				HttpSession session = ServletActionContext.getRequest().getSession();
				session.setAttribute("id", Student_ID);
				session.setAttribute("type", "3");
				return SUCCESS;
			}
		}
		else
		{
			Msg = "username or password is mistake!";
			return INPUT;
		}

	}
}
