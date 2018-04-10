package com.donation.service.serviceImp;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donation.constant.UserStatusEnum;
import com.donation.entity.Users;
import com.donation.mapper.UsersMapper;
import com.donation.query.LayUIQuery;
import com.donation.service.UsersService;
import com.donation.utils.EncryptUtil;

@Service
public class UsersServiceImp implements UsersService {
	@Autowired
	private UsersMapper mapper;

	@Override
	public int addOne(Users user) {
		user.setSalt(EncryptUtil.getSalt());
		user.setPassword(EncryptUtil.encrypt32(user.getPassword(), user.getSalt()));
		user.setCreatTime(new Date());
		if(user.getStatus() == null){
			user.setStatus(UserStatusEnum.inactive.getValue());	//设置状态为未激活
		}
		return mapper.insertSelective(user);
	}

	@Override
	public int updateSecurityCodeById(Users user) {
		return mapper.updateSecurityCodeById(user);
	}

	@Override
	public Users getUserByEmail(String email) {
		Users user = new Users();
		user.setEmail(email);
		return mapper.selectOne(user);
	}

	@Override
	public int updateUserStatusById(Users user) {
		return mapper.updateUserStatusById(user);
	}

	@Override
	public Users getUserByName(String name) {
		Users user = new Users();
		user.setName(name);
		return mapper.selectOne(user);
	}

	@Override
	public int updateUserById(Users user) {
		return mapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public int getUserTotal(LayUIQuery query) {
		return mapper.getUserTotal(query);
	}

	@Override
	public List<Users> getUserList(LayUIQuery query) {
		return mapper.getUserList(query);
	}

	@Override
	public Users getUserById(Long id) {
		
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateUserByIdForAdmin(Users user) {
		if(user.getPassword() != null && !"".equals(user.getPassword())){
			user.setSalt(EncryptUtil.getSalt());
			user.setPassword(EncryptUtil.encrypt32(user.getPassword(), user.getSalt()));
		}
		return mapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public String[] getEmailsByAreaAndType(Map<String, Object> param) {
		return mapper.getEmailsByAreaAndType(param);
	}

}
