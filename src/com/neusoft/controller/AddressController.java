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
import org.springframework.util.CollectionUtils;
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
@RequestMapping("/address")
public class AddressController extends BaseController {
	
	
	/**
	 * 依赖注入 start dao/service/===
	 */
	@Autowired
	private AddressService addressService;
	
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
	public String listAll(Address address, Model model, HttpServletRequest request, HttpServletResponse response){
		List<Address> listAll = addressService.listAll();
		model.addAttribute("list", listAll);
		return "address/address";
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
	public String listByEntity(Address address, Model model, HttpServletRequest request, HttpServletResponse response){
		List<Address> listAll = addressService.listAllByEntity(address);
		model.addAttribute("list", listAll);
		return "address/address";
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
	public String listByMap(Address address, Model model, HttpServletRequest request, HttpServletResponse response){
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
	        if(!isEmpty(address.getName())){
	        	params.put("name", address.getName());
			}
	        if(!isEmpty(address.getPhone())){
	        	params.put("phone", address.getPhone());
			}
	        if(!isEmpty(address.getArea())){
	        	params.put("area", address.getArea());
			}
	        if(!isEmpty(address.getBm())){
	        	params.put("bm", address.getBm());
			}
	        if(!isEmpty(address.getIsUse())){
	        	params.put("isUse", address.getIsUse());
			}
	    List<Address> listAll = addressService.listByMap(params);
		model.addAttribute("list", listAll);
		return "address/address";
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
	public String findByObj(Address address, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		Pager<Address> pagers = addressService.findByEntity(address);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", address);
		return "address/address";
	}
	
	/**
	 * 分页查询 返回list对象(通过对By Sql)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findBySql")
	public String findBySql(Address address, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		String sql = "SELECT * FROM address WHERE 1=1 ";
        if(!isEmpty(address.getName())){
        	sql += " and name like '%"+address.getName()+"%'";
		}
        if(!isEmpty(address.getPhone())){
        	sql += " and phone like '%"+address.getPhone()+"%'";
		}
        if(!isEmpty(address.getArea())){
        	sql += " and area like '%"+address.getArea()+"%'";
		}
        if(!isEmpty(address.getBm())){
        	sql += " and bm like '%"+address.getBm()+"%'";
		}
        if(!isEmpty(address.getIsUse())){
        	sql += " and isUse like '%"+address.getIsUse()+"%'";
		}
       sql += " ORDER BY ID DESC ";
		Pager<Address> pagers = addressService.findBySqlRerturnEntity(sql);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", address);
		return "address/address";
	}
	
	
	
	@RequestMapping(value = "/my")
	public String my(Address address, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		Object attribute = request.getSession().getAttribute("userId");
		if (attribute == null){
			return "redirect:/login/uLogin";
		}
		JSONObject js = new JSONObject();
		Integer userId = Integer.valueOf(attribute.toString());
		String sql = "SELECT * FROM address WHERE 1=1 and userId = "+userId;
        if(!isEmpty(address.getName())){
        	sql += " and name like '%"+address.getName()+"%'";
		}
        if(!isEmpty(address.getPhone())){
        	sql += " and phone like '%"+address.getPhone()+"%'";
		}
        if(!isEmpty(address.getArea())){
        	sql += " and area like '%"+address.getArea()+"%'";
		}
        if(!isEmpty(address.getBm())){
        	sql += " and bm like '%"+address.getBm()+"%'";
		}
        if(!isEmpty(address.getIsUse())){
        	sql += " and isUse like '%"+address.getIsUse()+"%'";
		}
       sql += " ORDER BY ID DESC ";
		Pager<Address> pagers = addressService.findBySqlRerturnEntity(sql);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", address);
		return "address/my";
	}
	
	/**
	 * 分页查询 返回list对象(通过Map)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByMap")
	public String findByMap(Address address, Model model, HttpServletRequest request, HttpServletResponse response) {
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
        if(!isEmpty(address.getName())){
        	params.put("name", address.getName());
		}
        if(!isEmpty(address.getPhone())){
        	params.put("phone", address.getPhone());
		}
        if(!isEmpty(address.getArea())){
        	params.put("area", address.getArea());
		}
        if(!isEmpty(address.getBm())){
        	params.put("bm", address.getBm());
		}
        if(!isEmpty(address.getIsUse())){
        	params.put("isUse", address.getIsUse());
		}
		//分页查询
		Pager<Address> pagers = addressService.findByMap(params);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", address);
		return "address/address";
	}
	
	/**********************************【增删改】******************************************************/
	
	/**
	 * 跳至添加页面
	 * @return
	 */
	@RequestMapping(value = "/add")
	public String add() {
		return "address/add";
	}

	/**
	 * 跳至详情页面
	 * @return
	 */
	@RequestMapping(value = "/view")
	public String view(Integer id,Model model) {
		Address obj = addressService.load(id);
		model.addAttribute("obj",obj);
		return "address/view";
	}
	
	/**
	 * 添加执行
	 * @return
	 */
	@RequestMapping(value = "/exAdd")
	public String exAdd(Address address, Model model, HttpServletRequest request, HttpServletResponse response) {
		Object attribute = request.getSession().getAttribute("userId");
		if (attribute == null){
			return "redirect:/login/uLogin";
		}
		Integer userId = Integer.valueOf(attribute.toString());
		address.setUserId(userId);
		address.setIsUse(0);
		addressService.insert(address);
		
		return "redirect:/car/view.action";
	}
	
	
	@RequestMapping(value = "/exAdd1")
	public String exAdd1(Address address, Model model, HttpServletRequest request, HttpServletResponse response) {
		Object attribute = request.getSession().getAttribute("userId");
		if (attribute == null){
			return "redirect:/login/uLogin";
		}
		Integer userId = Integer.valueOf(attribute.toString());
		address.setUserId(userId);
		address.setIsUse(0);
		addressService.insert(address);
		
		return "redirect:/address/my.action";
	}
	
	/**
	 * 跳至修改页面
	 * @return
	 */
	@RequestMapping(value = "/update")
	public String update(Integer id,Model model) {
		Address obj = addressService.load(id);
		model.addAttribute("obj",obj);
		return "address/update";
	}
	
	/**
	 * 添加修改
	 * @return
	 */
	@RequestMapping(value = "/exUpdate")
	public String exUpdate(Address address, Model model, HttpServletRequest request, HttpServletResponse response) {
		//1.通过实体类修改，可以多传修改条件
		addressService.updateById(address);
		//2.通过主键id修改
		//addressService.updateById(address);
		return "redirect:/car/view.action";
	}
	
	@RequestMapping(value = "/exUpdate2")
	public String exUpdate2(Address address, Model model, HttpServletRequest request, HttpServletResponse response) {
		//1.通过实体类修改，可以多传修改条件
		addressService.updateById(address);
		//2.通过主键id修改
		//addressService.updateById(address);
		return "redirect:/address/my.action";
	}
	@RequestMapping(value = "/delete1")
	public String delete1(Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
		///1.通过主键删除
		addressService.deleteById(id);
		/*以下是多种删除方式*/
		return "redirect:/address/my.action";
	}
	@RequestMapping(value = "/mr")
	public String mr(Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
		///1.通过主键删除
		//分页查询
	Object attribute = request.getSession().getAttribute("userId");
	if (attribute == null){
		return "redirect:/login/uLogin";
	}
	JSONObject js = new JSONObject();
	Integer userId = Integer.valueOf(attribute.toString());
	String sql = "SELECT * FROM address WHERE 1=1 and userId = "+userId;
	List<Address> listBySqlReturnEntity = addressService.listBySqlReturnEntity(sql);
	if (!CollectionUtils.isEmpty(listBySqlReturnEntity)){
		for(Address a : listBySqlReturnEntity){
			a.setIsUse(0);
			addressService.updateById(a);
		}
	}
	Address load = addressService.load(id);
	load.setIsUse(1);
	addressService.updateById(load);
		/*以下是多种删除方式*/
		return "redirect:/address/my.action";
	}
	/**
	 * 删除通过主键
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public String delete(Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
		///1.通过主键删除
		addressService.deleteById(id);
		/*以下是多种删除方式*/
//		//2.通过实体条件删除
//		addressService.deleteByEntity(address);
//		//3.通过参数删除
//     //通过map查询
//		Map<String,Object> params = new HashMap<String,Object>();
//		
//        if(!isEmpty(address.getName())){
//        	params.put("name", address.getName());
//		}
//       
//        if(!isEmpty(address.getPhone())){
//        	params.put("phone", address.getPhone());
//		}
//       
//        if(!isEmpty(address.getArea())){
//        	params.put("area", address.getArea());
//		}
//       
//        if(!isEmpty(address.getBm())){
//        	params.put("bm", address.getBm());
//		}
//       
//        if(!isEmpty(address.getIsUse())){
//        	params.put("isUse", address.getIsUse());
//		}
//       
//		addressService.deleteByMap(params);
//		//4.状态删除
//		Address load = addressService.getById(address.getId())
//		load.setIsDelete(1);
//		addressService.update(load);
		//5.状态删除
		//Address load = addressService.load(id);
		//load.setIsDelete(1);
		//addressService.update(load);
		return "redirect:/address/findBySql.action";
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
	public String listAllJson(Address address, HttpServletRequest request, HttpServletResponse response){
		List<Address> listAll = addressService.listAll();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("list", listAll);
		jsonObject.put("obj", address);
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
	public String listByEntityJson(Address address,  HttpServletRequest request, HttpServletResponse response){
		List<Address> listAll = addressService.listAllByEntity(address);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("list", listAll);
		jsonObject.put("obj", address);
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
	public String listByMapJson(Address address,HttpServletRequest request, HttpServletResponse response){
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
	        if(!isEmpty(address.getName())){
	        	params.put("name", address.getName());
			}
	        if(!isEmpty(address.getPhone())){
	        	params.put("phone", address.getPhone());
			}
	        if(!isEmpty(address.getArea())){
	        	params.put("area", address.getArea());
			}
	        if(!isEmpty(address.getBm())){
	        	params.put("bm", address.getBm());
			}
	        if(!isEmpty(address.getIsUse())){
	        	params.put("isUse", address.getIsUse());
			}
	    List<Address> listAll = addressService.listByMap(params);
	    JSONObject jsonObject = new JSONObject();
		jsonObject.put("list", listAll);
		jsonObject.put("obj", address);
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
	public String findByObjByEntityJson(Address address, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		Pager<Address> pagers = addressService.findByEntity(address);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("pagers", pagers);
		jsonObject.put("obj", address);
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
	public String findByMapJson(Address address,HttpServletRequest request, HttpServletResponse response) {
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
        if(!isEmpty(address.getName())){
        	params.put("name", address.getName());
		}
        if(!isEmpty(address.getPhone())){
        	params.put("phone", address.getPhone());
		}
        if(!isEmpty(address.getArea())){
        	params.put("area", address.getArea());
		}
        if(!isEmpty(address.getBm())){
        	params.put("bm", address.getBm());
		}
        if(!isEmpty(address.getIsUse())){
        	params.put("isUse", address.getIsUse());
		}
		//分页查询
		Pager<Address> pagers = addressService.findByMap(params);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("pagers", pagers);
		jsonObject.put("obj", address);
		return jsonObject.toString();
	}
	
	
	/**
	 * ajax 添加
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/exAddJson", method = RequestMethod.POST)
	@ResponseBody
	public String exAddJson(Address address, Model model, HttpServletRequest request, HttpServletResponse response) {
		addressService.insert(address);
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
	public String exUpdateJson(Address address, Model model, HttpServletRequest request, HttpServletResponse response) {
		//1.通过实体类修改，可以多传修改条件
		addressService.updateById(address);
		//2.通过主键id修改
		//addressService.updateById(address);
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
		addressService.deleteById(id);
		/*以下是多种删除方式*/
//		//2.通过实体条件删除
//		addressService.deleteByEntity(address);
//		//3.通过参数删除
//        //通过map查询
//		Map<String,Object> params = new HashMap<String,Object>();
//		
//        if(!isEmpty(address.getName())){
//        	params.put("name", address.getName());
//		}
//       
//        if(!isEmpty(address.getPhone())){
//        	params.put("phone", address.getPhone());
//		}
//       
//        if(!isEmpty(address.getArea())){
//        	params.put("area", address.getArea());
//		}
//       
//        if(!isEmpty(address.getBm())){
//        	params.put("bm", address.getBm());
//		}
//       
//        if(!isEmpty(address.getIsUse())){
//        	params.put("isUse", address.getIsUse());
//		}
//       
//		addressService.deleteByMap(params);
//		//4.状态删除
//		Address load = addressService.getById(address.getId())
//		load.setIsDelete(1);
//		addressService.updateById(load);
		//5.状态删除
		//Address load = addressService.load(id);
		//load.setIsDelete(1);
		//addressService.updateById(load);
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
