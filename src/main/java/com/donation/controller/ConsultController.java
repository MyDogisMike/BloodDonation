package com.donation.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.donation.constant.ConsultStatusEnum;
import com.donation.constant.LoginVariable;
import com.donation.entity.Consult;
import com.donation.entity.Users;
import com.donation.query.LayUIQuery;
import com.donation.service.ConsultService;
import com.donation.vo.LayUIVO;

@Controller
@RequestMapping("/consult")
public class ConsultController {
	@Autowired
	private ConsultService consultService;	
	
	/**
	 * 添加咨询
	 * @param request
	 * @param consult
	 * @return
	 */
	@PostMapping("/addConsult")
	@ResponseBody
	public String addConsult(HttpServletRequest request, Consult consult){
		Users loginUser = (Users) request.getSession().getAttribute(LoginVariable.LOGIN_USER);
		consult.setUserId(loginUser.getId());
		consult.setTime(new Date());
		consult.setStatus(ConsultStatusEnum.notReply.getValue());
		consultService.addConsult(consult);
		if(consult.getId() != null){
			return "添加成功";
		}
		return "请稍后再试";
	}
	
	@PostMapping("/showConsult")
	@ResponseBody
	public List<Consult> showConsult(HttpServletRequest request){
		Users loginUser = (Users) request.getSession().getAttribute(LoginVariable.LOGIN_USER);
		return consultService.getListByUserId(loginUser.getId());
	}
	
	@PostMapping("/checkConsult")
	@ResponseBody
	public Consult checkConsult(Long consultId){
		return consultService.getConsultById(consultId);
	}
	
	@PostMapping("/deleteConsult")
	@ResponseBody
	public String deleteConsult(Long consultId){
		int flag = consultService.deleteConsultById(consultId);
		if (flag > 0){
			return "删除成功";
		}
		return "删除失败，请稍后再试";
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public LayUIVO getConsultList(LayUIQuery query){
		return consultService.getConsultList(query);
	}
	
	@RequestMapping("/replyConsult")
	public String replyConsult(Long id, HttpServletRequest request){
		request.setAttribute("replyConsultId", id);
		return "admin/reply_consult";
	}

	@RequestMapping("/getOneConsult")
	@ResponseBody
	public Consult getOneConsult(Long id){
		return consultService.getConsultById(id);
	}
	
	@RequestMapping("/replyOneConsult")
	@ResponseBody
	public String replyOneConsult(Consult consult){
		consult.setStatus(1);
		int flag = consultService.updateConsult(consult);
		if(flag > 0){
			return "回复成功";
		}
		return "回复失败，请稍后再试";
	}
}
