package com.hscrm.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class CorsFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        //处理跨域:授权，谁可以访问我。
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        //允许的访问源
        response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5501");
        //允许的请求方式，get、post
        response.setHeader("Access-Control-Allow-Methods", "*");
        //用来指定本次预检请求的有效期，单位为秒
        response.setHeader("Access-Control-Max-Age", "3600");
        //请求头中必须包含的键值对
//        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Headers", "Origin,Accept,Content-Type,token");
        response.setHeader("Access-Control-Expose-Headers","token");
        //允许访问的凭证是否作为响应的一部分
        response.setHeader("Access-Control-Allow-Credentials", "true");


        //非简单请求发送前需要预检，预检没有携带token，预检不通过，报500
        //可以通过设置强行让预检通过
        if ("OPTIONS".equals(request.getMethod())){
            //如果是预检请求，直接返回OK，预检请求不需要执行
            response.setStatus(HttpServletResponse.SC_OK);
        }else {
            //不是预检请求，交给后面的过滤器链执行
            chain.doFilter(req, res);
        }
    }
    @Override
    public void destroy() {

    }
}
