package edu0425.spring.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu0425.spring.interfaces.ValidParam;
import edu0425.spring.service.DeptService;
import edu0425.spring.vo.DeptInfo;

@Controller
@RequestMapping("/dept")
public class DeptController {
	@Autowired
	 private DeptService deptService;
	
	//http://localhost:8080/edu0425/dept/list
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String deptList(ModelMap modelMap, Integer pageIndex, Integer pageSize) {
		
		//modelMap.put("list", deptService.getDeptList());
		//modelMap.put("cnt", deptService.getDeptCount());
		//WEB-INF/jsp/dept.jsp
		modelMap.put("rs", deptService.getDeptPage(pageIndex, pageSize));
		
		return "dept";
	}
	@RequestMapping(value="/add",method = RequestMethod.GET)
	public String getAddPage(ModelMap modelMap) {
		modelMap.put("dept", new DeptInfo());
		return "add_dept";
	}
	/*
	 * @Valid 为验证器vadidator需要的注解
	 * @ValidParam 为自定义的注解，也用于标记参数是否被校验，由自定义的校验类的方法进行校验
	 */
	
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public String addDept(@Valid @ValidParam DeptInfo dept, ModelMap modelMap) {
//		 try {
//	            CheckIsNull.doValidator(dept);
//	            deptService.addDept(dept);
//	            return "redirect:/dept/list";
//	        } catch (Exception e) {
//	        	modelMap.put("errMsg", CheckIsNull.handlerExcpetion(e));
//	        	modelMap.put("dept", dept);
//	        	return "add_dept";
//	        }
		deptService.addDept(dept);
		return "redirect:/dept/list";
		
	}
	@RequestMapping(value="/delete/{deptno}", method = RequestMethod.GET)
	public String deleteDept(@PathVariable Integer deptno, ModelMap modelMap) {
		deptService.deleteDept(deptno);
		return "redirect:/dept/list";
		
	}
	@RequestMapping(value="/update/{deptno}", method = RequestMethod.GET)
	public String getUpdatePage(@PathVariable Integer deptno, ModelMap modelMap) {
		modelMap.put("dept", deptService.getDeptById(deptno));
		return "update_dept";
	}
	
	@RequestMapping(value="/update/{deptno}", method = RequestMethod.POST)
	public String updateDept(@PathVariable Integer deptno,DeptInfo dept, ModelMap modelMap) {
		deptService.updateDept(dept);
		return "redirect:/dept/list";
	}

}
