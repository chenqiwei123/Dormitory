package com.dao;

import com.bean.DataNumber;
import com.bean.DomitoryBean;
import com.db.DBHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DomitoryDao {
	public static void main(String[] args) {
		GetList("Domitory_BuildingID='1'","Domitory_Name");
	}
	//获取列表
	public static List<DomitoryBean> GetList(String strwhere,String strorder){
		String sql="select * from Domitory,Building where Domitory.Domitory_BuildingID=Building.Building_ID";
		if(!(isInvalid(strwhere)))
		{
			sql+=" and "+strwhere;
		}
		if(!(isInvalid(strorder)))
		{
			sql+=" order by "+strorder;
		}
		System.out.println("sql:"+sql);
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		List<DomitoryBean> list=new ArrayList<DomitoryBean>();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				DomitoryBean cnbean=new DomitoryBean();
				cnbean.setDomitory_ID(rs.getInt("Domitory_ID"));
				cnbean.setDomitory_BuildingID(rs.getInt("Domitory_BuildingID"));
				cnbean.setDomitory_Name(rs.getString("Domitory_Name"));
				cnbean.setDomitory_Type(rs.getString("Domitory_Type"));
				cnbean.setDomitory_Number(rs.getString("Domitory_Number"));
				cnbean.setDomitory_Tel(rs.getString("Domitory_Tel"));
				cnbean.setBuilding_Name(rs.getString("Building_Name"));
				System.out.println(cnbean.getDomitory_Number());
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

	//查询数据库数量
	public static List<DataNumber> GetAllListCount() throws SQLException {
		String sql="select * from Domitory";
		List<DataNumber> list=new ArrayList<DataNumber>();
		Connection connection=new DBHelper().getConn();
		Statement statement=null;
		ResultSet rs=null;
		int count=0;
		statement=connection.createStatement();
		rs=statement.executeQuery(sql);

		while (rs.next()){
			count+=1;
		}
		DataNumber dataNumber=new DataNumber();
		dataNumber.setDorm_Row(count);
		list.add(dataNumber);
		System.out.println("count:"+count);
		if (connection!=null) connection.close();
		if (statement!=null) statement.close();
		if (rs!=null) rs.close();
		return list;
	}
	public static List<DomitoryBean> GetList(String strwhere,String strorder,int a){
		String sql="select * from Domitory,Building where Domitory.Domitory_BuildingID=Building.Building_ID";
		if(!(isInvalid(strwhere)))
		{
			sql+=" and "+strwhere;
		}
		if(!(isInvalid(strorder)))
		{
			sql+=" order by "+strorder;
		}
		a=a*16;
		int b=16;
		sql+=" LIMIT "+a+","+b;
		System.out.println("sql:"+sql);
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		List<DomitoryBean> list=new ArrayList<DomitoryBean>();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				DomitoryBean cnbean=new DomitoryBean();
				cnbean.setDomitory_ID(rs.getInt("Domitory_ID"));
				cnbean.setDomitory_BuildingID(rs.getInt("Domitory_BuildingID"));
				cnbean.setDomitory_Name(rs.getString("Domitory_Name"));
				cnbean.setDomitory_Type(rs.getString("Domitory_Type"));
				cnbean.setDomitory_Number(rs.getString("Domitory_Number"));
				cnbean.setDomitory_Tel(rs.getString("Domitory_Tel"));
				cnbean.setBuilding_Name(rs.getString("Building_Name"));
				System.out.println(cnbean.getDomitory_Number());
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


	public List<DomitoryBean> GetList1(String strorder){
		String sql="select * from Domitory";
		if(!(isInvalid(strorder)))
		{
			sql+=" order by "+strorder;
		}
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		List<DomitoryBean> list=new ArrayList<DomitoryBean>();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				DomitoryBean cnbean=new DomitoryBean();
				cnbean.setDomitory_ID(rs.getInt("Domitory_ID"));
//				cnbean.setDomitory_BuildingID(rs.getInt("Domitory_BuildingID"));
				cnbean.setDomitory_Name(rs.getString("Domitory_Name"));
				cnbean.setDomitory_Type(rs.getString("Domitory_Type"));
				cnbean.setDomitory_Number(rs.getString("Domitory_Number"));
				cnbean.setDomitory_Tel(rs.getString("Domitory_Tel"));
//				cnbean.setBuilding_Name(rs.getString("Building_Name"));
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
	
	//获取指定ID的实体Bean
	public DomitoryBean GetBean(int id){
		String sql="select * from Domitory,Building where Domitory_BuildingID=Building_ID and Domitory_ID="+id;
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		DomitoryBean cnbean=new DomitoryBean();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				cnbean.setDomitory_ID(rs.getInt("Domitory_ID"));
				cnbean.setDomitory_BuildingID(rs.getInt("Domitory_BuildingID"));
				cnbean.setDomitory_Name(rs.getString("Domitory_Name"));
				cnbean.setDomitory_Type(rs.getString("Domitory_Type"));
				cnbean.setDomitory_Number(rs.getString("Domitory_Number"));
				cnbean.setDomitory_Tel(rs.getString("Domitory_Tel"));
				cnbean.setBuilding_Name(rs.getString("Building_Name"));
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
	
	//添加
	public void Add(DomitoryBean cnbean){
		String sql="insert into Domitory (";
		sql+="Domitory_BuildingID,Domitory_Name,Domitory_Type,Domitory_Number,Domitory_Tel";
		sql+=") values(";
		sql+="'"+cnbean.getDomitory_BuildingID()+"','"+cnbean.getDomitory_Name()+"','"+cnbean.getDomitory_Type()+"','"+cnbean.getDomitory_Number()+"','"+cnbean.getDomitory_Tel()+"'";
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
	public void Update(DomitoryBean cnbean){
		String sql="update Domitory set ";
		sql+="Domitory_BuildingID='"+cnbean.getDomitory_BuildingID()+"',";
		sql+="Domitory_Name='"+cnbean.getDomitory_Name()+"',";
		sql+="Domitory_Type='"+cnbean.getDomitory_Type()+"',";
		sql+="Domitory_Number='"+cnbean.getDomitory_Number()+"',";
		sql+="Domitory_Tel='"+cnbean.getDomitory_Tel()+"'";
		
		sql+=" where Domitory_ID='"+cnbean.getDomitory_ID()+"'";
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
	//删除
	public void Delete(String strwhere){
		String sql="delete Domitory where ";
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

	
	//判断是否空值
	private static boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}
	
	//测试
//	public static void main(String[] args) {
//		System.out.println("");
//	}
	
}

