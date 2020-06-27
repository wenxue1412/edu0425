package edu0425.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import edu0425.spring.service.EmpService;
import edu0425.spring.vo.EmpInfo;

@Controller
@RequestMapping("/emp")
public class EmpController {
	@Autowired
	 private EmpService empService;
	
	//http://localhost:8080/edu0425/dept/list
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String empList(ModelMap modelMap, Integer pageIndex, Integer pageSize) {
		
		//modelMap.put("list", empService.getEmpList());
		//modelMap.put("cnt", empService.getEmpCount());
		//WEB-INF/jsp/dept.jsp
		modelMap.put("rs", empService.getEmpPage(pageIndex, pageSize));
		
		return "emp";
	}
	@RequestMapping(value="/add",method = RequestMethod.GET)
	public String getAddPage(ModelMap modelMap) {
		modelMap.put("emp", new EmpInfo());
		return "add_emp";
	}
	
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public String addEmp(EmpInfo emp, ModelMap modelMap) {
		empService.addEmp(emp);
		return "redirect:/emp/list";
	}
	@RequestMapping(value="/delete/{empno}", method = RequestMethod.GET)
	public String deleteEmp(@PathVariable Integer empno, ModelMap modelMap) {
		empService.deleteEmp(empno);
		return "redirect:/emp/list";
		
	}
	@RequestMapping(value="/update/{empno}", method = RequestMethod.GET)
	public String getUpdatePage(@PathVariable Integer empno, ModelMap modelMap) {
		modelMap.put("emp", empService.getEmpById(empno));
		return "update_emp";
	}
	
	@RequestMapping(value="/update/{empno}", method = RequestMethod.POST)
	public String updateEmp(@PathVariable Integer empno,EmpInfo emp, ModelMap modelMap) {
		empService.updateEmp(emp);
		return "redirect:/emp/list";
	}

}