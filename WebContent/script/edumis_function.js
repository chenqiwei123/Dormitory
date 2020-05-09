/**
 * Created by Vincent on 14-8-7.
 */
/**
 * 
 * 获得URL参数
 */
function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}
//iframe获取url指定参数
function GetParentQueryString(name){
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = parent.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}
/**
 * 关闭ajax产生的缓存
 */
$.ajaxSetup({
	cache : false
// 关闭AJAX相应的缓存
});
/**
 * 弹出警告框
 * 
 * @param mes
 * @constructor
 */

function Edialog(mes) {
	$("body").eq(0).css("overflow", "hidden");
	var height = $(document).height();
	var width = $(document).width();
	$(document.body).append('<div id="bg"></div>');
	$('#bg').css({
		position : "absolute",
		"z-index" : 9998,
		left : 0,
		top : 0,
		zoom : 1,
		"height" : height,
		"width" : width,
		"filters" : "alpha(opacity=50)",
		"background" : "#212121",
		"opacity" : 0
	});
	$(document.body).append(
			'<div  style="width: 300px; height: 170px; " id="mydialog"><p id="dia_p">'
					+ mes + '</p><div id="C_OK">确定</div></div>');
	$('#mydialog').css({
		background : '#FFFFFF',
		position : 'absolute',
		"z-index" : 9999,
		left : ($(window).width() - $('#mydialog').outerWidth()) / 2,
		top : $(document).scrollTop() + ($(window).height()-250) * 0.5
	});
	$('#dia_p').css({
		textAlign : 'center',
		margin : '45px 15px',
		height : '25px',
		lineHeight : '25px'
	});
	$('#C_OK').css({
		position : 'absolute',
		bottom : '0px',
		height : '40px',
		lineHeight : '40px',
		textAlign : 'center',
		width : '300px',
		background : '#DADADA',
		cursor : 'pointer',
		letterSpacing : '5px',
		fontSize : '16px'
	});
	$('#bg').fadeTo(200, 0.4);
	$('#mydialog').fadeTo(200);

	$('#C_OK').mouseover(function() {
		$(this).css({
			background : '#37CDB1',
			color : '#FFFFFF'
		});
	}).mouseout(function() {
		$(this).css({
			background : '#DADADA',
			color : '#000000'
		});
	}).click(function() {
		$("body").eq(0).css("overflow", "auto");
		$('#bg').fadeTo(0, 0, function() {
			$(this).css({
				"z-index" : -1
			});
			$('#mydialog').remove(0);
			$('#bg').remove(0);
		});
	});
}

/**
 * 获取时间函数
 */
function timmer() {
	today = new Date();
	function initArray() {
		this.length = initArray.arguments.length;
		for (var i = 0; i < this.length; i++)
			this[i + 1] = initArray.arguments[i];
	}
	var d = new initArray("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");
	document.write("<font color=#FFFFFF style='font-size: 14px;'> ", today
			.getFullYear(), "年", today.getMonth() + 1, "月", today.getDate(),
			"日 ", d[today.getDay() + 1], "</font>");
}

/**
 * 个人中心
 */
$(document).ready(
		function() {
			$(".info").bind(
					{
						'mouseover' : function() {
							$(".set-info-ul").stop().animate({
								top : '30px'
							}, 100).show();
							$(this).find('.arrow-index').removeClass(
									'arrow-down').addClass('arrow-top');
						},
						'mouseout' : function() {
							$(".set-info-ul").stop().animate({
								top : '27px'
							}, 100).hide();
							$(this).find('.arrow-index').removeClass(
									'arrow-top').addClass('arrow-down');
						}
					});
		});
/**
 * adjust the rightside data-center's height
 */
function reSize() {
	var sidebarH = $("#sidebar").height();
	var contentH = $("#data-center").height();
	if (contentH < sidebarH) {
		contentH = sidebarH - 100;
	} else {
		contentH = "auto";
	}
	
	$("#data-center").css("min-height", contentH);
}
$(function() {
	reSize();
});
/**
 * loading
 */
$(function() {
	$('body')
			.prepend(
					"<div class=\"loading\"></div><div class=\"circle-loading\"></div>");
	// loading
	$('.loading').animate({
		'width' : '95%'
	}, 9000);
	$(window).load(function() {
		$('.circle-loading').fadeOut(300);
		$('.loading').stop().animate({
			'width' : '100%'
		}, 300, function() {
			$(this).fadeOut(300);
		});
	});
});

/**
 * 获取浏览器版本
 */
function getBrowser() {
	try {
		var browser = navigator.appName;
		var b_version = navigator.appVersion;
		var version = b_version.split(";");
		var trim_Version = version[1].replace(/[ ]/g, "");
		if (browser == "Microsoft Internet Explorer"
				&& trim_Version == "MSIE6.0") {
			return 6;
		} else if (browser == "Microsoft Internet Explorer"
				&& trim_Version == "MSIE7.0") {
			return 7;
		} else if (browser == "Microsoft Internet Explorer"
				&& trim_Version == "MSIE8.0") {
			return 8;
		} else if (browser == "Microsoft Internet Explorer"
				&& trim_Version == "MSIE9.0") {
			return 9;
		} else {
			return 10;
		}

	} catch (e) {
		// TODO handle the exception
	}
}
/**
 * fix ie7 empty td auto add &nbsp;
 */
$(function() {
	var broswerVersion = getBrowser();
	if (broswerVersion < 8) {
		$('table td').each(function() {
			var textLength = $(this).html().length;
			if (textLength <= 0) {
				$(this).append("&nbsp;");
			}
		});
	}
});

$(function() {
	var broswerVersion = getBrowser();
	var messsgeTip = "<div class=\"top-tip\">"
			+ "<div class=\"tip-content\">"
			+ "<p class=\"tip-ico\">提示：您的浏览器IE内核版本过低，部分显示效果不佳，请切换成极速模式（360浏览器）或更新你的浏览器！</p>"
			+ "<span class=\"how-change\" href=\"javascript:;\">如何设置极速模式？</span> "
			+ "<a class=\"b1\" href=\"http://se.360.cn\" target=\"_blank\">360&nbsp;浏览器</a> "
			+ "<a class=\"b2\" href=\"http://www.firefox.com.cn\" target=\"_blank\">火狐&nbsp;浏览器</a>"
			+ "<em class=\"tip-close\"></em>" + "</div></div>";
	if (broswerVersion < 9) {
		$('body').prepend(messsgeTip);
		$(".top-tip").slideDown(300);
	}
	$(".tip-close").click(function() {
		$(".top-tip").slideUp(300);
	});

	$(".how-change")
			.click(
					function() {
						/* 弹出层 */
						var contentHelp = "<img width=\"980px\" style=\"margin:0 auto;border:none;display:block\" src=\"images/360broswer.jpg\"/>";
						layer.open({
							type : 1,
							shade : [ 0.3, "#000" ],
							area : [ '1000px' ],
							title : "如何将360浏览器切换成极速模式？", // 不显示标题"如何将360浏览器切换成极速模式？"
							maxmin : true,
							content : contentHelp,
							cancel : function(index) {
								layer.close(index);
							}
						});
					});
});

/**
 * 获取下一天 上一天
 * 
 * @param {Object}
 *            _val format "YY-MM-DD"
 * @param {Object}
 *            _day 要加的天数
 */
function addDay(_val, _day) {
	Date.prototype.Format = function(fmt) {
		var o = {
			"M+" : this.getMonth() + 1, // 月份
			"d+" : this.getDate(), // 日
			"h+" : this.getHours(), // 小时
			"m+" : this.getMinutes(), // 分
			"s+" : this.getSeconds(), // 秒
			"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
			"S" : this.getMilliseconds()
		// 毫秒
		};
		if (/(y+)/.test(fmt))
			fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
					.substr(4 - RegExp.$1.length));
		for ( var k in o)
			if (new RegExp("(" + k + ")").test(fmt))
				fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
						: (("00" + o[k]).substr(("" + o[k]).length)));
		return fmt;
	};
	Date.prototype.addDays = function(d) {
		this.setDate(this.getDate() + d);
	};
	Date.prototype.addWeeks = function(w) {
		this.addDays(w * 7);
	};
	Date.prototype.addMonths = function(m) {
		var d = this.getDate();
		this.setMonth(this.getMonth() + m);
		if (this.getDate() < d)
			this.setDate(0);
	};
	Date.prototype.addYears = function(y) {
		var m = this.getMonth();
		this.setFullYear(this.getFullYear() + y);
		if (m < this.getMonth()) {
			this.setDate(0);
		}
	};
	var now = new Date(_val);
	now.addDays(_day); // 加减日期操作
	return now.Format("yyyy-MM-dd");
}
/**
 * 内添加斑马线形式的条纹
 */
$(function() {
	$(".table-striped").each(function() {
		$(this).find("tr:even").css("background", "#F9F9F9");
	});
});
/**
 * back to top
 */
$(function() {
	var _goToTop = "<ul class=\"left-side\">"
			+ "<li class=\"side-weixin\" title=\"微信\">"
			+ "<img src=\"images/qr_img.png\">" + "</li>"
			+ "<li class=\"side-weibo\" title=\"新浪微博\"></li>"
			+ "<li class=\"side-top\" title=\"返回页面顶部\"></li>" + "</ul>";
	$("#footer").after(_goToTop);
	$(window).scroll(function() {
		if ($(window).scrollTop() > ($("#sidebar").height() - 200)) {
			$(".left-side").fadeIn(300);
		} else {
			$(".left-side").fadeOut(300);
		}
	});

	$(".side-weixin").hover(function() {
		$(this).addClass("side-weixin-h").find("img").show();
	}, function() {
		$(this).removeClass("side-weixin-h").find("img").hide();
	});
	$(".side-weibo").hover(function() {
		$(this).addClass("side-weibo-h");
	}, function() {
		$(this).removeClass("side-weibo-h");
	}).click(function() {
		window.open("http://weibo.com/p/1005051156351525");
	});
	$(".side-top").hover(function() {
		$(this).addClass("side-top-h");
	}, function() {
		$(this).removeClass("side-top-h");
	}).click(function() {
		goTop();
	});
	// 回到顶部
	function goTop() {
		$('html,body').animate({
			'scrollTop' : 0
		}, 400);
	}

});

/**
 * 鼠标经过表格变色
*/
function rowChangeColor(){
	$("tr").each(function(){
		$(this).mouseover(function(){
			$(this).css("background","#eee");
		}).mouseout(function(){
			$(this).css("background","#fff");
		});
	});
	
}

/*首页设置
 *
 */
