<%--
  Created by IntelliJ IDEA.
  User: dotwintech
  Date: 2018/8/21
  Time: 10:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery-1.9.1.js"></script>
    <script src="js/jquery-ui.js"></script>
    <link rel="stylesheet" href="css/recorderList.css"/>
</head>
<body>
<div class="content">
    <div class="center">
        <div class="header">
            <%@include file="header.jsp" %>
        </div>
        <div class="list">
            <div class="pageHeader">
                <span>
                    <br>
                    我的足迹
                    <hr>
                </span>
            </div>
            <div id="dataTable"><table></table></div>
            <div id="pager" class="yahoo2"></div>
        </div>
        <%--/list--%>
    </div>
    <%--/center--%>
</div>
</body>
</html>
<script type="application/javascript">
    $(function () {

        $.post("api/recorder/getNum", null, function (data) {
            if (data.result == 0) {
                alert("获取数据总数失败");
            } else {
                var total = data.recorderNum;
                PageClick(1, total, 3);
            }
        });

        PageClick = function (pageIndex, total, spanInterval) {
            var pageSize = 5;
            $.ajax({
                url: "api/recorder/list",
                data: JSON.stringify({
                    "page": {
                        "pageNo": pageIndex,
                        "pageSize": pageSize
                    },
                    "recorder": {
                        "title": "",
                        "startTime": "",
                        "endTime": ""
                    }
                }),
                type: "post",
                dataType: "json",
                contentType: "application/json;charset=UTF-8",
                success: function (data) {
                    if (data.result == 0) {
                        alert("数据加载失败");
                    } else {
                        //索引从1开始
                        //将当前页索引转为int类型
                        var intPageIndex = parseInt(pageIndex);

                        //获取显示数据的表格
                        var table = $("#dataTable");
                        //清除表格中内容
                        $("#dataTable tr").remove();
                        //向表格中添加内容
                        var dataList = data.list;
                        for (var i = 0; i < dataList.length; i++) {
                            var ser = i+1;
                            table.append(
                                $("<tr><td class='title'>" +
                                    ser  + ".  " + dataList[i].title
                                    + "</td></tr>")
                            );
                            table.append($("<tr class='blank'></tr>"))
                            table.append(
                                $("<tr><td class='logTime'>时间：" +
                                     dataList[i].logTime
                                    + "</td><td class='partner'>同伴：" +
                                    dataList[i].partner
                                    + "</td><td class='location'>地点：" +
                                    dataList[i].location
                                    + "</td></tr>")
                            );
                            table.append($("<tr class='blank'></tr>"))
                            table.append(
                                $("<tr class='noteArea'><td class='note' colspan='3'>故事：" +
                                    dataList[i].note
                                    + "</td></tr>"
                                )
                            )
                            table.append(
                                $("<tr id = 'imageTr" + i + "' class='imagesArea'></tr>")
                            );
                            table.append($("<tr class='blank'></tr>"))
                            var images = dataList[i].imageBeanList;
                            for (var j = 0; j < images.length; j++) {
                                var imagesTr = $("#imageTr" + i);
                                imagesTr.append(
                                    $("<td class='imageAlone'><img src='image/1.jpg' style='height: 200px;width: 100%'></td>")
                                )
                            }
                        }
                    }

                    //创建分页
                    //将总记录数结果 得到 总页码数
                    var pageS = total
                    if (pageS % pageSize == 0) pageS = pageS / pageSize;
                    else pageS = parseInt(total / pageSize) + 1;
                    var $pager = $("#pager");

                    //清楚分页div中的内容
                    $("#pager span").remove();
                    $("#pager a").remove();

                    //添加第一页
                    if (intPageIndex == 1)
                        $pager.append("<span class='disabled'>第一页</span>");
                    else {
                        var first = $("<a href='javascript:void(0)' first='" + 1 + "'>第一页</a>").click(function () {
                            PageClick($(this).attr('first'), total, spanInterval);
                            return false;
                        });
                        $pager.append(first);
                    }


                    //添加上一页
                    if (intPageIndex == 1)
                        $pager.append("<span class='disabled'>上一页</span>");
                    else {
                        var pre = $("<a href='javascript:void(0)' pre='" + (intPageIndex - 1) + "'>上一页</a>").click(function () {
                            PageClick($(this).attr('pre'), total, spanInterval);
                            return false;
                        });
                        $pager.append(pre);
                    }

                    //设置分页的格式  这里可以根据需求完成自己想要的结果
                    var interval = parseInt(spanInterval); //设置间隔
                    var start = Math.max(1, intPageIndex - interval); //设置起始页
                    var end = Math.min(intPageIndex + interval, pageS)//设置末页

                    if (intPageIndex < interval + 1) {
                        end = (2 * interval + 1) > pageS ? pageS : (2 * interval + 1);
                    }

                    if ((intPageIndex + interval) > pageS) {
                        start = (pageS - 2 * interval) < 1 ? 1 : (pageS - 2 * interval);

                    }


                    //生成页码
                    for (var j = start; j < end + 1; j++) {
                        if (j == intPageIndex) {
                            var spanSelectd = $("<span class='current'>" + j + "</span>");
                            $pager.append(spanSelectd);
                        } //if
                        else {
                            var a = $("<a href='javascript:void(0)'>" + j + "</a>").click(function () {
                                PageClick($(this).text(), total, spanInterval);
                                return false;
                            });
                            $pager.append(a);
                        } //else
                    } //for

                    //上一页
                    if (intPageIndex == total) {
                        $pager.append("<span class='disabled'>下一页</span>");

                    }
                    else {

                        var next = $("<a href='javascript:void(0)' next='" + (intPageIndex + 1) + "'>下一页</a>").click(function () {
                            PageClick($(this).attr("next"), total, spanInterval);
                            return false;
                        });
                        $pager.append(next);
                    }

                    //最后一页
                    if (intPageIndex == pageS) {
                        $pager.append("<span class='disabled'>最后一页</span>");
                    }
                    else {
                        var last = $("<a href='javascript:void(0)' last='" + pageS + "'>最后一页</a>").click(function () {
                            PageClick($(this).attr("last"), total, spanInterval);
                            return false;
                        });
                        $pager.append(last);
                    }

                }

            });

        };

    });
</script>
