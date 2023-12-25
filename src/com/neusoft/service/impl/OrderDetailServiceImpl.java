
package com.neusoft.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.neusoft.base.*;
import com.neusoft.po.*;
import com.neusoft.service.OrderDetailService;
import java.util.*;

import com.neusoft.po.*;
import com.neusoft.mapper.*;
import com.neusoft.service.*;


@Service
public class OrderDetailServiceImpl extends BaseServiceImpl<OrderDetail> implements OrderDetailService{
	 
	
	@Autowired
	private OrderDetailMapper orderDetailMapper;
	@Override
	public BaseDao<OrderDetail> getBaseDao() {
		return orderDetailMapper;
	}

}
