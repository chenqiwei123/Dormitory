package com.action;

import com.bean.NoticeBean;
import com.dao.NoticeDao;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;

public class Notice extends ActionSupport {
    private String Msg1;
    private String Msg2;

    public String getMsg2() {
        return Msg2;
    }

    public void setMsg2(String msg2) {
        Msg2 = msg2;
    }

    public String getMsg1() {
        return Msg1;
    }
    public void setMsg1(String msg1) {
        Msg1 = msg1;
    }
    private List<NoticeBean> list;
    public List<NoticeBean> getList() {
        return list;
    }
    public void setList(List<NoticeBean> list) {
        this.list = list;
    }
    private List<NoticeBean> repairlist;
    public List<NoticeBean> getRepairlist() {
        return list;
    }
    public void setRepairlist(List<NoticeBean> list) {
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
        list= new NoticeDao().GetBean();
        Msg1= String.valueOf(list.get(0));
        Msg2= String.valueOf(list.get(1));

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
