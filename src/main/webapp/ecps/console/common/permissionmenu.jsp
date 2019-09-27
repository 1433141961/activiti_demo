<%@ page language="java" errorPage="/ecps/console/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="taglibs.jsp"%>
<head>
<meta name="menu" content=""/>
</head>
<h2><samp class="t03"></samp>权限管理</h2>
<ul class="ul">
    <p:perm url="fun/selectAllFun.do?assignee=boss">
        <li><a href="${path}/user/selectAllUser.do?assignee=employee"><samp class="t05"></samp>用户管理</a></li>
        <li><a href="${path}/role/selectAllRole.do?assignee=manager"><samp class="t05"></samp>角色管理</a></li>
        <li><a href="${path}/fun/selectAllFun.do?assignee=boss"><samp class="t05"></samp>权限配置</a></li>
    </p:perm>
<%--    <p:perm url="user/selectAllUser.do?assignee=employee">
        <span style="color: red;">
            你没有该权限
        </span>
    </p:perm>--%>
</ul>
