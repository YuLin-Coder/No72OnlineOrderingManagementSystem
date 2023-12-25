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
   <title>列表</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/resource/user/css/style.css">
	<script src="${ctx}/resource/user/js/jquery-1.8.3.min.js"></script>
	<script src="${ctx}/resource/user/js/jquery.luara.0.0.1.min.js"></script>
	
	<style type="text/css">
	
	.highligter{
	 color: red;
	 font-weight: bold;
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
        </div>
    </div>
</div>
<!--当前位置-->
<div class="width1200 center_yh hidden_yh font14" style="height:40px;line-height:40px;">
	<span>当前位置:</span><a href="#" class="c_66">首页></a><a href="#" class="c_66">菜品列表</a>
</div>

<div class="width1198 center_yh" style="height:35px;background:#f0f0f0;border:1px solid #ddd;margin-top:25px;">
	<a href="${ctx}/item/shoplist?categoryIdTwo=${obj.categoryIdTwo}" class="mR">默认</a>
    <a href="${ctx}/item/shoplist?price=1&categoryIdTwo=${obj.categoryIdTwo}" class="mR">价格<img src="${ctx}/resource/user/images/gg.png"></a>
    <a href="${ctx}/item/shoplist?gmNum=1&categoryIdTwo=${obj.categoryIdTwo}" class="mR">销量<img src="${ctx}/resource/user/images/gg.png"></a>
    <script>
    	$(".mR").click(function(){
    		var src = '${ctx}/resource/user/images/gg2.png';
    		//alert(src);
    		$(this).addClass("on");
			//$(this).addClass("on").find("img").attr("src",src);
			//$(this).siblings().removeClass("on");
			})
    </script>
</div>
<!--菜品列表-->
<div class="width1200 center_yh hidden_yh" style="margin-top:25px;">
    <ul class="listSs">
    
    
     <c:forEach items="${pagers.datas}" var="data" varStatus="l">
     
        <li>
            <a href="${ctx}/item/view?id=${data.id}" class="bjK">
            	<img src="${ctx}/resource${data.url1}">
            </a>
            <h3 class="spName">${data.name}</h3>
            <p class="center_yh block_yh hidden_yh" style="width:202px;">
            	<font class="left_yh red font16">￥${data.price}.00</font>
            	<c:if test="${data.zk != null}">
            	<font class="right_yh c_66 font14" >${data.zk}<span style="color: red">折</span></font>
            	</c:if>
                
            </p>
            <div class="wCa">
            <a href="${ctx}/sc/exAdd?itemId=${data.id}" class="fav"><div class="wCa1">
                	<b><img src="${ctx}/resource/user/images/star.png"></b>
                    <font>收藏</font>
                </div></a>
            	
                <div class="wCa2"  onclick="addcar('${data.id}')">
                	<b><img src="${ctx}/resource/user/images/sar.png"></b>
                    <font>加入购物车</font>
                </div>
            </div>
        </li>
     
     </c:forEach>
        
       
    </ul>
</div>
<div id="navs">
     <div class="pagelist">
		        <!-- 分页开始 -->
					      <pg:pager  url="${ctx}/item/shoplist" maxIndexPages="5" items="${pagers.total}"  maxPageItems="15" export="curPage=pageNumber" >
					        <pg:param name="categoryIdTwo" value="${obj.categoryIdTwo}"/>
					        <pg:param name="price" value="${obj.price}"/>
					        <pg:param name="gmNum" value="${obj.gmNum}"/>
					          <pg:param name="condition" value="${condition}"/>
					        
							<pg:last>  
								共${pagers.total}记录,共${pageNumber}页,  
							</pg:last>  
								当前第${curPage}页 
					        <pg:first>  
					    		<a href="${pageUrl}">首页</a>  
							</pg:first>  
							<pg:prev>  
					    		<a href="${pageUrl}">上一页</a>  
							</pg:prev>  
					       	<pg:pages>  
					        	<c:choose>  
					            	<c:when test="${curPage eq pageNumber}">  
					                	<font color="red">[${pageNumber }]</font>  
					            	</c:when>  
					            	<c:otherwise>  
					               		<a href="${pageUrl}">${pageNumber}</a>  
					            	</c:otherwise>  
					        	</c:choose>  
					    	</pg:pages>
					             
					        <pg:next>  
					    		<a href="${pageUrl}">下一页</a>  
							</pg:next>  
							<pg:last>  
								<c:choose>  
					            	<c:when test="${curPage eq pageNumber}">  
					                	<font color="red">尾页</font>  
					            	</c:when>  
					            	<c:otherwise>  
					               		<a href="${pageUrl}">尾页</a>  
					            	</c:otherwise>  
					        	</c:choose> 
					    		  
							</pg:last>
						</pg:pager>
					</div>
</div>
<script>
$(".wCa div").click(function(){
	$(this).addClass("on")
	})
	
	function addcar(id){
	   var num = 1;
	   var s = "${ctx}/car/exAdd?itemId="+id+"&num="+num;
	   $.ajax({  
      type : "POST",  //提交方式  
      url : "${ctx}/car/exAdd?itemId="+id+"&num="+num,//路径  
      success : function(result) {//返回数据根据结果进行相应的处理  
      	var re = JSON.parse(result)
      	if (re.res == 0){
      		alert("请登陆");
      		window.location.href = "${ctx}/login/uLogin";
      	}else{
      		window.location.href = "${ctx}/car/findBySql";
      	}
      }  
  }); 
}
</script>
    
 <%@ include file="/common/ufooter.jsp" %>



</body>
</html>