package com.oracle.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.entity.PageBean;
import com.oracle.mapper.XmorderMapper;
import com.oracle.service.XmorderService;

//订单业务层的实现
@Service
public class XmorderServiceImpl implements XmorderService {
	
	//注入数据访问层
	@Autowired
	private XmorderMapper xmorderMapper;

	//分页查询
	@Override
	public PageBean<HashMap<String, Object>> getOrderByPage(int page, int pagesize) {
		//获取分页数据
		List<HashMap<String,Object>> list = xmorderMapper.getOrderByPage(page,pagesize);
		//封装分页数据
		PageBean<HashMap<String,Object>> pb = new PageBean<>();
		pb.setPage(page);
		pb.setPagesize(pagesize);
		pb.setList(list);
		pb.setRowcount(getTotalRowCount());//总的行数
		//需要计算总页数，需要先获得总的行数
		int rowcount = getTotalRowCount();
		if (rowcount % pagesize == 0)
			pb.setPages(rowcount / pagesize);//整除   总的页码
		else {
			pb.setPages(rowcount / pagesize + 1);//不整除 总的页码
		}
		return pb;
	}
	
	//获取订单的总的行数
	private int getTotalRowCount(){
		int count = xmorderMapper.getRowCount();
		return count;
	}

}
