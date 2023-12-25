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
   <title>登录</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/resource/user/css/style.css">
	<script src="${ctx}/resource/user/js/jquery-1.8.3.min.js"></script>
	<script src="${ctx}/resource/user/js/jquery.luara.0.0.1.min.js"></script>
</head>
<body>



<div class="width100">
	<div class="width1200 center_yh hidden_yh" style="height:181px">
    	<a href="#" class="left_yh"><img src="${ctx}/resource/images/loogo.jpg" style="height: 80px;padding-top: 30px"></a>
        <a href="${ctx}/login/res" class="right_yh font16 c_33" style="line-height:181px;">还没有账号，<font class="red">马上去注册></font></a>
    </div>
</div>
<div class="width100 hidden_yh" style="height:573px;background:url(${ctx}/resource/user/images/bj.jpg) no-repeat center">
	<div class="width1200 center_yh hidden_yh" style="margin-top:70px">
    	<div class="right_yh bj_fff" style="width:408px;height:438px;">
        	<h3 class="tcenter font30 c_33" style="font-weight:100;margin-top:36px;margin-bottom:36px;">账户登录</h3>
            <div class="center_yh hidden_yh" style="width:336px;">
            	 <form action="${ctx}/login/utoLogin" method="post">
                    <div class="width100 box-sizing hidden_yh" style="height:44px;border:1px solid #c9c9c9;margin-bottom:34px;">
                        <img src="${ctx}/resource/user/images/rw.jpg" class="left_yh" width="42" height="42">
                        <input type="text" placeholder="请输入用户名或手机号" name="userName" value="zs" style="border:0;width:292px;height:42px;font-size:16px;text-indent:22px;">
                    </div>
                    <div class="width100 box-sizing hidden_yh" style="height:44px;border:1px solid #c9c9c9;margin-bottom:14px;">
                        <img src="${ctx}/resource/user/images/pass.jpg" class="left_yh" width="42" height="42">
                        <input type="password" placeholder="请输入密码" name="passWord" value="123456" style="border:0;width:292px;height:42px;font-size:16px;text-indent:22px;">
                    </div>
                    <p class="width100 tright font16" style="margin-bottom:26px;"><a href="#" style="color:#4585dd;">忘记密码?</a></p>
                    <input type="submit" value="登录" class="center_yh" style="width:100%;height:43px;font-size:16px;background:#dd4545;outline:none;border:0;color:#fff;cursor:pointer;">
                </form>
            </div>
        </div>
    </div>
</div>
    
    
 <%@ include file="/common/ufooter.jsp" %>



</body>
</html>