package com.neusoft.controller;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.neusoft.base.BaseController;
import com.neusoft.po.Item;
import com.neusoft.po.ItemCategory;
import com.neusoft.service.ItemCategoryService;
import com.neusoft.service.ItemService;
import com.neusoft.service.ScService;
import com.neusoft.utils.Pager;
import com.neusoft.utils.UUIDUtils;


@Controller
@RequestMapping("/item")
public class ItemController extends BaseController {
	@Autowired
	private ScService scService;
	
	/**
	 * 依赖注入 start dao/service/===
	 */
	@Autowired
	private ItemService itemService;
	
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
	public String listAll(Item item, Model model, HttpServletRequest request, HttpServletResponse response){
		List<Item> listAll = itemService.listAll();
		model.addAttribute("list", listAll);
		return "item/item";
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
	public String listByEntity(Item item, Model model, HttpServletRequest request, HttpServletResponse response){
		List<Item> listAll = itemService.listAllByEntity(item);
		model.addAttribute("list", listAll);
		return "item/item";
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
	public String listByMap(Item item, Model model, HttpServletRequest request, HttpServletResponse response){
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
	        if(!isEmpty(item.getName())){
	        	params.put("name", item.getName());
			}
	        if(!isEmpty(item.getPrice())){
	        	params.put("price", item.getPrice());
			}
	        if(!isEmpty(item.getScNum())){
	        	params.put("scNum", item.getScNum());
			}
	        if(!isEmpty(item.getGmNum())){
	        	params.put("gmNum", item.getGmNum());
			}
	        if(!isEmpty(item.getUrl1())){
	        	params.put("url1", item.getUrl1());
			}
	        if(!isEmpty(item.getUrl2())){
	        	params.put("url2", item.getUrl2());
			}
	        if(!isEmpty(item.getUrl3())){
	        	params.put("url3", item.getUrl3());
			}
	        if(!isEmpty(item.getUrl4())){
	        	params.put("url4", item.getUrl4());
			}
	        if(!isEmpty(item.getUrl5())){
	        	params.put("url5", item.getUrl5());
			}
	        if(!isEmpty(item.getMs())){
	        	params.put("ms", item.getMs());
			}
	        if(!isEmpty(item.getPam1())){
	        	params.put("pam1", item.getPam1());
			}
	        if(!isEmpty(item.getPam2())){
	        	params.put("pam2", item.getPam2());
			}
	        if(!isEmpty(item.getPam3())){
	        	params.put("pam3", item.getPam3());
			}
	        if(!isEmpty(item.getType())){
	        	params.put("type", item.getType());
			}
	        if(!isEmpty(item.getIsDelete())){
	        	params.put("isDelete", item.getIsDelete());
			}
	    List<Item> listAll = itemService.listByMap(params);
		model.addAttribute("list", listAll);
		return "item/item";
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
	public String findByObj(Item item, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		Pager<Item> pagers = itemService.findByEntity(item);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", item);
		return "item/item";
	}
	
	/**
	 * 分页查询 返回list对象(通过对By Sql)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findBySql")
	public String findBySql(Item item, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		String sql = "SELECT * FROM item WHERE 1=1 and isDelete =0  ";
        if(!isEmpty(item.getName())){
        	sql += " and name like '%"+item.getName()+"%'";
		}
        if(!isEmpty(item.getPrice())){
        	sql += " and price like '%"+item.getPrice()+"%'";
		}
        if(!isEmpty(item.getScNum())){
        	sql += " and scNum like '%"+item.getScNum()+"%'";
		}
        if(!isEmpty(item.getGmNum())){
        	sql += " and gmNum like '%"+item.getGmNum()+"%'";
		}
        if(!isEmpty(item.getUrl1())){
        	sql += " and url1 like '%"+item.getUrl1()+"%'";
		}
        if(!isEmpty(item.getUrl2())){
        	sql += " and url2 like '%"+item.getUrl2()+"%'";
		}
        if(!isEmpty(item.getUrl3())){
        	sql += " and url3 like '%"+item.getUrl3()+"%'";
		}
        if(!isEmpty(item.getUrl4())){
        	sql += " and url4 like '%"+item.getUrl4()+"%'";
		}
        if(!isEmpty(item.getUrl5())){
        	sql += " and url5 like '%"+item.getUrl5()+"%'";
		}
        if(!isEmpty(item.getMs())){
        	sql += " and ms like '%"+item.getMs()+"%'";
		}
        if(!isEmpty(item.getPam1())){
        	sql += " and pam1 like '%"+item.getPam1()+"%'";
		}
        if(!isEmpty(item.getPam2())){
        	sql += " and pam2 like '%"+item.getPam2()+"%'";
		}
        if(!isEmpty(item.getPam3())){
        	sql += " and pam3 like '%"+item.getPam3()+"%'";
		}
        if(!isEmpty(item.getType())){
        	sql += " and type like '%"+item.getType()+"%'";
		}
        if(!isEmpty(item.getIsDelete())){
        	sql += " and isDelete like '%"+item.getIsDelete()+"%'";
		}
       sql += " ORDER BY ID DESC ";
		Pager<Item> pagers = itemService.findBySqlRerturnEntity(sql);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", item);
		return "item/item";
	}
	
	
	/**
	 * 用户端查询列表
	 * @param item
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/shoplist")
	public String shoplist(Item item,String condition, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		//当二级分类为空，并且condition 为空的时候，不做查询
		/*if (StringUtils.isEmpty(condition) && item.getCategoryIdTwo() == null){
			return "redirect:/login/uIndex.action";
		}*/
		
		//直接点击类型过来的
		 //直接冲数据库查询
			String sql = "SELECT * FROM item WHERE 1=1 and isDelete =0 " ;
			  if(!isEmpty(item.getCategoryIdTwo())){
		        	sql += " and category_id_two= "+item.getCategoryIdTwo();
				}
			
			  if(!isEmpty(condition)){
		        	sql += " and name like '%"+condition+"%'";
				}
			if (!StringUtils.isEmpty(item.getPrice())){
				sql += " ORDER BY (price+0) DESC";
			}
			if (item.getGmNum() != null){
				sql += " ORDER BY gmNum DESC";
			}
			
			if (StringUtils.isEmpty(item.getPrice()) && item.getGmNum() == null){
				sql += " ORDER BY id DESC";
			}
			Pager<Item> pagers = itemService.findBySqlRerturnEntity(sql);
			model.addAttribute("pagers", pagers);
		model.addAttribute("obj", item);
		model.addAttribute("condition", condition);
		return "item/shoplist";
		
	}
	/**
	 * 分页查询 返回list对象(通过Map)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByMap")
	public String findByMap(Item item, Model model, HttpServletRequest request, HttpServletResponse response) {
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
        if(!isEmpty(item.getName())){
        	params.put("name", item.getName());
		}
        if(!isEmpty(item.getPrice())){
        	params.put("price", item.getPrice());
		}
        if(!isEmpty(item.getScNum())){
        	params.put("scNum", item.getScNum());
		}
        if(!isEmpty(item.getGmNum())){
        	params.put("gmNum", item.getGmNum());
		}
        if(!isEmpty(item.getUrl1())){
        	params.put("url1", item.getUrl1());
		}
        if(!isEmpty(item.getUrl2())){
        	params.put("url2", item.getUrl2());
		}
        if(!isEmpty(item.getUrl3())){
        	params.put("url3", item.getUrl3());
		}
        if(!isEmpty(item.getUrl4())){
        	params.put("url4", item.getUrl4());
		}
        if(!isEmpty(item.getUrl5())){
        	params.put("url5", item.getUrl5());
		}
        if(!isEmpty(item.getMs())){
        	params.put("ms", item.getMs());
		}
        if(!isEmpty(item.getPam1())){
        	params.put("pam1", item.getPam1());
		}
        if(!isEmpty(item.getPam2())){
        	params.put("pam2", item.getPam2());
		}
        if(!isEmpty(item.getPam3())){
        	params.put("pam3", item.getPam3());
		}
        if(!isEmpty(item.getType())){
        	params.put("type", item.getType());
		}
        if(!isEmpty(item.getIsDelete())){
        	params.put("isDelete", item.getIsDelete());
		}
		//分页查询
		Pager<Item> pagers = itemService.findByMap(params);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", item);
		return "item/item";
	}
	
	/**********************************【增删改】******************************************************/
	
	/**
	 * 跳至添加页面
	 * @return
	 */
	@RequestMapping(value = "/add")
	public String add(Model model) {
		String sql = "SELECT * FROM item_category WHERE isDelete = 0 and pid is not null";
		List<ItemCategory> listBySqlReturnEntity = itemCategoryService.listBySqlReturnEntity(sql);
		model.addAttribute("types",listBySqlReturnEntity);
		return "item/add";
	}

	/**
	 * 跳至详情页面
	 * @return
	 */
	@RequestMapping(value = "/view")
	public String view(Integer id,Model model) {
		Item obj = itemService.load(id);
		model.addAttribute("obj",obj);
		//查询是否收藏
		return "item/view";
	}
	
	/**
	 * 添加执行
	 * @return
	 */
	@RequestMapping(value = "/exAdd")
	public String exAdd(Item item, @RequestParam("file") CommonsMultipartFile[] files, HttpServletRequest request, Model model) throws IllegalStateException, IOException {  
		
		
		if (files.length > 0){
			 // 0 1 2 3 4
			for (int s = 0;s < files.length;s++){
				 System.out.println("开始");  
		          long  startTime=System.currentTimeMillis();
		          System.out.println("fileName："+files[s].getOriginalFilename());
		          String n = UUIDUtils.create();
		          String path="C:\\work\\code\\YuLin-Coder\\No72OnlineOrderingManagementSystem\\WebRoot\\resource\\upload\\"+n+files[s].getOriginalFilename();
		          System.out.println("===================================================");
		           System.out.println(path);
		          File newFile=new File(path);
		          //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
		          files[s].transferTo(newFile);
		          long  endTime=System.currentTimeMillis();
		          System.out.println("方法二的运行时间："+String.valueOf(endTime-startTime)+"ms");
		         System.out.println("*********************************************************");
		         System.out.println("*********************************************************");
		         
		         if (s == 0){
		        	 item.setUrl1("\\upload\\"+n+files[s].getOriginalFilename());
		         }
		         if (s == 1){
		        	 item.setUrl2("\\upload\\"+n+files[s].getOriginalFilename());
		         }
		         if (s == 2){
		        	 item.setUrl3("\\upload\\"+n+files[s].getOriginalFilename());
		         }
		         if (s == 3){
		        	 item.setUrl4("\\upload\\"+n+files[s].getOriginalFilename());
		         }
		         if (s == 4){
		        	 item.setUrl5("\\upload\\"+n+files[s].getOriginalFilename());
		         }
		    	}
			}
	    	 
		item.setGmNum(0);
		item.setIsDelete(0);
		item.setScNum(0);
		ItemCategory byId = itemCategoryService.getById(item.getCategoryIdTwo());
		item.setCategoryIdOne(byId.getPid());
		itemService.insert(item);
		return "redirect:/item/findBySql.action";
	}
	/**
	 * 跳至修改页面
	 * @return
	 */
	@RequestMapping(value = "/update")
	public String update(Integer id,Model model) {
		Item obj = itemService.load(id);
		String sql = "SELECT * FROM item_category WHERE isDelete = 0 and pid is not null";
		List<ItemCategory> listBySqlReturnEntity = itemCategoryService.listBySqlReturnEntity(sql);
		model.addAttribute("types",listBySqlReturnEntity);
		model.addAttribute("obj",obj);
		return "item/update";
	}
	
	/**
	 * 添加修改
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping(value = "/exUpdate")
	public String exUpdate(Item item, Model model, @RequestParam("file") CommonsMultipartFile[] files, HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {
		//1.通过实体类修改，可以多传修改条件
		ItemCategory byId = itemCategoryService.getById(item.getCategoryIdTwo());
		item.setCategoryIdOne(byId.getPid());
		
		if (files.length > 0){
			 // 0 1 2 3 4
			for (int s = 0;s < files.length;s++){
				 System.out.println("开始");  
		          long  startTime=System.currentTimeMillis();
		          System.out.println("fileName："+files[s].getOriginalFilename());
		          String n = UUIDUtils.create();
		          String path="C:\\work\\code\\YuLin-Coder\\No72OnlineOrderingManagementSystem\\WebRoot\\resource\\upload\\"+n+files[s].getOriginalFilename();
		          System.out.println("===================================================");
		           System.out.println(path);
		          File newFile=new File(path);
		          //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
		          files[s].transferTo(newFile);
		          long  endTime=System.currentTimeMillis();
		          System.out.println("方法二的运行时间："+String.valueOf(endTime-startTime)+"ms");
		         System.out.println("*********************************************************");
		         System.out.println("*********************************************************");
		         
		         if (s == 0){
		        	 item.setUrl1("\\upload\\"+n+files[s].getOriginalFilename());
		         }
		         if (s == 1){
		        	 item.setUrl2("\\upload\\"+n+files[s].getOriginalFilename());
		         }
		         if (s == 2){
		        	 item.setUrl3("\\upload\\"+n+files[s].getOriginalFilename());
		         }
		         if (s == 3){
		        	 item.setUrl4("\\upload\\"+n+files[s].getOriginalFilename());
		         }
		         if (s == 4){
		        	 item.setUrl5("\\upload\\"+n+files[s].getOriginalFilename());
		         }
		    	}
			}
		
		
		itemService.updateById(item);
		
		Item byId2 = itemService.getById(item.getId());
		//2.通过主键id修改
		//itemService.updateById(item);
		
		return "redirect:/item/findBySql.action";
	}
	
	/**
	 * 删除通过主键
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public String delete(Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
		///1.通过主键删除
		//itemService.deleteById(id);
		/*以下是多种删除方式*/
//		//2.通过实体条件删除
//		itemService.deleteByEntity(item);
//		//3.通过参数删除
//     //通过map查询
//		Map<String,Object> params = new HashMap<String,Object>();
//		
//        if(!isEmpty(item.getName())){
//        	params.put("name", item.getName());
//		}
//       
//        if(!isEmpty(item.getPrice())){
//        	params.put("price", item.getPrice());
//		}
//       
//        if(!isEmpty(item.getScNum())){
//        	params.put("scNum", item.getScNum());
//		}
//       
//        if(!isEmpty(item.getGmNum())){
//        	params.put("gmNum", item.getGmNum());
//		}
//       
//        if(!isEmpty(item.getUrl1())){
//        	params.put("url1", item.getUrl1());
//		}
//       
//        if(!isEmpty(item.getUrl2())){
//        	params.put("url2", item.getUrl2());
//		}
//       
//        if(!isEmpty(item.getUrl3())){
//        	params.put("url3", item.getUrl3());
//		}
//       
//        if(!isEmpty(item.getUrl4())){
//        	params.put("url4", item.getUrl4());
//		}
//       
//        if(!isEmpty(item.getUrl5())){
//        	params.put("url5", item.getUrl5());
//		}
//       
//        if(!isEmpty(item.getMs())){
//        	params.put("ms", item.getMs());
//		}
//       
//        if(!isEmpty(item.getPam1())){
//        	params.put("pam1", item.getPam1());
//		}
//       
//        if(!isEmpty(item.getPam2())){
//        	params.put("pam2", item.getPam2());
//		}
//       
//        if(!isEmpty(item.getPam3())){
//        	params.put("pam3", item.getPam3());
//		}
//       
//        if(!isEmpty(item.getType())){
//        	params.put("type", item.getType());
//		}
//       
//        if(!isEmpty(item.getIsDelete())){
//        	params.put("isDelete", item.getIsDelete());
//		}
//       
//		itemService.deleteByMap(params);
//		//4.状态删除
//		Item load = itemService.getById(item.getId())
//		load.setIsDelete(1);
//		itemService.update(load);
		//5.状态删除
		Item load = itemService.load(id);
		load.setIsDelete(1);
		itemService.updateById(load);
		
		return "redirect:/item/findBySql.action";
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
	public String listAllJson(Item item, HttpServletRequest request, HttpServletResponse response){
		List<Item> listAll = itemService.listAll();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("list", listAll);
		jsonObject.put("obj", item);
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
	public String listByEntityJson(Item item,  HttpServletRequest request, HttpServletResponse response){
		List<Item> listAll = itemService.listAllByEntity(item);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("list", listAll);
		jsonObject.put("obj", item);
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
	public String listByMapJson(Item item,HttpServletRequest request, HttpServletResponse response){
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
	        if(!isEmpty(item.getName())){
	        	params.put("name", item.getName());
			}
	        if(!isEmpty(item.getPrice())){
	        	params.put("price", item.getPrice());
			}
	        if(!isEmpty(item.getScNum())){
	        	params.put("scNum", item.getScNum());
			}
	        if(!isEmpty(item.getGmNum())){
	        	params.put("gmNum", item.getGmNum());
			}
	        if(!isEmpty(item.getUrl1())){
	        	params.put("url1", item.getUrl1());
			}
	        if(!isEmpty(item.getUrl2())){
	        	params.put("url2", item.getUrl2());
			}
	        if(!isEmpty(item.getUrl3())){
	        	params.put("url3", item.getUrl3());
			}
	        if(!isEmpty(item.getUrl4())){
	        	params.put("url4", item.getUrl4());
			}
	        if(!isEmpty(item.getUrl5())){
	        	params.put("url5", item.getUrl5());
			}
	        if(!isEmpty(item.getMs())){
	        	params.put("ms", item.getMs());
			}
	        if(!isEmpty(item.getPam1())){
	        	params.put("pam1", item.getPam1());
			}
	        if(!isEmpty(item.getPam2())){
	        	params.put("pam2", item.getPam2());
			}
	        if(!isEmpty(item.getPam3())){
	        	params.put("pam3", item.getPam3());
			}
	        if(!isEmpty(item.getType())){
	        	params.put("type", item.getType());
			}
	        if(!isEmpty(item.getIsDelete())){
	        	params.put("isDelete", item.getIsDelete());
			}
	    List<Item> listAll = itemService.listByMap(params);
	    JSONObject jsonObject = new JSONObject();
		jsonObject.put("list", listAll);
		jsonObject.put("obj", item);
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
	public String findByObjByEntityJson(Item item, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		Pager<Item> pagers = itemService.findByEntity(item);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("pagers", pagers);
		jsonObject.put("obj", item);
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
	public String findByMapJson(Item item,HttpServletRequest request, HttpServletResponse response) {
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
        if(!isEmpty(item.getName())){
        	params.put("name", item.getName());
		}
        if(!isEmpty(item.getPrice())){
        	params.put("price", item.getPrice());
		}
        if(!isEmpty(item.getScNum())){
        	params.put("scNum", item.getScNum());
		}
        if(!isEmpty(item.getGmNum())){
        	params.put("gmNum", item.getGmNum());
		}
        if(!isEmpty(item.getUrl1())){
        	params.put("url1", item.getUrl1());
		}
        if(!isEmpty(item.getUrl2())){
        	params.put("url2", item.getUrl2());
		}
        if(!isEmpty(item.getUrl3())){
        	params.put("url3", item.getUrl3());
		}
        if(!isEmpty(item.getUrl4())){
        	params.put("url4", item.getUrl4());
		}
        if(!isEmpty(item.getUrl5())){
        	params.put("url5", item.getUrl5());
		}
        if(!isEmpty(item.getMs())){
        	params.put("ms", item.getMs());
		}
        if(!isEmpty(item.getPam1())){
        	params.put("pam1", item.getPam1());
		}
        if(!isEmpty(item.getPam2())){
        	params.put("pam2", item.getPam2());
		}
        if(!isEmpty(item.getPam3())){
        	params.put("pam3", item.getPam3());
		}
        if(!isEmpty(item.getType())){
        	params.put("type", item.getType());
		}
        if(!isEmpty(item.getIsDelete())){
        	params.put("isDelete", item.getIsDelete());
		}
		//分页查询
		Pager<Item> pagers = itemService.findByMap(params);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("pagers", pagers);
		jsonObject.put("obj", item);
		return jsonObject.toString();
	}
	
	
	/**
	 * ajax 添加
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/exAddJson", method = RequestMethod.POST)
	@ResponseBody
	public String exAddJson(Item item, Model model, HttpServletRequest request, HttpServletResponse response) {
		itemService.insert(item);
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
	public String exUpdateJson(Item item, Model model, HttpServletRequest request, HttpServletResponse response) {
		//1.通过实体类修改，可以多传修改条件
		itemService.updateById(item);
		//2.通过主键id修改
		//itemService.updateById(item);
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
		itemService.deleteById(id);
		/*以下是多种删除方式*/
//		//2.通过实体条件删除
//		itemService.deleteByEntity(item);
//		//3.通过参数删除
//        //通过map查询
//		Map<String,Object> params = new HashMap<String,Object>();
//		
//        if(!isEmpty(item.getName())){
//        	params.put("name", item.getName());
//		}
//       
//        if(!isEmpty(item.getPrice())){
//        	params.put("price", item.getPrice());
//		}
//       
//        if(!isEmpty(item.getScNum())){
//        	params.put("scNum", item.getScNum());
//		}
//       
//        if(!isEmpty(item.getGmNum())){
//        	params.put("gmNum", item.getGmNum());
//		}
//       
//        if(!isEmpty(item.getUrl1())){
//        	params.put("url1", item.getUrl1());
//		}
//       
//        if(!isEmpty(item.getUrl2())){
//        	params.put("url2", item.getUrl2());
//		}
//       
//        if(!isEmpty(item.getUrl3())){
//        	params.put("url3", item.getUrl3());
//		}
//       
//        if(!isEmpty(item.getUrl4())){
//        	params.put("url4", item.getUrl4());
//		}
//       
//        if(!isEmpty(item.getUrl5())){
//        	params.put("url5", item.getUrl5());
//		}
//       
//        if(!isEmpty(item.getMs())){
//        	params.put("ms", item.getMs());
//		}
//       
//        if(!isEmpty(item.getPam1())){
//        	params.put("pam1", item.getPam1());
//		}
//       
//        if(!isEmpty(item.getPam2())){
//        	params.put("pam2", item.getPam2());
//		}
//       
//        if(!isEmpty(item.getPam3())){
//        	params.put("pam3", item.getPam3());
//		}
//       
//        if(!isEmpty(item.getType())){
//        	params.put("type", item.getType());
//		}
//       
//        if(!isEmpty(item.getIsDelete())){
//        	params.put("isDelete", item.getIsDelete());
//		}
//       
//		itemService.deleteByMap(params);
//		//4.状态删除
//		Item load = itemService.getById(item.getId())
//		load.setIsDelete(1);
//		itemService.updateById(load);
		//5.状态删除
		//Item load = itemService.load(id);
		//load.setIsDelete(1);
		//itemService.updateById(load);
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
