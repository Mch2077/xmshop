package com.oracle.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.oracle.entity.Product;

@Repository
public interface ProductMapper {
	
	//没有分页的商品信息
	public List<HashMap<String, Object>> getAllProduct();

	//获取总的行数
	public int getRowCount(@Param("name")String name,@Param("typeid")int typeid);
	
	//分页的商品信息
	public List<HashMap<String, Object>> getAllProductByPage(@Param("page")int page, 
			@Param("pagesize")int pagesize,@Param("name")String name,@Param("typeid")int typeid);
	
	//删除商品
	public int deleteProduct(@Param("id")int id);

	//添加商品
	public int addProduct(Product product);

	//根据id获取当前行的数据
	public Product getProductById(@Param("id")int id);

	//商品修改
	public int updatePro(Product product);

}
