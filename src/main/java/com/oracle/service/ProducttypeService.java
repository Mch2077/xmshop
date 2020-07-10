package com.oracle.service;

import java.util.List;

import com.oracle.entity.PageBean;
import com.oracle.entity.Producttype;

public interface ProducttypeService {
	
	//实现获取商品类型的分页数据
	PageBean<Producttype> getAllProType(int page, int pagesize);

	//删除商品类型
	int delProducttype(int id);

	//添加商品类型
	int insertProtype(String name);

	//根据商品类型的ID获取商品信息
	Producttype getProtypeById(int id);
	
	//修改商品类型
	int updateProtype(Producttype protype);

	//获取所有的商品类型
	List<Producttype> getAllProductType();

}
