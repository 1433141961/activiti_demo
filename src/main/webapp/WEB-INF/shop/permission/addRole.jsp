<%@ include file="/ecps/console/common/taglibs.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<head>
    <title>角色添加</title>
    <meta name="heading" content="角色添加"/>
    <meta name="tag" content="tagName"/>
    <script type="text/javascript">
       function backToList(pathURL) {
           $(location).attr("href",pathURL)
       }
    </script>
</head>
<body id="main">
<div class="frameL">
    <div class="menu icon">
        <jsp:include page="/ecps/console/common/permissionmenu.jsp"/>
    </div>
</div>

<div class="frameR">
    <div class="content">

        <div class="loc icon"><samp class="t12"></samp>当前位置：权限管理&nbsp;&raquo;&nbsp;<a
                href="<c:url value="${path}/role/selectAllRole.do"/>" title="角色管理">角色管理</a>&nbsp;&raquo;&nbsp;

            <span class="gray" title="添加角色">添加角色</span>

            <a href="<c:url value="${path}/role/selectAllRole.do"/>"
               title="返回角色管理" class="inb btn120x20">返回角色管理</a>
        </div>
        <form id="form111" name="form111" action="${base}/role/addRole.do" method="post">
            <div class="edit set">
                <div class="wp92">
                    <div class="wp92">
                        <label class="alg_t">
                            <samp>*</samp>
                            角色名称：</label>
                        <input type="text" name="roleName"
                               class="text state"
                               reg="^[a-zA-Z0-9\u4e00-\u9fa5]{2,20}$"
                               tip="必须是中英文或数字字符，长度2-20"/>
                        <span><c:out value="${rolename}"/></span>
                    </div>


                </div>
                <div class="wp92">
                    <div class="wp92">
                        <label class="alg_t">角色描述：</label><textarea rows="4" cols="35" class="are" id="description"
                                                                    name="roleDesc"></textarea>
                        <span></span>
                    </div>

                    <p>
                        <label>&nbsp;</label><input type="submit" class="hand btn83x23" value="提交"/>
                        <input type="button" class="hand btn83x23b" value="取消" onclick="backToList('${path}/role/selectAllRole.do')"/>
                    </p>
                </div>
        </form>
        <input type="hidden" id="roleListUrl" name="roleListUrl" value="${path}/role/listRole.do"/>
        <div class="loc">&nbsp;</div>

    </div>
</div>
</body>

