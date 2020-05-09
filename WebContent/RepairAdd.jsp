<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>校园宿舍管理系统</title>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="Style/Style.css" rel="stylesheet" type="text/css" />
    <script language="JavaScript">
        function mycheck(){
            if(isNull(form1.Address.value)){
                alert("请输入维修地址！");
                return false;
            }
            if(isNull(form1.Info.value)){
                alert("请输入维修信息！");
                return false;
            }
            if(isNull(form1.Tel.value)){
                alert("请输入联系电话！");
                return false;
            }
        }

        function isNull(str){
            if ( str == "" ) return true;
            var regu = "^[ ]+$";
            var re = new RegExp(regu);
            return re.test(str);
        }


    </script>
</head>
<body>
<center>
    <table width="900" border="0" cellspacing="0" cellpadding="0">
        <tr>
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
                    <td width="709" align="center" valign="top" bgcolor="#F6F9FE"><table width="709" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td height="30" background="Images/mainMenuBg.jpg" style="padding-left:25px;">上报维修信息</td>
                        </tr>
                        <tr>
                            <td height="470" align="center" valign="top" bgcolor="#F6F9FE">
                                <form name="form1" method="post" action="RepairAdd.action" onSubmit="return mycheck()" >
                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                    <tr>
                                        <td width="33%" height="30" align="right">&nbsp;</td>
                                        <td width="67%">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td height="30" align="right"><span style="color:red;">*</span>请输入报修地址：</td>
                                        <td><input name="repair_address" type="text" class="text2" id="Address">
                                            <%if(request.getAttribute("Msg")!=null){%>
                                            <span style="color:red;"><%=request.getAttribute("Msg")%></span>
                                            <%}%></td>
                                    </tr>
                                    <tr>
                                        <td height="30" align="right"><span style="color:red;">*</span>请描述维修对象：</td>
                                        <td><input name="repair_info" type="text" class="text2" id="Info"></td>
                                    </tr>
                                    <tr>
                                        <td height="30" align="right"><span style="color:red;">*</span>请输入联系电话：</td>
                                        <td><input name="repair_tel" type="text" class="text2" id="Tel"></td>
                                    </tr>
                                    <tr>
                                        <td height="30">&nbsp;</td>
                                        <td><input type="submit" name="button" id="button" value="上报信息"></td>
                                    </tr>
                                </table>
                            </form>
                            </td>
                        </tr>
                    </table></td>
                </tr>
            </table></td>
        </tr>
        <tr>
            <td height="35" background="Images/bootBg.jpg">&nbsp;</td>
        </tr>
    </table>

</center>
</body>
</html>
