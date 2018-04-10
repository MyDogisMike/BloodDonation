package com.donation.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.donation.entity.Users;
import com.donation.query.LayUIQuery;

@Mapper
public interface UsersMapper extends tk.mybatis.mapper.common.Mapper<Users>{
	int updateSecurityCodeById(Users user);
	
	int updateUserStatusById(Users user);
	
	int getUserTotal(LayUIQuery query);
	
	List<Users> getUserList(LayUIQuery query);
	
	List<Users> getUserList1();
	
	String[] getEmailsByAreaAndType(Map<String, Object> param);
}
