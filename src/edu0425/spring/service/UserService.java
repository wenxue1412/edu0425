package edu0425.spring.service;

import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONArray;

import edu0425.spring.vo.LoginInfo;
import edu0425.spring.vo.UserInfo;

public interface UserService {
	
	UserInfo getUserByLoginId(String loginId);
	
	boolean loginValid(LoginInfo login, HttpSession session);
	
	JSONArray getPermissions(String loginId);
}
