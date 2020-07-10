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

//��Ʒ��Ŀ�����@Controller
@Controller
public class ProductController {
	
	//������Ʒҵ���
	@Autowired
	private ProductService productService;
	
	//��Ʒ���͵�ҵ���
	@Autowired
	private ProducttypeService producttypeService;
	
	//<a href="${pageContext.request.contextPath}/getprobypage"></a>����ķ���Ϊget����
/*	@RequestMapping(value="/getprobypage",method=RequestMethod.GET)
	public String getProducts(Model model){//û�з�ҳ�Ļ�ȡ���ݵķ���
		//����ҵ���
		//����ctrl+shift+o
		List<HashMap<String,Object>> products = productService.getAllProduct();
		//�������װ��model
		model.addAttribute("products", products);
		//����ͼ��product.jsp��ʾ����
		return "product";//web-inf/back/product.jsp
	}*/
	
	
/*	//ʵ�ַ�ҳ�Ŀ�����
	@RequestMapping(value="/getprobypage",method=RequestMethod.GET)
	public String getProducts(Model model,@RequestParam(name="page", defaultValue="1") int page){//pageΪ��ǰ��ҳ��
		//����ÿҳ��ʾ��
		int pagesize = 5;
		//��ҳ�������
		PageBean<HashMap<String,Object>> pb = productService.getProductByPage(page,pagesize);
		model.addAttribute("pb", pb);
		
		return "product";//web-inf/back/product.jsp
	}*/
	
	
	//ʵ�ַ�ҳ�Ŀ�����(��ҳ��ѯ)
	@RequestMapping(value="/getprobypage",method=RequestMethod.GET)
	public String getProducts(Model model,@RequestParam(name="page", defaultValue="1") int page,
			String name,@RequestParam(name="typeid", defaultValue="-1")int typeid){//pageΪ��ǰ��ҳ��  nameΪ��Ʒ���ƣ�ģ����ѯ�� typeidΪ��Ʒ����id
		//��ȡ���е���Ʒ���͵���Ϣ
		List<Producttype>  ptlist = producttypeService.getAllProductType();
		model.addAttribute("ptlist", ptlist);
		
		//����ÿҳ��ʾ��
		int pagesize = 5;
		//��ҳ�������
		PageBean<HashMap<String,Object>> pb = productService.getProductByPage(page,pagesize,name,typeid);
		model.addAttribute("pb", pb);
		//Ϊ����product.jspҳ����ʾ��ѯ��Ʒ��������
		model.addAttribute("name", name);
		//Ϊ����product.jspҳ����ʾ��ѯ��Ʒ����id����
		model.addAttribute("typeid", typeid);
		return "product";//web-inf/back/product.jsp
	}
	
	
	//ɾ����Ʒ
	@RequestMapping("/delproduct")
	public String delProduct(int id){
		productService.delProductById(id);
		//ɾ���ɹ���ֱ��ȥ�����@RequestMapping("/getprobypage")�����ַ��ȡ���µ�����
		return "redirect:getprobypage";
	}
	
	
	//���������Ʒ��ҳ��
	@RequestMapping(value="/addproduct",method=RequestMethod.GET)
	public String toAddProductPage(Model model){
		//��ȡ���е���Ʒ���͵���Ϣ
		List<Producttype>  ptlist = producttypeService.getAllProductType();
		model.addAttribute("ptlist", ptlist);
		return "addproduct";
	}
	
	//�ļ��ϴ�
	@RequestMapping("/produpload")
	@ResponseBody//����Ϊjson  ��Map����תjson��ʽobj = {"imgurl":"xxx","msg":"�ϴ��ɹ�","imgName":"xxx.jsp"}
	public Map<String, String> uploadImage(MultipartFile upimage,Model model,HttpServletRequest request){//MultipartFile��ȡ�ϴ����ļ�
		//�ļ�������
		String fileName = upimage.getOriginalFilename();//abc.jpg
		//��չ��
		String extName = fileName.substring(fileName.indexOf("."));
		//uuid
		String uuidName = UUID.randomUUID().toString();
		//���յ��ļ�������
		String fname = uuidName + extName;
		//�����ļ��ϴ���·������������·��,���ǹ��̵�ͼƬ�ļ��е�·��C:\Users\zxk\Desktop\xmshop\src\main\webapp\resources��
		//��������·��D:\workspace-sts-3.7.3.RELEASE\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\xmshop\resources\image_big
		//��ȡ���������ϴ���·��
		String uploadPath = request.getServletContext().getRealPath("/");
		uploadPath = uploadPath + "resources/image_big/";
		Map<String,String> map = new HashMap<>();
		
		File file = new File(uploadPath+fname);
		try {
			//ʵ���ļ��ϴ�
			upimage.transferTo(file);
			//�ϴ��ɹ�����������
			//ͼƬ��·����Ϊ��ʵ��ͼƬ��Ԥ����
			map.put("imgurl", request.getContextPath() + "/resources/image_big/" + fname);
			// msg---�ϴ��ɹ�
			map.put("msg", "�ϴ��ɹ�");
			// imgName--dfsdgdfgd-sdfsdf-dfgdf-erwerw.jpg
			map.put("imgName", fname);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	//�����Ʒ
	@RequestMapping(value="/addproduct",method=RequestMethod.POST)
	public String addProduct(Product product,Model model){
		//��װ��ǰ��ʱ��
		product.setDate(new Date());
		productService.insertProduct(product);
		//��ӳɹ���ֱ��ȥ�����@RequestMapping("/getprobypage")�����ַ��ȡ���µ�����
		return "redirect:getprobypage";
	}
	
	
	//������Ʒ�޸�ҳ��
	@RequestMapping("/toupdateproductpage")
	public String toUpdatePage(int id,Model model){
		//��ȡ���е���Ʒ���͵���Ϣ
		List<Producttype>  ptlist = producttypeService.getAllProductType();
		model.addAttribute("ptlist", ptlist);
		//����id��ȡ��ǰ�е�����
		Product product = productService.getProductById(id);
		model.addAttribute("prod", product);
		return "updateproduct";
		
	}
	
	//��Ʒ�޸�
	@RequestMapping(value="/updateproduct",method=RequestMethod.POST)
	public String updateProduct(Product product){
		//��װ��ǰ��ʱ��
		product.setDate(new Date());
		//����id��ȡ��ǰ�е�����
		productService.updateProduct(product);
		//�޸ĳɹ���ֱ��ȥ�����@RequestMapping("/getprobypage")�����ַ��ȡ���µ�����
		return "redirect:getprobypage";
	}
	

}
