package com.oracle.controller;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.oracle.entity.PageBean;
import com.oracle.entity.Product;
import com.oracle.entity.Producttype;
import com.oracle.service.ProductService;
import com.oracle.service.ProducttypeService;

//商品类的控制器@Controller
@Controller
public class ProductController {
	
	//创建商品业务层
	@Autowired
	private ProductService productService;
	
	//商品类型的业务层
	@Autowired
	private ProducttypeService producttypeService;
	
	//<a href="${pageContext.request.contextPath}/getprobypage"></a>请求的方法为get方法
/*	@RequestMapping(value="/getprobypage",method=RequestMethod.GET)
	public String getProducts(Model model){//没有分页的获取数据的方法
		//调用业务层
		//导包ctrl+shift+o
		List<HashMap<String,Object>> products = productService.getAllProduct();
		//将结果封装到model
		model.addAttribute("products", products);
		//到视图层product.jsp显示数据
		return "product";//web-inf/back/product.jsp
	}*/
	
	
/*	//实现分页的控制器
	@RequestMapping(value="/getprobypage",method=RequestMethod.GET)
	public String getProducts(Model model,@RequestParam(name="page", defaultValue="1") int page){//page为当前的页码
		//定义每页显示数
		int pagesize = 5;
		//分页后的数据
		PageBean<HashMap<String,Object>> pb = productService.getProductByPage(page,pagesize);
		model.addAttribute("pb", pb);
		
		return "product";//web-inf/back/product.jsp
	}*/
	
	
	//实现分页的控制器(分页查询)
	@RequestMapping(value="/getprobypage",method=RequestMethod.GET)
	public String getProducts(Model model,@RequestParam(name="page", defaultValue="1") int page,
			String name,@RequestParam(name="typeid", defaultValue="-1")int typeid){//page为当前的页码  name为商品名称（模糊查询） typeid为商品类型id
		//获取所有的商品类型的信息
		List<Producttype>  ptlist = producttypeService.getAllProductType();
		model.addAttribute("ptlist", ptlist);
		
		//定义每页显示数
		int pagesize = 5;
		//分页后的数据
		PageBean<HashMap<String,Object>> pb = productService.getProductByPage(page,pagesize,name,typeid);
		model.addAttribute("pb", pb);
		//为了在product.jsp页面显示查询商品名称条件
		model.addAttribute("name", name);
		//为了在product.jsp页面显示查询商品类型id条件
		model.addAttribute("typeid", typeid);
		return "product";//web-inf/back/product.jsp
	}
	
	
	//删除商品
	@RequestMapping("/delproduct")
	public String delProduct(int id){
		productService.delProductById(id);
		//删除成功后直接去上面的@RequestMapping("/getprobypage")请求地址获取最新的数据
		return "redirect:getprobypage";
	}
	
	
	//进入添加商品的页面
	@RequestMapping(value="/addproduct",method=RequestMethod.GET)
	public String toAddProductPage(Model model){
		//获取所有的商品类型的信息
		List<Producttype>  ptlist = producttypeService.getAllProductType();
		model.addAttribute("ptlist", ptlist);
		return "addproduct";
	}
	
	//文件上传
	@RequestMapping("/produpload")
	@ResponseBody//返回为json  将Map集合转json格式obj = {"imgurl":"xxx","msg":"上传成功","imgName":"xxx.jsp"}
	public Map<String, String> uploadImage(MultipartFile upimage,Model model,HttpServletRequest request){//MultipartFile获取上传的文件
		//文件的名称
		String fileName = upimage.getOriginalFilename();//abc.jpg
		//扩展名
		String extName = fileName.substring(fileName.indexOf("."));
		//uuid
		String uuidName = UUID.randomUUID().toString();
		//最终的文件的名称
		String fname = uuidName + extName;
		//定义文件上传的路径（服务器的路径,不是工程的图片文件夹的路径C:\Users\zxk\Desktop\xmshop\src\main\webapp\resources）
		//服务器的路径D:\workspace-sts-3.7.3.RELEASE\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\xmshop\resources\image_big
		//获取服务器的上传的路径
		String uploadPath = request.getServletContext().getRealPath("/");
		uploadPath = uploadPath + "resources/image_big/";
		Map<String,String> map = new HashMap<>();
		
		File file = new File(uploadPath+fname);
		try {
			//实现文件上传
			upimage.transferTo(file);
			//上传成功，返回数据
			//图片的路径（为了实现图片的预览）
			map.put("imgurl", request.getContextPath() + "/resources/image_big/" + fname);
			// msg---上传成功
			map.put("msg", "上传成功");
			// imgName--dfsdgdfgd-sdfsdf-dfgdf-erwerw.jpg
			map.put("imgName", fname);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	//添加商品
	@RequestMapping(value="/addproduct",method=RequestMethod.POST)
	public String addProduct(Product product,Model model){
		//封装当前的时间
		product.setDate(new Date());
		productService.insertProduct(product);
		//添加成功后直接去上面的@RequestMapping("/getprobypage")请求地址获取最新的数据
		return "redirect:getprobypage";
	}
	
	
	//进入商品修改页面
	@RequestMapping("/toupdateproductpage")
	public String toUpdatePage(int id,Model model){
		//获取所有的商品类型的信息
		List<Producttype>  ptlist = producttypeService.getAllProductType();
		model.addAttribute("ptlist", ptlist);
		//根据id获取当前行的数据
		Product product = productService.getProductById(id);
		model.addAttribute("prod", product);
		return "updateproduct";
		
	}
	
	//商品修改
	@RequestMapping(value="/updateproduct",method=RequestMethod.POST)
	public String updateProduct(Product product){
		//封装当前的时间
		product.setDate(new Date());
		//根据id获取当前行的数据
		productService.updateProduct(product);
		//修改成功后直接去上面的@RequestMapping("/getprobypage")请求地址获取最新的数据
		return "redirect:getprobypage";
	}
	

}
