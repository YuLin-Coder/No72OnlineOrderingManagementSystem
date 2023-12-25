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
@RequestMapping("/itemCategory")
public class ItemCategoryController extends BaseController {
	
	
	/**
	 * 依赖注入 start dao/service/===
	 */
	@Autowired
	private ItemCategoryService itemCategoryService;
	
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
	public String listAll(ItemCategory itemCategory, Model model, HttpServletRequest request, HttpServletResponse response){
		List<ItemCategory> listAll = itemCategoryService.listAll();
		model.addAttribute("list", listAll);
		return "itemCategory/itemCategory";
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
	public String listByEntity(ItemCategory itemCategory, Model model, HttpServletRequest request, HttpServletResponse response){
		List<ItemCategory> listAll = itemCategoryService.listAllByEntity(itemCategory);
		model.addAttribute("list", listAll);
		return "itemCategory/itemCategory";
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
	public String listByMap(ItemCategory itemCategory, Model model, HttpServletRequest request, HttpServletResponse response){
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
	        if(!isEmpty(itemCategory.getName())){
	        	params.put("name", itemCategory.getName());
			}
	        if(!isEmpty(itemCategory.getPid())){
	        	params.put("pid", itemCategory.getPid());
			}
	        if(!isEmpty(itemCategory.getIsDelete())){
	        	params.put("isDelete", itemCategory.getIsDelete());
			}
	    List<ItemCategory> listAll = itemCategoryService.listByMap(params);
		model.addAttribute("list", listAll);
		return "itemCategory/itemCategory";
	}
	
	
	/*********************************查询列表【分页】***********************************************/
	@Autowired
	private ItemService itemService;
	
	
	/**
	 * 分页查询 返回list对象(通过对象)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByObj")
	public String findByObj(ItemCategory itemCategory, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		Pager<ItemCategory> pagers = itemCategoryService.findByEntity(itemCategory);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", itemCategory);
		return "itemCategory/itemCategory";
	}
	
	/**
	 * 分页查询 返回list对象(通过对By Sql)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findBySql")
	public String findBySql(ItemCategory itemCategory, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		String sql = "SELECT * FROM item_category WHERE isDelete = 0 and pid is null";
//        if(!isEmpty(itemCategory.getName())){
//        	sql += " and name like '%"+itemCategory.getName()+"%'";
//		}
//        if(!isEmpty(itemCategory.getPid())){
//        	sql += " and pid like '%"+itemCategory.getPid()+"%'";
//		}
//        if(!isEmpty(itemCategory.getIsDelete())){
//        	sql += " and isDelete like '%"+itemCategory.getIsDelete()+"%'";
//		}
       sql += " ORDER BY ID DESC ";
		Pager<ItemCategory> pagers = itemCategoryService.findBySqlRerturnEntity(sql);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", itemCategory);
		return "itemCategory/itemCategory";
	}
	
	
	@RequestMapping(value = "/tj")
	public String tj(ItemCategory itemCategory, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		String sql = "SELECT * FROM item_category WHERE isDelete = 0 and pid is null";
        sql += " ORDER BY ID DESC ";
		List<ItemCategory> list = itemCategoryService.listBySqlReturnEntity(sql); 

		List<Map<String,Object>> maps = new ArrayList<Map<String,Object>>();
		
		
		
		List<TjDto> res = new ArrayList<TjDto>();
		
		if (!CollectionUtils.isEmpty(list)){
			
			for (ItemCategory c : list){
				TjDto td = new TjDto();
				int tot = 0;
				List<Item> listBySqlReturnEntity = itemService.listBySqlReturnEntity("SELECT * FROM item WHERE 1=1 and isDelete =0 and category_id_one="+c.getId());
				
				if (!CollectionUtils.isEmpty(listBySqlReturnEntity)){
					
					for (Item i : listBySqlReturnEntity){
						tot+= i.getGmNum();
					}
				}
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("name", c.getName());
				map.put("value", tot);
				maps.add(map);
			}
			
		}
		
		//存储查询条件
		model.addAttribute("maps", maps);
		return "itemCategory/tj";
	}
	
	
	@RequestMapping(value = "/findBySql2")
	public String findBySql2(ItemCategory itemCategory, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		String sql = "SELECT * FROM item_category WHERE isDelete = 0 and pid = "+itemCategory.getPid();
        if(!isEmpty(itemCategory.getName())){
        	sql += " and name like '%"+itemCategory.getName()+"%'";
		}
//        if(!isEmpty(itemCategory.getPid())){
//        	sql += " and pid like '%"+itemCategory.getPid()+"%'";
//		}
//        if(!isEmpty(itemCategory.getIsDelete())){
//        	sql += " and isDelete like '%"+itemCategory.getIsDelete()+"%'";
//		}
       sql += " ORDER BY ID DESC ";
		Pager<ItemCategory> pagers = itemCategoryService.findBySqlRerturnEntity(sql);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", itemCategory);
		return "itemCategory/itemCategory2";
	}
	
	
	
	/**
	 * 分页查询 返回list对象(通过Map)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByMap")
	public String findByMap(ItemCategory itemCategory, Model model, HttpServletRequest request, HttpServletResponse response) {
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
        if(!isEmpty(itemCategory.getName())){
        	params.put("name", itemCategory.getName());
		}
        if(!isEmpty(itemCategory.getPid())){
        	params.put("pid", itemCategory.getPid());
		}
        if(!isEmpty(itemCategory.getIsDelete())){
        	params.put("isDelete", itemCategory.getIsDelete());
		}
		//分页查询
		Pager<ItemCategory> pagers = itemCategoryService.findByMap(params);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", itemCategory);
		return "itemCategory/itemCategory";
	}
	
	/**********************************【增删改】******************************************************/
	
	/**
	 * 跳至添加页面
	 * @return
	 */
	@RequestMapping(value = "/add")
	public String add() {
		return "itemCategory/add";
	}
	
	@RequestMapping(value = "/add2")
	public String add2(int pid,Model model) {
		
		model.addAttribute("pid",pid);
		
		return "itemCategory/add2";
	}

	/**
	 * 跳至详情页面
	 * @return
	 */
	@RequestMapping(value = "/view")
	public String view(Integer id,Model model) {
		ItemCategory obj = itemCategoryService.load(id);
		model.addAttribute("obj",obj);
		return "itemCategory/view";
	}
	
	/**
	 * 添加执行
	 * @return
	 */
	@RequestMapping(value = "/exAdd")
	public String exAdd(ItemCategory itemCategory, Model model, HttpServletRequest request, HttpServletResponse response) {
		itemCategory.setIsDelete(0);
		itemCategoryService.insert(itemCategory);
				
		return "redirect:/itemCategory/findBySql.action";
	}
	
	
	@RequestMapping(value = "/exAdd2")
	public String exAdd2(ItemCategory itemCategory, Model model, HttpServletRequest request, HttpServletResponse response) {
		itemCategory.setIsDelete(0);
		itemCategoryService.insert(itemCategory);
				
		return "redirect:/itemCategory/findBySql2.action?pid="+itemCategory.getPid();
	}
	
	/**
	 * 跳至修改页面
	 * @return
	 */
	@RequestMapping(value = "/update")
	public String update(Integer id,Model model) {
		ItemCategory obj = itemCategoryService.load(id);
		model.addAttribute("obj",obj);
		return "itemCategory/update";
	}
	
	
	
	
	@RequestMapping(value = "/update2")
	public String update2(Integer id,Model model) {
		ItemCategory obj = itemCategoryService.load(id);
		model.addAttribute("obj",obj);
		return "itemCategory/update2";
	}
	
	/**
	 * 添加修改
	 * @return
	 */
	@RequestMapping(value = "/exUpdate")
	public String exUpdate(ItemCategory itemCategory, Model model, HttpServletRequest request, HttpServletResponse response) {
		//1.通过实体类修改，可以多传修改条件
		itemCategoryService.updateById(itemCategory);
		//2.通过主键id修改
		//itemCategoryService.updateById(itemCategory);
		
				
		return "redirect:/itemCategory/findBySql.action";
	}
	
	@RequestMapping(value = "/exUpdate2")
	public String exUpdate2(ItemCategory itemCategory, Model model, HttpServletRequest request, HttpServletResponse response) {
		//1.通过实体类修改，可以多传修改条件
		itemCategoryService.updateById(itemCategory);
		//2.通过主键id修改
		//itemCategoryService.updateById(itemCategory);
		
		//同步redis
				redisUpdate();
				
		return "redirect:/itemCategory/findBySql2.action?pid="+itemCategory.getPid();
	}
	
	
	/**
	 * 当有添加修改删除的时候，就需要重新设置一下redis
	 */
	public void redisUpdate(){
		
	}
	
	/**
	 * 删除通过主键
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public String delete(Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
		///1.通过主键删除
		//itemCategoryService.deleteById(id);
		/*以下是多种删除方式*/
//		//2.通过实体条件删除
//		itemCategoryService.deleteByEntity(itemCategory);
//		//3.通过参数删除
//     //通过map查询
//		Map<String,Object> params = new HashMap<String,Object>();
//		
//        if(!isEmpty(itemCategory.getName())){
//        	params.put("name", itemCategory.getName());
//		}
//       
//        if(!isEmpty(itemCategory.getPid())){
//        	params.put("pid", itemCategory.getPid());
//		}
//       
//        if(!isEmpty(itemCategory.getIsDelete())){
//        	params.put("isDelete", itemCategory.getIsDelete());
//		}
//       
//		itemCategoryService.deleteByMap(params);
//		//4.状态删除
//		ItemCategory load = itemCategoryService.getById(itemCategory.getId())
//		load.setIsDelete(1);
//		itemCategoryService.update(load);
		//5.状态删除
		ItemCategory load = itemCategoryService.load(id);
		load.setIsDelete(1);
		
		//将儿子也删除
		String sql2 = "SELECT * FROM item_category WHERE isDelete = 0 and pid = "+load.getId();
		 List<ItemCategory> childrens = itemCategoryService.listBySqlReturnEntity(sql2);
		 if (!CollectionUtils.isEmpty(childrens)){
			for (ItemCategory i : childrens){ 
				i.setIsDelete(1);
				itemCategoryService.updateById(i);
			}
		 }
		itemCategoryService.updateById(load);
		
		return "redirect:/itemCategory/findBySql.action";
	}
	
	
	@RequestMapping(value = "/delete2")
	public String delete2(Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
		ItemCategory load = itemCategoryService.load(id);
		load.setIsDelete(1);
		itemCategoryService.updateById(load);
		
		//将儿子也删除
		String sql2 = "SELECT * FROM item_category WHERE isDelete = 0 and pid = "+load.getId();
		 List<ItemCategory> childrens = itemCategoryService.listBySqlReturnEntity(sql2);
		 if (!CollectionUtils.isEmpty(childrens)){
			for (ItemCategory i : childrens){ 
				i.setIsDelete(1);
				itemCategoryService.updateById(i);
			}
		 }
				
				
		return "redirect:/itemCategory/findBySql2.action?pid="+load.getPid();
		
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
	public String listAllJson(ItemCategory itemCategory, HttpServletRequest request, HttpServletResponse response){
		List<ItemCategory> listAll = itemCategoryService.listAll();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("list", listAll);
		jsonObject.put("obj", itemCategory);
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
	public String listByEntityJson(ItemCategory itemCategory,  HttpServletRequest request, HttpServletResponse response){
		List<ItemCategory> listAll = itemCategoryService.listAllByEntity(itemCategory);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("list", listAll);
		jsonObject.put("obj", itemCategory);
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
	public String listByMapJson(ItemCategory itemCategory,HttpServletRequest request, HttpServletResponse response){
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
	        if(!isEmpty(itemCategory.getName())){
	        	params.put("name", itemCategory.getName());
			}
	        if(!isEmpty(itemCategory.getPid())){
	        	params.put("pid", itemCategory.getPid());
			}
	        if(!isEmpty(itemCategory.getIsDelete())){
	        	params.put("isDelete", itemCategory.getIsDelete());
			}
	    List<ItemCategory> listAll = itemCategoryService.listByMap(params);
	    JSONObject jsonObject = new JSONObject();
		jsonObject.put("list", listAll);
		jsonObject.put("obj", itemCategory);
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
	public String findByObjByEntityJson(ItemCategory itemCategory, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		Pager<ItemCategory> pagers = itemCategoryService.findByEntity(itemCategory);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("pagers", pagers);
		jsonObject.put("obj", itemCategory);
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
	public String findByMapJson(ItemCategory itemCategory,HttpServletRequest request, HttpServletResponse response) {
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
        if(!isEmpty(itemCategory.getName())){
        	params.put("name", itemCategory.getName());
		}
        if(!isEmpty(itemCategory.getPid())){
        	params.put("pid", itemCategory.getPid());
		}
        if(!isEmpty(itemCategory.getIsDelete())){
        	params.put("isDelete", itemCategory.getIsDelete());
		}
		//分页查询
		Pager<ItemCategory> pagers = itemCategoryService.findByMap(params);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("pagers", pagers);
		jsonObject.put("obj", itemCategory);
		return jsonObject.toString();
	}
	
	
	/**
	 * ajax 添加
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/exAddJson", method = RequestMethod.POST)
	@ResponseBody
	public String exAddJson(ItemCategory itemCategory, Model model, HttpServletRequest request, HttpServletResponse response) {
		itemCategoryService.insert(itemCategory);
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
	public String exUpdateJson(ItemCategory itemCategory, Model model, HttpServletRequest request, HttpServletResponse response) {
		//1.通过实体类修改，可以多传修改条件
		itemCategoryService.updateById(itemCategory);
		//2.通过主键id修改
		//itemCategoryService.updateById(itemCategory);
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
		itemCategoryService.deleteById(id);
		/*以下是多种删除方式*/
//		//2.通过实体条件删除
//		itemCategoryService.deleteByEntity(itemCategory);
//		//3.通过参数删除
//        //通过map查询
//		Map<String,Object> params = new HashMap<String,Object>();
//		
//        if(!isEmpty(itemCategory.getName())){
//        	params.put("name", itemCategory.getName());
//		}
//       
//        if(!isEmpty(itemCategory.getPid())){
//        	params.put("pid", itemCategory.getPid());
//		}
//       
//        if(!isEmpty(itemCategory.getIsDelete())){
//        	params.put("isDelete", itemCategory.getIsDelete());
//		}
//       
//		itemCategoryService.deleteByMap(params);
//		//4.状态删除
//		ItemCategory load = itemCategoryService.getById(itemCategory.getId())
//		load.setIsDelete(1);
//		itemCategoryService.updateById(load);
		//5.状态删除
		//ItemCategory load = itemCategoryService.load(id);
		//load.setIsDelete(1);
		//itemCategoryService.updateById(load);
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
