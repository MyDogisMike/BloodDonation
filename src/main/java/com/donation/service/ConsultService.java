package com.donation.service;

import java.util.List;

import com.donation.entity.Consult;
import com.donation.query.LayUIQuery;
import com.donation.vo.LayUIVO;

public interface ConsultService {
	public int addConsult(Consult consult);
	
	public List<Consult> getListByUserId(Long userId);
	
	public Consult getConsultById(Long consultId);
	
	public int deleteConsultById(Long consultId);
	
	public LayUIVO getConsultList(LayUIQuery query);
	
	public int updateConsult(Consult consult);
}
