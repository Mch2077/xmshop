package xmshop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestHashMap {
	
	public static void main(String[] args) {
		
		//键String---封装数据的字段的名称 id name typename  值Object 1 小米
		//一个map只能封装一行数据
		HashMap<String, Object> map = new HashMap<>();
		map.put("id", 1);
		map.put("name", "小米");
		
		//使用List就可以封装多行数据
		List<HashMap<String, Object>> list = new ArrayList<>();
		
	}

}
