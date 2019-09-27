<%@ include file="/ecps/console/common/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
<title>权限管理_用户管理</title>
<meta name="heading" content="权限管理"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/${system}/scripts/tabletree/css/jquery.treeTable.css'/>"/>
<script type="text/javascript" src="<c:url value='/${system}/scripts/tabletree/js/jquery.treeTable.min.js'/>"></script>
<meta name="tag" content="tagName"/>
<script type="text/javascript">
	
</script>
</head>
<body id="main">
<div class="frameL"><div class="menu icon">
    <jsp:include page="/ecps/console/common/permissionmenu.jsp"/>
</div></div>

<div class="frameR"><div class="content">
    <div class="loc icon"><samp class="t12"></samp>当前位置：权限管理&nbsp;&raquo;&nbsp;<span class="gray" title="权限配置">权限配置</span></div>

<form id="form1" name="form1" action="${base}//perm/listAllPerm.do" method="post">
	<div class="page_c">
        <span class="r inb_a">
            <a href="/fun/toAddFun.do" title="添加权限" class="btn80x20">添加权限</a>
        </span>
    </div>
    <table id="treeid" name="treeid" cellspacing="0" summary="" class="tab">
    	<thead>
    		<tr>
<%--
    			<th><input type='checkbox' id='all' name='all' title='全选/取消' /></th>
--%>
    			<th>功能名称</th>
    			<th>URL</th>
    			<th>功能描述</th>
    			<th>操作</th>
    		</tr>
    	</thead>
    	<tbody>
    		<c:forEach items="${funs}" var="fun">
				<tr>
					<td>${fun.funName}</td>
					<td>${fun.url}</td>
					<td>${fun.funDesc}</td>
					<td><a href="${path}/fun/deleteFunByFunId.do?funId=${fun.funId}" style="text-underline: none;">删除</a></td>
				</tr>
			</c:forEach>
    	</tbody>
    </table>
	
</form>
</div></div>
</body>


