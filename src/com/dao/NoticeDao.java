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


    //��ȡָ��ID��ʵ��Bean
    public List<NoticeBean> GetBean(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
        String time1 = df.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ��
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

    //���
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
    //�޸�

    //ɾ��
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


    //�ж��Ƿ��ֵ
    private boolean isInvalid(String value) {
        return (value == null || value.length() == 0);
    }

    //����
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
//                System.out.println("dt1 ��dt2ǰ");
//                return 1;
//            } else if (dt1.getTime() < dt2.getTime()) {
//                System.out.println("dt1��dt2��");
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

