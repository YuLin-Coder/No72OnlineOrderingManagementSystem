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
        	<a href="${ctx}/news/list">最新公告</a>
        	<a href="${ctx}/message/add">售后服务</a>
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
            	<div class="width100 font24" style="height:60px;line-height:60px;text-indent:50px;background:#f5f8fa;border-bottom:1px solid #ddd;">公告列表</div>
                <div class="hidden_yh" style="padding:20px;width:898px;">
                 <c:forEach items="${pagers.datas}" var="data" varStatus="l">
                 <!--一条-->
                 <a href="${ctx}/news/view?id=${data.id}">
                    <div class="width100 hidden_yh" style="border-bottom:1px dashed #ddd;padding-top:10px;padding-bottom:10px;">
                        <div class="left_yh" style="width:580px;">
                        	<h3 class="font18 c_33 font100"></h3>
                            <p class="red" style="margin-top:10px;">${data.name}</p>
                        </div>
                        <div class="right_yh">
                            <a href="" class="onfff block_yh tcenter font16" style="margin-top:10px;padding:6px;"><fmt:formatDate value="${data.addTime }" pattern="yyyy-MM-dd HH:mm:ss"/></a>
                        </div>
                    </div>
                    </a>
                    <!--一条-->
                 
                 </c:forEach>
                </div>
            </div>
        </div>
        
         <div class="pagelist">
		        <!-- 分页开始 -->
					      <pg:pager  url="${ctx}/news/list" maxIndexPages="5" items="${pagers.total}"  maxPageItems="15" export="curPage=pageNumber" >
					      
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