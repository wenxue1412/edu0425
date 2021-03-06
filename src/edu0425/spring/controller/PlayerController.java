package edu0425.spring.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import edu0425.common.page.PaginationResult;
import edu0425.spring.service.PlayerService;
import edu0425.spring.vo.PlayerInfo;

@Controller
@RequestMapping("/player")
public class PlayerController {
	
	@Autowired
	private PlayerService playService;
	
	@RequestMapping(value = "/page",method =RequestMethod.GET)
	@ResponseBody
	public PaginationResult<List<PlayerInfo>>getPage(Integer pageIndex, Integer pageSize){
		return playService.getPlayerPage(pageIndex,pageSize);
	}
	@RequestMapping(value = "/info/{pid}",method =RequestMethod.GET)
	@ResponseBody
	public PlayerInfo getPlayer(@PathVariable Integer pid) {
		//TODO
		return playService.getPlayerById(pid);
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String getMain(ModelMap modelMap, Integer pageIndex, Integer pageSize) {
		modelMap.put("pageIndex", pageIndex);
		modelMap.put("pageSize", pageSize);
		
		//获取当前登录用户
		Subject subject = SecurityUtils.getSubject();
		String msg="未登录";
		if(null != subject.getPrincipal()) {
			msg="欢迎："+subject.getPrincipal();
		}
		modelMap.put("msg", msg);
		return "player";
	}
	
	@RequestMapping(value = "/chartofage", method = RequestMethod.GET)
	@ResponseBody
	public JSONArray getChartOfAge() {
		return playService.getJsonAge();
	}
	
	@RequestMapping(value = "/chartofoveralltoage", method = RequestMethod.GET)
	@ResponseBody
	public JSONArray getChartOfOverallToAge() {
		return playService.getJsonOverallToAge();
		
	}
	
	@RequestMapping(value = "/chartofoverall", method = RequestMethod.GET)
	@ResponseBody
	JSONObject getJsonAvgOfOverallByNation() {
		return playService.getJsonAvgOfOverallByNation();
	}
}
