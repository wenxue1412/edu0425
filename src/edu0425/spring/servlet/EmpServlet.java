package edu0425.spring.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu0425.spring.service.EmpService;
import edu0425.spring.service.impl.EmpServiceImpl;
import edu0425.spring.vo.EmpInfo;

public class EmpServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EmpService empService;
//http://localhost:8080/edu025/demo/dept.html
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
	  empService = new EmpServiceImpl();
	  List<EmpInfo> list = empService.getEmpList();
	  Integer cnt = empService.getEmpCount();
	  req.setAttribute("cnt", cnt);
	  req.setAttribute("list", list);
	  req.getRequestDispatcher("/emp.jsp").forward(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
	
	  super.doPost(req, resp);
	  }

}
