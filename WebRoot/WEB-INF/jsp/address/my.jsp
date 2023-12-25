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
		<script src="${ctx}/resource/js/jquery-1.11.3/jquery.min.js" type="text/javascript" charset="utf-8"></script>
    
      <link href="${ctx}/resource/uc/iconfont/iconfont.css" rel="stylesheet"/>
 <link href="${ctx}/resource/uc/common.css" rel="stylesheet"/>
    <link href="${ctx}/resource/uc/uc.css" rel="stylesheet"/>
</head>
    
    <style type="text/css">
    
    </style>
</head>
<body style="height: 100%">
    <!--头部-->
      <%@ include file="/common/utop.jsp" %>
    <div class="wrapper uc-router">
    </div>

    <div class="wrapper">
        <div class="uc-main clearfix">
            <div class="uc-aside">
                <div class="uc-menu">
                    <div class="tit">订单中心</div>
                    <ul class="sublist">
                        <li><a class="" href="${ctx}/itemOrder/my">我的订单</a></li>
                        <li><a  href="${ctx}/sc/findBySql">我的收藏</a></li>
                    </ul>
                    <div class="tit">账户中心</div>
                    <ul class="sublist">
                        <li><a href="${ctx}/user/view">账户信息</a></li>
                        <li><a class="active" href="${ctx}/address/my">收货地址</a></li>
                    </ul>
                </div>
            </div>
       <div class="uc-content">
                <div class="uc-panel">
                    <div class="uc-bigtit">收货地址</div>
                    <div class="uc-panel-bd">


                            <div class="address-list">
                                <div class="col col-4">
                                    <a class="item va-m-box ta-c add">
                                        <div class="add-new">
                                            <span class="ico"><i class="iconfont icon-tianjia"></i></span>
                                            <div class="label">添加收货地址</div>
                                        </div>
                                    </a>
                                </div>
                                  <c:forEach items="${pagers.datas}" var="data" varStatus="l">
                                    <div class="col col-4">
                                       <c:if test="${data.isUse == 0 }">
                                          <div class="item">
                                       </c:if>
                                       <c:if test="${data.isUse != 0 }">
                                            <div class="active item">
                                       </c:if>
                                       <div class="action">
                                            <input type="hidden" id="id" value="${data.id}">
                                            <input type="hidden" id="bm" value="${data.bm}">
                                            <div class="fl"><a class="edit" href="javascript:;">修改</a>
                                            <a class="del" href="${ctx}/address/delete1?id=${data.id}">删除</a></div>
                                            <div class="fr"><a class="setdft" href="${ctx}/address/mr?id=${data.id}">设为默认</a></div>

                                        </div>
                                        <div class="info">
                                            <div class="info-item name ellipsis aname">${data.name }</div>
                                            <div class="info-item address aarea">${data.area }</div>
                                            <div class="info-item tel ellipsis aphone">${data.phone}</div>
                                        </div>
                                    </div>
                                </div>
                                  </c:forEach>
                            </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
 <!--脚部-->
    <div id="addressform" style="display:none" class="layer-address">
        <form action="${ctx}/address/exAdd1" method="post" id="save">
            <div class="control-group">
                <div class="hd">
                    收货人姓名：
                </div>
                <div class="bd">
                    <input class="ui-txtin ui-txtin-thin" style="width: 200px;" type="text" name="name" id="">
                </div>
            </div>
            <div class="control-group">
                <div class="hd">
                    联系电话：
                </div>
                <div class="bd">
                    <input class="ui-txtin ui-txtin-thin" style="width: 200px;" type="text" name="phone" id="">
                </div>
            </div>
            <div class="control-group">
                <div class="hd vat">
                    所在地区：
                </div>
                <div class="bd">
                    <div class="mt10">
                        <input class="ui-txtin ui-txtin-thin" style="width: 560px;" type="text" name="area" id="" placeholder="请填写具体地址，不需要重复填写省/市/区">
                    </div>
                </div>
            </div>
            <div class="control-group">
                <div class="hd vat">
                    邮政编码：
                </div>
                <div class="bd">
                    <input class="ui-txtin ui-txtin-thin" style="width: 200px;" type="text" name="bm" id="">
                </div>
            </div>
            <div class="control-bottom clearfix">
                <a href="javascript:;" class="fl btn ui-btn-theme bc1">保存</a><a href="" class="fr btn ui-btn cancle">取消</a>
            </div>
        </form>
    </div>
      <div id="addressform2" style="display:none" class="layer-address">
     
     
        <form action="${ctx}/address/exUpdate2" method="post" id="update">
        
         <input type="hidden" id="aid" value="" name="id"> 
            <div class="control-group">
                <div class="hd">
                    收货人姓名：
                </div>
                <div class="bd">
                    <input class="ui-txtin ui-txtin-thin" style="width: 200px;" type="text" name="name" id="aname">
                </div>
            </div>
            <div class="control-group">
                <div class="hd">
                    联系电话：
                </div>
                <div class="bd">
                    <input class="ui-txtin ui-txtin-thin" style="width: 200px;" type="text" name="phone" id="aphone">
                </div>
            </div>
            <div class="control-group">
                <div class="hd vat">
                    所在地区：
                </div>
                <div class="bd">
                    <div class="mt10">
                        <input class="ui-txtin ui-txtin-thin" style="width: 560px;" type="text" name="area" id="aarea" placeholder="请填写具体地址，不需要重复填写省/市/区">
                    </div>
                </div>
            </div>
            <div class="control-group">
                <div class="hd vat">
                    邮政编码：
                </div>
                <div class="bd">
                    <input class="ui-txtin ui-txtin-thin" style="width: 200px;" type="text" name="" id="abm">
                </div>
            </div>
            <div class="control-bottom clearfix">
                <a href="javascript:;" class="fl btn ui-btn-theme bc2">保存</a><a href="" class="fr btn ui-btn cancle">取消</a>
            </div>
        </form>
    </div>
    <!--脚部-->
    <div class="fatfooter">

    </div>
    <!--脚部-->
</body>
<script src="${ctx}/resource/js/jquery.js"></script>
<link rel="stylesheet" href="${ctx}/resource/js/icheck/style.css"/>
<script src="${ctx}/resource/js/icheck/icheck.min.js"></script>
<script src="${ctx}/resource/js/layer/layer.js"></script>
<script src="${ctx}/resource/js/global.js"></script>
<script>
$(function(){
	//新增
	$('.address-list .add-new').on('click',function () {
	    layer.open({
	        type: 1,
	        skin: 'layui-layer-seaing',
	        title: '添加地址',
	        area: ['720px', 'auto'],
	        content: $('#addressform')
	        //btn: ['按钮一', '按钮二']
	    });
	});
	
	$(".bc1").click(function(){
		$("#save").submit();
	});
	$(".bc2").click(function(){
		$("#update").submit();
	});
	$('.address-list .edit').on('click',function () {
		/* 	<div class="ellipsis aname"><img src="${ctx}/resource/img/ico/user.jpg" alt="" />${data.name}</div>
		    <div class="ellipsis aarea"><img src="${ctx}/resource/img/ico/dizhi.jpg" alt="" />${data.area }</div>
		    <div class="ellipsis aphone"><img src="${ctx}/resource/img/ico/tel.jpg" alt="" />${data.phone }</div> */
			var id = $(this).parent().parent().parent().find("#id").val();
		    var aname = $(this).parent().parent().parent().find(".aname").text();
		    var aarea = $(this).parent().parent().parent().find(".aarea").text();
		    var aphone = $(this).parent().parent().parent().find(".aphone").text();
		    var abm = $(this).parent().parent().parent().find("#bm").val();
		    /* alert($(this).parent().parent().parent().html());
		    alert(id+"---"+aname+"---"+aarea+"==="+aphone); */
		   $("#aid").val(id);
		   $("#aname").val(aname);
		   $("#aarea").val(aarea);
		   $("#aphone").val(aphone);
		   $("#abm").val(abm);
			
		    layer.open({
		        type: 1,
		        skin: 'layui-layer-seaing',
		        title: '修改地址',
		        area: ['720px', 'auto'],
		        content: $('#addressform2')
		        //btn: ['按钮一', '按钮二']
		    });
		});

	
});
</script>
</html> --%>