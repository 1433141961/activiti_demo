<%@ include file="/ecps/console/common/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
<title>角色管理_用户管理</title>
<meta name="heading" content="角色管理"/>
<meta name="tag" content="tagName"/>
<script type="text/javascript">

	$(function () {
	    /**
		 * 全选
		 *
		 * */
	    $("#checkAll").click(function () {
	        if ($(this).attr("checked") =="checked"){
                $("#viewlblID input" ).each(function () {
                    $(this).attr("checked","checked");
                })
			}else{
                $("#viewlblID input" ).each(function () {
                    $(this).removeAttr("checked");
                })
			}

        });
	    /**
		 * 赋权：
         * 点击确定后首先删除已有的所有权限（删除表role_fun），然后重新插入新赋值的权限
         *
		 *
		 * */

		$("#lblQuery").click(function(){
			var roleId = $("#roleId").val();
			var funIds = "";
			$("#viewlblID input" ).each(function () {
				var checked = $(this).attr("checked");
				var funId = $(this).attr("id");
				if(checked == "checked"){
				    funIds = funIds+funId+",";
				}
            })
            $.ajax({
                data:{
                    roleId:roleId,
					funIds:funIds
                },
                url:"${path}/roleFun/grantFun.do",
                type:"post",
                dataType:"text",
                success:function (text) {
                        if (text=="success"){
                            alert("赋权成功");
                            //com.js下的隐藏窗口的方法
                            tipHide("#editSimCardLabels");
                        }
                },
                error:function () {
                    alert("系统异常")
                }
            })
        })
    });

    /**
	 * 查询所有权限
     * @param roleId
     */
	function addFun(roleId) {
		// alert(getRoleId);
        //com.js下的显示窗口的方法
		tipShow("#editSimCardLabels");
        $("#viewlblID").empty();
		$.ajax({
            url:"${path}/user/selectAllFunForAjax.do",
            type:"post",
            dataType:"text",
			success:function (text) {
                //把字符串转化为json数组
                var jsonArray = $.parseJSON(text);

                for (var i = 0; i<jsonArray.length;i++){
                    //给每个checkedbox赋值id为当前权限的funId
                    $("<tr><td><input type='checkbox' id='"+jsonArray[i].funId+"'></td>" +
						"<td>"+jsonArray[i].funName+"</td>" +
						"<td>"+jsonArray[i].url+"</td>" +
						"<td>"+jsonArray[i].funDesc+"</td>" +
						"</tr>").appendTo("#viewlblID");
                }

                /**
				 * 查看已有权限并且选中checkedBox
                 */
                $.ajax({
                		data:{
                		  roleId:roleId
                        },
                        url:"${path}/user/selectFunByRoleId.do",
                        type:"post",
                        dataType:"text",
                        success:function (text) {
                            // alert(text)
                            //把字符串转化为json数组
                            var jsonArray = $.parseJSON(text);
                            // alert(jsonArray)
                            for (var i = 0; i<jsonArray.length;i++){
								var funId = jsonArray[i].funId;
								$("#"+funId).attr("checked","checked");
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
    //给隐藏域赋值roleId方便在赋权的时候取到
    $("#roleId").attr("value",roleId);
    }

</script>
</head>
<body id="main">
<div class="frameL"><div class="menu icon">
    <jsp:include page="/ecps/console/common/permissionmenu.jsp"/>
</div></div>

<div class="frameR"><div class="content">

    <div class="loc icon"><samp class="t12"></samp>当前位置：权限管理&nbsp;&raquo;&nbsp;<span class="gray" title="角色管理">角色管理</span></div>
    
    <div class="page_c">
        <span class="r inb_a">
            <a href="${path}/role/toAddRole.do" title="添加角色" class="btn80x20">添加角色</a>
        </span>
    </div>
	<input type="hidden" id="roleId" value="">
	<table cellspacing="0" summary="" class="tab">

		<thead>
		<tr>
			<%--<th><input type="checkbox" id="all" name="all" title="全选/取消" /></th>--%>
			<th>角色名称</th>
			<th>角色描述</th>
			<th>操作</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${roles}" var="role">
			<tr>
				<td>${role.roleName}</td>
				<td>${role.roleDesc}</td>
				<td><a href="JavaScript:void (0)" onclick="addFun(${role.roleId})">添加权限</a>&nbsp;&nbsp;<a href="${path}/role/delete.do?roleId=${role.roleId}">删除</a></td>
			</tr>
		</c:forEach>

		</tbody>
	</table>
	
</div></div>
</body>

