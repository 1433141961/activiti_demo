<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp"%>
<div class="top w">
   <h1 title="管理员">
		<a href="<c:url value='/'/>">管理员</a>
   </h1>
   <dl>
   		<dt>
   			<%--<c:if test="${user!= null }">
			<span title="${user.fullName}">
				<a href="${base}/user/getUserIndex.do">修改资料</a></span>
			</c:if>
			&nbsp;--%>
			<c:if test="${user == null }">
			   <a href="<c:url value="${path}/ecps/console/login.jsp"/>">请登陆</a>
			</c:if>
			<c:if test="${user != null }">
			   <a href="<c:url value="${path}/ecps/console/logout.jsp"/>">退出</a>
			</c:if>
   		</dt>
   		<dd>
   			<span class="pre"></span>
   			<ul class="ul">
			</ul>
			<span class="next"></span>
   		</dd>
   </dl>
</div>
<%-- Put constants into request scope --%>
<appfuse:constants scope="request"/>