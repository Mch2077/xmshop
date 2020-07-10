package com.oracle.mapper;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface XmorderMapper {

	//获取分页数据
	List<HashMap<String, Object>> getOrderByPage(@Param("page")int page,@Param("pagesize") int pagesize);
	//获取中行数
	int getRowCount();

}
