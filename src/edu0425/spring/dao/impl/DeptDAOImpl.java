package edu0425.spring.dao.impl;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

import edu0425.spring.dao.DeptDAO;
import edu0425.spring.vo.DeptInfo;

public class DeptDAOImpl implements DeptDAO {
	private static String url ="jdbc:mysql://localhost:3306/edu20200415";
	private static String user="root";
	private static String password="root";
	

	@Override
	public List<DeptInfo> getDeptList() {
		List<DeptInfo> deptList = new ArrayList<DeptInfo>();
		try {
		Class.forName("com.mysql.jdbc.Driver");
		java.sql.Connection conn =(Connection)DriverManager.getConnection(url,user,password);
		Statement stmt = (Statement) conn.createStatement();
		
		
		
		ResultSet rs = stmt.executeQuery("select * from dept");
		while(rs.next()) {
			DeptInfo dept = new DeptInfo(rs.getInt("deptno"),rs.getString("dname"),rs.getString("loc"));
			
			deptList.add(dept);
		}

		stmt.close();
		conn.close();
	  }catch(Exception e) {
		  e.printStackTrace();
	  }


		// TODO Auto-generated method stub
		return deptList;
	}

	@Override
	public DeptInfo getDeptById(Integer deptno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getDeptCount() {
				
			Integer cnt = 0 ;
			try
			{Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection conn =(Connection)DriverManager.getConnection(url,user,password);
			Statement stmt = (Statement) conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("select count(*) as cnt from dept");
			while(rs.next()) {
				cnt = rs.getInt("cnt");
				}
			stmt.close();
			conn.close();
			}catch(Exception e) {
				  e.printStackTrace();
			  }
			
			 
		return cnt;
		
	}

}
