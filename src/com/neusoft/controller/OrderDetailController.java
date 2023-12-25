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
@RequestMapping("/orderDetail")
public class OrderDetailController extends BaseController {
	
	
	/**
	 * 依赖注入 start dao/service/===
	 */
	@Autowired
	private OrderDetailService orderDetailService;
	
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
	public String listAll(OrderDetail orderDetail, Model model, HttpServletRequest request, HttpServletResponse response){
		List<OrderDetail> listAll = orderDetailService.listAll();
		model.addAttribute("list", listAll);
		return "orderDetail/orderDetail";
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
	public String listByEntity(OrderDetail orderDetail, Model model, HttpServletRequest request, HttpServletResponse response){
		List<OrderDetail> listAll = orderDetailService.listAllByEntity(orderDetail);
		model.addAttribute("list", listAll);
		return "orderDetail/orderDetail";
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
	public String listByMap(OrderDetail orderDetail, Model model, HttpServletRequest request, HttpServletResponse response){
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
	        if(!isEmpty(orderDetail.getItemId())){
	        	params.put("itemId", orderDetail.getItemId());
			}
	        if(!isEmpty(orderDetail.getOrderId())){
	        	params.put("orderId", orderDetail.getOrderId());
			}
	        if(!isEmpty(orderDetail.getStatus())){
	        	params.put("status", orderDetail.getStatus());
			}
	    List<OrderDetail> listAll = orderDetailService.listByMap(params);
		model.addAttribute("list", listAll);
		return "orderDetail/orderDetail";
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
	public String findByObj(OrderDetail orderDetail, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		Pager<OrderDetail> pagers = orderDetailService.findByEntity(orderDetail);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", orderDetail);
		return "orderDetail/orderDetail";
	}
	
	/**
	 * 分页查询 返回list对象(通过对By Sql)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findBySql")
	public String findBySql(OrderDetail orderDetail, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		String sql = "SELECT * FROM orderDetail WHERE 1=1 ";
        if(!isEmpty(orderDetail.getItemId())){
        	sql += " and itemId like '%"+orderDetail.getItemId()+"%'";
		}
        if(!isEmpty(orderDetail.getOrderId())){
        	sql += " and orderId like '%"+orderDetail.getOrderId()+"%'";
		}
        if(!isEmpty(orderDetail.getStatus())){
        	sql += " and status like '%"+orderDetail.getStatus()+"%'";
		}
       sql += " ORDER BY ID DESC ";
		Pager<OrderDetail> pagers = orderDetailService.findBySqlRerturnEntity(sql);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", orderDetail);
		return "orderDetail/orderDetail";
	}
	
	
	@RequestMapping(value = "/ulist")
	public String ulist(OrderDetail orderDetail, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		String sql = "SELECT * FROM order_detail WHERE 1=1 and order_id="+orderDetail.getOrderId();
        if(!isEmpty(orderDetail.getItemId())){
        	sql += " and itemId like '%"+orderDetail.getItemId()+"%'";
		}
       /* if(!isEmpty(orderDetail.getOrderId())){
        	sql += " and orderId like '%"+orderDetail.getOrderId()+"%'";
		}*/
        if(!isEmpty(orderDetail.getStatus())){
        	sql += " and status like '%"+orderDetail.getStatus()+"%'";
		}
       sql += " ORDER BY ID DESC ";
		Pager<OrderDetail> pagers = orderDetailService.findBySqlRerturnEntity(sql);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", orderDetail);
		return "orderDetail/ulist";
	}
	/**
	 * 分页查询 返回list对象(通过Map)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByMap")
	public String findByMap(OrderDetail orderDetail, Model model, HttpServletRequest request, HttpServletResponse response) {
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
        if(!isEmpty(orderDetail.getItemId())){
        	params.put("itemId", orderDetail.getItemId());
		}
        if(!isEmpty(orderDetail.getOrderId())){
        	params.put("orderId", orderDetail.getOrderId());
		}
        if(!isEmpty(orderDetail.getStatus())){
        	params.put("status", orderDetail.getStatus());
		}
		//分页查询
		Pager<OrderDetail> pagers = orderDetailService.findByMap(params);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", orderDetail);
		return "orderDetail/orderDetail";
	}
	
	/**********************************【增删改】******************************************************/
	
	/**
	 * 跳至添加页面
	 * @return
	 */
	@RequestMapping(value = "/add")
	public String add() {
		return "orderDetail/add";
	}
	
	
	@RequestMapping(value = "/th")
	public String th(Integer id,Model model) {
		OrderDetail obj = orderDetailService.load(id);
		obj.setStatus(1);
		orderDetailService.updateById(obj);
		model.addAttribute("obj",obj);
		return "redirect:/itemOrder/my";
	}

	/**
	 * 跳至详情页面
	 * @return
	 */
	@RequestMapping(value = "/view")
	public String view(Integer id,Model model) {
		OrderDetail obj = orderDetailService.load(id);
		model.addAttribute("obj",obj);
		return "orderDetail/view";
	}
	
	/**
	 * 添加执行
	 * @return
	 */
	@RequestMapping(value = "/exAdd")
	public String exAdd(OrderDetail orderDetail, Model model, HttpServletRequest request, HttpServletResponse response) {
		orderDetailService.insert(orderDetail);
		return "redirect:/orderDetail/findBySql.action";
	}
	
	
	/**
	 * 跳至修改页面
	 * @return
	 */
	@RequestMapping(value = "/update")
	public String update(Integer id,Model model) {
		OrderDetail obj = orderDetailService.load(id);
		model.addAttribute("obj",obj);
		return "orderDetail/update";
	}
	
	/**
	 * 添加修改
	 * @return
	 */
	@RequestMapping(value = "/exUpdate")
	public String exUpdate(OrderDetail orderDetail, Model model, HttpServletRequest request, HttpServletResponse response) {
		//1.通过实体类修改，可以多传修改条件
		orderDetailService.updateById(orderDetail);
		//2.通过主键id修改
		//orderDetailService.updateById(orderDetail);
		return "redirect:/orderDetail/findBySql.action";
	}
	
	/**
	 * 删除通过主键
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public String delete(Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
		///1.通过主键删除
		orderDetailService.deleteById(id);
		/*以下是多种删除方式*/
//		//2.通过实体条件删除
//		orderDetailService.deleteByEntity(orderDetail);
//		//3.通过参数删除
//     //通过map查询
//		Map<String,Object> params = new HashMap<String,Object>();
//		
//        if(!isEmpty(orderDetail.getItemId())){
//        	params.put("itemId", orderDetail.getItemId());
//		}
//       
//        if(!isEmpty(orderDetail.getOrderId())){
//        	params.put("orderId", orderDetail.getOrderId());
//		}
//       
//        if(!isEmpty(orderDetail.getStatus())){
//        	params.put("status", orderDetail.getStatus());
//		}
//       
//		orderDetailService.deleteByMap(params);
//		//4.状态删除
//		OrderDetail load = orderDetailService.getById(orderDetail.getId())
//		load.setIsDelete(1);
//		orderDetailService.update(load);
		//5.状态删除
		//OrderDetail load = orderDetailService.load(id);
		//load.setIsDelete(1);
		//orderDetailService.update(load);
		return "redirect:/orderDetail/findBySql.action";
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
	public String listAllJson(OrderDetail orderDetail, HttpServletRequest request, HttpServletResponse response){
		List<OrderDetail> listAll = orderDetailService.listAll();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("list", listAll);
		jsonObject.put("obj", orderDetail);
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
	public String listByEntityJson(OrderDetail orderDetail,  HttpServletRequest request, HttpServletResponse response){
		List<OrderDetail> listAll = orderDetailService.listAllByEntity(orderDetail);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("list", listAll);
		jsonObject.put("obj", orderDetail);
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
	public String listByMapJson(OrderDetail orderDetail,HttpServletRequest request, HttpServletResponse response){
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
	        if(!isEmpty(orderDetail.getItemId())){
	        	params.put("itemId", orderDetail.getItemId());
			}
	        if(!isEmpty(orderDetail.getOrderId())){
	        	params.put("orderId", orderDetail.getOrderId());
			}
	        if(!isEmpty(orderDetail.getStatus())){
	        	params.put("status", orderDetail.getStatus());
			}
	    List<OrderDetail> listAll = orderDetailService.listByMap(params);
	    JSONObject jsonObject = new JSONObject();
		jsonObject.put("list", listAll);
		jsonObject.put("obj", orderDetail);
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
	public String findByObjByEntityJson(OrderDetail orderDetail, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		Pager<OrderDetail> pagers = orderDetailService.findByEntity(orderDetail);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("pagers", pagers);
		jsonObject.put("obj", orderDetail);
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
	public String findByMapJson(OrderDetail orderDetail,HttpServletRequest request, HttpServletResponse response) {
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
        if(!isEmpty(orderDetail.getItemId())){
        	params.put("itemId", orderDetail.getItemId());
		}
        if(!isEmpty(orderDetail.getOrderId())){
        	params.put("orderId", orderDetail.getOrderId());
		}
        if(!isEmpty(orderDetail.getStatus())){
        	params.put("status", orderDetail.getStatus());
		}
		//分页查询
		Pager<OrderDetail> pagers = orderDetailService.findByMap(params);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("pagers", pagers);
		jsonObject.put("obj", orderDetail);
		return jsonObject.toString();
	}
	
	
	/**
	 * ajax 添加
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/exAddJson", method = RequestMethod.POST)
	@ResponseBody
	public String exAddJson(OrderDetail orderDetail, Model model, HttpServletRequest request, HttpServletResponse response) {
		orderDetailService.insert(orderDetail);
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
	public String exUpdateJson(OrderDetail orderDetail, Model model, HttpServletRequest request, HttpServletResponse response) {
		//1.通过实体类修改，可以多传修改条件
		orderDetailService.updateById(orderDetail);
		//2.通过主键id修改
		//orderDetailService.updateById(orderDetail);
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
		orderDetailService.deleteById(id);
		/*以下是多种删除方式*/
//		//2.通过实体条件删除
//		orderDetailService.deleteByEntity(orderDetail);
//		//3.通过参数删除
//        //通过map查询
//		Map<String,Object> params = new HashMap<String,Object>();
//		
//        if(!isEmpty(orderDetail.getItemId())){
//        	params.put("itemId", orderDetail.getItemId());
//		}
//       
//        if(!isEmpty(orderDetail.getOrderId())){
//        	params.put("orderId", orderDetail.getOrderId());
//		}
//       
//        if(!isEmpty(orderDetail.getStatus())){
//        	params.put("status", orderDetail.getStatus());
//		}
//       
//		orderDetailService.deleteByMap(params);
//		//4.状态删除
//		OrderDetail load = orderDetailService.getById(orderDetail.getId())
//		load.setIsDelete(1);
//		orderDetailService.updateById(load);
		//5.状态删除
		//OrderDetail load = orderDetailService.load(id);
		//load.setIsDelete(1);
		//orderDetailService.updateById(load);
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
