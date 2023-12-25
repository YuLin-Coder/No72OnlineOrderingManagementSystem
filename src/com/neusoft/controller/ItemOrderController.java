package com.neusoft.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.neusoft.base.BaseController;
import com.neusoft.po.*;
import com.neusoft.service.*;
import com.neusoft.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

/*import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradePageMergePayModel;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;*/
//import com.neusoft.pay.AlipayConfig;


@Controller
@RequestMapping("/itemOrder")
public class ItemOrderController extends BaseController {
	@Autowired
	private ItemService itemService;
	/**
	 * 依赖注入 start dao/service/===
	 */
	@Autowired
	private UserService userService;
	@Autowired
	private CarService carService;
	@Autowired
	private ItemOrderService itemOrderService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private OrderDetailService orderDetailService;
	// --------------------------------------- 华丽分割线 ------------------------------
	
	/**
     * 生成二维码，返回到页面上
      * @param response
     */
    @RequestMapping(value="/getErWeiCode",method={RequestMethod.POST,RequestMethod.GET} )
    public void getErWeiCode(HttpServletResponse response,Integer id){
   	 ItemOrder byId = itemOrderService.getById(id);
       String url=JSON.toJSONString(byId);
         if(url!=null&&!"".equals(url)){
            ServletOutputStream stream=null;
           try {
                int width=400;
               int height=400;
               stream=response.getOutputStream();
                QRCodeWriter writer=new QRCodeWriter();
               BitMatrix m=writer.encode(url, BarcodeFormat.QR_CODE, height,width);
                MatrixToImageWriter.writeToStream(m, "png", stream);
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }finally{
                if(stream!=null){
                    try {
                        stream.flush();
                        stream.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    
                }
            }
        }
    }
    
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
	public String listAll(ItemOrder itemOrder, Model model, HttpServletRequest request, HttpServletResponse response){
		List<ItemOrder> listAll = itemOrderService.listAll();
		model.addAttribute("list", listAll);
		return "itemOrder/itemOrder";
	}
	
	@RequestMapping(value = "/pay2")
	
	public String pay(ItemOrder itemOrder, Model model2, HttpServletRequest req, HttpServletResponse res) throws UnsupportedEncodingException{
		
				return "itemOrder/pay";
	}
	
	/*@RequestMapping(value = "/notify")
	@ResponseBody
	public String notify(ItemOrder itemOrder, Model model, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, AlipayApiException{
		
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}*/
		
	//	boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

		//——请在这里编写您的程序（以下代码仅作参考）——
		
		/* 实际验证过程建议商户务必添加以下校验：
		1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
		2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
		3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
		4、验证app_id是否为该商户本身。
		*/
		/*if(signVerified) {//验证成功
			//商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		
			//支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
		
			//交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
			
			if(trade_status.equals("TRADE_FINISHED")){
				//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序
					
				
				//注意：
				//退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
			}else if (trade_status.equals("TRADE_SUCCESS")){
				//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序
				ItemOrder o = new ItemOrder();
				o.setCode(out_trade_no);
				ItemOrder byEntity = itemOrderService.getByEntity(o);
				byEntity.setStatus(0);
				itemOrderService.updateById(byEntity);
				//注意：
				//付款完成后，支付宝系统发送该交易状态通知
			}
			
			return "success";
			
		}else {//验证失败
			return "fail";
			//调试用，写文本函数记录程序运行情况是否正常
			//String sWord = AlipaySignature.getSignCheckContentV1(params);
			//AlipayConfig.logResult(sWord);
		}
		
		//——请在这里编写您的程序（以上代码仅作参考）——

		
	}
	
	*/
	/*@RequestMapping(value = "/test")
	@ResponseBody
	public String test(ItemOrder itemOrder, Model model2, HttpServletRequest req, HttpServletResponse res){
		//实例化客户端
		//AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, "RSA2");
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
		
		//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay alipay.trade.page.pay
		AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
		//SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
		//AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		AlipayTradePagePayModel model = new AlipayTradePagePayModel();
		model.setBody("111");
		model.setSubject("22");
		model.setOutTradeNo("111"+new Date().getTime());
		model.setTimeoutExpress("30m");
		model.setTotalAmount("0.01");
		model.setProductCode("FAST_INSTANT_TRADE_PAY");
		request.setBizModel(model);
		request.setNotifyUrl(AlipayConfig.notify_url);
		try {
		        //这里和普通的接口调用不同，使用的是sdkExecute
			//请求
			String result = alipayClient.pageExecute(request).getBody();
			return result;
		    } catch (AlipayApiException e) {
		        e.printStackTrace();
		}
		return null;*/
		/*//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
		
		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
		
		//商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no = new String("111"+new Date().getTime());
		//付款金额，必填
		String total_amount = new String("0.01");
		//订单名称，必填
		String subject = new String("测试");
		//商品描述，可空
		String body = new String("测试");
		
		alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
				+ "\"total_amount\":\""+ total_amount +"\"," 
				+ "\"subject\":\""+ subject +"\"," 
				+ "\"body\":\""+ body +"\"," 
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		
		//若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
		//alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
		//		+ "\"total_amount\":\""+ total_amount +"\"," 
		//		+ "\"subject\":\""+ subject +"\"," 
		//		+ "\"body\":\""+ body +"\"," 
		//		+ "\"timeout_express\":\"10m\"," 
		//		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		//请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节
		
		//请求
		try {
			String result = alipayClient.pageExecute(alipayRequest).getBody();
			return result;
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	//}
	
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
	public String listByEntity(ItemOrder itemOrder, Model model, HttpServletRequest request, HttpServletResponse response){
		List<ItemOrder> listAll = itemOrderService.listAllByEntity(itemOrder);
		model.addAttribute("list", listAll);
		return "itemOrder/itemOrder";
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
	public String listByMap(ItemOrder itemOrder, Model model, HttpServletRequest request, HttpServletResponse response){
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
	        if(!isEmpty(itemOrder.getItemId())){
	        	params.put("itemId", itemOrder.getItemId());
			}
	        if(!isEmpty(itemOrder.getUserId())){
	        	params.put("userId", itemOrder.getUserId());
			}
	        if(!isEmpty(itemOrder.getCode())){
	        	params.put("code", itemOrder.getCode());
			}
	        if(!isEmpty(itemOrder.getAddTime())){
	        	params.put("addTime", itemOrder.getAddTime());
			}
	        if(!isEmpty(itemOrder.getTotal())){
	        	params.put("total", itemOrder.getTotal());
			}
	        if(!isEmpty(itemOrder.getIsDelete())){
	        	params.put("isDelete", itemOrder.getIsDelete());
			}
	        if(!isEmpty(itemOrder.getStatus())){
	        	params.put("status", itemOrder.getStatus());
			}
	    List<ItemOrder> listAll = itemOrderService.listByMap(params);
		model.addAttribute("list", listAll);
		return "itemOrder/itemOrder";
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
	public String findByObj(ItemOrder itemOrder, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		Pager<ItemOrder> pagers = itemOrderService.findByEntity(itemOrder);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", itemOrder);
		return "itemOrder/itemOrder";
	}
	
	/**
	 * 分页查询 返回list对象(通过对By Sql)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findBySql")
	public String findBySql(ItemOrder itemOrder, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		String sql = "SELECT * FROM item_order WHERE 1=1 ";
        if(!isEmpty(itemOrder.getItemId())){
        	sql += " and itemId like '%"+itemOrder.getItemId()+"%'";
		}
        if(!isEmpty(itemOrder.getUserId())){
        	sql += " and userId like '%"+itemOrder.getUserId()+"%'";
		}
        if(!isEmpty(itemOrder.getCode())){
        	sql += " and code like '%"+itemOrder.getCode()+"%'";
		}
        if(!isEmpty(itemOrder.getAddTime())){
        	sql += " and addTime like '%"+itemOrder.getAddTime()+"%'";
		}
        if(!isEmpty(itemOrder.getTotal())){
        	sql += " and total like '%"+itemOrder.getTotal()+"%'";
		}
        if(!isEmpty(itemOrder.getIsDelete())){
        	sql += " and isDelete like '%"+itemOrder.getIsDelete()+"%'";
		}
        if(!isEmpty(itemOrder.getStatus())){
        	sql += " and status like '%"+itemOrder.getStatus()+"%'";
		}
       sql += " ORDER BY ID DESC ";
		Pager<ItemOrder> pagers = itemOrderService.findBySqlRerturnEntity(sql);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", itemOrder);
		return "itemOrder/itemOrder";
	}
	
	
	
	@RequestMapping(value = "/my")
	public String my(ItemOrder itemOrder, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		Object attribute = request.getSession().getAttribute("userId");
		if (attribute == null){
			return "redirect:/login/uLogin";
		}
		JSONObject js = new JSONObject();
		Integer userId = Integer.valueOf(attribute.toString());
		String sql = "SELECT * FROM item_order WHERE 1=1 and user_id="+userId;
        sql += " ORDER BY ID DESC ";
		
      //  0.新建代发货1.已取消 2已已发货3.到收货
        
		List<ItemOrder> all = itemOrderService.listBySqlReturnEntity(sql);
		
		String sql2 = "SELECT * FROM item_order WHERE status = 0 and user_id="+userId;
        sql2 += " ORDER BY ID DESC ";
		
        //代发货
        List<ItemOrder> dfh = itemOrderService.listBySqlReturnEntity(sql2);
        
    	String sql3 = "SELECT * FROM item_order WHERE status = 1 and user_id="+userId;
        sql3 += " ORDER BY ID DESC ";
        
        //已取消
        List<ItemOrder> yqx = itemOrderService.listBySqlReturnEntity(sql3);
        
        
    	String sql4 = "SELECT * FROM item_order WHERE status = 2 and user_id="+userId;
        sql4 += " ORDER BY ID DESC ";
        
        //已发货
        List<ItemOrder> yfh = itemOrderService.listBySqlReturnEntity(sql4);
        
        
    	String sql5 = "SELECT * FROM item_order WHERE status = 3 and user_id="+userId;
        sql5 += " ORDER BY ID DESC ";
        
        //待评价
        List<ItemOrder> dpj = itemOrderService.listBySqlReturnEntity(sql5);
        
		model.addAttribute("all", all);
		model.addAttribute("dfh", dfh);
		model.addAttribute("yqx", yqx);
		model.addAttribute("yfh", yfh);
		model.addAttribute("dpj", dpj);
		//存储查询条件
		model.addAttribute("obj", itemOrder);
		return "itemOrder/my";
	}
	
	
	/**
	 * 分页查询 返回list对象(通过Map)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByMap")
	public String findByMap(ItemOrder itemOrder, Model model, HttpServletRequest request, HttpServletResponse response) {
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
        if(!isEmpty(itemOrder.getItemId())){
        	params.put("itemId", itemOrder.getItemId());
		}
        if(!isEmpty(itemOrder.getUserId())){
        	params.put("userId", itemOrder.getUserId());
		}
        if(!isEmpty(itemOrder.getCode())){
        	params.put("code", itemOrder.getCode());
		}
        if(!isEmpty(itemOrder.getAddTime())){
        	params.put("addTime", itemOrder.getAddTime());
		}
        if(!isEmpty(itemOrder.getTotal())){
        	params.put("total", itemOrder.getTotal());
		}
        if(!isEmpty(itemOrder.getIsDelete())){
        	params.put("isDelete", itemOrder.getIsDelete());
		}
        if(!isEmpty(itemOrder.getStatus())){
        	params.put("status", itemOrder.getStatus());
		}
		//分页查询
		Pager<ItemOrder> pagers = itemOrderService.findByMap(params);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", itemOrder);
		return "itemOrder/itemOrder";
	}
	
	/**********************************【增删改】******************************************************/
	
	/**
	 * 跳至添加页面
	 * @return
	 */
	@RequestMapping(value = "/add")
	public String add() {
		return "itemOrder/add";
	}

	/**
	 * 跳至详情页面
	 * @return
	 */
	@RequestMapping(value = "/view")
	public String view(Integer id,Model model) {
		ItemOrder obj = itemOrderService.load(id);
		model.addAttribute("obj",obj);
		return "itemOrder/view";
	}
	@RequestMapping(value = "/qx")
	public String qx(Integer id,Model model) {
		ItemOrder obj = itemOrderService.load(id);
		obj.setStatus(1);
		itemOrderService.updateById(obj);
		model.addAttribute("obj",obj);
		return "redirect:/itemOrder/my";
	}
	
	@RequestMapping(value = "/sh")
	public String sh(Integer id,Model model) {
		ItemOrder obj = itemOrderService.load(id);
		obj.setStatus(3);
		itemOrderService.updateById(obj);
		model.addAttribute("obj",obj);
		return "redirect:/itemOrder/my";
	}
	
	
	@RequestMapping(value = "/fh")
	public String fh(Integer id,Model model) {
		ItemOrder obj = itemOrderService.load(id);
		obj.setStatus(2);
		itemOrderService.updateById(obj);
		model.addAttribute("obj",obj);
		return "redirect:/itemOrder/findBySql";
	}
	
	
	@RequestMapping(value = "/pj")
	public String pj(Integer id,Model model) {
		model.addAttribute("id",id);
		return "itemOrder/pj";
	}
	
	/**
	 * 添加执行
	 * @return
	 */
	@RequestMapping(value = "/exAdd")
	@ResponseBody
	public String exAdd(@RequestBody List<CarDto> list, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		
		//itemOrderService.insert(itemOrder);
		Object attribute = request.getSession().getAttribute("userId");
		JSONObject js = new JSONObject();
		if (attribute == null){
			js.put("res", 0);
			return js.toJSONString();
		}
		Integer userId = Integer.valueOf(attribute.toString());
		User byId = userService.getById(userId);
		
		if (StringUtils.isEmpty(byId.getAddress())){
			js.put("res", 2);
			return js.toJSONString();
		}
		List<Integer> ids = new ArrayList<Integer>();
		
		double to = 0.0;
		for (CarDto c : list){
			ids.add(c.getId());
			Car load = carService.load(c.getId());
			to += load.getPrice()*c.getNum();
		}
		ItemOrder order= new ItemOrder();
		order.setStatus(0);
		order.setCode(getOrderNo());
		order.setIsDelete(0);
		order.setTotal(String.valueOf(to));
		order.setUserId(userId);
		order.setAddTime(new Date());
		itemOrderService.insert(order);
		//删除购车
		if (!CollectionUtils.isEmpty(ids)){
			for (CarDto c : list){
				Car load = carService.load(c.getId());
				OrderDetail de = new OrderDetail();
				de.setItemId(load.getItemId());
				de.setOrderId(order.getId());
				de.setStatus(0);
				de.setNum(c.getNum());
				de.setTotal(String.valueOf(c.getNum()*c.getNum()));
				orderDetailService.insert(de);
				//修改成交数
				Item load2 = itemService.load(load.getItemId());
				load2.setGmNum(load2.getGmNum()+c.getNum());
				itemService.updateById(load2);
				carService.deleteById(c.getId());
			}
		}
		js.put("res", 1);
		js.put("id", order.getId());
		js.put("code", order.getCode());
		return js.toJSONString();
	}
	private static String date ;
	private static long orderNum = 0l; 
     public static synchronized String getOrderNo() {    
         String str = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());    
         if(date==null||!date.equals(str)){    
             date = str;    
             orderNum  = 0l;    
         }    
         orderNum ++;    
         long orderNo = Long.parseLong((date)) * 10000;    
         orderNo += orderNum;;    
         return orderNo+"";    
     }    
	
	/**
	 * 跳至修改页面
	 * @return
	 */
	@RequestMapping(value = "/update")
	public String update(Integer id,Model model) {
		ItemOrder obj = itemOrderService.load(id);
		model.addAttribute("obj",obj);
		return "itemOrder/update";
	}
	@RequestMapping(value = "/pay")
	public String pay(Integer id,Model model) {
		ItemOrder obj = itemOrderService.load(id);
	//	Integer addressId = obj.getAddressId();
//		Address load = addressService.load(addressId);
//		model.addAttribute("obj",obj);
//		model.addAttribute("address", load);
		return "itemOrder/pay";
	}
	
	/**
	 * 添加修改
	 * @return
	 */
	@RequestMapping(value = "/exUpdate")
	public String exUpdate(ItemOrder itemOrder, Model model, HttpServletRequest request, HttpServletResponse response) {
		//1.通过实体类修改，可以多传修改条件
		itemOrderService.updateById(itemOrder);
		//2.通过主键id修改
		//itemOrderService.updateById(itemOrder);
		return "redirect:/itemOrder/findBySql.action";
	}
	
	/**
	 * 删除通过主键
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public String delete(Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
		///1.通过主键删除
		itemOrderService.deleteById(id);
		/*以下是多种删除方式*/
//		//2.通过实体条件删除
//		itemOrderService.deleteByEntity(itemOrder);
//		//3.通过参数删除
//     //通过map查询
//		Map<String,Object> params = new HashMap<String,Object>();
//		
//        if(!isEmpty(itemOrder.getItemId())){
//        	params.put("itemId", itemOrder.getItemId());
//		}
//       
//        if(!isEmpty(itemOrder.getUserId())){
//        	params.put("userId", itemOrder.getUserId());
//		}
//       
//        if(!isEmpty(itemOrder.getCode())){
//        	params.put("code", itemOrder.getCode());
//		}
//       
//        if(!isEmpty(itemOrder.getAddTime())){
//        	params.put("addTime", itemOrder.getAddTime());
//		}
//       
//        if(!isEmpty(itemOrder.getTotal())){
//        	params.put("total", itemOrder.getTotal());
//		}
//       
//        if(!isEmpty(itemOrder.getIsDelete())){
//        	params.put("isDelete", itemOrder.getIsDelete());
//		}
//       
//        if(!isEmpty(itemOrder.getStatus())){
//        	params.put("status", itemOrder.getStatus());
//		}
//       
//		itemOrderService.deleteByMap(params);
//		//4.状态删除
//		ItemOrder load = itemOrderService.getById(itemOrder.getId())
//		load.setIsDelete(1);
//		itemOrderService.update(load);
		//5.状态删除
		//ItemOrder load = itemOrderService.load(id);
		//load.setIsDelete(1);
		//itemOrderService.update(load);
		return "redirect:/itemOrder/findBySql.action";
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
	public String listAllJson(ItemOrder itemOrder, HttpServletRequest request, HttpServletResponse response){
		List<ItemOrder> listAll = itemOrderService.listAll();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("list", listAll);
		jsonObject.put("obj", itemOrder);
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
	public String listByEntityJson(ItemOrder itemOrder,  HttpServletRequest request, HttpServletResponse response){
		List<ItemOrder> listAll = itemOrderService.listAllByEntity(itemOrder);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("list", listAll);
		jsonObject.put("obj", itemOrder);
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
	public String listByMapJson(ItemOrder itemOrder,HttpServletRequest request, HttpServletResponse response){
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
	        if(!isEmpty(itemOrder.getItemId())){
	        	params.put("itemId", itemOrder.getItemId());
			}
	        if(!isEmpty(itemOrder.getUserId())){
	        	params.put("userId", itemOrder.getUserId());
			}
	        if(!isEmpty(itemOrder.getCode())){
	        	params.put("code", itemOrder.getCode());
			}
	        if(!isEmpty(itemOrder.getAddTime())){
	        	params.put("addTime", itemOrder.getAddTime());
			}
	        if(!isEmpty(itemOrder.getTotal())){
	        	params.put("total", itemOrder.getTotal());
			}
	        if(!isEmpty(itemOrder.getIsDelete())){
	        	params.put("isDelete", itemOrder.getIsDelete());
			}
	        if(!isEmpty(itemOrder.getStatus())){
	        	params.put("status", itemOrder.getStatus());
			}
	    List<ItemOrder> listAll = itemOrderService.listByMap(params);
	    JSONObject jsonObject = new JSONObject();
		jsonObject.put("list", listAll);
		jsonObject.put("obj", itemOrder);
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
	public String findByObjByEntityJson(ItemOrder itemOrder, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		Pager<ItemOrder> pagers = itemOrderService.findByEntity(itemOrder);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("pagers", pagers);
		jsonObject.put("obj", itemOrder);
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
	public String findByMapJson(ItemOrder itemOrder,HttpServletRequest request, HttpServletResponse response) {
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
        if(!isEmpty(itemOrder.getItemId())){
        	params.put("itemId", itemOrder.getItemId());
		}
        if(!isEmpty(itemOrder.getUserId())){
        	params.put("userId", itemOrder.getUserId());
		}
        if(!isEmpty(itemOrder.getCode())){
        	params.put("code", itemOrder.getCode());
		}
        if(!isEmpty(itemOrder.getAddTime())){
        	params.put("addTime", itemOrder.getAddTime());
		}
        if(!isEmpty(itemOrder.getTotal())){
        	params.put("total", itemOrder.getTotal());
		}
        if(!isEmpty(itemOrder.getIsDelete())){
        	params.put("isDelete", itemOrder.getIsDelete());
		}
        if(!isEmpty(itemOrder.getStatus())){
        	params.put("status", itemOrder.getStatus());
		}
		//分页查询
		Pager<ItemOrder> pagers = itemOrderService.findByMap(params);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("pagers", pagers);
		jsonObject.put("obj", itemOrder);
		return jsonObject.toString();
	}
	
	
	/**
	 * ajax 添加
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/exAddJson", method = RequestMethod.POST)
	@ResponseBody
	public String exAddJson(ItemOrder itemOrder, Model model, HttpServletRequest request, HttpServletResponse response) {
		itemOrderService.insert(itemOrder);
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
	public String exUpdateJson(ItemOrder itemOrder, Model model, HttpServletRequest request, HttpServletResponse response) {
		//1.通过实体类修改，可以多传修改条件
		itemOrderService.updateById(itemOrder);
		//2.通过主键id修改
		//itemOrderService.updateById(itemOrder);
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
		itemOrderService.deleteById(id);
		/*以下是多种删除方式*/
//		//2.通过实体条件删除
//		itemOrderService.deleteByEntity(itemOrder);
//		//3.通过参数删除
//        //通过map查询
//		Map<String,Object> params = new HashMap<String,Object>();
//		
//        if(!isEmpty(itemOrder.getItemId())){
//        	params.put("itemId", itemOrder.getItemId());
//		}
//       
//        if(!isEmpty(itemOrder.getUserId())){
//        	params.put("userId", itemOrder.getUserId());
//		}
//       
//        if(!isEmpty(itemOrder.getCode())){
//        	params.put("code", itemOrder.getCode());
//		}
//       
//        if(!isEmpty(itemOrder.getAddTime())){
//        	params.put("addTime", itemOrder.getAddTime());
//		}
//       
//        if(!isEmpty(itemOrder.getTotal())){
//        	params.put("total", itemOrder.getTotal());
//		}
//       
//        if(!isEmpty(itemOrder.getIsDelete())){
//        	params.put("isDelete", itemOrder.getIsDelete());
//		}
//       
//        if(!isEmpty(itemOrder.getStatus())){
//        	params.put("status", itemOrder.getStatus());
//		}
//       
//		itemOrderService.deleteByMap(params);
//		//4.状态删除
//		ItemOrder load = itemOrderService.getById(itemOrder.getId())
//		load.setIsDelete(1);
//		itemOrderService.updateById(load);
		//5.状态删除
		//ItemOrder load = itemOrderService.load(id);
		//load.setIsDelete(1);
		//itemOrderService.updateById(load);
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
