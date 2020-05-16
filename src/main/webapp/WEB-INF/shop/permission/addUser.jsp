<%@ include file="/ecps/console/common/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
<title>添加用户_用户管理_用户管理</title>
<meta name="heading" content="用户添加"/>
<meta name="tag" content="tagName"/>
</head>
<body id="main">
<div class="frameL"><div class="menu icon">
    <jsp:include page="/ecps/console/common/permissionmenu.jsp"/>
</div></div>

<script type="text/javascript">
    function backToList(pathURL) {
        $(location).attr("href",pathURL)
    }
 
</script>

<div class="frameR"><div class="content">

	<div class="loc icon"><samp class="t12"></samp>当前位置：权限管理&nbsp;&raquo;&nbsp;<a href="<c:url value="${path}/user/selectAllUser.do"/>" title="用户管理">用户管理</a>&nbsp;&raquo;&nbsp;<span class="gray" title="添加用户">添加用户</span><a href="<c:url value="${path}/user/selectAllUser.do"/>" title="返回用户管理" class="inb btn120x20">返回用户管理</a></div>
	<form id="form22" name="form1" action="${path}/user/insert.do" method="post">
		<ul class="uls edit set">
            <li>
            	<label class="alg_t"><span style="color:red">*</span>用户名：</label><input type="text" name="userName" class="text state" reg="^[a-zA-Z0-9\u4e00-\u9fa5]{2,20}$" tip="必须是中英文或数字字符，长度2-20"/>
            	<span></span>
            </li>
            <li>
            	<label class="alg_t"><span style="color:red">*</span> 密码：</label><input type="password" id="password" name="password" class="text state"/>
				<span></span>
            </li>
            <li>
            	<label class="alg_t"><span style="color:red">*</span> 电子邮箱：</label><input type="text" id="email" name="eMaol" class="text state" maxlength="50" value="" reg="^[a-zA-Z0-9]+[-_.a-zA-Z0-9]+@[-_a-zA-Z0-9]+(\.[-_a-zA-Z0-9]+)+$" tip="email不能大于50个字符，并且需要输入正确的格式！"/>
				<span id="spanEmail"></span>
            </li>
            <li>
            	<label class="alg_t"><span style="color:red">*</span> 电话号码：</label><input type="text" name="telNum" class="text state"/>
				<span></span>
            </li>
            
            <li>
            	<label>&nbsp;</label><input type="submit" id="btn" class="hand btn83x23" value="提交" />
            	<input type="button" class="hand btn83x23b" value="取消" onclick="backToList('${path}/user/selectAllUser.do')" />
            </li>
        </ul>
	</form>
	<input type="hidden" id="userListUrl" name="userListUrl" value="${path}/user/listUser.do" />
    <div class="loc">&nbsp;</div>

</div></div>
</body>

