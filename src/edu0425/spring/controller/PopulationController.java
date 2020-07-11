package edu0425.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu0425.common.page.PaginationResult;
import edu0425.spring.service.EmpService;
import edu0425.spring.service.PopulationService;
import edu0425.spring.vo.DeptInfo;
import edu0425.spring.vo.EmpInfo;
import edu0425.spring.vo.PlayerInfo;
import edu0425.spring.vo.PopulationInfo;

@Controller
@RequestMapping("/popu")
public class PopulationController {
	@Autowired
	 private PopulationService popuService;
	
	@RequestMapping(value = "/page",method =RequestMethod.GET)
	@ResponseBody
	public PaginationResult<List<PopulationInfo>> getPage(Integer pageIndex, Integer pageSize){
		return popuService.getPopuPage(pageIndex,pageSize);
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String getMain(ModelMap modelMap, Integer pageIndex, Integer pageSize) {
		modelMap.put("pageIndex", pageIndex);
		modelMap.put("pageSize", pageSize);
		return "popu";
	}
}