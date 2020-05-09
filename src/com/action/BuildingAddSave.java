package com.action;

import com.bean.BuildingBean;
import com.dao.BuildingDao;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;


public class BuildingAddSave extends ActionSupport {

	//下面是Action内用于封装用户请求参数的属性
	private String Building_Name ;
    private String Building_Introduction ;

	public String getBuilding_Name() {
		return Building_Name;
	}

	public void setBuilding_Name(String buildingName) {
		Building_Name = buildingName;
	}

	public String getBuilding_Introduction() {
		return Building_Introduction;
	}

	public void setBuilding_Introduction(String buildingIntroduction) {
		Building_Introduction = buildingIntroduction;
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
		
		//查询名称是否存在
		List<BuildingBean> list=new BuildingDao().GetList("Building_Name='"+Building_Name+"'", "");
		if(list.size()>0)
		{
			out.print("<script language='javascript'>alert('名称已经存在！');history.back(-1);</script>");
			out.flush();out.close();return null;
		}
		//添加
		BuildingBean cnbean=new BuildingBean();
		cnbean.setBuilding_Name(Building_Name);
		cnbean.setBuilding_Introduction(Building_Introduction);
		new BuildingDao().Add(cnbean);
		    
		//跳转
		out.print("<script language='javascript'>alert('添加成功！');window.location='BuildingManager.action';</script>");
		out.flush();out.close();return null;
		
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
