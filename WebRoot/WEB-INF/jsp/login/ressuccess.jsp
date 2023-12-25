<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
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
   <link rel="stylesheet" type="text/css" href="${ctx}/resource/js/bootstrap-3.3.6/css/bootstrap.min.css"/>
		<link rel="stylesheet" href="${ctx}/resource/css/open.css" />
		<script src="${ctx}/resource/js/jquery-1.11.3/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${ctx}/resource/js/bootstrap-3.3.6/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
   
</head>
<body>

<!-- private String userName;
	private String passWord;
	private String phone;
	private String linkMan;
	private String gs; -->
		<%@ include file="/common/top.jsp" %>
		<div class="reg-con">
			<div class="form-con clearfix">
				<div class="back-con reg-form finish-con regbg">
					<div class="finish"></div>
					<div class="title ft3">注册提交成功！</div>
					<div class="inp-list">
						<p class="ft6"><a href="${ctx}/login/uLogin">去登录></a></p>
						<!-- <p class="ft5">平台将在1个工作日内进行审核注册信息。通过审核前，无法浏览该平台相关内容。</p> -->
					</div>
				</div>
			</div>
		</div>
	</body>
<script type="text/javascript">
		$(function(){
			var h = $(window).height() - $('.header').height() - $('.footer').height();
			$('.reg-con').height(h);
			
		});
	</script>
</html> --%>