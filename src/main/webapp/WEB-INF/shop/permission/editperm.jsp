<%@ include file="/ecps/console/common/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
<title>编辑权限_用户管理_权限管理</title>
<meta name="heading" content="<fmt:message key='mainMenu.heading'/>"/>
<meta name="tag" content="tagName"/>
<script type="text/javascript">

</script>
</head>
<body id="main">
<div class="frameL"><div class="menu icon">
    <jsp:include page="/ecps/console/common/permissionmenu.jsp"/>
</div></div>

<div class="frameR"><div class="content">

	<div class="loc icon"><samp class="t12"></samp><fmt:message key='menu.current.loc'/>：权限管理&nbsp;&raquo;&nbsp;<a href="<c:url value="/${system}//perm/listPerm.do"/>" title="权限配置">权限配置</a>&nbsp;&raquo;&nbsp;<span class="gray" title="编辑权限">编辑权限</span><a href="<c:url value="/${system}//perm/listPerm.do"/>" title="返回权限管理" class="inb btn120x20">返回权限管理</a></div>
	<form id="form1" name="form1" action="${base}//perm/updatePerm.do" method="post">
		<div class="edit set">
            <p>
            	<label class="alg_t"><samp>*</samp>所属功能：</label><ui:select name="permUpid" list="listperm" rootId="0" currentValue="${perm.permUpid }" defaultvalue="0" defaulttext="电商系统"/>
            </p>
            <p>
            	<label class="alg_t"><samp>*</samp>功能名称：</label><input type="text" name="permName" class="text state" value="${perm.permName }" reg="^[a-zA-Z0-9\u4e00-\u9fa5]{3,20}$" tip="必须是中英文或数字字符，长度3-20"/>
				<span><c:out value="${permname}"/></span>
            </p>
            <p>
            	<label class="alg_t"><samp>*</samp>功能URL：</label><input type="text" name="permUrl" class="text state" value="${perm.permUrl }" size="50" reg="^[a-zA-Z0-9/._]{3,100}$" tip="必须是英文或数字字符，英文句号和斜杠和'_'，长度3-100"/>
				<span></span>
            </p>
        
            <p>
            	<label class="alg_t">功能描述：</label><textarea rows="4" cols="50" name="permNote"  class="are" reg="^((.|\n){4,99})?$" tip="5到100以内的任意字符">${perm.permNote }</textarea>     	
            	<span></span>
            </p>
            
            <p>
           		<label>&nbsp;</label><input type="button" class="hand btn83x23b" id="addURL" name="addURL" size="50" value="新增相关URL"/>
            </p>
           
        </div>
	</form>
	<input type="hidden" id="permListUrl" name="permListUrl" value="${base}//perm/listPerm.do" />
    <div class="loc">&nbsp;</div>

</div></div>
</body>

