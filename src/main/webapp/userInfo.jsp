<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"  %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>个人中心</title>
    <link rel="stylesheet" href="css/userInfo.css">
    <script src="./js/jquery-1.9.1.js"></script>
    <script src="js/jquery-ui.js"></script>
</head>
<body>
<div class="center">
    <div class="header">
      <%@include file="header.jsp"%>
    </div>
    <div class="content">
        <div class="sub"><h3>个人中心</h3></div>
        <hr>
        <div class="userInfoTab">
            <form class="userInfoFrom" id="userInfoFrom">
            <table>
                <tr>
                    <td class="key">头像</td>
                    <td class="value">会员**的个人资料</td>
                </tr>
                <tr>
                    <td class="key">会员等级</td>
                    <td class="value">黄金会员<button>升级会员</button></td>
                </tr>
                <tr>
                    <td class="key">昵称</td>
                    <td class="value"><input type="text" id="username" class="username" name="username"/></td>
                </tr>
                <tr>
                    <td class="key">真实姓名</td>
                    <td class="value"><input type="text" id="name" class="name" name="name"/></td>
                </tr>
                <tr>
                    <td class="key">绑定邮箱</td>
                    <td class="value"><input type="text" id="email" class="email" name="email"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="button" value="提交" id="sub" /></td>
                </tr>
            </table>
            </form>
        </div>
    </div>
</div>
</body>
</html>
<script type="text/javascript">
    $(function () {
        $("#sub").click(function () {
            var param =  $("#userInfoFrom").serialize();
            $.ajax({
                url: "api/userInfo/update",
                type: "post",
                dataType:"text",
                data:param,
                success:function (result) {
                    alert("保存成功");
                }
            });

        })
    });
</script>