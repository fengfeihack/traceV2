<%--
  Created by IntelliJ IDEA.
  User: dotwintech
  Date: 2018/8/24
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的关注</title>
    <script src="js/jquery-1.9.1.js"></script>
    <script src="js/jquery-ui.js"></script>
    <link rel="stylesheet" href="css/careInfo.css"/>
</head>
<body>
<div class="content">
    <div class="center">
        <div class="header">
            <%@include file="header.jsp" %>
        </div>
        <div class="careInfo">
            <div class="pageHeader">
                <span>
                    <br>
                    我的关注
                    <hr>
                </span>
            </div>
            <div class="userMap">
                <table id="mapTable"></table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<script type="text/javascript">
    $.getJSON("api/careInfo/getCareUserIds",null,function (data) {
        var result = data.result;
        if(result==0){
            alert("数据加载失败");
        }else{
            var dataList = data.list;
            var row = 0;
            var col = 3;
            if(dataList.length%col==0){
                row = dataList.length/col;
            }else{
                row = Math.ceil(dataList.length/col);
            }
            var table = $("#mapTable");
            for(var i=0;i<row;i++){
                table.append("<tr id='row"+i+"'></tr>");
                if(i==row-1){
                    col = dataList.length-i*col;
                }
                var tr = $("#row"+i);
                for(var j=0;j<col;j++){
                    var userId = dataList[4*i+j];
                    console.log(userId);
                    tr.append("<td id='beCaredUserMap'><iframe src='careMapMini.jsp?userId="+userId+"'></iframe></td>");
                }
            }
        }
    })
</script>

