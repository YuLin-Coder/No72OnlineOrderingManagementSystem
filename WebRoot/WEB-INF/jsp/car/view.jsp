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
   <title>提交订单</title>
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
        <!--普通导航-->
        <div class="left_yh font16" id="pageNav">
        	<a href="${ctx}/login/uIndex">首页</a>
        </div>
    </div>
</div>


<!--当前位置-->
<div class="width1200 center_yh hidden_yh font14" style="height:40px;line-height:40px;">
	<span>当前位置:</span><a href="#" class="c_66">首页></a><a href="#" class="c_66">购物车></a><a href="#" class="c_66">提交成功</a>
</div>
<!--第一步为on1,on2-->



<div class="hidden_yh center_yh" style="width:344px;height:482px;margin-top:50px;">
    <img src="${ctx}/itemOrder/getErWeiCode?id=${id}"  class="left_yh" style="display: block;float: left;overflow: hidden;">
    <div style="width:284px;height:82px;float:right;">
    	<p class="font24">请扫码支付</p>
        <p class="font18 c_99">订单编号${code}<a href="${ctx}/login/uIndex" class="red onHover">&nbsp;继续去逛逛»</a></p>
    </div>
</div>
    
    
 <%@ include file="/common/ufooter.jsp" %>



</body>
</html>