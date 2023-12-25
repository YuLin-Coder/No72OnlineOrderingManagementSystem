
package com.neusoft.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.neusoft.base.*;
import com.neusoft.po.*;
import com.neusoft.service.ItemOrderService;
import java.util.*;

import com.neusoft.po.*;
import com.neusoft.mapper.*;
import com.neusoft.service.*;

@Service
public class ItemOrderServiceImpl extends BaseServiceImpl<ItemOrder> implements ItemOrderService{
	 
	
	@Autowired
	private ItemOrderMapper itemOrderMapper;
	@Override
	public BaseDao<ItemOrder> getBaseDao() {
		return itemOrderMapper;
	}

}
