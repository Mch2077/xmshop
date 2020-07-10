package com.oracle.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.oracle.entity.Users;

//��������Ϊ���ݷ��ʲ�
@Repository
public interface UserMapper {
	
	//��¼
	public Users login(Users user);

	//��ȡ��ҳ����
	public List<Users> getAllUsers(@Param("page")int page, @Param("pagesize")int pagesize);

	//��ȡ���е���
	public int getRowCount();
	
	//����idɾ���û�
	int deleteUsersById(Integer id);
	//�����û�
	int insertUsers(Users value);

	int updateUsers(Users user);

	Users getUserById(int id);

}
