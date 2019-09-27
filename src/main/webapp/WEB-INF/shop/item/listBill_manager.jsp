<%@ include file="/ecps/console/common/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
<title>角色管理_用户管理</title>
<meta name="heading" content="角色管理"/>
<meta name="tag" content="tagName"/>
<script type="text/javascript">


</script>
</head>
<body id="main">
<div class="frameL"><div class="menu icon">
    <jsp:include page="/ecps/console/common/itemmenu.jsp"/>
</div></div>

<div class="frameR"><div class="content">

    <div class="loc icon"><samp class="t12"></samp>当前位置：请假单管理&nbsp;&raquo;&nbsp;<span class="gray" title="经理审批">经理审批</span></div>
    
 <%--   <div class="page_c">
        <span class="r inb_a">
            <a href="${path}/leave/toAddBill.do" title="添加请假单" class="btn80x20">添加请假单</a>
        </span>
    </div>--%>
	<input type="hidden" id="roleId" value="">
	<table cellspacing="0" summary="" class="tab">

		<thead>
		<tr>
			<%--<th><input type="checkbox" id="all" name="all" title="全选/取消" /></th>--%>
			<th>任务名称</th>
			<th>请假天数</th>
			<th>原因</th>
			<th>开始时间</th>
			<th>状态</th>
			<th>操作</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${taskBeans}" var="taskBean">
			<tr>
				<td>${taskBean.taskName}</td>
				<td>${taskBean.leaveBill.days}</td>
				<td>${taskBean.leaveBill.reason}</td>
				<td><fmt:formatDate value="${taskBean.leaveBill.beginTime}" pattern="yyyy-MM-dd"/></td>
				<td>${taskBean.incomeing}</td>
				<td><a href="${path}/leave/doBillDetail.do?taskId=${taskBean.taskId}">查看</a></td>

			</tr>
		</c:forEach>

		</tbody>
	</table>
	
</div></div>
</body>

