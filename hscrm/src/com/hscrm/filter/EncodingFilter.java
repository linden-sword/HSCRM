package com.hscrm.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        //post
        req.setCharacterEncoding("utf-8");
        //get:需要修改tomcat的server.xml

        //响应
        resp.setContentType("application/json;charset=utf-8");
//        resp.setContentType("text/html;charset=utf-8");
        chain.doFilter(req,resp);
    }

    @Override
    public void destroy() {

    }
}
