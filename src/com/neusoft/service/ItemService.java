package com.neusoft.service;
import com.neusoft.base.BaseService;
import com.neusoft.po.*;
import java.util.*;

import com.neusoft.po.*;
import com.neusoft.mapper.*;
import com.neusoft.service.*;
import com.neusoft.utils.Pager;


public interface ItemService extends BaseService<Item>{

	Pager<Item> solrFind(Item item, String condition);

	List<Item> listtj(List<Integer> types);
	

}
