<%@ include file="/ecps/console/common/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
<title>角色管理_用户管理</title>
<meta name="heading" content="角色管理"/>
<meta name="tag" content="tagName"/>
<script type="text/javascript">
	$(function () {
		var state = $("#state").val();
		if (state=='1'){
		    $("#tab2").hide();
		    $("#label4").attr("class","here");
		}else if (state=='2'){
            $("#tab1").hide();
            $("#label5").attr("class","here");
		}

		//获得taskId并且将值赋给已完成的href链接
		var taskId = $("#taskId").val();
		var url = "${path}/leave/doBillDetail.do?taskId="+taskId;
		if(taskId!=null){
		    $("#historyInstanceBillDetail").attr("href",url)
		}
    })

</script>
</head>
<body id="main">
<div class="frameL"><div class="menu icon">
    <jsp:include page="/ecps/console/common/itemmenu.jsp"/>
</div></div>

<div class="frameR"><div class="content">
	<input type="hidden" id="state" value="${state}">

    <div class="loc icon"><samp class="t12"></samp>当前位置：请假单管理&nbsp;&raquo;&nbsp;<span class="gray" title="用户请假">用户请假</span></div>
    
    <div class="page_c">
        <span class="r inb_a">
            <a href="${path}/leave/toAddBill.do" title="添加请假单" class="btn80x20">添加请假单</a>
        </span>
    </div>

	<h2 class="h2_ch"><span id="tabs" class="l">
        <a id="label4" href="${path}/leave/listBill.do?assignee=employee&state=1"  class="nor">未完成</a>
        <a id="label5" href="${path}/leave/listBill.do?assignee=employee&state=2"   class="nor">已完成</a>
    </span></h2>
	<input type="hidden" id="roleId" value="">
	<table cellspacing="0" summary="" class="tab" id="tab1">

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

	<table cellspacing="0" summary="" class="tab" id="tab2">

		<thead>
		<tr>
			<%--<th><input type="checkbox" id="all" name="all" title="全选/取消" /></th>--%>
			<th>请假天数</th>
			<th>原因</th>
			<th>开始时间</th>
			<th>完成时间</th>
			<th>状态</th>
			<%--<th>操作</th>--%>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${piList}" var="pib">
			<tr>
				<td>${pib.leaveBill.days}</td>
				<td>${pib.leaveBill.reason}</td>
				<td><fmt:formatDate value="${pib.startTime}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
				<td><fmt:formatDate value="${pib.finishDate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
				<td>已完成</td>
				<%--<td><a id="historyInstanceBillDetail" href="">查看</a></td>--%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div></div>
</body>

