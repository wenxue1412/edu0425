package edu0425.spring.controller;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

import edu0425.common.util.MD5Util;
import edu0425.spring.service.UserService;
import edu0425.spring.vo.LoginInfo;
import edu0425.spring.vo.UserInfo;


@RequestMapping("/")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/user/{loginId}", method = RequestMethod.GET)
	@ResponseBody
	public UserInfo getUserInfoByLoginId(@PathVariable String loginId) {
		return userService.getUserByLoginId(loginId);
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(ModelMap modelMap) {
		modelMap.put("user", new LoginInfo());
		return "login";
	}
	

	public String login(LoginInfo user, HttpSession session, ModelMap modelMap) {
		//todo 濡傛灉鎴愬姛锛岃烦杞埌index椤甸潰锛�
		if(userService.loginValid(user, session)) {
			return "redirect:player/index?pageIndex=1&pageSize=10";
		}
		
		//鍚﹀垯杩斿洖鐧婚檰椤碉紝瀵嗙爜鏄┖锛屾樉绀鸿处鍙锋垨瀵嗙爜閿欒
		user.setPassword(null);
		modelMap.put("user", user);
		modelMap.put("msg", "璐﹀彿鎴栧瘑鐮侀敊璇�");
		return "login";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String login2(LoginInfo user, HttpSession session, ModelMap modelMap) {
		//鑾峰彇褰撳墠鐧诲綍鐢ㄦ埛鐨勭粺涓�鏂规硶
		Subject subject = SecurityUtils.getSubject();
		//灏佽琛ㄥ崟涓彁浜ょ殑鐢ㄦ埛鍚嶅拰瀵嗙爜
		UsernamePasswordToken token = new UsernamePasswordToken(user.getLoginId(),MD5Util.textToMD5U16(user.getPassword()),user.isRemember());
		try {
			//璋冪敤login鏂规硶锛屼紶鍏ュ皝瑁呭ソ鐨則oken锛堜护鐗岋級
			subject.login(token);
			//鐧诲綍鎴愬姛璺宠浆锛�
			return "redirect:player/index?pageIndex=1&pageSize=10";

		}catch(Exception e){
			//鍚﹀垯杩斿洖鐧婚檰椤碉紝瀵嗙爜鏄┖锛屾樉绀鸿处鍙锋垨瀵嗙爜閿欒
			user.setPassword(null);
			modelMap.put("user", user);
			modelMap.put("msg", "璐﹀彿鎴栧瘑鐮侀敊璇�");
			return "login";
		}
	
	}
	//RESTFUL API 鎺ュ彛锛岃繑鍥瀓son鏁版嵁
	@RequestMapping(value="/permission/{loginId}", method = RequestMethod.GET)
	@ResponseBody
	public JSONArray getPermissions(@PathVariable String loginId) {
		//todo 鏍规嵁loginId鏌ヨ杩欎釜鐢ㄦ埛鐨勬墍浠ユ潈闄愬叧閿瓧銆�
		
		return userService.getPermissions(loginId);
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "redirect:login";
	}
	@RequestMapping("/profile/{loginId}")
	public String userProfile(@PathVariable String loginId,ModelMap modelMap) {
		UserInfo user = userService.getUserByLoginId(loginId);
		modelMap.put("user",user);
		return "user_profile";
	}
	
}
