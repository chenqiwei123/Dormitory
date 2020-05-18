package com.dao;

import com.db.DBHelper;
import com.bean.BuildingBean;

import java.util.*;
import java.sql.*;

public class BuildingDao {
	public static void main(String[] args) {
//		GetList("","Building_Name");
		Delete("Building_ID=22");
	}
	
	//��ȡ�б�
	public static List<BuildingBean> GetList(String strwhere,String strorder){
		String sql="select * from Building";
		if(!(isInvalid(strwhere)))
		{
			sql+=" where "+strwhere;
		}
		if(!(isInvalid(strorder)))
		{
			sql+=" order by "+strorder;
		}
		System.out.println("sql:"+sql);
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		System.out.println("conn:"+conn);
		List<BuildingBean> list=new ArrayList<BuildingBean>();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				BuildingBean cnbean=new BuildingBean();
				cnbean.setBuilding_ID(rs.getInt("Building_ID"));
				cnbean.setBuilding_Name(rs.getString("Building_Name"));
				cnbean.setBuilding_Introduction(rs.getString("Building_Introduction"));
				System.out.println(cnbean.getBuilding_ID());
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
	public BuildingBean GetBean(int id){
		String sql="select * from Building where Building_ID="+id;
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		BuildingBean cnbean=new BuildingBean();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				cnbean.setBuilding_ID(rs.getInt("Building_ID"));
				cnbean.setBuilding_Name(rs.getString("Building_Name"));
				cnbean.setBuilding_Introduction(rs.getString("Building_Introduction"));
				
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
	public void Add(BuildingBean cnbean){
		String sql="insert into Building (";
		sql+="Building_Name,Building_Introduction";
		sql+=") values(";
		sql+="'"+cnbean.getBuilding_Name()+"','"+cnbean.getBuilding_Introduction()+"'";
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
	public void Update(BuildingBean cnbean){
		String sql="update Building set ";
		sql+="Building_Name='"+cnbean.getBuilding_Name()+"',";
		sql+="Building_Introduction='"+cnbean.getBuilding_Introduction()+"'";
		
		sql+=" where Building_ID='"+cnbean.getBuilding_ID()+"'";
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
	public static void Delete(String strwhere){
		String sql="delete FROM Building where ";
		sql+=strwhere;
		Statement stat = null;
		ResultSet rs = null;
		System.out.println(sql);
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

