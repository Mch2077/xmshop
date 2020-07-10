package com.oracle.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.oracle.entity.PageBean;
import com.oracle.entity.Producttype;
import com.oracle.entity.Users;
import com.oracle.service.UserService;
import com.oracle.util.MD5Util;

//����?����������?������
@Controller
public class UserController {
	
	//����ҵ���? ��Ҫʹ��@Autowiredֱ��ע��
	@Autowired
	private UserService userService;
	
	//�����·��? ������ķ���������?  <form action="${pageContext.request.contextPath}/login" method="post">
	//��ݼ�? alt+/
	@RequestMapping(value="/login",method=RequestMethod.POST)
	//��ȡҳ��Ĳ���?,ʵ��������Ժͱ���name�����ֵһ��?
	//<input type="text" placeholder="Username" class="td2" name="uname">
	//<input type="password" placeholder="Password" class="td2" name="upass">
	//servlet  requst.getParameter("uname") requst.getParameter("upass")
	public String login(Users user,Model model,HttpSession session ){//ʵ������ܻ�ȡҳ����˺ź�����
		//�����Ƿ��ȡ��ҳ��Ĳ���
//		System.out.println(user);
		//����������ҵ���?
		String upass = user.getUpass();//��ȡ���������?
		upass = MD5Util.getMd5Str(upass);//�õ�����
		user.setUpass(upass);//���·�װ����
		
		Users ur = userService.login(user); 
		//���ݷ��صĽ���ж�?
		if(Objects.isNull(ur)){//����?�գ��˺Ż����벻�ԣ���Ҫ���µ�¼��
			//��ʾ��Ϣ����login.jsp�����ȡ��ʾ���?
			model.addAttribute("info", "�˺Ż��������?");
			return "login";//WEB-INF/back/login.jsp
		}
		//��¼�ɹ�,��Ҫ���ֵ�ǰ��¼���û�����Ϣ
		session.setAttribute("user", ur);
		return "main";//WEB-INF/back/main.jsp ��̨��ҳ
	}
	
	//�����¼ҳ��?
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String toLoginPage(){
		return "login";//WEB-INF/back/login.jsp ��¼ҳ��
	}
	
	//�˳�
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpSession session){
		//���session
		session.invalidate();
		return "login";
	}
	
	
	//��ȡ��ҳ����
	@RequestMapping("/getusersbypage")
	public String getUsersByPage(@RequestParam(name="page",defaultValue="1") int page,Model model){
		int pagesize = 5;
		PageBean<Users> pb =  userService.getAllUsers(page,pagesize);
		model.addAttribute("pb", pb);
		return "users";
	}
	
	
	
	//ɾ���û�
	@RequestMapping("/deluser")
	public String delUserById(int uid){
		userService.deleteUsersById(uid);
		return "redirect:getusersbypage";
	}
	
	//���������û�ҳ��
	@RequestMapping(value="/adduser",method=RequestMethod.GET)
	public String toAddUserPage(){
		return "adduser";
	}
	
	//�����û�
	@RequestMapping(value="/adduser",method=RequestMethod.POST)
	public String addUser(Users user){
		//����Ϊ����
		user.setUpass(MD5Util.getMd5Str(user.getUpass()));
		userService.insertUsers(user);
		return "redirect:getusersbypage";
	}
	
	//�ϴ��û�ͷ��
	@RequestMapping("/userupload")
	@ResponseBody//����Ϊjson  ��Map����תjson��ʽobj = {"imgurl":"xxx","msg":"�ϴ��ɹ�","imgName":"xxx.jsp"}
	public Map<String, String> uploadImage(MultipartFile uuimage,Model model,HttpServletRequest request){//MultipartFile��ȡ�ϴ����ļ�
		//�ļ�������
		String fileName = uuimage.getOriginalFilename();//abc.jpg
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
		uploadPath = uploadPath + "resources/image_user/";
		Map<String,String> map = new HashMap<>();
		
		File file = new File(uploadPath+fname);
		try {
			//ʵ���ļ��ϴ�
			uuimage.transferTo(file);
			//�ϴ��ɹ�����������
			//ͼƬ��·����Ϊ��ʵ��ͼƬ��Ԥ����
			map.put("imgurl", request.getContextPath() + "/resources/image_user/" + fname);
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
	
	@RequestMapping("/getuserbyid")
	public String toUpdatePage(int uid,Model model){
		Users user = userService.getUserById(uid); //ȡ����ǰҪ�޸ĵ��û��Ļ�����Ϣ
		model.addAttribute("u", user); //�ŵ�model���Ӹ�jsp
		return "updateuser"; //��ת updateuser.jsp
	}
	
	
	@RequestMapping("/updateuser")
	public String updateUser(Users user, HttpSession session){
		user.setUpass(null);
		/**由于MD5无法反向解密，如何在未修改密码时避免密码被重复加密是一个非常棘手的问题
		 *此处修改用户信息不对密码进行修改，逻辑可见于userMapper.xml
		*/
		userService.updateUsers(user);
		Users ur = userService.getUserById(user.getUid());
		session.setAttribute("user", ur);  //更新session里的对象
		return "redirect:getusersbypage";
	}
	
	@RequestMapping(value="/updateupass",method=RequestMethod.GET)
	public String toUpdateUpass(){
		return "updateupass";
	}
	
	/**
	 * Created by ChenKeJun on 2020/7/10
	 */
	@RequestMapping(value="/updateupass",method=RequestMethod.POST)
	public String updateUpass(@RequestParam(name = "oldUpass")String oldUpass,@RequestParam(name = "newUpass")String newUpass,HttpSession session){
		Users ur = (Users) session.getAttribute("user");
		if (ur.getUpass().equals(MD5Util.getMd5Str(oldUpass))) { //原密码验证通过
			ur.setUpass(MD5Util.getMd5Str(newUpass)); 
			userService.updateUsers(ur); //此时只有upass被更改
			//session.invalidate(); //clean session里的对象
		}
		return "redirect:logout";
	}
	

}
