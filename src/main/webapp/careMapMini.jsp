
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"  %>
<% String userId=request.getParameter("userId");%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no"/>
    <style type="text/css">
        body, html, #allmap {
            width: 100%;
            height: 100%;
            overflow: hidden;
            margin: 0;
            font-family: "微软雅黑";
        }
    </style>
    <script type="text/javascript"
            src="http://api.map.baidu.com/api?v=2.0&ak=G3MR0i3dPER0sLXk7gqQ9kAObfuIlZVw"></script>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <script src="./js/jquery-1.9.1.js"></script>
    <script src="js/jquery-ui.js"></script>
    <link rel="stylesheet" href="http://jqueryui.com/resources/demos/style.css">
    <title>地图展示</title>
    <style type="text/css">
        #dialog {
            background-color: bisque;
        }

        #dialog input {
            width: 300px;
            height: auto;
        }

        #dialog textarea {
            width: 300px;
            height: 200px;
        }

        #dialog table tr td {
            font-family: "Arial", "Microsoft YaHei UI";
            font-size: 14px;
        }
    </style>
</head>
<body>
<div id="allmap">
    <span id="switch"><button value="开关"></button></span>
</div>
<div id="dialog" title="踩踩足迹">
    <div>
        <form id="mainForm" class="infomation">
            <input name='markerId' hidden='hidden' id="markerId" type="text"/>
            <table align="center">
                <tr>
                    <td>足迹(标题)</td>
                    <td><input type="text" name="title" id="title"/></td>
                </tr>
                <tr>
                    <td>旅行时间</td>
                    <td><input type="text" name="logTime" id="logTime"/></td>
                </tr>
                <tr>
                    <td>具体地点</td>
                    <td><input type="text" name="location" id="location"/></td>
                </tr>
                <tr>
                    <td>我的同伴</td>
                    <td><input type="text" name="partner" id="partner"/></td>
                </tr>
                <tr>
                    <td>我的故事</td>
                    <td><textarea name="note" style="height: 200px" id="note"></textarea></td>
                </tr>

                <tr>
                    <form>
                        <td>上传图片</td>
                        <td><input type="file" multiple id="file" name="file[]" style="width: 240px">
                            <input type="button" id="btn" value="上传" style="width: 50px"/></td>
                    </form>

                </tr>
                <tr>
                    <td id="info"></td>
                    <td><input id="sub" type="button" value="记忆"/></td>
                </tr>
            </table>
            <input name='images' hidden='hidden' type="text" id="images"/>
        </form>
    </div>
</div>
</body>
</html>
<script type="text/javascript">
    // 百度地图API功能
    var map = new BMap.Map("allmap");    // 创建Map实例
    map.centerAndZoom(new BMap.Point(116.220027, 36.845), 4);  // 初始化地图,设置中心点坐标和地图级别
    //添加地图类型控件
    map.setMapType(BMAP_HYBRID_MAP);
    map.setCurrentCity("北京");          // 设置地图显示的城市 此项是必须设置的
    map.disableDoubleClickZoom(); //禁用双击放大
    map.disableScrollWheelZoom();

    getMarkerList();//获取用户标注数据

    /************************ 获取用户所有标注点信息，并动态添加标注和折线 ****************************/
    function getMarkerList() {
        $.getJSON("api/marker/list?userId=<%=userId %>",function (results) {
            var result = results.result;
            if (result == 0) {
                return 0;
            } else {
                var list = results.list;
                for (var index = 0; index < list.length; index++) {
                    addMarker(list[index].id, list[index].pointX, list[index].pointY)
                    if (index != 0) {
                        //todo 将折线换做箭头
                        var pointLast = new BMap.Point(list[index - 1].pointX, list[index - 1].pointY);//获取上一标注位置
                        var polyline = new BMap.Polyline([pointLast, new BMap.Point(list[index].pointX, list[index].pointY)], {
                            strokeColor: "blue",
                            StrokeWeight: 4,
                            strokeOpacity: 0.5
                        });
                        map.addOverlay(polyline);
                    }
                }
                var viewPort = map.getViewport(getPoints(list));
                map.centerAndZoom(viewPort.center, viewPort.zoom);
            }
        })
    }
  $("#allmap").click(function () {
      window.parent.location.href = "careMapBig.jsp?userId=<%=userId %>" ;
  })
    function addMarker(markerId, pointx, pointy) {
        var point = new BMap.Point(pointx, pointy);
        var marker = new BMap.Marker(point);
        map.addOverlay(marker);
        return marker;
    }
    /***** 通过后台获取的标注数据，生成一个point数组，用于动态调整地图级别*****/
    function getPoints(list) {
        var points = new Array();
        for (var index = 0; index < list.length; index++) {
            var point = new BMap.Point(list[index].pointX, list[index].pointY);
            points.push(point);
        }
        return points;
    }
</script>
