package zj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zj.common.Constant;
import zj.common.Opertorlog;
import zj.dao.UserMapper;
import zj.model.User;
import zj.service.UserServiceI;

@Service
public class UserServiceImpl implements UserServiceI {

	@Autowired
	private UserMapper userMapper;
	@Opertorlog(name="取得用戶",functionPath="/user/111/get",operateType=Constant.OPERATE_TYPE_LOGIN)
	public User getUserById(String id) {
		return this.userMapper.selectByPrimaryKey(id);
	}

}
