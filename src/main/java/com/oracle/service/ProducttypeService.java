package com.oracle.service;

import java.util.List;

import com.oracle.entity.PageBean;
import com.oracle.entity.Producttype;

public interface ProducttypeService {
	
	//ʵ�ֻ�ȡ��Ʒ���͵ķ�ҳ����
	PageBean<Producttype> getAllProType(int page, int pagesize);

	//ɾ����Ʒ����
	int delProducttype(int id);

	//�����Ʒ����
	int insertProtype(String name);

	//������Ʒ���͵�ID��ȡ��Ʒ��Ϣ
	Producttype getProtypeById(int id);
	
	//�޸���Ʒ����
	int updateProtype(Producttype protype);

	//��ȡ���е���Ʒ����
	List<Producttype> getAllProductType();

}
