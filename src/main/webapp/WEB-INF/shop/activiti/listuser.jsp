<%@ include file="/ecps/console/common/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
<title>用户管理_用户管理</title>
<meta name="heading" content="用户管理"/>
<meta name="tag" content="tagName"/>
<script type="text/javascript">
	
</script>
</head>
<body id="main">
<div class="frameL"><div class="menu icon">
    <jsp:include page="/ecps/console/common/activitimenu.jsp"/>
</div></div>

<div class="frameR"><div class="content">

    <div class="loc icon"><samp class="t12"></samp>当前位置 请假管理&nbsp;&raquo;&nbsp;<span class="gray" title="请假单">请假单</span></div>

<form id="form1" name="form1" action="${base}/leave/user/listUser.do" method="post">
	

    <div class="page_c">
        
        <span class="r inb_a">
            <a href="${path }/shop/leave/adduser.jsp" title="添加用户" class="btn80x20">添加用户</a>
        </span>
    </div>
    
	<table cellspacing="0" summary="" class="tab">
		<thead>
			<th><input type="checkbox" id="all" name="all" title="全选/取消" /></th>
			<th>用户名</th>
			<th class="nwp">所属角色</th>
			<th>状态</th>
			<th>最后登录IP</th>
			<th>最后登录时间</th>
			<th>操作</th>
		</thead>
		<tbody>
			<tr>
				
			</tr>
			
		</tbody>
	</table>
	
	
</form>
</div></div>
</body>

