package com.oracle.service;

import com.oracle.entity.PageBean;
import com.oracle.entity.Users;

//�û���ҵ���
public interface UserService {
	//������¼��ҵ�񷽷�
	public Users login(Users user);

	//��ҳ����
	public PageBean<Users> getAllUsers(int page, int pagesize);
	
	//����idɾ���û�
	int deleteUsersById(Integer id);
	
	//�����û�
	int insertUsers(Users value);

	int updateUsers(Users user);

	Users getUserById(int id);

}
