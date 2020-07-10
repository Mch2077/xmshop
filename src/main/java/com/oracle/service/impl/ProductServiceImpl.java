package com.oracle.service.impl;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.entity.PageBean;
import com.oracle.entity.Product;
import com.oracle.mapper.ProductMapper;
import com.oracle.service.ProductService;

//��Ʒҵ����ʵ����@Service
@Service
public class ProductServiceImpl  implements ProductService{
	
	//�������ݷ��ʲ�@Autowired
	@Autowired
	private ProductMapper productMapper;

	//δ��ҳ��ʾ��Ʒ��Ϣ
	@Override
	public List<HashMap<String, Object>> getAllProduct() {
		return productMapper.getAllProduct();
	}

	//��ҳ��ʾ��Ʒ��Ϣ����ѯ��ҳ��
	@Override
	public PageBean<HashMap<String,Object>> getProductByPage(int page, int pagesize,String name,int typeid) {
		//�Ȼ�ȡ��ǰҳ������
		List<HashMap<String,Object>> products = productMapper.getAllProductByPage(page,pagesize,name,typeid);
		PageBean<HashMap<String,Object>> pageBean = new PageBean<>();
		pageBean.setPage(page);//ҳ��
		pageBean.setPagesize(pagesize);//ÿҳ��ʾ��
		pageBean.setList(products);//��ǰҳ������
		pageBean.setRowcount(getTotalRowCount(name,typeid));//�ܵ�����
		//��Ҫ������ҳ������Ҫ�Ȼ���ܵ�����
		int rowcount = getTotalRowCount(name,typeid);
		if (rowcount % pagesize == 0)
			pageBean.setPages(rowcount / pagesize);//����   �ܵ�ҳ��
		else {
			pageBean.setPages(rowcount / pagesize + 1);//������ �ܵ�ҳ��
		}
		return pageBean;
	}
	//��ȡ��Ʒ���ܵ�����
	private int getTotalRowCount(String name,int typeid){
		int count = productMapper.getRowCount(name,typeid);
		return count;
	}

	//ɾ����Ʒ
	@Override
	public int delProductById(int id) {
		return productMapper.deleteProduct(id);
	}

	//�����Ʒ
	@Override
	public int insertProduct(Product product) {
		return productMapper.addProduct(product);
	}

	//����id��ȡ��ǰ�е�����
	@Override
	public Product getProductById(int id) {
		return productMapper.getProductById(id);
	}

	//��Ʒ�޸�
	@Override
	public int updateProduct(Product product) {
		return productMapper.updatePro(product);
	}

}
