package com.oracle.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.oracle.entity.Producttype;

public interface ProducttypeMapper {

	//获取商品类型的分页
	List<Producttype> getAllProType(@Param("page")int page, @Param("pagesize")int pagesize);

	//获取所有的行数
	int getRowCount();

	////删除商品类型
	int delProtype(@Param("id")int id);

	//添加商品类型
	int addProtype(@Param("name")String name);
	
	//根据商品类型的ID获取商品信息
	Producttype getProtypeById(@Param("id")int id);
	
	//修改商品类型
	int updateProtype(Producttype protype);
	
	
	//获取所有的商品类型
	List<Producttype> getAllProductType();

}
