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
    	<!--列表导航(downSlide就显示,否则就不显示)-->
        <!--普通导航-->
        <div class="left_yh font16" id="pageNav">
        	<a href="${ctx}/login/uIndex">首页</a>
        </div>
    </div>
</div>
<!--当前位置-->
<div class="width1200 center_yh hidden_yh font14" style="height:40px;line-height:40px;">
	<span>当前位置:</span><a href="#" class="c_66">首页></a><a href="#" class="c_66">个人中心></a><a href="#" class="c_66">购物车</a>
</div>
<!--全部商品-->
<div class="width1200 hidden_yh center_yh font20">全部菜品<font class="red">(${fn:length(list)})</font></div>
<!--商品详情-->
<div class="width1198 hidden_yh center_yh" style="border:1px solid #ddd;margin-top:18px;min-height:300px;">
	<div class="width100 hidden_yh font14" style="height:32px;line-height:32px;background:#f0f0f0;text-indent:21px;color:#000;font-weight:600;border-bottom:1px solid #ddd;">菜品详情</div>
    <!--列表头部-->
    <div class="width100 hidden_yh font14" style="height:42px;line-height:42px;border-bottom:1px solid #ddd;">
    	<div class="left_yh tcenter font14" style="width:486px;">菜品</div>
        <div class="left_yh tcenter font14" style="width:175.75px;">价格</div>
        <div class="left_yh tcenter font14" style="width:175.75px;">数量</div>
        <div class="left_yh tcenter font14" style="width:175.75px;">小计</div>
        <div class="left_yh tcenter font14" style="width:175.75px;">操作</div>
    </div>
    <!--具体的商品-->
    
    
    <!-- 单个商品 -->
      <c:forEach items="${list}" var="data" varStatus="l">
      
		    <div class="speCific" data-id="${data.id}">
		    	<div class="xzWxz">
		        	<b><img src="${ctx}/resource/user/images/xzwxz.png"></b>
		        </div>
		        <div class="xzSp">
		        	<img src="${ctx}/resource${data.item.url1}">
		            <div class="xzSpIn">
		            	<h3 class="font16 c_33 font100">${data.item.name}</h3>
		            </div>
		        </div>
		        <div class="xzJg">￥<font>${data.price}</font></div>
		        <div class="xzSl">
		        	<div class="xzSlIn">
		            	<b class="Amin">减</b>
		                <input type="text" value="${data.num}" readonly class="cOnt">
		                <b style="border-right:none;border-left:1px solid #ddd;" class="Aadd" onClick="zj(this)">加</b>
		            </div>
		        </div>
		        <div class="xzXj">￥<font></font></div>
		        <div class="xzCz">
		        	<div class="xzCzIn">
		                <a href="javascript:void(0)" class="Dels" data-id="${data.id}">删除</a>
		            </div>
		        </div>
		    </div>
    
      </c:forEach>
    <!-- 单个商品 -->
</div>
<!--合计-->
<div class="width1198 center_yh" style="height:60px;background:#f0f0f0;border:1px solid #ddd;margin-top:40px;">
	<div class="center_yh hidden_yh" style="width:1178px;height:60px;margin-left:20px;">
    	<div class="ifAll"><b><img src="${ctx}/resource/user/images/xzwxz2.png"></b><font>全选</font></div>
        <div class="ifDel">删除</div>
        <div class="sXd">
        	<div class="sXd1">已选菜品(<font style="color:#cb1c20">0</font>)件</div>
            <div class="sXd2">合计(不含运费):￥<font style="color:#cb1c20" id="zjJg">0</font></div>
            <a href="javascript:void(0)" class="ifJs" onclick="ifJs()">结算</a>
        </div>
    </div>
</div>
<script>

(function cx(){
	var e=$(".speCific").length
for(var a=0;a<e;a++){
	var lt=$(".xzJg").eq(a).find("font").html()
	$(".xzXj").eq(a).find("font").html(lt)
	}
})();
var es=$(".speCific").length;


$(".xzWxz").click(function(){
	if($(this).hasClass("on")){
		
		$(this).removeClass("on")
		var ty=$(".xzWxz.on").length;
		$(".sXd1").find("font").html(ty);
		
		if(ty!=es){
		    $(".ifAll").removeClass("on")
		}
		
		}else{
			$(this).addClass("on");
			
			var hj=$(this).siblings(".xzXj").find("font").html()*1;
			var ty=$(".xzWxz.on").length;
			$(".sXd1").find("font").html(ty);
			$("#zjJg").html()
			if(ty!=es){
			$(".ifAll").removeClass("on")
			}
			}
		
		jszj();
	})



$(".jRscj").click(function(){
	if($(this).hasClass("on")){
		$(this).removeClass("on").html("移入收藏夹")
		}else{
			$(this).addClass("on").html("已收藏")
			}
	})	
	
	
	
	
$(".Dels").click(function(){
	
	var id = $(this).attr("data-id");
	 $.ajax({  
         type : "POST",  //提交方式  
         url : "${ctx}/car/delete?id="+id,//路径  
         contentType:"application/json",  
         success : function(result) {//返回数据根据结果进行相应的处理  
         }  
     }); 
	$(this).parent().parent().parent().remove();
	jszj();
	})	
	
	
	
$(".Aadd").click(function(){
	var t=$(this).siblings(".cOnt").val()*1;
	var price=$(this).parent().parent().siblings(".xzJg").find("font").html()*1;
	t++;
	$(this).siblings(".cOnt").val(t);
	$(this).parent().parent().siblings(".xzXj").find("font").html(sswr(price*t));
	jszj();
	})



$(".Amin").click(function(){
	var t=$(this).siblings(".cOnt").val()*1;
	var price=$(this).parent().parent().siblings(".xzJg").find("font").html()*1;
	t--
	if(t<1){
		t=1
		}
	$(this).siblings(".cOnt").val(t);
	$(this).parent().parent().siblings(".xzXj").find("font").html(sswr(price*t));
	jszj();
	})



function sswr(num){
	return(
		num.toFixed(2)
		)
}



$(".ifDel").click(function(){
	
	$(".xzWxz.on").each(function(){
	    var id = $(this).parent().attr("data-id");
	    $.ajax({  
	         type : "POST",  //提交方式  
	         url : "${ctx}/car/delete?id="+id,//路径  
	         contentType:"application/json",  
	         success : function(result) {//返回数据根据结果进行相应的处理  
	         }  
	     }); 
	    });
	
	$(".xzWxz.on").parent().remove();
	
	jszj();
	
	})



$(".ifAll").click(function(){
	if($(".ifAll").hasClass("on")){
		$(this).removeClass("on");
		$(".xzWxz").removeClass("on");
		
		$(".sXd1").find("font").html(0);
		}else{
			$(this).addClass("on");
			$(".xzWxz").addClass("on");
			
			$(".sXd1").find("font").html(es);
			}
	
	jszj();;
	})


function zj(obj){
	var zj=$(obj).parent().parent().siblings(".xzXj").find("font").html()*1;
	console.log(zj)
	}	
	
	
	/**
	 * 计算总价
	 */
	function jszj(){
		//选中的数量

		
		var total = 0;
//		alert(e);find("font").html();
//		for(var a=0;a<e;a++){
//			var price=$(".xzJg").eq(a).find("font").html();
//			var num=$(".xzSl").eq(a).find("input").val();
//			//alert(price +"----" + num);
//			total += price*num;
//		}
		$(".xzWxz.on").each(function(){
	    var price = $(this).parent().find(".xzJg").find("font").html();
	    var num=$(this).parent().find(".xzSl").find("input").val();
	    //alert(price +"----" + num);
	    total += price*num;
	    });
	    
		//alert(total);
		$("#zjJg").html(sswr(total));
		
	}
	
	//结算
	function ifJs(){
		var arr = new Array();
		$(".xzWxz.on").each(function(){
			var $id = $(this).parent().attr("data-id");
		    var $num=$(this).parent().find(".xzSl").find("input").val();
		    var obj = new Object();
    		obj.id = $id;
    		obj.num = $num;
    		arr.push(obj);
		});
		
		if (arr.length == 0){
    		alert("购物车为空，请添加菜品");
    		return false;
    	}
		
		console.log(arr);
		   $.ajax({  
              type : "POST",  //提交方式  
              url : "${ctx}/itemOrder/exAdd",//路径  
              data:JSON.stringify(arr),
              contentType:"application/json",  
              success : function(result) {//返回数据根据结果进行相应的处理  
              	var re = JSON.parse(result)
              	if (re.res == 0){
              		alert("请登陆");
              		window.location.href = "${ctx}/login/uLogin";
              	}else if (re.res == 2){
              		alert("请编辑地址");
              	}else{
              		//跳转支付
              		window.location.href = "${ctx}/itemOrder/pay2?id="+re.id;
              	}
              }  
          }); 
	}
</script>
    
 <%@ include file="/common/ufooter.jsp" %>



</body>
</html>