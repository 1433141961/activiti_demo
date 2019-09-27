<%@ include file="/ecps/console/common/taglibs.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<head>
    <title>用户管理_用户管理</title>
    <meta name="heading" content="用户管理"/>
    <meta name="tag" content="tagName"/>
    <script type="text/javascript">

        $(function () {
            /**
             * 全选
             *
             * */
            $("#checkAll1").click(function () {
                if ($(this).attr("checked") =="checked"){
                    $("#viewlblID1 input" ).each(function () {
                        $(this).attr("checked","checked");
                    })
                }else{
                    $("#viewlblID1 input" ).each(function () {
                        $(this).removeAttr("checked");
                    })
                }

            });
            /**
             * 赋权：
             * 点击确定后首先删除已有的所有角色（user_role表里的），然后重新插入已选择的角色
             *
             *
             * */

            $("#lblQuery1").click(function(){
                var userId = $("#userId").val();
                var roleIds = "";
                $("#viewlblID1 input" ).each(function () {
                    var checked = $(this).attr("checked");
                    var roleId = $(this).attr("id");
                    if(checked == "checked"){
                        roleIds = roleIds+roleId+",";
                    }
                })
                $.ajax({
                    data:{
                        userId:userId,
                        roleIds:roleIds
                    },
                    url:"${path}/userRole/grantRole.do",
                    type:"post",
                    dataType:"text",
                    success:function (text) {
                        if (text=="success"){
                            alert("分配角色成功");
                            //com.js下的隐藏窗口的方法
                            tipHide("#editSimCardLabels1");
                        }
                    },
                    error:function () {
                        alert("系统异常")
                    }
                })
            })
        });
        /**
         * 查询所有角色
         * @param userId
         */
        function addUser(userId) {
            // alert(getRoleId);
            //com.js下的显示窗口的方法
            tipShow("#editSimCardLabels1");
            $("#viewlblID1").empty();
            $.ajax({
                url:"${path}/role/selectAllRoleForAjax.do",
                type:"post",
                dataType:"text",
                success:function (text) {
                    //把字符串转化为json数组
                    var jsonArray = $.parseJSON(text);
                    // alert(text);
                    for (var i = 0; i<jsonArray.length;i++){
                        //给每个checkedbox赋值id为当前权限的roleId
                        $("<tr><td><input type='checkbox' id='"+jsonArray[i].roleId+"'></td>" +
                            "<td>"+jsonArray[i].roleName+"</td>" +
                            "<td>"+jsonArray[i].roleDesc+"</td>" +
                            "</tr>").appendTo("#viewlblID1");
                    }

                    /**
                     * 查看已有权限并且选中checkedBox
                     */
                    $.ajax({
                        data:{
                            userId:userId
                        },
                        url:"${path}/role/selectRoleByUserId.do",
                        type:"post",
                        dataType:"text",
                        success:function (text) {
                            // alert(text)
                            //把字符串转化为json数组
                            var jsonArray = $.parseJSON(text);
                            // alert(jsonArray)
                            for (var i = 0; i<jsonArray.length;i++){
                                //获得roleId
                                var roleId = jsonArray[i].roleId;
                                $("#"+roleId).attr("checked","checked");
                            }

                        },
                        error:function () {
                            alert("系统异常")
                        }
                    })

                },
                error:function () {
                    alert("系统异常")
                }



            })
            //给隐藏域赋值userId方便在赋权的时候取到
            $("#userId").attr("value",userId);
        }
    </script>
</head>
<body id="main">
<div class="frameL">
    <div class="menu icon">
        <jsp:include page="/ecps/console/common/permissionmenu.jsp"/>
    </div>
</div>

<div class="frameR">
    <div class="content">

        <div class="loc icon"><samp class="t12"></samp>当前位置：权限管理&nbsp;&raquo;&nbsp;<span class="gray"
                                                                                         title="用户管理">用户管理</span></div>

        <form id="form1" name="form1" action="${base}/permission/user/listUser.do" method="post">


            <div class="page_c">
        
        <span class="r inb_a">
            <a href="${path }/user/toInsert.do" title="添加用户" class="btn80x20">添加用户</a>
        </span>
            </div>
            <input type="hidden" id="userId" value="">
            <table cellspacing="0" summary="" class="tab">
                <thead>
                <tr>
                    <%--<th><input type="checkbox" id="all" name="all" title="全选/取消" /></th>--%>
                    <th>用户名</th>
                    <th>密码</th>
                    <th>电话号码</th>
                    <th>邮箱</th>
                    <th>操作</th>
                </tr>

                </thead>
                <tbody>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>${user.userName}</td>
                        <td>${user.password}</td>
                        <td>${user.telNum}</td>
                        <td>${user.eMaol}</td>
                        <td><a href="javaScript:void(0)" onclick="addUser(${user.userId})">分配角色</a></td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>


        </form>
    </div>
</div>
</body>

