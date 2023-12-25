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
@RequestMapping("/news")
public class NewsController extends BaseController {
	
	
	/**
	 * 依赖注入 start dao/service/===
	 */
	@Autowired
	private NewsService newsService;
	
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
	public String listAll(News news, Model model, HttpServletRequest request, HttpServletResponse response){
		List<News> listAll = newsService.listAll();
		model.addAttribute("list", listAll);
		return "news/news";
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
	public String listByEntity(News news, Model model, HttpServletRequest request, HttpServletResponse response){
		List<News> listAll = newsService.listAllByEntity(news);
		model.addAttribute("list", listAll);
		return "news/news";
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
	public String listByMap(News news, Model model, HttpServletRequest request, HttpServletResponse response){
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
	        if(!isEmpty(news.getName())){
	        	params.put("name", news.getName());
			}
	        if(!isEmpty(news.getContent())){
	        	params.put("content", news.getContent());
			}
	        if(!isEmpty(news.getAddTime())){
	        	params.put("addTime", news.getAddTime());
			}
	    List<News> listAll = newsService.listByMap(params);
		model.addAttribute("list", listAll);
		return "news/news";
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
	public String findByObj(News news, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		Pager<News> pagers = newsService.findByEntity(news);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", news);
		return "news/news";
	}
	
	@RequestMapping(value = "/list")
	public String list(News news, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		Pager<News> pagers = newsService.findByEntity(news);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", news);
		return "news/list";
	}
	
	/**
	 * 分页查询 返回list对象(通过Map)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByMap")
	public String findByMap(News news, Model model, HttpServletRequest request, HttpServletResponse response) {
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
        if(!isEmpty(news.getName())){
        	params.put("name", news.getName());
		}
        if(!isEmpty(news.getContent())){
        	params.put("content", news.getContent());
		}
        if(!isEmpty(news.getAddTime())){
        	params.put("addTime", news.getAddTime());
		}
		//分页查询
		Pager<News> pagers = newsService.findByMap(params);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", news);
		return "news/news";
	}
	
	/**********************************【增删改】******************************************************/
	
	/**
	 * 跳至添加页面
	 * @return
	 */
	@RequestMapping(value = "/add")
	public String add() {
		return "news/add";
	}

	
	/**
	 * 添加执行
	 * @return
	 */
	@RequestMapping(value = "/exAdd")
	public String exAdd(News news, Model model, HttpServletRequest request, HttpServletResponse response) {
		news.setAddTime(new Date());
		newsService.insert(news);
		return "redirect:/news/findByObj";
	}
	
	
	/**
	 * 跳至修改页面
	 * @return
	 */
	@RequestMapping(value = "/update")
	public String update(Integer id,Model model) {
		News obj = newsService.load(id);
		model.addAttribute("obj",obj);
		return "news/update";
	}
	
	/**
	 * 跳至修改页面
	 * @return
	 */
	@RequestMapping(value = "/view")
	public String view(Integer id,Model model) {
		News obj = newsService.load(id);
		model.addAttribute("obj",obj);
		return "news/view";
	}
	
	/**
	 * 添加修改
	 * @return
	 */
	@RequestMapping(value = "/exUpdate")
	public String exUpdate(News news, Model model, HttpServletRequest request, HttpServletResponse response) {
		//1.通过实体类修改，可以多传修改条件
		newsService.updateById(news);
		//2.通过主键id修改
		//newsService.updateById(news);
		return "redirect:/news/findByObj";
	}
	
	/**
	 * 删除通过主键
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public String delete(Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
		///1.通过主键删除
		newsService.deleteById(id);
		/*以下是多种删除方式*/
//		//2.通过实体条件删除
//		newsService.deleteByEntity(news);
//		//3.通过参数删除
//     //通过map查询
//		Map<String,Object> params = new HashMap<String,Object>();
//		
//        if(!isEmpty(news.getName())){
//        	params.put("name", news.getName());
//		}
//       
//        if(!isEmpty(news.getContent())){
//        	params.put("content", news.getContent());
//		}
//       
//        if(!isEmpty(news.getAddTime())){
//        	params.put("addTime", news.getAddTime());
//		}
//       
//		newsService.deleteByMap(params);
//		//4.状态删除
//		News load = newsService.getById(news.getId())
//		load.setIsDelete(1);
//		newsService.update(load);
		//5.状态删除
		//News load = newsService.load(id);
		//load.setIsDelete(1);
		//newsService.update(load);
		return "redirect:/news/findByObj";
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
	public String listAllJson(News news, HttpServletRequest request, HttpServletResponse response){
		List<News> listAll = newsService.listAll();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("list", listAll);
		jsonObject.put("obj", news);
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
	public String listByEntityJson(News news,  HttpServletRequest request, HttpServletResponse response){
		List<News> listAll = newsService.listAllByEntity(news);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("list", listAll);
		jsonObject.put("obj", news);
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
	public String listByMapJson(News news,HttpServletRequest request, HttpServletResponse response){
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
	        if(!isEmpty(news.getName())){
	        	params.put("name", news.getName());
			}
	        if(!isEmpty(news.getContent())){
	        	params.put("content", news.getContent());
			}
	        if(!isEmpty(news.getAddTime())){
	        	params.put("addTime", news.getAddTime());
			}
	    List<News> listAll = newsService.listByMap(params);
	    JSONObject jsonObject = new JSONObject();
		jsonObject.put("list", listAll);
		jsonObject.put("obj", news);
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
	public String findByObjByEntityJson(News news, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		Pager<News> pagers = newsService.findByEntity(news);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("pagers", pagers);
		jsonObject.put("obj", news);
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
	public String findByMapJson(News news,HttpServletRequest request, HttpServletResponse response) {
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
        if(!isEmpty(news.getName())){
        	params.put("name", news.getName());
		}
        if(!isEmpty(news.getContent())){
        	params.put("content", news.getContent());
		}
        if(!isEmpty(news.getAddTime())){
        	params.put("addTime", news.getAddTime());
		}
		//分页查询
		Pager<News> pagers = newsService.findByMap(params);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("pagers", pagers);
		jsonObject.put("obj", news);
		return jsonObject.toString();
	}
	
	
	/**
	 * ajax 添加
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/exAddJson", method = RequestMethod.POST)
	@ResponseBody
	public String exAddJson(News news, Model model, HttpServletRequest request, HttpServletResponse response) {
		newsService.insert(news);
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
	public String exUpdateJson(News news, Model model, HttpServletRequest request, HttpServletResponse response) {
		//1.通过实体类修改，可以多传修改条件
		newsService.update(news);
		//2.通过主键id修改
		//newsService.updateById(news);
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
		newsService.deleteById(id);
		/*以下是多种删除方式*/
//		//2.通过实体条件删除
//		newsService.deleteByEntity(news);
//		//3.通过参数删除
//        //通过map查询
//		Map<String,Object> params = new HashMap<String,Object>();
//		
//        if(!isEmpty(news.getName())){
//        	params.put("name", news.getName());
//		}
//       
//        if(!isEmpty(news.getContent())){
//        	params.put("content", news.getContent());
//		}
//       
//        if(!isEmpty(news.getAddTime())){
//        	params.put("addTime", news.getAddTime());
//		}
//       
//		newsService.deleteByMap(params);
//		//4.状态删除
//		News load = newsService.getById(news.getId())
//		load.setIsDelete(1);
//		newsService.update(load);
		//5.状态删除
		//News load = newsService.load(id);
		//load.setIsDelete(1);
		//newsService.update(load);
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
