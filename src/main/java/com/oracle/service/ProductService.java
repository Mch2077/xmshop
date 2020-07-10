package com.oracle.service;

import java.util.HashMap;
import java.util.List;

import com.oracle.entity.PageBean;
import com.oracle.entity.Product;

//��Ʒ��ҵ���
public interface ProductService {
	
	//��ȡ���е���Ʒ��Ϣ��û�з�ҳ��
	public List<HashMap<String, Object>> getAllProduct();

	//��ȡ���е���Ʒ��Ϣ����ѯ��ҳ��
	public PageBean<HashMap<String,Object>> getProductByPage(int page, int pagesize,String name,int typeid);

	//ɾ����Ʒ
	public int delProductById(int id);

	//�����Ʒ
	public int insertProduct(Product product);
	//����id��ȡ��ǰ�е�����
	public Product getProductById(int id);
	//��Ʒ�޸�
	public int updateProduct(Product product);
	

}
