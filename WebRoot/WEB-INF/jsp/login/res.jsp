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
   <title>注册</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/resource/user/css/style.css">
	<script src="${ctx}/resource/user/js/jquery-1.8.3.min.js"></script>
	<script src="${ctx}/resource/user/js/jquery.luara.0.0.1.min.js"></script>
</head>
<body>


<div class="width100">
	<div class="width1200 center_yh hidden_yh" style="height:181px">
    		<a href="#" class="left_yh"><img src="${ctx}/resource/images/loogo.jpg" style="height: 80px;padding-top: 30px"></a>
        <a href="${ctx}/login/uLogin" class="right_yh font16 c_33" style="line-height:181px;">已有帐号，<font class="red">马上去登录></font></a>
    </div>
</div>
<div class="width100 hidden_yh" style="border-top:1px solid #ddd">
	<div class="width1200 hidden_yh center_yh" style="margin-top:75px">
	
	<form action="${ctx}/login/toRes" method="post">
        <div class="width100 hidden_yh">
        	<div class="center_yh hidden_yh" style="width:475px;margin-bottom:40px;">
            	<span style="margin-right:40px;height:42px;line-height:42px;width:100px" class="left_yh block_yh tright">用户名:</span>
                <input type="text"  name="userName" placeholder="请输入您的用户名" style="border:1px solid #c9c9c9;width:292px;height:42px;font-size:16px;text-indent:22px;" class="left_yh">
            </div>
            <div class="center_yh hidden_yh" style="width:475px;margin-bottom:40px;">
            	<span style="margin-right:40px;height:42px;line-height:42px;width:100px" class="left_yh block_yh tright">设置密码:</span>
                <input type="text" name="passWord" placeholder="建议至少使用两种字符组合" style="border:1px solid #c9c9c9;width:292px;height:42px;font-size:16px;text-indent:22px;" class="left_yh">
            </div>
            
            <div class="center_yh hidden_yh" style="width:475px;margin-bottom:40px;">
            	<span style="margin-right:40px;height:42px;line-height:42px;width:100px" class="left_yh block_yh tright">手机号:</span>
                <input type="text" name="phone" placeholder="建议使用常用的手机" style="border:1px solid #c9c9c9;width:292px;height:42px;font-size:16px;text-indent:22px;" class="left_yh">
            </div>
             <div class="center_yh hidden_yh" style="width:475px;margin-bottom:40px;">
            	<span style="margin-right:40px;height:42px;line-height:42px;width:100px" class="left_yh block_yh tright">邮箱:</span>
                <input type="email" name="email" placeholder="请输入邮箱" style="border:1px solid #c9c9c9;width:292px;height:42px;font-size:16px;text-indent:22px;" class="left_yh">
            </div>
            <p class="font14 c_66" style="text-indent:503px;margin-top:30px;"><input type="checkbox">我已阅读并同意<a href="#" class="red">«会员注册协议»</a>和<a href="#" class="red">«隐私保护政策»</a></p>
            <input type="submit" value="提交" class="ipt_tj" style="width:295px;height:44px;margin-left:505px;">
        </div>
        
        </form>
    </div>
</div>
    
 <%@ include file="/common/ufooter.jsp" %>



</body>
</html>