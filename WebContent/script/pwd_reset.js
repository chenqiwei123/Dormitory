/**
 * Created by Vincent on 14-9-12.
 */

$('#f_next').click(function(){
	  var username=document.getElementById('username');
      var mail=document.getElementById('mail');
      if(username.value.length<4 || username.value.length>16){
          alert('请填写正确的用户名！');
          username.value='';
          username.focus();
      }else if( !/^([a-zA-Z0-9_\.\-]+)@([a-zA-Z0-9_\.\-]+)\.([a-zA-Z]{2,4})$/.test(mail.value) ){
          alert('邮箱格式不正确！');
          mail.value='';
          mail.focus();
      }else{
    	  $.ajax({
    			url : "../stuMag/Reset_resetPassword.action",
    			type : "POST",
    			data : {
    				"UserName" : $("#form_count input[name=UserName]").val(),
    				"email" : $("#form_count input[name=email]").val(),
    			},
    			success : function(data) {
    				if (data == 1) {
    					alert('验证码已发送至你的邮箱，请立即查收并输入验证码进行重置 ！');
    					$('#form_count').css('display', 'none');
    					$('#f_1').removeClass('active');
    					$('#f_2').addClass('active');
    					$('#form_check').css('display', 'block');
    				} else {
    					alert('用户名和邮箱信息不匹配');
    				}

    			},
    			error : function() {
    			}
    		});
      }
});
$('#f_check').click(function() {
	var inputCode = document.getElementById('inputCode');
    if(inputCode.value.length!=4){
        alert('验证码格式不对');
        inputCode.value='';
        inputCode.focus();
    }else{
    	$.ajax({
    		url : "../stuMag/Reset_checkToken.action",
    		type : "POST",
    		data : {
    			"key" : $("#form_check input[name=key]").val(),
    		},
    		success : function(data) {
    			if (data == 1) {
    				$('#form_count').css('display', 'none');
    				$('#form_check').css('display', 'none');
    				$('#f_1').removeClass('active');
    				$('#f_2').removeClass('active');
    				$('#f_3').addClass('active');
    				$('#pwd_reset').css('display', 'block');
    			} else {
    				alert('验证码不匹配');
    			}

    		},
    		error : function() {
    		}
    	});
    }
});
$('#f_reset').click(function() {
	if ($("#pwd").val() != $("#repwd").val()) {
		alert("密码不一致，请核对！");
	} else {
		$.ajax({
			url : "../stuMag/Reset_resetPasswordtwo.action",
			type : "POST",
			data : {
				"password" : $("#pwd").val()
			},
			success : function(data) {
				if (data == 1) {
					alert('密码重置成功！');
					location = "login.jsp";
				} else {
					alert('密码重置异常！');
					location = "login.jsp";
				}
			},
			error : function() {
				alet('未知错误！');
			}
		});
	}
});