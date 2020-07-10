package com.oracle.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.entity.PageBean;
import com.oracle.mapper.XmorderMapper;
import com.oracle.service.XmorderService;

//����ҵ����ʵ��
@Service
public class XmorderServiceImpl implements XmorderService {
	
	//ע�����ݷ��ʲ�
	@Autowired
	private XmorderMapper xmorderMapper;

	//��ҳ��ѯ
	@Override
	public PageBean<HashMap<String, Object>> getOrderByPage(int page, int pagesize) {
		//��ȡ��ҳ����
		List<HashMap<String,Object>> list = xmorderMapper.getOrderByPage(page,pagesize);
		//��װ��ҳ����
		PageBean<HashMap<String,Object>> pb = new PageBean<>();
		pb.setPage(page);
		pb.setPagesize(pagesize);
		pb.setList(list);
		pb.setRowcount(getTotalRowCount());//�ܵ�����
		//��Ҫ������ҳ������Ҫ�Ȼ���ܵ�����
		int rowcount = getTotalRowCount();
		if (rowcount % pagesize == 0)
			pb.setPages(rowcount / pagesize);//����   �ܵ�ҳ��
		else {
			pb.setPages(rowcount / pagesize + 1);//������ �ܵ�ҳ��
		}
		return pb;
	}
	
	//��ȡ�������ܵ�����
	private int getTotalRowCount(){
		int count = xmorderMapper.getRowCount();
		return count;
	}

}
