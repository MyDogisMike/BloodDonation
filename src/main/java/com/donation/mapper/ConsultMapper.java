package com.donation.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.donation.entity.Consult;
import com.donation.query.LayUIQuery;
import com.donation.vo.ConsultVO;

@Mapper
public interface ConsultMapper extends tk.mybatis.mapper.common.Mapper<Consult>{
	public List<ConsultVO> getConsultVO(LayUIQuery query);
	
	public Integer getConsultTotal();
	
}
