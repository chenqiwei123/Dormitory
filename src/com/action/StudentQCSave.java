package com.action;

import com.bean.OutBean;
import com.bean.StudentBean;
import com.dao.OutDao;
import com.dao.StudentDao;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.Calendar;


public class StudentQCSave extends ActionSupport {

	//??????Action????????????????????????
	private String Student_ID ;
	private String Out_Remark ;
	public String getOut_Remark() {
		return Out_Remark;
	}

	public void setOut_Remark(String outRemark) {
		Out_Remark = outRemark;
	}

	public String getStudent_ID() {
		return Student_ID;
	}

	public void setStudent_ID(String studentID) {
		Student_ID = studentID;
	}

	//????????????execute????
	public String execute() throws Exception {
		
		//?????????????????
		HttpServletResponse response=null;
		response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		//????session????
		HttpSession session = ServletActionContext.getRequest().getSession();
		//?????????????
		if(session.getAttribute("id")==null){
			out.print("<script language='javascript'>alert('??????????');window.location='Login.jsp';</script>");
			out.flush();out.close();return null;
		}
		

		//????????
		StudentBean cnbean=new StudentBean();
		cnbean=new StudentDao().GetBean(Integer.parseInt(Student_ID));
		cnbean.setStudent_State("???");
		new StudentDao().Update(cnbean);
		
		//?????????
		OutBean outbean=new OutBean();
		outbean.setOut_StudentID(Integer.parseInt(Student_ID));
		outbean.setOut_Date(getNowdate());
		outbean.setOut_Remark(Out_Remark);

		new OutDao().Add(outbean);
		    
		//???
		out.print("<script language='javascript'>alert('???????????????');window.location='StudentTH.jsp';</script>");
		out.flush();out.close();return null;
		
	}
	//??????????
	public String getNowdate(){
		Calendar c=Calendar.getInstance();
		c.add(Calendar.MONTH, 1);
		int year=c.get(Calendar.YEAR);
		int month=c.get(Calendar.MONTH);
		int date=c.get(Calendar.DATE);
		return year+"-"+month+"-"+date;
	}
	//?ж??????
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}
	
	//????
	public static void main(String[] args) {
		System.out.println();
	}
	
}
