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

	//��ȡ��Ʒ���͵ķ�ҳ����
	@Override
	public PageBean<Producttype> getAllProType(int page, int pagesize) {
		
		List<Producttype> pts = producttypeMapper.getAllProType(page,pagesize);
		
		PageBean<Producttype> pageBean = new PageBean<>();
		//pagebean��װ������Ϊ
		pageBean.setPage(page);//ҳ��
		pageBean.setPagesize(pagesize);//ÿҳ��ʾ��
		pageBean.setList(pts);//��ǰҳ������
		pageBean.setRowcount(getTotalRowCount());//�ܵ�����
		//��Ҫ������ҳ������Ҫ�Ȼ���ܵ�����
		int rowcount = getTotalRowCount();
		if (rowcount % pagesize == 0)
			pageBean.setPages(rowcount / pagesize);//����   �ܵ�ҳ��
		else {
			pageBean.setPages(rowcount / pagesize + 1);//������ �ܵ�ҳ��
		}
		return pageBean;
	}
	
	//��ȡ��Ʒ���͵��ܵ�����
	private int getTotalRowCount(){
		int count = producttypeMapper.getRowCount();
		return count;
	}

	//ɾ����Ʒ����
	@Override
	public int delProducttype(int id) {
		return producttypeMapper.delProtype(id);
	}

	//�����Ʒ����
	@Override
	public int insertProtype(String name) {
		return producttypeMapper.addProtype(name);
	}

	//������Ʒ���͵�ID��ȡ��Ʒ��Ϣ
	@Override
	public Producttype getProtypeById(int id) {
		return producttypeMapper.getProtypeById(id);
	}

	//�޸���Ʒ����
	@Override
	public int updateProtype(Producttype protype) {
		return producttypeMapper.updateProtype(protype);
	}

	//��ȡ���е���Ʒ����
	@Override
	public List<Producttype> getAllProductType() {
		return producttypeMapper.getAllProductType();
	}

}
