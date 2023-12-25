<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
   <title>首页</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/resource/user/css/style.css">
	<script src="${ctx}/resource/user/js/jquery-1.8.3.min.js"></script>
	<script src="${ctx}/resource/user/js/jquery.luara.0.0.1.min.js"></script>
</head>
<body>






 <%@ include file="/common/utop.jsp" %>
 
<!--导航条-->
<div class="width100" style="height:45px;background:#dd4545;margin-top:40px;position:relative;z-index:100;">
	<!--中间的部分-->	
	<div class="width1200 center_yh relative_yh" style="height:45px;">
    	<!--列表导航-->
    	<div class="left_yh Selected" style="width:230px;height:45px;" id="hiddenShow">
        	<!--头部的图标-->
        	<img src="${ctx}/resource/user/images/cd.png" class="left_yh" style="margin-left:24px;">
            <span class="block_yh left_yh fff" style="height:45px;line-height:44px;margin-left:10px;">菜品分类</span>
            <!--下面的导航展开部分-->
            <div class="downSlide">
            
              <c:forEach items="${lbs}" var="data" varStatus="l">
	              <div class="n1Nav">
	                	<font>${data.father.name}</font>
	                    <img src="${ctx}/resource/user/images/jt.png">
	                    <div class="n2Nav">
		                        <div class="n3Nav">
			                          <h3>${data.father.name}</h3>
			                         <c:forEach items="${data.childrens}" var="chil" varStatus="l">
			                            <a href="${ctx}/item/shoplist?categoryIdTwo=${chil.id}">${chil.name }</a>
			                          </c:forEach>
		                        </div>
	                    </div>
	                </div>
              </c:forEach>
            	<!--一块-->
            </div>
        </div>
        <!--普通导航-->
        <div class="left_yh font16" id="pageNav">
        	<a href="${ctx}/login/uIndex">首页</a>
        	<a href="${ctx}/news/list">最新公告</a>
        	<a href="${ctx}/message/add">售后服务</a>
        </div>
    </div>
</div>


<!--banner-->
<div class="width1200 center_yh hidden_yh" style="position:relative;z-index:80;">
    <div class="example2" style="width:1200px;height:490px;overflow:hidden;margin-left:230px">
        <ul>
            <li><img src="${ctx}/resource/images/index01.jpg" alt="1"/></li>
            <li><img src="${ctx}/resource/images/index02.jpg" alt="1"/></li>
            <li><img src="${ctx}/resource/images/index03.jpg" alt="1"/></li>
            <li><img src="${ctx}/resource/images/index04.jpg" alt="1"/></li>
        </ul>
    </div>
    <!--Luara图片切换骨架end-->
    <script>
        $(function(){
            $(".example2").luara({width:"966",height:"490",interval:4500,selected:"seleted",deriction:"left"});
        });
    </script>
</div>

<!--热门-->

<!--折扣大促销-->
<div class="width1200 center_yh hidden_yh">
	<div class="width100" style="height:45px;line-height:45px;border-bottom:2px solid #dd4545;margin-top:20px;">
    	<font class="left_yh font20">折扣大促销</font>
        <a href="#" class="right_yh c_33 font16">更多»</a>
    </div>
    <div class="width100 hidden_yh" style="height:480px;">
    	
        <div class="normalPic">
        
             <c:forEach items="${zks}" var="data" varStatus="l">
	             <a href="${ctx}/item/view?id=${data.id}">
	            	<h3 class="yihang c_33 font14 font100" style="padding-left:10px;padding-right:10px;margin-top:10px;">${data.name}</h3>
	                <p class="red font14" style="padding-left:10px;">￥${data.price}</p>
	                <img src="${ctx}/resource${data.url1}" width="105" height="118" style="margin:0 auto">
	            </a>
             </c:forEach>
           
        </div>
    </div>
</div>
<!--热门菜品-->
<div class="width1200 center_yh hidden_yh">
	<div class="width100" style="height:45px;line-height:45px;border-bottom:2px solid #dd4545;margin-top:20px;">
    	<font class="left_yh font20">热门菜品</font>
        <a href="#" class="right_yh c_33 font16">更多»</a>
    </div>
    <div class="width100 hidden_yh" style="height:480px;">
        <div class="normalPic">
        	 <c:forEach items="${rxs}" var="data" varStatus="l">
	             <a href="${ctx}/item/view?id=${data.id}">
	            	<h3 class="yihang c_33 font14 font100" style="padding-left:10px;padding-right:10px;margin-top:10px;">${data.name}</h3>
	                <p class="red font14" style="padding-left:10px;">￥${data.price}</p>
	                <img src="${ctx}/resource${data.url1}" width="105" height="118" style="margin:0 auto">
	            </a>
             </c:forEach>
        </div>
    </div>
</div>

<div class="width1200 center_yh hidden_yh">
	<div class="width100" style="height:45px;line-height:45px;border-bottom:2px solid #dd4545;margin-top:20px;">
    	<font class="left_yh font20">推荐菜品</font>
        <a href="#" class="right_yh c_33 font16">更多»</a>
    </div>
    <div class="width100 hidden_yh" style="height:480px;">
        <div class="normalPic">
        	 <c:forEach items="${tjs}" var="data" varStatus="l">
	             <a href="${ctx}/item/view?id=${data.id}">
	            	<h3 class="yihang c_33 font14 font100" style="padding-left:10px;padding-right:10px;margin-top:10px;">${data.name}</h3>
	                <p class="red font14" style="padding-left:10px;">￥${data.price}</p>
	                <img src="${ctx}/resource${data.url1}" width="105" height="118" style="margin:0 auto">
	            </a>
             </c:forEach>
        </div>
    </div>
</div>


    
    
 <%@ include file="/common/ufooter.jsp" %>



</body>
</html>