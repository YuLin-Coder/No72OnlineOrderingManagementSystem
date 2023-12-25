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
        	<a href="${ctx}/news/list">新闻</a>
        	<a href="${ctx}/message/add">售后服务</a>
        </div>
    </div>
</div>


<!--当前位置-->
<div class="width1200 center_yh hidden_yh font14" style="height:40px;line-height:40px;">
</div>
<div class="width100 hidden_yh" style="background:#f0f0f0;padding-top:34px;padding-bottom:34px;">
	<div class="width1200 hidden_yh center_yh">
    	
        <div id="vipRight" style="width:1200px">
            <div class="font24" style="height:60px;line-height:60px;text-indent:50px;background:#f5f8fa;width:1200px;border:1px solid #ddd;">
            	提交留言
         	</div>
         	
         	
            <div class="bj_fff hidden_yh" style="width:1200px;border:1px solid #ddd;margin-top:29px;padding:50px;">
                <div class="width100" style="height:32px;line-height:32px;">
                	<div class="left_yh font16 tright" style="width:120px;"><font class="red">*</font>姓名：</div>
                    <input type="text" id="jiu" style="width:243px;border:1px solid #ddd;outline:none;height:30px;text-indent:10px;">
                </div>
                <div class="width100" style="height:32px;line-height:32px;margin-top:25px;">
                	<div class="left_yh font16 tright" style="width:120px;"><font class="red">*</font>联系方式：</div>
                    <input type="password" id="xin" style="width:243px;border:1px solid #ddd;outline:none;height:30px;text-indent:10px;">
                </div>
                <div class="width100" style="height:32px;line-height:32px;margin-top:25px;">
                	<div class="left_yh font16 tright" style="width:120px;"><font class="red">*</font>内容：</div>
                    <textarea rows="10" cols="60" id="que" style="style="width:303px;border:1px solid #ddd;outline:none;height:30px;text-indent:10px;"">  </textarea>
                    
                  
                </div>
                <div class="width100" style="height:32px;line-height:32px;margin-top:183px;">
                	<a href="javascript:void(0)" class="left_yh block_yh font16 tcenter ff5802 con" style="width:244px;height:33px;line-height:33px;margin-left:120px;">保存</a>
                </div>
            </div>
    	</div>
    </div>    
</div>
</body>
<script type="text/javascript">
		$(function(){
			
			$(".con").click(function(){
				var name = $("#jiu").val();
				var phone = $("#xin").val();
				var content = $("#que").val();
				if (name == null || name == ''){
					alert("请填写完整信息");
					return false;
				}
				if (phone == null || phone == ''){
					alert("新密码不能为空");
					return false;
				}
				
				 $.ajax({  
	                    type : "POST",  //提交方式  
	                    url : "${ctx}/message/exAdd.do",//路径  
	                    data : {  
	                        "name" : name,
	                        "phone":phone,
	                        "content":content
	                    },//数据，这里使用的是Json格式进行传输  
	                    success : function(result) {//返回数据根据结果进行相应的处理  
	                    	alert("您的反馈很重要，谢谢！");
	                        window.location.href = "${ctx}/message/add";
	                    }  
	                });  
			});
			
		});
	</script>
</html>