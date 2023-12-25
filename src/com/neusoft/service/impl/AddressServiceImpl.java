
package com.neusoft.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.neusoft.base.*;
import com.neusoft.po.*;
import com.neusoft.service.AddressService;
import java.util.*;

import com.neusoft.po.*;
import com.neusoft.mapper.*;
import com.neusoft.service.*;


@Service
public class AddressServiceImpl extends BaseServiceImpl<Address> implements AddressService{
	 
	
	@Autowired
	private AddressMapper addressMapper;
	@Override
	public BaseDao<Address> getBaseDao() {
		return addressMapper;
	}

}
