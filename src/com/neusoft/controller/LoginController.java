package com.neusoft.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.neusoft.base.BaseController;
import com.neusoft.po.Car;
import com.neusoft.po.CategoryDto;
import com.neusoft.po.Item;
import com.neusoft.po.ItemCategory;
import com.neusoft.po.ItemOrder;
import com.neusoft.po.Manage;
import com.neusoft.po.OrderDetail;
import com.neusoft.po.User;
import com.neusoft.service.CarService;
import com.neusoft.service.ItemCategoryService;
import com.neusoft.service.ItemOrderService;
import com.neusoft.service.ItemService;
import com.neusoft.service.ManageService;
import com.neusoft.service.OrderDetailService;
import com.neusoft.service.UserService;
import com.neusoft.utils.ItemDto;
import com.neusoft.utils.Pager;

@Controller
@RequestMapping("/login")
public class LoginController  extends BaseController{
	
	@Autowired
	private ManageService manageService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ItemOrderService itemOrderService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private CarService carService;
	
	@Autowired
	private ItemCategoryService itemCategoryService;
	
	@Autowired
	private OrderDetailService orderDetailService;
	
	/**
	 * 跳转登陆
	 * @return
	 */
	@RequestMapping("/login")
	public String login(){
		return "login/mLogin";
	}
	@RequestMapping("/uLogin")
	public String uLogin(){
		return "login/uLogin";
	}
	
	@RequestMapping("/res")
	public String res(){
		return "login/res";
	}
	
	@RequestMapping("/toRes")
	public String toRes(User u){
		userService.insert(u);
		return "login/uLogin";
	}
	
	
	
	@RequestMapping("/uIndex")
	public String uIndex(Model model,Item item,String prices,Integer xl, HttpServletRequest request){
		String sql2 = "SELECT * FROM item_category WHERE isDelete = 0 and pid is null";
        sql2 += " ORDER BY ID DESC ";
        List<ItemCategory> fatherList = itemCategoryService.listBySqlReturnEntity(sql2);
		
		List<CategoryDto> list = new ArrayList<CategoryDto>();
		
		if (!CollectionUtils.isEmpty(fatherList)){
			
			for (ItemCategory ic : fatherList){
				CategoryDto dto = new CategoryDto();
				dto.setFather(ic);
				//查询儿子
				String sql3 = "SELECT * FROM item_category WHERE isDelete = 0 and pid = "+ic.getId();
				 List<ItemCategory> childrens = itemCategoryService.listBySqlReturnEntity(sql3);
				 dto.setChildrens(childrens);
				 list.add(dto);
			}
			
			System.out.println("====================================================");
			model.addAttribute("lbs",list);
		}
		//在redis中查询所有分类
		
		/*String string = RedisUtil.getJedis().get("lbs");
		
		List<CategoryDto> parseArray = JSONArray.parseArray(string, CategoryDto.class);
		
		System.out.println(JSONObject.toJSONString(parseArray));
		
		model.addAttribute("lbs",parseArray);*/
		
		// 1-500
		
	
		//热销
		List<Item> listBySqlReturnEntity = itemService.listBySqlReturnEntity("SELECT * FROM item WHERE 1=1 and isDelete =0 order by gmNum desc limit 0,10");
		model.addAttribute("rxs",listBySqlReturnEntity);
		
		//折扣
		List<Item> zks = itemService.listBySqlReturnEntity("SELECT * FROM item WHERE 1=1 and isDelete =0 and zk is not null order by zk desc limit 0,10");
		model.addAttribute("zks",zks);
		
		//做推荐
		Object attribute = request.getSession().getAttribute("userId");
		if (attribute != null){
			Integer userId = Integer.valueOf(attribute.toString());
			//协同过滤
			List<Item> tjs =  getListByUCF(userId);
			
			 model.addAttribute("tjs",tjs);
			
		}
		
		model.addAttribute("obj",item);
		model.addAttribute("prices",prices);
		model.addAttribute("xl",xl);
		return "login/uIndex";
	}
	
	
	
	private List<Item> getListByUCF(Integer userId) {
		
	   List<Item> returnList = new ArrayList<Item> ();
		
		List<User> listAll = userService.listAll();
		List<Integer> userIds = new ArrayList<Integer>();
		List<ItemDto> res = new ArrayList<ItemDto>();
		
		//判断这些人，有咩有买过东西
		for (User u : listAll){
				userIds.add(u.getId());
		}
		

		//输入用户总量
		int N = userIds.size();
		int[][] sparseMatrix = new int[N][N];//建立用户稀疏矩阵，用于用户相似度计算【相似度矩阵】
		Map<String, Integer> userItemLength = new HashMap<String, Integer>();//存储每一个用户对应的不同物品总数  eg: A 3
		Map<String, Set<String>> itemUserCollection = new HashMap<String, Set<String>>();//建立物品到用户的倒排表 eg: a A B
		Set<String> items = new HashSet<String>();//辅助存储物品集合
		Map<String, Integer> userID = new HashMap<String, Integer>();//辅助存储每一个用户的用户ID映射
		Map<Integer, String> idUser = new HashMap<Integer, String>();//辅助存储每一个ID对应的用户映射
		
		Integer a = 0;
		
		for (User u : listAll){
			ItemOrder or = new ItemOrder();
			or.setUserId(u.getId());
			List<ItemOrder> listAllByEntity = itemOrderService.listAllByEntity(or);
			
			if (!CollectionUtils.isEmpty(listAllByEntity)){
				for (ItemOrder ors : listAllByEntity){
					OrderDetail de = new OrderDetail();
					de.setOrderId(ors.getId());
					List<OrderDetail> listAllByEntity2 = orderDetailService.listAllByEntity(de);
					if (!CollectionUtils.isEmpty(listAllByEntity2)){
						for (OrderDetail dd : listAllByEntity2){
							items.add(String.valueOf(dd.getItemId()));
						}
					}
				}
				
			}
			
			String[] user_item = new String[items.size()+1];
			List<String> isitems = new ArrayList<String>(items);
			user_item[0] = String.valueOf(u.getId());
			for(int k = 1; k < items.size()+1 ; k++){
				user_item[k] = String.valueOf(isitems.get(k-1));
			}
			
			int length = user_item.length;
			
			userItemLength.put(user_item[0], length);//eg: A 3
			userID.put(user_item[0], a);//用户ID与稀疏矩阵建立对应关系
			idUser.put(a, user_item[0]);
			//建立物品--用户倒排表
			for(int j = 1; j < length; j ++){
				if(items.contains(user_item[j])){//如果已经包含对应的物品--用户映射，直接添加对应的用户
					Set<String> set2 = itemUserCollection.get(user_item[j]);
					if (!CollectionUtils.isEmpty(set2)){
						set2.add(user_item[0]);
					}else{
						itemUserCollection.put(user_item[j], new HashSet<String>());//创建物品--用户倒排关系
						itemUserCollection.get(user_item[j]).add(user_item[0]);
					}
					//itemUserCollection.get(user_item[j]).add(user_item[0]);
				}else{//否则创建对应物品--用户集合映射
					items.add(user_item[j]);
					itemUserCollection.put(user_item[j], new HashSet<String>());//创建物品--用户倒排关系
					itemUserCollection.get(user_item[j]).add(user_item[0]);
				}
			}
			a++;	
		}
		
		System.out.println(itemUserCollection.toString());
		//计算相似度矩阵【稀疏】
		Set<Entry<String, Set<String>>> entrySet = itemUserCollection.entrySet();
		Iterator<Entry<String, Set<String>>> iterator = entrySet.iterator();
		while(iterator.hasNext()){
			Set<String> commonUsers = iterator.next().getValue();
			for (String user_u : commonUsers) {
				for (String user_v : commonUsers) {
					if(user_u.equals(user_v)){
						continue;
					}
					sparseMatrix[userID.get(user_u)][userID.get(user_v)] += 1;//计算用户u与用户v都有正反馈的物品总数
				}
			}
		}
		System.out.println(userItemLength.toString());
		String recommendUser = String.valueOf(userId);
		System.out.println(userID.get(recommendUser));
		//计算用户之间的相似度【余弦相似性】
		Integer s = userID.get(recommendUser);
		int recommendUserId = 0;
		if (s != null){
			recommendUserId = s;
		}else{
			
		}
		
		//判断当前用户的index
		Integer index = 0;
		for(int j = 0; j < userIds.size(); j ++){
			if (userIds.get(j).equals(recommendUserId)){
				index = j;
			}
		}
		recommendUserId = index;
		
		for (int j = 0;j < sparseMatrix.length; j++) {
				if(j != recommendUserId){
				//	System.out.println(idUser.get(recommendUserId)+"--"+idUser.get(j)+"相似度:"+sparseMatrix[recommendUserId][j]/Math.sqrt(userItemLength.get(idUser.get(recommendUserId))*userItemLength.get(idUser.get(j))));
				}
		}
		
		//计算指定用户recommendUser的物品推荐度
		for(String item: items){//遍历每一件物品
			Set<String> users = itemUserCollection.get(item);//得到购买当前物品的所有用户集合
			if (users == null){
				continue;
			}
//		
			double itemRecommendDegree = 0.0;
			for(String user: users){
				itemRecommendDegree += sparseMatrix[userID.get(user)][userID.get(user)]/Math.sqrt(userItemLength.get(recommendUser)*userItemLength.get(recommendUser));//推荐度计算
			}
			System.out.println("The item "+item+" for "+recommendUser +"'s recommended degree:"+itemRecommendDegree);
			ItemDto itd = new ItemDto();
			itd.setItemId(Integer.valueOf(item));
			itd.setItemRecommendDegree(itemRecommendDegree);
			res.add(itd);
		}
		
		if (!CollectionUtils.isEmpty(res)){
			   Collections.sort(res, new Comparator<ItemDto>() {
				  public int compare(ItemDto o1, ItemDto o2) {
				    return Double.compare(o1.getItemRecommendDegree(),o2.getItemRecommendDegree());
				 }
				});
			   Integer b = 1;
			   
			   for (ItemDto i :res){
				   
				   if(b<=10){
					   
					   Item byId = itemService.getById(i.getItemId());
					   returnList.add(byId);
				   }
				   b++;
				  
				   
			   }
		}
		
		return returnList;
	}
	@RequestMapping("/mtuichu")
	public String mtuichu(HttpServletRequest request){
		//request.getSession().invalidate();
		return "login/mLogin";
	}
	@RequestMapping("/welcome")
	private String welcome(){
		return "login/welcome";
	}
	
	@RequestMapping("/toLogin")
	public String toLogin(Manage manage, HttpServletRequest request, HttpServletResponse response){
		Manage byEntity = manageService.getByEntity(manage);
		if(byEntity == null){
			return "redirect:/login/mtuichu";
		}else{
			/*request.getSession().setAttribute("role", 1);
			request.getSession().setAttribute("username", byEntity.getUserName());
			request.getSession().setAttribute("userId", byEntity.getId());*/
		}
		return "login/mIndex";
	}
	
//	
//	
	@RequestMapping("/utoLogin")
	public String utoLogin(User manage, HttpServletRequest request, HttpServletResponse response){
		User byEntity = userService.getByEntity(manage);
		if(byEntity == null){
			return "redirect:/login/res.action";
		}else{
			request.getSession().setAttribute("role", 2);
			request.getSession().setAttribute("username", byEntity.getUserName());
			request.getSession().setAttribute("userId", byEntity.getId());
		}
		return "redirect:/login/uIndex.action";
	}
//	
//	
	@RequestMapping("/pass")
	public String pass(HttpServletRequest request){
		Object attribute = request.getSession().getAttribute("userId");
		
	   	if (attribute == null){
	   		return "redirect:/login/uLogin.action";
		}
			Integer userId = Integer.valueOf(attribute.toString());
			
			User load = userService.load(userId);
			request.setAttribute("obj", load);
		return "login/pass";
	}
	
	@RequestMapping("/upass")
	@ResponseBody
	public String upass(HttpServletRequest request,String password){
      Object attribute = request.getSession().getAttribute("userId");
      JSONObject j = new  JSONObject();
	   	if (attribute == null){
	   		j.put("res", 0);
	   		return j.toString();
		}
			Integer userId = Integer.valueOf(attribute.toString());
			User load = userService.load(userId);
			load.setPassWord(password);
			userService.updateById(load);
			j.put("res", 1);
			return j.toString();
			
	}
	
	
	
//	@RequestMapping("/toLogin2")
//	public String toLogin(Student student, HttpServletRequest request, HttpServletResponse response){
//		student.setIsdel(0);
//		Student byEntity = studentService.getByEntity(student);
//		if(byEntity == null){
//			return "redirect:/login/login.action";
//		}else{
//			request.getSession().setAttribute("role",2);
//			request.getSession().setAttribute("type",3);
//			request.getSession().setAttribute("username", byEntity.getXh());
//			request.getSession().setAttribute("userId", byEntity.getId());
//		}
//		return "login/index";
//	}
	
	/**
	 * 退出
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/tuichu")
	public String tuichu( HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		session.invalidate();
		return "login/login";
	}
	@RequestMapping("/uTui")
	public String uTui( HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/login/uLogin.action";
	}
	

	@RequestMapping("/head")
	private String head(){
		return "inc/head";
	}
	
	@RequestMapping("/left")
	private String left(){
		return "inc/left";
	}
	
	@RequestMapping("/main")
	private String main(HttpServletRequest request){
		Object attribute = request.getSession().getAttribute("userId");
		if (attribute == null){
			return "redirect:/login/uLogin.action";
		}
		Integer userId = Integer.valueOf(attribute.toString());
		User load = userService.load(userId);
		request.setAttribute("user", load);
		return "login/main";
	}
	
	
	@RequestMapping("/info")
	private String info(HttpServletRequest request){
		Object attribute = request.getSession().getAttribute("userId");
		Integer userId = Integer.valueOf(attribute.toString());
		User load = userService.load(userId);
		request.setAttribute("user", load);
		return "login/info";
	}
}
