package edu0425.spring.dao.impl;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import edu0425.spring.dao.EmpDAO;
import edu0425.spring.vo.EmpInfo;

public class EmpDAOImpl implements EmpDAO {
	private static String url = "jdbc:mysql://localhost:3306/edu20200415";
	private static String user = "root";
	private static String password = "root";

	@Override
	public List<EmpInfo> getEmpList() {
		List<EmpInfo> empList = new ArrayList<EmpInfo>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = (Connection) DriverManager.getConnection(url, user, password);
			Statement stmt = (Statement) conn.createStatement();

			ResultSet rs1 = stmt.executeQuery("select * from emp");
			while (rs1.next()) {
				EmpInfo emp = new EmpInfo(rs1.getInt("empno"), rs1.getString("ename"), rs1.getString("job"),
						rs1.getInt("mgr"), rs1.getDate("hiredate"), rs1.getInt("sal"), rs1.getInt("deptno"));

				empList.add(emp);
			}

			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// TODO Auto-generated method stub
		return empList;
	}

	@Override
	public EmpInfo getEmpById(Integer emptno) {
		return null;
	}

	@Override
	public Integer getEmpCount() {
		Integer cnt = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = (Connection) DriverManager.getConnection(url, user, password);
			Statement stmt = (Statement) conn.createStatement();

			ResultSet rs = stmt.executeQuery("select count(*) as cnt from emp");
			while (rs.next()) {
				cnt = rs.getInt("cnt");
			}
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cnt;
	}

}
