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
	
	//获取商品类型的分页数据
	@RequestMapping("/getprotypebypage")
	public String getProTypeByPage(@RequestParam(name="page",defaultValue="1") int page,Model model){
		int pagesize = 5;
		PageBean<Producttype> pb =  producttypeService.getAllProType(page,pagesize);
		model.addAttribute("pb", pb);
		return "producttype";
		
	}
	
	//删除商品类型
	@RequestMapping("/delprotype")
	public String delProType(int id){//id为参数
		producttypeService.delProducttype(id);
		//删除成功后直接去上面的@RequestMapping("/getprotypebypage")请求地址获取最新的数据
		return "redirect:getprotypebypage";
	}
	
	//进入添加商品类型页面
	@RequestMapping(value="/addprotype",method=RequestMethod.GET)
	public String addProtypePage(){
		return "addprotype";
	}
	
	//添加商品类型
	@RequestMapping(value="/addprotype",method=RequestMethod.POST)
	public String addProtype(String name){//参数名称
		//调用业务层实现添加商品类型
		producttypeService.insertProtype(name);
		//添加成功后直接去上面的@RequestMapping("/getprotypebypage")请求地址获取最新的数据
		return "redirect:getprotypebypage";
	}
	
	//进入修改商品类型的页面
	@RequestMapping("/toupdateprotypepage")
	public String toUpdatePage(int id,Model model){
		//调用业务层获取当前行的数据
		Producttype protype = producttypeService.getProtypeById(id);
		model.addAttribute("protype", protype);
		return "updateprotype";
	}
	
	//修改商品类型
	@RequestMapping(value="/updateprotype",method=RequestMethod.POST)
	public String updateProtype(Producttype protype){//使用实体类获取参数的前提是name="id" name="name" 刚好是实体类的属性的名称
		//调用业务层修改
		producttypeService.updateProtype(protype);
		//修改成功后直接去上面的@RequestMapping("/getprotypebypage")请求地址获取最新的数据
		return "redirect:getprotypebypage";
	}

}
