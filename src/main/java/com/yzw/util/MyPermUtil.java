package com.yzw.util;

import com.yzw.model.User;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import javax.wsdl.Output;
import java.util.List;

public class MyPermUtil extends TagSupport {

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int doStartTag() throws JspException {

        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        HttpSession session = request.getSession();
        //拿到user对象
        User user = (User) session.getAttribute("user");
        //获得url
        List<String> urls = user.getUrlList();
//        判断url是否和自定义标签的url相同，如果相同就显示被自定义标签包含的内容，反之则跳过
        if (urls!=null && urls.contains(url)) {
            return TagSupport.EVAL_BODY_INCLUDE;
        }
        return TagSupport.SKIP_BODY;
    }
}
