<%@ page language="java" errorPage="/ecps/console/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="taglibs.jsp"%>

<menu:useMenuDisplayer name="Velocity" config="cssHorizontalMenu.vm">
<div class="nav w">
    <ul id="nav_m" class="ul">
        <menu:displayMenu name="ItemMgmtMenu"/>
        <menu:displayMenu name="permission"/>
        <%--<menu:displayMenu name="activiti"/>--%>
     </ul>
    <p></p>
</div>
</menu:useMenuDisplayer>
