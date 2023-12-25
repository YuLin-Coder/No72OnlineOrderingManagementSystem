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
   <title>后台管理中心</title>  
   <link rel="stylesheet" href="${ctx}/resource/css/pintuer.css">
    <link rel="stylesheet" href="${ctx}/resource/css/admin.css">
    <script src="${ctx}/resource/js/jquery.js"></script>
    <script src="${ctx}/resource/js/pintuer.js"></script>  
    
</head>
<body>
<form method="post" action="">
  <div class="panel admin-panel">
    <div class="padding border-bottom">
    <ul class="search" style="padding-left:10px;">
       <li> <a class="button border-main icon-plus-square-o" href="${ctx}/itemCategory/add2?pid=${obj.pid}"> 新增二级类目</a> </li>
    </ul>
      
    </div>
    <table class="table table-hover text-center">
      <tr>
               <th>ID</th>
               <th>类别名称</th>
               <th>操作</th>
       </tr> 
       
         <c:forEach items="${pagers.datas}" var="data" varStatus="l">
	                  <tr>
	                    <td>${data.id}</td>
	                    <td >${data.name} </td>
	                    
	                     <td>
	                       <div class="button-group">
	                           <a class="button border-main" href="${ctx}/itemCategory/update2?id=${data.id}"><span class="icon-edit"></span> 修改</a>
	                           <a class="button border-red" href="${ctx}/itemCategory/delete2?id=${data.id}&pid=${obj.pid}">
	                                <span class="icon-trash-o"></span> 删除
	                           </a>
	                        </div>
	                     </td>
	                </tr>
         </c:forEach>
                  
      
       <tr>
        <td colspan="8">
		        <div class="pagelist">
		        <!-- 分页开始 -->
					      <pg:pager  url="${ctx}/itemCategory/findBySql2" maxIndexPages="5" items="${pagers.total}"  maxPageItems="15" export="curPage=pageNumber" >
					        
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
				 </td>
                    <!-- 分页结束 -->
      			 </tr>
    </table>
  </div>
</form>
<script type="text/javascript">

function del(id){
	if(confirm("您确定要删除吗?")){
		
	}
}


</script>
</body>
</html>