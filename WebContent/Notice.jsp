<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<table width="709" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td height="470" align="center" valign="top" bgcolor="#F6F9FE">
                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                    <tr align="center"  class="t1">
                                        <td height="25" bgcolor="#D5E4F4" style="display: block;text-align: center;"><strong>公告</strong></td>
                                        <td bgcolor="#D5E4F4"><strong></strong></td>
                                        <td bgcolor="#D5E4F4" style="display: block;float: right;"><strong>截至时间</strong></td>
                                    </tr>
                                    <s:iterator id="aa" value="list">
                                        <tr align="center">
                                            <td height="25" align="center"></td>
                                            <td>${info}</td>
                                            <td>${date}</td>
                                        </tr>
                                    </s:iterator>
                                </table>
                            </td>
                        </tr>
</table>
</center>
</body>
</html>
