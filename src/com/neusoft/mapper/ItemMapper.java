package com.neusoft.mapper;

import com.neusoft.base.BaseDao;
import com.neusoft.po.*;
import com.neusoft.utils.Pager;
import java.util.*;

import org.apache.ibatis.annotations.Param;

import com.neusoft.po.*;
import com.neusoft.mapper.*;
import com.neusoft.service.*;



public interface ItemMapper extends BaseDao<Item>{

	List<Item> listtj(@Param("list")List<Integer> types);
	
}
