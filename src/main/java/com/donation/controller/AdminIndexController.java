package com.donation.controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.donation.constant.BloodTypeEnum;
import com.donation.constant.LoginVariable;
import com.donation.entity.Users;
import com.donation.query.LayUIQuery;
import com.donation.service.UsersService;
import com.donation.utils.EmailUtil2;
import com.donation.vo.Email;
import com.donation.vo.LayUIVO;

@Controller
@RequestMapping("/admin")
public class AdminIndexController {
	@Autowired
	private UsersService usersService;
	
	@Autowired
	EmailUtil2 sendEmail;
	
	@RequestMapping("/index")
	public String adminIndex(){
		return "admin/index";
	}
	
	@RequestMapping("/main")
	public String adminMain(){
		return "admin/main";
	}
	
	@RequestMapping("/users")
	public String adminUsers(){
		return "admin/user_list";
	}
	
	@RequestMapping("/articles")
	public String adminArticles(){
		return "admin/article_list";
	}
	
	@RequestMapping("/consult")
	public String adminConsult(){
		return "admin/consult_list";
	}
	
	@RequestMapping("/activity")
	public String adminActivity(){
		return "admin/activity_list";
	}
	
	@RequestMapping("/addUser")
	public String adminAddUser(){
		return "admin/add_user";
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public LayUIVO getUserList(LayUIQuery query){
		query.setSkipRows( (query.getPage()-1)*query.getLimit() );	//计算跳过数据库前多少条数据
		List<Users> list =  usersService.getUserList(query);
		int total = usersService.getUserTotal(query);
		return LayUIVO.data(total, list);
	}
	
	@RequestMapping("/addOneUser")
	@ResponseBody
	public String adminAddUser(Users user){
		user.setStatus(3);
		int flag = usersService.addOne(user);
		if(flag < 0){
			return "添加失败，请稍后再试";
		}
		return "添加成功";
	}
	
	@RequestMapping("/editUser")
	public String adminEditUser(Long id, HttpServletRequest request){
		request.setAttribute("editUserId", id);
		return "admin/edit_user";
	}
	
	@RequestMapping("/getOneUser")
	@ResponseBody
	public Users adminGetOneUser(Long id){
		return usersService.getUserById(id);
	}
	
	@RequestMapping("/editOneUser")
	@ResponseBody
	public String editAddUser(Users user){
		int flag = usersService.updateUserByIdForAdmin(user);
		if(flag <= 0){
			return "修改失败，请稍后再试";
		}
		return "修改成功";
	}
	
	@RequestMapping("/bloodLack")
	public String bloodLack(){
		return "admin/blood_lack";
	}
	
	@RequestMapping("/getLoginUser")
	@ResponseBody
	public Users getLoginUser(HttpServletRequest request){
		Users loginUser = (Users) request.getSession().getAttribute(LoginVariable.LOGIN_USER);
		return loginUser;
	}
	
	@RequestMapping("/sendEmails")
	@ResponseBody
	public String sendEmails(HttpServletRequest request, String cmbProvince, String cmbCity, Integer[] bloodType){
		//Users loginUser = (Users) request.getSession().getAttribute(LoginVariable.LOGIN_USER);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("area", cmbProvince+"省"+cmbCity+"%");
		param.put("bloodType", bloodType);
		String[] list = usersService.getEmailsByAreaAndType(param);
		String tos = StringUtils.join(list, ";");
		Email email = new Email();
		email.setSubject("献血通知");
		email.setAddressee(tos);
		StringBuffer content = new StringBuffer("<h3>"+cmbCity+"现急需");
		for(Integer type : bloodType){
			content.append(BloodTypeEnum.getLabelByValue(type)+"，");
		}
		content.append("请广大爱心人士积极参与献血活动</h3><a href='localhost:8080/map/'>点击查看附近的献血站</a>");
		email.setContent(content.toString());
		try {
			sendEmail.sendMail(email);
		} catch (Exception e) {
			e.printStackTrace();
			return "请稍后再试";
		} 
		return sendEmail.getMessage().toString();
	}

}
