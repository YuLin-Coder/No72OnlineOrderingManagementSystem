<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@include file="/common/taglibs.jsp"%>
 <!--头部-->
 
   <div class="width100" style="height:25ox;background:#f0f0f0;">
	<div class="width1200 center_yh font14 c_66" style="height:25px;line-height:25px;">
    	<font class="left_yh">欢迎来到在线订餐系统！</font>
        <div class="right_yh" id="fast_jump">
        
            <c:if test="${userId != null }">
            <a href="#">欢迎您：${username}</a>
            <b></b>
             <a href="${ctx}/login/uTui">退出</a>
            <b></b>
            
             <a href="${ctx}/car/findBySql">我的购物车</a>
             <b></b>
            
            </c:if>
            <c:if test="${userId == null }">
            <a href="${ctx}/login/uLogin">登录</a>
            <b></b>
            <a href="${ctx}/login/res">注册</a>
            <b></b>
            </c:if>
        	
            <a href="${ctx}/user/view">个人中心</a>
        </div>
    </div>
</div>
<div class="width100 hidden_yh" >
	<div class="width1200 center_yh hidden_yh" style="margin-bottom: -0px">
    	<a href="#" class="block_yh left_yh" style="margin-top:10px;"><img src="${ctx}/resource/images/loogo.jpg" style="height:70px"></a>
        <div class="right_yh" style="height:28px;width:316px;border:2px solid #dd4545;margin-top:-20px;">
        	<form action="${ctx}/item/shoplist" method="post">
                <input type="text" placeholder="关键词查询" class="searCh" name="condition" value="${condition }">
                <input type="submit" class="btnSearh" value="搜索">
            </form>
        </div>
    </div>
</div>