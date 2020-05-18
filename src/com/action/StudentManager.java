package com.action;

import com.bean.DataNumber;
import com.bean.StudentBean;
import com.dao.StudentDao;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;


public class StudentManager extends ActionSupport {

	//下面是Action内用于封装用户请求参数的属性
	private List<StudentBean> list;
	private List<DataNumber> dataNumberlist;
	private int a;

    public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public List<StudentBean> getList() {
		return list;
	}
	public void setList(List<StudentBean> list) {
		this.list = list;
	}
	private String SearchRow;
	private String SearchKey;
	private String State;
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
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
//		查询条件
		String strWhere="1=1";
		if (!(isInvalid(SearchKey))||!(isInvalid(State))) {
			if (!(isInvalid(SearchKey))) {
				strWhere += " and " + SearchRow + "='" + SearchKey + "'";
			}
			if (!(isInvalid(State))){
				strWhere += " and Student_State='" + State + "'";
			}
			list = new StudentDao().GetAllList(strWhere, "Student_Username");
			dataNumberlist = new StudentDao().GetAllListCount();
			return SUCCESS;
		}

		//查询数据个数
		//查询所有
		if (session.getAttribute("number111")==null) {
			a=1;
			list=new StudentDao().GetAllList1(a);
		}
		else {
			list=new StudentDao().GetAllList1(a);
		}
		dataNumberlist=new StudentDao().GetAllListCount();
	
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

	public List<DataNumber> getDataNumberlist() {
		return dataNumberlist;
	}
}
