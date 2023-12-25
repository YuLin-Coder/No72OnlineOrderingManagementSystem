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
   <title>详情</title>
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
	<span>当前位置:</span><a href="#" class="c_66">首页></a><a href="#" class="c_66">菜品列表></a><a href="#" class="c_66">详情</a>
</div>
<!--菜品详情-->
<div class="width1200 center_yh hidden_yh" style="height:420px;">
	<div class="left_yh" style="width:350px;height:420px;">
	
	<input type="hidden" id="id" value="${obj.id}">
	
	
	
    	<script src="${ctx}/resource/user/js/163css.js"></script>
        <script src="${ctx}/resource/user/js/lib.js"></script>
		<div id="preview">
				<div class=jqzoom id="spec-n1" onClick="window.open('/')">
                	<IMG height="350" src="${ctx}/resource${obj.url1}" jqimg="${ctx}/resource${obj.url1}" width="350">
				</div>
					<div id="spec-n5">
						<div class=control id="spec-left">
							<img src="${ctx}/resource/user/images/left.gif">
						</div>
						<div id="spec-list">
							<ul class="list-h">
								<li><img src="${ctx}/resource${obj.url1}"></li>
								<li><img src="${obj.url2}"></li>
								<li><img src="${obj.url3}"></li>
								<li><img src="${obj.url4}"></li>
								<li><img src="${obj.url5}"></li>
							</ul>
						</div>
						<div class=control id="spec-right">
							<img src="${ctx}/resource/user/images/right.gif">
						</div>
					</div>
				</div>
		<script type=text/javascript>
            $(function(){			
                   $(".jqzoom").jqueryzoom({
                        xzoom:400,
                        yzoom:400,
                        offset:10,
                        position:"right",
                        preload:1,
                        lens:1
                    });
                    $("#spec-list").jdMarquee({
                        deriction:"left",
                        width:350,
                        height:56,
                        step:2,
                        speed:4,
                        delay:10,
                        control:true,
                        _front:"#spec-right",
                        _back:"#spec-left"
                    });
                    $("#spec-list img").bind("mouseover",function(){
                        var src=$(this).attr("src");
                        $("#spec-n1 img").eq(0).attr({
                            src:src.replace("\/n5\/","\/n1\/"),
                            jqimg:src.replace("\/n5\/","\/n0\/")
                        });
                        $(this).css({
                            "border":"2px solid #ff6600",
                            "padding":"1px"
                        });
                    }).bind("mouseout",function(){
                        $(this).css({
                            "border":"",
                            "padding":"0"
                        });
                    });				
                })
        </script>
    </div>
    <div class="left_yh" style="width:486px;height:420px;margin-left:42px;">
    	<h3 class="font20 font100">${obj.name}</h3>
        <p class="font16 c_66" style="margin-top:25px;">
        	<font style="margin-right:20px;">价格:</font>
            <font class="red font20">
            	<em class="font14" style="font-style:normal">￥</em>
                ${obj.price}
            </font>
            <font class="font14 c_66"></font>
        </p>
        
        <c:if test="${obj.zk != null}">
         <p class="font16 c_66" style="margin-top:25px;">
        	<font style="margin-right:20px;">促销:</font>
            <font class="">${obj.zk}<span style="color: red">折</span></font>
        </p>
       </c:if>
            	
       
        <p class="font16 c_66 likeColor" style="margin-top:25px;">
        	<font style="margin-right:20px;">数量:</font>
        	<a href="javascript:void(0)" id="min_s">-</a>
            <input type="text" value="1" readonly id="t_a">
            <a href="javascript:void(0)" id="add_s">+</a>
            <font class="font14 c_99" style="margin-left:20px;">库存100件</font>
        	<script>
			$(".likeColor span").click(function(){
				$(this).addClass("on").siblings().removeClass("on")
				})
			var tr=$("#t_a").val();
			$("#add_s").click(function(){
					tr++;
					$("#t_a").val(tr)
				})
			$("#min_s").click(function(){
				tr--;
				if(tr<1){
					$("#t_a").val(1);
					tr=1
				}else{
					$("#t_a").val(tr);
				}
					$("#ano").html(tr);
			})	
        </script>
        </p>
        <div class="buyFor">
        	<a href="${ctx}/sc/exAdd?itemId=${obj.id}" class="mstBuy">收藏</a>
            <a href="javascript:void(0)" class="addCar">加入购物车</a>
        </div>
    </div>
</div>
<!--左边和右边-->
<div class="width1200 hidden_yh center_yh" style="margin-top:40px;">
	
    <div class="right_yh" style="width:1200px;">
    	<div class="hidden_yh" id="spXqpj">
        	<a href="javascript:void(0)" class="on">菜品详情</a>
            <a href="javascript:void(0)">菜品评价</a>
        </div>
        <div class="width100 hidden_yh">
        	<!--菜品详情-->
            <div id="spDetil">
            	${obj.ms}
            </div>
            <!--菜品评价-->
            <div id="spPj">
            	<div class="pjYxz">
                </div>
                <div class="pjBox">
                	<!--全部-->
                    <div class="pjBoxA">
                    	<!--一条-->
                        <div class="width100 hidden_yh" style="padding-bottom:20px;border-bottom:1px dashed #ddd;margin-bottom:10px;">
                             <c:forEach items="${obj.pls}" var="data" varStatus="l">
                             <div style="width:790px;float:right;overflow:hidden;margin-top: 20px">
                              <img src="${ctx}/resource/user/images/x.jpg" style="width:40px;height:40px;border-radius:50%;float:left;border:1px solid #ddd;margin-top: 20px">
                            	<h3 class="font16 c_33 font100" style="color: red;font-weight: blod;">${data.user.userName}</h3>
                                <p class="font14 c_99" style="margin-top:6px;">
                                 <fmt:formatDate value="${data.addTime}" pattern="yyyy-MM-dd"/>
                                </p>
                                <p class="font16 c_33" style="margin-top:6px;">${data.content}</p>
                            </div>
                            <div class="width100 hidden_yh" style="padding-bottom:20px;border-bottom:1px dashed #ddd;margin-bottom:10px;"></div>
                             </c:forEach>
                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
$("#spXqpj a").click(function(){
	$(this).addClass("on").siblings().removeClass("on")
	});
$("#spXqpj a").eq(0).click(function(){
	$("#spDetil").css({display:"block"}).siblings().css({display:"none"});
	})
$("#spXqpj a").eq(1).click(function(){
	$("#spPj").css({display:"block"}).siblings().css({display:"none"});
	})
$(".pjYxz a").eq(0).click(function(){
	$(".pjBoxA").css({display:"block"}).siblings().css({display:"none"});
	})
$(".pjYxz a").eq(1).click(function(){
	$(".pjBoxG").css({display:"block"}).siblings().css({display:"none"});
	})
$(".pjYxz a").eq(2).click(function(){
	$(".pjBoxL").css({display:"block"}).siblings().css({display:"none"});
	})
</script>
    
 <%@ include file="/common/ufooter.jsp" %>

<script type="text/javascript">

$(function(){
	   $(".addCar").click(function(){
		   var id = $("#id").val();
		   var num = $("#t_a").val();
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
		   /*  window.location.href = "${ctx}/car/exAdd?itemId="+id+"&num="+num; */
	   });
	   
	   //直接提交
	   $(".buy-now").click(function(){
		   var id = $("#id").val();
		   var num = $(".count-input").val();
		   var s = "${ctx}/car/js2?itemId="+id+"&num="+num;
		   $.ajax({  
            type : "POST",  //提交方式  
            url : "${ctx}/car/js2?itemId="+id+"&num="+num,//路径  
            success : function(result) {//返回数据根据结果进行相应的处理  
            	var re = JSON.parse(result)
            	if (re.res == 0){
            		alert("请登陆");
            		window.location.href = "${ctx}/login/uLogin";
            	}else{
            		window.location.href = "${ctx}/car/view";
            	}
            }  
        }); 
		   /*  window.location.href = "${ctx}/car/exAdd?itemId="+id+"&num="+num; */
	   });
});
</script>

</body>
</html>