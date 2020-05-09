/**
 * Created by Vincent on 14-8-2.
 */
/*
 * 函数调用
 */
var flag;   /*用于判断验证码是否输入正确，正确为true,否则为false*/ 

/*点击验证码图片，相应的验证码输入框改变，提示信息改变*/
$("#regCode").click(function(){
	$("#inputCode_Z").val("").focus();
	$("#codeInp").attr("src","images/white.jpg");
	$("#sCode").find("span").text("请输入验证码");
});
$("#tRegCode").click(function(){
	$("#inputCode_Z_T").val("").focus();
	$("#tcodeInp").attr("src","images/white.jpg");
	$("#tCode").find("span").text("请输入验证码");
});

function doCheck(){
	/*
	 * 学生函数调用
	 */
	if(document.getElementById("userID").value.length>=18){
		checkStuIdenID();
	}	
	if(document.getElementById("inputCode_Z").value.length>=4)
		studCodeRegTest();
	/*
	 * 教师函数调用
	 */
	if(document.getElementById("inputCode_Z_T").value.length>=4)
		techCodeRegTest();
}
/*
 * 校验学号和工资编号  onkeyup
 */
function doCheckId(fid,id,minNum,maxNum,Type){
	if(document.getElementById("inputUserID").value.length>14 || document.getElementById("inputUserID_T").value.length>14){
		checkId(fid,id,minNum,maxNum,Type);
	}
}
/*
 * 校验再次输入密码  onkeyup
 */
function doCheckRepass(repfid,pid,inRepassId,inPassId){
	if(document.getElementById(inRepassId).value.length>5){
		checkRepass(repfid,pid,inRepassId,inPassId);
	}
}
/*
 * 校验输入的密码  onkeyup
 */
function doCheckPass(fid,inputId){
	if(document.getElementById(inputId).value.length>5){
		checkPass(fid,inputId);
	}
}
/*
 * 验证码的改变
 */
function changeCode(id){
	$(id).attr("src","servlet/code.servlet?rm="+Math.random());
}
$(function(){
	/*====================================学生页面验证码改变=====================================================*/
	$("#student-tab").click(function(){
		$("#regCode").attr("src","servlet/code.servlet?rm="+Math.random());
		$("#inputCode_Z").val("");
		$("#codeInp").attr("src","images/white.jpg");
		$("#sCode").find("span").text("请输入验证码");
	});
	
	/*====================================教师页面验证码改变=====================================================*/
	$("#teacher-tab").click(function(){
		$("#tRegCode").attr("src","servlet/code.servlet?rm="+Math.random());
		$("#inputCode_Z_T").val("");
		$("#tcodeInp").attr("src","images/white.jpg");
		$("#tCode").find("span").text("请输入验证码");
	});
	
	/*====================================登录页面验证码改变=====================================================*/
	$("#btnClose").click(function(){
		$("#logCode").attr("src","servlet/code.servlet?rm="+Math.random());
	});
	$("#logReset").click(function(){
		$("#logCode").attr("src","servlet/code.servlet?rm="+Math.random());
	});
	
});

/*
 * 点击欢迎注册后重置学生注册界面和教师注册界面
 */
$(function(){
	$("#reg").click(function(){ 
		/*===============学生注册界面刷新===================*/
		sResetInfo();
		/*=============教师注册界面刷新==================*/
		tResetInfo();
	});
});


/*
 * 填写完正确的信息后,展示的信息
 */
function writeOk(id){
	$(id).find("img").attr(
			"src","images/icon_ok.png"
	);
	$(id).find("span").text("");
	$(id).find("span").html("&nbsp;");
}

/*
 * 校验学生和老师姓名
 */
function checkName(fid,inputId){
	var name=document.getElementById(inputId).value;  
	if(name==""){
		$(fid).find("img").attr("src","images/icon_warn.png");
		$(fid).find("span").text("姓名不允许为空");
		return false;
	}
	else{
		writeOk(fid);
		return true;
	}
}
/*
 * 校验学号和工资编号
 */
function checkId(fid,id,minNum,maxNum,Type){
	var sId=document.getElementById(id).value;
	if(sId==""){
		$(fid).find("img").attr("src","images/icon_warn.png");
		$(fid).find("span").text(Type+"不为空");
		return false;
	}
	else if(sId!="" && isNaN(sId)){   
		$(fid).find("img").attr("src","images/icon_warn.png");
		$(fid).find("span").text(Type+"应全为数字");
		return false;     
	}
	else if(sId!="" && !isNaN(sId) && (sId.length<minNum || sId.length>maxNum)){  
		$(fid).find("img").attr("src","images/icon_warn.png");
		$(fid).find("span").text(Type+"为"+minNum+"到"+maxNum+"位");
		return false;
	}
	else{	
		writeOk(fid);
		return true;
	}
}
/*
 * 校验学生和老师密码
 */
function checkPass(fid,inputId){
	var sPass=document.getElementById(inputId).value; 
	if(sPass==""){
		$(fid).find("img").attr("src","images/icon_warn.png");
		$(fid).find("span").text("密码不允许为空");
		return false;
	}
	else if(sPass!="" && (sPass.length<6 || sPass.length>15)){
		$(fid).find("img").attr("src","images/icon_warn.png");
		$(fid).find("span").text("密码为6到15位");
		return false;
	}
	else{
		writeOk(fid);
		return true;
	}
}

/*
 * 校验再次输入的学生和老师密码
 */
function checkRepass(repfid,pid,inRepassId,inPassId){
	var sRepass=document.getElementById(inRepassId).value;
	var sPass=document.getElementById(inPassId).value;
	if(sRepass==""){
		$(repfid).find("img").attr("src","images/icon_warn.png");
		$(repfid).find("span").text("不为空");
		return false;
	}
	else if(sRepass!="" && sRepass!=sPass){
		$(repfid).find("img").attr("src","images/icon_warn.png");
		$(repfid).find("span").text("两次密码不一致");
		return false;
	}
	else{
		if($(pid).find("img").attr("src")=="images/icon_warn.png"){
			$(repfid).find("img").attr("src","images/icon_warn.png");
		}else{
			$(repfid).find("img").attr("src","images/icon_ok.png");
		}
		$(repfid).find("span").text("");
		$(repfid).find("span").html("&nbsp;");
		return true;
	}
}

/*=======================================  学生注册校验  =======================================================================*/

/*
 * 校验身份证
 */
function checkStuIdenID(){
	var sIID=document.getElementById("userID").value;   
	var reg = /^\d{17}([0-9]|X)$/;   
	if(sIID==""){
		$("#sIdenID").find("img").attr("src","images/icon_warn.png");
		$("#sIdenID").find("span").text("身份证不能为空");
		return false;
	}
	else if(sIID!="" && !reg.test(sIID)){
		$("#sIdenID").find("img").attr("src","images/icon_warn.png");
		$("#sIdenID").find("span").text("输入不正确");
		return false;     
	}
	else{
		writeOk("#sIdenID");
		return true;
	}
}

/*
 * 校验学生验证码
 */
function studCodeRegTest(){
    $.ajax({
        url:"stuMag/reg_stuCodeRegTest.action",
        type:"POST",
        data:{"regInfo.StuID":$("#inputUserID").val(),"regInfo.StuName":$("#inputUserName").val(),
            "regInfo.StuIdenID":$("#userID").val(),"regInfo.Password":$("#Password_U").val(),
            "regInfo.Repass":$("#Password_U_A").val(),"regInfo.Code":$("#inputCode_Z").val()
        },
        success:function(data){
        	if(data=="sCoEquNo"){
        		$("#codeInp").attr("src","images/icon_warn.png");
        		$("#sCode").find("span").text("输入对的验证码");
        		flag = false;
        	}
    		if(data=="sCoEquYes"){
    			$("#codeInp").attr("src","images/icon_ok.png");
	    		$("#sCode").find("span").text("");
	    		$("#sCode").find("span").html("&nbsp;");
	    		flag = true;
    		}
        },
        error:function(){
        	flag = false;
        }
    });
    return flag;
}

/*
 * 学生注册
 */

function studentReg(IFid,IId,IMinNum,IMaxNum,IType,nFid,nInputId,pFid,pInputId,repfid,inRepassId){
    if(checkId(IFid,IId,IMinNum,IMaxNum,IType) && checkName(nFid,nInputId) && checkPass(pFid,pInputId) && checkRepass(repfid,pFid,inRepassId,pInputId) && checkStuIdenID() &&studCodeRegTest()){
        $.ajax({
            url:"stuMag/reg_reg.action",
            type:"POST",
            data:{"regInfo.StuID":$("#inputUserID").val(),"regInfo.StuName":$("#inputUserName").val(),
                "regInfo.StuIdenID":$("#userID").val(),"regInfo.Password":$("#Password_U").val(),
                "regInfo.Repass":$("#Password_U_A").val(),"regInfo.Code":$("#inputCode_Z").val()
            },
            success:function(data){
            	if(data=="regSucc"){
            		sResetInfo();
//            		$(document).ready(function(){alert("恭喜你注册成功！");});
            		alert("恭喜你注册成功！");
            		//Edialog("恭喜你注册成功！");
            		location="login.jsp";
            	}
                if(data=="noMateInECJTU"){
                	var sId=document.getElementById("inputUserID").value;
                	if(sId=="")
                		$(document).ready(function(){Edialog("还有信息未填完，不可以注册，请填完！");});
//                		alert("还有信息未填完，不可以注册，请填完！");
                	else
                		$(document).ready(function(){Edialog("对不起，你输入的信息非法，不可以注册！");});
//                		alert("对不起，您不是本校人员，不可以注册！");
                	sbackRegInfo();
                }
                if(data=="inRegInfo"){
                	$(document).ready(function(){Edialog("对不起，您已注册！");});
//                	alert("对不起，您已注册！");
                	sbackRegInfo();
                }
                if(data=="tryAgain"){
                	$(document).ready(function(){Edialog("网络原因，网访未到服务器！");});
//                	alert("网络原因，网访未到服务器！");
                	sbackRegInfo();
                }
            },
            error:function(){
            }
        });
    }else{
    	alert("对不起，还有错误注册信息未修改，请修改正确后再注册！");
    }
}
/*
 * 学生注册失败后展示的信息
 */
function sbackRegInfo(){
	$("#regCode").attr("src","servlet/code.servlet?rm="+Math.random());
	$("#sId").find("img").attr("src","images/icon_warn.png");
	$("#sId").find("span").text("");
	$("#sId").find("span").html("&nbsp;");
	$("#sName").find("img").attr("src","images/white.jpg");
	$("#sName").find("span").text("");
	$("#sIdenID").find("img").attr("src","images/white.jpg");
	$("#sIdenID").find("span").text("");
	$("#sPass").find("img").attr("src","images/white.jpg");
	$("#sPass").find("span").text("");
	$("#sRepass").find("img").attr("src","images/white.jpg");
	$("#sRepass").find("span").text("");
	$("#codeInp").attr("src","images/white.jpg");
	$("#sCode").find("span").text("");
}
/*
 * 学生重置
 */
$(function(){
//ajax 提交
	($("#reset").click(function () {
		$("#regCode").attr("src","servlet/code.servlet?rm="+Math.random());
		sResetInfo();
	}));
});


/*
 * 学生重置后页面改变信息方法
 */
function sResetInfo(){  
	$("#sId").find("img").attr("src","images/white.jpg");
	$("#sId").find("span").text("请输入学号");
	$("#sName").find("img").attr("src","images/white.jpg");
	$("#sName").find("span").text("请输入真实姓名");
	$("#sIdenID").find("img").attr("src","images/white.jpg");
	$("#sIdenID").find("span").text("请输入身份证号");
	$("#sPass").find("img").attr("src","images/white.jpg");
	$("#sPass").find("span").text("请输入密码");
	$("#sRepass").find("img").attr("src","images/white.jpg");
	$("#sRepass").find("span").text("请再次输入密码");
	$("#codeInp").attr("src","images/white.jpg");
	$("#sCode").find("span").text("请输入验证码");
}
/*=======================================  学生注册校验结束  =======================================================================*/


/*=======================================  教师注册校验  =======================================================================*/
/*
 * 校验验证码
 */
function techCodeRegTest(){ 
    $.ajax({
        url:"stuMag/reg_techCodeRegTest.action", 
        type:"POST",
        data:{"tech.code":$("#inputCode_Z_T").val() },
        success:function(data){    
        	if(data=="tCoEquNo"){
        		$("#tcodeInp").attr("src","images/icon_warn.png");
        		$("#tCode").find("span").text("输入对的验证码");
        		flag = false;
        	}
    		if(data=="tCoEquYes"){
    			$("#tcodeInp").attr("src","images/icon_ok.png");
	    		$("#tCode").find("span").text("");
	    		$("#tCode").find("span").html("&nbsp;");
	    		flag = true;
    		}
        },
        error:function(){
        	flag =  false;
        }
    });
    return flag;
}

/*
 * 教师注册
 */
function teacherReg(IFid,IId,IMinNum,IMaxNum,IType,nFid,nInputId,pFid,pInputId,repfid,inRepassId){  
    //ajax 提交
	if(checkId(IFid,IId,IMinNum,IMaxNum,IType) && checkName(nFid,nInputId) && checkPass(pFid,pInputId) && checkRepass(repfid,pFid,inRepassId,pInputId) && techCodeRegTest()){
        $.ajax({
            url:"stuMag/reg_teachReg.action",
            type:"POST",
            data:{"tech.teacherID":$("#inputUserID_T").val(),"tech.name":$("#inputUserName_T").val(),
                "tech.password":$("#Password_U_T").val(),
                "tech.repass":$("#Password_U_T_A").val(),"tech.code":$("#inputCode_Z_T").val()
            },
            success:function(data){
            	if(data=="regSucc"){
            		tResetInfo();
//            		$(document).ready(function(){Edialog("恭喜你注册成功！");});
            		alert("恭喜你注册成功！");
            		location="login.jsp";
            	}
            	if(data=="noMateInECJTU"){
                	if($("#inputUserID_T").val()=="")
                		$(document).ready(function(){Edialog("还有信息未填完，不可以注册，请填完！");});
//                		alert("还有信息未填完，不可以注册，请填完！");
                	else
                		$(document).ready(function(){Edialog("对不起，您不是本校人员，不可以注册！");});
//                		alert("对不起，您不是本校人员，不可以注册！");
                	tbackRegInfo();
                }
                if(data=="inRegInfo"){
                	$(document).ready(function(){Edialog("对不起，您已注册！");});
//                	alert("对不起，您已注册！");
                	tbackRegInfo();
                }
                if(data=="tryAgain"){
                	$(document).ready(function(){Edialog("网络原因，网访未到服务器！");});
//                	alert("网络原因，网访未到服务器！");
                	tbackRegInfo();
                }
            },
            error:function(){
            }
        });
	}else{
		alert("对不起，还有错误注册信息未修改，请修改正确后再注册！");
	}
}
/*
 * 老师注册失败后展示的信息
 */
function tbackRegInfo(){
	$("#tRegCode").attr("src","servlet/code.servlet?rm="+Math.random());
	$("#tId").find("img").attr("src","images/icon_warn.png");
	$("#tId").find("span").text("");
	$("#tId").find("span").html("&nbsp;");
	$("#tName").find("img").attr("src","images/white.jpg");
	$("#tName").find("span").text("");
	$("#tPass").find("img").attr("src","images/white.jpg");
	$("#tPass").find("span").text("");
	$("#tRepass").find("img").attr("src","images/white.jpg");
	$("#tRepass").find("span").text("");
	$("#tcodeInp").attr("src","images/white.jpg");
	$("#tCode").find("span").text("");
}

/* 
 * 教师重置 
 */
$(function(){
//ajax 提交
	$("#tReset").click(function () {
		$("#tRegCode").attr("src","servlet/code.servlet?rm="+Math.random());
		tResetInfo();
	});
});

/*
 * 教师重置后页面改变信息方法
 */
function tResetInfo(){
	$("#tId").find("img").attr("src","images/white.jpg");
	$("#tId").find("span").text("请输入学号");
	$("#tName").find("img").attr("src","images/white.jpg");
	$("#tName").find("span").text("请输入真实姓名");
	$("#tPass").find("img").attr("src","images/white.jpg");
	$("#tPass").find("span").text("请输入密码");
	$("#tRepass").find("img").attr("src","images/white.jpg");
	$("#tRepass").find("span").text("请再次输入密码");
	$("#tcodeInp").attr("src","images/white.jpg");
	$("#tCode").find("span").text("请输入验证码");
}