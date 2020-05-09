<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>校园宿舍管理系统</title>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="Style/Style.css" rel="stylesheet" type="text/css" />
      <script src="script/jquery-1.11.1.min.js">
      </script>

      <style>
          #right1{
              background-image: url("Images/bg002.jpg");
              background-repeat: no-repeat;
              background-position: center;
          }
          #right2{
              background-image: url("Images/bg001.jpg");
              background-repeat: no-repeat;
              background-position: center;
          }
          #right3{
              background-image: url("Images/bg003.jpg");
              background-repeat: no-repeat;
              background-position: center;
            background-size: 600px 400px;
          }
           *{ padding: 0; margin: 0}
          .mark{
            width: 100%;
            height: 3128px;
            background: #000;
            position: absolute;
            left: 0;
            top: 0;
            opacity: 0.3;
          }
          .pic{
            position: center;
            margin-left: 292px;
            margin-top: -620px;
            opacity: 0.9;
          }
      </style>
</head>

<body id="body">
<center>
  <table width="900" border="0" cellspacing="0" cellpadding="0">
    <tr style="background-color:blue;">
      <td height="60" bgcolor="#E6F5FF" style="color:#06F; font-size:19px; font-weight:bolder; padding-left:50px;">校园宿舍管理系统</td>
    </tr>
    <tr>
      <td height="30" background="Images/MenuBg.jpg">&nbsp;</td>
    </tr>
    <tr>
      <td height="500" align="center" valign="top"><table width="900" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="191" height="500" align="center" valign="top" background="Images/leftbg.jpg">
          <%@ include file="Left.jsp"%>
          </td>
          <%if(session.getAttribute("type").toString().equals("1")){%>
          <td width="709" align="center" valign="middle" bgcolor="#F6F9FE" id="right1"><h1 style="color: blue;font-size: 2.4em;">欢迎系统管理员登陆</h1></td>
          <%}%>
          <%if(session.getAttribute("type").toString().equals("2")){%>
          <td width="709" align="center" valign="middle" bgcolor="#F6F9FE" id="right2"><h1 style="color: red;font-size: 2.4em;">欢迎宿舍管理员登陆</h1></td>
          <%}%>
          <%if(session.getAttribute("type").toString().equals("3")){%>
          <td width="709" align="center" valign="middle" bgcolor="#F6F9FE" id="right3"><h1 style="color: yellow;font-size: 2.4em;">欢迎高校学生登陆</h1></td>
          <%}%>
        </tr>
      </table>
      </td>
    </tr>
    <tr>
      <td height="35" background="Images/bootBg.jpg">&nbsp;</td>
    </tr>
  </table>

</center>
</body>
</html>
<script>
  function auto(){
    //2、3秒后要干的事情
    var body=document.getElementsByTagName('body');
    //创建一个灰色的蒙层
    var newDiv=document.createElement("div"); //<div></div>
    newDiv.className="mark"//div的class名称为mark  <div class="mark"></div>
    //将创建好的蒙层追加至body
    body[0].appendChild(newDiv);
    //创建img元素
    var newImg=document.createElement('img');//<img>
    newImg.src="Images/notice.jpg";
    //<img src="images/pic.png">
    newImg.className='pic';
    body[0].appendChild(newImg);

    //点击图片上的"x"时，关闭黑色蒙层和图片
    newImg.onclick=function()
    {
      body[0].removeChild(newDiv);
      body[0].removeChild(newImg);
    }
  }
  setTimeout(auto,3000);
</script>
