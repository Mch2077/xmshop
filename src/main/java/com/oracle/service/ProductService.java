package com.oracle.service;

import java.util.HashMap;
import java.util.List;

import com.oracle.entity.PageBean;
import com.oracle.entity.Product;

//商品的业务层
public interface ProductService {
	
	//获取所有的商品信息（没有分页）
	public List<HashMap<String, Object>> getAllProduct();

	//获取所有的商品信息（查询分页）
	public PageBean<HashMap<String,Object>> getProductByPage(int page, int pagesize,String name,int typeid);

	//删除商品
	public int delProductById(int id);

	//添加商品
	public int insertProduct(Product product);
	//根据id获取当前行的数据
	public Product getProductById(int id);
	//商品修改
	public int updateProduct(Product product);
	

}
