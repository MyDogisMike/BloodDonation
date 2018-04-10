package com.donation.service;

import java.util.List;
import java.util.Map;

import com.donation.entity.Users;
import com.donation.query.LayUIQuery;

public interface UsersService {
	public int addOne(Users user);
	
	public int updateSecurityCodeById(Users user);
	
	public Users getUserByEmail(String email);
	
	public Users getUserByName(String name);
	
	public Users getUserById(Long id);
	
	public int updateUserStatusById(Users user);
	
	public int updateUserById(Users user);
	
	public int updateUserByIdForAdmin(Users user);
	
	public int getUserTotal(LayUIQuery query);
	
	public List<Users> getUserList(LayUIQuery query);
	
	public String[] getEmailsByAreaAndType(Map<String, Object> param);
}
