package edu0425.spring.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu0425.common.page.PaginationResult;
import edu0425.spring.service.PopulationService;
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
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String Search(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response, Integer pageIndex, Integer pageSize) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String dname = request.getParameter("fname");
		modelMap.put("pageIndex", pageIndex);
		modelMap.put("pageSize", pageSize);
		popuService.getPopuByDname(dname, pageIndex, pageSize);
		return "search";
	}
}