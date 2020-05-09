package com.dao;

import com.db.DBHelper;
import com.bean.TBBean;

import java.util.*;
import java.sql.*;

public class TBDao {

	public static void main(String[] args) {
		GetList("TB_TeacherID='1'","Building_Name");
	}
	
	//��ȡ�б�
	public static List<TBBean> GetList(String strwhere,String strorder){
		String sql="select * from TB,Teacher,Building where TB_TeacherID=Teacher_ID and TB_BuildingID=Building_ID";
		if(!(isInvalid(strwhere)))
		{
			sql+=" and "+strwhere;
		}
		if(!(isInvalid(strorder)))
		{
			sql+=" order by "+strorder;
		}
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		List<TBBean> list=new ArrayList<TBBean>();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				TBBean cnbean=new TBBean();
				cnbean.setTB_ID(rs.getInt("TB_ID"));
				cnbean.setTB_TeacherID(rs.getInt("TB_TeacherID"));
				cnbean.setTB_BuildingID(rs.getInt("TB_BuildingID"));
				cnbean.setTeacher_Name(rs.getString("Teacher_Name"));
				cnbean.setTeacher_Sex(rs.getString("Teacher_Sex"));
				cnbean.setTeacher_Tel(rs.getString("Teacher_Tel"));
				cnbean.setTeacher_Username(rs.getString("Teacher_Username"));
				cnbean.setBuilding_Name(rs.getString("Building_Name"));
				System.out.println(cnbean.getBuilding_Name());
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
	
	//��ȡָ��ID��ʵ��Bean
	public TBBean GetBean(int id){
		String sql="select * from TB where TB_ID="+id;
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		TBBean cnbean=new TBBean();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				cnbean.setTB_ID(rs.getInt("TB_ID"));
				cnbean.setTB_TeacherID(rs.getInt("TB_TeacherID"));
				cnbean.setTB_BuildingID(rs.getInt("TB_BuildingID"));
				
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
		return cnbean;
	}
	
	//���
	public void Add(TBBean cnbean){
		String sql="insert into TB (";
		sql+="TB_TeacherID,TB_BuildingID";
		sql+=") values(";
		sql+="'"+cnbean.getTB_TeacherID()+"','"+cnbean.getTB_BuildingID()+"'";
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
	public void Update(TBBean cnbean){
		String sql="update TB set ";
		sql+="TB_TeacherID='"+cnbean.getTB_TeacherID()+"',";
		sql+="TB_BuildingID='"+cnbean.getTB_BuildingID()+"'";
		
		sql+=" where TB_ID='"+cnbean.getTB_ID()+"'";
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
		String sql="delete TB where ";
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
	private static boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}
	
	//����
//	public static void main(String[] args) {
//		System.out.println("");
//	}
	
}

