/*使用jqzoom*/
$(function(){
	$(".jqzoom").jqueryzoom({
					xzoom: 460, //放大图的宽度(默认是 200)
					yzoom: 460, //放大图的高度(默认是 200)
					offset:0, //离原图的距离(默认是 10)
					position: "0", //放大图的定位(默认是 "right")
					preload:1   
	});
});