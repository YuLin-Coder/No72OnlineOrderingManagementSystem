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
    <link rel="stylesheet" type="text/css" href="${ctx}/resource/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resource/css/main.css"/>
     <script type="text/javascript" src="${ctx}/resource/ueditor/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="${ctx}/resource/ueditor/ueditor.all.min.js"></script>
    <link rel="stylesheet" href="${ctx}/resource/css/reset.css" />
	<link rel="stylesheet" href="${ctx}/resource/css/style.css" />
	<script src="${ctx}/resource/js/Ecalendar.jquery.min.js"></script>
	
	<script src="${ctx }/resource/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/resource/js/echarts.min.js"></script>
<script type="text/javascript" src="${ctx}/resource/js/macarons.js"></script>
</head>
<body>

 
  <div class="">

        <div class="result-wrap">
           
           <div class="if_sy_tongyongkuai_cont" id="thscfx" style="height:360px;">
 
		 
		 </div>
	  	<div class="if_sy_tongyongkuai_cont" id="thscfx2" style="height:360px;">
		 
		 
		 </div>
           	<div class="if_sy_tongyongkuai_cont" id="thscfx3" style="height:360px;">
		 
		 
		 </div>
        </div>

    </div>
</body>
<script type="text/javascript">
var myChartFjHfl = echarts.init(document.getElementById('thscfx'));
option = {
	    title: {
	        text: "菜品销售统计",
	        x: "center"
	    },
	    tooltip: {
	        trigger: "item",
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    legend: {
	        x: "left",
	        /* data: ["中药", "西药", "草药"] */
	        data: [
	               <c:forEach items="${maps}" var="spe" varStatus="b">
	                <c:if test="${b.index+1 == fn:length(maps)}">
	                "${spe.name}"
	                </c:if>
	                <c:if test="${b.index+1 != fn:length(maps)}">
	                "${spe.name}",
	                </c:if>
	               </c:forEach>
	               ]
	    },
	    label: {
	        normal: {
	            formatter: "{b} ({d}%)",
	            position: "insideTopRight"
	        }
	    },
	    labelLine: {
	        normal: {
	            smooth: .6
	        }
	    },
	    toolbox: {
	        show: !0,
	        feature: {
	            mark: {
	                show: !0
	            },
	            dataView: {
	                show: !0,
	                readOnly: !1
	            },
	            magicType: {
	                show: !0,
	                type: ["pie", "funnel"]
	            },
	            restore: {
	                show: !0
	            },
	            saveAsImage: {
	                show: !0
	            }
	        }
	    },
	    calculable: !0,
	    series: [{
	        name: "类别",
	        type: "pie",
	        roseType: "area",
	        label: {
	            normal: {
	                show: !0
	            },
	            emphasis: {
	                show: !0
	            }
	        },
	        lableLine: {
	            normal: {
	                show: !0
	            },
	            emphasis: {
	                show: !0
	            }
	        },
	       /*  data: [{
	            value: 305,
	            name: "西药"
	        }, {
	            value: 234,
	            name: "中药"
	        }, {
	            value: 145,
	            name: "草药"
	        }] */
	        data: [
	               <c:forEach items="${maps}" var="row" varStatus="b">
	               <c:if test="${b.index+1 == fn:length(maps)}">
	               { name: '${row.name}', value: ${row.value}}
	               </c:if>
	               <c:if test="${b.index+1 != fn:length(maps)}">
	               { name: '${row.name}', value: ${row.value}},
	               </c:if>
	              </c:forEach>
	               ]
	       
	    }]
	};
myChartFjHfl.setOption(option);

</script>


<script type="text/javascript">
var myChartFjHfl2 = echarts.init(document.getElementById('thscfx2'));
option2 = {
	    backgroundColor: '#424956',
	    tooltip: {
	        trigger: 'item',
	        formatter: "{a} <br/>{b}: {c} ({d}%)",

	    },
	    legend: {
	        
	        orient: 'vertical',
	        x: 'right',
	       itemWidth: 14,
	        itemHeight: 14,
	        align: 'left',
	    
	        data:['总收入','总支出'],
	                textStyle: {
	            color: '#fff'
	        }
	    },
	    series: [
	        {
	            name:'费用',
	            type:'pie',
	            hoverAnimation: false,
	            legendHoverLink:false,
	            radius: ['40%', '42%'],
	            color: ['#915872', '#3077b7'],
	
	            label: {
	                normal: {
	                    position: 'inner'
	                }
	            },
	            labelLine: {
	                normal: {
	                    show: false
	                },
	               
	            },
	            tooltip: {
	               show:false,
	               
	               
	            },
	            
	            data:[
	            	<c:forEach items="${maps}" var="row" varStatus="b">
		               <c:if test="${b.index+1 == fn:length(maps)}">
		               { name: '${row.name}', value: ${row.value}}
		               </c:if>
		               <c:if test="${b.index+1 != fn:length(maps)}">
		               { name: '${row.name}', value: ${row.value}},
		               </c:if>
		              </c:forEach>
	            ]
	        },
	        {
	            name:'费用',
	            type:'pie',
	            radius: ['42%', '55%'],
	            color: ['#d74e67', '#0092ff'],
	            label: {
	                normal: {
	                    formatter: '{b}\n{d}%'
	                },
	          
	            },
	            data:[
	            	<c:forEach items="${maps}" var="row" varStatus="b">
		               <c:if test="${b.index+1 == fn:length(maps)}">
		               { name: '${row.name}', value: ${row.value}}
		               </c:if>
		               <c:if test="${b.index+1 != fn:length(maps)}">
		               { name: '${row.name}', value: ${row.value}},
		               </c:if>
		              </c:forEach>
	            ]
	        }
	    ]
	};
myChartFjHfl2.setOption(option2);

</script>

<script type="text/javascript">
var myChartFjHf3 = echarts.init(document.getElementById('thscfx3'));


var optionFjHf3 = {
        backgroundColor: '#ffffff',
        title: {
            text: '菜品销售统计',
            top: '7%',
            textStyle: {fontSize: 12},
            left: '43%'
        },
        color: ['#3398DB'],
        tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: [
            {
                type: 'category',
                axisLabel: {
                    //横坐标上的文字换行显示 begin
                    formatter: function (val) {
                        return val.split("").join("\n");
                    }
                    //横坐标上的文字换行显示 end
                },
                data: [
							<c:forEach items="${maps}" var="spe" varStatus="b">
								<c:if test="${b.index+1 == fn:length(maps)}">
									"${spe.name}"
								</c:if>
								<c:if test="${b.index+1 != fn:length(maps)}">
									"${spe.name}",
								</c:if>
							</c:forEach>
               ],
                axisTick: {
                    alignWithLabel: true
                }
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series: [
            {
                name: '销售量',
                type: 'bar',
                barWidth: '60%',
                clickable: false,//绑定点击事件
                data: [
                       <c:forEach items="${maps}" var="row" varStatus="b">
    	               <c:if test="${b.index+1 == fn:length(maps)}">
    	               {id:'${row.id}', name: '${row.name}', value: ${row.value}}
    	               </c:if>
    	               <c:if test="${b.index+1 != fn:length(maps)}">
    	               {id:'${row.id}', name: '${row.name}', value: ${row.value}},
    	               </c:if>
    	              </c:forEach>
              ]
            }
        ]
    };


myChartFjHf3.setOption(optionFjHf3);

</script>
</html>