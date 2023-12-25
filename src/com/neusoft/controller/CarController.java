package com.neusoft.controller;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/car")
public class CarController extends BaseController {
	@Autowired
	private ItemService itemService;
	@Autowired
	private AddressService addressService;
	/**
	 * 依赖注入 start dao/service/===
	 */
	@Autowired
	private CarService carService;
	
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
	public String listAll(Car car, Model model, HttpServletRequest request, HttpServletResponse response){
		List<Car> listAll = carService.listAll();
		model.addAttribute("list", listAll);
		return "car/car";
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
	public String listByEntity(Car car, Model model, HttpServletRequest request, HttpServletResponse response){
		List<Car> listAll = carService.listAllByEntity(car);
		model.addAttribute("list", listAll);
		return "car/car";
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
	public String listByMap(Car car, Model model, HttpServletRequest request, HttpServletResponse response){
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
	        if(!isEmpty(car.getItemId())){
	        	params.put("itemId", car.getItemId());
			}
	        if(!isEmpty(car.getUserId())){
	        	params.put("userId", car.getUserId());
			}
	        if(!isEmpty(car.getNum())){
	        	params.put("num", car.getNum());
			}
	        if(!isEmpty(car.getTotal())){
	        	params.put("total", car.getTotal());
			}
	    List<Car> listAll = carService.listByMap(params);
		model.addAttribute("list", listAll);
		return "car/car";
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
	public String findByObj(Car car, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		Pager<Car> pagers = carService.findByEntity(car);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", car);
		return "car/car";
	}
	
	/**
	 * 分页查询 返回list对象(通过对By Sql)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findBySql")
	public String findBySql(Car car, Model model, HttpServletRequest request, HttpServletResponse response) {
		Object attribute = request.getSession().getAttribute("userId");
		if (attribute == null){
			return "redirect:/login/uLogin";
		}
		JSONObject js = new JSONObject();
		Integer userId = Integer.valueOf(attribute.toString());
		//分页查询
		String sql = "SELECT * FROM car WHERE 1=1 and user_id = "+userId;
        if(!isEmpty(car.getItemId())){
        	sql += " and itemId like '%"+car.getItemId()+"%'";
		}
        if(!isEmpty(car.getUserId())){
        	sql += " and userId like '%"+car.getUserId()+"%'";
		}
        if(!isEmpty(car.getNum())){
        	sql += " and num like '%"+car.getNum()+"%'";
		}
        if(!isEmpty(car.getTotal())){
        	sql += " and total like '%"+car.getTotal()+"%'";
		}
       sql += " ORDER BY ID DESC ";
		List<Car> listBySqlReturnEntity = carService.listBySqlReturnEntity(sql);
		model.addAttribute("list", listBySqlReturnEntity);
		//存储查询条件
		model.addAttribute("obj", car);
		return "car/car";
	}
	
	
	/**
	 * 分页查询 返回list对象(通过Map)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByMap")
	public String findByMap(Car car, Model model, HttpServletRequest request, HttpServletResponse response) {
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
        if(!isEmpty(car.getItemId())){
        	params.put("itemId", car.getItemId());
		}
        if(!isEmpty(car.getUserId())){
        	params.put("userId", car.getUserId());
		}
        if(!isEmpty(car.getNum())){
        	params.put("num", car.getNum());
		}
        if(!isEmpty(car.getTotal())){
        	params.put("total", car.getTotal());
		}
		//分页查询
		Pager<Car> pagers = carService.findByMap(params);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", car);
		return "car/car";
	}
	
	/**********************************【增删改】******************************************************/
	
	/**
	 * 跳至添加页面
	 * @return
	 */
	@RequestMapping(value = "/add")
	public String add() {
		return "car/add";
	}

	/**
	 * 跳至详情页面
	 * @return
	 */
	@RequestMapping(value = "/view")
	public String view(Model model,HttpServletRequest request,String code) {
		Object attribute = request.getSession().getAttribute("userId");
		if (attribute == null){
			return "redirect:/login/uLogin";
		}
		Integer userId = Integer.valueOf(attribute.toString());
		
		model.addAttribute("code", code);
		return "car/view";
	}
	
	/**
	 * 添加执行
	 * @return
	 */
	@RequestMapping(value = "/exAdd")
	@ResponseBody
	public String exAdd(Car car, Model model, HttpServletRequest request, HttpServletResponse response) {
		Object attribute = request.getSession().getAttribute("userId");
		JSONObject js = new JSONObject();
		if (attribute == null){
			js.put("res", 0);
			return js.toJSONString();
		}
		Integer userId = Integer.valueOf(attribute.toString());
		car.setUserId(userId);
		Item load = itemService.load(car.getItemId());
		String price = load.getPrice();
		Double valueOf = Double.valueOf(price);
		car.setPrice(valueOf);
		if (load.getZk() != null){
			valueOf = (valueOf*load.getZk())/10;
			 BigDecimal bg = new BigDecimal(valueOf).setScale(2, RoundingMode.UP);
			car.setPrice(bg.doubleValue());
			valueOf= bg.doubleValue();
		}
		Integer num = car.getNum();
		Double t = valueOf*num;
		
		 BigDecimal bg = new BigDecimal(t).setScale(2, RoundingMode.UP);
		 double doubleValue = bg.doubleValue();
		car.setTotal(doubleValue+"");
		carService.insert(car);
		js.put("res", 1);
		return js.toJSONString();
	}
	
	
	/**
	 * 跳至修改页面
	 * @return
	 */
	@RequestMapping(value = "/update")
	public String update(Integer id,Model model) {
		Car obj = carService.load(id);
		model.addAttribute("obj",obj);
		return "car/update";
	}
	
	
	@RequestMapping(value = "/js")
	@ResponseBody
	public String js(@RequestBody List<CarDto> list,Model model,HttpServletRequest request) {
		
		
		Object attribute = request.getSession().getAttribute("userId");
		JSONObject js = new JSONObject();
		if (attribute == null){
			js.put("res", 0);
			return js.toJSONString();
		}
		
		
		
		Integer userId = Integer.valueOf(attribute.toString());
		String key = "car_"+userId;
		request.getSession().setAttribute(key, list);
		js.put("res", 1);
		return js.toJSONString();
	}
	
	
	
	@RequestMapping(value = "/js2")
	@ResponseBody
	public String js2(Car car, Model model, HttpServletRequest request, HttpServletResponse response) {
		Object attribute = request.getSession().getAttribute("userId");
		JSONObject js = new JSONObject();
		if (attribute == null){
			js.put("res", 0);
			return js.toJSONString();
		}
		Integer userId = Integer.valueOf(attribute.toString());
		car.setUserId(userId);
		Item load = itemService.load(car.getItemId());
		String price = load.getPrice();
		Long valueOf = Long.valueOf(price);
		Integer num = car.getNum();
		Long t = valueOf*num;
		car.setTotal(t.toString());
		carService.insert(car);
		String key = "car_"+userId;
		List<CarDto> list = new ArrayList<CarDto>();
		CarDto d = new CarDto();
		d.setId(car.getId());
		d.setNum(car.getNum());
		list.add(d);
		request.getSession().setAttribute(key, list);
		js.put("res", 1);
		return js.toJSONString();
	}
	
	/**
	 * 添加修改
	 * @return
	 */
	@RequestMapping(value = "/exUpdate")
	public String exUpdate(Car car, Model model, HttpServletRequest request, HttpServletResponse response) {
		//1.通过实体类修改，可以多传修改条件
		carService.updateById(car);
		//2.通过主键id修改
		//carService.updateById(car);
		return "redirect:/car/findBySql.action";
	}
	
	/**
	 * 删除通过主键
	 * @return
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public String delete(Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
		///1.通过主键删除
		carService.deleteById(id);
		/*以下是多种删除方式*/
//		//2.通过实体条件删除
//		carService.deleteByEntity(car);
//		//3.通过参数删除
//     //通过map查询
//		Map<String,Object> params = new HashMap<String,Object>();
//		
//        if(!isEmpty(car.getItemId())){
//        	params.put("itemId", car.getItemId());
//		}
//       
//        if(!isEmpty(car.getUserId())){
//        	params.put("userId", car.getUserId());
//		}
//       
//        if(!isEmpty(car.getNum())){
//        	params.put("num", car.getNum());
//		}
//       
//        if(!isEmpty(car.getTotal())){
//        	params.put("total", car.getTotal());
//		}
//       
//		carService.deleteByMap(params);
//		//4.状态删除
//		Car load = carService.getById(car.getId())
//		load.setIsDelete(1);
//		carService.update(load);
		//5.状态删除
		//Car load = carService.load(id);
		//load.setIsDelete(1);
		//carService.update(load);
		return "redirect:/car/findBySql.action";
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
	public String listAllJson(Car car, HttpServletRequest request, HttpServletResponse response){
		List<Car> listAll = carService.listAll();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("list", listAll);
		jsonObject.put("obj", car);
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
	public String listByEntityJson(Car car,  HttpServletRequest request, HttpServletResponse response){
		List<Car> listAll = carService.listAllByEntity(car);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("list", listAll);
		jsonObject.put("obj", car);
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
	public String listByMapJson(Car car,HttpServletRequest request, HttpServletResponse response){
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
	        if(!isEmpty(car.getItemId())){
	        	params.put("itemId", car.getItemId());
			}
	        if(!isEmpty(car.getUserId())){
	        	params.put("userId", car.getUserId());
			}
	        if(!isEmpty(car.getNum())){
	        	params.put("num", car.getNum());
			}
	        if(!isEmpty(car.getTotal())){
	        	params.put("total", car.getTotal());
			}
	    List<Car> listAll = carService.listByMap(params);
	    JSONObject jsonObject = new JSONObject();
		jsonObject.put("list", listAll);
		jsonObject.put("obj", car);
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
	public String findByObjByEntityJson(Car car, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		Pager<Car> pagers = carService.findByEntity(car);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("pagers", pagers);
		jsonObject.put("obj", car);
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
	public String findByMapJson(Car car,HttpServletRequest request, HttpServletResponse response) {
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
        if(!isEmpty(car.getItemId())){
        	params.put("itemId", car.getItemId());
		}
        if(!isEmpty(car.getUserId())){
        	params.put("userId", car.getUserId());
		}
        if(!isEmpty(car.getNum())){
        	params.put("num", car.getNum());
		}
        if(!isEmpty(car.getTotal())){
        	params.put("total", car.getTotal());
		}
		//分页查询
		Pager<Car> pagers = carService.findByMap(params);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("pagers", pagers);
		jsonObject.put("obj", car);
		return jsonObject.toString();
	}
	
	
	/**
	 * ajax 添加
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/exAddJson", method = RequestMethod.POST)
	@ResponseBody
	public String exAddJson(Car car, Model model, HttpServletRequest request, HttpServletResponse response) {
		carService.insert(car);
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
	public String exUpdateJson(Car car, Model model, HttpServletRequest request, HttpServletResponse response) {
		//1.通过实体类修改，可以多传修改条件
		carService.updateById(car);
		//2.通过主键id修改
		//carService.updateById(car);
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
		carService.deleteById(id);
		/*以下是多种删除方式*/
//		//2.通过实体条件删除
//		carService.deleteByEntity(car);
//		//3.通过参数删除
//        //通过map查询
//		Map<String,Object> params = new HashMap<String,Object>();
//		
//        if(!isEmpty(car.getItemId())){
//        	params.put("itemId", car.getItemId());
//		}
//       
//        if(!isEmpty(car.getUserId())){
//        	params.put("userId", car.getUserId());
//		}
//       
//        if(!isEmpty(car.getNum())){
//        	params.put("num", car.getNum());
//		}
//       
//        if(!isEmpty(car.getTotal())){
//        	params.put("total", car.getTotal());
//		}
//       
//		carService.deleteByMap(params);
//		//4.状态删除
//		Car load = carService.getById(car.getId())
//		load.setIsDelete(1);
//		carService.updateById(load);
		//5.状态删除
		//Car load = carService.load(id);
		//load.setIsDelete(1);
		//carService.updateById(load);
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
