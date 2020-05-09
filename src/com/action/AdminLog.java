package com.action;

import com.bean.BuildingBean;
import com.bean.DomitoryBean;
import com.dao.BuildingDao;
import com.dao.DomitoryDao;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;



public class AdminLog extends ActionSupport {

	private List<BuildingBean> buildinglist;
	private List<DomitoryBean> domitorylist;

	public List<BuildingBean> getBuildinglist() {
		return buildinglist;
	}

	public void setBuildinglist(List<BuildingBean> buildinglist) {
		this.buildinglist = buildinglist;
	}

	public List<DomitoryBean> getDomitorylist() {
		return domitorylist;
	}

	public void setDomitorylist(List<DomitoryBean> domitorylist) {
		this.domitorylist = domitorylist;
	}

	private String BuildingID;
	private String DomitoryID;
	public String getBuildingID() {
		return BuildingID;
	}

	public void setBuildingID(String buildingID) {
		BuildingID = buildingID;
	}

	public String getDomitoryID() {
		return DomitoryID;
	}

	public void setDomitoryID(String domitoryID) {
		DomitoryID = domitoryID;
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
			out.print("<script language='javascript'>alert('请重新登录!');window.location='Login.jsp';</script>");
			out.flush();out.close();return null;
		}

		//查询楼宇
		buildinglist=new BuildingDao().GetList("","Building_Name");
//		System.out.println(BuildingID);
		//查询条件
		String strWhere="1=1 ";
		if(!(isInvalid(BuildingID)))
		{
			strWhere+=" and Domitory_BuildingID='"+BuildingID+"'";
		}
		else{
			strWhere+=" and 1=2";
		}
		//查询寝室
		domitorylist=new DomitoryDao().GetList(strWhere,"Domitory_Name");
		
		return SUCCESS;
		
	}

	//判断是否空值
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}

	public static void main(String[] args) {
		System.out.println();
	}
	
}
