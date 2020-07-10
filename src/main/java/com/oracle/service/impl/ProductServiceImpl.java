package com.oracle.service.impl;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.entity.PageBean;
import com.oracle.entity.Product;
import com.oracle.mapper.ProductMapper;
import com.oracle.service.ProductService;

//商品业务层的实现类@Service
@Service
public class ProductServiceImpl  implements ProductService{
	
	//调用数据访问层@Autowired
	@Autowired
	private ProductMapper productMapper;

	//未分页显示商品信息
	@Override
	public List<HashMap<String, Object>> getAllProduct() {
		return productMapper.getAllProduct();
	}

	//分页显示商品信息（查询分页）
	@Override
	public PageBean<HashMap<String,Object>> getProductByPage(int page, int pagesize,String name,int typeid) {
		//先获取当前页的数据
		List<HashMap<String,Object>> products = productMapper.getAllProductByPage(page,pagesize,name,typeid);
		PageBean<HashMap<String,Object>> pageBean = new PageBean<>();
		pageBean.setPage(page);//页码
		pageBean.setPagesize(pagesize);//每页显示行
		pageBean.setList(products);//当前页的数据
		pageBean.setRowcount(getTotalRowCount(name,typeid));//总的行数
		//需要计算总页数，需要先获得总的行数
		int rowcount = getTotalRowCount(name,typeid);
		if (rowcount % pagesize == 0)
			pageBean.setPages(rowcount / pagesize);//整除   总的页码
		else {
			pageBean.setPages(rowcount / pagesize + 1);//不整除 总的页码
		}
		return pageBean;
	}
	//获取商品的总的行数
	private int getTotalRowCount(String name,int typeid){
		int count = productMapper.getRowCount(name,typeid);
		return count;
	}

	//删除商品
	@Override
	public int delProductById(int id) {
		return productMapper.deleteProduct(id);
	}

	//添加商品
	@Override
	public int insertProduct(Product product) {
		return productMapper.addProduct(product);
	}

	//根据id获取当前行的数据
	@Override
	public Product getProductById(int id) {
		return productMapper.getProductById(id);
	}

	//商品修改
	@Override
	public int updateProduct(Product product) {
		return productMapper.updatePro(product);
	}

}
