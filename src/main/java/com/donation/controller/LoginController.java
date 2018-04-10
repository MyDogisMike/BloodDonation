package com.donation.controller;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.donation.constant.LoginVariable;
import com.donation.constant.UserStatusEnum;
import com.donation.entity.Users;
import com.donation.service.UsersService;
import com.donation.utils.EmailConfig;
import com.donation.utils.EncryptUtil;
import com.donation.utils.RegExpValidator;
import com.donation.utils.SendEmailUtil;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
	@Autowired
	private UsersService usersService;
	@Autowired
	private EmailConfig emailConfig;
	
	
	/**
	 * 用户可以根据用户名或邮箱加密码进行登录
	 * @param name
	 * @param password
	 * @return
	 */
	@PostMapping(value = "/in")
	@ResponseBody
	public String LonginIn(String login_name, String login_password, String address, HttpServletRequest request){
		Users user = null;
		if(RegExpValidator.isEmail(login_name)){	//如果是邮箱就根据邮箱查找用户
			user = usersService.getUserByEmail(login_name);
		}else{
			user = usersService.getUserByName(login_name);
		}
		if(user == null){
			return "用户不存在";
		}
		if(!EncryptUtil.encrypt32(login_password, user.getSalt()).equals(user.getPassword())){	//判断密码是否相等
			return "密码错误";
		}
		if(user.getStatus() == UserStatusEnum.inactive.getValue()){	//如果用户未激活
			String securityCode = EncryptUtil.encrypt32(UUID.randomUUID().toString().replace("-", ""), EncryptUtil.getSalt());
			user.setSecurityCode(securityCode);
			user.setProofTime(new Date());
			usersService.updateSecurityCodeById(user);
			String subject = "注册用户激活邮件";
			String content = "<h3>若不是本人操作，请勿随意点击下面链接，请在30分钟内激活，点击下面的链接进行用户激活</h3><a href='http://localhost"
					+":"+request.getLocalPort()+"/reg/activateUser?"
					+ "email="+user.getEmail()
					+ "&securityCode="+securityCode+"'>"
					+ emailConfig.getTemplate()+"/reg/activateUser?email="+user.getEmail()
					+ "&emailVerifyCode="+securityCode
					+"</a>";
			try{
				String checked = SendEmailUtil.sendEmailUtil.sendSimpleMail(user.getEmail(), subject, content);
			}catch(Exception e){
				return "邮箱发送错误，请稍后再试";
			}
		}
		if(address != null && !"".equals(address)){
			user.setAddress(address);
			if(usersService.updateUserById(user) < 0){
				return "网络异常，请稍后再试";
			}
		}
		
		HttpSession session = request.getSession();
		session.setAttribute(LoginVariable.LOGIN_USER, user); //这个user的loginTime会不会改变
		user.setLastLoginTime(new Date());
		usersService.updateUserById(user);	
		return "success";
	}
	
	@RequestMapping(value = "/out")
	public String LoginOut(HttpServletRequest request){
		request.getSession().removeAttribute("user");
		return "/";
	}

}
