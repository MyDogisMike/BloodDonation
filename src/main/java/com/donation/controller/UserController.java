package com.donation.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.donation.constant.LoginVariable;
import com.donation.entity.Users;
import com.donation.service.UsersService;
import com.donation.utils.EncryptUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UsersService usersService;
	
	@RequestMapping("/info")
	public String getUserInfo(){
		return "user_info";
	}
	
	@PostMapping("/update")
	@ResponseBody
	public String updateUserInfo(Users user, HttpServletRequest request){
		Users loginUser = (Users) request.getSession().getAttribute(LoginVariable.LOGIN_USER);
		user.setId(loginUser.getId());
		int flag = usersService.updateUserById(user);
		if(flag > 0){
			loginUser = usersService.getUserByEmail(loginUser.getEmail());
			request.getSession().setAttribute(LoginVariable.LOGIN_USER, loginUser);
			return "success";
		}
		return "error";
	}
	
	@PostMapping("/modifyPass")
	@ResponseBody
	public String modifyPass(String oldPass, String newPass, HttpServletRequest request){
		Users loginUser = (Users) request.getSession().getAttribute(LoginVariable.LOGIN_USER);
		if(!EncryptUtil.encrypt32(oldPass, loginUser.getSalt()).equals(loginUser.getPassword())){	//判断密码是否相等
			return "原密码错误";
		}
		//修改用户的salt和密码
		loginUser.setSalt(EncryptUtil.getSalt());
		loginUser.setPassword(EncryptUtil.encrypt32(newPass, loginUser.getSalt()));
		int flag = usersService.updateUserById(loginUser);
		if(flag > 0){
			loginUser = usersService.getUserByEmail(loginUser.getEmail());
			request.getSession().setAttribute(LoginVariable.LOGIN_USER, loginUser);
			return "密码修改成功";
		}
		return "未知错误，请稍后再试";
	}
}
