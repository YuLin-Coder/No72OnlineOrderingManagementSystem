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
<form method="post" action="${ctx}/itemOrder/findBySql" id="listform">
  <div class="panel admin-panel">
    <div class="padding border-bottom">
    <ul class="search" style="padding-left:10px;">
       <li>
          <input type="text" placeholder="订单号" name="code" class="input" value="${obj.code}" style="width:250px; line-height:17px;display:inline-block" />
          <a href="javascript:void(0)" class="button border-main icon-search" onclick="changesearch()" > 搜索</a></li>
    </ul>
      
    </div>
    <table class="table table-hover text-center">
     <tr>
                            <th>订单号</th>
                            <th>下单时间</th>
                            <th>总金额</th>
                            <th>下单人</th>
                            <th>订单状态</th>
                            <th>操作</th>
                        </tr>
                        
        <c:forEach items="${pagers.datas}" var="data" varStatus="l">
	                  <tr>
	                    <td>${data.code}</td>
	                    <td><fmt:formatDate value="${data.addTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                    <td >${data.total} </td>
	                    <td>${data.user.userName}</td>
	                    <td style="color: red">
	                    
	                  <c:if test="${data.status == 0}"> 代发货</c:if>
                      <c:if test="${data.status == 1}"> 已取消</c:if>
                      <c:if test="${data.status == 2}"> 待收货</c:if>
                      <c:if test="${data.status == 3}"> 已收货</c:if>
	                    </td>
	                   <td>
	                         
	                         <a class="button border-main" href="${ctx}/orderDetail/ulist?orderId=${data.id}"><span class="icon-edit"></span> 查看购买商品</a>
	                         
	                           <c:if test="${data.status == 0}">
	                            <a class="button border-main" href="${ctx}/itemOrder/fh?id=${data.id}"><span class="icon-edit"></span> 去发货</a>
	                           </c:if>
	                        
	                           
	                    </td> 
	                </tr>
                  </c:forEach>
                  
      
       <tr>
        <td colspan="8">
		        <div class="pagelist">
		        <!-- 分页开始 -->
					      <pg:pager  url="${ctx}/itemOrder/findBySql" maxIndexPages="5" items="${pagers.total}"  maxPageItems="15" export="curPage=pageNumber" >
					        
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

//搜索
//搜索
function changesearch(){	
		$("#listform").submit();
}

$("#checkall").click(function(){ 
  $("input[name='id[]']").each(function(){
	  if (this.checked) {
		  this.checked = false;
	  }
	  else {
		  this.checked = true;
	  }
  });
})

function DelSelect(){
	var Checkbox=false;
	 $("input[name='id[]']").each(function(){
	  if (this.checked==true) {		
		Checkbox=true;	
	  }
	});
	if (Checkbox){
		var t=confirm("您确认要删除选中的内容吗？");
		if (t==false) return false; 		
	}
	else{
		alert("请选择您要删除的内容!");
		return false;
	}
}

</script>
</body>
</html>