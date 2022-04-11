package edu0425.spring.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu0425.spring.service.DeptService;
import edu0425.spring.service.impl.DeptServiceImpl;
import edu0425.spring.vo.DeptInfo;

public class DeptServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//1.实现Servelet接口
	//2.继承GenericServlet实现servlet方法
	//3继承HttpServelet 实现 doget dopost
      private DeptService deptService;
      //http://localhost:8080/edu025/demo/dept.html
      protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
    	  deptService = new DeptServiceImpl();
    	  List<DeptInfo> list = deptService.getDeptList();
    	  Integer cnt = deptService.getDeptCount();
    	  req.setAttribute("cnt", cnt);
    	  req.setAttribute("list", list);
    	  req.getRequestDispatcher("/dept.jsp").forward(req, resp);
      }
      protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
    	
    	  super.doPost(req, resp);
    	  }
}
