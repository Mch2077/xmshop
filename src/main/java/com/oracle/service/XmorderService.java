package com.oracle.service;

import java.util.HashMap;

import com.oracle.entity.PageBean;

//������ҵ���
public interface XmorderService {

	//��ҳ��ѯ
	PageBean<HashMap<String, Object>> getOrderByPage(int page, int pagesize);

}
