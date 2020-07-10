package com.oracle.service;

import java.util.HashMap;

import com.oracle.entity.PageBean;

//订单的业务层
public interface XmorderService {

	//分页查询
	PageBean<HashMap<String, Object>> getOrderByPage(int page, int pagesize);

}
