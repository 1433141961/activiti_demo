<%@ page language="java" errorPage="/ecps/console/error.jsp" pageEncoding="UTF-8"
         contentType="text/html;charset=UTF-8" %>
<%@ include file="taglibs.jsp" %>
<head>
    <title>商品管理</title>

</head>
<h2><samp class="t03"></samp>请假单管理</h2>
<ul class="ul">
    <p:perm url="user/selectAllUser.do?assignee=employee">
        <li><a href="${path}/leave/listBill.do?assignee=employee&state=1"><samp class="t05"></samp>用户请假</a></li>
    </p:perm>
    <p:perm url="role/selectAllRole.do?assignee=manager">
        <li><a href="${path}/leave/listBill.do?assignee=manager"><samp class="t05"></samp>经理审批</a></li>
    </p:perm>
    <p:perm url="fun/selectAllFun.do?assignee=boss">
        <li><a href="${base}/leave/listBill.do?assignee=boss"><samp class="t05"></samp>老板审批</a>
        </li>
    </p:perm>
</ul>

