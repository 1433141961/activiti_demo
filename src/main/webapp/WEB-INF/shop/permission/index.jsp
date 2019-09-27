<%@ page import="org.aspectj.weaver.ast.Var" %>
<%@ page import="com.yzw.model.User" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/ecps/console/common/taglibs.jsp" %>
<head>
    <title><fmt:message key="menu.shopuserMgmt"/></title>
    <meta name="heading" content="<fmt:message key='mainme.heading'/>"/>
    <meta name="menu" content=""/>

</head>
<body id="main">
<div class="frameL">
    <div class="menu icon">
        <jsp:include page="/ecps/console/common/permissionmenu.jsp"/>
    </div>
</div>

<div class="frameR">
    <div class="content">
        <%
        User user = (User) session.getAttribute("user");
        List<String> urls = user.getUrlList();
        if (urls!=null && !urls.contains("fun/selectAllFun.do?assignee=boss")){
        %>
        <script type="text/javascript" >
            alert("老板才有该权限");
        </script>
        <%
            }
        %>
        <!--这里是body -->
    </div>

</div>
</body>

