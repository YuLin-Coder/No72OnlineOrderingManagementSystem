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
    <script src="${ctx}/resource/js/jquery.js"></script>
    
    <link rel="stylesheet" href="${ctx}/resource/css/pintuer.css">
    <link rel="stylesheet" href="${ctx}/resource/css/admin.css">
    <script src="${ctx}/resource/js/jquery.js"></script>
    <script src="${ctx}/resource/js/pintuer.js"></script>  
     <script type="text/javascript" src="${ctx}/resource/ueditor/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="${ctx}/resource/ueditor/ueditor.all.min.js"></script>
   
</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>新增菜品</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="${ctx}/item/exUpdate"  enctype="multipart/form-data"> 
    
      <input type="hidden" name="id" value="${obj.id}"/>
      
       
      <div class="form-group">
        <div class="label">
          <label>菜品名称：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="${obj.name }"  name="name" data-validate="required:请输入名称" />
          <div class="tips"></div>
        </div>
      </div>
      
       <div class="form-group">
        <div class="label">
          <label>菜品价格：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="${obj.price }"  name="price" data-validate="required:请输入价格" />
          <div class="tips"></div>
        </div>
      </div>
      
      
       <div class="form-group">
        <div class="label">
          <label>菜品折扣：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="${obj.zk}" name="zk" data-validate="required:请输入菜品折扣" />
          <div class="tips"></div>
        </div>
      </div>
      
       
       <div class="form-group">
        <div class="label">
          <label>菜品类别：</label>
        </div>
        <div class="field">
          <select name="categoryIdTwo" class="input w50">
            <c:forEach items="${types}" var="data" varStatus="l">
                  <option value="${data.id}" ${obj.categoryIdTwo == data.id ? "selected" : ""}>${data.name}</option>
            </c:forEach>
          </select>
        </div>
      </div>
      
       <div class="form-group">
        <div class="label">
          <label>主图</label>
        </div>
        <div class="field">
          <input type="file" class="input w50" value="" name="file"  />
          <div class="tips"></div>
        </div>
      </div>
      
      <div class="form-group">
        <div class="label">
          <label>副图1：</label>
        </div>
        <div class="field">
          <input type="file" class="input w50" value="" name="file"  />
          <div class="tips"></div>
        </div>
      </div>
      
      <div class="form-group">
        <div class="label">
          <label>副图2:</label>
        </div>
        <div class="field">
          <input type="file" class="input w50" value="" name="file"  />
          <div class="tips"></div>
        </div>
      </div>
      
      <div class="form-group">
        <div class="label">
          <label>副图3:</label>
        </div>
        <div class="field">
          <input type="file" class="input w50" value="" name="file"  />
          <div class="tips"></div>
        </div>
      </div>
      
      <div class="form-group">
        <div class="label">
          <label>副图4:</label>
        </div>
        <div class="field">
          <input type="file" class="input w50" value="" name="file"  />
          <div class="tips"></div>
        </div>
      </div>
      
      
       <div class="form-group">
        <div class="label">
          <label>描述：</label>
        </div>
        <div class="field">
                          
                 <!-- 加载编辑器的容器 -->
					        <script id="remark_txt_1" name="ms" type="text/plain" style="width:100%;height:300px;">${obj.ms}</script>
						    <!-- 实例化编辑器 -->
						    <script type="text/javascript">
						        var editor = UE.getEditor('remark_txt_1');
						        UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;  
						        UE.Editor.prototype.getActionUrl = function(action) {  
						              if (action == 'uploadimage' || action == 'uploadscrawl' || action == 'uploadvideo') {  
						                  return '${ctx}/ueditor/saveFile';  
						              } else {  
						                  return this._bkGetActionUrl.call(this, action);  
						              }  
						        }  
						    </script>  
          <div class="tips"></div>
        </div>
      </div>
      
      
      
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" type="submit"> 提交</button>
        </div>
      </div>
    </form>
  </div>
</div>

</body>
</html>