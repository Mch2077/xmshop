package com.oracle.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.oracle.entity.PageBean;
import com.oracle.service.XmorderService;

@Controller
public class XmorderController {
	
	//ע��ҵ���
	@Autowired
	private XmorderService xmorderService;
	
	//ʵ�ֶ����ķ�ҳ��ѯ
	@RequestMapping("/getorderbypage")
	public String getOrdersByPage(@RequestParam(name="page",defaultValue="1") int page,Model model){
		int pagesize = 5;
		//������ϲ�ѯʹ��HashMap<String,Object>
		PageBean<HashMap<String,Object>> pb = xmorderService.getOrderByPage(page,pagesize);
		model.addAttribute("pb", pb);
		return "xmorder";
		
	}

}
