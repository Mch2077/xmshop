package com.oracle.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.oracle.entity.Product;

@Repository
public interface ProductMapper {
	
	//û�з�ҳ����Ʒ��Ϣ
	public List<HashMap<String, Object>> getAllProduct();

	//��ȡ�ܵ�����
	public int getRowCount(@Param("name")String name,@Param("typeid")int typeid);
	
	//��ҳ����Ʒ��Ϣ
	public List<HashMap<String, Object>> getAllProductByPage(@Param("page")int page, 
			@Param("pagesize")int pagesize,@Param("name")String name,@Param("typeid")int typeid);
	
	//ɾ����Ʒ
	public int deleteProduct(@Param("id")int id);

	//�����Ʒ
	public int addProduct(Product product);

	//����id��ȡ��ǰ�е�����
	public Product getProductById(@Param("id")int id);

	//��Ʒ�޸�
	public int updatePro(Product product);

}
