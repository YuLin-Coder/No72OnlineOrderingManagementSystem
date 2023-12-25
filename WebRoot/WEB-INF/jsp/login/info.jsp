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
   <title>首页</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/resource/js/bootstrap-3.3.6/css/bootstrap.min.css"/>
		<link rel="stylesheet" href="${ctx}/resource/css/open.css" />
		<script src="${ctx}/resource/js/jquery-1.11.3/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${ctx}/resource/js/bootstrap-3.3.6/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
		<div class="frame-con">
			<div style="width: 100%;height: 1px;"></div>
			<div class="con-box">
				<div class="tit"><span class="line"></span>账户信息</div>
				<div class="info-list clearfix">
					<label class="left">登录账号</label>
					<span class="left">${user.userName }</span>
				</div>
				<div class="info-list clearfix">
					<label class="left">联系人</label>
					<span class="left">${user.linkMan }</span>
				</div>
				<div class="info-list clearfix">
					<label class="left">联系电话</label>
					<span class="left">${user.phone }</span>
				</div>
				<div class="info-list clearfix">
					<label class="left">公司</label>
					<span class="left">${user.gs }</span>
				</div>
			</div>
		</div>
	</body>
<script type="text/javascript">
		$(function(){
			var parm = parent.getWH();
			$('.frame-con').width(parm.w).height(parm.h);
		});
	</script>
</html> --%>