package com.action;

import com.bean.TBBean;
import com.bean.TeacherBean;
import com.dao.TBDao;
import com.dao.TeacherDao;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;


public class TBManager extends ActionSupport {

	//下面是Action内用于封装用户请求参数的属性
	private String Building_ID;
	public String getBuilding_ID() {
		return Building_ID;
	}
	public void setBuilding_ID(String buildingID) {
		Building_ID = buildingID;
	}
	private List<TBBean> list;
	public List<TBBean> getList() {
		return list;
	}
	public void setList(List<TBBean> list) {
		this.list = list;
	}
	private List<TeacherBean> teacherlist;
	public List<TeacherBean> getTeacherlist() {
		return teacherlist;
	}
	public void setTeacherlist(List<TeacherBean> teacherlist) {
		this.teacherlist = teacherlist;
	}
	//处理用户请求的execute方法
	public String execute() throws Exception {
		
		//解决乱码，用于页面输出
		HttpServletResponse response=null;
		response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		//创建session对象
		HttpSession session = ServletActionContext.getRequest().getSession();
		//验证是否正常登录
		if(session.getAttribute("id")==null){
			out.print("<script language='javascript'>alert('请重新登录！');window.location='Login.jsp';</script>");
			out.flush();out.close();return null;
		}

		//查询
		teacherlist=new TeacherDao().GetList("","");
		
		//查询
		list=new TBDao().GetList("TB_BuildingID="+Building_ID,"");
	
		return SUCCESS;
		
	}
	
	//判断是否空值
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}
	
	//测试
	public static void main(String[] args) {
		System.out.println();
	}
	
}
