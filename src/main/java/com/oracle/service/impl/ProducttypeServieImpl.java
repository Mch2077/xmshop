package com.oracle.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.entity.PageBean;
import com.oracle.entity.Producttype;
import com.oracle.mapper.ProducttypeMapper;
import com.oracle.service.ProducttypeService;

@Service
public class ProducttypeServieImpl implements ProducttypeService {
	
	@Autowired
	private ProducttypeMapper producttypeMapper;

	//获取商品类型的分页数据
	@Override
	public PageBean<Producttype> getAllProType(int page, int pagesize) {
		
		List<Producttype> pts = producttypeMapper.getAllProType(page,pagesize);
		
		PageBean<Producttype> pageBean = new PageBean<>();
		//pagebean封装的数据为
		pageBean.setPage(page);//页码
		pageBean.setPagesize(pagesize);//每页显示行
		pageBean.setList(pts);//当前页的数据
		pageBean.setRowcount(getTotalRowCount());//总的行数
		//需要计算总页数，需要先获得总的行数
		int rowcount = getTotalRowCount();
		if (rowcount % pagesize == 0)
			pageBean.setPages(rowcount / pagesize);//整除   总的页码
		else {
			pageBean.setPages(rowcount / pagesize + 1);//不整除 总的页码
		}
		return pageBean;
	}
	
	//获取商品类型的总的行数
	private int getTotalRowCount(){
		int count = producttypeMapper.getRowCount();
		return count;
	}

	//删除商品类型
	@Override
	public int delProducttype(int id) {
		return producttypeMapper.delProtype(id);
	}

	//添加商品类型
	@Override
	public int insertProtype(String name) {
		return producttypeMapper.addProtype(name);
	}

	//根据商品类型的ID获取商品信息
	@Override
	public Producttype getProtypeById(int id) {
		return producttypeMapper.getProtypeById(id);
	}

	//修改商品类型
	@Override
	public int updateProtype(Producttype protype) {
		return producttypeMapper.updateProtype(protype);
	}

	//获取所有的商品类型
	@Override
	public List<Producttype> getAllProductType() {
		return producttypeMapper.getAllProductType();
	}

}
