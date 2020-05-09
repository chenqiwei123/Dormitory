package com.dao;

import com.bean.NoticeBean;
import com.db.DBHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class NoticeDao {


    //获取指定ID的实体Bean
    public List<NoticeBean> GetBean(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time1 = df.format(new Date());// new Date()为获取当前系统时间
        String sql="select * from notice where '"+time1+"'<date";
        System.out.println(sql);
        Statement stat = null;
        ResultSet rs = null;
        Connection conn = new DBHelper().getConn();
        NoticeBean cnbean=new NoticeBean();
        try{
            stat = conn.createStatement();
            rs = stat.executeQuery(sql);
            while(rs.next()){
//                cnbean.setTeacher_ID(rs.getInt("Teacher_ID"));
                cnbean.setInfo(rs.getString("info"));
                cnbean.setDate(rs.getString("date"));

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
        return Collections.singletonList(cnbean);
    }

    //添加
    public void Add(NoticeBean cnbean){
        String sql="insert into notice (";
        sql+="info,date";
        sql+=") values(";
        sql+="'"+cnbean.getInfo()+"','"+cnbean.getDate()+"'";
        sql+=")";
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
    //修改

    //删除
//    public void Delete(String strwhere){
//        String sql="delete Teacher where ";
//        sql+=strwhere;
//        Statement stat = null;
//        ResultSet rs = null;
//        Connection conn = new DBHelper().getConn();
//        try{
//            stat = conn.createStatement();
//            stat.executeUpdate(sql);
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
//    }


    //判断是否空值
    private boolean isInvalid(String value) {
        return (value == null || value.length() == 0);
    }

    //测试
    public static void main(String[] args) {
        System.out.println("");
    }
//    public static int compare_date(String DATE1, String DATE2) {
//
//
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
//        try {
//            Date dt1 = df.parse(DATE1);
//            Date dt2 = df.parse(DATE2);
//            if (dt1.getTime() > dt2.getTime()) {
//                System.out.println("dt1 在dt2前");
//                return 1;
//            } else if (dt1.getTime() < dt2.getTime()) {
//                System.out.println("dt1在dt2后");
//                return -1;
//            } else {
//                return 0;
//            }
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }
//        return 0;
//    }

}

