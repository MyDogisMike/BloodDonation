package com.donation.controller;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.donation.constant.UserStatusEnum;
import com.donation.entity.Users;
import com.donation.service.UsersService;
import com.donation.utils.EmailConfig;
import com.donation.utils.EncryptUtil;
import com.donation.utils.SendEmailUtil;

@Controller
@RequestMapping(value = "/reg")
public class RegisterController {
	@Autowired
	private UsersService usersService;
	@Autowired
	private EmailConfig emailConfig;
	
	@GetMapping
    public String index() {
        return "register";
    }
	
	@PostMapping("/add")
	@ResponseBody
	public String add(Users user ,HttpServletRequest request){
		//给新用户设置默认的值
		user.setBirthday("1995-12-18");
		user.setBloodType(5);
		user.setGender(0);
		int flag = usersService.addOne(user);
		if(flag > 0){
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
				return "emailError";
			}
			return "success";
		}else{
			return "regError";
		}
	}
	
	@RequestMapping("/activateUser")
	public String activateCustomer(String email, String securityCode, Model model, HttpServletRequest request){
		Users user = usersService.getUserByEmail(email);
		if(user != null && user.getSecurityCode().equals(securityCode)){

			if(user.getStatus() == UserStatusEnum.inactive.getValue()){
				Date nowDate = new Date();
				//限制30分钟内（1800000）操作完成
				if(nowDate.getTime()-user.getProofTime().getTime()>1800000){
					String newSecurityCode = EncryptUtil.encrypt32(UUID.randomUUID().toString().replace("-", ""), EncryptUtil.getSalt());
					user.setSecurityCode(newSecurityCode);
					user.setProofTime(new Date());
					usersService.updateSecurityCodeById(user);
					String subject = "注册用户激活邮件";
					String content = "<h3>若不是本人操作，请勿随意点击下面链接，请在30分钟内激活，点击下面的链接进行用户激活</h3><a href='http://localhost"
							+":"+request.getLocalPort()+"/reg/activateUser?"
							+ "email="+user.getEmail()
							+ "&securityCode="+newSecurityCode+"'>"
							+ emailConfig.getTemplate()+"/reg/activateUser?email="+user.getEmail()
							+ "&emailVerifyCode="+newSecurityCode
							+"</a>";
					try{
						String checked = SendEmailUtil.sendEmailUtil.sendSimpleMail(user.getEmail(), subject, content);
					}catch(Exception e){
						model.addAttribute("msg", "网络异常，请检查网络后操作！");
						return "/activate";
					}
					model.addAttribute("msg", "已超过验证时间，已重新发送验证邮件！");
					return "/activate";
				}
				user.setStatus(UserStatusEnum.activated.getValue());
				usersService.updateUserStatusById(user);
				model.addAttribute("msg", "已验证成功，可以登录！");
			}else {
				model.addAttribute("msg", "已验证，请不要重复验证！");
			}
		}else{
			model.addAttribute("msg", "未知错误，请联系网站工作人员！");
			
		}
		return "/activate";
	}
	
	@RequestMapping("/validateEmail")
	@ResponseBody
	public String validateEmail(String email){
		if(usersService.getUserByEmail(email) == null){
			return "";
		}
		return "error";
	}
	
	@RequestMapping("/validateName")
	@ResponseBody
	public String validateName(String name){
		if(usersService.getUserByName(name) == null){
			return "";
		}
		return "error";
	}
}
