package com.oracle.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.oracle.entity.Producttype;

public interface ProducttypeMapper {

	//��ȡ��Ʒ���͵ķ�ҳ
	List<Producttype> getAllProType(@Param("page")int page, @Param("pagesize")int pagesize);

	//��ȡ���е�����
	int getRowCount();

	////ɾ����Ʒ����
	int delProtype(@Param("id")int id);

	//�����Ʒ����
	int addProtype(@Param("name")String name);
	
	//������Ʒ���͵�ID��ȡ��Ʒ��Ϣ
	Producttype getProtypeById(@Param("id")int id);
	
	//�޸���Ʒ����
	int updateProtype(Producttype protype);
	
	
	//��ȡ���е���Ʒ����
	List<Producttype> getAllProductType();

}
