<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"  %>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>主页</title>
    <link rel="stylesheet" href="css/mainStyle.css"/>
    <script src="js/jquery-1.9.1.js"></script>
    <script src="js/jquery-ui.js"></script>
</head>
<body>

<div class="content">
    <div class="center">
        <div class="header">
           <%@include file="header.jsp" %>
        </div>
        <div class="map">
            <iframe name="mapFrame" src="myMap.jsp"></iframe>
        </div>
        <div class="friends">
            <div class="message" style="background-color: #2ba0db;height: 30px;margin-bottom: 10px;"><p style="margin-left: 50%; font-size: medium"><b>好友足迹</b></p></div>
            <div class="careMap">
                <div class="friend" id="friend1" style="height: 200px;width: 18%;position: absolute;float: left"></div>
                <div class="friend" id="friend2" style="height: 200px;width: 25%;float: right"></div>
                <div class="friend" id="friend3" style="height: 200px;width: 25%;float: right"></div>
                <div class="friend" id="friend4" style="height: 200px;width: 25%;float: right"></div>
            </div>
        </div>
        <script>
            var data1;
            $.getJSON("api/careInfo/getRecentCareUser",function (data) {
                    var list = data.list;
                    data1 = data.list;
                    var a=list[0],b=list[1],c=list[2],d=list[3];
                    document.getElementById("friend1").innerHTML= '<iframe name="mapFrame" id="careMap1" src="careMapMini.jsp?userId='+a+'" frameborder="1" style="height: 200px;width: 100%"></iframe>';
                    document.getElementById("friend2").innerHTML='<iframe name="mapFrame" id="careMap2" src="careMapMini.jsp?userId='+b+'" frameborder="1" style="height: 200px;width: 100%"></iframe>';
                    document.getElementById("friend3").innerHTML='<iframe name="mapFrame" id="careMap3" src="careMapMini.jsp?userId='+c+'" frameborder="1" style="height: 200px;width: 100%"></iframe>';
                    document.getElementById("friend4").innerHTML='<iframe name="mapFrame" id="careMap4" src="careMapMini.jsp?userId='+d+'" frameborder="1" style="height: 200px;width: 100%"></iframe>';
            })
        </script>
    </div>
</div>
</body>
</html>

