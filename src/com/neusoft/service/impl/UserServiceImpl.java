
package com.neusoft.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.neusoft.base.*;
import com.neusoft.po.*;
import com.neusoft.service.UserService;
import java.util.*;

import com.neusoft.po.*;
import com.neusoft.mapper.*;
import com.neusoft.service.*;


@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{
	 
	
	@Autowired
	private UserMapper userMapper;
	@Override
	public BaseDao<User> getBaseDao() {
		return userMapper;
	}

}
