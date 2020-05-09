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


public class DomitoryManager extends ActionSupport {

	//下面是Action内用于封装用户请求参数的属性
	private List<DomitoryBean> list;
	private List<DomitoryBean> list1;
	public List<DomitoryBean> getList() {
		return list;
	}
	public void setList(List<DomitoryBean> list) {
		this.list = list;
	}
	private int a;

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}
	private String SearchRow;
	private String SearchKey;
	public String getSearchRow() {
		return SearchRow;
	}
	public void setSearchRow(String searchRow) {
		SearchRow = searchRow;
	}
	public String getSearchKey() {
		return SearchKey;
	}
	public void setSearchKey(String searchKey) {
		SearchKey = searchKey;
	}
	private List<BuildingBean> buildinglist;
	public List<BuildingBean> getBuildinglist() {
		return buildinglist;
	}
	public void setBuildinglist(List<BuildingBean> buildinglist) {
		this.buildinglist = buildinglist;
	}
	private String Domitory_BuildingID;
	public String getDomitory_BuildingID() {
		return Domitory_BuildingID;
	}
	public void setDomitory_BuildingID(String domitoryBuildingID) {
		Domitory_BuildingID = domitoryBuildingID;
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
		//查询条件
		String strWhere="1=1";
		if(!(isInvalid(SearchKey)))
		{
			strWhere+=" and "+SearchRow+"='"+SearchKey+"'";
		}
		if(!(isInvalid(Domitory_BuildingID)))
		{
			strWhere+=" and Domitory_BuildingID='"+Domitory_BuildingID+"'";
		}

		//查询所有楼宇
		buildinglist=new BuildingDao().GetList("","Building_Name");

		//查询所有
		list=new DomitoryDao().GetList(strWhere,"Building_Name");
		list1=new DomitoryDao().GetList(strWhere,"Building_Name",a);

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

	public List<DomitoryBean> getList1() {
		return list1;
	}

	public void setList1(List<DomitoryBean> list1) {
		this.list1 = list1;
	}
}
