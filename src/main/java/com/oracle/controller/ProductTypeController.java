package com.oracle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.oracle.entity.PageBean;
import com.oracle.entity.Producttype;
import com.oracle.service.ProducttypeService;

@Controller
public class ProductTypeController {
	
	@Autowired
	private ProducttypeService producttypeService;
	
	//��ȡ��Ʒ���͵ķ�ҳ����
	@RequestMapping("/getprotypebypage")
	public String getProTypeByPage(@RequestParam(name="page",defaultValue="1") int page,Model model){
		int pagesize = 5;
		PageBean<Producttype> pb =  producttypeService.getAllProType(page,pagesize);
		model.addAttribute("pb", pb);
		return "producttype";
		
	}
	
	//ɾ����Ʒ����
	@RequestMapping("/delprotype")
	public String delProType(int id){//idΪ����
		producttypeService.delProducttype(id);
		//ɾ���ɹ���ֱ��ȥ�����@RequestMapping("/getprotypebypage")�����ַ��ȡ���µ�����
		return "redirect:getprotypebypage";
	}
	
	//���������Ʒ����ҳ��
	@RequestMapping(value="/addprotype",method=RequestMethod.GET)
	public String addProtypePage(){
		return "addprotype";
	}
	
	//�����Ʒ����
	@RequestMapping(value="/addprotype",method=RequestMethod.POST)
	public String addProtype(String name){//��������
		//����ҵ���ʵ�������Ʒ����
		producttypeService.insertProtype(name);
		//��ӳɹ���ֱ��ȥ�����@RequestMapping("/getprotypebypage")�����ַ��ȡ���µ�����
		return "redirect:getprotypebypage";
	}
	
	//�����޸���Ʒ���͵�ҳ��
	@RequestMapping("/toupdateprotypepage")
	public String toUpdatePage(int id,Model model){
		//����ҵ����ȡ��ǰ�е�����
		Producttype protype = producttypeService.getProtypeById(id);
		model.addAttribute("protype", protype);
		return "updateprotype";
	}
	
	//�޸���Ʒ����
	@RequestMapping(value="/updateprotype",method=RequestMethod.POST)
	public String updateProtype(Producttype protype){//ʹ��ʵ�����ȡ������ǰ����name="id" name="name" �պ���ʵ��������Ե�����
		//����ҵ����޸�
		producttypeService.updateProtype(protype);
		//�޸ĳɹ���ֱ��ȥ�����@RequestMapping("/getprotypebypage")�����ַ��ȡ���µ�����
		return "redirect:getprotypebypage";
	}

}
