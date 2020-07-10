package com.oracle.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.entity.PageBean;
import com.oracle.entity.Producttype;
import com.oracle.entity.Users;
import com.oracle.mapper.UserMapper;
import com.oracle.service.UserService;


//�û���ҵ�������Ҫ���ע��@Service
@Service
public class UserServiceImpl implements  UserService{
	
	//��Ҫ�������ݷ��ʲ�,��Ҫʹ��@Autowiredע�����ݷ��ʲ�
	@Autowired
	private UserMapper userMapper;

	//��¼
	@Override
	public Users login(Users user) {
		return userMapper.login(user);
	}
	
	//��ȡ��ҳ����
	@Override
	public PageBean<Users> getAllUsers(int page, int pagesize) {
		
		List<Users> users = userMapper.getAllUsers(page,pagesize);
		
		PageBean<Users> pageBean = new PageBean<>();
		//pagebean��װ������Ϊ
		pageBean.setPage(page);//ҳ��
		pageBean.setPagesize(pagesize);//ÿҳ��ʾ��
		pageBean.setList(users);//��ǰҳ������
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
		int count = userMapper.getRowCount();
		return count;
	}
	
	//����idɾ���û�
    @Override
    public int deleteUsersById(Integer id){
        return userMapper.deleteUsersById(id);
    }

    //�����û�
    @Override
    public int insertUsers(Users value){
        return userMapper.insertUsers(value);
    }

	@Override
	public int updateUsers(Users user) {
        return userMapper.updateUsers(user);
	}

	@Override
	public Users getUserById(int id) {
		return userMapper.getUserById(id);
	}
	
	
    
    
}
