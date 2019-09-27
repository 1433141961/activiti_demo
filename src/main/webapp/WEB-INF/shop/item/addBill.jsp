<%@ include file="/ecps/console/common/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
<title>添加请假单</title>
<meta name="heading" content="添加请假单"/>
<meta name="tag" content="tagName"/>
<script type="text/javascript">
    function backToList(pathURL) {
        $(location).attr("href",pathURL)
    }

</script>
</head>
<body id="main">
<div class="frameL"><div class="menu icon">
    <jsp:include page="/ecps/console/common/itemmenu.jsp"/>
</div></div>

<div class="frameR"><div class="content">

	<div class="loc icon"><samp class="t12"></samp>当前位置：请假单管理&nbsp;&raquo;&nbsp;<a href="<c:url value="${path}/leave/listBill.do?assignee=employee&state=1"/>" title="用户请假">用户请假</a>&nbsp;&raquo;&nbsp;<span class="gray" title="添加请假单">添加请假单</span><a href="<c:url value="${path}/leave/listBill.do?assignee=employee&state=1"/>" title="返回用户请假" class="inb btn120x20">返回用户请假</a></div>
	<form id="form1" name="form1" action="${path}/leave/addBill.do" method="post">
		<div class="edit set">

            <p>
                <label class="alg_t"><samp>*</samp>请假天数：</label><input type="text" name="days" class="text state" reg="^[a-zA-Z0-9\u4e00-\u9fa5]{3,20}$" tip="必须是中英文或数字字符，长度3-20"/>
            </p>
            <p>
                <label class="alg_t"><samp>*</samp>请假日期：</label><input type="text" name="beginTime" class="text state" size="50" reg="^[a-zA-Z0-9/._?-&*#@!]{3,100}$" tip="必须是英文或数字字符，英文/._?&*#@!，长度3-100"/>
                <span></span>
            </p>

            <p>
                <label class="alg_t">请假原因：</label><textarea rows="4" cols="50" class="are" name="reason" reg="^((.|\n){0,50})?$" tip="0到50以内的任意字符"></textarea>
                <span></span>
            </p>
           
            <p>
            	<label>&nbsp;</label><input type="submit" class="hand btn83x23" value="添加" />
            	<input type="button" class="hand btn83x23b" value="取消" onclick="backToList('${path}/leave/listBill.do?assignee=employee&state=1')" />
            </p>
        </div>
	</form>
	<input type="hidden" id="permListUrl" name="permListUrl" value="${base}//perm/listPerm.do" />
    <div class="loc">&nbsp;</div>

    <%--<table cellspacing="0" summary="" class="tab">

        <thead>
        <tr>
            &lt;%&ndash;<th><input type="checkbox" id="all" name="all" title="全选/取消" /></th>&ndash;%&gt;
            <th>操作人</th>
            <th>批注</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${commentBeans}" var="role">
            <tr>
                <td>${role.roleName}</td>
                <td>${role.roleDesc}</td>
                <td><a href="JavaScript:void (0)" onclick="addFun(${role.roleId})">添加权限</a></td>
            </tr>
        </c:forEach>

        </tbody>
    </table>--%>
</div></div>
</body>

