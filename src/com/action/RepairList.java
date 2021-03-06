package com.action;

import com.bean.Repair_addBean;
import com.dao.RepairDao;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;

public class RepairList extends ActionSupport {
    private List<Repair_addBean> list;
    public List<Repair_addBean> getList() {
        return list;
    }
    public void setList(List<Repair_addBean> list) {
        this.list = list;
    }
    private List<Repair_addBean> repairlist;
    public List<Repair_addBean> getRepairlist() {
        return list;
    }
    public void setRepairlist(List<Repair_addBean> list) {
        this.list = list;
    }
    //处理用户请求的execute方法
    public String execute() throws Exception {

        //解决乱码，用于页面输出
        HttpServletResponse response=null;
        response= ServletActionContext.getResponse();
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        //创建session对象
        HttpSession session = ServletActionContext.getRequest().getSession();
        //验证是否正常登录
        //查询
        System.out.println(session.getAttribute("id"));
        list= new RepairDao().GetBean(String.valueOf(session.getAttribute("id")));
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
