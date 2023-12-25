package com.neusoft.controller;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.neusoft.base.BaseController;
import com.neusoft.po.*;
import com.neusoft.utils.Pager;
import java.util.*;

import com.neusoft.po.*;
import com.neusoft.mapper.*;
import com.neusoft.service.*;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	
	
	/**
	 * 依赖注入 start dao/service/===
	 */
	@Autowired
	private UserService userService;
	
	// --------------------------------------- 华丽分割线 ------------------------------
	
	/*********************************查询列表【不分页】***********************************************/
	
	/**
	 * 【不分页 => 查询列表 => 无条件】
	* @Title: listAll 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return 设定文件 
	* @author
	* @return String 返回类型 
	* @throws
	 */
	@RequestMapping(value = "/listAll")
	public String listAll(User user, Model model, HttpServletRequest request, HttpServletResponse response){
		List<User> listAll = userService.listAll();
		model.addAttribute("list", listAll);
		return "user/user";
	}
	
	/**
	 *  【不分页=》查询列表=>有条件】
	* @Title: listByEntity 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return 设定文件 
	* @author
	* @return String 返回类型 
	* @throws
	 */
	@RequestMapping(value = "/listByEntity")
	public String listByEntity(User user, Model model, HttpServletRequest request, HttpServletResponse response){
		List<User> listAll = userService.listAllByEntity(user);
		model.addAttribute("list", listAll);
		return "user/user";
	}
	
	/**
	 *  【不分页=》查询列表=>有条件】
	* @Title: listByMap 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return 设定文件 
	* @author 
	* @return String 返回类型 
	* @throws
	 */
	@RequestMapping(value = "/listByMap")
	public String listByMap(User user, Model model, HttpServletRequest request, HttpServletResponse response){
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
	        if(!isEmpty(user.getUserName())){
	        	params.put("userName", user.getUserName());
			}
	        if(!isEmpty(user.getPassWord())){
	        	params.put("passWord", user.getPassWord());
			}
	        if(!isEmpty(user.getPhone())){
	        	params.put("phone", user.getPhone());
			}
	        if(!isEmpty(user.getRealName())){
	        	params.put("realName", user.getRealName());
			}
	        if(!isEmpty(user.getSex())){
	        	params.put("sex", user.getSex());
			}
	        if(!isEmpty(user.getAddress())){
	        	params.put("address", user.getAddress());
			}
	        if(!isEmpty(user.getEmail())){
	        	params.put("email", user.getEmail());
			}
	    List<User> listAll = userService.listByMap(params);
		model.addAttribute("list", listAll);
		return "user/user";
	}
	
	
	/*********************************查询列表【分页】***********************************************/
	
	
	
	/**
	 * 分页查询 返回list对象(通过对象)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByObj")
	public String findByObj(User user, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		Pager<User> pagers = userService.findByEntity(user);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", user);
		return "user/user";
	}
	
	/**
	 * 分页查询 返回list对象(通过对By Sql)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findBySql")
	public String findBySql(User user, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		String sql = "SELECT * FROM user WHERE 1=1 ";
        if(!isEmpty(user.getUserName())){
        	sql += " and userName like '%"+user.getUserName()+"%'";
		}
        if(!isEmpty(user.getPassWord())){
        	sql += " and passWord like '%"+user.getPassWord()+"%'";
		}
        if(!isEmpty(user.getPhone())){
        	sql += " and phone like '%"+user.getPhone()+"%'";
		}
        if(!isEmpty(user.getRealName())){
        	sql += " and realName like '%"+user.getRealName()+"%'";
		}
        if(!isEmpty(user.getSex())){
        	sql += " and sex like '%"+user.getSex()+"%'";
		}
        if(!isEmpty(user.getAddress())){
        	sql += " and address like '%"+user.getAddress()+"%'";
		}
        if(!isEmpty(user.getEmail())){
        	sql += " and email like '%"+user.getEmail()+"%'";
		}
       sql += " ORDER BY ID DESC ";
		Pager<User> pagers = userService.findBySqlRerturnEntity(sql);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", user);
		return "user/user";
	}
	
	
	/**
	 * 分页查询 返回list对象(通过Map)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByMap")
	public String findByMap(User user, Model model, HttpServletRequest request, HttpServletResponse response) {
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
        if(!isEmpty(user.getUserName())){
        	params.put("userName", user.getUserName());
		}
        if(!isEmpty(user.getPassWord())){
        	params.put("passWord", user.getPassWord());
		}
        if(!isEmpty(user.getPhone())){
        	params.put("phone", user.getPhone());
		}
        if(!isEmpty(user.getRealName())){
        	params.put("realName", user.getRealName());
		}
        if(!isEmpty(user.getSex())){
        	params.put("sex", user.getSex());
		}
        if(!isEmpty(user.getAddress())){
        	params.put("address", user.getAddress());
		}
        if(!isEmpty(user.getEmail())){
        	params.put("email", user.getEmail());
		}
		//分页查询
		Pager<User> pagers = userService.findByMap(params);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", user);
		return "user/user";
	}
	
	/**********************************【增删改】******************************************************/
	
	/**
	 * 跳至添加页面
	 * @return
	 */
	@RequestMapping(value = "/add")
	public String add() {
		return "user/add";
	}

	/**
	 * 跳至详情页面
	 * @return
	 */
	@RequestMapping(value = "/view")
	public String view(Model model,HttpServletRequest request) {
		Object attribute = request.getSession().getAttribute("userId");
		if (attribute == null){
			return "redirect:/login/uLogin";
		}
		JSONObject js = new JSONObject();
		Integer userId = Integer.valueOf(attribute.toString());
		User obj = userService.load(userId);
		model.addAttribute("obj",obj);
		return "user/view";
	}
	
	/**
	 * 添加执行
	 * @return
	 */
	@RequestMapping(value = "/exAdd")
	public String exAdd(User user, Model model, HttpServletRequest request, HttpServletResponse response) {
		userService.insert(user);
		return "redirect:/user/findBySql.action";
	}
	
	
	/**
	 * 跳至修改页面
	 * @return
	 */
	@RequestMapping(value = "/update")
	public String update(Integer id,Model model) {
		User obj = userService.load(id);
		model.addAttribute("obj",obj);
		return "user/update";
	}
	
	/**
	 * 添加修改
	 * @return
	 */
	@RequestMapping(value = "/exUpdate")
	public String exUpdate(User user, Model model, HttpServletRequest request, HttpServletResponse response) {
		//1.通过实体类修改，可以多传修改条件
		Object attribute = request.getSession().getAttribute("userId");
		if (attribute == null){
			return "redirect:/login/uLogin";
		}
		JSONObject js = new JSONObject();
		user.setId(Integer.valueOf(attribute.toString()));
		userService.updateById(user);
		//2.通过主键id修改
		//userService.updateById(user);
		return "redirect:/user/view.action";
	}
	
	/**
	 * 删除通过主键
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public String delete(Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
		///1.通过主键删除
		userService.deleteById(id);
		/*以下是多种删除方式*/
//		//2.通过实体条件删除
//		userService.deleteByEntity(user);
//		//3.通过参数删除
//     //通过map查询
//		Map<String,Object> params = new HashMap<String,Object>();
//		
//        if(!isEmpty(user.getUserName())){
//        	params.put("userName", user.getUserName());
//		}
//       
//        if(!isEmpty(user.getPassWord())){
//        	params.put("passWord", user.getPassWord());
//		}
//       
//        if(!isEmpty(user.getPhone())){
//        	params.put("phone", user.getPhone());
//		}
//       
//        if(!isEmpty(user.getRealName())){
//        	params.put("realName", user.getRealName());
//		}
//       
//        if(!isEmpty(user.getSex())){
//        	params.put("sex", user.getSex());
//		}
//       
//        if(!isEmpty(user.getAddress())){
//        	params.put("address", user.getAddress());
//		}
//       
//        if(!isEmpty(user.getEmail())){
//        	params.put("email", user.getEmail());
//		}
//       
//		userService.deleteByMap(params);
//		//4.状态删除
//		User load = userService.getById(user.getId())
//		load.setIsDelete(1);
//		userService.update(load);
		//5.状态删除
		//User load = userService.load(id);
		//load.setIsDelete(1);
		//userService.update(load);
		return "redirect:/user/findBySql.action";
	}
	
	// --------------------------------------- 华丽分割线 ------------------------------
	// --------------------------------------- 【下面是ajax操作的方法。】 ------------------------------

	/*********************************查询列表【不分页】***********************************************/
	
	/**
	 * 【不分页 => 查询列表 => 无条件】
	* @Title: listAll 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return 设定文件 
	* @author
	* @return String 返回类型 
	* @throws
	 */
	@RequestMapping(value = "/listAllJson", method = RequestMethod.POST)
	@ResponseBody
	public String listAllJson(User user, HttpServletRequest request, HttpServletResponse response){
		List<User> listAll = userService.listAll();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("list", listAll);
		jsonObject.put("obj", user);
		return jsonObject.toString();
	}
	
	/**
	 *  【不分页=》查询列表=>有条件】
	* @Title: listByEntity 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return 设定文件 
	* @author
	* @return String 返回类型 
	* @throws
	 */
	@RequestMapping(value = "/listByEntityJson", method = RequestMethod.POST)
	@ResponseBody
	public String listByEntityJson(User user,  HttpServletRequest request, HttpServletResponse response){
		List<User> listAll = userService.listAllByEntity(user);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("list", listAll);
		jsonObject.put("obj", user);
		return jsonObject.toString();
	}
	
	/**
	 *  【不分页=》查询列表=>有条件】
	* @Title: listByMap 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return 设定文件 
	* @author 
	* @return String 返回类型 
	* @throws
	 */
	@RequestMapping(value = "/listByMapJson", method = RequestMethod.POST)
	@ResponseBody
	public String listByMapJson(User user,HttpServletRequest request, HttpServletResponse response){
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
	        if(!isEmpty(user.getUserName())){
	        	params.put("userName", user.getUserName());
			}
	        if(!isEmpty(user.getPassWord())){
	        	params.put("passWord", user.getPassWord());
			}
	        if(!isEmpty(user.getPhone())){
	        	params.put("phone", user.getPhone());
			}
	        if(!isEmpty(user.getRealName())){
	        	params.put("realName", user.getRealName());
			}
	        if(!isEmpty(user.getSex())){
	        	params.put("sex", user.getSex());
			}
	        if(!isEmpty(user.getAddress())){
	        	params.put("address", user.getAddress());
			}
	        if(!isEmpty(user.getEmail())){
	        	params.put("email", user.getEmail());
			}
	    List<User> listAll = userService.listByMap(params);
	    JSONObject jsonObject = new JSONObject();
		jsonObject.put("list", listAll);
		jsonObject.put("obj", user);
		return jsonObject.toString();
	}
	
	
	/**
	 * 分页查询 返回list json(通过对象)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByObjJson", method = RequestMethod.POST)
	@ResponseBody
	public String findByObjByEntityJson(User user, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		Pager<User> pagers = userService.findByEntity(user);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("pagers", pagers);
		jsonObject.put("obj", user);
		return jsonObject.toString();
	}
	
	  
	/**
	 * 分页查询 返回list json(通过Map)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByMapJson",  method = RequestMethod.POST)
	@ResponseBody
	public String findByMapJson(User user,HttpServletRequest request, HttpServletResponse response) {
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
        if(!isEmpty(user.getUserName())){
        	params.put("userName", user.getUserName());
		}
        if(!isEmpty(user.getPassWord())){
        	params.put("passWord", user.getPassWord());
		}
        if(!isEmpty(user.getPhone())){
        	params.put("phone", user.getPhone());
		}
        if(!isEmpty(user.getRealName())){
        	params.put("realName", user.getRealName());
		}
        if(!isEmpty(user.getSex())){
        	params.put("sex", user.getSex());
		}
        if(!isEmpty(user.getAddress())){
        	params.put("address", user.getAddress());
		}
        if(!isEmpty(user.getEmail())){
        	params.put("email", user.getEmail());
		}
		//分页查询
		Pager<User> pagers = userService.findByMap(params);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("pagers", pagers);
		jsonObject.put("obj", user);
		return jsonObject.toString();
	}
	
	
	/**
	 * ajax 添加
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/exAddJson", method = RequestMethod.POST)
	@ResponseBody
	public String exAddJson(User user, Model model, HttpServletRequest request, HttpServletResponse response) {
		userService.insert(user);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("message", "添加成功");
		return jsonObject.toString();
	}
	

	/**
	 * ajax 修改
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/exUpdate.json", method = RequestMethod.POST)
	@ResponseBody
	public String exUpdateJson(User user, Model model, HttpServletRequest request, HttpServletResponse response) {
		//1.通过实体类修改，可以多传修改条件
		userService.updateById(user);
		//2.通过主键id修改
		//userService.updateById(user);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("message", "修改成功");
		return jsonObject.toString();
	}

	/**
	 * ajax 删除
	 * @return
	 */
	@RequestMapping(value = "/delete.json", method = RequestMethod.POST)
	@ResponseBody
	public String exDeleteJson(Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
		///1.通过主键删除
		userService.deleteById(id);
		/*以下是多种删除方式*/
//		//2.通过实体条件删除
//		userService.deleteByEntity(user);
//		//3.通过参数删除
//        //通过map查询
//		Map<String,Object> params = new HashMap<String,Object>();
//		
//        if(!isEmpty(user.getUserName())){
//        	params.put("userName", user.getUserName());
//		}
//       
//        if(!isEmpty(user.getPassWord())){
//        	params.put("passWord", user.getPassWord());
//		}
//       
//        if(!isEmpty(user.getPhone())){
//        	params.put("phone", user.getPhone());
//		}
//       
//        if(!isEmpty(user.getRealName())){
//        	params.put("realName", user.getRealName());
//		}
//       
//        if(!isEmpty(user.getSex())){
//        	params.put("sex", user.getSex());
//		}
//       
//        if(!isEmpty(user.getAddress())){
//        	params.put("address", user.getAddress());
//		}
//       
//        if(!isEmpty(user.getEmail())){
//        	params.put("email", user.getEmail());
//		}
//       
//		userService.deleteByMap(params);
//		//4.状态删除
//		User load = userService.getById(user.getId())
//		load.setIsDelete(1);
//		userService.updateById(load);
		//5.状态删除
		//User load = userService.load(id);
		//load.setIsDelete(1);
		//userService.updateById(load);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("message", "删除成功");
		return jsonObject.toString();
	}
	/**
	 * 单文件上传
	 * @param file
	 * @param request
	 * @param model
	 * @return
	 */
    @RequestMapping(value = "/saveFile")  
    public String saveFile(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, Model model) {  
  
        System.out.println("开始");  
        String path = request.getSession().getServletContext().getRealPath("/upload");  
        String fileName = file.getOriginalFilename();  
        System.out.println(path);  
        File targetFile = new File(path, fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  
        //保存  
        try {  
            file.transferTo(targetFile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        return "";  
    }  
	
	
	/**
	 * springMvc多文件上传
	 * @param files
	 * @param id
	 * @return
	 */
    @RequestMapping(value = "/saveFiles")
    public String saveFiles(@RequestParam("file") CommonsMultipartFile[] files,Integer id,HttpServletRequest request){
		for(int i = 0;i<files.length;i++){
	      	System.out.println("fileName---------->" + files[i].getOriginalFilename());
		  if(!files[i].isEmpty()){
            int pre = (int) System.currentTimeMillis();
	     	try {
			//拿到输出流，同时重命名上传的文件
			 String filePath = request.getRealPath("/upload");
			 File f=new File(filePath);
			 if(!f.exists()){
				f.mkdirs();
			 }
		     String fileNmae=new Date().getTime() + files[i].getOriginalFilename();
		     File file=new File(filePath+"/"+pre + files[i].getOriginalFilename());
			  if(!file.exists()){
				  file.createNewFile();
			 }
			  files[i].transferTo(file);
		     } catch (Exception e) {
				e.printStackTrace();
				System.out.println("上传出错");
			 }
		  }
		}
	  return "";
	}
 // --------------------------------------- 华丽分割线 ------------------------------
	
	
}
