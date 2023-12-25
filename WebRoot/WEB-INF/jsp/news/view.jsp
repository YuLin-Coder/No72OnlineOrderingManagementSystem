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
   <title>个人中心</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/resource/user/css/style.css">
	<script src="${ctx}/resource/user/js/jquery-1.8.3.min.js"></script>
	<script src="${ctx}/resource/user/js/jquery.luara.0.0.1.min.js"></script>
	
	<style type="text/css">
	
	.cff a {
	 color: red;
	}
	</style>
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
        	<a href="${ctx}/news/list">公告</a>
        	<a href="${ctx}/message/add">留言</a>
        </div>
    </div>
</div>

<!--当前位置-->
<div class="width1200 center_yh hidden_yh font14" style="height:40px;line-height:40px;">
</div>
<div class="width100 hidden_yh" style="background:#f0f0f0;padding-top:34px;padding-bottom:34px;">
	<div class="width1200 hidden_yh center_yh">
    	
        <div id="vipRight" style="width: 1200px">
            <div class="hidden_yh bj_fff" style="width:1198px;border:1px solid #ddd;">
            	<div class="width100 font24" style="height: 50px;line-height: 50px;width: 200px;text-align: center;">${obj.name}</div>
                <div class="hidden_yh" style="padding:20px;width:1100px;margin: 0 auto">
                 <!--一条-->
                 <a href="">
                    <div class="width100 hidden_yh" style="border-bottom:1px dashed #ddd;padding-top:10px;padding-bottom:10px;">
                        <div class="left_yh cff" style="width:100%;color:black">
                            ${obj.content}
                        </div>
                    </div>
                    </a>
                    <!--一条-->
                 
                </div>
            </div>
        </div>
    </div>
    
         
</div>
 <%@ include file="/common/ufooter.jsp" %>
</body>
<script type="text/javascript">

$(function(){
	
	$(".sub").click(function(){
		$("#myf").submit();
		
		
	});
	
});

</script>
</html>