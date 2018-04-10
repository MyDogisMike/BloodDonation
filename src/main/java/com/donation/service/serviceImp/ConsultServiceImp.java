package com.donation.service.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donation.entity.Consult;
import com.donation.mapper.ConsultMapper;
import com.donation.query.LayUIQuery;
import com.donation.service.ConsultService;
import com.donation.vo.ConsultVO;
import com.donation.vo.LayUIVO;

@Service
public class ConsultServiceImp implements ConsultService{
	@Autowired
	private ConsultMapper mapper;

	@Override
	public int addConsult(Consult consult) {
		return mapper.insertSelective(consult);
	}

	@Override
	public List<Consult> getListByUserId(Long userId) {
		Consult consult = new Consult();
		consult.setUserId(userId);
		return mapper.select(consult);
	}

	@Override
	public Consult getConsultById(Long consultId) {
		return mapper.selectByPrimaryKey(consultId);
	}

	@Override
	public int deleteConsultById(Long consultId) {
		return mapper.deleteByPrimaryKey(consultId);
	}

	@Override
	public LayUIVO getConsultList(LayUIQuery query) {
		query.setSkipRows( (query.getPage()-1)*query.getLimit() );	//计算跳过数据库前多少条数据
		List<ConsultVO> list =  mapper.getConsultVO(query);
		int total = mapper.getConsultTotal();
		return LayUIVO.data(total, list);
	}

	@Override
	public int updateConsult(Consult consult) {
		return mapper.updateByPrimaryKeySelective(consult);
	}

}
