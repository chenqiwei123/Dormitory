/**
 * Created by Vincent on 14-8-1.
 */

/**
 * 登录sysnax
 */
function checkLogInfo() {
	var name = document.getElementById("inputUser").value;
	var pass = document.getElementById("inputPassword").value;
	var code = document.getElementById("inputCode").value;
	if (name == "" || pass == "" || code == "") {
		layer.alert('您的信息还未填完,请填完整', {icon: 5});
		return false;
	}else if((name.length<4)||(name.length>16)){
		layer.alert('你的用户名不正确！请核对', {icon: 5});
		return false;
	}else{
		return true;
	}
}
/**
 *login  
 */
var doWeixinLoginInterval=null;
var doWeixinLoginIntervalNum=0;
function QRCodeshowclick(){
	if($("#QRCode").css("display") == "none"){
		$("#QRCode").css("display","block");
		$("#wx").css("display","none");
		$("#pc").css("display","block");		
		$("#login-action").css("display","none");
		doWeixinLoginIntervalNum=0;
		doWeixinLoginInterval=setInterval("doWeixinLogin()",4000);
	}
	else{
		$("#QRCode").css("display","none");
		$("#wx").css("display","block");
		$("#pc").css("display","none");
		$("#login-action").css("display","block");
		doWeixinLoginIntervalNum=0;
		if(doWeixinLoginInterval!=null)
			clearInterval(doWeixinLoginInterval);
	}
	
}

$("#QRCodeshow").click( function(){
	QRCodeshowclick();		
});

function doWeixinLogin(){
	doWeixinLoginIntervalNum=doWeixinLoginIntervalNum+1;
	if(doWeixinLoginIntervalNum>15) QRCodeshowclick();	
	else 
	$.ajax({
		type: "post",
		url: "stuMag/Login_weixinLogin.action",
		success: function(data) {
			if (data == "success") {
				if(doWeixinLoginInterval!=null)
					clearInterval(doWeixinLoginInterval);
				window.location.href = "index.action";
			}
		},
		error: function() {
			//layer.alert("未知错误!请稍后重试", {icon: 5});
		},
		complete: function() {			
		}
	});
}


function doLogin(){
	if (checkLogInfo()) {
		var index = layer.load(1,{shade: 0.4});
		if($("#inputPassword").val().length < 32)
			$("#inputPassword").val($.md5($("#inputPassword").val()));
		$.ajax({
			type: "post",
			url: "stuMag/Login_login.action",
			data: $("#login-action").serialize(), //参数信息，采用ＪＳ对象的形式，也可以使用URL地址比较传统的&将参数分隔
			success: function(data) {
				if (data == "success") {
					window.location.href = "index.action";
				} else {
					layer.alert(data, {icon: 5},function(index){
						layer.close(index);
						$("#inputCode").empty();
						$("#logCode").attr("src", "servlet/code.servlet?rm=" + Math.random());
					});
				}
			},
			error: function() {
				layer.alert("未知错误!请稍后重试", {icon: 5});
			},
			complete: function() {
				layer.close(index);
			}
		});
	}else{
		
	}
}
$("#log").click(function(){
    doLogin();
});
$(document).keydown(function (event) {
    if (event.keyCode == 13) {
    	if(($(".layui-layer").length>0)&&(($(".layui-layer-shade").css("display")!='none') || ($(".layui-layer").css("display")!='none'))){
    		layer.closeAll();
    	}else{
    		doLogin();
    	}
    }
});

/**
 *注册
 */
$(function() {
	//注册弹出层
	$('#reg').click(function() {
		$("body").eq(0).css("overflow", "hidden");
		var height = $(document).height();
		var width = $(document).width();
		$(document.body).append('<div id="bg"></div>');
		$('#bg').css({
			position: "absolute",
			"z-index": 0,
			left: 0,
			top: 0,
			zoom: 1,
			"height": height,
			"width": width,
			"filters": "alpha(opacity=50)",
			"background": "#212121",
			"opacity": 0
		});
		$('#bg').fadeTo(200, 0.7);
		$('.modal-dialog').fadeIn(400);
		/**
		 * 点击欢迎注册，学生注册界面还原
		 */
		$("#reset").trigger("click");

		/**
		 * 点击欢迎注册，老师注册界面还原
		 */
		$("#tReset").trigger("click");
		var stuDisp = $("#student").css("display");
		if(stuDisp=="none"){
			$("#tRegCode").attr("src","servlet/code.servlet?rm="+Math.random());
		}else{
			$("#regCode").attr("src","servlet/code.servlet?rm="+Math.random());
		}
	});
	//关闭
	$('#btnClose').click(function() {
		$("body").eq(0).css("overflow", "auto");
		$('#bg').fadeTo(0, 0, function() {
			$(this).css({
				"z-index": -1
			});
			$('#bg').remove(0);
		});
		$('.modal-dialog').fadeOut(0);
	});

	//tab
	$('#student-tab').click(function() {
		$('#teacher').fadeOut(0);
		$('.sub-tab-s').addClass('active');
		$('.sub-tab-t').removeClass('active');
		$('#student').fadeIn(400);
	});

	$('#teacher-tab').click(function() {
		$('#student').fadeOut(0);
		$('.sub-tab-t').addClass('active');
		$('.sub-tab-s').removeClass('active');
		$('#teacher').fadeIn(400);
	});
});

/**
 * 弹出层居中显示
 */
$(window).resize(function() {
	$('.modal-dialog').css({
		position: 'absolute',
		left: ($(window).width() - $('.modal-dialog').outerWidth()) / 2,
		top: ($(window).height() - $('.modal-dialog').outerHeight()) / 2 + $(document).scrollTop()
	});
});
//初始化函数
$(window).resize();