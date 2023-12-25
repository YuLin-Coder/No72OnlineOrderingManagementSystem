<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@include file="/common/taglibs.jsp"%>
   <div class="float-con">
			<span class="icon consult"></span>
			<a href="#topbar" class="icon top"></a>
		</div>
		<!--弹窗-->
		<div class="fullscreen" id="message-win">
	  		<div class="full-con">
	  			<span class="close">×</span>
	  			<div class="form">
	  				<p class="tit">咨询反馈</p>
	  				<div class="list">
	  					<input type="text" id="title" placeholder="输入咨询标题" />
	  				</div>
	  				<div class="list">
	  					<textarea name="" id="con" rows="6" placeholder="告诉我们你遇到的问题..."></textarea>
	  				</div>
	  				<div class="list">
	  					<span class="confirm">确认</span>
	  				</div>
	  			</div>
	  		</div>
	  	</div>
<div class="footer">
			&copy; 2017XXXXX软件有限公司
		</div>
		
		<script>
		
		 $(function(){
			 $(".confirm").click(function(){
				 var title= $("#title").val();
				 var con = $("#con").val();
				 if (title == null || title == ""){
					 alert("请填写标题");
					 return false;
				 }
				 if (con == null || con == ""){
					 alert("请填写内容");
					 return false;
				 }
				 $.ajax({  
		             type : "POST",  //提交方式  
		             url : "${ctx}/pl/exAdd.do",//路径  
		             data : {  
		                 "title" :$("#title").val(),"content":$("#con").val()
		             },//数据，这里使用的是Json格式进行传输  
		             success : function(result) {//返回数据根据结果进行相应的处理  
		             if (result.res == 0){
		            	 alert("请重新登陆");
		            	 top.location.href = "${ctx}/login/uLogin";
		             }else{
		            	 alert("留言成功");
		            	 $("#title").val("");
						 $("#con").val("");
		            	$(".fullscreen").hide();
		            	
		            	$("#message-win").hide();
		             }
		             }  
		         });   
			 });
		 });
		
		
		</script>