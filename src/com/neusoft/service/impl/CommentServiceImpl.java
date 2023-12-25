
package com.neusoft.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.neusoft.base.*;
import com.neusoft.po.*;
import com.neusoft.service.CommentService;
import java.util.*;

import com.neusoft.po.*;
import com.neusoft.mapper.*;
import com.neusoft.service.*;


@Service
public class CommentServiceImpl extends BaseServiceImpl<Comment> implements CommentService{
	 
	
	@Autowired
	private CommentMapper commentMapper;
	@Override
	public BaseDao<Comment> getBaseDao() {
		return commentMapper;
	}

}
