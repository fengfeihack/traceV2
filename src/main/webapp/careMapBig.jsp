
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
</head>
<body>
<div id="allmap">
</div>
</body>
</html>
<script type="text/javascript">
    // 百度地图API功能
    var map = new BMap.Map("allmap", {minZoom: 4, maxZoom: 14});    // 创建Map实例
    map.centerAndZoom(new BMap.Point(116.404, 39.915), 5);  // 初始化地图,设置中心点坐标和地图级别
    //添加地图类型控件
    map.addControl(new BMap.MapTypeControl({
        mapTypes: [
            BMAP_NORMAL_MAP,
            BMAP_SATELLITE_MAP,
            BMAP_HYBRID_MAP
        ]
    }));
    map.addControl(new BMap.ScaleControl(BMAP_ANCHOR_TOP_LEFT));
    map.setMapType(BMAP_HYBRID_MAP);
    map.setCurrentCity("北京");          // 设置地图显示的城市 此项是必须设置的
    map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
    map.disableDoubleClickZoom(); //禁用双击放大
    getMarkerList();//获取用户标注数据

    /********************************自定义轮滚缩放控件*******************************************/
    function ZoomControl(){
        // 默认停靠位置和偏移量
        this.defaultAnchor = BMAP_ANCHOR_TOP_LEFT;
        this.defaultOffset = new BMap.Size(10, 10);
    }

    // 通过JavaScript的prototype属性继承于BMap.Control
    ZoomControl.prototype = new BMap.Control();

    // 自定义控件必须实现自己的initialize方法,并且将控件的DOM元素返回
    // 在本方法中创建个div元素作为控件的容器,并将其添加到地图容器中
    ZoomControl.prototype.initialize = function(map){
        // 创建一个DOM元素
        var div = document.createElement("div");
        var button = document.createElement("button");
        button.innerText="关闭轮滚缩放";
        var state=1;
        // 添加文字说明
        div.appendChild(button);
        // 设置样式
        div.style.cursor = "pointer";
        div.style.border = "1px solid gray";
        div.style.backgroundColor = "white";
        // 绑定事件,点击一次放大两级
        div.onclick = function(e){
            if(state==1){
                map.disableScrollWheelZoom();
                button.innerText="开启轮滚缩放";
                state=0;
            }else{
                map.enableScrollWheelZoom();
                button.innerText="关闭轮滚缩放";
                state=1;
            }

        }
        // 添加DOM元素到地图中
        map.getContainer().appendChild(div);
        // 将DOM元素返回
        return div;
    }
    // 创建控件
    var myZoomCtrl = new ZoomControl();
    // 添加到地图当中
    map.addControl(myZoomCtrl);
    /************************ 自定义跳转主页控件 **********************************************/
    function FullControl(){
        // 默认停靠位置和偏移量
        this.defaultAnchor = BMAP_ANCHOR_TOP_LEFT;
        this.defaultOffset = new BMap.Size(130, 10);
    }

    // 通过JavaScript的prototype属性继承于BMap.Control
    FullControl.prototype = new BMap.Control();

    // 自定义控件必须实现自己的initialize方法,并且将控件的DOM元素返回
    // 在本方法中创建个div元素作为控件的容器,并将其添加到地图容器中
    FullControl.prototype.initialize = function(map){
        // 创建一个DOM元素
        var div = document.createElement("div");
        var button = document.createElement("button");
        button.innerText="返回";
        // 添加文字说明
        div.appendChild(button);
        // 设置样式
        div.style.cursor = "pointer";
        div.style.border = "1px solid gray";
        div.style.backgroundColor = "white";
        div.onclick = function(e){
            window.history.back(-1);
        }
        // 添加DOM元素到地图中
        map.getContainer().appendChild(div);
        // 将DOM元素返回
        return div;
    }
    // 创建控件
    var myFullCtrl = new FullControl();
    // 添加到地图当中
    map.addControl(myFullCtrl);
    /**************************  自定义返回我的地图控件 ***************************************/
    function MyMapControl(){
        // 默认停靠位置和偏移量
        this.defaultAnchor = BMAP_ANCHOR_TOP_LEFT;
        this.defaultOffset = new BMap.Size(230, 10);
    }

    // 通过JavaScript的prototype属性继承于BMap.Control
    MyMapControl.prototype = new BMap.Control();

    // 自定义控件必须实现自己的initialize方法,并且将控件的DOM元素返回
    // 在本方法中创建个div元素作为控件的容器,并将其添加到地图容器中
    MyMapControl.prototype.initialize = function(map){
        // 创建一个DOM元素
        var div = document.createElement("div");
        var button = document.createElement("button");
        button.innerText="我的地图";
        // 添加文字说明
        div.appendChild(button)
       // button.appendChild(a);
        // 设置样式
        div.style.cursor = "pointer";
        div.style.border = "1px solid gray";
        div.style.backgroundColor = "white";
        div.onclick = function(e){
           window.location.href="myMapBig.html";
        }
        // 添加DOM元素到地图中
        map.getContainer().appendChild(div);
        // 将DOM元素返回
        return div;
    }
    // 创建控件
    var MyMapControl = new MyMapControl();
    // 添加到地图当中
    map.addControl(MyMapControl);
    /************************ 获取用户所有标注点信息，并动态添加标注和折线 ****************************/
    function getMarkerList(zoom,center) {
        $.getJSON("api/marker/list?userId=<%=userId %>", function (results) {
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
                map.centerAndZoom(new BMap.Point(center,zoom));
            }
        })
    }
    function addMarker(markerId, pointx, pointy) {
        var point = new BMap.Point(pointx, pointy);
        var marker = new BMap.Marker(point);
        var opts = {
            width :0 ,     // 信息窗口宽度
            height:0,     // 信息窗口高度
            enableMessage:true,//设置允许信息窗发送短息
        }
        var infoWindow = new BMap.InfoWindow("",opts);
        $.getJSON("api/recorder/getNum?markerId="+markerId,function (data) {
            if(data.result==0){
                alert("未知错误");
            }else{
               infoWindow.setContent("<a href='index.html'>"+"足迹数量"+data.recorderNum+"</a>");
            }
        });
        /*******添加监听事件：好友地图上鼠标左键点击标注点时打开足迹列表界面，显示该标注下的足迹*******/
        //TODO
        marker.addEventListener("click", function (){
           window.location.href="recorderList.jsp?markerId="+markerId;
        })
        /*******添加监听事件：鼠标浮到标注点上时打开信息窗口*******/
        marker.addEventListener("mouseover",function () {
            marker.openInfoWindow(infoWindow);
        });
        /*******添加监听事件：鼠标移开标注点上时关闭信息窗口*******/
        marker.addEventListener("mouseout",function () {
            marker.closeInfoWindow();
        });
        map.addOverlay(marker);
        return marker;
    }
</script>
