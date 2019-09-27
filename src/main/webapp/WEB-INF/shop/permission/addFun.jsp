<%@ include file="/ecps/console/common/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
<title>权限添加_用户管理_权限管理</title>
<meta name="heading" content="添加权限"/>
<meta name="tag" content="tagName"/>
<script type="text/javascript">
    function backToList(pathURL) {
        $(location).attr("href",pathURL)
    }

</script>
</head>
<body id="main">
<div class="frameL"><div class="menu icon">
    <jsp:include page="/ecps/console/common/permissionmenu.jsp"/>
</div></div>

<div class="frameR"><div class="content">

	<div class="loc icon"><samp class="t12"></samp>当前位置：权限管理&nbsp;&raquo;&nbsp;<a href="<c:url value="${path}/fun/selectAllFun.do"/>" title="权限配置">权限配置</a>&nbsp;&raquo;&nbsp;<span class="gray" title="添加权限">添加权限</span><a href="<c:url value="${path}/fun/selectAllFun.do"/>" title="返回权限管理" class="inb btn120x20">返回权限管理</a></div>
	<form id="form1" name="form1" action="${path}/fun/addFun.do" method="post">
		<div class="edit set">
            
            <p>
            	<label class="alg_t"><samp>*</samp>功能名称：</label><input type="text" name="funName" class="text state" reg="^[a-zA-Z0-9\u4e00-\u9fa5]{3,20}$" tip="必须是中英文或数字字符，长度3-20"/>
				<span><c:out value="${permname}"/></span>
            </p>
            <p>
            	<label class="alg_t"><samp>*</samp>功能URL：</label><input type="text" name="Url" class="text state" size="50" reg="^[a-zA-Z0-9/._?&*#@!]{3,100}$" tip="必须是英文或数字字符，英文/._?&*#@!，长度3-100"/>
				<span></span>
            </p>
          
            <p>
            	<label class="alg_t">功能描述：</label><textarea rows="4" cols="50" class="are" name="funDesc" reg="^((.|\n){4,99})?$" tip="5到100以内的任意字符"></textarea>
            	<span></span>
            </p>
           
            <p>
            	<label>&nbsp;</label><input type="submit" class="hand btn83x23" value="添加" />
            	<input type="button" class="hand btn83x23b" value="取消" onclick="backToList('${path}/fun/selectAllFun.do')" />
            </p>
        </div>
	</form>
	<input type="hidden" id="permListUrl" name="permListUrl" value="${base}//perm/listPerm.do" />
    <div class="loc">&nbsp;</div>

</div></div>
</body>

