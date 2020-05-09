package com.dao;

import com.bean.Repair_addBean;
import com.bean.TeacherBean;
import com.db.DBHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RepairDao {
    //��ȡ�б�
//    public List<TeacherBean> GetList(String strwhere,String strorder){
//        String sql="select * from repair";
//        if(!(isInvalid(strwhere)))
//        {
//            sql+=" where "+strwhere;
//        }
//        if(!(isInvalid(strorder)))
//        {
//            sql+=" order by "+strorder;
//        }
//        Statement stat = null;
//        ResultSet rs = null;
//        Connection conn = new DBHelper().getConn();
//        List<TeacherBean> list=new ArrayList<TeacherBean>();
//        try{
//            stat = conn.createStatement();
//            rs = stat.executeQuery(sql);
//            while(rs.next()){
//                TeacherBean cnbean=new TeacherBean();
//                cnbean.setTeacher_ID(rs.getInt("Teacher_ID"));
//                cnbean.setTeacher_Username(rs.getString("Teacher_Username"));
//                cnbean.setTeacher_Password(rs.getString("Teacher_Password"));
//                cnbean.setTeacher_Name(rs.getString("Teacher_Name"));
//                cnbean.setTeacher_Sex(rs.getString("Teacher_Sex"));
//                cnbean.setTeacher_Tel(rs.getString("Teacher_Tel"));
//                list.add(cnbean);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (conn != null)
//                    conn.close();
//                if (stat != null)
//                    stat.close();
//                if (rs != null)
//                    rs.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return list;
//    }


    //��ȡָ��ID��ʵ��Bean
    public static List<Repair_addBean> GetBean(String id){
        String sql="select * from repair where repair_student='"+id+"'";
        System.out.println("sql:"+sql);
        Statement stat = null;
        ResultSet rs = null;
        Connection conn = new DBHelper().getConn();
        List<Repair_addBean> list=new ArrayList<Repair_addBean>();
        try{
            stat = conn.createStatement();
            rs = stat.executeQuery(sql);
            while(rs.next()){
                Repair_addBean cnbean=new Repair_addBean();
//                cnbean.setTeacher_ID(rs.getInt("Teacher_ID"));
                cnbean.setRepair_address(rs.getString("repair_address"));
                cnbean.setRepair_info(rs.getString("repair_info"));
                cnbean.setRepair_cost(rs.getString("repair_cost"));
                cnbean.setRepair_status(rs.getString("repair_status"));
                cnbean.setRepair_time(rs.getString("repair_time"));
                cnbean.setRepair_tel(rs.getString("repair_tel"));
                System.out.println(cnbean.getRepair_time());
                list.add(cnbean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
                if (stat != null)
                    stat.close();
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static void main(String[] args) {
//        Date date = new Date(System.currentTimeMillis());
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        //��ǰ�˻����Լ�ģ��һ�����ڸ�ʽ��תΪString����
//        String dateStr = format.format(date);
//        Repair_addBean cnbean=new Repair_addBean();
//        cnbean.setRepair_address("123");
//        cnbean.setRepair_info("123");
//        cnbean.setRepair_tel("123");
//        cnbean.setRepair_time(dateStr);
//        cnbean.setRepair_cost("123");
//        cnbean.setRepair_cost("123");
//        Add(cnbean);
        GetBean("12");
    }

    //���
    public static int Add(Repair_addBean cnbean){
        String sql="insert into repair (";
        sql+="repair_address,repair_tel,repair_cost,repair_info,repair_status,repair_time,repair_student";
        sql+=") values(";
        sql+="'"+cnbean.getRepair_address()+"','"+cnbean.getRepair_tel()+"','"+cnbean.getRepair_cost()+"','"+cnbean.getRepair_info()+"','"+cnbean.getRepair_status()+"','"+cnbean.getRepair_time()+"','"+cnbean.getRepair_student()+"'";
        sql+=")";
        Statement stat = null;
        ResultSet rs = null;
        int result=-1;
        Connection conn = new DBHelper().getConn();
        try{
            stat = conn.createStatement();
            result=stat.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
                if (stat != null)
                    stat.close();
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    //�޸�
    public void Update(TeacherBean cnbean){
        String sql="update Teacher set ";
        sql+="Teacher_Username='"+cnbean.getTeacher_Username()+"',";
        sql+="Teacher_Password='"+cnbean.getTeacher_Password()+"',";
        sql+="Teacher_Name='"+cnbean.getTeacher_Name()+"',";
        sql+="Teacher_Sex='"+cnbean.getTeacher_Sex()+"',";
        sql+="Teacher_Tel='"+cnbean.getTeacher_Tel()+"'";

        sql+=" where Teacher_ID='"+cnbean.getTeacher_ID()+"'";
        Statement stat = null;
        ResultSet rs = null;
        Connection conn = new DBHelper().getConn();
        try{
            stat = conn.createStatement();
            stat.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
                if (stat != null)
                    stat.close();
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //ɾ��
    public void Delete(String strwhere){
        String sql="delete Teacher where ";
        sql+=strwhere;
        Statement stat = null;
        ResultSet rs = null;
        Connection conn = new DBHelper().getConn();
        try{
            stat = conn.createStatement();
            stat.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
                if (stat != null)
                    stat.close();
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    //�ж��Ƿ��ֵ
    private boolean isInvalid(String value) {
        return (value == null || value.length() == 0);
    }

    //����
//    public static void main(String[] args) {
//        System.out.println("");
//    }

}

