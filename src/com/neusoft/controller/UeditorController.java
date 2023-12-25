package com.neusoft.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.neusoft.utils.UUIDUtils;

@Controller
@RequestMapping("/ueditor")
public class UeditorController {
	
	
	  
	  @ResponseBody  
	@RequestMapping(value = "/saveFile")  
    public Map<String, Object> saveFile(@RequestParam(value = "upfile", required = false) MultipartFile file,HttpServletRequest request, Model model) throws IllegalStateException, IOException {  
		  Map<String, Object> params = new HashMap<String, Object>();  
    	  System.out.println("开始");  
          long  startTime=System.currentTimeMillis();
          System.out.println("fileName："+file.getOriginalFilename());
          String n = UUIDUtils.create();
          String path="C:\\work\\code\\YuLin-Coder\\No72OnlineOrderingManagementSystem\\WebRoot\\resource\\upload\\"+n+file.getOriginalFilename();
          System.out.println("===================================================");
           System.out.println(path);
          File newFile=new File(path);
          //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
          file.transferTo(newFile);
          String visitUrl = "/ueditor/"; 
          visitUrl = visitUrl.concat(n+file.getOriginalFilename());
          long  endTime=System.currentTimeMillis();
          System.out.println("方法二的运行时间："+String.valueOf(endTime-startTime)+"ms");
         System.out.println("*********************************************************");
         System.out.println("*********************************************************");
         params.put("state", "SUCCESS");  
         params.put("url", visitUrl);  
         params.put("size", file.getSize());  
         params.put("original", file.getOriginalFilename());  
         params.put("type", file.getContentType());  
         return params;  
    }  
    
}
