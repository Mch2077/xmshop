package com.oracle.mapper;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface XmorderMapper {

	//��ȡ��ҳ����
	List<HashMap<String, Object>> getOrderByPage(@Param("page")int page,@Param("pagesize") int pagesize);
	//��ȡ������
	int getRowCount();

}
