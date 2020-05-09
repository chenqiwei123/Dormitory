package com.action;

import com.bean.Repair_addBean;
import com.dao.RepairDao;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;


public class RepairAdd extends ActionSupport {

    //下面是Action内用于封装用户请求参数的属性
    private String repair_address ;
    private String repair_student;
    private String repair_info ;
    private String repair_cost;
    private String repair_time;
    private String repair_status ;
    private String repair_tel ;

    public String getRepair_address() {
        return repair_address;
    }

    public void setRepair_address(String repair_address) {
        this.repair_address = repair_address;
    }

    public String getRepair_info() {
        return repair_info;
    }

    public void setRepair_info(String repair_info) {
        this.repair_info = repair_info;
    }

    public String getRepair_cost() {
        return repair_cost;
    }

    public void setRepair_cost(String repair_cost) {
        this.repair_cost = repair_cost;
    }

    public String getRepair_time() {
        return repair_time;
    }

    public void setRepair_time(String repair_time) {
        this.repair_time = repair_time;
    }

    public String getRepair_status() {
        return repair_status;
    }

    public void setRepair_status(String repair_status) {
        this.repair_status = repair_status;
    }

    public String getRepair_tel() {
        return repair_tel;
    }

    public void setRepair_tel(String repair_tel) {
        this.repair_tel = repair_tel;
    }

    //处理用户请求的execute方法
    public String execute() throws Exception {
        //解决乱码，用于页面输出
        int result;
        HttpServletResponse response=null;
        response=ServletActionContext.getResponse();
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        //创建session对象
        HttpSession session = ServletActionContext.getRequest().getSession();
        //验证是否正常登录
        if(session.getAttribute("id")==null){
            out.print("<script language='javascript'>alert('请重新登录！');window.location='RepairAdd.jsp';</script>");
            out.flush();out.close(); return null;
        }

        //查询用户名是否存在
//        List<StudentBean> list=new StudentDao().GetList("Student_Username='"+Student_Username+"'", "");
//        if(list.size()>0)
//        {
//            out.print("<script language='javascript'>alert('用户名已经存在！');history.back(-1);</script>");
//            out.flush();out.close();return null;
//        }
        //添加


//new日期对
        long l = System.currentTimeMillis();
        Date date = new Date(l);
//转换提日期输出格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        repair_time=dateFormat.format(date);
        repair_cost="25";
        repair_status="待维修";
        Date date1 = new java.sql.Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //从前端或者自己模拟一个日期格式，转为String即可
        repair_time = format.format(date1);
        repair_student= (String) session.getAttribute("id");
        Repair_addBean cnbean=new Repair_addBean();
        cnbean.setRepair_address(repair_address);
        cnbean.setRepair_info(repair_info);
        cnbean.setRepair_tel(repair_tel);
        cnbean.setRepair_time(repair_time);
        cnbean.setRepair_cost(repair_cost);
        cnbean.setRepair_status(repair_status);
        cnbean.setRepair_student(repair_student);
        result=new RepairDao().Add(cnbean);
        if (result>=1){
            out.printf("<script language='javascript'>alert('上报维修信息成功！');window.location='RepairList.action';</script>");
        }
        //跳转
        else {
            out.printf("<script language='javascript'>alert('上报维修信息错误，请重修上报！');window.location='RepairList.action';</script>");
        }
        out.flush();out.close();return SUCCESS;

    }

    //判断是否空值
    private boolean isInvalid(String value) {
        return (value == null || value.length() == 0);
    }

    //测试
    public static void main(String[] args) {
        System.out.println();
    }

    public String getRepair_student() {
        return repair_student;
    }

    public void setRepair_student(String repair_student) {
        this.repair_student = repair_student;
    }
}
