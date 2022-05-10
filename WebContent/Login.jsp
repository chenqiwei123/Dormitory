<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>校园宿舍管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="Style/Style.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="styles/edumis.css">
    <link rel="stylesheet" type="text/css" href="styles/login.css">
    <link rel="stylesheet" type="text/css" href="css/sweetalert.css">
    <script type="text/javascript" src="script/sweetalert-dev.js"></script>
    <script src="script/jquery-1.11.1.min.js"></script>
    <script src="script/edumis_function.js"></script>
    <script src="script/jquery.md5.js"></script>
    <style>
        body {
            background-image: url("Images/bg11.jpg");
        }

        .btn {
            text-align: center;
            padding: 10px;
            width: 100%;
            margin: 15px 0px;
            background-image: linear-gradient(to right, #75d7c8, #238175);
            color: #fff;
        }

        #checkCode {
            margin: 10px 0px 0px 5px;
            display: inline-block;
            width: 100px;
            height: 30px;
            text-align: center;
            background-color: rgba(128, 128, 128, 0.158);
            color: blue;
            font-size: 26px;
            font-style: italic;
            letter-spacing: 2px;
            font-family: Arial, Helvetica, sans-serif;
        }

        #Mycanvas {
            text-align: center;
            overflow: hidden;
        }

        tr {
            margin-bottom: 5px;
        }

        .text1 {
            display: inline-block;
            height: 30px;
            width: 100%;
            margin-top: 10px;
        }
    </style>
</head>
<body>
<div id="body-bg"></div>
<div class="clear"></div>
<canvas id="Mycanvas"></canvas>
<center class="panel login center-block">
    <img src="Images/dorm.png" style="width: 100%;height: 30%;"/>
    <table border="0" cellspacing="0" cellpadding="0" style="width: 100%;">
        <tr>
            <td align="center" valign="top" style="background-color: #b0ded9b3;">
                <table border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td align="center" valign="top">
                            <form name="form1" action="GoLogin.action" method="post"
                                  onSubmit="return LogincheckMessage()">
                                <table border="0" cellspacing="0" cellpadding="0">
                                    <tr>
                                        <td height="30" colspan="2" align="center" class="STYLE2"><span
                                                style="color:red;">
                    <%if (request.getAttribute("Msg") != null) {%>
                    <%=request.getAttribute("Msg")%>
                    <%}%>
                  </span></td>
                                    </tr>
                                    <tr>
                                        <td height="30" align="right" class="STYLE2" style="font-size: 1.3em">身份：</td>
                                        <td align="left"><select name="Type" id="Type" class="text1">
                                            <option value="" style="font-size: 1.3em">请选择</option>
                                            <option value="admin" style="font-size: 1.3em">admin</option>
                                            <option value="floor" style="font-size: 1.3em">floor</option>
                                            <option value="student" style="font-size: 1.3em">student</option>
                                        </select></td>
                                    </tr>
                                    <div style="height: 8px"></div>
                                    <tr>
                                        <td width="37%" height="30" align="right" class="STYLE2"
                                            style="font-size: 1.3em">用户名：
                                        </td>
                                        <td width="300" align="left"><input type="text" name="Username" id="Username"
                                                                            class="text1"/></td>
                                    </tr>
                                    <div style="height: 8px"></div>
                                    <tr>
                                        <td height="30" align="right" class="STYLE2" style="font-size: 1.3em">密码：</td>
                                        <td align="left"><input type="password" name="Password" id="Password"
                                                                class="text1"/></td>
                                    </tr>
                                    <div style="height: 8px"></div>
                                    <tr>
                                        <td height="30" align="right" class="STYLE2" style="font-size: 1.3em">验证码：</td>
                                        <td align="left">
                                            <input type="text" class="text1" name="check" id="check"/>
                                        </td>
                                        <td><span id="checkCode">adf34y</span>
                                        </td>
                                    </tr>
                                    <div style="height: 8px"></div>
                                    <tr>
                                        <td></td>
                                        <td>
                                            <input type="submit" name="button" id="button" value="登录" class="btn">
                                        </td>
                                    </tr>
                                </table>
                            </form>

                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>


</center>

<script type="text/javascript" src="script/layer_edumis/layer.js"></script>
<script src="scripts/login.js"></script>
<script type="text/javascript" src="script/logReg.js"></script>
<script type="text/javascript">

    var mes = "";
    mes = "";
    if (mes.length > 0) {
        layer.alert("本系统无此用户，请联系所在学院。", {icon: 5});
    }
    var date = new Date();
    date = date.getFullYear();
    window.onload = function () {
        var year = document.getElementById("year");
        year.innerHTML = date;
    }
    //定义画布宽高和生成点的个数
    var WIDTH = window.innerWidth, HEIGHT = window.innerHeight, POINT = 35;

    var canvas = document.getElementById('Mycanvas');
    canvas.width = WIDTH,
        canvas.height = HEIGHT;
    var context = canvas.getContext('2d');
    context.strokeStyle = 'rgba(255,255,255,0.4)',
        context.strokeWidth = 1,
        context.fillStyle = 'rgba(255,255,255,0.4)';
    var circleArr = [];

    //线条：开始xy坐标，结束xy坐标，线条透明度
    function Line(x, y, _x, _y, o) {
        this.beginX = x,
            this.beginY = y,
            this.closeX = _x,
            this.closeY = _y,
            this.o = o;
    }

    //点：圆心xy坐标，半径，每帧移动xy的距离
    function Circle(x, y, r, moveX, moveY) {
        this.x = x,
            this.y = y,
            this.r = r,
            this.moveX = moveX,
            this.moveY = moveY;
    }

    //生成max和min之间的随机数
    function num(max, _min) {
        var min = arguments[1] || 0;
        return Math.floor(Math.random() * (max - min + 1) + min);
    }

    // 绘制原点
    function drawCricle(cxt, x, y, r, moveX, moveY) {
        var circle = new Circle(x, y, r, moveX, moveY)
        cxt.beginPath()
        cxt.arc(circle.x, circle.y, circle.r, 0, 2 * Math.PI)
        cxt.closePath()
        cxt.fill();
        return circle;
    }

    //绘制线条
    function drawLine(cxt, x, y, _x, _y, o) {
        var line = new Line(x, y, _x, _y, o)
        cxt.beginPath()
        cxt.strokeStyle = 'rgba(255,255,255,' + o + ')'
        cxt.lineWidth = 2;
        cxt.moveTo(line.beginX, line.beginY)
        cxt.lineTo(line.closeX, line.closeY)
        cxt.closePath()
        cxt.stroke();
    }

    //初始化生成原点
    function init() {
        circleArr = [];
        for (var i = 0; i < POINT; i++) {
            circleArr.push(drawCricle(context, num(WIDTH), num(HEIGHT), num(15, 2), num(10, -10) / 40, num(10, -10) / 40));
        }
        draw();
    }

    //每帧绘制
    function draw() {
        context.clearRect(0, 0, canvas.width, canvas.height);
        for (var i = 0; i < POINT; i++) {
            drawCricle(context, circleArr[i].x, circleArr[i].y, circleArr[i].r);
        }
        for (var i = 0; i < POINT; i++) {
            for (var j = 0; j < POINT; j += 3) {
                if (i + j < POINT) {
                    var A = Math.abs(circleArr[i + j].x - circleArr[i].x),
                        B = Math.abs(circleArr[i + j].y - circleArr[i].y);
                    var lineLength = Math.sqrt(A * A + B * B);

                    var C = 1 / lineLength * 40 - 0.03;
                    var lineOpacity = C > 0.2 ? 0.2 : C;
                    if (lineOpacity > 0) {
                        drawLine(context, circleArr[i].x, circleArr[i].y, circleArr[i + j].x, circleArr[i + j].y, lineOpacity);
                    }
                }
            }
        }
    }

    //调用执行
    window.onload = function () {
        init();
        setInterval(function () {
            for (var i = 0; i < POINT; i++) {
                var cir = circleArr[i];
                cir.x += cir.moveX;
                cir.y += cir.moveY;
                if (cir.x > WIDTH) cir.x = 0;
                else if (cir.x < 0) cir.x = WIDTH;
                if (cir.y > HEIGHT) cir.y = 0;
                else if (cir.y < 0) cir.y = HEIGHT;

            }
            draw();
        }, 16);
    }
</script>
</body>
</html>
<script src="script/Jquery/Jquery.js"></script>
<script>
    var codecheck = "?";
    $(function () {
        // 登录界面生成验证码
        (function create_code() {
            function shuffle() {
                var arr = ['1', 'r', 'Q', '4', 'S', '6', 'w', 'u', 'D', 'I', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', '2', 's', 't', '8', 'v', '7', 'x', 'y', 'z', 'A', 'B', 'C', '9', 'E', 'F', 'G', 'H', '0', 'J', 'K', 'L', 'M', 'N', 'O', 'P', '3', 'R', '5', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];
                return arr.sort(function () {
                    return (Math.random() - .5);
                });
            };
            shuffle();

            function showAuthCode() {
                var ar1 = '';
                var code = shuffle();
                for (var i = 0; i < 6; i++) {
                    ar1 += code[i];
                }
                ;
                $(".check").text(ar1);
                code1(ar1);
            };
            showAuthCode();
            $(".check").click(function () {
                showAuthCode();
            });
        })();

        function code1(code) {
            codecheck = code;
        }

        //登录界面账户输入框失去焦点
    });
</script>
<script type="text/javascript">
    var mes = "";
    mes = "";
    if (mes.length > 0) {
        layer.alert("本系统无此用户，请联系所在学院。", {icon: 5});
    }
    ;
    var date = new Date();
    date = date.getFullYear();
    window.onload = function () {
        var year = document.getElementById("year");
        year.innerHTML = date;
    }
    //定义画布宽高和生成点的个数
    var WIDTH = window.innerWidth, HEIGHT = window.innerHeight, POINT = 35;

    var canvas = document.getElementById('Mycanvas');
    canvas.width = WIDTH,
        canvas.height = HEIGHT;
    var context = canvas.getContext('2d');
    context.strokeStyle = 'rgba(255,255,255,0.4)',
        context.strokeWidth = 1,
        context.fillStyle = 'rgba(255,255,255,0.4)';
    var circleArr = [];

    //线条：开始xy坐标，结束xy坐标，线条透明度
    function Line(x, y, _x, _y, o) {
        this.beginX = x,
            this.beginY = y,
            this.closeX = _x,
            this.closeY = _y,
            this.o = o;
    }

    //点：圆心xy坐标，半径，每帧移动xy的距离
    function Circle(x, y, r, moveX, moveY) {
        this.x = x,
            this.y = y,
            this.r = r,
            this.moveX = moveX,
            this.moveY = moveY;
    }

    //生成max和min之间的随机数
    function num(max, _min) {
        var min = arguments[1] || 0;
        return Math.floor(Math.random() * (max - min + 1) + min);
    }

    // 绘制原点
    function drawCricle(cxt, x, y, r, moveX, moveY) {
        var circle = new Circle(x, y, r, moveX, moveY)
        cxt.beginPath()
        cxt.arc(circle.x, circle.y, circle.r, 0, 2 * Math.PI)
        cxt.closePath()
        cxt.fill();
        return circle;
    }

    //绘制线条
    function drawLine(cxt, x, y, _x, _y, o) {
        var line = new Line(x, y, _x, _y, o)
        cxt.beginPath()
        cxt.strokeStyle = 'rgba(255,255,255,' + o + ')'
        cxt.lineWidth = 2;
        cxt.moveTo(line.beginX, line.beginY)
        cxt.lineTo(line.closeX, line.closeY)
        cxt.closePath()
        cxt.stroke();

    }

    //初始化生成原点
    function init() {
        circleArr = [];
        for (var i = 0; i < POINT; i++) {
            circleArr.push(drawCricle(context, num(WIDTH), num(HEIGHT), num(15, 2), num(10, -10) / 40, num(10, -10) / 40));
        }
        draw();
    }

    //每帧绘制
    function draw() {
        context.clearRect(0, 0, canvas.width, canvas.height);
        for (var i = 0; i < POINT; i++) {
            drawCricle(context, circleArr[i].x, circleArr[i].y, circleArr[i].r);
        }
        for (var i = 0; i < POINT; i++) {
            for (var j = 0; j < POINT; j += 3) {
                if (i + j < POINT) {
                    var A = Math.abs(circleArr[i + j].x - circleArr[i].x),
                        B = Math.abs(circleArr[i + j].y - circleArr[i].y);
                    var lineLength = Math.sqrt(A * A + B * B);

                    var C = 1 / lineLength * 40 - 0.03;
                    var lineOpacity = C > 0.2 ? 0.2 : C;
                    if (lineOpacity > 0) {
                        drawLine(context, circleArr[i].x, circleArr[i].y, circleArr[i + j].x, circleArr[i + j].y, lineOpacity);
                    }
                }
            }
        }
    }

    //调用执行
    window.onload = function () {
        init();
        setInterval(function () {
            for (var i = 0; i < POINT; i++) {
                var cir = circleArr[i];
                cir.x += cir.moveX;
                cir.y += cir.moveY;
                if (cir.x > WIDTH) cir.x = 0;
                else if (cir.x < 0) cir.x = WIDTH;
                if (cir.y > HEIGHT) cir.y = 0;
                else if (cir.y < 0) cir.y = HEIGHT;

            }
            draw();
        }, 16);
    }
</script>
<script>
    let sum = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'a', 'b', 'c', 'd', 'e', 'f'];
    var check = document.getElementById("checkCode");
    var code;

    function fun() {
        let str = "";
        for (let i = 0; i < 6; i++) {
            let pos = Math.round(Math.random() * 15);//注意这个写法，取随机数
            str += sum[pos];
        }
        check.innerHTML = str;
        return str;
    }

    window.onload = function () {
        code = fun();
    }

    let checka = document.getElementById("checkCode");
    checka.onclick = function () {
        code = fun();
    }

    function LogincheckMessage() {
        if (isNull(form1.Type.value)) {
            swal("请选择身份！");
            return false;
        }
        if (isNull(form1.Username.value)) {
            swal("请输入用户名！");
            return false;
        }
        if (isNull(form1.Password.value)) {
            swal("请输入密码！");
            return false;
        }
        // if (isNull(form1.check.value)) {
        //     swal("输入验证码！");
        //     return false;
        // }
        let btn = document.getElementById("button");

        let t = document.getElementById("check").value;
        console.log(t)
        if (t == code) {
        } else {
            swal("验证码输入错误！");
            document.getElementById("check").value = "";
            return false;
        }
        return true;
    }

    function isNull(str) {
        if (str == "") return true;
        var regu = "^[ ]+$";
        var re = new RegExp(regu);
        return re.test(str);
    }


</script>
